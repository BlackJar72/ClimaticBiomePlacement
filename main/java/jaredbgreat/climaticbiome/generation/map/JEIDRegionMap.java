package jaredbgreat.climaticbiome.generation.map;

import jaredbgreat.climaticbiome.generation.cache.AbstractCachable;

public class JEIDRegionMap extends AbstractCachable {
    
    public final int dataSize;
    public final int cWidth;
    public final int bWidth;
    
    final int[] data;
    
    static int n = 0;
    
    public JEIDRegionMap(int x, int z, int width) {
        super(x, z);
        cWidth = width;
        bWidth = width * 16;
        dataSize = width * width;
        data = new int[dataSize];
        n++;
    }
    
    
    public void finalize() throws Throwable {
    	n--;
    	super.finalize();
    }
    
    
    /**
     * Returns in game biome id.
     * 
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     * @return The biome id as in int
     */
    public int getBiome(int x, int z) {
        return (data[(x * cWidth) + z] & 0xff);
    }
    
    
    /**
     * Returns the and id for the sub-biome exentions as a byte.
     * 
     * The id will be returned as a short.
     * 
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     * @return The biome id for world-gen as a short
     */
    public int getSubBiomeId(int x, int z) {
        return (data[(x * cWidth) + z] & 0xff00) >> 8;
    }
    
    
    /**
     * Returns the id for the biome to be for world gen.  For 
     * registered biomes this should be there real id, and less than 
     * 256.  For pseudo-biomes used for generated specialized terrain 
     * this should contain the real biome in the lower byte and the 
     * id of the variant in the second byte.
     * 
     * The id will be returned as an int.
     * 
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     * @return The biome id for world-gen as an int
     */
    public int getFullBiome(int x, int z) {
        return (data[(x * cWidth) + z]);
    }
    
    
    /**
     * This will set the real and pseudo-biomes to the same value 
     * while assuming no data has been stored (i.e., that the array 
     * is freshly initialized so that the value is zero).
     * 
     * @param biome
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     */
    public void setBiomeExpress(int biome, int i) {
        data[i] = biome;    
    }
    
    
    int[] getData() {
    	return data;
    }
    
    
    public int hashCode() {
    	return getCoords().hashCode();
    }
    
    
    public boolean equals(Object other) {
    	if(other instanceof RegionMap) {
    		return getCoords().equals(((RegionMap)other).getCoords());
    	}
    	return false;
    	
    }
    
    
    /**
     * Returns a hash based on the data array; purely for
     * debugging.
     * 
     * @return
     */
    public int otherHash() {
    	int hash = 0;
    	int count = 0;
    	for(int i = 0; i < data.length; i++) {
    		for(int j = 0; j < 2; j++) {
		    	int longbyte = (data[i] >> (j * 8)) & 0xff;
		    	hash ^= longbyte << (8 * count);
		    	hash ^= hash << 13;
		    	hash ^= hash >> 5;
		    	hash ^= hash << 17;
		    	count = (++count) % 4;
    		}
    	}
    	return hash;
    }
    
    
    /**
     * Returns a hash of the input array, for debugging.
     * 
     * @param in
     * @return
     */
    public static int otherHash(short[] in) {
    	int hash = 0;
    	int count = 0;
    	for(int i = 0; i < in.length; i++) {
    		for(int j = 0; j < 2; j++) {
		    	int longbyte = (in[i] >> (j * 8)) & 0xff;
		    	hash ^= longbyte << (8 * count);
		    	hash ^= hash << 13;
		    	hash ^= hash >> 5;
		    	hash ^= hash << 17;
		    	count = (++count) % 4;
    		}
    	}
    	return hash;
    }
    
    
    
    
}
