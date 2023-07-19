package kipster.nt.biomes.icy;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.WorldGenPatches;
import kipster.nt.world.gen.trees.WorldGenTreeShrubSpruce;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BiomeColdDesert extends Biome 
{
	protected static final WorldGenPatches GRAVEL_PATCHES = new WorldGenPatches(Blocks.GRAVEL.getDefaultState(), 5);
	protected static final WorldGenPatches SNOW_PATCHES = new WorldGenPatches(Blocks.SNOW.getDefaultState(), 5);
	protected static final WorldGenAbstractTree SHRUB_SPRUCE = new WorldGenTreeShrubSpruce();
	protected static final WorldGenPatches DIRT_PATCHES = new WorldGenPatches(Blocks.DIRT.getDefaultState(), 5);
	
	
	public BiomeColdDesert(BiomeProperties properties)
	{	
		super(properties);
	
		this.decorator.cactiPerChunk = 5;
		this.decorator.deadBushPerChunk = 15;
		this.decorator.extraTreeChance = 0.07F;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 2;
	    this.decorator.generateFalls = true;
	    
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPolarBear.class, 1, 1, 2));
        Iterator<Biome.SpawnListEntry> iterator = this.spawnableMonsterList.iterator();

        while (iterator.hasNext())
        {
            Biome.SpawnListEntry biome$spawnlistentry = iterator.next();

            if (biome$spawnlistentry.entityClass == EntitySkeleton.class)
            {
                iterator.remove();
            }
        }

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySkeleton.class, 20, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityStray.class, 80, 4, 4));
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int spruceShrubWeight = 1;

		int totalWeight = spruceShrubWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(SHRUB_SPRUCE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);

	}
	
	@Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        if (noiseVal > 1D) {
        	topBlock = BlockInit.COLDSAND.getDefaultState();
    		fillerBlock = BlockInit.COLDSAND.getDefaultState();}
    		
        else {
        	
         this.topBlock = Blocks.SAND.getDefaultState();
            this.fillerBlock = Blocks.SAND.getDefaultState();
        }

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
        
		}
	
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		  net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
	       WorldGenerator lapis = new LapisGenerator();
	       if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, lapis, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS))
	    	   lapis.generate(worldIn, rand, pos);
	       
		int stonepatchChance = rand.nextInt(4);
		if (stonepatchChance == 0) {
			int k6 = rand.nextInt(16) + 8;
			int l = rand.nextInt(16) + 8;
			BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
			GRAVEL_PATCHES.generate(worldIn, rand, blockpos);
		}
	    
			int snowpatchChance = rand.nextInt(4);
			if (snowpatchChance == 0) {
				int k6 = rand.nextInt(16) + 8;
				int l = rand.nextInt(16) + 8;
				BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
				SNOW_PATCHES.generate(worldIn, rand, blockpos);
			}
			
	    super.decorate(worldIn, rand, pos);
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
	 @Override
	    public int getGrassColorAtPos(BlockPos pos)
	    {
	        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
	        return d0 < -0.1D ? super.getModdedBiomeGrassColor(0xAB853E) : super.getModdedBiomeGrassColor(0xB46438);
	    }
		@Override
		public int getModdedBiomeFoliageColor(int original) {
		    return super.getModdedBiomeFoliageColor(0xB2893A);
	}
}