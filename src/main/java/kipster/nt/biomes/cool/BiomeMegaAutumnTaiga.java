package kipster.nt.biomes.cool;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.flowers.*;
import kipster.nt.world.gen.trees.WorldGenTreeBigAutumnTaigaOrange;
import kipster.nt.world.gen.trees.WorldGenTreeBigAutumnTaigaYellow;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class BiomeMegaAutumnTaiga extends Biome 
{

	protected static final WorldGenerator CHRYSANTHEMUMflower= new WorldGenChrysanthemumFlower(BlockInit.CHRYSANTHEMUMFLOWER.getDefaultState());
	protected static final WorldGenerator AMBROSIAflower = new WorldGenAlliumFlower(BlockInit.AMBROSIAFLOWER.getDefaultState());
	protected static final WorldGenerator AGAPANTHUSflower = new WorldGenAspalathusFlower(BlockInit.AGAPANTHUSFLOWER.getDefaultState());

	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenAbstractTree YELLOW_TREE = new WorldGenTreeBigAutumnTaigaYellow(true);
	protected static final WorldGenAbstractTree ORANGE_TREE = new WorldGenTreeBigAutumnTaigaOrange(true);
	private final WorldGenMegaPineTree spruceGenerator= new WorldGenMegaPineTree(false, true);
	 
	   public BiomeMegaAutumnTaiga(BiomeProperties properties)
		{	
			super(properties);
		
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		 this.decorator.generateFalls = true;
       
       this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 8, 4, 4));
       this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
       this.decorator.treesPerChunk = 5;
       this.decorator.flowersPerChunk = 2;
       this.decorator.grassPerChunk = 4;

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
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        
            if (noiseVal > 1.75D)
            {
                this.topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
            }
            else if (noiseVal > -0.95D)
            {
                this.topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
            }

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
	if (rand.nextInt(2) > 0)
	{
		  return this.spruceGenerator;
	}
	else
	{
	  return (WorldGenAbstractTree)(rand.nextInt(4) == 0 ? YELLOW_TREE : ORANGE_TREE);
	}
}
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
   {
       return rand.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
   }

   public void decorate(World worldIn, Random rand, BlockPos pos)
   {

	   int flowersPerChunk = 7;

	   generateFlowers(worldIn, rand, pos, flowersPerChunk, CHRYSANTHEMUMflower);
	   generateFlowers(worldIn, rand, pos, flowersPerChunk, AMBROSIAflower);
	   generateFlowers(worldIn, rand, pos, flowersPerChunk, AGAPANTHUSflower);

       net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
       WorldGenerator diamonds = new DiamondGenerator();
       if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, diamonds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND))
    	   diamonds.generate(worldIn, rand, pos);

       super.decorate(worldIn, rand, pos);
   }
   
	@Override
	public int getModdedBiomeGrassColor(int original) {
	    return super.getModdedBiomeGrassColor(0xA4BA6B);
	}
	@Override
	public int getModdedBiomeFoliageColor(int original) {
	    return super.getModdedBiomeFoliageColor(0xA4BA6B);
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