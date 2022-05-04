package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.biomes.decorators.VolcanoDecorator;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.util.BlockRegistrar;

import java.util.Random;
import java.util.StringTokenizer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ActiveVolcano extends Biome  {
    protected static IBlockState BASALT;
    protected static IBlockState ASH;
    protected static WorldGenLakes LAVA = new WorldGenLakes(Blocks.LAVA);

	public ActiveVolcano(BiomeProperties properties) {
		super(properties);
        decorator = new VolcanoDecorator();
        decorator.cactiPerChunk = -999;
		decorator.treesPerChunk = -999;
        decorator.grassPerChunk = -999;
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
	}
	
	
	public static void init() {
		String a = ConfigHandler.ashBlock;
		String b = ConfigHandler.basaltBlock;
		if((a == null) || (a.equals("default")) || (a.equals(ConfigHandler.ASH_BLOCK))) {
		    ASH = BlockRegistrar.getAshBlock();
		} else {
		    ASH = getBlockState(a);
		    if(ASH == null) {
		    	ASH = BlockRegistrar.getAshBlock();
		    }
		}
		if((b == null) || (b.equals("default")) || (b.equals(ConfigHandler.BASALT_BLOCK))) {
		    BASALT = BlockRegistrar.getBasaltBlock();
		} else {
		    BASALT = getBlockState(b);
            if(BASALT == null) {
            	BASALT = BlockRegistrar.getBasaltBlock();
            }
		}		
	}
	
	
	private static IBlockState getBlockState(String s) {
		StringTokenizer tokens = new StringTokenizer(s, "()");
		String str = tokens.nextToken();
		if(tokens.hasMoreTokens()) {
			int meta = Integer.parseInt(tokens.nextToken());
		    return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(str))
		    		.getStateFromMeta(meta);
		} else {
		    return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(str))
		    		.getDefaultState();
		}
	}
	

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer cpi, 
    			int x, int z, double noise) {
        if(noise > 2.5) {
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
        
        generateBiomeTerrain(worldIn, rand, cpi, x, z, noise);
        
        int k1 = x & 15;
        int l1 = z & 15;
        int ty = 64 + (int)(noise * 4);
        for (int j1 = 255; j1 >= ty; --j1) {
        	if((cpi.getBlockState(l1, j1, k1).getBlock() == Blocks.STONE)) {
        		cpi.setBlockState(l1, j1, k1, BASALT);
        	}
        }
    }
    
    
//    @Override
//    public void decorate(World worldIn, Random rand, BlockPos pos) {    
//    	//System.out.println("BlockPos pos " + pos);
//    	super.decorate(worldIn, rand, pos);
//    }
    
    
}
