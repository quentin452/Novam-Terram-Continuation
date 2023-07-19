package kipster.nt.blocks.blocks.blockleaves;

import kipster.nt.blocks.blocks.BlockLeavesBase;
import kipster.nt.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLeavesJacaranda extends BlockLeavesBase implements IHasModel
{
    int[] surroundings;

    public BlockLeavesJacaranda(String name, Material material) {
        super(name, material);
    }

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = 25;
        int j = 25;
        int k = pos.getX();
        int l = pos.getY();
        int i1 = pos.getZ();

        if (worldIn.isAreaLoaded(new BlockPos(k - 2, l - 2, i1 - 2), new BlockPos(k + 2, l + 2, i1 + 2)))
        {
            for (int j1 = -1; j1 <= 1; ++j1)
            {
                for (int k1 = -1; k1 <= 1; ++k1)
                {
                    for (int l1 = -1; l1 <= 1; ++l1)
                    {
                        BlockPos blockpos = pos.add(j1, k1, l1);
                        IBlockState iblockstate = worldIn.getBlockState(blockpos);

                        if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos))
                        {
                            iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
                        }
                    }
                }
            }
        }
    }

	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue() && ((Boolean)state.getValue(DECAYABLE)).booleanValue())
            {
                int i = 25;
                int j = 30;
                int k = pos.getX();
                int l = pos.getY();
                int i1 = pos.getZ();
                int j1 = 32;
                int k1 = 1024;
                int l1 = 16;

                if (this.surroundings == null)
                {
                    this.surroundings = new int[32768];
                }

                if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent decaying leaves from updating neighbors and loading unloaded chunks
                if (worldIn.isAreaLoaded(pos, 31)) // Forge: extend range from 5 to 6 to account for neighbor checks in world.markAndNotifyBlock -> world.updateObservingBlocksAt
                {
                    BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                    for (int i2 = -4; i2 <= 4; ++i2)
                    {
                        for (int j2 = -4; j2 <= 4; ++j2)
                        {
                            for (int k2 = -4; k2 <= 4; ++k2)
                            {
                                IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2));
                                Block block = iblockstate.getBlock();

                                if (!block.canSustainLeaves(iblockstate, worldIn, blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2)))
                                {
                                    if (block.isLeaves(iblockstate, worldIn, blockpos$mutableblockpos.setPos(k + i2, l + j2, i1 + k2)))
                                    {
                                        this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = -2;
                                    }
                                    else
                                    {
                                        this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = -1;
                                    }
                                }
                                else
                                {
                                    this.surroundings[(i2 + 16) * 1024 + (j2 + 16) * 32 + k2 + 16] = 0;
                                }
                            }
                        }
                    }

                    for (int i3 = 1; i3 <= 4; ++i3)
                    {
                        for (int j3 = -4; j3 <= 4; ++j3)
                        {
                            for (int k3 = -4; k3 <= 4; ++k3)
                            {
                                for (int l3 = -4; l3 <= 4; ++l3)
                                {
                                    if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16] == i3 - 1)
                                    {
                                        if (this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16] == -2)
                                        {
                                            this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + l3 + 16] = i3;
                                        }

                                        if (this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16] == -2)
                                        {
                                            this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + l3 + 16] = i3;
                                        }

                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16] == -2)
                                        {
                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + l3 + 16] = i3;
                                        }

                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16] == -2)
                                        {
                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + l3 + 16] = i3;
                                        }

                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + (l3 + 16 - 1)] == -2)
                                        {
                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + (l3 + 16 - 1)] = i3;
                                        }

                                        if (this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1] == -2)
                                        {
                                            this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + l3 + 16 + 1] = i3;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                int l2 = this.surroundings[16912];

                if (l2 >= 0)
                {
                    worldIn.setBlockState(pos, state.withProperty(CHECK_DECAY, Boolean.valueOf(false)), 26);
                }
                else
                {
                    this.destroy(worldIn, pos);
                }
            }
        }
    }
	public void destroy(World worldIn, BlockPos pos)
    {
        this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
        worldIn.setBlockToAir(pos);
    }
}
