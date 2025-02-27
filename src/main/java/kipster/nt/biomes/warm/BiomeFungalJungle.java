package kipster.nt.biomes.warm;

import kipster.nt.world.gen.WorldGenPatches;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class BiomeFungalJungle extends Biome 
	{
	
	protected static final WorldGenPatches MUSHROOM_PATCHES = new WorldGenPatches(Blocks.MYCELIUM.getDefaultState(), 9);
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
	private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    private static final IBlockState OAK_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    
    public BiomeFungalJungle(BiomeProperties properties)
	{	
		super(properties);
	
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		

		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.generateFalls = true;
        this.decorator.treesPerChunk = 50;
        this.decorator.flowersPerChunk = 6;
        this.decorator.grassPerChunk = 25;
        this.decorator.mushroomsPerChunk = 25;
        this.decorator.bigMushroomsPerChunk = 17;
        
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityParrot.class, 40, 1, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
        this.flowers.clear();
        for (BlockFlower.EnumFlowerType type : BlockFlower.EnumFlowerType.values())
        {
            if (type.getBlockType() == BlockFlower.EnumFlowerColor.YELLOW) continue;
            if (type == BlockFlower.EnumFlowerType.BLUE_ORCHID) type = BlockFlower.EnumFlowerType.POPPY;
            addFlower(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), type), 10);
        }
}
 public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
            double d0 = MathHelper.clamp((1.0D + GRASS_COLOR_NOISE.getValue((double)pos.getX() / 48.0D, (double)pos.getZ() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
            BlockFlower.EnumFlowerType blockflower$enumflowertype = BlockFlower.EnumFlowerType.values()[(int)(d0 * (double)BlockFlower.EnumFlowerType.values().length)];
            return blockflower$enumflowertype == BlockFlower.EnumFlowerType.BLUE_ORCHID ? BlockFlower.EnumFlowerType.POPPY : blockflower$enumflowertype;
        }
	
 public void addDoublePlants(World p_185378_1_, Random p_185378_2_, BlockPos p_185378_3_, int p_185378_4_)
 {
     for (int i = 0; i < p_185378_4_; ++i)
     {
         int j = p_185378_2_.nextInt(3);

         if (j == 0)
         {
             DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.SYRINGA);
         }
         else if (j == 1)
         {
             DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.ROSE);
         }
         else if (j == 2)
         {
             DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.PAEONIA);
         }

         for (int k = 0; k < 5; ++k)
         {
             int l = p_185378_2_.nextInt(16) + 8;
             int i1 = p_185378_2_.nextInt(16) + 8;
             int j1 = p_185378_2_.nextInt(p_185378_1_.getHeight(p_185378_3_.add(l, 0, i1)).getY() + 32);

             if (DOUBLE_PLANT_GENERATOR.generate(p_185378_1_, p_185378_2_, new BlockPos(p_185378_3_.getX() + l, j1, p_185378_3_.getZ() + i1)))
             {
                 break;
             }
         }
     }
 }

public WorldGenAbstractTree getRandomTreeFeature(Random rand)
{
 if (rand.nextInt(10) == 0)
 {
     return BIG_TREE_FEATURE;
 }
 else if (rand.nextInt(2) == 0)
 {
     return new WorldGenShrub(JUNGLE_LOG, OAK_LEAF);
 }
 else
 {
     return (WorldGenAbstractTree)(rand.nextInt(3) == 0 ? new WorldGenMegaJungle(false, 10, 20, JUNGLE_LOG, JUNGLE_LEAF) : new WorldGenTrees(false, 4 + rand.nextInt(7), JUNGLE_LOG, JUNGLE_LEAF, true));
 }
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
	        

	         int i = rand.nextInt(16) + 8;
	         int j = rand.nextInt(16) + 8;
	         int height = worldIn.getHeight(pos.add(i, 0, j)).getY() * 2; // could == 0, which crashes nextInt
	         if (height < 1) height = 1;
	         int k = rand.nextInt(height);
	         if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.PUMPKIN))
	         (new WorldGenMelon()).generate(worldIn, rand, pos.add(i, k, j));
	         WorldGenVines worldgenvines = new WorldGenVines();

	         if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	         for (int j1 = 0; j1 < 50; ++j1)
	         {
	             k = rand.nextInt(16) + 8;
	             int l = 128;
	             int i1 = rand.nextInt(16) + 8;
	             worldgenvines.generate(worldIn, rand, pos.add(k, 128, i1));
	         }
	         if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS))
	         { // no tab for patch
	         int i1 = rand.nextInt(5) - 3;
	         {
	             i1 += 2;
	         }

	         this.addDoublePlants(worldIn, rand, pos, i1);
	         }
	         
	         int mushroompatchChance = rand.nextInt(4);
	 		if (mushroompatchChance == 0) {
	 			int k6 = rand.nextInt(16) + 8;
	 			int l = rand.nextInt(16) + 8;
	 			BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
	 			MUSHROOM_PATCHES.generate(worldIn, rand, blockpos);
	        
	        }
	         net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));

	    super.decorate(worldIn, rand, pos);
	}
	
	   @Override
	    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
	        if (noiseVal > 2D) {
	            this.topBlock = Blocks.MYCELIUM.getDefaultState();
	            this.fillerBlock = Blocks.DIRT.getDefaultState();
	        } else {
	            this.topBlock = Blocks.GRASS.getDefaultState();
	            this.fillerBlock = Blocks.DIRT.getDefaultState();
	        }

	        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
}
	
	@Override
	public int getModdedBiomeGrassColor(int original) {
	    return super.getModdedBiomeGrassColor(0x41B04E);
	}
	@Override
	public int getModdedBiomeFoliageColor(int original) {
	    return super.getModdedBiomeFoliageColor(0x41B04E);
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

                IBlockState state = worldIn.getBlockState(blockpos);
                if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, net.minecraft.block.state.pattern.BlockMatcher.forBlock(Blocks.STONE)))
                {
                    worldIn.setBlockState(blockpos, Blocks.EMERALD_ORE.getDefaultState(), 16 | 2);
                }
            }
            return true;
        }
    }
}