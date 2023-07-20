package kipster.nt.world.gen.trees;

import kipster.nt.world.gen.TreeGeneratorRegistry;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;
public class WorldGenTreeCypress extends WorldGenAbstractTree {
    private static final IBlockState LOG = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
    private static final IBlockState LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    private final TreeGeneratorRegistry registry = new TreeGeneratorRegistry();

    private final boolean useExtraRandomHeight;

    public WorldGenTreeCypress(boolean notify, boolean useExtraRandomHeightIn) {
        super(notify);
        this.useExtraRandomHeight = useExtraRandomHeightIn;

        registry.registerTreeGenerator(this);
    }

    public void setBlockAndNotifyAdequately(World worldIn, BlockPos position, IBlockState state) {
        worldIn.setBlockState(position, state, 3);
        worldIn.notifyNeighborsRespectDebug(position, state.getBlock(), false);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (registry.containsTreeAt(worldIn, position, this)) {
            return false;
        }
        if (registry.overlapsExistingTrees(worldIn, position)) {
            return false;
        }

        int height = 15 + rand.nextInt(6);

        if (this.useExtraRandomHeight) {
            height += rand.nextInt(11);
        }

        boolean flag = true;

            if (!flag) {
                return false;
            }
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

                                    if (worldIn.isAirBlock(leafPos) || worldIn.getBlockState(leafPos).getBlock().isLeaves(worldIn.getBlockState(leafPos), worldIn, leafPos)) {
                                        this.setBlockAndNotifyAdequately(worldIn, leafPos, LEAF);
                                    }
                                }
                            }
                        }
                    }

                    for (int y = 0; y < height; ++y) {
                        BlockPos logPos = position.up(y);

                        if (worldIn.isAirBlock(logPos) || worldIn.getBlockState(logPos).getBlock().isLeaves(worldIn.getBlockState(logPos), worldIn, logPos)) {
                            this.setBlockAndNotifyAdequately(worldIn, logPos, LOG);
                        }

                        // Génération des branches secondaires
                        for (int y2 = 0; y2 < height; ++y2){
                            proposeBranch(worldIn, logPos, 3);
                        }
                    }

                    return true;
                } else {
                    return false;
            }
    }
    private void proposeBranch(World worldIn, BlockPos pos, int length) {
        for (int i = 0; i < length; i++) {
            if(worldIn.isAirBlock(pos)) {
                this.setBlockAndNotifyAdequately(worldIn, pos, LOG);
            } else {
                break;
            }
            BlockPos abovePos = pos.up();
            if(worldIn.isAirBlock(abovePos)) {
                pos = abovePos;
            } else {
                break;
            }
        }
    }
}
