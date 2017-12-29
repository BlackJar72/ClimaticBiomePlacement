package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.generation.cache.AbstractCachable;
import jaredbgreat.climaticbiome.generation.cache.MutableCoords;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.Biome;

public class BiomeArray extends AbstractCachable {
	public static final int WIDTH = 16;
	public static final int SIZE  = WIDTH * WIDTH;
	
	private Biome[] biomes;
    
    private MutableCoords coords;
    private long timestamp;
    private boolean cached;
    
	
	public BiomeArray() {
		biomes = new Biome[SIZE];
    	cached = false;
    	coords = new MutableCoords();
	}
    
	
	public BiomeArray(Biome[] in, int x, int z) {
		biomes = in;
    	cached = true;
    	coords = new MutableCoords().init(x, z);
    	timestamp = MinecraftServer.getCurrentTimeMillis();    	
	}
	
	
	public BiomeArray init(int cx, int cz) {
    	cached = false;
        coords.init(cx, cz);
        timestamp = MinecraftServer.getCurrentTimeMillis();
        return this;
	}
	
	
	public Biome[] getArray() {
		return biomes;
	}
	
	
	void setArray(Biome[] in) {
		biomes = in;
	}
}
