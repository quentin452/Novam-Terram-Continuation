package kipster.nt.biomes.cool;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.trees.WorldGenTreeMaple;
import kipster.nt.world.gen.trees.WorldGenTreeRedSpruce2;
import kipster.nt.world.gen.trees.WorldGenTreeShrubOak;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class BiomeMapleForest extends Biome {
    private static final WorldGenAbstractTree SHRUB_OAK = new WorldGenTreeShrubOak();
    private static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
    private static final WorldGenAbstractTree MAPLE_TREE = new WorldGenTreeMaple(false, false);
    private final WorldGenTreeRedSpruce2 spruceGenerator = new WorldGenTreeRedSpruce2(true);

    public BiomeMapleForest(BiomeProperties properties) {
        super(properties);

        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 8, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        this.decorator.treesPerChunk = 7;
        this.decorator.flowersPerChunk = 2;
        this.decorator.grassPerChunk = 4;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(1) > 0) {
            return SHRUB_OAK;
        } else {
            return rand.nextInt(4) == 0 ? this.spruceGenerator : MAPLE_TREE;
        }
    }

    private void generateFallenTree(World worldIn, Random rand, BlockPos pos) {
        Block woodBlock = Blocks.LOG; // Adjust this to the desired wood block
        IBlockState woodState = woodBlock.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.NONE); // Adjust the property values as needed

        // Adjust the probability value as desired (lower value = less frequent spawning)
        double spawnProbability = 0.2;

        // Check if the random number is within the desired probability range
        if (rand.nextDouble() > spawnProbability) {
            return; // Do not generate the fallen tree
        }

        int trunkHeight = rand.nextInt(3) + 3; // Random number of wood blocks between 3 and 5

        // Determine the facing direction of the fallen tree
        EnumFacing direction = EnumFacing.HORIZONTALS[rand.nextInt(EnumFacing.HORIZONTALS.length)];

        // Calculate the starting position for the fallen tree
        BlockPos startPos = pos.offset(direction, trunkHeight / 2);

        // Check if the fallen tree would intersect with leaves
        if (checkLeavesIntersect(worldIn, startPos, direction, trunkHeight)) {
            return; // Do not generate the fallen tree if it intersects with leaves
        }

        // Generate the fallen tree by placing wood blocks side by side
        for (int i = 0; i < trunkHeight; i++) {
            BlockPos blockPos = startPos.offset(direction.getOpposite(), i);
            worldIn.setBlockState(blockPos, woodState, 2);
        }
    }

    private boolean checkLeavesIntersect(World worldIn, BlockPos startPos, EnumFacing direction, int trunkHeight) {
        for (int i = 0; i < trunkHeight; i++) {
            BlockPos blockPos = startPos.offset(direction.getOpposite(), i);
            Block block = worldIn.getBlockState(blockPos).getBlock();
            if (block instanceof BlockLeaves) {
                return true; // Leaves intersect with the fallen tree
            }
        }
        return false; // No intersection with leaves
    }

    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
    }

    public void decorate(World worldIn, Random rand, BlockPos pos) {
        DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.FERN);

        if (TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.FLOWERS)) {
            for (int i1 = 0; i1 < 7; ++i1) {
                int j1 = rand.nextInt(16) + 8;
                int k1 = rand.nextInt(16) + 8;
                int l1 = rand.nextInt(worldIn.getHeight(pos.add(j1, 0, k1)).getY() + 32);
                DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j1, l1, k1));
            }
        }

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldIn, rand, pos));
        WorldGenerator diamonds = new DiamondGenerator();
        if (TerrainGen.generateOre(worldIn, rand, diamonds, pos, OreGenEvent.GenerateMinable.EventType.DIAMOND))
            diamonds.generate(worldIn, rand, pos);

        if (TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.LAKE_WATER)) {
            int boulderChance = rand.nextInt(12);
            if (boulderChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
                LAKE.generate(worldIn, rand, blockpos);
            }
            MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(worldIn, rand, pos));
        }

        // Generate fallen trees
        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y = worldIn.getHeight(pos.add(x, 0, z)).getY();

            if (y > 63 && rand.nextInt(6) == 0) {
                generateFallenTree(worldIn, rand, pos.add(x, y, z));
            }
        }

        // Generate flowers
        WorldGenerator flowers = new WorldGenFlowers(Blocks.RED_FLOWER, BlockFlower.EnumFlowerType.POPPY);
        WorldGenerator otherFlowers = new WorldGenFlowers(Blocks.YELLOW_FLOWER, BlockFlower.EnumFlowerType.DANDELION);
        WorldGenerator thirdFlower = new WorldGenFlowers(Blocks.RED_FLOWER, BlockFlower.EnumFlowerType.BLUE_ORCHID);

        if (TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.FLOWERS)) {
            for (int i = 0; i < 5; i++) {
                int x = rand.nextInt(16) + 8;
                int y = rand.nextInt(128);
                int z = rand.nextInt(16) + 8;

                // Generate a random flower type
                int flowerType = rand.nextInt(3);
                switch (flowerType) {
                    case 0:
                        flowers.generate(worldIn, rand, pos.add(x, y, z));
                        break;
                    case 1:
                        otherFlowers.generate(worldIn, rand, pos.add(x, y, z));
                        break;
                    case 2:
                        thirdFlower.generate(worldIn, rand, pos.add(x, y, z));
                        break;
                    default:
                        break;
                }
            }
        }

        super.decorate(worldIn, rand, pos);
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        if (noiseVal > 2.50D) {
            this.topBlock = BlockInit.REDPODZOL.getDefaultState();
            this.fillerBlock = Blocks.DIRT.getDefaultState();
        } else {
            this.topBlock = Blocks.GRASS.getDefaultState();
            this.fillerBlock = Blocks.DIRT.getDefaultState();
        }

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0x9FBB53);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0xD9A44F);
    }

    public static class DiamondGenerator extends WorldGenerator {
        @Override
        public boolean generate(World worldIn, Random rand, BlockPos pos) {
            int count = 10 + rand.nextInt(6);
            for (int i = 0; i < count; i++) {
                int offset = ForgeModContainer.fixVanillaCascading ? 8 : 0; // MC-114332
                BlockPos blockpos = pos.add(rand.nextInt(16) + offset, rand.nextInt(28) + 2, rand.nextInt(16) + offset);

                IBlockState state = worldIn.getBlockState(blockpos);
                if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, BlockMatcher.forBlock(Blocks.STONE))) {
                    worldIn.setBlockState(blockpos, Blocks.DIAMOND_ORE.getDefaultState(), 16 | 2);
                }
            }
            return true;
        }
    }
}
