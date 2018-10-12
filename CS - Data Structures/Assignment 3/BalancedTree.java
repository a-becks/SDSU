import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by Allison on 8/7/2017.
 */
public class BalancedTree<K extends Comparable<K>,V> implements DictionaryADT<K,V> {

    private TreeMap<K,V> map;

    public BalancedTree(){
        map = new TreeMap<>();
    }

    @Override
    public boolean contains(K key) {
        return map.containsKey(key);
    }

    @Override
    public boolean insert(K key, V value) {
        if(contains(key) || isFull()) return false;
        map.put(key, value);
        return true;
    }

    @Override
    public boolean remove(K key) {
        V returnedValue = map.remove(key);
        if (returnedValue == null) return false;
        return true;
    }

    @Override
    public V getValue(K key) {
        return map.get(key);
    }

    @Override
    public K getKey(V value) {
        Iterator<K> iter = keys();
        while(iter.hasNext()){
            K currentKey = iter.next();
            if (((Comparable<V>)value).compareTo(getValue(currentKey)) == 0)
                return currentKey;
        }
        return null;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (map.size() == 0);
    }

    @Override
    public void clear() {
        map.clear();
    }


    @Override
    public Iterator<K> keys() {
        return map.keySet().iterator();
    }

    @Override
    public Iterator<V> values() {
        return map.values().iterator();
    }
}
