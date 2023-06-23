package kipster.nt;

import kipster.nt.util.handlers.RegistryHandler;
import kipster.nt.world.type.WorldTypeIslands;
import kipster.nt.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = NovamTerram.modId, name = NovamTerram.name, version = NovamTerram.version)
public class NovamTerram {
	
	public static final String modId = "nt";
	public static final String name = "Novam Terram";
	public static final String version = "7.3.2";

	public static final CreativeTabs NOVAMTERRAMTAB = new NovamTerramTab("novamterramtab");
	
	@Mod.Instance(modId)
	public static NovamTerram instance;
	
	@SidedProxy(serverSide = "kipster.nt.proxy.CommonProxy", clientSide = "kipster.nt.proxy.ClientProxy")
	public static CommonProxy proxy;
	

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		RegistryHandler.preInitRegistries(event);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		RegistryHandler.initRegistries();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("Stream ARTPOP");
		proxy.postInit(event);
		RegistryHandler.postInitRegistries();
		WorldType ISLANDS = new WorldTypeIslands("Islands");
		}
}