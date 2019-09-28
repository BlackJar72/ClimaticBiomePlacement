package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.biomes.decorators.VolcanoDecorator;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.util.BlockRegistrar;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ActiveVolcano extends Biome  {
    protected static IBlockState BASALT = BlockRegistrar.blockBasalt.getDefaultState();
    protected static IBlockState ASH = BlockRegistrar.blockAsh.getDefaultState();
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
		    ASH = BlockRegistrar.blockAsh.getDefaultState();
		} else {
		    ASH = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(a)).getDefaultState();
		}
		if((b == null) || (b.equals("default")) || (b.equals(ConfigHandler.BASALT_BLOCK))) {
		    BASALT = BlockRegistrar.blockBasalt.getDefaultState();
		} else {
		    BASALT = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(b)).getDefaultState();
		}		
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
    
    
}
