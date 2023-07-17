package kipster.nt.world.gen.trees;

import kipster.nt.blocks.BlockInit;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;
//#todo it seem thats doesn't work
public class WorldGenTreeOlive extends WorldGenTrees {
    private static final IBlockState LOG = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
    private static final IBlockState LEAF = BlockInit.OLIVELEAVES.getDefaultState();
    private final boolean useExtraRandomHeight;

    public WorldGenTreeOlive(boolean notify, boolean useExtraRandomHeightIn) {
        super(notify);
        this.useExtraRandomHeight = useExtraRandomHeightIn;
    }

    public void setBlockAndNotifyAdequately(World worldIn, BlockPos position, IBlockState state) {
        worldIn.setBlockState(position, state, 3);
        worldIn.notifyNeighborsRespectDebug(position, state.getBlock(), false);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height = 10 + rand.nextInt(11);
        int radius = 10 + rand.nextInt(11);

        if (this.useExtraRandomHeight) {
            height += rand.nextInt(11);
        }

        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getHeight()) {
            for (int y = position.getY(); y <= position.getY() + 1 + height; ++y) {
                int checkRadius = radius;

                if (y == position.getY()) {
                    checkRadius = 0;
                }

                if (y >= position.getY() + 1 + height - 2) {
                    checkRadius = 2;
                }

                BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

                for (int x = position.getX() - checkRadius; x <= position.getX() + checkRadius && flag; ++x) {
                    for (int z = position.getZ() - checkRadius; z <= position.getZ() + checkRadius && flag; ++z) {
                        if (y >= 0 && y < worldIn.getHeight()) {
                            if (!this.isReplaceable(worldIn, mutablePos.setPos(x, y, z))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

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

                                if (Math.abs(dx) != l || Math.abs(dz) != l || rand.nextInt(2) != 0 && k != 0) {
                                    BlockPos leafPos = new BlockPos(x, y, z);
                                    IBlockState leafState = worldIn.getBlockState(leafPos);

                                    if (leafState.getBlock().isAir(leafState, worldIn, leafPos) || leafState.getBlock().isLeaves(leafState, worldIn, leafPos)) {
                                        this.setBlockAndNotifyAdequately(worldIn, leafPos, LEAF);
                                    }
                                }
                            }
                        }
                    }

                    for (int y = 0; y < height; ++y) {
                        BlockPos logPos = position.up(y);
                        IBlockState logState = worldIn.getBlockState(logPos);

                        if (logState.getBlock().isAir(logState, worldIn, logPos) || logState.getBlock().isLeaves(logState, worldIn, logPos)) {
                            this.setBlockAndNotifyAdequately(worldIn, logPos, LOG);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
