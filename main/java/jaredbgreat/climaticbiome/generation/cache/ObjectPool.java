// Copyright (c) Jared Blackburn 2017
// It is licensed under the creative commons 4.0 attribution license:
// https://creativecommons.org/licenses/by/4.0/legalcode 
package jaredbgreat.climaticbiome.generation.cache;

/**
 * A class for creating pools of objects, and primarily for avoiding 
 * garbage collection be recycling memory through object re-use.
 * 
 * @author Jared Blackburn
 * @param <T> Object type to be held
 */
public class ObjectPool<T> {
    private final ObjectStack<T>   stack;
    private final ObjectFactory<T> factory;
    
    
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
    private class ObjectStack<T> {
        final Object[] data;
        int pos;
        public ObjectStack(int size) {
            pos = 0;
            data = new Object[size];
        }
        void push(T in) {
            if(pos < data.length) {
                data[pos++] = in;
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
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" }) 
    public ObjectPool(ObjectFactory<T> fact, int maxSize) {
        factory = fact;
        stack = new ObjectStack(maxSize);        
    }
    
    
    /**
     * The method used to retrieve or create an object (depending on whether an 
     * object is in the pool).
     * 
     * @return 
     */
    public T getObject() {
        if(stack.isEmpty()) {
            return factory.create();
        } else {
            return stack.pop();
        }
    }
    
    
    /**
     * The method for placed objects in the pool for storage.  This should be 
     * used after the object in question is no longer going to be in use, much 
     * like calling delete on a (non-pooled / non-managed) C++ object.
     * 
     * @param object 
     */
    public void free(T object) {
        stack.push(object);
    }
}
