package kipster.nt.biomes.desert;

import kipster.nt.world.gen.WorldGenPatches;
import kipster.nt.world.gen.trees.WorldGenTreeShrubJungle;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BiomeLushDesert extends Biome 
{
	protected static final WorldGenPatches GRASS_PATCHES = new WorldGenPatches(Blocks.GRASS.getDefaultState(), 7);
	private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	protected static final WorldGenTreeShrubJungle SHRUB_JUNGLE = new WorldGenTreeShrubJungle();
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);

	public BiomeLushDesert(BiomeProperties properties)
	{	
		super(properties);
	
			this.decorator.treesPerChunk = 4;
			this.decorator.flowersPerChunk = 3;
			this.decorator.grassPerChunk = 14;
	        this.decorator.deadBushPerChunk = 18;
	        this.decorator.reedsPerChunk = 30;
	        this.decorator.cactiPerChunk = 25;
	        
	        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
	        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 5, 2, 6));
	        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityDonkey.class, 1, 1, 3));
	        Iterator<Biome.SpawnListEntry> iterator = this.spawnableMonsterList.iterator();

	        while (iterator.hasNext())
	        {
	            Biome.SpawnListEntry biome$spawnlistentry = iterator.next();

	            if (biome$spawnlistentry.entityClass == EntityZombie.class || biome$spawnlistentry.entityClass == EntityZombieVillager.class)
	            {
	                iterator.remove();
	            }
	        }

	        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 19, 4, 4));
	        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombieVillager.class, 1, 1, 1));
	        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityHusk.class, 80, 4, 4));
	}
	
	 public WorldGenerator getRandomWorldGenForGrass(Random rand)
	    {
	        return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
	    }

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int jungleTreeWeight = 4;
		int jungleShrubWeight = 1;

		int totalWeight = jungleTreeWeight + jungleShrubWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(new WorldGenTrees(false, 4 + rand.nextInt(7), JUNGLE_LOG, JUNGLE_LEAF, true));
		treeList.add(SHRUB_JUNGLE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);
	}
	
	@Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        if (noiseVal > 1.9D) {
            this.topBlock = Blocks.GRASS.getDefaultState();
            this.fillerBlock = Blocks.DIRT.getDefaultState(); }
        
        else {
        	
        	this.topBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
            this.fillerBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
        }

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
        
		}
	private void generateVines(World worldIn, Random rand, BlockPos pos) {
		WorldGenVines worldgenvines = new WorldGenVines();
		for (int j1 = 0; j1 < 50; ++j1) {
			int k = rand.nextInt(16) + 8;
			int l = 128;
			int i1 = rand.nextInt(16) + 8;
			worldgenvines.generate(worldIn, rand, pos.add(k, 128, i1));
		}
	}
	private void postOreGenEvent(World worldIn, Random rand, BlockPos pos) {
		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
	}

	public void decorate(World worldIn, Random rand, BlockPos pos) {
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS)) {
			generateVines(worldIn, rand, pos);
		}

		int grasspatchChance = rand.nextInt(4);
		if (grasspatchChance == 0) {
			int k6 = rand.nextInt(16) + 8;
			int l = rand.nextInt(16) + 8;
			BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
			GRASS_PATCHES.generate(worldIn, rand, blockpos);
		}

		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DESERT_WELL)) {
			if (rand.nextInt(1000) == 0) {
				int i1 = rand.nextInt(16) + 8;
				int j1 = rand.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(pos.add(i1, 0, j1)).up();
				(new WorldGenDesertWells()).generate(worldIn, rand, blockpos);
			}
		}

		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
		WorldGenerator gold = new GoldGenerator();

		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, gold, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD))
			gold.generate(worldIn, rand, pos);

		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL)) {
			if (rand.nextInt(64) == 0) {
				(new WorldGenFossils()).generate(worldIn, rand, pos);
			}
		}

		postOreGenEvent(worldIn, rand, pos);

		super.decorate(worldIn, rand, pos);
	}

	public static class GoldGenerator extends WorldGenerator
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
		   	                    worldIn.setBlockState(blockpos, Blocks.GOLD_ORE.getDefaultState(), 16 | 2);
		   	                }
		   	            }
		   	            return true;
		   	        }
		   	    }
		  	@Override
		   	public int getModdedBiomeGrassColor(int original) {
		   	    return super.getModdedBiomeGrassColor(0x94C72B);
		   	}

		   	@Override
		   	public int getModdedBiomeFoliageColor(int original) {
		   	    return super.getModdedBiomeFoliageColor(0x94C72B);
	    }
}
