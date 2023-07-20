package kipster.nt.world.gen.trees;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.flowers.WorldGenOliveFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenTreeOlive extends WorldGenAbstractTree {
    private static final IBlockState LOG = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
    private static final IBlockState LEAF = BlockInit.OLIVELEAVES.getDefaultState();
    private static final IBlockState BARK = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.NONE);
   // private static final IBlockState FLOWER = BlockInit.OLIVEFLOWER.getDefaultState();
  // private static final IBlockState FRUIT = BlockInit.OLIVEFRUIT.getDefaultState();
   protected static final WorldGenOliveFlower OLIVE_FLOWER = new WorldGenOliveFlower(BlockInit.OLIVEFLOWER.getDefaultState());

    private final boolean useExtraRandomHeight;

    public WorldGenTreeOlive(boolean notify, boolean useExtraRandomHeightIn) {
        super(notify);
        this.useExtraRandomHeight = useExtraRandomHeightIn;
    }

    public void setBlockAndNotifyAdequately(World worldIn, BlockPos position, IBlockState state) {
        worldIn.setBlockState(position, state, 3);
        worldIn.notifyNeighborsRespectDebug(position, state.getBlock(), false);
    }

    private boolean hasLeavesAbove(World worldIn, BlockPos pos) {
        for (int i = 1; i <= 3; i++) { // Check up to three blocks above for leaves
            if (worldIn.getBlockState(pos.up(i)).getBlock().isLeaves(worldIn.getBlockState(pos.up(i)), worldIn, pos.up(i))) {
                return true;
            }
        }
        return false;
    }
    private void generateFlowers(World worldIn, Random rand, BlockPos logPos) {
        int crownRadius = (int) (10 / 2.5); // Adjust the crown radius based on tree height and reduce it by 2.5 times

        for (int x = -crownRadius; x <= crownRadius; x++) {
            for (int z = -crownRadius; z <= crownRadius; z++) {
                BlockPos flowerPos = logPos.add(x, 0, z); // Offset flower position relative to the log position
                BlockPos groundPos = flowerPos.down(); // Check if the block below is grass or dirt

                if (worldIn.getBlockState(groundPos).getBlock() == Blocks.GRASS || worldIn.getBlockState(groundPos).getBlock() == Blocks.DIRT) {
                    if (hasLeavesAbove(worldIn, flowerPos)) { // Only place flowers if there are leaves above
                        OLIVE_FLOWER.generate(worldIn, rand, flowerPos); // Generate olive flowers using the WorldGenOliveFlower generator
                    }
                }
            }
        }
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {

        int height = 20 + rand.nextInt(11);
        int radius = 10 + rand.nextInt(11);

        if (this.useExtraRandomHeight) {
            height += rand.nextInt(11);
        }

        boolean flag = true;

        if (!flag) {
            return false;
        } else {
            BlockPos down = position.down();
            IBlockState state = worldIn.getBlockState(down);
            boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling) Blocks.SAPLING);

            if (isSoil && position.getY() < worldIn.getHeight() - height - 1) {
                state.getBlock().onPlantGrow(state, worldIn, down, position);

                for (int y = position.getY() - 3 + height; y <= position.getY() + height; ++y) {
                    int k = y - (position.getY() + height);
                    int l = 1 - k / 2;

                    for (int x = position.getX() - l; x <= position.getX() + l; ++x) {
                        int dx = x - position.getX();

                        for (int z = position.getZ() - l; z <= position.getZ() + l; ++z) {
                            int dz = z - position.getZ();
                            double distance = Math.sqrt(dx * dx + dz * dz);

                            if (distance <= radius * 1.5 + rand.nextDouble() * 0.5 && (Math.abs(dx) != l || Math.abs(dz) != l || rand.nextDouble() < 0.2 && k != 0)) {
                                for (int i = 0; i < rand.nextInt(3) + 3; i++) {
                                    int offsetX = x + rand.nextInt(3) - 1;
                                    int offsetY = y + rand.nextInt(3) - 1;
                                    int offsetZ = z + rand.nextInt(3) - 1;
                                    BlockPos leafPos = new BlockPos(offsetX, offsetY, offsetZ);
                                    IBlockState leafState = worldIn.getBlockState(leafPos);

                                    if (leafState.getBlock().isAir(leafState, worldIn, leafPos) || leafState.getBlock().isLeaves(leafState, worldIn, leafPos)) {
                                        // Check for leaves above before decaying
                                        if (!hasLeavesAbove(worldIn, leafPos)) {
                                            this.setBlockAndNotifyAdequately(worldIn, leafPos, LEAF);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                generateLeaves(worldIn, rand, position.up(height), height);

                for (int y = 0; y <= height; ++y) {
                    BlockPos logPos = position.up(y);
                    IBlockState logState = worldIn.getBlockState(logPos);

                    if (logState.getBlock().isAir(logState, worldIn, logPos) || logState.getBlock().isLeaves(logState, worldIn, logPos)) {
                        if (y < height) {
                            this.setBlockAndNotifyAdequately(worldIn, logPos, LOG);
                        } else {
                            this.setBlockAndNotifyAdequately(worldIn, logPos, BARK);

                            if (rand.nextDouble() < 0.1) {
                                // #todo # todo flower don't want to generate
                                generateFlowers(worldIn, rand, logPos); // Generate olive flowers
                            }
                            //#todo # todo not terminated
                            if (rand.nextDouble() < 0.1) {
                                //     BlockPos fruitPos = logPos.up().offset(rand.nextInt(3) - 1, 0, rand.nextInt(3) - 1);
                                //    IBlockState fruitState = worldIn.getBlockState(fruitPos);
                                //    if (fruitState.getBlock().isAir(fruitState, worldIn, fruitPos)) {
                                //        this.setBlockAndNotifyAdequately(worldIn, fruitPos, FRUIT);
                                //    }
                            }
                        }
                    }
                }

                return true;
            } else {
                return false;
            }
        }
    }

    private void generateLeaves(World worldIn, Random rand, BlockPos logPos, int treeHeight) {
        // Adjust crown radius based on tree height and reduce it by 2.5 times
        int crownRadius = (int) (5 / 2.5);

        // Generate leaves at a reduced density in the circular crown
        for (int y = -crownRadius; y <= crownRadius; y++) {
            for (int x = -crownRadius; x <= crownRadius; x++) {
                for (int z = -crownRadius; z <= crownRadius; z++) {
                    if (x * x + y * y + z * z <= crownRadius * crownRadius) {
                        // Generate leaves with a probability of 60%
                        if (rand.nextDouble() <= 0.6) {
                            BlockPos leafPos = logPos.add(x, y, z);
                            IBlockState leafState = worldIn.getBlockState(leafPos);

                            // Generate leaves without any checks
                            if (leafState.getBlock().isAir(leafState, worldIn, leafPos) || leafState.getBlock().isLeaves(leafState, worldIn, leafPos)) {
                                this.setBlockAndNotifyAdequately(worldIn, leafPos, LEAF);
                            }
                        }
                    }
                }
            }
        }

        // Decay some of the leaves to fill in gaps
        for (int i = 0; i < 3; i++) {
            int x = logPos.getX() + rand.nextInt(crownRadius * 2) - crownRadius;
            int y = logPos.getY() + rand.nextInt(crownRadius);
            int z = logPos.getZ() + rand.nextInt(crownRadius * 2) - crownRadius;
            BlockPos leafPos = new BlockPos(x, y, z);
            IBlockState leafState = worldIn.getBlockState(leafPos);

            if (leafState.getBlock().isLeaves(leafState, worldIn, leafPos)) {
                this.setBlockAndNotifyAdequately(worldIn, leafPos, LEAF);
            }
        }
    }
}
