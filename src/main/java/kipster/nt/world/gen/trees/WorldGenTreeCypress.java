package kipster.nt.world.gen.trees;

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
// # todo #todo bugged worldgen
public class WorldGenTreeCypress extends WorldGenAbstractTree {
    private static final IBlockState LOG = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
    private static final IBlockState LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));

    private final boolean useExtraRandomHeight;

    public WorldGenTreeCypress(boolean notify, boolean useExtraRandomHeightIn) {
        super(notify);
        this.useExtraRandomHeight = useExtraRandomHeightIn;
    }

    public void setBlockAndNotifyAdequately(World worldIn, BlockPos position, IBlockState state) {
        worldIn.setBlockState(position, state, 3);
        worldIn.notifyNeighborsRespectDebug(position, state.getBlock(), false);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height = 15 + rand.nextInt(6); // Ajustement de la hauteur
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
                        int branchHeight = y / 3; // Détermine la hauteur de la branche secondaire
                        float branchChance = 0.2F - branchHeight * 0.03F; // Détermine la probabilité de génération d'une branche secondaire
                        BlockPos logPos = position.up(y);
                        IBlockState logState = worldIn.getBlockState(logPos);

                        if (logState.getBlock().isAir(logState, worldIn, logPos) || logState.getBlock().isLeaves(logState, worldIn, logPos)) {
                            this.setBlockAndNotifyAdequately(worldIn, logPos, LOG);
                        }

                        // Génération des branches secondaires
                        if (y >= 3 && rand.nextFloat() < branchChance) {
                            generateBranch(worldIn, rand, logPos, height - y, rand.nextInt(3) + 1);
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

    // Méthode pour générer une branche secondaire
    private void generateBranch(World worldIn, Random rand, BlockPos startPos, int height, int length) {
        int dx = 0;
        int dz = 0;

         // Détermine la direction de la branche
            switch (rand.nextInt(4)) {
                case 0:
                    dx = -1;
                    break;
                case 1:
                    dx = 1;
                    break;
                case 2:
                    dz = -1;
                    break;
                case 3:
                dz = 1;
                break;
        }

        BlockPos branchPos = startPos.up(height).add(dx, 0, dz); // Utilisez la méthode 'add' au lieu de 'offset'

        for (int i = 0; i < length; i++) {
            if (worldIn.isAirBlock(branchPos)) {
                this.setBlockAndNotifyAdequately(worldIn, branchPos, LOG);
            }

            branchPos = branchPos.up();
        }
    }
}
