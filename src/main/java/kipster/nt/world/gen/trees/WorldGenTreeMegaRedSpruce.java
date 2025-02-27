package kipster.nt.world.gen.trees;

import kipster.nt.blocks.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;

import java.util.Random;

public class WorldGenTreeMegaRedSpruce extends WorldGenHugeTrees
{
    private static final IBlockState TRUNK = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
    private static final IBlockState LEAF = BlockInit.SPRUCELEAVESRED.getDefaultState();
    private static final IBlockState PODZOL = BlockInit.REDPODZOL.getDefaultState();
    private final boolean useBaseHeight;

    public WorldGenTreeMegaRedSpruce(boolean notify, boolean p_i45457_2_)
    {
        super(notify, 14, 16, TRUNK, LEAF);
        this.useBaseHeight = p_i45457_2_;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        int i = this.getHeight(rand);

        if (!this.ensureGrowable(worldIn, rand, position, i))
        {
            return false;
        }
        else
        {
            this.createCrown(worldIn, position.getX(), position.getZ(), position.getY() + i, 0, rand);

            for (int j = 0; j < i; ++j)
            {
                if (isAirLeaves(worldIn, position.up(j)))
                {
                    this.setBlockAndNotifyAdequately(worldIn, position.up(j), this.woodMetadata);
                }

                if (j < i - 1)
                {
                    if (isAirLeaves(worldIn, position.add(1, j, 0)))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, position.add(1, j, 0), this.woodMetadata);
                    }

                    if (isAirLeaves(worldIn, position.add(1, j, 1)))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, position.add(1, j, 1), this.woodMetadata);
                    }


                    if (isAirLeaves(worldIn, position.add(0, j, 1)))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, position.add(0, j, 1), this.woodMetadata);
                    }
                }
            }

            return true;
        }
    }

    private void createCrown(World worldIn, int x, int z, int y, int p_150541_5_, Random rand)
    {
        int i = rand.nextInt(5) + (this.useBaseHeight ? this.baseHeight : 3);
        int j = 0;

        for (int k = y - i; k <= y; ++k)
        {
            int l = y - k;
            int i1 = p_150541_5_ + MathHelper.floor((float)l / (float)i * 3.5F);
            this.growLeavesLayerStrict(worldIn, new BlockPos(x, k, z), i1 + (l > 0 && i1 == j && (k & 1) == 0 ? 1 : 0));
            j = i1;
        }
    }

    public void generateSaplings(World worldIn, Random random, BlockPos pos)
    {
        this.placePodzolCircle(worldIn, pos.west().north());
        this.placePodzolCircle(worldIn, pos.east(2).north());
        this.placePodzolCircle(worldIn, pos.west().south(2));
        this.placePodzolCircle(worldIn, pos.east(2).south(2));

        for (int i = 0; i < 5; ++i)
        {
            int j = random.nextInt(64);
            int k = j % 8;
            int l = j / 8;

            if (k == 0 || k == 7 || l == 0 || l == 7)
            {
                this.placePodzolCircle(worldIn, pos.add(-3 + k, 0, -3 + l));
            }
        }
    }

    private void placePodzolCircle(World worldIn, BlockPos center)
    {
        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                if (Math.abs(i) != 2 || Math.abs(j) != 2)
                {
                    this.placePodzolAt(worldIn, center.add(i, 0, j));
                }
            }
        }
    }

    private void placePodzolAt(World worldIn, BlockPos pos)
    {
        for (int i = 2; i >= -3; --i)
        {
            BlockPos blockpos = pos.up(i);
            IBlockState iblockstate = worldIn.getBlockState(blockpos);
            Block block = iblockstate.getBlock();

            if (block.canSustainPlant(iblockstate, worldIn, blockpos, net.minecraft.util.EnumFacing.UP, ((net.minecraft.block.BlockSapling)Blocks.SAPLING)))
            {
                this.setBlockAndNotifyAdequately(worldIn, blockpos, PODZOL);
                break;
            }

            if (iblockstate.getMaterial() != Material.AIR && i < 0)
            {
                break;
            }
        }
    }

    //Helper macro
    private boolean isAirLeaves(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos);
    }
    public void setBlockAndNotifyAdequately(World worldIn, BlockPos position, IBlockState state) {
        worldIn.setBlockState(position, state, 3);
        worldIn.notifyBlockUpdate(position, state, state, 3);
    }
}