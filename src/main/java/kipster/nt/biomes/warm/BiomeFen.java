package kipster.nt.biomes.warm;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenFossils;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Random;

public class BiomeFen extends Biome 
{
	
	protected static final IBlockState WATER_LILY = Blocks.WATERLILY.getDefaultState();
	
	public BiomeFen(BiomeProperties properties)
	{	
		super(properties);
	
		

		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.extraTreeChance = 0.05F;
        this.decorator.flowersPerChunk = 1;
        this.decorator.deadBushPerChunk = 1;
        this.decorator.mushroomsPerChunk = 10;
        this.decorator.reedsPerChunk = 10;
        this.decorator.clayPerChunk = 5;
        this.decorator.waterlilyPerChunk = 6;
        this.decorator.sandPatchesPerChunk = 0;
        this.decorator.gravelPatchesPerChunk = 0;
        this.decorator.grassPerChunk = 15;
        
        
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 2, 2, 2));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWitch.class, 5, 1, 1));
        
	}

	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
		if (rand.nextInt(2) > 0)
		{
			return BlockFlower.EnumFlowerType.BLUE_ORCHID;
		}
		else
		{
        return BlockFlower.EnumFlowerType.HOUSTONIA;
    }
    }

    public void addDoublePlants(World world, Random random, BlockPos pos) {

        ArrayList<BlockDoublePlant.EnumPlantType> plantTypes = new ArrayList<>();
        plantTypes.add(BlockDoublePlant.EnumPlantType.SYRINGA);
        plantTypes.add(BlockDoublePlant.EnumPlantType.ROSE);
        plantTypes.add(BlockDoublePlant.EnumPlantType.PAEONIA);

        BlockDoublePlant.EnumPlantType plantType = getRandomPlantType(random, plantTypes);
        DOUBLE_PLANT_GENERATOR.setPlantType(plantType);

        int x = random.nextInt(16) + 8;
        int z = random.nextInt(16) + 8;
        int y = findTopNonAirBlock(world, pos.add(x, 0, z));
        BlockPos plantPos = pos.add(x, y, z);

        DOUBLE_PLANT_GENERATOR.generate(world, random, plantPos);
    }

    private BlockDoublePlant.EnumPlantType getRandomPlantType(Random random,
                                                              ArrayList<BlockDoublePlant.EnumPlantType> plantTypes) {
        return plantTypes.get(random.nextInt(plantTypes.size()));
    }

    private int findTopNonAirBlock(World world, BlockPos pos) {
        for (int y = 255; y >= 0; y--) {
            if(world.getBlockState(pos).getBlock() != Blocks.AIR)  {
                return y + 1;
            }
        }
        return -1;
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

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }
    

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
        if (rand.nextInt(64) == 0)
        {
            (new WorldGenFossils()).generate(worldIn, rand, pos);
        }
        this.addDoublePlants(worldIn, rand, pos);
        super.decorate(worldIn, rand, pos);
    }
        @Override
        public int getModdedBiomeGrassColor(int original) {
            return 0x63A154;
        }

        @Override
        public int getModdedBiomeFoliageColor(int original) {
            return 0x63A154;
        }


   	 @Override
   	    public void addDefaultFlowers()
   	    {
   	        addFlower(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), BlockFlower.EnumFlowerType.BLUE_ORCHID), 20);
   	    
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