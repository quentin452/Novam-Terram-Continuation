package kipster.nt.biomes.warm;

import kipster.nt.world.gen.trees.WorldGenTreeOak;
import net.minecraft.entity.passive.EntityWolf;
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

public class BiomeOakForestTall extends Biome 
{
	
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenTreeOak OAK_TREE = new WorldGenTreeOak(false, false);
	protected static final WorldGenTreeOak TALL_OAK_TREE = new WorldGenTreeOak(false, true);
	
	public BiomeOakForestTall(BiomeProperties properties)
	{	
		super(properties);
		
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.treesPerChunk = 10;
		this.decorator.flowersPerChunk = 1;
	    this.decorator.grassPerChunk = 3;
	    this.decorator.generateFalls = true;
	    
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
		
	}
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int tallOakWeight = 1;
		int oakWeight = 9;

		int totalWeight = tallOakWeight + oakWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(TALL_OAK_TREE);
		treeList.add(OAK_TREE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);
	}

	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
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
            }
            return true;
        }
    }
}