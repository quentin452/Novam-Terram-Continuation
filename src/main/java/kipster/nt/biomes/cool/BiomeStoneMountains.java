package kipster.nt.biomes.cool;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.BlockStateBase;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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
		if (rand.nextInt(100) < 20) { // Chance of generating a cave, adjust the percentage as desired
			int caveHeight = 30 + rand.nextInt(20); // Adjust the height range of the cave as desired
			int caveRadius = 8 + rand.nextInt(8); // Adjust the radius of the cave as desired

			for (int y = -caveHeight; y <= 0; y++) {
				for (int x = -caveRadius; x <= caveRadius; x++) {
					for (int z = -caveRadius; z <= caveRadius; z++) {
						int distanceSq = x * x + y * y + z * z;
						int radiusSq = (int) ((caveRadius + rand.nextDouble() * 0.5) * (caveRadius + rand.nextDouble() * 0.5));
						if (distanceSq <= radiusSq) {
							BlockPos blockPos = pos.add(x, y, z);
							IBlockState blockState = world.getBlockState(blockPos);
							Block block = blockState.getBlock();

							// Replace blocks with air to create the cave
							if (block.isReplaceable(world, blockPos)) {
								world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 2);
							}
						}
					}
				}
			}
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

		IntStream.rangeClosed(-radius, radius)
				.forEach(x -> {
					IntStream.range(0, height)
							.forEach(y -> {
								IntStream.rangeClosed(-radius, radius)
										.forEach(z -> {
											double distanceSq = x * x + y * y + z * z;
											if (distanceSq <= radiusSq + rand.nextDouble() * 0.5) {
												BlockPos blockPos = pos.add(x, y, z);
												Block block = world.getBlockState(blockPos).getBlock();

												// Check if the stone block exists
												if (block == Blocks.STONE) {
													// Only replace blocks that can be replaced by stone
													if (block.isReplaceableOreGen(world.getBlockState(blockPos), world, blockPos, state -> state.getBlock() == Blocks.STONE)) {
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

														world.setBlockState(blockPos, interpolatedState, 2);
													}
												}
											}
										});
							});
				});
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

	public void decorate(World worldIn, Random rand, BlockPos pos) {
		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
		WorldGenerator diamonds = new DiamondGenerator();
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, diamonds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND))
			diamonds.generate(worldIn, rand, pos);

		for (int j1 = 0; j1 < 7; ++j1) {
			int k1 = rand.nextInt(16);
			int l1 = rand.nextInt(64);
			int i2 = rand.nextInt(16);
			if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, silverfishSpawner, pos.add(j1, k1, l1), net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.SILVERFISH))
				this.silverfishSpawner.generate(worldIn, rand, pos.add(j1, k1, l1));
		}

		// Generate rock formations
		for (BlockPos modifiedPos : modifications) {
			generateRockFormation(worldIn, rand, modifiedPos);
		}
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
