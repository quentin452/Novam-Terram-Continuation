package kipster.nt.biomes.warm;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeLake extends Biome 
{	
	
	private final WorldGenTaiga2 spruceGenerator = new WorldGenTaiga2(false);
	
	public BiomeLake(BiomeProperties properties)
	{	
		super(properties);
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.treesPerChunk = 2;
		this.decorator.reedsPerChunk = 3;
	    this.decorator.clayPerChunk = 4;
		this.decorator.extraTreeChance = 0.05F;
		this.decorator.sandPatchesPerChunk = 2;
	    this.decorator.gravelPatchesPerChunk = 4;
	    this.decorator.grassPerChunk = 3;
	    
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	{
	    return (WorldGenAbstractTree)(rand.nextInt(3) > 0 ? this.spruceGenerator : super.getRandomTreeFeature(rand));
	}
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
	{
	    return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
	}

	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
	    net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
        WorldGenerator emeralds = new EmeraldGenerator();
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
            emeralds.generate(worldIn, rand, pos);
	    
	    DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

	    if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	    for (int i = 0; i < 7; ++i)
	    {
	        int j = rand.nextInt(16) + 8;
	        int k = rand.nextInt(16) + 8;
	        int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	        DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	    }
		super.decorate(worldIn, rand, pos);
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
}