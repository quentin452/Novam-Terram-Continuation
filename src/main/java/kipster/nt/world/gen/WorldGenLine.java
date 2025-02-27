package kipster.nt.world.gen;


import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenLine extends WorldGenerator
{
    private final Block block;
    private final int startRadius;

    public WorldGenLine(Block blockIn, int startRadiusIn)
    {
        super(false);
        this.block = blockIn;
        this.startRadius = startRadiusIn;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        while (true)
        {
            label50:
            {
                if (position.getY() > 1)
                {
                    if (worldIn.isAirBlock(position.down()))
                    {
                        break label50;
                    }

                    Block block = worldIn.getBlockState(position.down()).getBlock();

                    if (block != Blocks.GRASS && block != Blocks.DIRT && block != Blocks.STONE)
                    {
                        break label50;
                    }
                }

                if (position.getY() <= 1)
                {
                    return false;
                }

                int i1 = this.startRadius;

                for (int i = 0; i1 >= 0 && i < 3; ++i)
                {
                    int j = i1 + rand.nextInt(1);
                    int k = i1 + rand.nextInt(1);
                    int l = i1 + rand.nextInt(1);
                    float f = (float)(j + k + l) * 0.230F + 0.7F;

                    for (BlockPos blockpos : BlockPos.getAllInBox(position.add(-j, -k, -l), position.add(j, k, l)))
                    {
                        if (blockpos.distanceSq(position) <= (double)(f * f))
                        {
                            worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2);
                        }
                    }

                    position = position.add(-(i1 + 1) + rand.nextInt(1 + i1 * 1), 0 - rand.nextInt(1), -(i1 + 1) + rand.nextInt(1 + i1 * 1));
                }

                return true;
            }
            position = position.down();
        }
    }
}