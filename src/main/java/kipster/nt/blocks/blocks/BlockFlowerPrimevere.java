package kipster.nt.blocks.blocks;

import kipster.nt.NovamTerram;
import kipster.nt.blocks.BlockInit;
import kipster.nt.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Random;

public class BlockFlowerPrimevere extends BlockFlower implements IHasModel {

    protected static final AxisAlignedBB PRIMEVEREFLOWER_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

    public BlockFlowerPrimevere(String name, Material material) {
        super();
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(SoundType.PLANT);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return PRIMEVEREFLOWER_AABB;
    }

    @Override
    public Item getItemDropped(IBlockState state, java.util.Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public void registerModels() {
        NovamTerram.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    public static void register(IForgeRegistry<Block> registry) {
        BlockFlowerPrimevere primevere = new BlockFlowerPrimevere("primevere_flower", Material.PLANTS);
        registry.register(primevere);
        ForgeRegistries.ITEMS.register(new ItemBlock(primevere).setRegistryName(primevere.getRegistryName()));
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Override
    public EnumFlowerColor getBlockType() {
        return EnumFlowerColor.YELLOW;
    }
}