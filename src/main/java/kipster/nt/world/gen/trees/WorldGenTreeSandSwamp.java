package kipster.nt.world.gen.trees;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenTreeSandSwamp extends WorldGenAbstractTree
{
    private static final IBlockState TRUNK = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
    private static final IBlockState LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false));

    public WorldGenTreeSandSwamp()
    {
        super(false);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        int i;

        for (i = rand.nextInt(4) + 4; worldIn.getBlockState(position.down()).getMaterial() == Material.WATER; position = position.down())
        {
            ;
        }
        for (i = rand.nextInt(4) + 4; worldIn.getBlockState(position.down()).getMaterial() == Material.SAND; position = position.down())
        {
            ;
        }

        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= 256)
        {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j)
            {
                int k = 1;

                if (j == position.getY())
                {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2)
                {
                    k = 3;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l)
                {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1)
                    {
                        if (j >= 0 && j < 256)
                        {
                            IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos.setPos(l, j, i1));
                            Block block = iblockstate.getBlock();

                            if (!iblockstate.getBlock().isAir(iblockstate, worldIn, blockpos$mutableblockpos.setPos(l, j, i1)) && !iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos$mutableblockpos.setPos(l, j, i1)))
                            {
                                if (block != Blocks.WATER && block != Blocks.FLOWING_WATER && block != Blocks.SAND)
                                {
                                    flag = false;
                                }
                                else if (j > position.getY())
                                {
                                    flag = false;
                                }
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                BlockPos down = position.down();
                IBlockState state = worldIn.getBlockState(down);
                boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, net.minecraft.util.EnumFacing.UP, ((net.minecraft.block.BlockSapling)Blocks.SAPLING));

                if (isSoil && position.getY() < worldIn.getHeight() - i - 1)
                {
                    state.getBlock().onPlantGrow(state, worldIn, position.down(),position);

                    for (int k1 = position.getY() - 3 + i; k1 <= position.getY() + i; ++k1)
                    {
                        int j2 = k1 - (position.getY() + i);
                        int l2 = 2 - j2 / 2;

                        for (int j3 = position.getX() - l2; j3 <= position.getX() + l2; ++j3)
                        {
                            int k3 = j3 - position.getX();

                            for (int i4 = position.getZ() - l2; i4 <= position.getZ() + l2; ++i4)
                            {
                                int j1 = i4 - position.getZ();

                                if (Math.abs(k3) != l2 || Math.abs(j1) != l2 || rand.nextInt(2) != 0 && j2 != 0)
                                {
                                    BlockPos blockpos = new BlockPos(j3, k1, i4);
                                    state = worldIn.getBlockState(blockpos);

                                    if (state.getBlock().canBeReplacedByLeaves(state, worldIn, blockpos))
                                    {
                                        this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
                                    }
                                }
                            }
                        }
                    }

                    for (int l1 = 0; l1 < i; ++l1)
                    {
                        BlockPos upN = position.up(l1);
                        IBlockState iblockstate1 = worldIn.getBlockState(upN);
                        Block block2 = iblockstate1.getBlock();

                        if (block2.isAir(iblockstate1, worldIn, upN) || block2.isLeaves(iblockstate1, worldIn, upN) || block2 == Blocks.FLOWING_WATER || block2 == Blocks.WATER)
                        {
                            this.setBlockAndNotifyAdequately(worldIn, position.up(l1), TRUNK);
                        }
                    }

                    for (int i2 = position.getY() - 3 + i; i2 <= position.getY() + i; ++i2)
                    {
                        int k2 = i2 - (position.getY() + i);
                        int i3 = 2 - k2 / 2;
                        BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

                        for (int l3 = position.getX() - i3; l3 <= position.getX() + i3; ++l3)
                        {
                            for (int j4 = position.getZ() - i3; j4 <= position.getZ() + i3; ++j4)
                            {
                                blockpos$mutableblockpos1.setPos(l3, i2, j4);

                                if (worldIn.getBlockState(blockpos$mutableblockpos1).getMaterial() == Material.LEAVES)
                                {
                                    BlockPos blockpos3 = blockpos$mutableblockpos1.west();
                                    BlockPos blockpos4 = blockpos$mutableblockpos1.east();
                                    BlockPos blockpos1 = blockpos$mutableblockpos1.north();
                                    BlockPos blockpos2 = blockpos$mutableblockpos1.south();

                                    if (rand.nextInt(4) == 0 && worldIn.isAirBlock(blockpos3))
                                    {
                                        this.addVine(worldIn, blockpos3, BlockVine.EAST);
                                    }

                                    if (rand.nextInt(4) == 0 && worldIn.isAirBlock(blockpos4))
                                    {
                                        this.addVine(worldIn, blockpos4, BlockVine.WEST);
                                    }

                                    if (rand.nextInt(4) == 0 && worldIn.isAirBlock(blockpos1))
                                    {
                                        this.addVine(worldIn, blockpos1, BlockVine.SOUTH);
                                    }

                                    if (rand.nextInt(4) == 0 && worldIn.isAirBlock(blockpos2))
                                    {
                                        this.addVine(worldIn, blockpos2, BlockVine.NORTH);
                                    }
                                }
                            }
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
    public void setBlockAndNotifyAdequately(World worldIn, BlockPos position, IBlockState state) {
        worldIn.setBlockState(position, state, 3);
        worldIn.notifyBlockUpdate(position, state, state, 3);
    }
    private void addVine(World worldIn, BlockPos pos, PropertyBool prop)
    {
        IBlockState iblockstate = Blocks.VINE.getDefaultState().withProperty(prop, Boolean.valueOf(true));
        this.setBlockAndNotifyAdequately(worldIn, pos, iblockstate);
        int i = 4;

        for (BlockPos blockpos = pos.down(); worldIn.isAirBlock(blockpos) && i > 0; --i)
        {
            this.setBlockAndNotifyAdequately(worldIn, blockpos, iblockstate);
            blockpos = blockpos.down();
        }
    }
}