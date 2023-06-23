package kipster.nt.blocks.blocks;

import kipster.nt.NovamTerram;
import kipster.nt.blocks.BlockInit;
import kipster.nt.items.ItemInit;
import kipster.nt.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockSandstoneFace extends Block implements IHasModel
{
	public BlockSandstoneFace(String name, Material material) 
	{
		super(Material.ROCK);
		setTranslationKey(name);
		setRegistryName(name);
		setSoundType(SoundType.STONE);
		setHardness(0.8F);
		setCreativeTab(NovamTerram.NOVAMTERRAMTAB);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() 
	{
		NovamTerram.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}