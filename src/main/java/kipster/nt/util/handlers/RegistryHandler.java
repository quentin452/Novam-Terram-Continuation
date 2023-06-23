package kipster.nt.util.handlers;

import kipster.nt.biomes.BiomeInit;
import kipster.nt.blocks.BlockInit;
import kipster.nt.items.ItemInit;
import kipster.nt.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

@EventBusSubscriber
public class RegistryHandler {


  @SubscribeEvent
  public static void onItemRegister(RegistryEvent.Register<Item> event) {
    event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
  }

  @SubscribeEvent
  public static void onBlockRegister(RegistryEvent.Register<Block> event) {
    event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
  }

  @SubscribeEvent
  public static void onModelRegister(ModelRegistryEvent event) {
    for (Item item : ItemInit.ITEMS) {
      if (item instanceof IHasModel) {
        ((IHasModel) item).registerModels();
      }
    }

    for (Block block : BlockInit.BLOCKS) {
      if (block instanceof IHasModel) {
        ((IHasModel) block).registerModels();
      }
    }
  }

  @SubscribeEvent
  public static void onBiomeRegister(RegistryEvent.Register<Biome> event) {
    BiomeInit.initBiomes(event);
  }

  public static void preInitRegistries(FMLPreInitializationEvent e) {
    File directory = e.getModConfigurationDirectory();
    
  }

  public static void initRegistries() {
    OreDictionaryHandler.registerOreDictionary();
    GameRegistry.registerFuelHandler(new FuelHandler());
    FurnaceRecipeHandler.registerFurnaceRecipes();
  }

  public static void postInitRegistries() {
    
    }

  }
