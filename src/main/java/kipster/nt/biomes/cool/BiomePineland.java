package kipster.nt.biomes.cool;

import kipster.nt.blocks.BlockInit;
import kipster.nt.world.gen.flowers.WorldGenAethionemaFlower;
import kipster.nt.world.gen.flowers.WorldGenAgapantusFlower;
import kipster.nt.world.gen.flowers.WorldGenAmbrosiaFlower;
import kipster.nt.world.gen.trees.WorldGenTreeShrubSpruce;
import kipster.nt.world.gen.trees.WorldGenTreeTallSpruce;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomePineland extends Biome 
{
	protected static final WorldGenerator AETHIONEMAFLOWER= new WorldGenAethionemaFlower(BlockInit.AETHIONEMAFLOWER.getDefaultState());
	protected static final WorldGenerator AMBROSIAFLOWER = new WorldGenAmbrosiaFlower(BlockInit.AMBROSIAFLOWER.getDefaultState());
	protected static final WorldGenerator WorldGenAgapantusFlower = new WorldGenAgapantusFlower(BlockInit.AGAPANTHUSFLOWER.getDefaultState());

	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenAbstractTree SHRUB_SPRUCE = new WorldGenTreeShrubSpruce();
	   private final WorldGenTreeTallSpruce spruceGenerator = new WorldGenTreeTallSpruce(true);
	
		 public BiomePineland(BiomeProperties properties)
		  	{	
		  		super(properties);
		  	
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.treesPerChunk = 3;
		this.decorator.flowersPerChunk = 4;
	    this.decorator.grassPerChunk = 8;
	    this.decorator.gravelPatchesPerChunk = 2;
	    this.decorator.generateFalls = true;
	    
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 5, 2, 6));
		
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int spruceWeight = 1;
		int spruceShrubWeight = 1;

		int totalWeight = spruceWeight + spruceShrubWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(this.spruceGenerator);
		treeList.add(SHRUB_SPRUCE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);

	}

	private void generateFlowers(World worldIn, Random rand, BlockPos pos, int flowersPerChunk, WorldGenerator flowerGenerator) {
		for (int i = 0; i < flowersPerChunk; ++i) {

			// Generate a random offset in x and z directions
			int offsetX = rand.nextInt(16) + 8;
			int offsetZ = rand.nextInt(16) + 8;

			boolean success = false;

			for (int j = 0; j < 3 + rand.nextInt(3); j++) {
				BlockPos blockpos = pos.add(
						offsetX,                  // Use random x offset
						rand.nextInt(10) + 60,
						offsetZ);                 // Use random z offset

				if (flowerGenerator.generate(worldIn, rand, blockpos)) {
					success = true;
				}
			}

			if (success) {
				break; // Move on to the next group of flowers
			}
		}
	}
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
	{
	    return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
	}
	
    
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		int flowersPerChunk = 1;

		generateFlowers(worldIn, rand, pos, flowersPerChunk, AETHIONEMAFLOWER);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, AMBROSIAFLOWER);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, WorldGenAgapantusFlower);

		   net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
	       WorldGenerator diamonds = new DiamondGenerator();
	       if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, diamonds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND))
	    	   diamonds.generate(worldIn, rand, pos);

	    super.decorate(worldIn, rand, pos);
	        }
	
	 public static class DiamondGenerator extends WorldGenerator
	    {
	        @Override
	        public boolean generate(World worldIn, Random rand, BlockPos pos)
	        {
	           int count = 10 + rand.nextInt(6);
	            for (int i = 0; i < count; i++)
	            {
	                int offset = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 8 : 0; // MC-114332
	                BlockPos blockpos = pos.add(rand.nextInt(16) + offset, rand.nextInt(28) + 2, rand.nextInt(16) + offset);

	                net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
	                if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, net.minecraft.block.state.pattern.BlockMatcher.forBlock(Blocks.STONE)))
	                {
	                    worldIn.setBlockState(blockpos, Blocks.DIAMOND_ORE.getDefaultState(), 16 | 2);
	                }
	            }
	            return true;
        }
    }
	
	 @Override
	 public int getModdedBiomeGrassColor(int original) {
		    return super.getModdedBiomeGrassColor(0x7CB660);
		}
	 
	 @Override
	 public int getModdedBiomeFoliageColor(int original) {
		    return super.getModdedBiomeFoliageColor(0x74BA52);
		    
	}
}