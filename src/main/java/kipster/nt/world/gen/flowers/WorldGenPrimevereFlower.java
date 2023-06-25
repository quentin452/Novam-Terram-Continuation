package kipster.nt.world.gen.flowers;

import kipster.nt.blocks.blocks.BlockFlowerPrimevere;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenPrimevereFlower extends WorldGenerator {

    private BlockFlowerPrimevere flower;
    private IBlockState grassState;
    private IBlockState dirtState;

    public WorldGenPrimevereFlower(BlockFlowerPrimevere flower) {
        this.flower = flower;
        this.grassState = Blocks.GRASS.getDefaultState();
        this.dirtState = Blocks.DIRT.getDefaultState();
    }

    public BlockFlowerPrimevere getFlower() {
        return flower;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        // Check position is valid
        if (position.getY() >= 0 && position.getY() < worldIn.getHeight()) {
            // Check block below is grass or dirt
            BlockPos downPos = position.down();
            IBlockState groundState = worldIn.getBlockState(downPos);
            if (groundState.getBlock() == Blocks.GRASS || groundState.getBlock() == Blocks.DIRT) {
                // Check if the flower can be placed at the position
                if (worldIn.isAirBlock(position)) {
                    // Set flower block state
                    IBlockState flowerState = flower.getDefaultState();
                    worldIn.setBlockState(position, flowerState, 3);
                    return true;
                } else {
                    // The block is already occupied, do not generate the flower
                    return false;
                }
            }
        }
        return false;
    }
}
