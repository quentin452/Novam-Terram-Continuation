package kipster.nt.util.handlers;

import kipster.nt.blocks.BlockInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public  class FuelHandler implements IFuelHandler{

	@Override
	public int getBurnTime(ItemStack fuel) {
	
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.AUTUMNSAPLINGBROWN)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SAPLINGPOPlAR)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SAPLINGTALLSPRUCE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.AUTUMNSAPLINGORANGE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.AUTUMNSAPLINGRED)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.AUTUMNSAPLINGYELLOW)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.CHERRYSAPLINGPINK)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.CHERRYSAPLINGPURPLE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.CHERRYSAPLINGWHITE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.CONIFERSAPLINGORANGE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.CONIFERSAPLINGYELLOW)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.JACARANDASAPLING)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.MAPLESAPLING)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.OAKSAPLINGRED)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.ORCHARDSAPLING)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.PAULOWNIASAPLING)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.WHITEMYRTLESAPLING)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGACACIA)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGBIRCH)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGBLUESPRUCE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGCHERRYPINK)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGCHERRYPURPLE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGCHERRYWHITE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGCONIFERORANGE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGCONIFERYELLOW)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGDARKOAK)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGJACARANDA)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGJUNGLE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGMAPLE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGOAK)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGREDOAK)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGREDSPRUCE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SHRUBSAPLINGSPRUCE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SPRUCESAPLINGBLUE)) {
			return 100;
		}
		if (fuel.getItem() == Item.getItemFromBlock(BlockInit.SPRUCESAPLINGRED)) {
			return 100;
		}
		
		return 0;
	
	}
} 