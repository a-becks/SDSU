//package data_structures;

import java.util.Iterator;

public class Queue<E extends Comparable<E>> implements Iterable<E> {
    private UnorderedListADT<E> list;

    public Queue() {
        list = new LinkedList<E>();
    }
    public void enqueue(E obj) {
        list.addLast(obj);
    }
    public E dequeue() {return list.removeFirst();}

    public int size() {return list.size();}

    public boolean isEmpty() {return list.isEmpty();}

    public boolean isFull() {return list.isFull();}

    public E peek() {
        return list.get(1);
    }

    public boolean contains(E obj) {
        return(list.contains(obj));
    }
    public void makeEmpty() {
        list.clear();
    }
    public Iterator<E> iterator() {return list.iterator();}
}