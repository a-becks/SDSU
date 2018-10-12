import java.security.Key;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Allison on 8/7/2017.
 */
public class Hashtable<K extends Comparable<K>,V> implements DictionaryADT<K,V>{

    private int tableSize, maxSize, currentSize, modCounter;
    private LinkedList<Wrapper<K,V>>[] list;

    private class Wrapper<K extends Comparable<K>,V> implements Comparable<Wrapper<K, V>>{
        K key;
        V value;

        public Wrapper(K k, V v){
            key = k;
            value = v;
        }

        @Override
        public int compareTo(Wrapper<K, V> w) {
            return key.compareTo(w.key);
        }
    }

    public Hashtable(int max){
        maxSize = max;
        tableSize = (int)(maxSize * 1.3f);
        currentSize = 0;
        modCounter = 0;
        list = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i ++){
            list[i] = new LinkedList<Wrapper<K,V>>();
        }
    }

    @Override
    public boolean contains(K key) {
        return list[getIndex(key)].contains(new Wrapper<K,V>(key, null));
    }

    @Override
    public boolean insert(K key,V value) {
        if (contains(key) || isFull()) return false;
        list[getIndex(key)].addLast(new Wrapper<K,V>(key, value));
        currentSize++;
        modCounter++;
        return true;
    }

    @Override
    public boolean remove(K key) {
        Wrapper<K,V> returnedWrapper = list[getIndex(key)].remove(new Wrapper<>(key, null));
        if (returnedWrapper == null) return false;
        currentSize--;
        modCounter++;
        return true;
    }

    @Override
    public V getValue(K key) {
        Wrapper<K,V> temp = list[getIndex(key)].get(new Wrapper<>(key, null));
        if (temp == null) return null;
        return temp.value;
    }

    @Override
    public K getKey(V value) {
        for (int i = 0; i < tableSize; i ++)
            for(Wrapper<K,V> w: list[i])
                if(((Comparable<V>)value).compareTo(w.value) == 0)
                    return w.key;
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isFull() {
        return (currentSize == maxSize);
    }

    @Override
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    @Override
    public void clear() {
        for(int i = 0; i < tableSize; i++)
            list[i].clear();
        currentSize = 0;
        modCounter = 0;
    }

    @Override
    public Iterator keys() {
        return new KeyIteratorHelper<>();
    }

    @Override
    public Iterator values() {
        return new ValueIteratorHelper<>();
    }

    abstract class IteratorHelper<E> implements Iterator<E>{
        protected Wrapper<K,V>[] nodes;
        protected int index;

        public IteratorHelper(){
            nodes = new Wrapper[currentSize];
            index = 0;
            for (int i = 0; i < tableSize; i ++)
                for (Wrapper<K,V> w: list[i])
                    nodes[index++] = w;
            index = 0;
            sort();
        }

        @Override
        public abstract E next();

        @Override
        public boolean hasNext(){
            return index < currentSize;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void sort(){
            Wrapper<K,V> temp;
            int in, out, h = 1;
            int size = nodes.length;

            while(h<= size/3)
                h = h*3 + 1;
            while (h > 0) {
                for (out = h; out < size; out++) {
                    temp = nodes[out];
                    in = out;
                    while (in > h - 1 && nodes[in - h].compareTo(temp) >= 0) {
                        nodes[in] = nodes[in - h];
                        in -= h;
                    }
                    nodes[in] = temp;
                }
                h = (h - 1) / 3;
            }
        }
    }

    private class KeyIteratorHelper<K> extends IteratorHelper<K>{
        public KeyIteratorHelper(){
            super();
        }

        @Override
        public K next() {
            if (!hasNext()) throw new NoSuchElementException("Cannot continue iterating.");
            return (K)nodes[index++].key;
        }
    }

    private class ValueIteratorHelper<V> extends IteratorHelper<V>{
        public ValueIteratorHelper(){
            super();
        }

        public V next(){
            if (!hasNext()) throw new NoSuchElementException("Cannot continue iterating.");
            return (V)nodes[index++].value;
        }
    }
    private int getIndex(K key){
        return key.hashCode() & 0x7FFFFFF % tableSize;
    }
}
