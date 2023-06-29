package kipster.nt.biomes.cool;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.flowers.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeModContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeStoneMountains extends Biome {
	private final WorldGenerator silverfishSpawner = new WorldGenMinable(Blocks.MONSTER_EGG.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.STONE), 9);
	private final WorldGenTaiga2 spruceGenerator = new WorldGenTaiga2(false);
	List<BlockPos> modifications = new ArrayList<>();

	public BiomeStoneMountains(BiomeProperties properties) {
		super(properties);

		this.decorator.treesPerChunk = 2;
		this.decorator.flowersPerChunk = 2;
		this.decorator.grassPerChunk = 3;
		this.decorator.generateFalls = true;

		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityLlama.class, 5, 4, 6));
	}
	private void generateCaves(World world, Random rand, BlockPos pos) {
		if (rand.nextInt(100) < 20) {
			int caveHeight = 30 + rand.nextInt(20);
			int caveRadius = 8 + rand.nextInt(8);

			List<BlockPos> modifiedBlocks = new ArrayList<>();

			// Get base block state
			IBlockState baseBlockState = world.getBlockState(pos);
			boolean isReplaceable = baseBlockState.getBlock().isReplaceable(world, pos);

			for (int y = -caveHeight; y <= 0; y++) {
				for (int x = -caveRadius; x <= caveRadius; x++) {
					if (isReplaceable) {
						modifiedBlocks.add(pos.add(x, y, x));
					}
				}
			}

			// Apply block changes in bulk
			for (BlockPos blockPos : modifiedBlocks) {
				world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 2);
			}

			modifiedBlocks.clear();
		}
	}

	private static void generateRockFormation(World world, Random rand, BlockPos pos) {
		int radius = 2 + rand.nextInt(6);
		int height = 2 + rand.nextInt(5);

		IBlockState stoneState = Blocks.STONE.getDefaultState();
		IBlockState graniteState = stoneState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE);
		IBlockState dioriteState = stoneState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE);
		IBlockState andesiteState = stoneState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE);

		double radiusSq = radius * radius;

		List<BlockPos> modifiedBlocks = new ArrayList<>();

		// Get the block state before the loops
		IBlockState baseBlockState = world.getBlockState(pos);

		for (int x = -radius; x <= radius; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = -radius; z <= radius; z++) {
					double distanceSq = x * x + y * y + z * z;
					BlockPos blockPos = pos.add(x, y, z);

					// Check if the block position is within the StoneMountains biome
					BlockPos biomeCheckPos = pos.add(x, y - 1, z); // Use separate BlockPos for biome check
					if (world.getBiome(biomeCheckPos) instanceof BiomeStoneMountains) {

						// Check if the stone block exists
						if (baseBlockState.getBlock() == Blocks.STONE) {
							// Only replace blocks that can be replaced by stone
							if (baseBlockState.getBlock().isReplaceableOreGen(baseBlockState, world, blockPos, state -> state.getBlock() == Blocks.STONE)) {
								// Calculate the blend factor based on distance from the center
								double blendFactor = 1.0 - (distanceSq / radiusSq);

								// Interpolate between the stone and variant states based on the blend factor
								IBlockState interpolatedState;
								if (blendFactor < 0.2) {
									interpolatedState = stoneState;
								} else if (blendFactor < 0.4) {
									interpolatedState = graniteState;
								} else if (blendFactor < 0.6) {
									interpolatedState = dioriteState;
								} else if (blendFactor < 0.8) {
									interpolatedState = andesiteState;
								} else {
									interpolatedState = andesiteState;
								}

								modifiedBlocks.add(blockPos);
								world.setBlockState(blockPos, interpolatedState, 2);
							}
						}
					}
				}
			}
		}

		// Batch the block state changes and apply them in bulk
		for (BlockPos modifiedPos : modifiedBlocks) {
			world.notifyBlockUpdate(modifiedPos, world.getBlockState(modifiedPos), world.getBlockState(modifiedPos), 3);
		}
		modifiedBlocks.clear();
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		if (noiseVal > 2D) {
			this.topBlock = Blocks.COBBLESTONE.getDefaultState();
		} else {
			this.topBlock = Blocks.STONE.getDefaultState();
		}
		this.fillerBlock = Blocks.STONE.getDefaultState();

		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return rand.nextInt(3) > 0 ? this.spruceGenerator : super.getRandomTreeFeature(rand);
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
	protected static final WorldGenerator FRUITBOTFLOWER= new WorldGenFruitTopFlower(BlockInit.FRUITBOTFLOWER.getDefaultState());
	protected static final WorldGenerator ALCEAFLOWER = new WorldGenAlceaFlower(BlockInit.ALCEAFLOWER.getDefaultState());

	protected static final WorldGenerator AJUGAFLOWER= new WorldGenAjugaFlower(BlockInit.AJUGAFLOWER.getDefaultState());
	protected static final WorldGenerator ASCLEPIASFLOWER = new WorldGenAsclepiasFlower(BlockInit.ASCLEPIASFLOWER.getDefaultState());

	public void decorate(World worldIn, Random rand, BlockPos pos) {
		int flowersPerChunk = 7;

		generateFlowers(worldIn, rand, pos, flowersPerChunk, FRUITBOTFLOWER);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, ALCEAFLOWER);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, AJUGAFLOWER);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, ASCLEPIASFLOWER);


		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
		WorldGenerator diamonds = new DiamondGenerator();
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, diamonds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND))
			diamonds.generate(worldIn, rand, pos);

		if (worldIn.getBiome(pos) instanceof BiomeStoneMountains) {
			this.silverfishSpawner.generate(worldIn, rand, pos);
		}

		// Generate rock formations
	    generateRockFormation(worldIn, rand, pos);

		// Generate caves
		generateCaves(worldIn, rand, pos);

		super.decorate(worldIn, rand, pos);
	}

	public static class DiamondGenerator extends WorldGenerator {
		public boolean generate(World worldIn, Random rand, BlockPos pos) {
			int count = 10 + rand.nextInt(6);
			for (int i = 0; i < count; i++) {
				int offset = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 8 : 0; // MC-114332
				BlockPos blockpos = pos.add(rand.nextInt(16) + offset, rand.nextInt(28) + 2, rand.nextInt(16) + offset);

				net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
				Block stoneBlock = Blocks.STONE;

				if (stoneBlock != null && state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, net.minecraft.block.state.pattern.BlockMatcher.forBlock(stoneBlock))) {
					worldIn.setBlockState(blockpos, Blocks.DIAMOND_ORE.getDefaultState(), 16 | 2);
				}
			}

			return true;
		}
	}
}
