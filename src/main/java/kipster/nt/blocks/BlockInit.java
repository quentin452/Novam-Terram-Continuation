package kipster.nt.blocks;

import kipster.nt.blocks.blocks.*;
import kipster.nt.blocks.blocks.blockflower.BlockFlowerAllium;
import kipster.nt.blocks.blocks.blockflower.BlockFlowerAspalathus;
import kipster.nt.blocks.blocks.blockflower.BlockFlowerPrimevere;
import kipster.nt.blocks.blocks.blockleaves.*;
import kipster.nt.blocks.blocks.blockspalings.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {

	public static final List<Block> BLOCKS = new ArrayList<Block>();

	//LEAVES
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

	//FLOWER
	public static final Block PRIMEVEREFLOWER = new BlockFlowerPrimevere("flower_primevere", Material.PLANTS);
	public static final Block ALLIUMFLOWER = new BlockFlowerAllium("flower_allium", Material.PLANTS);
	public static final Block ASPALATHUSFLOWER = new BlockFlowerAspalathus("flower_aspalathus", Material.PLANTS);

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
