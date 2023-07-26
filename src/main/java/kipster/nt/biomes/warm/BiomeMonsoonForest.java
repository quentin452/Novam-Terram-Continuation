package kipster.nt.biomes.warm;

import kipster.nt.config.MiscConfig;
import kipster.nt.world.gen.trees.WorldGenTreeBigJacaranda;
import kipster.nt.world.gen.trees.WorldGenTreeShrubJungle;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeMonsoonForest extends Biome 
	{
	protected static final WorldGenTreeBigJacaranda JACARANDA_TREE = new WorldGenTreeBigJacaranda(false);
	protected static final WorldGenBlockBlob STONE_BOULDER_FEATURE = new WorldGenBlockBlob(Blocks.MOSSY_COBBLESTONE, 1);
	private static final WorldGenSavannaTree SAVANNA_TREE = new WorldGenSavannaTree(false);
	protected static final WorldGenLakes LAKE = new WorldGenLakes(Blocks.WATER);
    private static final WorldGenTreeShrubJungle JUNGLE_TREE = new WorldGenTreeShrubJungle();
    public BiomeMonsoonForest(BiomeProperties properties)
	{	
		super(properties);
	
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.DIRT.getDefaultState();
		
		this.decorator.waterlilyPerChunk = 3;
		this.decorator.reedsPerChunk = 15;
		this.decorator.generateFalls = true;
        this.decorator.treesPerChunk = 25;
        this.decorator.flowersPerChunk = 14;
        this.decorator.grassPerChunk = 28;
        this.decorator.mushroomsPerChunk = 25;
        this.decorator.bigMushroomsPerChunk = 1;
		this.decorator.clayPerChunk = 5;
        
        
	    this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 1, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityParrot.class, 40, 1, 2));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.flowers.clear();
        for (BlockFlower.EnumFlowerType type : BlockFlower.EnumFlowerType.values())
        {
            if (type.getBlockType() == BlockFlower.EnumFlowerColor.YELLOW) continue;
            if (type == BlockFlower.EnumFlowerType.BLUE_ORCHID) type = BlockFlower.EnumFlowerType.POPPY;
            addFlower(net.minecraft.init.Blocks.RED_FLOWER.getDefaultState().withProperty(net.minecraft.init.Blocks.RED_FLOWER.getTypeProperty(), type), 10);
        }
}
 public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
            double d0 = MathHelper.clamp((1.0D + GRASS_COLOR_NOISE.getValue((double)pos.getX() / 48.0D, (double)pos.getZ() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
            BlockFlower.EnumFlowerType blockflower$enumflowertype = BlockFlower.EnumFlowerType.values()[(int)(d0 * (double)BlockFlower.EnumFlowerType.values().length)];
            return blockflower$enumflowertype == BlockFlower.EnumFlowerType.BLUE_ORCHID ? BlockFlower.EnumFlowerType.POPPY : blockflower$enumflowertype;
        }

        public void addDoublePlants(World world, Random random, BlockPos pos, int count) {
            List<BlockDoublePlant.EnumPlantType> validPlantTypes = new ArrayList<>();
            validPlantTypes.add(BlockDoublePlant.EnumPlantType.SYRINGA);
            validPlantTypes.add(BlockDoublePlant.EnumPlantType.ROSE);
            validPlantTypes.add(BlockDoublePlant.EnumPlantType.PAEONIA);

            DOUBLE_PLANT_GENERATOR.setPlantType(getPlantType(random, validPlantTypes));

            for (int i = 0; i < count; i++) {

                int x, z, y;
                do {
                    x = random.nextInt(16) + 8;
                    z = random.nextInt(16) + 8;
                    y = random.nextInt(world.getHeight(pos.add(x, 0, z)).getY() + 32);
                } while (!DOUBLE_PLANT_GENERATOR.generate(world, random, pos.add(x, y, z)));
            }
        }

        private BlockDoublePlant.EnumPlantType getPlantType(Random random, List<BlockDoublePlant.EnumPlantType> plants) {
            return plants.get(random.nextInt(plants.size()));
        }

        public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

            int bigTreeWeight = 10;
            int shrubWeight = 3;
            int swampWeight = 2;
            int savannaWeight = 2;
            int jacarandaWeight = 2;
            int jungleWeight = 1;

            int totalWeight = bigTreeWeight + shrubWeight +swampWeight + savannaWeight + jacarandaWeight + jungleWeight;

            int randomWeight = rand.nextInt(totalWeight);

            List<WorldGenAbstractTree> treeList = new ArrayList<>();
            treeList.add(BIG_TREE_FEATURE);
            treeList.add(SWAMP_FEATURE);
            treeList.add(SAVANNA_TREE);
            treeList.add(JACARANDA_TREE);
            treeList.add(JUNGLE_TREE);
            // Ajoutez les autres arbres à la liste

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
	        WorldGenerator emeralds = new EmeraldGenerator();
	        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
	            emeralds.generate(worldIn, rand, pos);
	        
	        if (!MiscConfig.disableBouldersInMonsoonForest && net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.ROCK)) {
	            int genChance = rand.nextInt(3);
	            if (genChance == 0) {
	                int k6 = rand.nextInt(16) + 8;
	                int l = rand.nextInt(16) + 8;
	                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
	                STONE_BOULDER_FEATURE.generate(worldIn, rand, blockpos);
	            }
            }

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
	         
	         net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
		 }
	    super.decorate(worldIn, rand, pos);
	        
	}
	
	
	@Override
	public int getModdedBiomeGrassColor(int original) {
	    return super.getModdedBiomeGrassColor(0x33B058);
	}
	@Override
	public int getModdedBiomeFoliageColor(int original) {
	    return super.getModdedBiomeFoliageColor(0x26C139);
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