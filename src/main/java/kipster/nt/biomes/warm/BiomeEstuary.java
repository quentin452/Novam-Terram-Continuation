package kipster.nt.biomes.warm;

import kipster.nt.world.gen.WorldGenPatches;
import kipster.nt.world.gen.trees.WorldGenTreeSandSwamp;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFossils;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeEstuary extends Biome 
{
	private static final WorldGenAbstractTree SWAMP_TREE = new WorldGenTreeSandSwamp();
	protected static final WorldGenPatches GRASS_PATCHES = new WorldGenPatches(Blocks.GRASS.getDefaultState(), 7);
	protected static final IBlockState WATER_LILY = Blocks.WATERLILY.getDefaultState();

	public BiomeEstuary(BiomeProperties properties)
	{	
		super(properties);
	
			topBlock = Blocks.GRASS.getDefaultState();
			fillerBlock = Blocks.DIRT.getDefaultState();
		
			 this.decorator.generateFalls = true;
		    this.decorator.treesPerChunk = 8;
	        this.decorator.flowersPerChunk = 1;
	        this.decorator.reedsPerChunk = 20;
	        this.decorator.clayPerChunk = 5;
	        this.decorator.waterlilyPerChunk = 4;
	        this.decorator.sandPatchesPerChunk = 19;
	        this.decorator.grassPerChunk = 6;
	        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 1, 1, 1));
	    }

	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return (WorldGenAbstractTree)(rand.nextInt(3) > 0 ? SWAMP_TREE : SWAMP_TREE);
}
	    
	    public WorldGenerator getRandomWorldGenForGrass(Random rand)
	    {
	        return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
	    }
	    
	    

	    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
	    {
	        return BlockFlower.EnumFlowerType.BLUE_ORCHID;
	    }

	    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
	    {
	        double d0 = GRASS_COLOR_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);

	        if (d0 > 0.0D)
	        {
	            int i = x & 15;
	            int j = z & 15;

	            for (int k = 255; k >= 0; --k)
	            {
	                if (chunkPrimerIn.getBlockState(j, k, i).getMaterial() != Material.AIR)
	                {
	                    if (k == 62 && chunkPrimerIn.getBlockState(j, k, i).getBlock() != Blocks.WATER)
	                    {
	                        chunkPrimerIn.setBlockState(j, k, i, WATER);

	                        if (d0 < 0.12D)
	                        {
	                            chunkPrimerIn.setBlockState(j, k + 1, i, WATER_LILY);
	                        }
	                    }

	                    break;
	                }
	            }
	        }
	        if (noiseVal > 2.1D) {
	            this.topBlock = Blocks.SAND.getDefaultState();
	            this.fillerBlock = Blocks.SAND.getDefaultState(); }
	        
	        else {
	        	
	         this.topBlock = Blocks.GRASS.getDefaultState();
	            this.fillerBlock = Blocks.DIRT.getDefaultState();
	        }

	        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	    }

	    public void decorate(World worldIn, Random rand, BlockPos pos)
	    {
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
	        if (rand.nextInt(64) == 0)
	        {
	            (new WorldGenFossils()).generate(worldIn, rand, pos);
	        }
	        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
	        WorldGenerator emeralds = new EmeraldGenerator();
	        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
	            emeralds.generate(worldIn, rand, pos);
	      
	        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
			super.decorate(worldIn, rand, pos);
		}
	  	@Override
	   	public int getModdedBiomeGrassColor(int original) {
	   	    return 0x92B25C;
	   	}

	   	@Override
	   	public int getModdedBiomeFoliageColor(int original) {
	   	    return 0x629D30;
	   	}

	    @Override
	    public void addDefaultFlowers()
	    {
	        addFlower(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), BlockFlower.EnumFlowerType.BLUE_ORCHID), 10);
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