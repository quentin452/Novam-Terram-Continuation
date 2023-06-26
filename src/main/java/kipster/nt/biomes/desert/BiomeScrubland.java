package kipster.nt.biomes.desert;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.flowers.WorldGenAlopecurusFlower;
import kipster.nt.world.gen.flowers.WorldGenGalanthusFlower;
import kipster.nt.world.gen.flowers.WorldGenNartheciumFlower;
import kipster.nt.world.gen.flowers.WorldGenRoyalBluebellFlower;
import kipster.nt.world.gen.trees.WorldGenTreeShrubAcacia;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Iterator;
import java.util.Random;

public class BiomeScrubland extends Biome 
{
	private static final WorldGenAbstractTree SAVANNA_TREE = new WorldGenSavannaTree(false);
	protected static final WorldGenAbstractTree SHRUB_ACACIA = new WorldGenTreeShrubAcacia();
	protected static final WorldGenLakes LAVA_LAKE_FEATURE = new WorldGenLakes(Blocks.LAVA);
	public BiomeScrubland(BiomeProperties properties)
	{	
		super(properties);
	
		 this.decorator.treesPerChunk = 1;
	     this.decorator.flowersPerChunk = 4;
	     this.decorator.grassPerChunk = 25;
	     this.decorator.deadBushPerChunk = 25;
	     this.decorator.cactiPerChunk = 3;
	        
	     this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 1, 2, 6));
	     this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityDonkey.class, 1, 1, 1));
	        
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
		
	     public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	     {
	         return (WorldGenAbstractTree)(rand.nextInt(5) > 0 ? SHRUB_ACACIA : SAVANNA_TREE);
	     }
	     
	     @Override
		   public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		       if (noiseVal > 2.50D) {
		           this.topBlock = Blocks.SAND.getDefaultState();
		           this.fillerBlock = Blocks.SAND.getDefaultState();  } 
		       else {
		        this.topBlock = Blocks.GRASS.getDefaultState();
		           this.fillerBlock = Blocks.DIRT.getDefaultState();
		       }

		       this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
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
	protected static final WorldGenerator GALANTHUSFLOWER= new WorldGenGalanthusFlower(BlockInit.GALANTHUSFLOWER.getDefaultState());
	protected static final WorldGenerator NARTHECIUMFLOWER = new WorldGenNartheciumFlower(BlockInit.NARTHECIUMFLOWER.getDefaultState());


	public void decorate(World worldIn, Random rand, BlockPos pos)
	     {
			 int flowersPerChunk = 7;

			 generateFlowers(worldIn, rand, pos, flowersPerChunk, GALANTHUSFLOWER);
			 generateFlowers(worldIn, rand, pos, flowersPerChunk, NARTHECIUMFLOWER);

	         if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA)) {
  	           int boulderChance = rand.nextInt(12);
  	           if (boulderChance == 0) {
  	            int k6 = rand.nextInt(16) + 8;
  	            int l = rand.nextInt(16) + 8;
  	             BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
  	             LAVA_LAKE_FEATURE.generate(worldIn, rand, blockpos);
  	           }
	     }
	         
	      
	         net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
	 	       WorldGenerator gold = new GoldGenerator();
	 	       if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, gold, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD))
	 	    	   gold.generate(worldIn, rand, pos);

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
	}