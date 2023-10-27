package kipster.nt.blocks.blocks.blockspalings;

import kipster.nt.blocks.blocks.BlockSaplingBase;
import kipster.nt.util.interfaces.IHasModel;
import kipster.nt.world.gen.trees.WorldGenTreeAutumnYellow;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class BlockSaplingYellowAutumn extends BlockSaplingBase implements IHasModel, IGrowable
{
    public BlockSaplingYellowAutumn(String name, Material material) {
        super(name, material);
    }
}