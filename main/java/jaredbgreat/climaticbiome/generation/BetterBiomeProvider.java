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
    	int size = width * height;
    	if (biomes == null || biomes.length < size) {
            biomes = new Biome[size];
        }
    	for(int i = 0; i < width; i++) 
    		for(int j = 0; j < height; j++) {
    			biomes[(j * 10) + i] = Biome.getBiome(getIDForCoords(x + i, z + j), 
    					Biomes.DEFAULT);
    		}
        return biomes;
    }


    @Override
    public Biome[] getBiomes(@Nullable Biome[] in, int x, int z, int width, int depth, boolean cacheFlag) {
    	int size = width * depth;
    	Biome[] out;
        if((in == null) || (in.length < (size))) {
            in = new Biome[size];
        }
        if((width == 16) && (depth == 16) && ((x & 15) == 0) && ((z & 15) == 0)) {
    		out = finder.findChunkGrid(x / 16, z / 16);
    		System.arraycopy(out, 0, in, 0, size);
    	} else {
    		// FIXME: Handle non-chunk dimensions
    		in = finder.findChunkGrid(x / 16, z / 16);  
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
        return allowed.contains(Biome.getBiome(finder.findSingleChunk((x / 16), (z / 16)).getBiome()));
    }


    /**
     * Calls the WorldChunkManager's biomeCache.cleanupCache()
     */
    public void cleanupCache()  {
    	finder.cleanCaches();
        super.cleanupCache();
    }

}
