import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E extends Comparable<E>> implements UnorderedListADT<E> {

    class Node<T>{
        T data;
        Node<T> next;

        public Node(T obj){
            data = obj;
            next = null;
        }
    }

    private Node<E> head, tail;
    private int size;

    private boolean isValidInsert(int position){
        return (position <= size + 1 && position > 0);
    }

    private boolean isValidRemove(int position){
        return (position <= size && position > 0);
    }

    public LinkedList(){
        head = tail = null;
        size = 0;

    }
    @Override
    public void addFirst(E obj) {
        Node<E> newNode = new Node<E>(obj);

        newNode.next = head;
        head = newNode;
        if (tail == null)
            tail = newNode;
        size ++;
        return;
    }

    @Override
    public void addLast(E obj) {
        Node<E> newNode = new Node<E>(obj);
        if (head == null)
            head = newNode;
        else
            tail.next = newNode;
        tail = newNode;
        size++;
        return;
    }

    @Override
    public void add(E obj, int position) {
        if(!isValidInsert(position))
            throw new RuntimeException("Invalid insert position.");

        Node<E> newNode = new Node<E>(obj);
        Node<E> previous = null, current = head;

        if(position == 1) {
            addFirst(obj);
            return;
        }
        else if (position == size + 1) {
            addLast(obj);
            return;
        }
        else {
            for(int i = 1; i < position; i ++){
                previous = current;
                current = current.next;
            }
            previous.next = newNode;
            newNode.next = current;
        }
        size ++;
        return;
    }

    @Override
    public E remove(int position) {
        if(!isValidRemove(position))
            throw new RuntimeException("Not a valid remove position.");
        if (position == 1)
            return removeFirst();
        else if (position == size){
            return removeLast();
        }

        Node<E> previous = null, current = head;
        for(int i = 1; i < position; i ++){
            previous = current;
            current = current.next;
        }
        previous.next = current.next;
        size--;
        return current.data;
    }

    @Override
    public E remove(E obj) {
        Node<E> previous = null, current = head;
        while(current != null && obj.compareTo(current.data)!= 0){
            previous = current;
            current = current.next;
        }
        if (current == null) return null;
        if (head == tail)
            head = tail = null;
        else if (previous == null)
            head = head.next;
        else if (current == tail) {
            tail = previous;
            previous.next = current.next;
        }
        else
            previous.next = current.next;
        size--;
        return current.data;
    }

    @Override
    public E removeFirst() {
        if (head == null) return null;

        Node<E> temp = head;
        if (head == tail)
            head = tail = null;
        else
            head = head.next;

        size--;
        return temp.data;
    }

    @Override
    public E removeLast() {
        if (head == null) return null;

        Node<E> previous = null, current = head;
        while(current != tail){
            previous = current;
            current = current.next;
        }
        if (head == tail)
            head = tail = null;
        else{
            tail = previous;
            previous.next = null;
        }
        size--;
        return current.data;
    }

    @Override
    public E get(int position) {
        if(!isValidRemove(position))
            throw new RuntimeException("Position does not exist");
        Node<E> current = head;
        for(int i = 1; i < position; i ++){
            current = current.next;
        }
        return current.data;
    }

    @Override
    public E get(E obj) {
        Node<E> current = head;
        while(current != null && obj.compareTo(current.data) != 0){
            current = current.next;
        }
        if (current == null) return null;
        return current.data;
    }

    @Override
    public int find(E obj) {
        Node<E> current = head;
        int position = 1;
        while(current != null && obj.compareTo(current.data) != 0) {
            current = current.next;
            position++;
        }
        if (current == null) return -1;
        return position;
    }

    @Override
    public boolean contains(E obj) {
        return (find(obj) != -1);
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorHelper(head);
    }

    private class IteratorHelper implements Iterator<E> {
        private Node<E> current;

        public IteratorHelper(Node headNode) {
            current = headNode;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            Node<E> temp;
            if(!hasNext()){
                throw new NoSuchElementException("Cannot iterate to position.");
            }
            else {
                temp = current;
                current = current.next;
            }
            return temp.data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
