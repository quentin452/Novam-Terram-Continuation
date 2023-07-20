package kipster.nt.world.gen.trees;

import kipster.nt.world.gen.TreeGeneratorRegistry;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;

public class WorldGenTreeShrubBirch2 extends WorldGenTrees
{
    private final TreeGeneratorRegistry registry = new TreeGeneratorRegistry();
    private final IBlockState leavesMetadata;
    private final IBlockState woodMetadata;

    public WorldGenTreeShrubBirch2()
    {
        super(false);
        this.woodMetadata = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
        this.leavesMetadata = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, false);
        registry.registerTreeGenerator(this);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        if (registry.containsTreeAt(worldIn, position, this)) {
            return false;
        }
        if (registry.overlapsExistingTrees(worldIn, position)) {
            return false;
        }
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position))
        {
            position = position.down();
        }

        IBlockState state = worldIn.getBlockState(position);

        if (state.getBlock().canSustainPlant(state, worldIn, position, net.minecraft.util.EnumFacing.UP, ((net.minecraft.block.BlockSapling)Blocks.SAPLING)))
        {
            position = position.up();
            this.setBlockAndNotifyAdequately(worldIn, position, this.woodMetadata);

            for (int i = position.getY(); i <= position.getY() + 1; ++i)
            {
                int j = i - position.getY();
                int k = 2 - j;

                for (int l = position.getX() - k; l <= position.getX() + k; ++l)
                {
                    int i1 = l - position.getX();

                    for (int j1 = position.getZ() - k; j1 <= position.getZ() + k; ++j1)
                    {
                        int k1 = j1 - position.getZ();

                        if (Math.abs(i1) != k || Math.abs(k1) != k || rand.nextInt(1) != 0)
                        {
                            BlockPos blockpos = new BlockPos(l, i, j1);
                            state = worldIn.getBlockState(blockpos);

                            if (state.getBlock().canBeReplacedByLeaves(state, worldIn, blockpos))
                            {
                                this.setBlockAndNotifyAdequately(worldIn, blockpos, this.leavesMetadata);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public void setBlockAndNotifyAdequately(World worldIn, BlockPos position, IBlockState state) {
        worldIn.setBlockState(position, state, 3);
        worldIn.notifyBlockUpdate(position, state, state, 3);
    }
}