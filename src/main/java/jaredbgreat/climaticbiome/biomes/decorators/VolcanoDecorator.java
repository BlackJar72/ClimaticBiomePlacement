package jaredbgreat.climaticbiome.biomes.decorators;

public class VolcanoDecorator /*extends BiomeDecorator*/ {/*
    private WorldGenerator extraIron;
    private WorldGenerator extraGold;
	

	@Override
    protected void genDecorations(Biome biome, World world, Random random) {
        extraIron = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), 
        		chunkProviderSettings.ironSize);
        extraGold = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), 
        		(int)(chunkProviderSettings.goldSize * 1.5));
        
    	super.genDecorations(biome, world, random);
    	
    	for (int i = 0; i < 24; ++i)  {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            int y = random.nextInt(random.nextInt(random.nextInt(64) + 64) + 64);
            BlockPos pos = chunkPos.add(x, y, z);
    		IBlockState old = world.getBlockState(pos);
        	if(old.getMaterial() == Material.ROCK) {
                IBlockState bs = Blocks.FLOWING_LAVA.getDefaultState();
                world.setBlockState(pos, bs, 3);
                world.immediateBlockTick(pos, bs, random);
        	}
        }
        int x = random.nextInt(16) + 8;
        int y = random.nextInt(256);
        int z = random.nextInt(16) + 8;
        new WorldGenLakes(Blocks.LAVA).generate(world, random, chunkPos.add(x, y, z));
    }
	

	@Override
    protected void generateOres(World world, Random random) {
    	super.generateOres(world, random);
        if(TerrainGen.generateOre(world, random, 
        		extraIron, chunkPos, 
        		OreGenEvent.GenerateMinable.EventType.IRON))
        	genStandardOre1(world, random, chunkProviderSettings.ironCount, extraIron, 
        			chunkProviderSettings.ironMinHeight, 
        			(int)(chunkProviderSettings.ironMaxHeight * 0.75));
        if(TerrainGen.generateOre(world, random, goldGen, chunkPos, 
        		OreGenEvent.GenerateMinable.EventType.GOLD)) 
        	genStandardOre1(world, random, chunkProviderSettings.goldCount, extraGold, 
        			chunkProviderSettings.goldMinHeight, 
        			(int)(chunkProviderSettings.goldMaxHeight * 1.5));
        
    }

*/}
