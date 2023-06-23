package kipster.nt.items;

import kipster.nt.NovamTerram;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {

	public static final List<Item> ITEMS = new ArrayList<Item>();

	public static void registerRender(Item item) {
		String translationKey = "item." + NovamTerram.modId + ".example";

		item.setTranslationKey(translationKey);

		ModelLoader.setCustomModelResourceLocation(
				item,
				0,
				new ModelResourceLocation(
						new ResourceLocation(NovamTerram.modId, translationKey.substring(5)),
						"inventory"));
	}


	public static void registerRender(Item item, int meta, String fileName) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(new ResourceLocation(NovamTerram.modId, fileName), "inventory"));
		
		}
	}