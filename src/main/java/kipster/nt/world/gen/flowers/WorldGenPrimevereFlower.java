package kipster.nt.world.gen.flowers;

import kipster.nt.blocks.blocks.BlockFlowerPrimevere;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenPrimevereFlower extends WorldGenerator {

    private final BlockFlowerPrimevere flower;

    public WorldGenPrimevereFlower(BlockFlowerPrimevere flower) {
        this.flower = flower;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {

        // Check position is valid
        if (position.getY() >= 0 && position.getY() < worldIn.getHeight()) {

            // Check block below is grass
            IBlockState grassState = worldIn.getBlockState(position.down());
            if (grassState.getBlock() == Blocks.GRASS) {

                // Set flower block state
                worldIn.setBlockState(position, flower.getDefaultState(), 2);
                return true;
            }
        }

        return false;
    }
}
