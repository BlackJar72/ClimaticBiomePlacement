package jaredbgreat.climaticbiomes.generation.cache;

/**
 *
 * @author Jared Blackburn
 * @param <T> Object type to be held
 */
public class ObjectPool<T> {
    private final ObjectStack<T>   stack;
    private final ObjectFactory<T> factory;


    public interface ObjectFactory<T> {
        T create();
    }


    @SuppressWarnings("hiding")
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


    public T getObject() {
        if(stack.isEmpty()) {
            return factory.create();
        } else {
            return stack.pop();
        }
    }


    public void free(T object) {
        stack.push(object);
    }
}
