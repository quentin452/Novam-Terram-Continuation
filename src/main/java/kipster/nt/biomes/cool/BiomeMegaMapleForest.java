package kipster.nt.biomes.cool;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.flowers.WorldGenAeschynanthusFlower;
import kipster.nt.world.gen.flowers.WorldGenCephalophyllumFlower;
import kipster.nt.world.gen.flowers.WorldGenDisaFlower;
import kipster.nt.world.gen.trees.WorldGenTreeBigMaple;
import kipster.nt.world.gen.trees.WorldGenTreeMegaRedSpruce;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeMegaMapleForest extends Biome 
{	
   
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenAbstractTree MAPLE_TREE = new WorldGenTreeBigMaple(false);
	private final WorldGenTreeMegaRedSpruce spruceGenerator= new WorldGenTreeMegaRedSpruce(false, true);

	protected static final WorldGenerator CEPHALOPHYLLIUMflower= new WorldGenCephalophyllumFlower(BlockInit.CEPHALOPHYLLUMFLOWER.getDefaultState());
	protected static final WorldGenerator DISAflower = new WorldGenDisaFlower(BlockInit.DISAFLOWER.getDefaultState());
	protected static final WorldGenerator AESCHYNANTHUSflower = new WorldGenAeschynanthusFlower(BlockInit.AESCHYNANTHUSFLOWER.getDefaultState());

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
	public BiomeMegaMapleForest(BiomeProperties properties)
		{	
			super(properties);
		
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
       
       this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 8, 4, 4));
       this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
       this.decorator.treesPerChunk = 5;
       this.decorator.flowersPerChunk = 2;
       this.decorator.grassPerChunk = 4;

	}
	
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        
            if (noiseVal > 1.75D)
            {
                this.topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
            }
            else if (noiseVal > -0.95D)
            {
                this.topBlock = BlockInit.REDPODZOL.getDefaultState();
            }

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int spruceWeight = 2;
		int mapleWeight = 1;

		int totalWeight = spruceWeight + mapleWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(this.spruceGenerator);
		treeList.add(MAPLE_TREE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);

	}

	public WorldGenerator getRandomWorldGenForGrass(Random rand)
   {
       return rand.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
   }

   public void decorate(World worldIn, Random rand, BlockPos pos)
   {

	   int flowersPerChunk = 7;

	   generateFlowers(worldIn, rand, pos, flowersPerChunk, CEPHALOPHYLLIUMflower);
	   generateFlowers(worldIn, rand, pos, flowersPerChunk, DISAflower);
	   generateFlowers(worldIn, rand, pos, flowersPerChunk, AESCHYNANTHUSflower);

       net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
       WorldGenerator diamonds = new DiamondGenerator();
       if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, diamonds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND))
    	   diamonds.generate(worldIn, rand, pos);
       

       super.decorate(worldIn, rand, pos);
   }
   
   @Override
 	public int getModdedBiomeGrassColor(int original) {
 	    return super.getModdedBiomeGrassColor(0x9FBB53);
 	}

 	@Override
 	public int getModdedBiomeFoliageColor(int original) {
 	    return super.getModdedBiomeFoliageColor(0xD44335);
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
	            }
	            return true;
	        }
	    }
}