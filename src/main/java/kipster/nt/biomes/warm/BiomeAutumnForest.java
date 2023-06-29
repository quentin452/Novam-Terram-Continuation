package kipster.nt.biomes.warm;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.flowers.*;
import kipster.nt.world.gen.trees.*;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeAutumnForest extends Biome {
	protected static final WorldGenerator ALLIARIAFLOWER = new WorldGenAlliariaFlower(BlockInit.ALLIARIAFLOWER.getDefaultState());
	protected static final WorldGenerator PLUCHEAGLUTONISAFLOWER = new WorldGenPlucheaGlutinosaFlower(BlockInit.PLUCHEAGLUTONISAFLOWER.getDefaultState());
	protected static final WorldGenerator ACANTHUSBOTFLOWER = new WorldGenAcanthusBotFlower(BlockInit.ACANTHUSBOTFLOWER.getDefaultState());

	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenTreeAutumnOrange ORANGE_TREE = new WorldGenTreeAutumnOrange(false, false);
	protected static final WorldGenTreeBigAutumnRed RED_TREE = new WorldGenTreeBigAutumnRed(false);
	protected static final WorldGenTreeAutumnYellow YELLOW_TREE = new WorldGenTreeAutumnYellow(false, false);
	protected static final WorldGenTreeShrubOak SHRUB_OAK = new WorldGenTreeShrubOak();
	protected static final WorldGenTreeDead DEAD_TREE = new WorldGenTreeDead(false);

	public BiomeAutumnForest(BiomeProperties properties) {
		super(properties);
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();

		this.decorator.generateFalls = true;

		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 8, 4, 4));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
		this.decorator.treesPerChunk = 8;
		this.decorator.flowersPerChunk = 2;
		this.decorator.grassPerChunk = 5;
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		int shrubOakWeight = 18;
		int orangeTreeWeight = 2;
		int deadTreeWeight = 1;
		int redTreeWeight = 2;
		int yellowTreeWeight = 6;

		int totalWeight = shrubOakWeight + orangeTreeWeight + deadTreeWeight + redTreeWeight + yellowTreeWeight;
		int randomWeight = rand.nextInt(totalWeight);

		if (randomWeight < shrubOakWeight) {
			return SHRUB_OAK;
		} else if (randomWeight < shrubOakWeight + orangeTreeWeight) {
			return ORANGE_TREE;
		} else if (randomWeight < shrubOakWeight + orangeTreeWeight + deadTreeWeight) {
			return DEAD_TREE;
		} else if (randomWeight < shrubOakWeight + orangeTreeWeight + deadTreeWeight + redTreeWeight) {
			return RED_TREE;
		} else {
			return YELLOW_TREE;
		}
	}

	private void generateFlowers(World worldIn, Random rand, BlockPos pos, int flowersPerChunk, WorldGenerator flowerGenerator) {
		for (int i = 0; i < flowersPerChunk; ++i) {
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
				break;
			}
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
		int flowersPerChunk = 7;

		generateFlowers(worldIn, rand, pos, flowersPerChunk, ALLIARIAFLOWER);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, PLUCHEAGLUTONISAFLOWER);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, ACANTHUSBOTFLOWER);

		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
		WorldGenerator emeralds = new EmeraldGenerator();
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
			emeralds.generate(worldIn, rand, pos);

		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));

		super.decorate(worldIn, rand, pos);
	}

	@Override
	public int getModdedBiomeGrassColor(int original) {
		return super.getModdedBiomeGrassColor(0x8A9B37);
	}

	@Override
	public int getModdedBiomeFoliageColor(int original) {
		return super.getModdedBiomeFoliageColor(0xABA144);
	}

	public static class EmeraldGenerator extends WorldGenerator {
		@Override
		public boolean generate(World worldIn, Random rand, BlockPos pos) {
			int count = 10 + rand.nextInt(6);
			for (int i = 0; i < count; i++) {
				int offset = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 8 : 0;
				BlockPos blockpos = pos.add(rand.nextInt(16) + offset, rand.nextInt(28) + 2, rand.nextInt(16) + offset);

				net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
				if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, net.minecraft.block.state.pattern.BlockMatcher.forBlock(Blocks.STONE))) {
					worldIn.setBlockState(blockpos, Blocks.EMERALD_ORE.getDefaultState(), 16 | 2);
				}
				net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
			}
			return true;
		}
	}
}
