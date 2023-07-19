package kipster.nt.blocks.blocks.blockspalings;

import kipster.nt.blocks.blocks.BlockSaplingBase;
import kipster.nt.util.interfaces.IHasModel;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;

public class BlockSaplingOrchard extends BlockSaplingBase implements IHasModel, IGrowable
{
    public BlockSaplingOrchard(String name, Material material) {
        super(name, material);
    }


}