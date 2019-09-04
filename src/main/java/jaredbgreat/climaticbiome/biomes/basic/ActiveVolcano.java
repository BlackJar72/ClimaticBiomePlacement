package jaredbgreat.climaticbiome.biomes.basic;

public class ActiveVolcano /*extends Biome*/  {/*
    protected static final IBlockState BASALT = BlockRegistrar.blockBasalt.getDefaultState();
    protected static final IBlockState ASH = BlockRegistrar.blockAsh.getDefaultState();
    protected static final WorldGenLakes LAVA = new WorldGenLakes(Blocks.LAVA);

	public ActiveVolcano(BiomeProperties properties) {
		super(properties);
        decorator = new VolcanoDecorator();
        decorator.cactiPerChunk = -999;
		decorator.treesPerChunk = -999;
        decorator.grassPerChunk = -999;
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
	}
	

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, 
    			int x, int z, double noise) {
		if(noise > 1.95) {
			fillerBlock = ASH;
		} else {
			fillerBlock = BASALT;
		}
        if(noise > 1.25) {
        	this.topBlock = ASH;
        } else if(rand.nextInt(196) == 0){
        	topBlock = Blocks.OBSIDIAN.getDefaultState();
        } else {
        	topBlock = BASALT;
    	}
        
        generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noise);
        
        int k1 = x & 15;
        int l1 = z & 15;
        int ty = 64 + (int)(noise * 4);
        for (int j1 = 255; j1 >= ty; --j1) {
        	if((chunkPrimerIn.getBlockState(l1, j1, k1).getBlock() == Blocks.STONE)) {
        		chunkPrimerIn.setBlockState(l1, j1, k1, BASALT);
        	}
        }
    }
    
    
//    @Override
//    public void decorate(World worldIn, Random rand, BlockPos pos) {    
//    	//System.out.println("BlockPos pos " + pos);
//    	super.decorate(worldIn, rand, pos);
//    }
    
    
*/}
