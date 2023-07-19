package kipster.nt.biomes.warm;

import kipster.nt.world.gen.trees.WorldGenTreeOak;
import kipster.nt.world.gen.trees.WorldGenTreeTallSpruce;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeGreenMixedForest extends Biome 
{
	
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenBirchTree SUPER_BIRCH_TREE = new WorldGenBirchTree(false, true);
	protected static final WorldGenTreeOak OAK_TREE = new WorldGenTreeOak(false, false);
	private final WorldGenTaiga1 otherspruceGenerator = new WorldGenTaiga1();
	private final WorldGenTreeTallSpruce spruceGenerator = new WorldGenTreeTallSpruce(true);
	
	public BiomeGreenMixedForest(BiomeProperties properties)
	{	
		super(properties);
	
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.treesPerChunk = 7;
		this.decorator.flowersPerChunk = 4;
	    this.decorator.grassPerChunk = 4;
	    this.decorator.generateFalls = true;
	    
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
		
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int spruceWeight = 3;
		int superWeight = 2;
		int bigTreeWeight = 1;
		int otherSpruceWeight = 3;
		int oakWeight = 1;

		int totalWeight = spruceWeight + superWeight + bigTreeWeight + otherSpruceWeight + oakWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(this.spruceGenerator);
		treeList.add(super.getRandomTreeFeature(rand));
		treeList.add(BIG_TREE_FEATURE);
		treeList.add(this.otherspruceGenerator);
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
	
	@Override
   	public int getModdedBiomeGrassColor(int original) {
   	    return super.getModdedBiomeGrassColor(0x72B04E);
   	}

   	@Override
   	public int getModdedBiomeFoliageColor(int original) {
   	    return super.getModdedBiomeFoliageColor(0x72B04E);
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