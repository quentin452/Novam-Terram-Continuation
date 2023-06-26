package kipster.nt.biomes.warm;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.flowers.WorldGenAlliumFlower;
import kipster.nt.world.gen.flowers.WorldGenAspalathusFlower;
import kipster.nt.world.gen.flowers.WorldGenPrimevereFlower;
import kipster.nt.world.gen.trees.WorldGenTreeBigJacaranda;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeAliumMeadow extends Biome {
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenAbstractTree JACARANDA_TREE = new WorldGenTreeBigJacaranda(false);
	protected static final WorldGenerator PRIMEVEflower = new WorldGenPrimevereFlower(BlockInit.PRIMEVEREFLOWER.getDefaultState());
	protected static final WorldGenerator ALLIUMflower = new WorldGenAlliumFlower(BlockInit.ALLIUMFLOWER.getDefaultState());
	protected static final WorldGenerator ASPALATHUSflower = new WorldGenAspalathusFlower(BlockInit.ASPALATHUSFLOWER.getDefaultState());
	public BiomeAliumMeadow(BiomeProperties properties) {
		super(properties);

		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();

		this.decorator.extraTreeChance = 0.3F;
		this.decorator.grassPerChunk = 35;

		this.decorator.generateFalls = true;

		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 5, 2, 6));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 4, 4));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCow.class, 8, 4, 4));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 5, 2, 6));
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return JACARANDA_TREE;
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		int flowersPerChunk = 5;

		generateFlowers(worldIn, rand, pos, flowersPerChunk, PRIMEVEflower);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, ALLIUMflower);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, ASPALATHUSflower);

		super.decorate(worldIn, rand, pos);
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
	public int getModdedBiomeGrassColor(int original) {
		return super.getModdedBiomeGrassColor(0xA0CA5F);
	}

	@Override
	public int getModdedBiomeFoliageColor(int original) {
		return super.getModdedBiomeFoliageColor(0xA0CA5F);
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

			net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));

			return true;
		}
	}
}
