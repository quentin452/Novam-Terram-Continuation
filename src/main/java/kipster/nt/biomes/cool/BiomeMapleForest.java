package kipster.nt.biomes.cool;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.flowers.*;
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
    protected static final WorldGenerator CEPHALOPHYLLIUMflower= new WorldGenCephalophyllumFlower(BlockInit.CEPHALOPHYLLUMFLOWER.getDefaultState());
    protected static final WorldGenerator DISAflower = new WorldGenDisaFlower(BlockInit.DISAFLOWER.getDefaultState());
    protected static final WorldGenerator AESCHYNANTHUSflower = new WorldGenAeschynanthusFlower(BlockInit.AESCHYNANTHUSFLOWER.getDefaultState());

    public BiomeMapleForest(BiomeProperties properties) {
        super(properties);

        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 8, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        this.decorator.treesPerChunk = 7;
        this.decorator.flowersPerChunk = 2;
        this.decorator.grassPerChunk = 4;
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
            worldIn.setBlockState(blockPos, woodState, 2 | 16); // Use flag 16 to prevent cascading during generation
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

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldIn, rand, pos));
        WorldGenerator diamonds = new DiamondGenerator();
        if (TerrainGen.generateOre(worldIn, rand, diamonds, pos, OreGenEvent.GenerateMinable.EventType.DIAMOND))
            diamonds.generate(worldIn, rand, pos);

        // Generate fallen trees
        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y = worldIn.getHeight(pos.add(x, 0, z)).getY();

            if (y > 63 && rand.nextInt(6) == 0) {
                generateFallenTree(worldIn, rand, pos.add(x, y, z));
            }
        }
        int flowersPerChunk = 7;

        generateFlowers(worldIn, rand, pos, flowersPerChunk, CEPHALOPHYLLIUMflower);
        generateFlowers(worldIn, rand, pos, flowersPerChunk, DISAflower);
        generateFlowers(worldIn, rand, pos, flowersPerChunk, AESCHYNANTHUSflower);

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
