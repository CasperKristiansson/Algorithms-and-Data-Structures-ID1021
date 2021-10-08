import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Bag implements a collection of elements which follows the Bag algorithm.
 * The bag contains items of the generic datatype <Item>.
 */
public class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;

    /**
     * Constructs a empty bag.
     */
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty bag.
     */
    public Bag() {
        first = null;
        n = 0;
    }

    /**
     * Checks if the bag is empty.
     * 
     * @return true if the bag is empty, false otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the size of the bag
     * 
     * @return the size of the bag
     */
    public int size() {
        return n;
    }

    /**
     * Adds an item to the bag.
     * 
     * @param item
     */
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    /**
     * Returns an iterator for the bag.
     */
    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);  
    }

    /**
     * Iterator class for the bag.
     */
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        /**
         * Constructs an iterator for the bag.
         * 
         * @param first
         */
        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        /**
         * Checks if there is a next item.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next item.
         */
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}
