/*
Allison Schroder
CS310 Summer 2017
cssc0140@edoras.sdsu.edu
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedVector<E> implements UnorderedListADT<E> {
    private int INITIAL_SIZE = 1000;
    private E[] array;
    private int currentSize, maxSize;

    //Constructor
    public UnorderedVector() {
        array = (E[]) new Object[INITIAL_SIZE];
        currentSize = 0;
        maxSize = INITIAL_SIZE;
    }

    //Private helper methods///////////////////////////////

    private void shiftUp(int index) {
        if (currentSize == maxSize) {
            array = resize();
        }
        for (int i = currentSize; i > index; i--) {
            array[i] = array[i - 1];
        }
    }

    private boolean isValidInsert(int index) {
        return (index <= currentSize && index >= 0);
    }

    private boolean isValidRemove(int index) {
        return (index < currentSize && index >= 0);
    }

    private E[] resize() {
        maxSize <<= 1;
        E[] tempArray = (E[]) new Object[maxSize];
        for (int i = 0; i < currentSize; i++)
            tempArray[i] = array[i];
        return tempArray;
    }

    //public methods from UnorderedListADT///////////////////////////

    public void addFirst(E obj) {
        add(obj, 1);
    }

    public void addLast(E obj) {
        add(obj, currentSize + 1);
    }

    public void add(E obj, int position) {
        int index = position - 1;
        if (!isValidInsert(index))
            throw new RuntimeException("Invalid insert position.");
        else {
            if (currentSize != 0) {
                shiftUp(index);
            }
            array[index] = obj;
            currentSize++;
        }
    }

    public E remove(int position) {
        int index = position - 1;
        E temp;
        if (!isValidRemove(index))
            throw new RuntimeException("Invalid remove position.");
        else {
            temp = array[index];
            for (int i = index; i < this.currentSize - 1; i++) {
                array[i] = array[i + 1];
            }
            currentSize--;
        }
        return temp;
    }

    public E remove(E obj) {
        int position;
        position = find(obj);
        if (position != -1){
            return remove(position);
        }
        return null;
    }

    public E removeFirst() {
        return (remove(1));
    }

    public E removeLast() {
        return (remove(currentSize));
    }

    public E get(int position) {
        int index = position - 1;
        if (!isValidRemove(index)){
            throw new RuntimeException("Input does not match valid position in list.");
        }
        else{
           return array[index];
        }
    }

    public E get(E obj) {
        int position, index;
        position = find(obj);
        if (position != -1) {
            index = position - 1;
            return array[index];
        }
        return null;
    }

    public int find(E obj) {
        for (int i = 0; i < currentSize; i++) {
            if (((Comparable<E>) obj).compareTo(array[i]) == 0)
                return i + 1;
        }
        return -1;
    }

    public boolean contains(E obj) {
        //find method returns -1 if object is not found in array
        return (find(obj) != -1);
    }

    public void clear() {
        currentSize = 0;
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public boolean isFull() {
        return false;
    }

    public int size() {
        return currentSize;
    }

    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    private class IteratorHelper implements Iterator<E> {
        private int index;

        public IteratorHelper() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return (index < currentSize);
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException("Cannot iterate to position.");
            }
            else {
                return array[index++];
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
