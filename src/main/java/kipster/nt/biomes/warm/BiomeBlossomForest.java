package kipster.nt.biomes.warm;

import kipster.nt.world.gen.trees.WorldGenTreeBigPurpleCherry;
import kipster.nt.world.gen.trees.WorldGenTreeCherryPink;
import kipster.nt.world.gen.trees.WorldGenTreeCherryWhite;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeBlossomForest extends Biome
{

	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenTreeCherryWhite WHITE_TREE = new WorldGenTreeCherryWhite(false, false);
	protected static final WorldGenTreeCherryPink PINK_TREE = new WorldGenTreeCherryPink(false, false);
	private final WorldGenTreeBigPurpleCherry purpleCherryGenerator = new WorldGenTreeBigPurpleCherry(false);

	public BiomeBlossomForest(BiomeProperties properties)
	{
		super(properties);

		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();

		this.decorator.treesPerChunk = 8;
		this.decorator.flowersPerChunk = 100;
		this.decorator.grassPerChunk = 1;
		this.decorator.generateFalls = true;

		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
		this.flowers.clear();
		for (BlockFlower.EnumFlowerType type : BlockFlower.EnumFlowerType.values())
		{
			if (type.getBlockType() == BlockFlower.EnumFlowerColor.YELLOW) continue;
			if (type == BlockFlower.EnumFlowerType.BLUE_ORCHID) type = BlockFlower.EnumFlowerType.POPPY;
			addFlower(net.minecraft.init.Blocks.RED_FLOWER.getDefaultState().withProperty(net.minecraft.init.Blocks.RED_FLOWER.getTypeProperty(), type), 10);
		}
	}
	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
	{
		double d0 = MathHelper.clamp((1.0D + GRASS_COLOR_NOISE.getValue((double)pos.getX() / 48.0D, (double)pos.getZ() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
		BlockFlower.EnumFlowerType blockflower$enumflowertype = BlockFlower.EnumFlowerType.values()[(int)(d0 * (double)BlockFlower.EnumFlowerType.values().length)];
		return blockflower$enumflowertype == BlockFlower.EnumFlowerType.BLUE_ORCHID ? BlockFlower.EnumFlowerType.POPPY : blockflower$enumflowertype;
	}
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int whiteWeight = 2;
		int purpleWeight = 1;
		int pinkWeight = 3;

		int totalWeight = whiteWeight + purpleWeight + pinkWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(WHITE_TREE);
		treeList.add(this.purpleCherryGenerator);
		treeList.add(PINK_TREE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);

	}

	public void addDoublePlants(World world, Random random, BlockPos pos, int count) {
		List<BlockDoublePlant.EnumPlantType> validPlantTypes = new ArrayList<>();
		validPlantTypes.add(BlockDoublePlant.EnumPlantType.SYRINGA);
		validPlantTypes.add(BlockDoublePlant.EnumPlantType.ROSE);
		validPlantTypes.add(BlockDoublePlant.EnumPlantType.PAEONIA);

		DOUBLE_PLANT_GENERATOR.setPlantType(getPlantType(random, validPlantTypes));

		for (int i = 0; i < count; i++) {

			int x, z, y;
			do {
				x = random.nextInt(16) + 8;
				z = random.nextInt(16) + 8;
				y = random.nextInt(world.getHeight(pos.add(x, 0, z)).getY() + 32);
			} while (!DOUBLE_PLANT_GENERATOR.generate(world, random, pos.add(x, y, z)));
		}
	}

	private BlockDoublePlant.EnumPlantType getPlantType(Random random, List<BlockDoublePlant.EnumPlantType> plants) {
		int index = random.nextInt(plants.size());
		return plants.get(index);
	}

	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
		WorldGenerator emeralds = new EmeraldGenerator();
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
			emeralds.generate(worldIn, rand, pos);

		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

		if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
			for (int i = 0; i < 7; ++i)
			{
				int j = rand.nextInt(16) + 8;
				int k = rand.nextInt(16) + 8;
				int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
				DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
			}
		if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS))
		{ // no tab for patch
			int i = rand.nextInt(5) - 3;
			{
				i += 2;
			}

			this.addDoublePlants(worldIn, rand, pos, i);
		}
		super.decorate(worldIn, rand, pos);
	}

	@Override
	public int getModdedBiomeGrassColor(int original) {
		return 0x6AD04A;
	}

	@Override
	public int getModdedBiomeFoliageColor(int original) {
		return 0x629D30;
	}

	@Override
	public int getSkyColorByTemp(float currentTemperature) {

		return 0x49A4EC;
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