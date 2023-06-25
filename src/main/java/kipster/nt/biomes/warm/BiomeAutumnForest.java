package kipster.nt.biomes.warm;

import kipster.nt.world.gen.trees.*;
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
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

public class BiomeAutumnForest extends Biome 
{	
	
	 protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenTreeDead DEAD_TREE = new WorldGenTreeDead(false);
	protected static final WorldGenTreeAutumnOrange ORANGE_TREE = new WorldGenTreeAutumnOrange(false, false);
	protected static final WorldGenTreeBigAutumnRed RED_TREE = new WorldGenTreeBigAutumnRed(false);
	protected static final WorldGenTreeAutumnYellow YELLOW_TREE = new WorldGenTreeAutumnYellow(false, false);
	protected static final WorldGenTreeShrubOak SHRUB_OAK = new WorldGenTreeShrubOak();
	 
   public BiomeAutumnForest(BiomeProperties properties)
	{	
		super(properties);
	
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		 this.decorator.generateFalls = true;
       
       this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 8, 4, 4));
       this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
       this.decorator.treesPerChunk = 8;
       this.decorator.flowersPerChunk = 2;
       this.decorator.grassPerChunk = 5;

	}
   
   @Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
	if (rand.nextInt(1) > 0)
	{
		  return SHRUB_OAK;
	}
	
	else if (rand.nextInt(3) > 0)
	{
		  return (WorldGenAbstractTree)(rand.nextInt(4) == 0 ? ORANGE_TREE : DEAD_TREE);
	}
	
	else 
	{
		  return (WorldGenAbstractTree)(rand.nextInt(4) == 0 ? RED_TREE : YELLOW_TREE);
		
		}
	}
   
   @Override
   public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
       if (noiseVal > 2.50D) {
           this.topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
           this.fillerBlock = Blocks.DIRT.getDefaultState();  } 
       else {
        this.topBlock = Blocks.GRASS.getDefaultState();
           this.fillerBlock = Blocks.DIRT.getDefaultState();
       }

       this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
}
   

	public WorldGenerator getRandomWorldGenForGrass(Random rand)
   {
       return rand.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
   }

   public void decorate(World worldIn, Random rand, BlockPos pos)
   {

       DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.FERN);

       if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS))
       for (int i1 = 0; i1 < 7; ++i1)
       {
           int j1 = rand.nextInt(16) + 8;
           int k1 = rand.nextInt(16) + 8;
           int l1 = rand.nextInt(worldIn.getHeight(pos.add(j1, 0, k1)).getY() + 32);
           DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j1, l1, k1));
       }
       
       net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
       WorldGenerator emeralds = new EmeraldGenerator();
       if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
           emeralds.generate(worldIn, rand, pos);

           net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));

       super.decorate(worldIn, rand, pos);
   }
   
	@Override
	public int getModdedBiomeGrassColor(int original) {
		return super.getModdedBiomeGrassColor(0xA4BA6B);
		//return super.getModdedBiomeGrassColor(0xB5C558);
		}
	@Override
	public int getModdedBiomeFoliageColor(int original) {
		return super.getModdedBiomeFoliageColor(0x96D065);
		//return super.getModdedBiomeFoliageColor(0x9DC558);
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