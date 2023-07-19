package kipster.nt.biomes.warm;

import kipster.nt.world.gen.trees.WorldGenTreePoplar;
import kipster.nt.world.gen.trees.WorldGenTreeShrubBirch;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeMeadow extends Biome 
{
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenAbstractTree POPLAR_TREE = new WorldGenTreePoplar(false, false);
	protected static final WorldGenAbstractTree SHRUB_BIRCH = new WorldGenTreeShrubBirch();
	
	public BiomeMeadow(BiomeProperties properties)
	{	
		super(properties);
	
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.extraTreeChance = 8.3F;
        this.decorator.flowersPerChunk = 2;
        this.decorator.grassPerChunk = 1;
	    this.decorator.generateFalls = true;
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 5, 2, 6));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 4, 4));
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int poplarTreeWeight = 2;
		int birchShrubWeight = 1;

		int totalWeight = poplarTreeWeight + birchShrubWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(POPLAR_TREE);
		treeList.add(SHRUB_BIRCH);

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
   	    return super.getModdedBiomeGrassColor(0x92C951);
   	}

   	@Override
   	public int getModdedBiomeFoliageColor(int original) {
   	    return super.getModdedBiomeFoliageColor(0x73AC42);
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
	                net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
	            }
	            return true;
	        }
	    }
	 
}