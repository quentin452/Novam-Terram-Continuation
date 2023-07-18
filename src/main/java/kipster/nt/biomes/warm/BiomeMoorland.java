package kipster.nt.biomes.warm;

import kipster.nt.world.gen.WorldGenPatches;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenFossils;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeMoorland extends Biome 
{
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	protected static final WorldGenPatches GRASS_PATCHES = new WorldGenPatches(Blocks.GRASS.getDefaultState(), 7);
	protected static final IBlockState WATER_LILY = Blocks.WATERLILY.getDefaultState();

	public BiomeMoorland(BiomeProperties properties)
	{	
		super(properties);
	
			topBlock = Blocks.GRASS.getDefaultState();
			fillerBlock = Blocks.DIRT.getDefaultState();
		
		    this.decorator.extraTreeChance = 0.0002F;
	        this.decorator.flowersPerChunk = 45;
	        this.decorator.reedsPerChunk = 13;
	        this.decorator.clayPerChunk = 5;
	        this.decorator.waterlilyPerChunk = 3;
	        this.decorator.grassPerChunk = 50;
	        this.decorator.generateFalls = true;
	        
	        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 1, 1, 1));
	        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 8, 4, 4));
	        
	}
	    
	    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
	    {
	        return BlockFlower.EnumFlowerType.ALLIUM;
	    }

	public void decorate(World worldIn, Random rand, BlockPos pos) {
		if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL)) {
			if (rand.nextInt(64) == 0) {
				(new WorldGenFossils()).generate(worldIn, rand, pos);
			}
		}

		net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
		WorldGenerator emeralds = new EmeraldGenerator();
		if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD)) {
			emeralds.generate(worldIn, rand, pos);
		}
		super.decorate(worldIn, rand, pos);
	}

	@Override
	    public int getGrassColorAtPos(BlockPos pos)
	    {
	        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
	        return d0 < -0.1D ? super.getModdedBiomeGrassColor(0xA19C5B) : super.getModdedBiomeGrassColor(0x95B652);
	    }

	   	@Override
	   	public int getModdedBiomeFoliageColor(int original) {
	   	    return super.getModdedBiomeFoliageColor(0x8EA13D);
	   	}

	    @Override
	    public void addDefaultFlowers()
	    {
	        addFlower(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), BlockFlower.EnumFlowerType.ALLIUM), 20);
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
	            }
	            return true;
	        }
	}
}