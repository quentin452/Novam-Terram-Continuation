package kipster.nt.blocks.blocks;

import kipster.nt.NovamTerram;
import kipster.nt.blocks.BlockInit;
import kipster.nt.items.ItemInit;
import kipster.nt.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFruitBase extends BlockBush implements IHasModel {

    protected static final AxisAlignedBB FRUIT_AABB =
            new AxisAlignedBB(0, 0, 0, 1, 1, 1);

    public BlockFruitBase(String name, Material material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setHardness(0.0F);
        setSoundType(SoundType.PLANT);
        setCreativeTab(NovamTerram.NOVAMTERRAMTAB);
        setHardness(1.0F);
        setResistance(10.0F);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState,
                                                 IBlockAccess worldIn, BlockPos pos) {
        return FRUIT_AABB;
    }
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FRUIT_AABB;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerModels() {
        NovamTerram.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }













    //# todo supprimer des que les tests sont finit .
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        // Update the state whenever a neighboring block changes
        this.checkAndDropBlock(worldIn, pos, state);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }

    @Override
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
        Block block = worldIn.getBlockState(pos.offset(side)).getBlock();

        if (block == this) {
            // Refuser le placement sur un fruit du même type
            return false;
        }

        // Autoriser le placement à côté de tous les autres types de blocs
        return true;
    }
    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        Block block = world.getBlockState(pos.down()).getBlock();
        return block != this;
    }
}