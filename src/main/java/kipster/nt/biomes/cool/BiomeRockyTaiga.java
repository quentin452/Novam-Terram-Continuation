package kipster.nt.biomes.cool;

import kipster.nt.blocks.BlockInit;
import kipster.nt.config.MiscConfig;
import kipster.nt.world.gen.WorldGenLine;
import kipster.nt.world.gen.WorldGenPatches;
import kipster.nt.world.gen.flowers.WorldGenBrachystelmaFlower;
import kipster.nt.world.gen.flowers.WorldGenBuckBrushFlower;
import kipster.nt.world.gen.flowers.WorldGenClusteredBroomrapeFlower;
import kipster.nt.world.gen.trees.WorldGenTreeRedSpruce1;
import kipster.nt.world.gen.trees.WorldGenTreeRedSpruce2;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeRockyTaiga extends Biome 
{	
	protected static final WorldGenPatches STONE_PATCHES = new WorldGenPatches(Blocks.STONE.getDefaultState(), 7);
	protected static final WorldGenBlockBlob STONE_BOULDER_FEATURE = new WorldGenBlockBlob(Blocks.STONE, 1);
	protected static final WorldGenLine STONE = new WorldGenLine(Blocks.STONE, 1);
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenTreeRedSpruce2 RED_SPRUCE = new WorldGenTreeRedSpruce2(false);
	private final WorldGenTreeRedSpruce1 spruceGenerator = new WorldGenTreeRedSpruce1();
	 
   public BiomeRockyTaiga(BiomeProperties properties)
  	{	
  		super(properties);
  	
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.STONE.getDefaultState();
		
       
       this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 8, 4, 4));
       this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
       this.decorator.generateFalls = true;
       this.decorator.treesPerChunk = 9;
       this.decorator.flowersPerChunk = 2;
       this.decorator.grassPerChunk = 5;

	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int spruceWeight = 1;
		int redSpruceWeight = 3;

		int totalWeight = spruceWeight + redSpruceWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(this.spruceGenerator);
		treeList.add(RED_SPRUCE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);

	}

	public WorldGenerator getRandomWorldGenForGrass(Random rand)
   {
       return rand.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
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
	protected static final WorldGenClusteredBroomrapeFlower CLUSTEREDBROOMRAPEFLOWER= new WorldGenClusteredBroomrapeFlower(BlockInit.CLUSTEREDBROOMRAPEFLOWER.getDefaultState());
	protected static final WorldGenBrachystelmaFlower BRACHYSTELMAFLOWER = new WorldGenBrachystelmaFlower(BlockInit.BRACHYSTELMAFLOWER.getDefaultState());
	protected static final WorldGenBuckBrushFlower BUCKBRUSHFLOWER = new WorldGenBuckBrushFlower(BlockInit.BUCKBRUSHFLOWER.getDefaultState());

	public void decorate(World worldIn, Random rand, BlockPos pos)
   {

	   int flowersPerChunk = 7;

	   generateFlowers(worldIn, rand, pos, flowersPerChunk, CLUSTEREDBROOMRAPEFLOWER);
	   generateFlowers(worldIn, rand, pos, flowersPerChunk, BRACHYSTELMAFLOWER);
	   generateFlowers(worldIn, rand, pos, flowersPerChunk, BUCKBRUSHFLOWER);


	   net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
       WorldGenerator diamonds = new DiamondGenerator();
       if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, diamonds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND))
    	   diamonds.generate(worldIn, rand, pos);
       
       int stonepatchChance = rand.nextInt(4);
		if (stonepatchChance == 0) {
			int k6 = rand.nextInt(16) + 8;
			int l = rand.nextInt(16) + 8;
			BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
			STONE_PATCHES.generate(worldIn, rand, blockpos);
		}

       if (!MiscConfig.disableBouldersInRockyTaiga && net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.ROCK)) {
	            int genChance = rand.nextInt(3);
	            if (genChance == 0) {
	                int k6 = rand.nextInt(16) + 8;
	                int l = rand.nextInt(16) + 8;
	                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
	                STONE_BOULDER_FEATURE.generate(worldIn, rand, blockpos);
	            }
	            if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.ROCK)) {
		            int genChance1 = rand.nextInt(3);
		            if (genChance1 == 0) {
		                int k6 = rand.nextInt(16) + 8;
		                int l = rand.nextInt(16) + 8;
		                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
		                STONE.generate(worldIn, rand, blockpos);
		            }
	            }
       }
       super.decorate(worldIn, rand, pos);
       }
   
   
   @Override
  	public int getModdedBiomeGrassColor(int original) {
  	    return super.getModdedBiomeGrassColor(0x6EA93C);
  	}

  	@Override
  	public int getModdedBiomeFoliageColor(int original) {
  	    return super.getModdedBiomeFoliageColor(0x639F2F);
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
}