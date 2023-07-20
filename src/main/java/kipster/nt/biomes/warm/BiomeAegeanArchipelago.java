package kipster.nt.biomes.warm;

import kipster.nt.blocks.BlockInit;
import kipster.nt.config.MiscConfig;
import kipster.nt.world.gen.flowers.WorldGenCambridgeBlueFlower;
import kipster.nt.world.gen.flowers.WorldGenCrassulaFlower;
import kipster.nt.world.gen.trees.WorldGenTreeBigPalmTree;
import kipster.nt.world.gen.trees.WorldGenTreeCypress;
import kipster.nt.world.gen.trees.WorldGenTreeOlive;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeAegeanArchipelago extends Biome {
	protected static final WorldGenBlockBlob STONE_BOULDER_FEATURE = new WorldGenBlockBlob(Blocks.STONE, 1);
	protected static final WorldGenCambridgeBlueFlower CAMBRIDGEBLUEFLOWER = new WorldGenCambridgeBlueFlower(BlockInit.CAMBRIDGEBLUEFLOWER.getDefaultState());
	protected static final WorldGenCrassulaFlower CRASSULAFLOWER = new WorldGenCrassulaFlower(BlockInit.CRASSULAFLOWER.getDefaultState());
	protected static final WorldGenTreeBigPalmTree PALM_TREE = new WorldGenTreeBigPalmTree(false);
	protected static final WorldGenTreeOlive OLIVE_TREE = new WorldGenTreeOlive(false, false);
	protected static final WorldGenTreeCypress CYPRESS_TREE = new WorldGenTreeCypress(false, false);

	public BiomeAegeanArchipelago(BiomeProperties properties) {
		super(properties);

		this.decorator.treesPerChunk = 5;
		this.decorator.clayPerChunk = 5;
		this.decorator.generateFalls = true;
		this.decorator.gravelPatchesPerChunk = 6;
		this.decorator.sandPatchesPerChunk = 10;
		this.decorator.reedsPerChunk = 4;
		this.decorator.flowersPerChunk = 4;
		this.decorator.grassPerChunk = 4;

		// Remove slimes from spawnable monsters
		this.spawnableMonsterList.removeIf(entry -> entry.entityClass == EntitySlime.class);
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		if (noiseVal > 1.90D) {
			this.topBlock = Blocks.STONE.getDefaultState();
			this.fillerBlock = Blocks.STONE.getDefaultState();
		} else {
			this.topBlock = Blocks.GRASS.getDefaultState();
			this.fillerBlock = Blocks.STONE.getDefaultState();
		}

		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int pamltreeweight = 18;
		int olivetreeweight = 2;
		int cypresstreeweight = 1;
		int redTreeWeight = 2;
		int yellowTreeWeight = 6;

		int totalWeight = pamltreeweight + olivetreeweight + cypresstreeweight + redTreeWeight + yellowTreeWeight;
		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(PALM_TREE);
		 treeList.add(OLIVE_TREE);
		// #todo # todo fix cascadingworldgen(not sure) + not worldgenned correctly
		  treeList.add(CYPRESS_TREE);

		int treeIndex = randomWeight % treeList.size();
		WorldGenAbstractTree tree = treeList.get(treeIndex);
		return tree;

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

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {

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

		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
		WorldGenerator emeralds = new EmeraldGenerator();
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
			emeralds.generate(worldIn, rand, pos);
		super.decorate(worldIn, rand, pos);
	}

	@Override
	public Biome.TempCategory getTempCategory() {
		return Biome.TempCategory.WARM;
	}

	public static class EmeraldGenerator extends WorldGenerator {
		@Override
		public boolean generate(World worldIn, Random rand, BlockPos pos) {
			int count = 10 + rand.nextInt(6);
			for (int i = 0; i < count; i++) {
				int offset = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 8 : 0; // MC-114332
				BlockPos blockpos = pos.add(rand.nextInt(16) + offset, rand.nextInt(28) + 2, rand.nextInt(16) + offset);

				IBlockState state = worldIn.getBlockState(blockpos);
				if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, net.minecraft.block.state.pattern.BlockMatcher.forBlock(Blocks.STONE))) {
					worldIn.setBlockState(blockpos, Blocks.EMERALD_ORE.getDefaultState(), 16 | 2);
				}
			}
			return true;
		}
	}
//#todo
	// Custom WorldGenerator classes for coral reefs, palm trees, and Mediterranean plants
	// Replace with your own implementations
	private static class CoralReefGenerator extends WorldGenerator {
		@Override
		public boolean generate(World worldIn, Random rand, BlockPos pos) {
			// Generate coral reef
			return true;
		}
	}
	//#todo
	private static class LavenderGenerator extends WorldGenerator {
		@Override
		public boolean generate(World worldIn, Random rand, BlockPos pos) {
			// Generate lavender
			return true;
		}
	}
	//#todo
	private static class ThymeGenerator extends WorldGenerator {
		@Override
		public boolean generate(World worldIn, Random rand, BlockPos pos) {
			// Generate thyme
			return true;
		}
	}
	//#todo
	private static class RosemaryGenerator extends WorldGenerator {
		@Override
		public boolean generate(World worldIn, Random rand, BlockPos pos) {
			// Generate rosemary
			return true;
		}
	}
}
