import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int size = 0;
    private int capacity;

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[1];
        capacity = 1;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (size == capacity) {
            capacity = capacity * 2;
            resize(capacity);
        }
        array[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        StdRandom.shuffle(array, 0, size);
        Item item = array[--size];
        array[size] = null;
        if (size > 0 && size == capacity / 4) {
            capacity = capacity / 4;
            resize(capacity);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int random = StdRandom.uniform(size);
        return array[random];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private void resize(int cap) {
        Item[] copy = (Item[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            copy[i] = array[i];
        }
        array = copy;
    }

    private class RandomizedIterator implements Iterator<Item> {

        int current = 0;

        public RandomizedIterator() {
            StdRandom.shuffle(array, 0, size);
        }


        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return array[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        System.out.println("hello from the other cat");
    }
}
