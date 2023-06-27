package kipster.nt.world.type;

import kipster.nt.biomes.BiomeInit;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldTypeIslands extends WorldType
{
	public WorldTypeIslands (String name) {
		
		super(name);
	}
	
	
	@Override
	public BiomeProvider getBiomeProvider(World world) 
	{
		BiomeProvider provider = new BiomeProviderSingle(BiomeInit.aliummeadow);
		return provider;
	}
	
	@Override
	  public float getCloudHeight()
	  {
	    return 280.0F;
	  }
	  
}