package kipster.nt.biomes;

import kipster.nt.NovamTerram;
import kipster.nt.biomes.cool.*;
import kipster.nt.biomes.desert.*;
import kipster.nt.biomes.icy.*;
import kipster.nt.biomes.warm.*;
import kipster.nt.config.BiomeWeightConfig;
import kipster.nt.config.MiscConfig;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;

import java.util.ArrayList;
import java.util.List;


	public class BiomeInit {
	public static final List<IBiomeDetails> BIOMES = new ArrayList<>();

  	private static RegistryEvent.Register<Biome> biomeRegister;

  //WARM
  		///public static Biome hills = new BiomeNHills(new BiomeProperties("Hills").setBaseHeight(1.44F).setHeightVariation(0.64F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
  		public static Biome aegeanarchipelago = new BiomeAegeanArchipelago(new BiomeProperties("Aegean Archipelago").setBaseHeight(-0.92F).setHeightVariation(0.52F).setTemperature(Biomes.OCEAN.getDefaultTemperature()).setRainfall(0.3F).setWaterColor(0x00b8ff));
  		public static Biome aliummeadow = new BiomeAliumMeadow(new BiomeProperties("Alium Meadow").setBaseHeight(0.071F).setHeightVariation(0.006F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome autumnforest = new BiomeAutumnForest(new BiomeProperties("Autumn Forest").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome autumnforesthills = new BiomeAutumnForest(new BiomeProperties("Autumn Forest Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome birchhills = new BiomeBirchHills(new BiomeProperties("Birch Hills").setBaseHeight(0.62F).setHeightVariation(0.5F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome blossomforest = new BiomeBlossomForest(new BiomeProperties("Blossom Forest").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()).setWaterColor(0x63AFE1));
  		public static Biome blossomforesthills = new BiomeBlossomForest(new BiomeProperties("Blossom Forest Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()).setWaterColor(0x63AFE1));
  		public static Biome estuary = new BiomeEstuary(new BiomeProperties("Estuary").setBaseHeight(-0.28F).setHeightVariation(0.11F).setTemperature(Biomes.SWAMPLAND.getDefaultTemperature()).setRainfall(Biomes.SWAMPLAND.getRainfall()));
  		public static Biome fen = new BiomeFen(new BiomeProperties("Fen").setBaseHeight(-0.28F).setHeightVariation(0F).setTemperature(0.8F).setRainfall(0.9F).setWaterColor(14745518));
  		public static Biome fungaljungle = new BiomeFungalJungle(new BiomeProperties("Fungal Jungle").setBaseHeight(-0.098F).setHeightVariation(0.68F).setTemperature(Biomes.JUNGLE.getDefaultTemperature()).setRainfall(Biomes.JUNGLE.getRainfall()));
  		public static Biome fungaljunglehills = new BiomeFungalJungle(new BiomeProperties("Fungal Jungle Hills").setBaseHeight(-0.04F).setHeightVariation(0.78F).setHeightVariation(0.70F).setTemperature(Biomes.JUNGLE.getDefaultTemperature()).setRainfall(Biomes.JUNGLE.getRainfall()));
  		public static Biome grasshills = new BiomeGrassHills(new BiomeProperties("Grass Hills").setBaseHeight(0.08F).setHeightVariation(0.25F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
  		public static Biome greenmixedforest = new BiomeGreenMixedForest(new BiomeProperties("Green Mixed Forest").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()));
  		public static Biome greenmixedforesthills = new BiomeGreenMixedForest(new BiomeProperties("Green Mixed Forest Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()));
  		public static Biome greenswamp = new BiomeGreenSwamp(new BiomeProperties("Green Swamp").setBaseHeight(Biomes.SWAMPLAND.getBaseHeight()).setHeightVariation(Biomes.SWAMPLAND.getHeightVariation()).setTemperature(Biomes.SWAMPLAND.getDefaultTemperature()).setRainfall(Biomes.SWAMPLAND.getRainfall()).setWaterColor(0x379A3E));
  		public static Biome heath = new BiomeHeath(new BiomeProperties("Heath").setBaseHeight(0.2F).setHeightVariation(0.046F).setTemperature(0.9F).setRainfall(0.5F));
  		public static Biome highland = new BiomeHighland(new BiomeProperties("Highland").setBaseHeight(0.993F).setHeightVariation(0.536F).setTemperature(0.7F).setRainfall(0.2F));
  		public static Biome hills = new BiomeNHills(new BiomeProperties("Hills").setBaseHeight(1.15F).setHeightVariation(0.558F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
  		public static Biome hotspring = new BiomeHotSpring(new BiomeProperties("Hot Spring").setBaseHeight(-0.1F).setHeightVariation(0.172F).setTemperature(1F).setRainfall(0.5F).setWaterColor(0x008904));
  		public static Biome lake = new BiomeLake(new BiomeProperties("Lake").setBaseHeight(-0.7F).setHeightVariation(0.1F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome lowland = new BiomeLowland(new BiomeProperties("Lowland").setBaseHeight(-0.14F).setHeightVariation(0.173F).setTemperature(0.4F).setRainfall(1F));
  		public static Biome meadow = new BiomeMeadow(new BiomeProperties("Meadow").setBaseHeight(0.071F).setHeightVariation(0.006F).setTemperature(0.8F).setRainfall(0.4F));
  		//public static Biome meadow = new BiomeMeadow(new BiomeProperties("Meadow").setBaseHeight(0.086F).setHeightVariation(0.014F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome mixedforest = new BiomeMixedForest(new BiomeProperties("Mixed Forest").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()));
  		public static Biome mixedforesthills = new BiomeMixedForest(new BiomeProperties("Mixed Forest Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()));
  		public static Biome monsoonforest = new BiomeMonsoonForest(new BiomeProperties("Monsoon Forest").setBaseHeight(-0.07F).setHeightVariation(0.68F).setTemperature(Biomes.JUNGLE.getDefaultTemperature()).setRainfall(2F).setWaterColor(0x00CDF9));
  		public static Biome moorland = new BiomeMoorland(new BiomeProperties("Moorland").setBaseHeight(1F).setHeightVariation(0.5F).setTemperature(0.47F).setRainfall(1.5F));
  		public static Biome mountains = new BiomeNHills(new BiomeProperties("Mountains").setBaseHeight(1.74F).setHeightVariation(0.78F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
  		public static Biome oakforest = new BiomeOakForest(new BiomeProperties("Oak Forest").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()));
  		public static Biome oakforesthills = new BiomeOakForest(new BiomeProperties("Oak Forest Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()));
  		public static Biome poppymeadow = new BiomePoppyMeadow(new BiomeProperties("Poppy Meadow").setBaseHeight(0.071F).setHeightVariation(0.006F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome royalforest = new BiomeRoyalForest(new BiomeProperties("Royal Forest").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()));
  		public static Biome royalforesthills = new BiomeRoyalForest(new BiomeProperties("Royal Forest Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()));
  		public static Biome talloakforest = new BiomeOakForestTall(new BiomeProperties("Tall Oak Forest").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()));
  		public static Biome talloakforesthills = new BiomeOakForestTall(new BiomeProperties("Tall Oak Forest Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.FOREST.getRainfall()));
  		public static Biome whiteorchard = new BiomeWhiteOrchard(new BiomeProperties("White Orchard").setBaseHeight(0.026F).setHeightVariation(0.03F).setTemperature(1F).setRainfall(0.4F));
  		public static Biome pinkbeach = new BiomePinkBeach(new BiomeProperties("Pink Beach").setBaseHeight(-0.4F).setHeightVariation(0.052F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome purplebeach = new BiomePurpleBeach(new BiomeProperties("Purple Beach").setBaseHeight(-0.4F).setHeightVariation(0.052F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome whitebeach = new BiomeWhiteBeach(new BiomeProperties("White Beach").setBaseHeight(-0.4F).setHeightVariation(0.052F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome orangebeach = new BiomeOrangeBeach(new BiomeProperties("Orange Beach").setBaseHeight(-0.4F).setHeightVariation(0.052F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome ironbeach = new BiomeIronBeach(new BiomeProperties("Iron Beach").setBaseHeight(-0.4F).setHeightVariation(0.052F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome olivinebeach = new BiomeOlivineBeach(new BiomeProperties("Olivine Beach").setBaseHeight(-0.4F).setHeightVariation(0.052F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome blackbeach = new BiomeBlackBeach(new BiomeProperties("Black Beach").setBaseHeight(-0.4F).setHeightVariation(0.052F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome brownbeach = new BiomeBrownBeach(new BiomeProperties("Brown Beach").setBaseHeight(-0.4F).setHeightVariation(0.052F).setTemperature(0.8F).setRainfall(0.4F));

  		//COOL
  		public static Biome autumntaiga = new BiomeAutumnTaiga(new BiomeProperties("Autumn Taiga").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome autumntaigahills = new BiomeAutumnTaiga(new BiomeProperties("Autumn Taiga Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome bluetaiga = new BiomeBlueTaiga(new BiomeProperties("Blue Taiga").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome bluetaigahills = new BiomeBlueTaiga(new BiomeProperties("Blue Taiga Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome cliffs = new BiomeCliffs(new BiomeProperties("Cliffs").setBaseHeight(3.2F).setHeightVariation(0.45F).setTemperature(Biomes.FOREST.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
  		public static Biome stonefields = new BiomeStoneFields(new BiomeProperties("Stone Fields").setBaseHeight(Biomes.PLAINS.getBaseHeight()).setHeightVariation(Biomes.PLAINS.getHeightVariation()).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome mapleforest = new BiomeMapleForest(new BiomeProperties("Maple Forest").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome mapleforesthills = new BiomeMapleForest(new BiomeProperties("Maple Forest Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome megaautumntaiga = new BiomeMegaAutumnTaiga(new BiomeProperties("Mega Autumn Taiga").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome megaautumntaigahills = new BiomeMegaAutumnTaiga(new BiomeProperties("Mega Autumn Taiga Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome megabluetaiga = new BiomeMegaBlueTaiga(new BiomeProperties("Mega Blue Taiga").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome megabluetaigahills = new BiomeMegaBlueTaiga(new BiomeProperties("Mega Blue Taiga Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome megamapleforest = new BiomeMegaMapleForest(new BiomeProperties("Mega Maple Forest").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome megamapleforesthills = new BiomeMegaMapleForest(new BiomeProperties("Mega Maple Forest Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome pineland = new BiomePineland(new BiomeProperties("Pineland").setBaseHeight(0.993F).setHeightVariation(0.536F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome rockytaiga = new BiomeRockyTaiga(new BiomeProperties("Rocky Taiga").setBaseHeight(0F).setHeightVariation(0.66F).setTemperature(Biomes.TAIGA.getDefaultTemperature()).setRainfall(Biomes.TAIGA.getRainfall()));
  		public static Biome shrubland = new BiomeShrubland(new BiomeProperties("Shrubland").setBaseHeight(0.42F).setHeightVariation(0.138F).setTemperature(0.8F).setRainfall(0.4F));
  		public static Biome stonemountains = new BiomeStoneMountains(new BiomeProperties("Stone Mountains").setBaseHeight(1.74F).setHeightVariation(0.78F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
  		
  		//DESERT
  		public static Biome clayland = new BiomeClayland(new BiomeProperties("Clayland").setBaseHeight(0.87F).setHeightVariation(0.51F).setTemperature(0.9F).setRainfall(0.3F));
  		public static Biome lushdesert = new BiomeLushDesert(new BiomeProperties("Lush Desert").setBaseHeight(0.123F).setHeightVariation(0.044F).setTemperature(Biomes.JUNGLE.getDefaultTemperature()).setRainfall(0.9F).setWaterColor(0x00b8ff));
  		public static Biome redsanddune = new BiomeRedSandDune(new BiomeProperties("Red Sand Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(Biomes.DESERT.getRainfall()).setRainDisabled());
  		public static Biome rockland = new BiomeRockland(new BiomeProperties("Rockland").setBaseHeight(0.87F).setHeightVariation(0.51F).setTemperature(0.9F).setRainfall(0.3F));
  		public static Biome sahel = new BiomeSahel(new BiomeProperties("Sahel").setBaseHeight(0.125F).setHeightVariation(0.046F).setTemperature(0.9F).setRainfall(1F));
  		public static Biome sanddune = new BiomeSandDune(new BiomeProperties("Sand Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(Biomes.DESERT.getRainfall()).setRainDisabled());
  		public static Biome sandymountains = new BiomeSandyMountains(new BiomeProperties("Sandy Mountains").setBaseHeight(1.74F).setHeightVariation(0.78F).setTemperature(Biomes.DESERT.getDefaultTemperature()).setRainfall(Biomes.DESERT.getRainfall()).setRainDisabled());
  		public static Biome scrubland = new BiomeScrubland(new BiomeProperties("Scrubland").setBaseHeight(Biomes.SAVANNA.getBaseHeight()).setHeightVariation(Biomes.SAVANNA.getHeightVariation()).setTemperature(Biomes.SAVANNA.getDefaultTemperature()).setRainfall(Biomes.SAVANNA.getRainfall()));
  		public static Biome tropicaldesert = new BiomeTropicalDesert(new BiomeProperties("Tropical Desert").setBaseHeight(0.1F).setHeightVariation(0.038F).setTemperature(Biomes.JUNGLE.getDefaultTemperature()).setRainfall(1F).setWaterColor(0x00CDF9));
  		
  		//ICY
  		public static Biome alps = new BiomeAlps(new BiomeProperties("Alps").setBaseHeight(1.17F).setHeightVariation(0.559F).setTemperature(Biomes.EXTREME_HILLS.getDefaultTemperature()).setRainfall(Biomes.EXTREME_HILLS.getRainfall()));
  		public static Biome coldbirchforest = new BiomeColdForest(BiomeForest.Type.BIRCH, "Cold Birch Forest");
  		public static Biome coldbirchforesthills = new BiomeColdForest(BiomeForest.Type.BIRCH, "Cold Birch Forest Hills");
  		public static Biome coldbluetaiga = new BiomeColdBlueTaiga(new BiomeProperties("Cold Blue Taiga").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.COLD_TAIGA.getDefaultTemperature()).setRainfall(Biomes.COLD_TAIGA.getRainfall()).setSnowEnabled());
  		public static Biome coldbluetaigahills = new BiomeColdBlueTaiga(new BiomeProperties("Cold Blue Taiga Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.COLD_TAIGA.getDefaultTemperature()).setRainfall(Biomes.COLD_TAIGA.getRainfall()).setSnowEnabled());
  		public static Biome colddesert = new BiomeColdDesert(new BiomeProperties("Cold Desert").setBaseHeight(Biomes.ROOFED_FOREST.getBaseHeight()).setHeightVariation(Biomes.ROOFED_FOREST.getHeightVariation()).setTemperature(-2).setRainfall(Biomes.ROOFED_FOREST.getRainfall()).setSnowEnabled());
  		public static Biome coldflowerforest = new BiomeColdForest(BiomeForest.Type.FLOWER, "Cold Flower Forest");
  		public static Biome coldflowerforesthills = new BiomeColdForest(BiomeForest.Type.FLOWER, "Cold Flower Forest Hills");
  		public static Biome coldforest = new BiomeColdForest(BiomeForest.Type.NORMAL, "Cold Forest");
  		public static Biome coldforesthills = new BiomeColdForest(BiomeForest.Type.NORMAL, "Cold Forest Hills");
  		public static Biome coldmegabluetaiga = new BiomeColdMegaBlueTaiga(new BiomeProperties("Cold Mega Blue Taiga").setBaseHeight(Biomes.FOREST.getBaseHeight()).setHeightVariation(Biomes.FOREST.getHeightVariation()).setTemperature(Biomes.COLD_TAIGA.getDefaultTemperature()).setRainfall(Biomes.COLD_TAIGA.getRainfall()).setSnowEnabled());
  		public static Biome coldmegabluetaigahills = new BiomeColdMegaBlueTaiga(new BiomeProperties("Cold Mega Blue Taiga Hills").setBaseHeight(0.98F).setHeightVariation(0.37F).setTemperature(Biomes.COLD_TAIGA.getDefaultTemperature()).setRainfall(Biomes.COLD_TAIGA.getRainfall()).setSnowEnabled());
  		public static Biome coldmegasprucetaiga = new BiomeColdMegaTaiga(BiomeTaiga.Type.MEGA_SPRUCE, "Cold Mega Spruce Taiga");
  		public static Biome coldmegasprucetaigahills = new BiomeColdMegaTaiga(BiomeTaiga.Type.MEGA_SPRUCE, "Cold Mega Spruce Taiga Hills");
  		public static Biome coldmegataiga = new BiomeColdMegaTaiga(BiomeTaiga.Type.MEGA, "Cold Mega Taiga");
  		public static Biome coldmegataigahills = new BiomeColdMegaTaiga(BiomeTaiga.Type.MEGA, "Cold Mega Taiga Hills");
  		public static Biome coldroofedforest = new BiomeColdForest(BiomeForest.Type.ROOFED, "Cold Roofed Forest");
  		public static Biome coldroofedforesthills = new BiomeColdForest(BiomeForest.Type.ROOFED, "Cold Roofed Forest Hills");
  		public static Biome glacier = new BiomeGlacier(new BiomeProperties("Glacier").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.FROZEN_OCEAN.getDefaultTemperature()).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()));
  		public static Biome icytundra = new BiomeIcyTundra(new BiomeProperties("Icy Tundra").setBaseHeight(Biomes.PLAINS.getBaseHeight()).setHeightVariation(Biomes.PLAINS.getHeightVariation()).setTemperature(-1).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()).setSnowEnabled());
  		public static Biome snowdune = new BiomeSnowDune(new BiomeProperties("Snow Dune").setBaseHeight(0.35F).setHeightVariation(0.49F).setTemperature(Biomes.FROZEN_OCEAN.getDefaultTemperature()).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()));
  		public static Biome tundra = new BiomeTundra(new BiomeProperties("Tundra").setBaseHeight(Biomes.PLAINS.getBaseHeight()).setHeightVariation(Biomes.PLAINS.getHeightVariation()).setTemperature(0.20F).setRainfall(Biomes.FROZEN_OCEAN.getRainfall()));


  public static void initBiomes(RegistryEvent.Register<Biome> event) {

    biomeRegister = event;

    //WARM
	registerBiome(aegeanarchipelago, "aegean_archipelago", BiomeWeightConfig.aegeanarchipelagoWeight, MiscConfig.enableVillageInAegeanArchipelago, BiomeType.WARM, Type.OCEAN);
	registerBiome(aliummeadow, "alium_meadow", BiomeWeightConfig.aliummeadowWeight, MiscConfig.enableVillageInAliumMeadow, BiomeType.WARM, Type.PLAINS, Type.MAGICAL, Type.RARE);
	registerBiome(autumnforest, "autumn_forest", BiomeWeightConfig.autumnforestWeight, MiscConfig.enableVillageInAutumnForest, BiomeType.WARM, Type.FOREST);
	registerBiome(autumnforesthills, "autumn_forest_hills", BiomeWeightConfig.autumnforesthillsWeight, MiscConfig.enableVillageInAutumnForestHills, BiomeType.WARM, Type.FOREST);
	registerBiome(birchhills, "birch_hills", BiomeWeightConfig.birchhillsWeight, MiscConfig.enableVillageInBirchHills, BiomeType.WARM, Type.HILLS);
	registerBiome(blossomforest, "blossom_forest", BiomeWeightConfig.blossomforestWeight, MiscConfig.enableVillageInBlosssomForest, BiomeType.WARM, Type.FOREST, Type.MAGICAL);
	registerBiome(blossomforesthills, "blossom_forest_hills", BiomeWeightConfig.blossomforesthillsWeight, MiscConfig.enableVillageInBlossomForestHills, BiomeType.WARM, Type.FOREST, Type.MAGICAL);
	registerBiome(estuary, "estuary", BiomeWeightConfig.estuaryWeight, MiscConfig.enableVillageInEstuary, BiomeType.WARM, Type.RIVER, Type.SWAMP);
	registerBiome(fen, "fen", BiomeWeightConfig.fenWeight, MiscConfig.enableVillageInFen, BiomeType.WARM, Type.SWAMP ,Type.WET);
	registerBiome(fungaljungle, "fungal_jungle", BiomeWeightConfig.fungaljungleWeight, MiscConfig.enableVillageInFungalJungle, BiomeType.WARM, Type.JUNGLE, Type.MUSHROOM, Type.MAGICAL, Type.RARE);
	registerBiome(fungaljunglehills, "fungal_jungle_hills", BiomeWeightConfig.fungaljunglehillsWeight, MiscConfig.enableVillageInFungalJungleHills, BiomeType.WARM, Type.JUNGLE, Type.MUSHROOM, Type.MAGICAL, Type.RARE);
	registerBiome(grasshills, "grass_hills", BiomeWeightConfig.grasshillsWeight, MiscConfig.enableVillageInGrassHills, BiomeType.WARM, Type.PLAINS);
	registerBiome(greenmixedforest, "green_mixed_forest", BiomeWeightConfig.greenmixedforestWeight, MiscConfig.enableVillageInGreenMixedForest, BiomeType.WARM, Type.FOREST);
	registerBiome(greenmixedforesthills, "green_mixed_forest_hills", BiomeWeightConfig.greenmixedforesthillsWeight, MiscConfig.enableVillageInGreenMixedForestHills, BiomeType.WARM, Type.FOREST);
	registerBiome(greenswamp, "green_swamp", BiomeWeightConfig.greenswampWeight, MiscConfig.enableVillageInGreenSwamp, BiomeType.WARM, Type.SWAMP);
	registerBiome(heath, "heath", BiomeWeightConfig.heathWeight, MiscConfig.enableVillageInHeath, BiomeType.WARM, Type.PLAINS, Type.SAVANNA);
	registerBiome(highland, "highland", BiomeWeightConfig.highlandWeight, MiscConfig.enableVillageInHighland, BiomeType.WARM, Type.HILLS);
	registerBiome(hills, "hills", BiomeWeightConfig.hillsWeight, MiscConfig.enableVillageInHills, BiomeType.WARM, Type.HILLS);
	registerBiome(hotspring, "hotspring", BiomeWeightConfig.hotspringWeight, MiscConfig.enableVillageInHotSpring, BiomeType.WARM, Type.WET);
	registerBiome(lake, "lake", BiomeWeightConfig.lakeWeight, MiscConfig.enableVillageInLake, BiomeType.WARM, Type.WATER, Type.CONIFEROUS);
	registerBiome(lowland, "lowland", BiomeWeightConfig.lowlandWeight, MiscConfig.enableVillageInLowland, BiomeType.WARM, Type.CONIFEROUS ,Type.WET);
	registerBiome(meadow, "meadow", BiomeWeightConfig.meadowWeight, MiscConfig.enableVillageInMeadow, BiomeType.WARM, Type.PLAINS);
	registerBiome(mixedforest, "mixed_forest", BiomeWeightConfig.mixedforestWeight, MiscConfig.enableVillageInMixedForest, BiomeType.WARM, Type.FOREST);
	registerBiome(mixedforesthills, "mixed_forest_hills", BiomeWeightConfig.mixedforesthillsWeight, MiscConfig.enableVillageInMixedForestHils, BiomeType.WARM, Type.FOREST);
	registerBiome(monsoonforest, "monsoon_forest", BiomeWeightConfig.monsoonforestWeight, MiscConfig.enableVillageInMonsoonForest, BiomeType.WARM, Type.JUNGLE, Type.WET, Type.RARE);
	registerBiome(moorland, "moorland", BiomeWeightConfig.moorlandWeight, MiscConfig.enableVillageInMoorland, BiomeType.WARM, Type.HILLS);
	registerBiome(mountains, "mountains", BiomeWeightConfig.mountainsWeight, MiscConfig.enableVillageInMountains, BiomeType.WARM, Type.MOUNTAIN);
	registerBiome(oakforest, "oak_forest", BiomeWeightConfig.oakforestWeight, MiscConfig.enableVillageInOakForest, BiomeType.WARM, Type.FOREST);
	registerBiome(oakforesthills, "oak_forest_hills", BiomeWeightConfig.oakforesthillsWeight, MiscConfig.enableVillageInOakForestHills, BiomeType.WARM, Type.FOREST);
	registerBiome(poppymeadow, "poppy_meadow", BiomeWeightConfig.poppymeadowWeight, MiscConfig.enableVillageInPoppyMeadow, BiomeType.WARM, Type.PLAINS, Type.MAGICAL, Type.RARE);
	registerBiome(royalforest, "royal_forest", BiomeWeightConfig.royalforestWeight, MiscConfig.enableVillageInRoyalForest, BiomeType.WARM, Type.FOREST, Type.MAGICAL);
	registerBiome(royalforesthills, "royal_forest_hills", BiomeWeightConfig.royalforesthillsWeight, MiscConfig.enableVillageInRoyalForestHils, BiomeType.WARM, Type.FOREST, Type.MAGICAL);
	registerBiome(talloakforest, "tall_oak_forest", BiomeWeightConfig.talloakforestWeight, MiscConfig.enableVillageInTallOakForest, BiomeType.WARM, Type.FOREST);
	registerBiome(talloakforesthills, "tall_oak_forest_hills", BiomeWeightConfig.talloakforesthillsWeight, MiscConfig.enableVillageInTallOakForestHills, BiomeType.WARM, Type.FOREST);
	registerBiome(whiteorchard, "white_orchard", BiomeWeightConfig.whiteorchardWeight, MiscConfig.enableVillageInWhiteOrchard, BiomeType.WARM, Type.PLAINS);
	registerBiome(pinkbeach, "pink_beach", BiomeWeightConfig.pinkbeachWeight, MiscConfig.enableVillageInPinkBeach, BiomeType.WARM, Type.BEACH, Type.OCEAN);
	registerBiome(purplebeach, "purple_beach", BiomeWeightConfig.purplebeachWeight, MiscConfig.enableVillageInPurpleBeach, BiomeType.WARM, Type.BEACH, Type.OCEAN);
	registerBiome(orangebeach, "orange_beach", BiomeWeightConfig.orangebeachWeight, MiscConfig.enableVillageInOrangeBeach, BiomeType.WARM, Type.BEACH, Type.OCEAN);
	registerBiome(whitebeach, "white_beach", BiomeWeightConfig.whitebeachWeight, MiscConfig.enableVillageInWhiteBeach, BiomeType.WARM, Type.BEACH, Type.OCEAN);
	registerBiome(blackbeach, "black_beach", BiomeWeightConfig.blackbeachWeight, MiscConfig.enableVillageInBlackBeach, BiomeType.WARM, Type.BEACH, Type.OCEAN);
	registerBiome(ironbeach, "iron_beach", BiomeWeightConfig.ironbeachWeight, MiscConfig.enableVillageInIronBeach, BiomeType.WARM, Type.BEACH, Type.OCEAN);
	registerBiome(olivinebeach, "olivine_beach", BiomeWeightConfig.olivinebeachWeight, MiscConfig.enableVillageInOlivineBeach, BiomeType.WARM, Type.BEACH, Type.OCEAN);
	registerBiome(brownbeach, "brown_beach", BiomeWeightConfig.brownbeachWeight, MiscConfig.enableVillageInBrownBeach, BiomeType.WARM, Type.BEACH, Type.OCEAN);
	
	//COOL
	registerBiome(autumntaiga, "autumn_taiga", BiomeWeightConfig.autumntaigaWeight, MiscConfig.enableVillageInAutumnTaiga, BiomeType.COOL, Type.CONIFEROUS);
	registerBiome(autumntaigahills, "autumn_taiga_hills", BiomeWeightConfig.autumntaigahillsWeight, MiscConfig.enableVillageInAutumnTaigaHills, BiomeType.COOL, Type.CONIFEROUS);
	registerBiome(bluetaiga, "blue_taiga", BiomeWeightConfig.bluetaigaWeight, MiscConfig.enableVillageInBlueTaiga, BiomeType.COOL, Type.CONIFEROUS);
	registerBiome(bluetaigahills, "blue_taiga_hills", BiomeWeightConfig.bluetaigahillsWeight, MiscConfig.enableVillageInBlueTaigaHills, BiomeType.COOL, Type.CONIFEROUS);
	registerBiome(cliffs, "cliffs", BiomeWeightConfig.cliffsWeight, MiscConfig.enableVillageInCliffs, BiomeType.COOL, Type.MOUNTAIN ,Type.CONIFEROUS);
	registerBiome(stonefields, "stone_fields", BiomeWeightConfig.stonefieldsWeight, MiscConfig.enableVillageInStoneFields, BiomeType.COOL, Type.PLAINS);
	registerBiome(mapleforest, "maple_forest", BiomeWeightConfig.mapleforestWeight, MiscConfig.enableVillageInMapleForest, BiomeType.COOL, Type.CONIFEROUS);
	registerBiome(mapleforesthills, "maple_forest_hills", BiomeWeightConfig.mapleforesthillsWeight, MiscConfig.enableVillageInMapleForestHills, BiomeType.COOL, Type.CONIFEROUS);
	registerBiome(megaautumntaiga, "mega_autumn_taiga", BiomeWeightConfig.megaautumntaigaWeight, MiscConfig.enableVillageInMegaAutumnTaiga, BiomeType.COOL, Type.CONIFEROUS, Type.RARE);
	registerBiome(megaautumntaigahills, "mega_autumn_taiga_hills", BiomeWeightConfig.megaautumntaigahillsWeight, MiscConfig.enableVillageInMegaAutumnTaigaHills, BiomeType.COOL, Type.CONIFEROUS, Type.RARE);
	registerBiome(megabluetaiga, "mega_blue_taiga", BiomeWeightConfig.megabluetaigaWeight, MiscConfig.enableVillageInMegaBlueTaiga, BiomeType.COOL, Type.CONIFEROUS, Type.RARE);
	registerBiome(megabluetaigahills, "mega_blue_taiga_hills", BiomeWeightConfig.megabluetaigahillsWeight, MiscConfig.enableVillageInMegaBlueTaigaHills, BiomeType.COOL, Type.CONIFEROUS, Type.RARE);
	registerBiome(megamapleforest, "mega_maple_forest", BiomeWeightConfig.megamapleforestWeight, MiscConfig.enableVillageInMegaMapleForest, BiomeType.COOL, Type.CONIFEROUS, Type.RARE);
	registerBiome(megamapleforesthills, "mega_maple_forest_hills", BiomeWeightConfig.megamapleforesthillsWeight, MiscConfig.enableVillageInMegaMapleForestHills, BiomeType.COOL, Type.CONIFEROUS, Type.RARE);
	registerBiome(pineland, "pineland", BiomeWeightConfig.pinelandWeight, MiscConfig.enableVillageInPineland, BiomeType.COOL, Type.HILLS, Type.CONIFEROUS);
	registerBiome(rockytaiga, "rocky_taiga", BiomeWeightConfig.rockytaigaWeight, MiscConfig.enableVillageInRockyTaiga, BiomeType.COOL, Type.CONIFEROUS);
	registerBiome(shrubland, "shrubland", BiomeWeightConfig.shrublandWeight, MiscConfig.enableVillageInShrubland, BiomeType.COOL, Type.CONIFEROUS);
	registerBiome(stonemountains, "stone_mountains", BiomeWeightConfig.stonemountainsWeight, MiscConfig.enableVillageInStoneMountains, BiomeType.COOL, Type.MOUNTAIN);
	
	//DESERT
	registerBiome(clayland, "clayland", BiomeWeightConfig.claylandWeight, MiscConfig.enableVillageInClayland, BiomeType.DESERT, Type.SAVANNA);
	registerBiome(lushdesert, "lush_desert", BiomeWeightConfig.lushdesertWeight, MiscConfig.enableVillageInLushDesert, BiomeType.DESERT, Type.SAVANNA, Type.LUSH, Type.JUNGLE);
	registerBiome(redsanddune, "red_sand_dune", BiomeWeightConfig.redsandduneWeight, MiscConfig.enableVillageInRedSandDune, BiomeType.DESERT, Type.SANDY);
	registerBiome(rockland, "rockland", BiomeWeightConfig.rocklandWeight, MiscConfig.enableVillageInRockland, BiomeType.DESERT, Type.SAVANNA);
	registerBiome(sahel, "sahel", BiomeWeightConfig.sahelWeight, MiscConfig.enableVillageInSahel, BiomeType.DESERT, Type.SANDY);
	registerBiome(sanddune, "sand_dune", BiomeWeightConfig.sandduneWeight, MiscConfig.enableVillageInSandDune, BiomeType.DESERT, Type.SANDY);
	registerBiome(sandymountains, "sandy_mountains", BiomeWeightConfig.sandymountainsWeight, MiscConfig.enableVillageInSandyMountains, BiomeType.DESERT, Type.SANDY, Type.MOUNTAIN);
	registerBiome(scrubland, "scrubland", BiomeWeightConfig.scrublandWeight, MiscConfig.enableVillageInScrubland, BiomeType.DESERT, Type.SAVANNA);
	registerBiome(tropicaldesert, "tropical_desert", BiomeWeightConfig.tropicaldesertWeight, MiscConfig.enableVillageInTropicalDesert, BiomeType.DESERT, Type.SAVANNA, Type.LUSH, Type.JUNGLE);

	//ICY
	registerBiome(alps, "alps", BiomeWeightConfig.alpsWeight, MiscConfig.enableVillageInAlps, BiomeType.ICY, Type.MOUNTAIN, Type.CONIFEROUS, Type.SNOWY);
	registerBiome(coldbirchforest, "cold_birch_forest", BiomeWeightConfig.coldbirchforestWeight, MiscConfig.enableVillageInColdBirchForest, BiomeType.ICY, Type.FOREST, Type.SNOWY);
	registerBiome(coldbirchforesthills, "cold_birch_forest_hills", BiomeWeightConfig.coldbirchforesthillsWeight, MiscConfig.enableVillageInColdBirchForestHills, BiomeType.ICY, Type.FOREST, Type.SNOWY);
	registerBiome(coldbluetaiga, "cold_blue_taiga", BiomeWeightConfig.coldbluetaigaWeight, MiscConfig.enableVillageInColdBlueTaiga, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
	registerBiome(coldbluetaigahills, "cold_blue_taiga_hills", BiomeWeightConfig.coldbluetaigahillsWeight, MiscConfig.enableVillageInColdBlueTaigaHills, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY);
	registerBiome(colddesert, "cold_desert", BiomeWeightConfig.colddesertWeight, MiscConfig.enableVillageInColdDesert, BiomeType.ICY, Type.COLD);
	registerBiome(coldflowerforest, "cold_flower_forest", BiomeWeightConfig.coldflowerforestWeight, MiscConfig.enableVillageInColdFlowerForest, BiomeType.ICY, Type.FOREST, Type.SNOWY);
	registerBiome(coldflowerforesthills, "cold_flower_forest_hills", BiomeWeightConfig.coldflowerforesthillsWeight, MiscConfig.enableVillageInColdFlowerForestHills, BiomeType.ICY, Type.FOREST, Type.SNOWY);
	registerBiome(coldforest, "cold_forest", BiomeWeightConfig.coldforestWeight, MiscConfig.enableVillageInColdForest, BiomeType.ICY, Type.FOREST, Type.SNOWY);
	registerBiome(coldforesthills, "cold_forest_hills", BiomeWeightConfig.coldforesthillsWeight, MiscConfig.enableVillageInColdForestHills, BiomeType.ICY, Type.FOREST, Type.SNOWY);
	registerBiome(coldmegabluetaiga, "cold_mega_blue_taiga", BiomeWeightConfig.coldmegabluetaigaWeight, MiscConfig.enableVillageInColdMegaBlueTaiga, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY, Type.RARE);
	registerBiome(coldmegabluetaigahills, "cold_mega_blue_taiga_hills", BiomeWeightConfig.coldmegabluetaigahillsWeight, MiscConfig.enableVillageInColdMegaBlueTaigaHills, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY, Type.RARE);
	registerBiome(coldmegasprucetaiga, "cold_mega_spruce_taiga", BiomeWeightConfig.coldmegasprucetaigaWeight, MiscConfig.enableVillageInColdMegaSpruceTaiga, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY, Type.RARE);
	registerBiome(coldmegasprucetaigahills, "cold_mega_spruce_taiga_hills", BiomeWeightConfig.coldmegasprucetaigahillsWeight, MiscConfig.enableVillageInColdMegaSpruceTaigaHills, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY, Type.RARE);
	registerBiome(coldmegataiga, "cold_mega_taiga", BiomeWeightConfig.coldmegataigaWeight, MiscConfig.enableVillageInColdMegaTaiga, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY, Type.RARE);
	registerBiome(coldmegataigahills, "cold_mega_taiga_hills", BiomeWeightConfig.coldmegataigahillsWeight, MiscConfig.enableVillageInColdMegaTaigaHills, BiomeType.ICY, Type.CONIFEROUS, Type.SNOWY, Type.RARE);
	registerBiome(coldroofedforest, "cold_roofed_forest", BiomeWeightConfig.coldroofedforestWeight, MiscConfig.enableVillageInColdRoofedForest, BiomeType.ICY, Type.FOREST, Type.SNOWY);
	registerBiome(coldroofedforesthills, "cold_roofed_forest_hills", BiomeWeightConfig.coldroofedforesthillsWeight, MiscConfig.enableVillageInColdRoofedForestHills, BiomeType.ICY, Type.FOREST, Type.SNOWY);
	registerBiome(glacier, "glacier", BiomeWeightConfig.glacierWeight, MiscConfig.enableVillageInGlacier, BiomeType.ICY, Type.COLD, Type.SNOWY);
	registerBiome(icytundra, "icy_tundra", BiomeWeightConfig.icytundraWeight, MiscConfig.enableVillageInIcyTundra, BiomeType.ICY, Type.COLD, Type.SNOWY);
	registerBiome(snowdune, "snowdune", BiomeWeightConfig.snowduneWeight, MiscConfig.enableVillageInSnowDune, BiomeType.ICY, Type.COLD, Type.SNOWY);
	registerBiome(tundra, "tundra", BiomeWeightConfig.tundraWeight, MiscConfig.enableVillageInTundra, BiomeType.ICY, Type.COLD);
	

	BiomeManager.addVillageBiome(Biomes.JUNGLE, MiscConfig.enableVillageInJungle);
	BiomeManager.addVillageBiome(Biomes.FOREST, MiscConfig.enableVillageInForest);
	BiomeManager.addVillageBiome(Biomes.BIRCH_FOREST, MiscConfig.enableVillageInBirchForest);
	BiomeManager.addVillageBiome(Biomes.ROOFED_FOREST, MiscConfig.enableVillageInRoofedForest);
	BiomeManager.addVillageBiome(Biomes.MUTATED_FOREST, MiscConfig.enableVillageInFlowerForest);
	BiomeManager.addVillageBiome(Biomes.JUNGLE_EDGE, MiscConfig.enableVillageInJungleEdge);
	BiomeManager.addVillageBiome(Biomes.MESA, MiscConfig.enableVillageInMesa);
	BiomeManager.addVillageBiome(Biomes.EXTREME_HILLS, MiscConfig.enableVillageInExtremeHills);
	BiomeManager.addVillageBiome(Biomes.ICE_PLAINS, MiscConfig.enableVillageInIcePlains);
	
	

  }

  public static void registerBiome(Biome biome, String name, int weight, boolean hasVillages, BiomeType biomeType, Type... types) {
    if (weight > 0) {
      biome.setRegistryName(new ResourceLocation(NovamTerram.modId, name));
      biomeRegister.getRegistry().register(biome);
      for (BiomeDictionary.Type type : types) {
        BiomeDictionary.addTypes(biome, type);
      }
      BIOMES.add(new IBiomeDetails() {
        @Override
        public boolean hasVillages() {
          return hasVillages;
        }

        @Override
        public Biome getBiome() {
          return biome;
        }

        @Override
        public BiomeType getBiomeType() {
          return biomeType;
        }

        @Override
        public BiomeEntry getBiomeEntry() {
          return new BiomeEntry(biome, weight);
        }
      });
//      BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
//      BiomeManager.addStrongholdBiome(biome);
//      BiomeManager.addSpawnBiome(biome);
//      if (hasVillages) {
//        BiomeManager.addVillageBiome(biome, true);
//      }
    }
  }

  public interface IBiomeDetails {

    boolean hasVillages();

    Biome getBiome();

    BiomeType getBiomeType();

    BiomeEntry getBiomeEntry();
  }
	}
