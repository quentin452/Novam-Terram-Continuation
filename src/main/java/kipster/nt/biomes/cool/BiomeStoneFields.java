package kipster.nt.biomes.cool;

import kipster.nt.blocks.BlockInit;
import kipster.nt.config.MiscConfig;
import kipster.nt.world.gen.WorldGenPatches;
import kipster.nt.world.gen.WorldGenStoneSpike;
import kipster.nt.world.gen.flowers.WorldGenHeliotropiumFlower;
import kipster.nt.world.gen.flowers.WorldGenNeglectedScorpionweedFlower;
import kipster.nt.world.gen.trees.WorldGenTreeShrubSpruce;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

public class BiomeStoneFields extends Biome 
{
	protected static final WorldGenBlockBlob COBBLESTONE_BOULDER_FEATURE = new WorldGenBlockBlob(Blocks.COBBLESTONE, 1);
	private final WorldGenStoneSpike stoneSpike = new WorldGenStoneSpike();
	protected static final WorldGenPatches STONE_PATCHES = new WorldGenPatches(Blocks.STONE.getDefaultState(), 7);
	protected static final WorldGenAbstractTree SHRUB_SPRUCE = new WorldGenTreeShrubSpruce();
	
	 public BiomeStoneFields(BiomeProperties properties)
	  	{	
	  		super(properties);
	  	
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.extraTreeChance = 2;
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 5;
	    this.decorator.generateFalls = true;
	    
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
	    this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) 
	{
		return (WorldGenAbstractTree)(rand.nextInt(1) == 0 ? SHRUB_SPRUCE : SHRUB_SPRUCE);
	}
	
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
	{
	    return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
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
	protected static final WorldGenerator HELIOTROPIUMFLOWER= new WorldGenHeliotropiumFlower(BlockInit.HELIOTROPIUMFLOWER.getDefaultState());
	protected static final WorldGenerator NEGLECTEDSCORPIONWEEDFLOWER = new WorldGenNeglectedScorpionweedFlower(BlockInit.NEGLECTEDSCORPIONWEEDFLOWER.getDefaultState());

	public void decorate(World worldIn, Random rand, BlockPos pos)
	{

		int flowersPerChunk = 3;

		generateFlowers(worldIn, rand, pos, flowersPerChunk, HELIOTROPIUMFLOWER);
		generateFlowers(worldIn, rand, pos, flowersPerChunk, NEGLECTEDSCORPIONWEEDFLOWER);


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
		if (!MiscConfig.disableBouldersInStoneFields && net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.ROCK)) {
            int genChance = rand.nextInt(3);
            if (genChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
                COBBLESTONE_BOULDER_FEATURE.generate(worldIn, rand, blockpos);
            }
		for (int i = 0; i < 5; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            this.stoneSpike.generate(worldIn, rand, worldIn.getHeight(pos.add(j, 0, k)));
        }
		}
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
	                net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
	            }
	            return true;
	        }
	    }
}