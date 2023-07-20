package kipster.nt.biomes.cool;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.flowers.WorldGenAcalyphaRFlower;
import kipster.nt.world.gen.flowers.WorldGenCanaigreFlower;
import kipster.nt.world.gen.trees.WorldGenTreeShrubSpruce2;
import kipster.nt.world.gen.trees.WorldGenTreeSpruce3;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeShrubland extends Biome 
{
	

	protected static final WorldGenTreeShrubSpruce2 SHRUB_SPRUCE = new WorldGenTreeShrubSpruce2();
	protected static final WorldGenTreeSpruce3 SPRUCE = new WorldGenTreeSpruce3();
	
	public BiomeShrubland(BiomeProperties properties)
	{	
		super(properties);
	
		
		this.decorator.treesPerChunk = 3;
        this.decorator.flowersPerChunk = 2;
        this.decorator.grassPerChunk = 7;
	    this.decorator.generateFalls = true;
	    
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 5, 2, 6));
	}
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int spruceWeight = 2;
		int spruceShrubWeight = 1;

		int totalWeight = spruceWeight + spruceShrubWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(SPRUCE);
		treeList.add(SHRUB_SPRUCE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);

	}
	
	@Override
	   public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
	       if (noiseVal > 3.50D) {
	           this.topBlock = Blocks.STONE.getDefaultState();
	           this.fillerBlock = Blocks.STONE.getDefaultState();  } 
	       else {
	        this.topBlock = Blocks.GRASS.getDefaultState();
	           this.fillerBlock = Blocks.DIRT.getDefaultState();
	       }

	       this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}
	private void generateFlowers(World worldIn, Random rand, BlockPos pos, int flowersPerChunk, WorldGenerator flowerGenerator) {
		for (int i = 0; i < flowersPerChunk; ++i) {

			// Generate a random offset in x and z directions
			int offsetX = rand.nextInt(16) + 8;
			int offsetZ = rand.nextInt(16) + 8;

			boolean success = false;

			for (int j = 0; j < 3 + rand.nextInt(3); j++) {
				BlockPos blockpos = pos.add(
						offsetX,                  // Use random x offset
						rand.nextInt(10) + 60,
						offsetZ);                 // Use random z offset

				if (flowerGenerator.generate(worldIn, rand, blockpos)) {
					success = true;
				}
			}

			if (success) {
				break; // Move on to the next group of flowers
			}
		}
	}
	protected static final WorldGenCanaigreFlower CANAIGREFLOWER = new WorldGenCanaigreFlower(BlockInit.CANAIGREFLOWER.getDefaultState());
	protected static final WorldGenAcalyphaRFlower ACALYPHARFLOWER = new WorldGenAcalyphaRFlower(BlockInit.ACALYPHARFLOWER.getDefaultState());

	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		int flowersPerChunk = 4;

		generateFlowers(worldIn, rand, pos, flowersPerChunk, CANAIGREFLOWER);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, ACALYPHARFLOWER);

		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
	        WorldGenerator emeralds = new DiamondGenerator();
	        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
	            emeralds.generate(worldIn, rand, pos);

	    super.decorate(worldIn, rand, pos);
		}

	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
        return BlockFlower.EnumFlowerType.WHITE_TULIP;
    }

	public WorldGenerator getRandomWorldGenForGrass(Random rand)
   {
       return rand.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
   }

	 @Override
	    public void addDefaultFlowers()
	    {
	        addFlower(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), BlockFlower.EnumFlowerType.WHITE_TULIP), 10);
	    }
	
	 public static class DiamondGenerator extends WorldGenerator
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
	                    worldIn.setBlockState(blockpos, Blocks.DIAMOND_ORE.getDefaultState(), 16 | 2);
	                }
	                net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
	            }
	            return true;
	        }
	    }
	 
	 @Override
	    public int getGrassColorAtPos(BlockPos pos)
	    {
	        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
	        return d0 < -0.1D ? super.getModdedBiomeGrassColor(0x5C9A49) : super.getModdedBiomeGrassColor(0x6C9C45);
	    }
	 
	 
	 @Override
	 public int getModdedBiomeFoliageColor(int original) {
		    return super.getModdedBiomeFoliageColor(0x4F9C37);
		    
	}
}