package jaredbgreat.climaticbiome.generation.cache;



/**
 * A cache system using a homebrewed hash map.  The reason for not using 
 * java.util.HashMap is that for this use case and the processing needed 
 * for the intended caching function would actually be far more complex 
 * in terms of both code and processing than this, due unusual requirements 
 * of the caching.
 * 
 * This does not include a linked list, but instead skips until finding a 
 * place to put items if the correct spot is taken by a hash collision.
 * 
 * @author Jared Blackburn
 * @param <T>
 */
public class Cache <T extends ICachable> {
    private T[] data;
    private final int minSize;
    private int capacity;
    private int lowLimit;
    private int length;
    
    
    /**
     * Creates a cache with a default starting size elements.
     */
    public Cache(int size) {
        data = (T[]) new ICachable[size];
        minSize = size;
        capacity = (size * 3) / 4;
        lowLimit = ((size - minSize) * 3) / 16;
        length = 0;
    }
    
    
    /**
     * Creates a cache with a default starting size of 16 elements.
     */
    public Cache() {
        data = (T[]) new ICachable[16];
        minSize = 16;
        capacity = 12;
        lowLimit = 0;
        length = 0;
    }
    
    
    /**
     * At a new element to the cache.
     * 
     * @param item the object to be added.
     */
    public void add(T item) {
        int bucket = (item.getCoords().hashCode() & 0x7fffffff) % data.length;
        int offset = 0;
        while(offset < data.length) {
            int slot = (bucket + offset) % data.length;
            if(data[slot] == null) {
                data[slot] = item;
                data[slot].use();
            	//System.out.println("**ADDING item  " + item + " to cache**");
                if(++length > capacity) {
                    grow();
                }
                return;
            } else if(data[slot].equals(item)) {
                data[slot].use();
                return;
            }else {
                offset++;
            }
        }
    }
    
    
    /**
     * Return the element at the given Coords.
     * @param coords
     * @return the object stored for those coordinates, or null.
     */
    public T get(Coords coords) {
        int bucket = (coords.hashCode() & 0x7fffffff) % data.length;
        int offset = 0;
        while(offset < data.length) {
            int slot = (bucket + offset) % data.length;
            if(data[slot] == null) {
                return null;
            } else if(data[slot].getCoords().equals(coords)) {
                data[slot].use();
                return (T)data[slot];
            } else {
                offset++;
            }
        }        
        return null;
    }
    
    
    /**
     * Return the element at the given Coords.
     * @param coords
     * @return the object stored for those coordinates, or null.
     */
    public T get(MutableCoords coords) {
        int bucket = (coords.hashCode() & 0x7fffffff) % data.length;
        int offset = 0;
        while(offset < data.length) {
            int slot = (bucket + offset) % data.length;
            if(data[slot] == null) {
                return null;
            } else if(data[slot].getCoords().equals(coords)) {
                data[slot].use();
                return (T)data[slot];
            } else {
                offset++;
            }
        }        
        return null;
    }
    
    
    /**
     * Return the element at the given coordinate values x and z.
     * 
     * @param x
     * @param z
     * @return the object stored for those coordinates, or null.
     */
    public T get(int x, int z) {
        int bucket = (Coords.hashCoords(x, z) & 0x7fffffff) % data.length;
        int offset = 0;
        while(offset < data.length) {
            int slot = (bucket + offset) % data.length;
            if(data[slot] == null) {
                return null;
            } else if(data[slot].getCoords().equals(x, z)) {
                data[slot].use();
                return (T)data[slot];
            } else {
                offset++;
            }
        }        
        return null;
    }
    
    
    /**
     * Will tell if an item for the given coordinates is in the cache.
     * 
     * @param coords
     * @return 
     */
    public boolean contains(Coords coords) {
        int bucket = (coords.hashCode() & 0x7fffffff) % data.length;
        int offset = 0;
        while(offset < data.length) {
            int slot = (bucket + offset) % data.length;
            if(data[slot] == null) {
                return false;
            } else if(data[slot].getCoords().equals(coords)) {
                return true;
            } else {
                offset++;
            }
        }        
        return false;
    }
    
    
    /**
     * Will tell if an item for the given coordinates is in the cache.
     * 
     * @param x
     * @param z
     * @return 
     */
    public boolean contains(int x, int z) {
        int bucket = (Coords.hashCoords(x, z) & 0x7fffffff) % data.length;
        int offset = 0;
        while(offset < data.length) {
            int slot = (bucket + offset) % data.length;
            if(data[slot] == null) {
                return false;
            } else if(data[slot].getCoords().equals(x, z)) {
                return true;
            } else {
                offset++;
            }
        }        
        return false;
    }
    
    
    /**
     * Will tell if an item the same coords is in the cache.
     * 
     * @param in
     * @return 
     */
    public boolean contains(T in) {
        Coords coords = in.getCoords();
        int bucket = (coords.hashCode() & 0x7fffffff) % data.length;
        int offset = 0;
        while(offset <= data.length) {
            int slot = (bucket + offset) % data.length;
            if(data[slot] == null) {
                return false;
            } else if(data[slot].getCoords().equals(coords)) {
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
        T[] old = data;
        data = (T[]) new ICachable[(old.length * 3) / 2];
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
        T[] old = data;
        data = (T[]) new ICachable[Math.max(old.length / 2, minSize)];
        for(int i = 0; i < old.length; i++) {
            if(old[i] != null) {
                rebucket(old[i]);
            }
        }
        capacity = (data.length * 3) / 4;
        lowLimit = ((data.length - minSize) * 3) / 16;
    }
    
    
    private void rebucket(T item) {
        int bucket = (item.getCoords().hashCode() & 0x7fffffff) % data.length;
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
    
    
    /**
     * This will iterate the cache items and remove any that identify 
 themselves as isOldData().  Usually this should mean removing items from 
 the cache that haven't been used in a set amount of time (most often 
 30 seconds), though other criteria for isOldData() could be created.
     */
    public void cleanup() {
    	int startSize = data.length;
        for(int i = 0; i < data.length; i++) {
            if((data[i] != null) && (data[i].isOldData())) {
            	//System.out.println("**Removing item from cache**");
                data[i] = null;
                length--;
            }
        }
        if(length < lowLimit) {
            shrink();
        } else if(data.length != startSize) {
        	T[] old = data;
        	data = (T[]) new ICachable[data.length];
        	for(int i = 0; i < length; i++) {
        		if(old[i] != null) {
        			rebucket(old[i]);
        		}
        	}
        }
    } 
}
