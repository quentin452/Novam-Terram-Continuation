package kipster.nt.blocks.blocks.blockspalings;

import kipster.nt.NovamTerram;
import kipster.nt.blocks.BlockInit;
import kipster.nt.blocks.blocks.BlockSaplingBase;
import kipster.nt.items.ItemInit;
import kipster.nt.util.interfaces.IHasModel;
import kipster.nt.world.gen.trees.WorldGenTreeShrubAcacia;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class BlockSaplingWhiteMyrtle extends BlockSaplingBase implements IHasModel, IGrowable
{
    public BlockSaplingWhiteMyrtle(String name, Material material) {
        super(name, material);
    }


}