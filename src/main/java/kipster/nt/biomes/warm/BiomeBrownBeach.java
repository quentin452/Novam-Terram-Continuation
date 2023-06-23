package kipster.nt.biomes.warm;

import kipster.nt.blocks.BlockInit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeBrownBeach extends Biome 
{
	
	
	public BiomeBrownBeach(BiomeProperties properties)
	{	
		super(properties);
        this.spawnableCreatureList.clear();
        this.topBlock = BlockInit.CONTINENTALSAND.getDefaultState();
        this.fillerBlock = BlockInit.CONTINENTALSAND.getDefaultState();
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
