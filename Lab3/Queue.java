import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * The class Queue represents a modified queue of objects.
 * The class is generic, which means that it can store objects of any type.
 * The queue has a modified remove which allows the user to remove an element using an index.
 * 
 * @param <Item> The type of the queue, in this case a generic type.
 */
public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    /**
     * Declaration of the Node class. The Node class contains two
     * references to the next node and the item. The next node contains
     * the address of the next node.
     */
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Construct an empty queue by defining the first and last node
     * as null and the size as 0.
     */
    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * We set the new item to the last node and if the queue is empty
     * (first == null) we set the first node to the new node (last).
     * If the queue is not empty we set the next node of the old last node
     * to the new node.
     * 
     * @param item The item to be added to the queue.
     */
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

    /**
     * Dequeue the first item in the queue.
     * 
     * @return The first item in the queue.
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return item;
    }

    /**
     * By comparing if the first and element in the queue is null, we can
     * determine if the queue is empty or not.
     * 
     * @return True if the queue is empty, false if the queue is not empty
     */
    boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the size of the queue.
     * 
     * @return The size of the queue
     */
    int size() {
        return n;
    }

    /**
     * Returns the string representation of the queue by using a StringBuilder.
     * We first add a bracket to the string, and then we add the items of the queue
     * by using a for loop. We add a comma between the items. We add a bracket to the
     * end of the string which than is returned.
     * 
     * @return The string representation of the queue
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<Item> current = first;
        while (current != null) {
            sb.append(current.item);
            current = current.next;
            if (current != null)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Peeks at the item at the first position in the queue.
     * 
     * @throws NoSuchElementException if the queue is empty
     * @return The item at the first position in the queue
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("The queue is empty");
        return first.item;
    }

    /**
     * Returns an iterator for the queue.
     * 
     * @return An iterator for the queue
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    /**
     * The class ListIterator is used to iterate through the queue.
     * 
     * @param <Item> The type of the queue, in this case a generic queue
     */
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;
        
        /**
         * The current node is set to the first node in the queue.
         * 
         * @param first The first node in the queue
         */
        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        /**
         * Returns true if the iterator has a next item, otherwise false.
         * 
         * @return True if the iterator has a next item, otherwise false
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next item in the queue.
         * 
         * @throws NoSuchElementException if the queue is empty
         * @return The next item in the queue
         */
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("The queue is empty");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}