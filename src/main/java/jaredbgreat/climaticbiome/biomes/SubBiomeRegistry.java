package jaredbgreat.climaticbiome.biomes;

import java.util.logging.Logger;

import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.generation.cache.ICachable;

public class SubBiomeRegistry {
	private static SubBiomeRegistry subreg;
    private SubBiome[] data;
    private final int minSize;
    private int capacity;
    private int lowLimit;
    private int length;
    
    
    /**
     * Creates a cache with a default starting size of 16 elements.
     */
    private SubBiomeRegistry() {
        data = new SubBiome[384];
        minSize = 384;
        capacity = 256;
        lowLimit = 0;
        length = 0;
    }
    
    
    // Ugghhh... another singleton -- I should find a better way....
    public static SubBiomeRegistry getSubBiomeRegistry() {
    	if(subreg == null) {
    		subreg = new SubBiomeRegistry();
    	}
    	return subreg;
    }
    
    
    private int xorshift(int in) {
    	in ^= in << 11;
    	in ^= in >> 5;
    	in ^= in << 17;
    	in ^= in >> 13;
    	in &= 0x7fffffff;
    	return in;
    }
    
    
    private int xorshift(long in) {
    	in ^= in << 11;
    	in ^= in >> 5;
    	in ^= in << 17;
    	in ^= in >> 13;
    	in &= 0x7fffffff;
    	return (int)(in & 0xffffffffL);
    }
    
    
    /**
     * At a new element to the cache.
     * 
     * @param item the object to be added.
     */
    public void add(SubBiome item) {
    	//System.out.println("Adding Pseudo-Biome with id " + item.getSubId());
        int bucket = (xorshift(item.getSubId())) % data.length;
        int offset = 0;
        while(offset < data.length) {
            int slot = (bucket + offset) % data.length;
            if(data[slot] == null) {
                data[slot] = item;
                if(++length > capacity) {
                    grow();
                }
                return;
            } else if(data[slot].getSubId() == item.getSubId()) {
                return;
            }else {
                offset++;
            }
        }
    }
    
    
    public SubBiome get(int id) {
    	return get(expandID(id));
    }
    
    
    public SubBiome get(long id) {
        int bucket = (xorshift(id)) % data.length;
        int offset = 0;
        while(offset < data.length) {
            int slot = (bucket + offset) % data.length;
            if(data[slot] == null) {
            	showError((int)id);
                return null;
            } else if(data[slot].getSubId() == id) {
                return data[slot];
            } else {
                offset++;
            }
        } 
    	showError((int)id);       
        return null;
    }
    
    
    private long expandID(long id) {
    	return (id & 0xff) + ((id & 0xff00) << 24);
    }
    
    
    
    private void showError(long id) {
    	if(ConfigHandler.badBiomeSpam) {
	    	System.err.println();
	    	System.err.println("*******************************************");
	    	System.err.println("Returning NULL for subbiome id " + id);
	    	System.err.println("Subbiome " + id + " must be registeerd!" );
	    	System.err.println("*******************************************");
	    	System.err.println();    	
    	}
    }
    
    
    /**
     * Will tell if an item for the given id is in the cache.
     * 
     * @param coords
     * @return 
     */
    public boolean contains(int id) {
        int bucket = (xorshift(id)) % data.length;
        int offset = 0;
        while(offset < data.length) {
            int slot = (bucket + offset) % data.length;
            if(data[slot] == null) {
                return false;
            } else if(data[slot].getSubId() == id) {
                return true;
            } else {
                offset++;
            }
        }        
        return false;
    }
    
    
    /**
     * This will grow the data size when needed.
     */
    private void grow() {
        SubBiome[] old = data;
        SubBiome[] data = new SubBiome[(old.length * 3) / 2];
        for(int i = 0; i < old.length; i++) {
            if(old[i] != null) {
                rebucket(old[i]);
            }
        }
        capacity = (data.length * 3) / 4;
        lowLimit = ((data.length - minSize) * 3) / 16;
    }
    
    
    /**
     * This will shrink the data size when needed.
     */
    private void shrink() {
        SubBiome[] old = data;
        SubBiome[] data = new SubBiome[old.length / 2];
        for(int i = 0; i < old.length; i++) {
            if(old[i] != null) {
                rebucket(old[i]);
            }
        }
        capacity = (data.length * 3) / 4;
        lowLimit = ((data.length - minSize) * 3) / 16;
    }
    
    
    private void rebucket(SubBiome item) {
        int bucket = (xorshift(item.getSubId())) % data.length;
        int offset = 0;
        while(offset <= data.length) {
            int slot = (bucket + offset) % data.length;
            if((data[slot] == null) || (data[slot].equals(item))) {
                data[slot] = item;
                return;
            }else {
                offset++;
            }
        }
    } 
}
