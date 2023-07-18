package kipster.nt.biomes.icy;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.WorldGenPatches;
import kipster.nt.world.gen.flowers.WorldGenNeglectedScorpionweedFlower;
import kipster.nt.world.gen.flowers.WorldGenSumastraFlower;
import kipster.nt.world.gen.flowers.WorldGenVeronicaFlower;
import kipster.nt.world.gen.trees.WorldGenTreeTallSpruce;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Iterator;
import java.util.Random;

	public class BiomeAlps extends Biome 
	{	
		
		protected static final WorldGenPatches GRASS = new WorldGenPatches(Blocks.GRASS.getDefaultState(), 8);
		private final WorldGenerator silverfishSpawner = new WorldGenMinable(Blocks.MONSTER_EGG.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.STONE), 9);
	    private final WorldGenTreeTallSpruce spruceGenerator = new WorldGenTreeTallSpruce(true);
		
	    public BiomeAlps(BiomeProperties properties)
		{	
			super(properties);
		
			this.decorator.generateFalls = true;
			this.decorator.treesPerChunk = 6;
			this.decorator.flowersPerChunk = 1;
		    this.decorator.grassPerChunk = 4;
		    this.spawnableCreatureList.clear();
		    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
			this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
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
		
		@Override
	    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
	        if (noiseVal > 0.90D) {
	            this.topBlock = Blocks.GRASS.getDefaultState();
	            this.fillerBlock = Blocks.DIRT.getDefaultState();  } 
	        else {
	         this.topBlock = Blocks.SNOW.getDefaultState();
	            this.fillerBlock = Blocks.SNOW.getDefaultState();
	        }

	        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

		public WorldGenAbstractTree getRandomTreeFeature(Random rand)
		{
		    return (WorldGenAbstractTree)(rand.nextInt(3) > 0 ? this.spruceGenerator : this.spruceGenerator);
		}
		
		public WorldGenerator getRandomWorldGenForGrass(Random rand)
		{
		    return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
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
		protected static final WorldGenerator NEGLECTEDSCORPIONWEEDFLOWER= new WorldGenNeglectedScorpionweedFlower(BlockInit.NEGLECTEDSCORPIONWEEDFLOWER.getDefaultState());
		protected static final WorldGenerator VERONICAFLOWER= new WorldGenVeronicaFlower(BlockInit.VERONICAFLOWER.getDefaultState());

		public void decorate(World worldIn, Random rand, BlockPos pos)
		{
			int flowersPerChunk = 4
					;
			generateFlowers(worldIn, rand, pos, flowersPerChunk, NEGLECTEDSCORPIONWEEDFLOWER);
			generateFlowers(worldIn, rand, pos, flowersPerChunk, VERONICAFLOWER);

			net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
		       WorldGenerator lapis = new LapisGenerator();
		       if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, lapis, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS))
		    	   lapis.generate(worldIn, rand, pos);
		       
		    for (int j1 = 0; j1 < 7; ++j1)
		    {
		        int k1 = rand.nextInt(16);
		        int l1 = rand.nextInt(64);
		        int i2 = rand.nextInt(16);
		        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, silverfishSpawner, pos.add(j1, k1, l1), net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.SILVERFISH))
		        this.silverfishSpawner.generate(worldIn, rand, pos.add(k1, l1, i2));
		    }
		    net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
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
		public int getModdedBiomeGrassColor(int original) {
		    return super.getModdedBiomeGrassColor(0x99C762);
		}

		@Override
		public int getModdedBiomeFoliageColor(int original) {
		    return super.getModdedBiomeFoliageColor(0x83B84D);
		}
	
	}