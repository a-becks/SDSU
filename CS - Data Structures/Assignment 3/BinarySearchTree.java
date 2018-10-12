import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Allison on 8/7/2017.
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements DictionaryADT<K ,V> {

    private int currentSize, modSize;
    private Node<K,V> root;

    private class Node<K, V>{
        K key;
        V value;
        Node<K,V> left, right;

        public Node(K k, V v){
            key = k;
            value = v;
            left = right = null;
        }
    }

    public BinarySearchTree(){
        currentSize = 0;
        modSize = 0;
        root = null;
    }

    public void print(){
        print(root);
    }

    private void print(Node n){
        if (n == null) return;
        print(n.left);
        System.out.println(n.key);
        print(n.right);

    }
    @Override
    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node<K,V> n, K key){
        if (n == null) return false;
        if (key.compareTo(n.key) == 0) return true;
        else if (key.compareTo(n.key) < 0)
            return contains(n.left, key);
        else
            return contains(n.right, key);
    }

    @Override
    public boolean insert(K key, V value) {
        boolean success = true;
        if (root == null)
            root = new Node<K,V>(key, value);
        else{
            success = insert(key, value, root, null, false);
        }
        if (success == true){
            currentSize ++;
            modSize++;
        }
        return success;
    }

    private boolean insert(K key, V value, Node<K,V> someNode, Node<K,V> parent, boolean wentLeft ){
        if (someNode == null) {
            if (wentLeft)
                parent.left = new Node<K, V>(key, value);
            else
                parent.right = new Node<K, V>(key, value);
            return true;
        }
        if (someNode.key.compareTo(key) == 0)
            return false;
        else if (key.compareTo(someNode.key) < 0)
            return insert(key, value, someNode.left, someNode, true);
        return insert(key, value, someNode.right, someNode, false);
    }

    @Override
    public boolean remove(K key) {
        if (root == null) return false;
        Node<K,V> parent = null, current = root;
        boolean wentLeft = false;
        while (current.key.compareTo(key) !=0){
            parent = current;
            if (current.key.compareTo(key) < 0) {
                current = current.left;
                wentLeft = true;
            }
            else {
                current = current.right;
                wentLeft = false;
            }
        }
        if (current == null) return false;
        else if (current.left == null & current.right == null) {  //zero children
            if (current == root)
                root = null;
            //leaf nodes
            else if (wentLeft)
                parent.left = null;
            else
                parent.right = null;
        }
    } else if (current.left == null) {  //one right child
        if (current == root)
            root = current.right;
        else if (wentLeft)
                parent.left = current.right;
            else
                parent.right = current.right;
        }
    } else if (current.right == null) {  //one left child
        if (current == root)
            root = current.left;
        else if (wentLeft)
            parent.left = current.left;
        else
            parent.right = current.left;
        else { //two children
            if (wentLeft)
                parent.left = getSuccessor(current);
            else
                parent.right = getSuccessor(current);
        }
        return true;
        //deal with root = if root has 2 children, there isno parent.
    }

    private Node<K,V> getSuccessor(Node<K,V> n){
        Node<K,V> parent = null;
        n = n.right;
        while (n.left !=null){
            parent = n;
            n = n.left;
        }
        if (parent != null) //if right child of node to delete has left children
            parent.left = n.right;
        return n;
    }

    @Override
    public V getValue(K key) {
        return valueSearch(root, key);
    }

    private V valueSearch(Node<K,V> n, K key){
        if (n == null) return null;
        if (key.compareTo(n.key) < 0)
            return valueSearch(n.left, key);
        else if (key.compareTo(n.key) > 0)
            return valueSearch(n.right, key);
        else
            return (V)n.value;
    }

    @Override
    public K getKey(V value) {
        Iterator keyIter = keys();
        Iterator valueIter = values();
        while (keyIter.hasNext()) {
            K key = (K) keyIter.next();
            if (((Comparable<V>) value).compareTo((V)valueIter.next()) == 0) {
                return key;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    @Override
    public void clear() {
        root = null;
        currentSize = 0;
        modSize = 0;
    }


    abstract class IteratorHelper<E> implements Iterator<E> {
        protected Node<K, V>[] nodes;
        protected int index;

        public IteratorHelper() {
            nodes = new Node[currentSize];
            index = 0;
            getNode(root);
            index = 0;
        }

        private void getNode(Node<K,V> n){
            if (n == null) return;
            getNode(n.left);
            nodes[index++] = n;
            getNode(n.right);
        }

        @Override
        public abstract E next();

        @Override
        public boolean hasNext() {
            return index < currentSize;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
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

    @Override
    public Iterator<K> keys() {
        return new KeyIteratorHelper<>();
    }

    @Override
    public Iterator<V> values() {
        return new ValueIteratorHelper<>();
    }
}
