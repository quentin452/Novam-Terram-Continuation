package kipster.nt.world.gen.flowers;

import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class WorldGenBlueflaxFlower extends WorldGenerator {
    private final IBlockState plantState;
    private final BlockBush plantBlock;
    private final Set<BlockPos> triedPositions = new HashSet<>();

    public WorldGenBlueflaxFlower(IBlockState plant) {
        this.plantState = plant;
        this.plantBlock = (BlockBush) plant.getBlock();
    }

    public boolean generate(World world, Random rand, BlockPos pos) {
        int flowerGroupSize = 3 + rand.nextInt(3); // 3-6 flowers

        boolean success = false;

        for (int i = 0; i < flowerGroupSize; i++) {
            BlockPos flowerPos = pos.add(rand.nextInt(3) - 1, 0, rand.nextInt(3) - 1);

            if (triedPositions.contains(flowerPos)) {
                continue; // Already tried this position, skip it
            }

            if (world.getBlockState(flowerPos.up()).getBlock() == Blocks.AIR
                    && plantBlock.canBlockStay(world, flowerPos.up(), plantState)
                    && world.getBlockState(flowerPos).getBlock() != Blocks.TALLGRASS
                    && world.getBlockState(flowerPos).getBlock() != Blocks.SAND
                    && world.getBlockState(flowerPos).getBlock() != Blocks.GRAVEL
                    && world.getBlockState(flowerPos).getBlock() == Blocks.GRASS) {

                world.setBlockState(flowerPos.up(), plantState);
                triedPositions.add(flowerPos);
                success = true;
            }
        }

        return success;
    }
}
