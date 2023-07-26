package kipster.nt.world.gen;

import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.HashSet;
import java.util.Set;

public class TreeGeneratorRegistry {

    private Set<WorldGenerator> treeGenerators = new HashSet<>();

    public void registerTreeGenerator(WorldGenerator generator) {
        treeGenerators.add(generator);
    }
    public boolean overlapsExistingTrees(World worldIn, BlockPos position) {
        for (int x = position.getX() - 4; x <= position.getX() + 4; x++) {
            for (int z = position.getZ() - 4; z <= position.getZ() + 4; z++) {
                for (int y = position.getY(); y < position.getY() + 20; y++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (worldIn.getBlockState(pos).getBlock() instanceof BlockLeaves
                            || worldIn.getBlockState(pos).getBlock() == Blocks.LOG) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean containsTreeAt(World world, BlockPos pos, WorldGenerator self) {
        for (WorldGenerator generator : treeGenerators) {
            if (generator == self) {
                continue;
            }
            if (generator.generate(world, world.rand, pos)) {
                return true;
            }
        }
        return false;
    }
}