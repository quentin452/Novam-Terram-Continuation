package kipster.nt.biomes.cool;

import com.google.common.collect.ImmutableSet;
import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.flowers.WorldGenAlliumFlower;
import kipster.nt.world.gen.flowers.WorldGenAspalathusFlower;
import kipster.nt.world.gen.flowers.WorldGenChrysanthemumFlower;
import kipster.nt.world.gen.flowers.WorldGenPrimevereFlower;
import kipster.nt.world.gen.trees.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.*;
import java.util.concurrent.*;

public class BiomeAutumnTaiga extends Biome
{
	protected static final WorldGenerator CHRYSANTHEMUMflower= new WorldGenChrysanthemumFlower(BlockInit.CHRYSANTHEMUMFLOWER.getDefaultState());
	protected static final WorldGenerator AMBROSIAflower = new WorldGenAlliumFlower(BlockInit.AMBROSIAFLOWER.getDefaultState());
	protected static final WorldGenerator AGAPANTHUSflower = new WorldGenAspalathusFlower(BlockInit.AGAPANTHUSFLOWER.getDefaultState());

	protected static final WorldGenAbstractTree YELLOW_TREE = new WorldGenTreeAutumnTaigaYellow(false, false);
	protected static final WorldGenAbstractTree ORANGE_TREE = new WorldGenTreeAutumnTaigaOrange(false, false);

	private static final WorldGenTreeDead DEAD_TREE = new WorldGenTreeDead(false);
	private static final WorldGenTreeShrubSpruce SHRUB_SPRUCE = new WorldGenTreeShrubSpruce();

	private final WorldGenTreeTallSpruce spruceGenerator = new WorldGenTreeTallSpruce(true);

	private static class DiamondGenerator extends WorldGenerator {
		private final Set<Block> replaceableBlocks = ImmutableSet.of(Blocks.STONE);

		@Override
		public boolean generate(World worldIn, Random rand, BlockPos pos) {
			int count = 10 + rand.nextInt(6);
			List<BlockPos> blockPositions = new ArrayList<>();

			for (int i = 0; i < count; i++) {
				int offset = ForgeModContainer.fixVanillaCascading ? 8 : 0;
				BlockPos blockpos = pos.add(rand.nextInt(16) + offset, rand.nextInt(28) + 2, rand.nextInt(16) + offset);
				blockPositions.add(blockpos);
			}

			Map<BlockPos, IBlockState> blockStates = generateBlockStatesInParallel(worldIn, blockPositions);

			for (Map.Entry<BlockPos, IBlockState> entry : blockStates.entrySet()) {
				worldIn.setBlockState(entry.getKey(), entry.getValue(), 16 | 2);
			}

			return true;
		}

		private Map<BlockPos, IBlockState> generateBlockStatesInParallel(World world, List<BlockPos> blockPositions) {
			Map<BlockPos, IBlockState> blockStates = new ConcurrentHashMap<>();

			List<Callable<Void>> tasks = new ArrayList<>();
			for (BlockPos blockPos : blockPositions) {
				tasks.add(() -> {
					IBlockState blockState = world.getBlockState(blockPos);
					if (replaceableBlocks.contains(blockState.getBlock())) {
						blockStates.put(blockPos, Blocks.DIAMOND_ORE.getDefaultState());
					}
					return null;
				});
			}

			try {
				ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
				executor.invokeAll(tasks);
				executor.shutdown();
				executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return blockStates;
		}
	}

	public BiomeAutumnTaiga(BiomeProperties properties) {
		super(properties);

		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();

		this.decorator.generateFalls = true;
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 8, 4, 4));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
		this.decorator.treesPerChunk = 7;
		this.decorator.grassPerChunk = 4;
		this.decorator.gravelPatchesPerChunk = 4;
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		rand.nextInt(1);
		if (rand.nextInt(3) > 0) {
			return this.spruceGenerator;
		} else {
			return rand.nextInt(4) == 0 ? YELLOW_TREE : ORANGE_TREE;
		}
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		if (noiseVal > 2.50D) {
			this.topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
			this.fillerBlock = Blocks.DIRT.getDefaultState();
		} else {
			this.topBlock = Blocks.GRASS.getDefaultState();
			this.fillerBlock = Blocks.DIRT.getDefaultState();
		}

		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return rand.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
	}

	public void decorate(World worldIn, Random rand, BlockPos pos) {
		int flowersPerChunk = 16;

		generateFlowers(worldIn, rand, pos, flowersPerChunk, CHRYSANTHEMUMflower);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, AMBROSIAflower);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, AGAPANTHUSflower);

		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
		WorldGenerator diamonds = new DiamondGenerator();
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, diamonds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND)) {
			diamonds.generate(worldIn, rand, pos);
		}

		super.decorate(worldIn, rand, pos);
	}

	private void generateFlowers(World worldIn, Random rand, BlockPos pos, int flowersPerChunk, WorldGenerator flowerGenerator) {
		for (int i = 0; i < flowersPerChunk; ++i) {
			// Generate a random offset in x and z directions
			int offsetX = rand.nextInt(16) + 8;
			int offsetZ = rand.nextInt(16) + 8;
			boolean success = false;

			for (int j = 0; j < 3 + rand.nextInt(3); j++) {
				BlockPos blockpos = pos.add(offsetX, rand.nextInt(10) + 60, offsetZ);

				if (flowerGenerator.generate(worldIn, rand, blockpos)) {
					success = true;
				}
			}

			if (success) {
				break; // Move on to the next group of flowers
			}
		}
	}

	@Override
	public int getModdedBiomeGrassColor(int original) {
		return super.getModdedBiomeGrassColor(0xA4BA6B);
	}

	@Override
	public int getModdedBiomeFoliageColor(int original) {
		return super.getModdedBiomeFoliageColor(0xA4BA6B);
	}
}
