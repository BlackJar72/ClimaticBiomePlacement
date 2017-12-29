package jaredbgreat.climaticbiome.generation.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CachedPool<T extends ICachable> {
    private final ObjectStack<T>      stack;
    private final ObjectFactory<T>  factory;
    private ICachable[] data;
    private final int minSize;
    private int capacity;
    private int lowLimit;
    private int length;
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" }) 
    public CachedPool(ObjectFactory<T> fact, int poolSize, int initCacheSize) {
        factory = fact;
        stack = new ObjectStack(poolSize);
        data  = new ICachable[initCacheSize];
        minSize  = initCacheSize;
        capacity = (initCacheSize * 3) / 4;
        lowLimit = ((initCacheSize - minSize) * 3) / 16;
        length = 0;
    }
    
    
    /**
     * The method used to retrieve or create an object (depending on whether an 
     * object is in the pool).
     * 
     * @return 
     */
    public T getEntry(MutableCoords c) {
        T out;
    	if((out = (T)get(c)) != null) {
            return out;
    	} else {
            if(stack.isEmpty()) {
                out = factory.create();
                out.setCached(false);
                return out;
            } else {
                return stack.pop();
            }
    	}
    }
    
    
    /**
     * This will add an entry to the cache.
     * 
     * @param entry 
     */
    public void add(T entry) {
            entry.setCached(true);
            length += bucketItem(entry, data);
            if(length > capacity) {
                grow();
            }        
        
    }
    
    
    /**
     * This will add an entry to the cache while setting its coords.  
     * 
     * This should not be used; set the entry's coords where it is acquired 
     * instead.
     * 
     * @param entry
     * @param c
     * @deprecated
     */
    @Deprecated
    public void add(T entry, MutableCoords c) {
            entry.setCached(true);
            entry.getCoords().init(c.getX(), c.getZ());
            add(entry);
    }
    
    
    /**
     * This will add an entry to the cache while setting its coords.  
     * 
     * This should not be used; set the entry's coords where it is acquired 
     * instead.
     * 
     * @param entry
     * @param c
     * @deprecated
     */
    @Deprecated
    public void add(T entry, int x, int z) {
            entry.setCached(true);
            entry.getCoords().init(x, z);
            add(entry);
    }

    
    /**
     * The method for placed objects in the pool for storage.  This should be 
     * used after the object in question is no longer going to be in use, much 
     * like calling delete on a (non-pooled / non-managed) C++ object.
     * 
     * @param item 
     */
    private void free(ICachable item) {
        stack.push(item);
    }
    
    
    /**
     * Return the element at the given MutableCoords.
     * @param coords
     * @return the object stored for those coordinates, or null.
     */
    private T get(MutableCoords coords) {
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
            	free(item);
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
    
    
    //**********************************************************************************************************/
    //                              Inner Classes barrowed from ObjectPool                                      /
    //**********************************************************************************************************/
    
    /**
     * An interface for creating a factory that is used to create objects when 
     * none are available in the pool.
     * 
     * @param <T> 
     */
    public interface ObjectFactory<T> {
        T create();
    }
    
    
    /**
     * A simple stack structure for storing the pooled objects.
     * 
     * @param <T> 
     */
    private class ObjectStack<T extends ICachable> {
        final Object[] data;
        int pos;
        public ObjectStack(int size) {
            pos = 0;
            data = new Object[size];
        }
        void push(ICachable item) {
            item.setCached(false);
            if(pos < data.length) {
                data[pos++] = item;
            }
        }
        @SuppressWarnings({ "unchecked", "rawtypes" }) 
        T pop() {
            T out = (T)data[--pos];
            data[pos] = null;
            return out;
        }
        boolean isEmpty() {
            return pos == 0;
        }
        boolean isFull() {
            return pos == data.length;
        }
    }
	

}
