import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int counter;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        counter = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return counter == 0;
    }

    // return the number of items on the deque
    public int size() {
        return counter;
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkNull(item);
        counter++;
        Node<Item> newNode = new Node<>(item);
        if (first != null) {
            first.setPrevious(newNode);
            newNode.setPrevious(null);
            newNode.setNext(first);
            first = newNode;
        } else {
            first = newNode;
            last = newNode;
        }
    }

    // add the item to the end
    public void addLast(Item item) {
        checkNull(item);
        counter++;
        Node<Item> newNode = new Node<>(item);
        if (last != null) {
            last.setNext(newNode);
            newNode.setNext(null);
            newNode.setPrevious(last);
            last = newNode;
        } else {
            first = newNode;
            last = newNode;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        whenEmpty();
        counter--;
        Node<Item> toRemove = first;
        if (isEmpty()) {
            first = null;
            last = null;
            return toRemove.data;
        } else {
            first = toRemove.next;
            first.previous = null;
            return toRemove.data;
        }
    }

    // remove and return the item from the end
    public Item removeLast() {
        whenEmpty();
        counter--;
        Node<Item> toRemove = last;
        if (isEmpty()) {
            last = null;
            first = null;
            return toRemove.data;
        } else {
            last = toRemove.previous;
            last.next = null;
            return toRemove.data;
        }
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private void checkNull(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void whenEmpty() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;
        private boolean flag = true;

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public Item next() {
            if (flag && first != null) {
                flag = false;
                return first.data;
            }
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            current = current.getNext();
            return current.data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node<G> {


        private Node<G> previous;
        private Node<G> next;
        private final G data;

        private Node(G data) {
            setNext(null);
            setPrevious(null);
            this.data = data;
        }

        void setPrevious(Node<G> previous) {
            this.previous = previous;
        }

        void setNext(Node<G> next) {
            this.next = next;
        }

        public Node<G> getPrevious() {
            return previous;
        }

        public Node<G> getNext() {
            return next;
        }

    }


    // unit testing (optional)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(7);
        deque.addFirst(5);
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addFirst(0);
        for (int item : deque) {
            System.out.println(1);
        }


    }

}
