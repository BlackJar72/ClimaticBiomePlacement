package jaredbgreat.climaticbiome.generation.cache;


/**
 * This a specialized caching class.  Its basically a re-implemented HashSet 
 * that works with data implementing an ICachable interface to eliminate the 
 * need for container objects.  More importantly, it will use an internal 
 * cleanup method that can remove old cache data without the use of iterators 
 * or an external loop, which can remove its data in one step without the need 
 * for a separate container to hold keys to be remove (i.e., without the 
 * problem of "concurrent modification" of the data being iterated).
 * 
 * @author Jared Blackburn
 */
public class Cache <T extends ICachable> {
    private ICachable[] data;
    private final int minSize;
    private int capacity;
    private int lowLimit;
    private int length;
    
    
    /**
     * Creates a cache with a default starting size elements.
     */
    public Cache(int size) {
        data = new ICachable[size];
        minSize = size;
        capacity = (size * 3) / 4;
        lowLimit = ((size - minSize) * 3) / 16;
        length = 0;
    }
    
    
    /**
     * Creates a cache with a default starting size of 16 elements.
     */
    public Cache() {
        data = new ICachable[16];
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
        item.setCached(true);
        length += bucketItem(item, data);
        if(length > capacity) {
            grow();
        }        
    }
    
    
    /**
     * Return the element at the given MutableCoords.
     * @param coords
     * @return the object stored for those coordinates, or null.
     */
    public T get(MutableCoords coords) {
        T out = (T)data[absMod(coords.hashCode(), data.length)];
        while(out != null && !out.getCoords().equals(coords)) {
            out = (T)out.getNextCacheBucket();
        }
        return out;
    }
    
    
    /**
     * Return the element at the given coordinate values x and z.
     * 
     * @param x
     * @param z
     * @return the object stored for those coordinates, or null.
     */
    public T get(int x, int z) {
        T out = (T)data[absMod(MutableCoords.hashCoords(x, z), data.length)];
        while(out != null && !out.getCoords().equals(x, z)) {
            out = (T)out.getNextCacheBucket();
        }
        return out;
    }
    
    
    /**
     * Will tell if an item for the given coordinates is in the cache.
     * 
     * @param coords
     * @return 
     */
    public boolean contains(MutableCoords coords) {
        ICachable item = data[absMod(coords.hashCode(), data.length)];
        while(item != null) {
            if(item.getCoords().equals(coords)) {
                return true;
            } else {
                item = item.getNextCacheBucket();
            }
        }
        return false;
    }
    
    
    /**
     * Will tell if an item for the given coordinates is in the cache.
     * 
     * @param coords
     * @return 
     */
    public boolean contains(int x, int z) {
        ICachable item = data[absMod(MutableCoords.hashCoords(x, z), data.length)];
        while(item != null) {
            if(item.getCoords().equals(x, z)) {
                return true;
            } else {
                item = item.getNextCacheBucket();
            }
        }
        return false;
    }
    
    
    /**
     * Will tell if an item the same coords is in the cache.
     * 
     * @param T object to check for
     * @return 
     */
    public boolean contains(T in) {
        ICachable item = data[absMod(in.getCoords().hashCode(), data.length)];
        while(item != null) {
            if(item.getCoords().equals(in.getCoords())) {
                return true;
            } else {
                item = item.getNextCacheBucket();
            }
        }
        return false;
    }
    
    
    /**
     * This will grow the data size when needed.
     */
    private void grow() {
        T[] newData = (T[]) new ICachable[(data.length * 3) / 2];
        capacity = (newData.length * 4) / 3;
        lowLimit = ((newData.length - minSize) * 3) / 16;
        for(int i = 0; i < data.length; i++) {
            ICachable item = data[i];
            while(item != null) {
                bucketItem(item, newData);
                item = item.getNextCacheBucket();
            }
        }
        data = newData;
    }
    
    
    /**
     * This will shrink the data size when needed.
     */
    private void shrink() {
        T[] newData = (T[]) new ICachable[data.length / 2];
        capacity = (newData.length * 4) / 3;
        lowLimit = ((newData.length - minSize) * 3) / 16;
        for(int i = 0; i < data.length; i++) {
            ICachable item = data[i];
            while(item != null) {
                bucketItem(item, newData);
                item = item.getNextCacheBucket();
            }
        }
        data = newData;
    }
    
    
    /**
     * This will place and item in the cache by locating the correct cache 
     * bucket and (if necessary due to hash collision) moving it to the end 
     * of a linked list of bucketed items.
     * 
     * This method is used both by add(T) and in internal housekeeping methods.
     * 
     * (Note that another implementation has been considered and may someday 
     *  be tried.  However, the interface should remain the same.)
     * 
     * @param item 
     */
    private int bucketItem(ICachable item, ICachable[] dat) {
        int bucket = absMod(item.getCoords().hashCode(), dat.length);
        ICachable element = dat[bucket];
        if(element == null) {
            dat[bucket] = item;
            return 1;
        } else {
            ICachable next = element.getNextCacheBucket();
            while(next != null) {
                // Do not cache the same data twice
                if(element.getCoords().equals(item.getCoords())) {
                    return 0;
                }
                element = next;
                next = element.getNextCacheBucket();
            }
            element.setNextCacheBucket(item);
            return 1;
        }
    }
    
    
    /**
     * This will iterate the cache items and remove any that identify 
     * themselves as isOld().  Usually this should mean removing items from 
     * the cache that haven't been used in a set amount of time (most often 
     * 30 seconds), though other criteria for isOld() could be created.
     */
    public void cleanup() {
        ICachable item;
        for(int i = 0; i < data.length; i++) {
            item = data[i];
            // Handle the first item in a bucket
            while((item != null) && item.isOld()) {
                item = data[i] = item.getNextCacheBucket();
                length--;
            }
            // Handle any other items in that bucket
            if(item != null) {
                ICachable next = item.getNextCacheBucket();
                while(next != null) {
                    if(next.isOld()) {
                        next = next.getNextCacheBucket();
                        item.setNextCacheBucket(next);
                        length--;
                    } else {
                        item = next;
                        next = item.getNextCacheBucket();
                    }
                }
            }
        }
        if(length < lowLimit) {
            shrink();
        }
    }
        
        
    private int absMod(int n, int m) {
        int out = n % m;
        if(out < 0) {
            out += m;
        }
        return out;
    }
    
}
