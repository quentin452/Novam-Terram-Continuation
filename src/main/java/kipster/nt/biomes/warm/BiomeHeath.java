package kipster.nt.biomes.warm;

import kipster.nt.world.gen.trees.WorldGenTreeShrubAcacia;
import kipster.nt.world.gen.trees.WorldGenTreeShrubDarkOak;
import kipster.nt.world.gen.trees.WorldGenTreeShrubJacaranda;
import kipster.nt.world.gen.trees.WorldGenTreeShrubOak;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeHeath extends Biome 
{

	protected static final WorldGenAbstractTree SHRUB_JACARANDA = new WorldGenTreeShrubJacaranda();
	protected static final WorldGenAbstractTree SHRUB_DARKOAK = new WorldGenTreeShrubDarkOak();
	protected static final WorldGenAbstractTree SHRUB_OAK = new WorldGenTreeShrubOak();
	protected static final WorldGenAbstractTree SHRUB_ACACIA = new WorldGenTreeShrubAcacia();
	
	public BiomeHeath(BiomeProperties properties)
	{	
		super(properties);
	
		this.decorator.extraTreeChance = 2F;
		this.decorator.treesPerChunk = 3;
        this.decorator.flowersPerChunk = 10;
        this.decorator.grassPerChunk = 11;
        this.decorator.deadBushPerChunk = 25;
        this.decorator.sandPatchesPerChunk = 10;
	    
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityDonkey.class, 1, 1, 3));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 5, 2, 6));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCow.class, 8, 4, 4));
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int jacarandaShrubWeight = 1;
		int acaciaShrubWeight  = 4;
		int oakShrubWeight = 3;
		int darkOakShrubWeight = 1;

		int totalWeight = jacarandaShrubWeight + acaciaShrubWeight +
				oakShrubWeight + darkOakShrubWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(SHRUB_JACARANDA);
		treeList.add(SHRUB_ACACIA);
		treeList.add(SHRUB_OAK);
		treeList.add(SHRUB_DARKOAK);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);
	}
	
	
	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
		if (rand.nextInt(2) > 0)
		{
			return BlockFlower.EnumFlowerType.HOUSTONIA;
		}
		else
		{
        return BlockFlower.EnumFlowerType.ALLIUM;
    }
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
	
	
	@Override
	   public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
	       if (noiseVal > 2.50D) {
	           this.topBlock = Blocks.SAND.getDefaultState();
	           this.fillerBlock = Blocks.SAND.getDefaultState();  } 
	       else {
	        this.topBlock = Blocks.GRASS.getDefaultState();
	           this.fillerBlock = Blocks.DIRT.getDefaultState();
	       }

	       this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}
	
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		
		 if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS))
		 { // no tab for patch
		 int i = rand.nextInt(5) - 3;
		 {
		     i += 2;
		 }

		 this.addDoublePlants(worldIn, rand, pos, i);
		 }
		 net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
	        WorldGenerator emeralds = new EmeraldGenerator();
	        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
	            emeralds.generate(worldIn, rand, pos);
		
	    super.decorate(worldIn, rand, pos);
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
	 
	 @Override
	    public void addDefaultFlowers()
	    {
	        addFlower(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), BlockFlower.EnumFlowerType.ALLIUM), 20);
	    }
	    
	 @Override
	    public int getGrassColorAtPos(BlockPos pos)
	    {
	        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
	        return d0 < -0.1D ? super.getModdedBiomeGrassColor(0x9FB068) : super.getModdedBiomeGrassColor(0xAFC568);
	    }

	 @Override
	 public int getModdedBiomeFoliageColor(int original) {
		    return super.getModdedBiomeFoliageColor(0xAFC568);
	   	}

}