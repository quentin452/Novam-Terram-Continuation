package kipster.nt.biomes.warm;

import kipster.nt.blocks.BlockInit;
import kipster.nt.blocks.blocks.BlockFlowerPrimevere;
import kipster.nt.world.gen.flowers.WorldGenPrimevereFlower;
import kipster.nt.world.gen.trees.WorldGenTreeBigJacaranda;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

public class BiomeAliumMeadow extends Biome {
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenAbstractTree JACARANDA_TREE = new WorldGenTreeBigJacaranda(false);
	protected static final WorldGenPrimevereFlower PRIMEVERE_FLOWER = new WorldGenPrimevereFlower(new BlockFlowerPrimevere("primevere_flower", Material.PLANTS));

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
		return rand.nextInt(1) == 0 ? JACARANDA_TREE : JACARANDA_TREE;
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);

		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
		WorldGenerator emeralds = new EmeraldGenerator();
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
			emeralds.generate(worldIn, rand, pos);
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


				int x = rand.nextInt(16) + (chunkX << 4);
				int z = rand.nextInt(16) + (chunkZ << 4);

				BlockPos topPos = worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
				int y = topPos.getY();

				BlockPos flowerPos = new BlockPos(x, y, z);

				Biome biome = worldIn.getBiome(flowerPos);
				if (biome instanceof BiomeAliumMeadow) {
					IBlockState blockState = worldIn.getBlockState(flowerPos.down());
					if (blockState.getBlock() == Blocks.GRASS && blockState.isFullBlock()) {
						PRIMEVERE_FLOWER.generate(worldIn, rand, flowerPos);
						System.out.println("Generated Primevere flower at " + flowerPos);
						System.out.println("Block at flowerPos: " + blockState.getBlock());
						System.out.println("Is block full: " + blockState.isFullBlock());
					} else {
						System.out.println("Failed to generate Primevere flower at " + flowerPos);
						System.out.println("Block at flowerPos: " + blockState.getBlock());
						System.out.println("Is block full: " + blockState.isFullBlock());
					}
				}
			}

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
