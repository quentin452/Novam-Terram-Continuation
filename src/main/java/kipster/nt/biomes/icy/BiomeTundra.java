package kipster.nt.biomes.icy;

import kipster.nt.world.gen.WorldGenPatches;
import kipster.nt.world.gen.trees.WorldGenTreeShrubSpruce;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
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

public class BiomeTundra extends Biome 
{
	
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenPatches ICE_PATCHES = new WorldGenPatches(Blocks.ICE.getDefaultState(), 5);
	protected static final WorldGenPatches GRAVEL_PATCHES = new WorldGenPatches(Blocks.GRAVEL.getDefaultState(), 5);
	protected static final WorldGenAbstractTree SHRUB_SPRUCE = new WorldGenTreeShrubSpruce();
	
	public BiomeTundra(BiomeProperties properties)
	{	
		super(properties);
	
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.extraTreeChance = 4;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 3;
	    this.decorator.generateFalls = true;
	    
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

		int spruceShrubWeight = 1;

		int totalWeight = spruceShrubWeight;

		int randomWeight = rand.nextInt(totalWeight);

		List<WorldGenAbstractTree> treeList = new ArrayList<>();
		treeList.add(SHRUB_SPRUCE);

		int treeIndex = randomWeight % treeList.size();
		return treeList.get(treeIndex);

	}
	
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
	{
	    return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
	}
	
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		  net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
	       WorldGenerator lapis = new LapisGenerator();
	       if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, lapis, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS))
	    	   lapis.generate(worldIn, rand, pos);
	       
		int stonepatchChance = rand.nextInt(3);
		if (stonepatchChance == 0) {
			int k6 = rand.nextInt(16) + 8;
			int l = rand.nextInt(16) + 8;
			BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
			GRAVEL_PATCHES.generate(worldIn, rand, blockpos);
		}
		int icepatchChance = rand.nextInt(3);
		if (icepatchChance == 0) {
			int k6 = rand.nextInt(16) + 8;
			int l = rand.nextInt(16) + 8;
			BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
			ICE_PATCHES.generate(worldIn, rand, blockpos);
		}
	    super.decorate(worldIn, rand, pos);
		}

	 public static class LapisGenerator extends WorldGenerator
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
	                    worldIn.setBlockState(blockpos, Blocks.LAPIS_ORE.getDefaultState(), 16 | 2);
	                }
	                net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
	            }
	            return true;
	        }
	    }
	 	@Override
	    public int getGrassColorAtPos(BlockPos pos)
	    {
	        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
	        return d0 < -0.1D ? super.getModdedBiomeGrassColor(0xAB853E) : super.getModdedBiomeGrassColor(0xB46438);
	    }
		@Override
		public int getModdedBiomeFoliageColor(int original) {
		    return super.getModdedBiomeFoliageColor(0xB2893A);
	}
}