package jaredbgreat.climaticbiome.generation;

import jaredbgreat.climaticbiome.generation.chunk.BasinNode;
import jaredbgreat.climaticbiome.generation.chunk.BiomeBasin;
import jaredbgreat.climaticbiome.generation.chunk.BiomeFinder;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.Region;
import jaredbgreat.climaticbiome.generation.chunk.SpatialNoise;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.IntCache;

public class BetterBiomeProvider extends BiomeProvider {
	private World world;
	private BiomeFinder finder;
	
	
	public BetterBiomeProvider(World world) {		
		super(world.getWorldInfo());		
		this.world = world;
		finder = new BiomeFinder(world.getSeed());
	}


    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
    	biomes = new Biome[100];
    	for(int i = 0; i < 10; i++) 
    		for(int j = 0; j < 10; j++) {
    			biomes[(j * 10) + i] = Biome.getBiome(getIDForCoords(x + i, z + j), 
    					Biomes.DEFAULT);
    		}
        return biomes;
    }


    @Override
    public Biome[] getBiomes(@Nullable Biome[] in, int x, int z, int width, int depth, boolean cacheFlag) {
    	IntCache.resetIntCache();
    	Biome[] out;
        if((in == null) || (in.length < (width * depth))) {
            in = new Biome[width * depth];
        }
        if(in.length == 256) {
    		out = finder.getChunkGrid(finder.makeChunk(x / 16, z / 16));
    		System.arraycopy(out, 0, in, 0, 256);  
    		//System.out.println("Made cached chunk at " + (x/15) +", " + (z/16));
    	} else {
    		in = finder.getChunkGrid(finder.makeChunk(x / 16, z / 16));    			 
    		//System.out.println("Re-generated chunk at " + (x/15) +", " + (z/16));
    	}
        return in;
    }
    
    
    private int getIDForCoords(int x, int z) {
    	return Biome.getIdForBiome(getBiome(new BlockPos(x * 4, 64, z * 4)));
    }
    
    
    private int modRight(int in) {
    	in %= 4;
    	if(in < 0) {
    		in += 4;
    	}
    	return in;
    }
    
    
    private int chunkModulus(int in) {
    	in %= 16;
    	if(in < 0) {
    		in += 16;
    	}
    	return in;
    }

    
    @Override
    public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed) {
        return allowed.contains(Biome.getBiome(finder.makeChunk((x / 16), (z / 16))[33].getBiome()));
    }

}
