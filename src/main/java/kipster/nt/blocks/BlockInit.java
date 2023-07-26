package kipster.nt.blocks;

import kipster.nt.blocks.blocks.*;
import kipster.nt.blocks.blocks.blockflower.*;
import kipster.nt.blocks.blocks.blockleaves.*;
import kipster.nt.blocks.blocks.blockspalings.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {

	public static final List<Block> BLOCKS = new ArrayList<Block>();

	//LEAVES
	public static final Block OLIVELEAVES = new BlockLeavesOlive("olive_leaves_green", Material.LEAVES);
	public static final Block CONIFERLEAVESYELLOW = new BlockLeavesConiferYellow("conifer_leaves_yellow", Material.LEAVES);
	public static final Block CONIFERLEAVESORANGE = new BlockLeavesConiferOrange("conifer_leaves_orange", Material.LEAVES);
	public static final Block CHERRYLEAVESPURPLE = new BlockLeavesCherryPurple("cherry_leaves_purple", Material.LEAVES);
	public static final Block CHERRYLEAVESWHITE = new BlockLeavesCherryWhite("cherry_leaves_white", Material.LEAVES);
	public static final Block CHERRYLEAVESPINK = new BlockLeavesCherryPink("cherry_leaves_pink", Material.LEAVES);
	public static final Block MAPLELEAVES = new BlockLeavesMaple("maple_leaves", Material.LEAVES);
	public static final Block JACARANDALEAVES= new BlockLeavesJacaranda("jacaranda_leaves", Material.LEAVES);
	public static final Block SPRUCELEAVESRED = new BlockLeavesRedSpruce("spruce_leaves_red", Material.LEAVES);
	public static final Block SPRUCELEAVESBLUE = new BlockLeavesBlueSpruce("spruce_leaves_blue", Material.LEAVES);
	public static final Block OAKLEAVESRED = new BlockLeavesRedOak("oak_leaves_red", Material.LEAVES);
	public static final Block AUTUMNLEAVESBROWN = new BlockLeavesBrownAutumn("brownautumn_leaves", Material.LEAVES);
	public static final Block AUTUMNLEAVESORANGE = new BlockLeavesOrangeAutumn("orangeautumn_leaves", Material.LEAVES);
	public static final Block AUTUMNLEAVESRED = new BlockLeavesRedAutumn("redautumn_leaves", Material.LEAVES);
	public static final Block AUTUMNLEAVESYELLOW = new BlockLeavesYellowAutumn("yellowautumn_leaves", Material.LEAVES);
	public static final Block PAULOWNIALEAVES = new BlockLeavesPaulownia("paulownia_leaves", Material.LEAVES);
	public static final Block ORCHARDLEAVES = new BlockLeavesOrchard("orchard_leaves", Material.LEAVES);
	public static final Block WHITEMYRTLELEAVES = new BlockLeavesWhiteMyrtle("whitemyrtle_leaves", Material.LEAVES);

	//FRUIT/LEGUME
	//# todo : fix cannot be eaten
	//public static final Block OLIVEFRUIT = new BlockOliveFruit("fruit_olive", Material.PLANTS);

	//FLOWER
	public static final Block PRIMEVEREFLOWER = new BlockFlowerPrimevere("flower_primevere", Material.PLANTS);
	public static final Block ALLIUMFLOWER = new BlockFlowerAllium("flower_allium", Material.PLANTS);
	public static final Block ASPALATHUSFLOWER = new BlockFlowerAspalathus("flower_aspalathus", Material.PLANTS);
	public static final Block ABELIATOPFLOWER = new BlockFlowerAbeliaTop("flower_abelia_top", Material.PLANTS);
	public static final Block ABELIOPHYLLUMFLOWER = new BlockFlowerAbeliophyllum("flower_abeliophyllum", Material.PLANTS);
	public static final Block ABROMAFLOWER = new BlockFlowerAbroma("flower_abroma", Material.PLANTS);
	public static final Block ABRONIABOTFLOWER = new BlockFlowerAbroniaBot("flower_abronia_bot", Material.PLANTS);
	public static final Block ABRONIATOPFLOWER = new BlockFlowerAbroniaTop("flower_abronia_top", Material.PLANTS);
	public static final Block ABUTILONFLOWER = new BlockFlowerAbutilon("flower_abutilon", Material.PLANTS);
	public static final Block ACAENAFLOWER = new BlockFlowerAcaena("flower_acaena", Material.PLANTS);
	public static final Block ACALYPHARFLOWER = new BlockFlowerAcalyphaR("flower_acalypha_r", Material.PLANTS);
	public static final Block ACANTHOLIMONFLOWER = new BlockFlowerAcantholimon("flower_acantholimon", Material.PLANTS);
	public static final Block ACANTHUSBOTFLOWER = new BlockFlowerAcanthusBot("flower_acanthus_bot", Material.PLANTS);
	public static final Block ACANTHUSTOPFLOWER = new BlockFlowerAcanthusTop("flower_acanthus_top", Material.PLANTS);
	public static final Block ACHILLEAFLOWER = new BlockFlowerAchillea("flower_achillea", Material.PLANTS);
	public static final Block ACHIMENESFLOWER = new BlockFlowerAchimenes("flower_achimenes", Material.PLANTS);
	public static final Block ACINOSFLOWER = new BlockFlowerAcinos("flower_acinos", Material.PLANTS);
	public static final Block ACIPHYLLAFLOWER = new BlockFlowerAciphylla("flower_aciphylla", Material.PLANTS);
	public static final Block ADENOCARPUSBOTFLOWER = new BlockFlowerAdenocarpusBot("flower_adenocarpus_bot", Material.PLANTS);
	public static final Block ADENOCARPUSTOPFLOWER = new BlockFlowerAdenocarpusTop("flower_adenocarpus_top", Material.PLANTS);
	public static final Block ADENOPHORAFLOWER = new BlockFlowerAdenophora("flower_adenophora", Material.PLANTS);
	public static final Block ADONISFLOWER = new BlockFlowerAdonis("flower_adonis", Material.PLANTS);
	public static final Block AECHMEAFLOWER = new BlockFlowerAechmea("flower_aechmea", Material.PLANTS);
	public static final Block AESCHYNANTHUSFLOWER = new BlockFlowerAeschynanthus("flower_aeschynanthus", Material.PLANTS);
	public static final Block AETHIONEMAFLOWER = new BlockFlowerAethionema("flower_aethionema", Material.PLANTS);
	public static final Block AGAPANTHUSFLOWER = new BlockFlowerAgapantus("flower_agapanthus", Material.PLANTS);
	public static final Block AGERATUMFLOWER = new BlockFlowerAgeratum("flower_ageratum", Material.PLANTS);
	public static final Block AGONISBOTFLOWER = new BlockFlowerAgonisBot("flower_agonis_bot", Material.PLANTS);
	public static final Block AGONISTOPFLOWER = new BlockFlowerAgonisTop("flower_agonis_top", Material.PLANTS);
	public static final Block AGROSTEMMAFLOWER = new BlockFlowerAgrostemma("flower_agrostemma", Material.PLANTS);
	public static final Block AIRAFLOWER = new BlockFlowerAira("flower_aira", Material.PLANTS);
	public static final Block AJUGAFLOWER = new BlockFlowerAjuga("flower_ajuga", Material.PLANTS);
	public static final Block ALBUCAFLOWER = new BlockFlowerAlbuca("flower_albuca", Material.PLANTS);
	public static final Block ALCEAFLOWER = new BlockFlowerAlcea("flower_alcea", Material.PLANTS);
	public static final Block ALKANNAFLOWER = new BlockFlowerAlkanna("flower_alkanna", Material.PLANTS);
	public static final Block ALLIARIAFLOWER = new BlockFlowerAlliaria("flower_alliaria", Material.PLANTS);
	public static final Block ALOEFLOWER = new BlockFlowerAloe("flower_aloe", Material.PLANTS);
	public static final Block ALONSOABOTFLOWER = new BlockFlowerAlonsoaBot("flower_alonsoa_bot", Material.PLANTS);
	public static final Block ALONSOATOPFLOWER = new BlockFlowerAlonsoaTop("flower_alonsoa_top", Material.PLANTS);
	public static final Block ALOPECURUSFLOWER = new BlockFlowerAlopecurus("flower_alopecurus", Material.PLANTS);
	public static final Block ALSOBIAFLOWER = new BlockFlowerAlsobia("flower_alsobia", Material.PLANTS);
	public static final Block ALSTROEMERIAFLOWER = new BlockFlowerAlstroemeria("flower_alstroemeria", Material.PLANTS);
	public static final Block ALYOGYNEFLOWER = new BlockFlowerAlyogyne("flower_alyogyne", Material.PLANTS);
	public static final Block AMARYLLISFLOWER = new BlockFlowerAmaryllis("flower_amaryllis", Material.PLANTS);
	public static final Block AMBROSIAFLOWER = new BlockFlowerAmbrosia("flower_ambrosia", Material.PLANTS);
	public static final Block AMESIELLAFLOWER = new BlockFlowerAmesiella("flower_amesiella", Material.PLANTS);
	public static final Block AMMOBIUMFLOWER = new BlockFlowerAmmobium("flower_ammobium", Material.PLANTS);
	public static final Block AMPHIPAPPUSFLOWER = new BlockFlowerAmphipappus("flower_amphipappus", Material.PLANTS);
	public static final Block ANACYCLUSFLOWER = new BlockFlowerAnacyclus("flower_anacyclus", Material.PLANTS);
	public static final Block ANAGALLISFLOWER = new BlockFlowerAnagallis("flower_anagallis", Material.PLANTS);
	public static final Block ANCHUSAFLOWER = new BlockFlowerAnchusa("flower_anchusa", Material.PLANTS);
	public static final Block APACHEPLUMEFLOWER = new BlockFlowerApachePlume("flower_apache_plume", Material.PLANTS);
	public static final Block ARGOCOFFEEOPSISFLOWER = new BlockFlowerArgocoffeeopsis("flower_argocoffeeopsis", Material.PLANTS);
	public static final Block ARIZONAPOPPYFLOWER = new BlockFlowerArizonaPoppy("flower_arizona_poppy", Material.PLANTS);
	public static final Block ASCLEPIASFLOWER = new BlockFlowerAsclepias("flower_asclepias", Material.PLANTS);
	public static final Block ASCLEPIASTFLOWER = new BlockFlowerAsclepiasT("flower_asclepias_t", Material.PLANTS);
	public static final Block ASIANMUSTARDFLOWER = new BlockFlowerAsianMustard("flower_asian_mustard", Material.PLANTS);
	public static final Block BARBAREAFLOWER = new BlockFlowerBarbarea("flower_barbarea", Material.PLANTS);
	public static final Block BARRENSTRAWBERRYFLOWER = new BlockFlowerBarrenStrawberry("flower_barren_strawberry", Material.PLANTS);
	public static final Block BEARDTONGUEFLOWER = new BlockFlowerBeardtongue("flower_beardtongue", Material.PLANTS);
	public static final Block BEGONIAFLOWER = new BlockFlowerBegonia("flower_begonia", Material.PLANTS);
	public static final Block BIGELOWSMONKEYSFLOWER = new BlockFlowerBigelowsMonkeyFlower("flower_bigelows_monkey_flower", Material.PLANTS);
	public static final Block BLACKTRACKPHACELIAFLOWER = new BlockFlowerBlacktackPhacelia("flower_blacktack_phacelia", Material.PLANTS);
	public static final Block BLAZINGSTARFLOWER = new BlockFlowerBlazingStar("flower_blazing_star", Material.PLANTS);
	public static final Block BLUEFLAXFLOWER = new BlockFlowerBlueflax("flower_blue_flax", Material.PLANTS);
	public static final Block BRACHYSTELMAFLOWER = new BlockFlowerBrachystelma("flower_brachystelma", Material.PLANTS);
	public static final Block BRISTLYFIDDLENECKFLOWER = new BlockFlowerBristyFiddleneck("flower_bristly_fiddleneck", Material.PLANTS);
	public static final Block BROWNEYESFLOWER = new BlockFlowerBrownEyes("flower_brown_eyes", Material.PLANTS);
	public static final Block BUCKBRUSHFLOWER = new BlockFlowerBuckBrush("flower_buckbrush", Material.PLANTS);
	public static final Block BULBILBUGLELILYBOTFLOWER = new BlockFlowerBulbilBuglelilyBot("flower_bulbil_buglelily_bot", Material.PLANTS);
	public static final Block BULBILBUGLELILYTOPFLOWER = new BlockFlowerBulbilBuglelilyTop("flower_bulbil_buglelily_top", Material.PLANTS);
	public static final Block BRITTLEBUSHFLOWER = new BlockflowerButtonBrittlebush("flower_button_brittlebush", Material.PLANTS);
	public static final Block CAMBRIDGEBLUEFLOWER = new BlockFlowerCambridgeBlue("flower_cambridge_blue", Material.PLANTS);
	public static final Block CANAIGREFLOWER = new BlockFlowerCanaigre("flower_canaigre", Material.PLANTS);
	public static final Block CARDAMINEFLOWER = new BlockFlowerCardamine("flower_cardamine", Material.PLANTS);
	public static final Block CEPHALOPHYLLUMFLOWER = new BlockFlowerCephalophyllum("flower_cephalophyllum", Material.PLANTS);
	public static final Block CHOCOLATEDROPSFLOWER = new BlockFlowerChocolateDrops("flower_chocolate_drops", Material.PLANTS);
	public static final Block CHRYSANTHEMUMFLOWER = new BlockFlowerChrysanthemum("flower_chrysanthemum", Material.PLANTS);
	public static final Block CHUPAROSAFLOWER = new BlockFlowerChuparosa("flower_chuparosa", Material.PLANTS);
	public static final Block CLOVEFLOWER = new BlockFlowerClove("flower_clove", Material.PLANTS);
	public static final Block CLUSTEREDBROOMRAPEFLOWER = new BlockFlowerClusteredBroomrape("flower_clustered_broomrape", Material.PLANTS);
	public static final Block CORYDALISFLOWER = new BlockFlowerCorydalis("flower_corydalis", Material.PLANTS);
	public static final Block COULTERSJEWELFLOWERFLOWER = new BlockFlowerCoultersJewelflower("flower_coulters_jewelflower", Material.PLANTS);
	public static final Block CRASSULAFLOWER = new BlockFlowerCrassula("flower_crassula", Material.PLANTS);
	public static final Block DISAFLOWER = new BlockFlowerDisa("flower_disa", Material.PLANTS);
	public static final Block DISTANTSCORPIONWEEDFLOWER = new BlockFlowerDistantScorpionweed("flower_distant_scorpionweed", Material.PLANTS);
	public static final Block EPIPOGIUMFLOWER = new BlockFlowerEpipogium("flower_epipogium", Material.PLANTS);
	public static final Block FABACEAEFLOWER = new BlockFlowerFabaceae("flower_fabaceae", Material.PLANTS);
	public static final Block FAIRYLILYFLOWER = new BlockFlowerFairyLily("flower_fairy_lily", Material.PLANTS);
	public static final Block ABELIABOTFLOWER = new BlockFlowerFlowerAbeliaBot("flower_flower_abelia_bot", Material.PLANTS);
	public static final Block FRUITBOTFLOWER = new BlockFlowerFruitBot("flower_fruit_bot", Material.PLANTS);
	public static final Block FRUITTOPFLOWER = new BlockFlowerFruitTop("flower_fruit_top", Material.PLANTS);
	public static final Block GALANTHUSFLOWER = new BlockFlowerGalanthus("flower_galanthus", Material.PLANTS);
	public static final Block HELIOTROPIUMFLOWER = new BlockFlowerHeliotropium("flower_heliotropium", Material.PLANTS);
	public static final Block HELLEBOREFLOWER = new BlockFlowerHellebore("flower_hellebore", Material.PLANTS);
	public static final Block HESPERISFLOWER = new BlockFlowerHesperis("flower_hesperis", Material.PLANTS);
	public static final Block IMPATIENSFLOWER = new BlockFlowerImpatiens("flower_impatiens", Material.PLANTS);
	public static final Block ISOLEPISFLOWER = new BlockFlowerIsolepis("flower_isolepis", Material.PLANTS);
	public static final Block NARTHECIUMFLOWER = new BlockFlowerNarthecium("flower_narthecium", Material.PLANTS);
	public static final Block NEGLECTEDSCORPIONWEEDFLOWER = new BlockFlowerNeglectedScorpionweed("flower_neglected_scorpionweed", Material.PLANTS);
	public static final Block NEMESIAFLOWER = new BlockFlowerNemesia("flower_nemesia", Material.PLANTS);
	public static final Block NONEBOTFLOWER = new BlockFlowerNoneBot("flower_none_bot", Material.PLANTS);
	public static final Block NONETOPFLOWER = new BlockFlowerNoneTop("flower_none_top", Material.PLANTS);
	public static final Block PUNKCORYDALISFLOWER = new BlockFlowerPinkCorydalis("flower_pink_corydalis", Material.PLANTS);
	public static final Block PLUCHEAGLUTONISAFLOWER = new BlockFlowerPlucheaGlutinosa("flower_pluchea_glutinosa", Material.PLANTS);
	public static final Block PSORALEACATARACTAFLOWER = new BlockFlowerPsoraleaCataracta("flower_psoralea_cataracta", Material.PLANTS);
	public static final Block PSORALEAMACROPHYLLAFLOWER = new BlockFlowerPsoraleaMacrophylla("flower_psoralea_macrophylla", Material.PLANTS);
	public static final Block PUERARIAFLOWER = new BlockFlowerPueraria("flower_pueraria", Material.PLANTS);
	public static final Block RHANTIUMFLOWER = new BlockFlowerRhanterium("flower_rhanterium", Material.PLANTS);
	public static final Block RORIPPAFLOWER = new BlockFlowerRorippa("flower_rorippa", Material.PLANTS);
	public static final Block ROYALBLUEBELLFLOWER = new BlockFlowerRoyalBluebell("flower_royal_bluebell", Material.PLANTS);
	public static final Block RUDBECKIAHFLOWER = new BlockFlowerRudbeckiaH("flower_rudbeckia_h", Material.PLANTS);
	public static final Block SAGEBRUSHFLOWER = new BlockFlowerSagebrush("flower_sagebrush", Material.PLANTS);
	public static final Block SANANGELOYUCCAFLOWER = new BlockFlowerSanAngeloYucca("flower_san_angelo_yucca", Material.PLANTS);
	public static final Block SANDBOGDEATHCAMASFLOWER = new BlockFlowerSandbogDeathCamas("flower_sandbog_death_camas", Material.PLANTS);
	public static final Block SNOWYRIVERWESTRINGIAFLOWER = new BlockFlowerSnowyRiverWestringia("flower_snowy_river_westringia", Material.PLANTS);
	public static final Block STREBLORRHIZAFLOWER = new BlockFlowerStreblorrhiza("flower_streblorrhiza", Material.PLANTS);
	public static final Block SUMASTRAFLOWER = new BlockFlowerSumastra("flower_sumatra", Material.PLANTS);
	public static final Block THISMIAFLOWER = new BlockFlowerThismia("flower_thismia", Material.PLANTS);
	public static final Block TRILLIUMFLOWER = new BlockFlowerTrillium("flower_trillium", Material.PLANTS);
	public static final Block VERACTRUMFLOWER = new BlockFlowerVeractrum("flower_veratrum", Material.PLANTS);
	public static final Block VERONICAFLOWER = new BlockFlowerVeronica("flower_veronica", Material.PLANTS);
	public static final Block VIOLAFLOWER = new BlockFlowerViola("flower_viola", Material.PLANTS);
	public static final Block WELDENIACANDIDAFLOWER = new BlockFlowerWeldeniaCandida("flower_weldenia_candida", Material.PLANTS);
	public static final Block WILDRICEFLOWER = new BlockFlowerWildRice("flower_wild_rice", Material.PLANTS);
	public static final Block YELLOWROOTFLOWER = new BlockFlowerYellowroot("flower_yellowroot", Material.PLANTS);
	public static final Block ZEBONIAFLOWER = new BlockFlowerZenobia("flower_zenobia", Material.PLANTS);
	public static final Block OLIVEFLOWER = new BlockFlowerOlive("flower_olive", Material.PLANTS);

	//SAPLING
	public static final Block CONIFERSAPLINGYELLOW = new BlockSaplingConiferYellow("conifer_sapling_yellow", Material.LEAVES);
	public static final Block CONIFERSAPLINGORANGE = new BlockSaplingConiferOrange("conifer_sapling_orange", Material.LEAVES);
	public static final Block CHERRYSAPLINGPURPLE = new BlockSaplingCherryPurple("cherry_sapling_purple", Material.LEAVES);
	public static final Block CHERRYSAPLINGWHITE = new BlockSaplingCherryWhite("cherry_sapling_white", Material.LEAVES);
	public static final Block CHERRYSAPLINGPINK = new BlockSaplingCherryPink("cherry_sapling_pink", Material.LEAVES);
	public static final Block MAPLESAPLING = new BlockSaplingMaple("maple_sapling", Material.LEAVES);
	public static final Block JACARANDASAPLING = new BlockSaplingJacaranda("jacaranda_sapling", Material.LEAVES);
	public static final Block SPRUCESAPLINGRED = new BlockSaplingRedSpruce("spruce_sapling_red", Material.LEAVES);
	public static final Block SPRUCESAPLINGBLUE = new BlockSaplingBlueSpruce("spruce_sapling_blue", Material.LEAVES);
	public static final Block OAKSAPLINGRED = new BlockSaplingRedOak("oak_sapling_red", Material.LEAVES);
	public static final Block AUTUMNSAPLINGBROWN = new BlockSaplingBrownAutumn("brownautumn_sapling", Material.LEAVES);
	public static final Block AUTUMNSAPLINGORANGE = new BlockSaplingOrangeAutumn("orangeautumn_sapling", Material.LEAVES);
	public static final Block AUTUMNSAPLINGRED = new BlockSaplingRedAutumn("redautumn_sapling", Material.LEAVES);
	public static final Block AUTUMNSAPLINGYELLOW = new BlockSaplingYellowAutumn("yellowautumn_sapling", Material.LEAVES);
	public static final Block PAULOWNIASAPLING = new BlockSaplingPaulownia("paulownia_sapling", Material.LEAVES);
	public static final Block ORCHARDSAPLING = new BlockSaplingOrchard("orchard_sapling", Material.LEAVES);
	public static final Block WHITEMYRTLESAPLING = new BlockSaplingWhiteMyrtle("whitemyrtle_sapling", Material.LEAVES);


	//CREATIVEONLYSAPLING
	public static final Block SHRUBSAPLINGACACIA = new BlockSaplingShrubAcacia("shrub_sapling_acacia", Material.LEAVES);
	public static final Block SHRUBSAPLINGBIRCH = new BlockSaplingShrubBirch("shrub_sapling_birch", Material.LEAVES);
	public static final Block SHRUBSAPLINGBLUESPRUCE = new BlockSaplingShrubBlueSpruce("shrub_sapling_bluespruce", Material.LEAVES);
	public static final Block SHRUBSAPLINGCHERRYPINK = new BlockSaplingShrubCherryPink("shrub_sapling_cherrypink", Material.LEAVES);
	public static final Block SHRUBSAPLINGCHERRYPURPLE = new BlockSaplingShrubCherryPurple("shrub_sapling_cherrypurple", Material.LEAVES);
	public static final Block SHRUBSAPLINGCHERRYWHITE = new BlockSaplingShrubCherryWhite("shrub_sapling_cherrywhite", Material.LEAVES);
	public static final Block SHRUBSAPLINGCONIFERORANGE = new BlockSaplingShrubConiferOrange("shrub_sapling_coniferorange", Material.LEAVES);
	public static final Block SHRUBSAPLINGCONIFERYELLOW = new BlockSaplingShrubConiferYellow("shrub_sapling_coniferyellow", Material.LEAVES);
	public static final Block SHRUBSAPLINGDARKOAK = new BlockSaplingShrubDarkOak("shrub_sapling_darkoak", Material.LEAVES);
	public static final Block SHRUBSAPLINGJACARANDA = new BlockSaplingShrubJacaranda("shrub_sapling_jacaranda", Material.LEAVES);
	public static final Block SHRUBSAPLINGJUNGLE = new BlockSaplingShrubJungle("shrub_sapling_jungle", Material.LEAVES);
	public static final Block SHRUBSAPLINGMAPLE = new BlockSaplingShrubMaple("shrub_sapling_maple", Material.LEAVES);
	public static final Block SHRUBSAPLINGOAK = new BlockSaplingShrubOak("shrub_sapling_oak", Material.LEAVES);
	public static final Block SHRUBSAPLINGREDOAK = new BlockSaplingShrubRedOak("shrub_sapling_redoak", Material.LEAVES);
	public static final Block SHRUBSAPLINGREDSPRUCE = new BlockSaplingShrubRedSpruce("shrub_sapling_redspruce", Material.LEAVES);
	public static final Block SHRUBSAPLINGSPRUCE = new BlockSaplingShrubSpruce("shrub_sapling_spruce", Material.LEAVES);
	public static final Block SAPLINGPOPlAR = new BlockSaplingPoplar("sapling_poplar", Material.LEAVES);
	public static final Block SAPLINGTALLSPRUCE = new BlockSaplingTallSpruce("sapling_tallspruce", Material.LEAVES);

	//SANDS
	public static final Block WHITESAND = new BlockSands("white_sand", Material.SAND);
	public static final Block WHITESANDSTONE = new BlockSandstone("white_sandstone", Material.ROCK);
	public static final Block WHITESANDSTONESMOOTH = new BlockSandstoneSmooth("white_sandstone_smooth", Material.ROCK);
	public static final Block WHITESANDSTONECARVED = new BlockSandstoneCarved("white_sandstone_carved", Material.ROCK);
	public static final Block WHITESANDSTONEFACE = new BlockSandstoneFace("white_sandstone_face", Material.ROCK);
	public static final Block WHITESANDSTONEGLYPH = new BlockSandstoneGlyph("white_sandstone_glyph", Material.ROCK);
	public static final Block COLDSAND = new BlockSands("cold_sand", Material.SAND);
	public static final Block COLDSANDSTONE = new BlockSandstone("cold_sandstone", Material.ROCK);
	public static final Block COLDSANDSTONESMOOTH = new BlockSandstoneSmooth("cold_sandstone_smooth", Material.ROCK);
	public static final Block COLDSANDSTONECARVED = new BlockSandstoneCarved("cold_sandstone_carved", Material.ROCK);
	public static final Block COLDSANDSTONEFACE = new BlockSandstoneFace("cold_sandstone_face", Material.ROCK);
	public static final Block COLDSANDSTONEGLYPH = new BlockSandstoneGlyph("cold_sandstone_glyph", Material.ROCK);
	public static final Block BLACKSAND = new BlockSands("black_sand", Material.SAND);
	public static final Block BLACKSANDSTONE= new BlockSandstone("black_sandstone", Material.ROCK);
	public static final Block BLACKSANDSTONESMOOTH = new BlockSandstoneSmooth("black_sandstone_smooth", Material.ROCK);
	public static final Block BLACKSANDSTONECARVED = new BlockSandstoneCarved("black_sandstone_carved", Material.ROCK);
	public static final Block BLACKSANDSTONEFACE = new BlockSandstoneFace("black_sandstone_face", Material.ROCK);
	public static final Block BLACKSANDSTONEGLYPH = new BlockSandstoneGlyph("black_sandstone_glyph", Material.ROCK);
	public static final Block OLIVINESAND = new BlockSands("olivine_sand", Material.SAND);
	public static final Block OLIVINESANDSTONE= new BlockSandstone("olivine_sandstone", Material.ROCK);
	public static final Block OLIVINESANDSTONESMOOTH = new BlockSandstoneSmooth("olivine_sandstone_smooth", Material.ROCK);
	public static final Block OLIVINESANDSTONECARVED = new BlockSandstoneCarved("olivine_sandstone_carved", Material.ROCK);
	public static final Block OLIVINESANDSTONEFACE = new BlockSandstoneFace("olivine_sandstone_face", Material.ROCK);
	public static final Block OLIVINESANDSTONEGLYPH = new BlockSandstoneGlyph("olivine_sandstone_glyph", Material.ROCK);
	public static final Block PINKSAND = new BlockSands("pink_sand", Material.SAND);
	public static final Block PINKSANDSTONE= new BlockSandstone("pink_sandstone", Material.ROCK);
	public static final Block PINKSANDSTONESMOOTH = new BlockSandstoneSmooth("pink_sandstone_smooth", Material.ROCK);
	public static final Block PINKSANDSTONECARVED = new BlockSandstoneCarved("pink_sandstone_carved", Material.ROCK);
	public static final Block PINKSANDSTONEFACE = new BlockSandstoneFace("pink_sandstone_face", Material.ROCK);
	public static final Block PINKSANDSTONEGLYPH = new BlockSandstoneGlyph("pink_sandstone_glyph", Material.ROCK);
	public static final Block PURPLESAND = new BlockSands("purple_sand", Material.SAND);
	public static final Block PURPLESANDSTONE= new BlockSandstone("purple_sandstone", Material.ROCK);
	public static final Block PURPLESANDSTONESMOOTH = new BlockSandstoneSmooth("purple_sandstone_smooth", Material.ROCK);
	public static final Block PURPLESANDSTONECARVED = new BlockSandstoneCarved("purple_sandstone_carved", Material.ROCK);
	public static final Block PURPLESANDSTONEFACE = new BlockSandstoneFace("purple_sandstone_face", Material.ROCK);
	public static final Block PURPLESANDSTONEGLYPH = new BlockSandstoneGlyph("purple_sandstone_glyph", Material.ROCK);
	public static final Block CONTINENTALSAND = new BlockSands("continental_sand", Material.SAND);
	public static final Block CONTINENTALSANDSTONE= new BlockSandstone("continental_sandstone", Material.ROCK);
	public static final Block CONTINENTALSANDSTONESMOOTH = new BlockSandstoneSmooth("continental_sandstone_smooth", Material.ROCK);
	public static final Block CONTINENTALSANDSTONECARVED = new BlockSandstoneCarved("continental_sandstone_carved", Material.ROCK);
	public static final Block CONTINENTALSANDSTONEFACE = new BlockSandstoneFace("continental_sandstone_face", Material.ROCK);
	public static final Block CONTINENTALSANDSTONEGLYPH = new BlockSandstoneGlyph("continental_sandstone_glyph", Material.ROCK);
	public static final Block IRONSAND = new BlockSands("iron_sand", Material.SAND);
	public static final Block IRONSANDSTONE= new BlockSandstone("iron_sandstone", Material.ROCK);
	public static final Block IRONSANDSTONESMOOTH = new BlockSandstoneSmooth("iron_sandstone_smooth", Material.ROCK);
	public static final Block IRONSANDSTONECARVED = new BlockSandstoneCarved("iron_sandstone_carved", Material.ROCK);
	public static final Block IRONSANDSTONEFACE = new BlockSandstoneFace("iron_sandstone_face", Material.ROCK);
	public static final Block IRONSANDSTONEGLYPH = new BlockSandstoneGlyph("iron_sandstone_glyph", Material.ROCK);
	public static final Block ORANGESAND = new BlockSands("orange_sand", Material.SAND);
	public static final Block ORANGESANDSTONE= new BlockSandstone("orange_sandstone", Material.ROCK);
	public static final Block ORANGESANDSTONESMOOTH = new BlockSandstoneSmooth("orange_sandstone_smooth", Material.ROCK);
	public static final Block ORANGESANDSTONECARVED = new BlockSandstoneCarved("orange_sandstone_carved", Material.ROCK);
	public static final Block ORANGESANDSTONEFACE = new BlockSandstoneFace("orange_sandstone_face", Material.ROCK);
	public static final Block ORANGESANDSTONEGLYPH = new BlockSandstoneGlyph("orange_sandstone_glyph", Material.ROCK);

	//MISC
	public static final Block REDPODZOL = new BlockPodzols("red_podzol", Material.GROUND);
	public static final Block BLUEPODZOL = new BlockPodzols("blue_podzol", Material.GROUND);

}
