package kipster.nt.biomes.warm;

import kipster.nt.world.gen.trees.WorldGenTreeJacaranda;
import kipster.nt.world.gen.trees.WorldGenTreePaulownia;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeRoyalForest extends Biome 
{
	
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenTreeJacaranda JACARANDA_TREE = new WorldGenTreeJacaranda(false, false);
	protected static final WorldGenTreePaulownia PAULOWNIA_TREE = new WorldGenTreePaulownia(false);
	
	public BiomeRoyalForest(BiomeProperties properties)
	{	
		super(properties);
		
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.treesPerChunk = 7;
		this.decorator.flowersPerChunk = 14;
	    this.decorator.grassPerChunk = 7;
	    this.decorator.generateFalls = true;
	    
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
	    this.flowers.clear();
        for (BlockFlower.EnumFlowerType type : BlockFlower.EnumFlowerType.values())
        {
            if (type.getBlockType() == BlockFlower.EnumFlowerColor.YELLOW) continue;
            if (type == BlockFlower.EnumFlowerType.BLUE_ORCHID) type = BlockFlower.EnumFlowerType.POPPY;
            addFlower(net.minecraft.init.Blocks.RED_FLOWER.getDefaultState().withProperty(net.minecraft.init.Blocks.RED_FLOWER.getTypeProperty(), type), 10);
        }
	}
	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
	{
            double d0 = MathHelper.clamp((1.0D + GRASS_COLOR_NOISE.getValue((double)pos.getX() / 48.0D, (double)pos.getZ() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
            BlockFlower.EnumFlowerType blockflower$enumflowertype = BlockFlower.EnumFlowerType.values()[(int)(d0 * (double)BlockFlower.EnumFlowerType.values().length)];
            return blockflower$enumflowertype == BlockFlower.EnumFlowerType.BLUE_ORCHID ? BlockFlower.EnumFlowerType.POPPY : blockflower$enumflowertype;
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int jacarandaWeight = 1;
		int paulowniaWeight = 3;

		int totalWeight = jacarandaWeight + paulowniaWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(JACARANDA_TREE);
		treeList.add(PAULOWNIA_TREE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);
	}

	public void addDoublePlants(World world, Random random, BlockPos pos, int count) {

		ArrayList<BlockDoublePlant.EnumPlantType> validPlantTypes = new ArrayList<>();
		validPlantTypes.add(BlockDoublePlant.EnumPlantType.SYRINGA);
		validPlantTypes.add(BlockDoublePlant.EnumPlantType.ROSE);
		validPlantTypes.add(BlockDoublePlant.EnumPlantType.PAEONIA);

		BlockDoublePlant.EnumPlantType plantType = getRandomPlantType(random, validPlantTypes);
		DOUBLE_PLANT_GENERATOR.setPlantType(plantType);

		for (int i = 0; i < count; i++) {

			int x = random.nextInt(16) + 8;
			int z = random.nextInt(16) + 8;
			BlockPos plantPos = pos.add(x, findTopNonAirBlock(world, pos.add(x, 0, z)), z);

			DOUBLE_PLANT_GENERATOR.generate(world, random, plantPos);
		}
	}

	private BlockDoublePlant.EnumPlantType getRandomPlantType(Random random,
															  ArrayList<BlockDoublePlant.EnumPlantType> plantTypes) {
		return plantTypes.get(random.nextInt(plantTypes.size()));
	}

	private int findTopNonAirBlock(World world, BlockPos pos) {
		for (int y = 255; y >=0 ; y--) {
			Block block = world.getBlockState(pos.add(0, y , 0)).getBlock();
			if (block != Blocks.AIR) return y+1;
		}
		return -1;
	}
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		 net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
	        WorldGenerator emeralds = new EmeraldGenerator();
	        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
	            emeralds.generate(worldIn, rand, pos);

		 DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

		 if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
		 for (int i = 0; i < 7; ++i)
		 {
		     int j = rand.nextInt(16) + 8;
		     int k = rand.nextInt(16) + 8;
		     int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
		     DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
		 }
		 if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS))
		 { // no tab for patch
		 int i = rand.nextInt(5) - 3;
		 {
		     i += 2;
		 }

		 this.addDoublePlants(worldIn, rand, pos, i);
		 }
	    super.decorate(worldIn, rand, pos);
	        }
	
	@Override
   	public int getModdedBiomeGrassColor(int original) {
   	    return super.getModdedBiomeGrassColor(0x55B450);
   	}

   	@Override
   	public int getModdedBiomeFoliageColor(int original) {
   	    return super.getModdedBiomeFoliageColor(0x3AA933);
   	}
	
	public static class EmeraldGenerator extends WorldGenerator
    {
        @Override
        public boolean generate(World worldIn, Random rand, BlockPos pos)
        {
           int count = 10 + rand.nextInt(6);
            for (int i = 0; i < count; i++)
            {
                int offset = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 8 : 0; // MC-114332
                BlockPos blockpos = pos.add(rand.nextInt(16) + offset, rand.nextInt(28) + 2, rand.nextInt(16) + offset);

                net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
                if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, net.minecraft.block.state.pattern.BlockMatcher.forBlock(Blocks.STONE)))
                {
                    worldIn.setBlockState(blockpos, Blocks.EMERALD_ORE.getDefaultState(), 16 | 2);
                }
            }
            return true;
        }
    }
}