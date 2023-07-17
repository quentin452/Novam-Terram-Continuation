package kipster.nt.biomes.warm;

import kipster.nt.blocks.BlockInit;
import kipster.nt.config.MiscConfig;
import kipster.nt.world.gen.flowers.WorldGenCambridgeBlueFlower;
import kipster.nt.world.gen.flowers.WorldGenCrassulaFlower;
import kipster.nt.world.gen.flowers.WorldGenNeglectedScorpionweedFlower;
import kipster.nt.world.gen.flowers.WorldGenVeronicaFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

public class BiomeAegeanArchipelago extends Biome 
{
	protected static final WorldGenBlockBlob STONE_BOULDER_FEATURE = new WorldGenBlockBlob(Blocks.STONE, 1);
	
	public BiomeAegeanArchipelago(BiomeProperties properties)
	{	
		super(properties);
	
		this.decorator.treesPerChunk = 5;
		this.decorator.clayPerChunk = 5;
		this.decorator.generateFalls = true;
		this.decorator.gravelPatchesPerChunk = 6;
		this.decorator.sandPatchesPerChunk = 5;
		this.decorator.reedsPerChunk = 4;
		this.decorator.flowersPerChunk = 4;
		this.decorator.grassPerChunk = 4;
	    this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 1, 1, 1));
	    
	    }

	    @Override
	    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
	        if (noiseVal > 1.90D) {
	            this.topBlock = Blocks.STONE.getDefaultState();
	            this.fillerBlock = Blocks.STONE.getDefaultState();  } 
	        else {
	         this.topBlock = Blocks.GRASS.getDefaultState();
	            this.fillerBlock = Blocks.STONE.getDefaultState();
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
	protected static final WorldGenerator CAMBRIDGEBLUEFLOWER= new WorldGenCambridgeBlueFlower(BlockInit.CAMBRIDGEBLUEFLOWER.getDefaultState());
	protected static final WorldGenerator CRASSULAFLOWER= new WorldGenCrassulaFlower(BlockInit.CRASSULAFLOWER.getDefaultState());

	    public void decorate(World worldIn, Random rand, BlockPos pos)
	    {
	        super.decorate(worldIn, rand, pos);
			int flowersPerChunk = 2;
			generateFlowers(worldIn, rand, pos, flowersPerChunk, CAMBRIDGEBLUEFLOWER);
			generateFlowers(worldIn, rand, pos, flowersPerChunk, CRASSULAFLOWER);


			if (!MiscConfig.disableBouldersInAegeanArchipelago && net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.ROCK)) {
	            int genChance = rand.nextInt(3);
	            if (genChance == 0) {
	                int k6 = rand.nextInt(16) + 8;
	                int l = rand.nextInt(16) + 8;
	                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
	                STONE_BOULDER_FEATURE.generate(worldIn, rand, blockpos);
	            }
	        }
	        //if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
	        //if (rand.nextInt(64) == 0)  	
	        //{	            
	        //}
	        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
	        WorldGenerator emeralds = new EmeraldGenerator();
	        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
	            emeralds.generate(worldIn, rand, pos);
	      
	        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
	    }
	        @Override
	    	public int getModdedBiomeGrassColor(int original) {
	    	    return super.getModdedBiomeGrassColor(0x96db1c);
	    	}
	    	@Override
	    	public int getModdedBiomeFoliageColor(int original) {
	    	    return super.getModdedBiomeFoliageColor(0x96db1c);
	    }
	    	@Override
	    	public Biome.TempCategory getTempCategory()
	        {
	            return Biome.TempCategory.OCEAN;
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