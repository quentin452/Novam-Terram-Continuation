package kipster.nt.biomes.warm;

import kipster.nt.world.gen.trees.WorldGenTreePoplar;
import kipster.nt.world.gen.trees.WorldGenTreeShrubBirch;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
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

public class BiomeBirchHills extends Biome 
{
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenTreePoplar POPLAR_TREE = new WorldGenTreePoplar(false, false);
	protected static final WorldGenTreeShrubBirch SHRUB_BIRCH = new WorldGenTreeShrubBirch();
	
	public BiomeBirchHills(BiomeProperties properties)
	{	
		super(properties);
		
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.treesPerChunk = 2;
        this.decorator.flowersPerChunk = 2;
        this.decorator.grassPerChunk = 4;
	    this.decorator.generateFalls = true;
	    
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 5, 2, 6));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 5, 2, 6));
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int shrubBirchWeight = 1;
		int poplarTreeWeight = 3;

		int totalWeight = shrubBirchWeight + poplarTreeWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(SHRUB_BIRCH);
		treeList.add(POPLAR_TREE);

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
		    return super.getModdedBiomeGrassColor(0x85C15B);
		}
	 
	 @Override
	 public int getModdedBiomeFoliageColor(int original) {
		    return super.getModdedBiomeFoliageColor(0x83BF4F);
		    
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