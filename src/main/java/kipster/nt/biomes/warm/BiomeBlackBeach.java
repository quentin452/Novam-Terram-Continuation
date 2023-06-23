package kipster.nt.biomes.warm;

import kipster.nt.blocks.BlockInit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;



public class BiomeBlackBeach extends Biome 
{
	
	protected static final WorldGenLakes LAVA_LAKE_FEATURE = new WorldGenLakes(Blocks.LAVA);
	public BiomeBlackBeach(BiomeProperties properties)
	{	
		super(properties);
        this.spawnableCreatureList.clear();
        this.topBlock = BlockInit.BLACKSAND.getDefaultState();
        this.fillerBlock = BlockInit.BLACKSAND.getDefaultState();
        this.decorator.treesPerChunk = -999;
        this.decorator.deadBushPerChunk = 0;
        this.decorator.reedsPerChunk = 0;
        this.decorator.cactiPerChunk = 0;
    }
	
	 public void decorate(World worldIn, Random rand, BlockPos pos)
     {
         super.decorate(worldIn, rand, pos);
         
         net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
         WorldGenerator emeralds = new EmeraldGenerator();
         if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
             emeralds.generate(worldIn, rand, pos);
         
	      if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA)) {
	           int boulderChance = rand.nextInt(12);
	           if (boulderChance == 0) {
	            int k6 = rand.nextInt(16) + 8;
	            int l = rand.nextInt(16) + 8;
	             BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
	             LAVA_LAKE_FEATURE.generate(worldIn, rand, blockpos);
	           }
	      }
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

