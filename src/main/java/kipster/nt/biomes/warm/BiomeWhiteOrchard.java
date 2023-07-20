package kipster.nt.biomes.warm;

import kipster.nt.world.gen.trees.WorldGenTreeBigOrchard;
import kipster.nt.world.gen.trees.WorldGenTreeWhiteMyrtle;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeWhiteOrchard extends Biome 
{
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenTreeBigOrchard TREE_ORCHARD = new WorldGenTreeBigOrchard(false);
	protected static final WorldGenTreeWhiteMyrtle SHRUB_WHITEMYRTLE = new WorldGenTreeWhiteMyrtle();
	protected static final WorldGenBigTree OAK_TREE = new WorldGenBigTree(false);
	
	public BiomeWhiteOrchard(BiomeProperties properties)
	{	
		super(properties);
	
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.treesPerChunk = 1;
        this.decorator.flowersPerChunk = 5;
        this.decorator.grassPerChunk = 30;
	    this.decorator.generateFalls = true;
	    
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 5, 2, 6));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityDonkey.class, 1, 1, 3));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 5, 2, 6));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCow.class, 8, 4, 4));
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int treeOrchardWeight = 1;
		int oakWeight = 1;
		int shrubWeight = 1;

		int totalWeight = treeOrchardWeight + oakWeight + shrubWeight;
		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(TREE_ORCHARD);
		treeList.add(OAK_TREE);
		treeList.add(SHRUB_WHITEMYRTLE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);
	}
	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    
		{
        return BlockFlower.EnumFlowerType.HOUSTONIA;
    }


	public void decorate(World worldIn, Random rand, BlockPos pos) {
		// Add specific decorations for this biome
		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
		WorldGenerator emeralds = new EmeraldGenerator();
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
			emeralds.generate(worldIn, rand, pos);
		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS)) {
			for (int i = 0; i < 7; ++i) {
				int j = rand.nextInt(8) + 8;
				int k = rand.nextInt(8) + 8;
				int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
				DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
			}
		}
		super.decorate(worldIn, rand, pos);
	}

	 @Override
	    public void addDefaultFlowers()
	    {
	        addFlower(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), BlockFlower.EnumFlowerType.HOUSTONIA), 20);
	    }
	    

	 public static class EmeraldGenerator extends WorldGenerator
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
	                    worldIn.setBlockState(blockpos, Blocks.EMERALD_ORE.getDefaultState(), 16 | 2);
	                }
	                net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
	            }
	            return true;
	        }
	    }
	 
	 @Override
	 public int getModdedBiomeGrassColor(int original) {
		    return super.getModdedBiomeGrassColor(0xA5D246);
		}
	 
	 @Override
	 public int getModdedBiomeFoliageColor(int original) {
		    return super.getModdedBiomeFoliageColor(0xA0CE3E);
		    
	}
}