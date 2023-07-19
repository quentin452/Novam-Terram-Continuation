package kipster.nt.biomes.icy;

import kipster.nt.world.gen.trees.WorldGenTreeBlueSpruce1;
import kipster.nt.world.gen.trees.WorldGenTreeBlueSpruce2;
import kipster.nt.world.gen.trees.WorldGenTreeShrubBlueSpruce;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeColdBlueTaiga extends Biome 
{	
	
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenAbstractTree BLUE_SPRUCE = new WorldGenTreeBlueSpruce2(false);
   private final WorldGenTreeBlueSpruce1 spruceGenerator = new WorldGenTreeBlueSpruce1();
	protected static final WorldGenAbstractTree SHRUB_SPRUCE = new WorldGenTreeShrubBlueSpruce(); 
   
   public BiomeColdBlueTaiga(BiomeProperties properties)
  	{	
	   super(properties);
	  	
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		 this.decorator.generateFalls = true;
      
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 8, 4, 4));
      this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
      this.decorator.treesPerChunk = 11;
      this.decorator.flowersPerChunk = 5;
      this.decorator.grassPerChunk = 4;

	}
  
  public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
  {
      return BlockFlower.EnumFlowerType.ALLIUM;
  }

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int spruceWeight = 1;
		int blueSpruceWeight = 1;
		int spruceShrubWeight = 1;

		int totalWeight = spruceWeight + blueSpruceWeight + spruceShrubWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(this.spruceGenerator);
		treeList.add(BLUE_SPRUCE);
		treeList.add(SHRUB_SPRUCE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);

	}
	
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
  {
      return rand.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
  }

   public void decorate(World worldIn, Random rand, BlockPos pos)
   {
       
       net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
       WorldGenerator diamonds = new LapisGenerator();
       if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, diamonds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND))
    	   diamonds.generate(worldIn, rand, pos);

       super.decorate(worldIn, rand, pos);
   }
   
   
   @Override
 	public int getModdedBiomeGrassColor(int original) {
 	    return super.getModdedBiomeGrassColor(0x6BB88C);
 	}

 	@Override
 	public int getModdedBiomeFoliageColor(int original) {
 	    return super.getModdedBiomeFoliageColor(0x4AAE68);
 	}
 	
	 @Override
	    public void addDefaultFlowers()
	    {
	        addFlower(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), BlockFlower.EnumFlowerType.ALLIUM), 20);
	    }
	
	 public static class LapisGenerator extends WorldGenerator
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
	                    worldIn.setBlockState(blockpos, Blocks.LAPIS_ORE.getDefaultState(), 16 | 2);
	                }
	                net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
	            }
	            return true;
	        }
	    }
}