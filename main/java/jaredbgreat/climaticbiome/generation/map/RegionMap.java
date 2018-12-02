package jaredbgreat.climaticbiome.generation.map;

import jaredbgreat.climaticbiome.generation.cache.AbstractCachable;
import jaredbgreat.climaticbiome.generation.generator.MapMaker;

public class RegionMap extends AbstractCachable {
    MapMaker maker;
    final int[] data = new int[65536];
    
    static int n = 0;
    
    public RegionMap(int x, int z) {
        super(x, z);
        n++;
        //System.out.println("Creating map " + x + ", " + z + "; there are " + n + " maps.");
    }
    
    
    public void finalize() throws Throwable {
    	n--;
    	super.finalize();
    }
    
    /**
     * Gets the actual in game biome id.
     * 
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     * @return The biome as a single byte.
     */
    public byte getBiomeAsByte(int x, int z) {
        return (byte)(data[(x * 256) + z] & 0xff);
    }
    
    
    /**
     * Returns in game biome id.
     * 
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     * @return The biome id as in int
     */
    public int getBiome(int x, int z) {
        return (data[(x * 256) + z] & 0xff);
    }
    
    
    /**
     * Returns the and id for the biome to be for world gen.  For 
     * registered biomes this should be there real id, and less than 
     * 256.  For pseudo-biomes used for generated specialized terrain 
     * this should be between 356 and 32767 and indexed to a specialized 
     * registry map (probably actually an ArrayList).
     * 
     * The id will be returned as a short.
     * 
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     * @return The biome id for world-gen as a short
     */
    public short getPseudoBiomeAsShort(int x, int z) {
        return (short)((data[(x * 256) + z] & 0xffff00) >> 8);
    }
    
    
    
    
    /**
     * Returns the and id for the biome to be for world gen.  For 
     * registered biomes this should be there real id, and less than 
     * 256.  For pseudo-biomes used for generated specialized terrain 
     * this should be between 356 and 32767 and indexed to a specialized 
     * registry map (probably actually an ArrayList).
     * 
     * The id will be returned as an int.
     * 
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     * @return The biome id for world-gen as an int
     */
    public int getPseudoBiome(int x, int z) {
        return (data[(x * 256) + z] & 0xffff00) >> 8;
    }
    
    
    /**
     * This sets the biome data using a byte.
     * 
     * @param biome
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     */
    public void setBiome(byte biome, int x, int z) {
        data[(x * 256) + z] &= 0xffffff00;
        data[(x * 256) + z] |= biome;
    }
    
    
    /**
     * This sets the biome data using an int.
     * 
     * @param biome
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     */
    public void setBiome(int biome, int x, int z) {
        data[(x * 256) + z] &= 0xffffff00;
        data[(x * 256) + z] |= (biome & 0xff);
    }
    
    
    /**
     * This set the pseudo biome used by world gen.  This will 
     * most often be the same as the real biome, but may not if 
     * if specialized terrain generation is desired.
     * 
     * @param biome
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     */
    public void setPseudoBiome(int biome, int x, int z) {
        data[(x * 256) + z] &= 0xff0000ff;
        data[(x * 256) + z] |= ((biome & 0xffff) << 8);
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
    public void setBiomeExpress(int biome, int x, int z) {
        data[(x * 256) + z] |= biome;
        data[(x * 256) + z] |= (biome << 8);        
    }
    
    
    /**
     * This sets the biome data using a byte.
     * 
     * @param biome
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     */
    public void setBiome(byte biome, int i) {
        data[i] &= 0xffffff00;
        data[i] |= biome;
    }
    
    
    /**
     * This sets the biome data using an int.
     * 
     * @param biome
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     */
    public void setBiome(int biome, int i) {
        data[i] &= 0xffffff00;
        data[i] |= (biome & 0xff);
    }
    
    
    /**
     * This set the pseudo biome used by world gen.  This will 
     * most often be the same as the real biome, but may not if 
     * if specialized terrain generation is desired.
     * 
     * @param biome
     * @param x relative chunk x within region
     * @param z relative chunk x within region
     */
    public void setPseudoBiome(int biome, int i) {
        data[i] &= 0xff0000ff;
        data[i] |= ((biome & 0xffff) << 8);
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
        data[i] |= biome;
        data[i] |= (biome << 8);        
    }
    
    
    
    
    
    
}
