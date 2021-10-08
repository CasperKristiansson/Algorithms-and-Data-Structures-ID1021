import java.util.NoSuchElementException;
import java.util.Iterator;
/**
 * The class Stack is a simple stack of generic items. In this case it is used to
 * reverses a string using iteration. The method creates a stack and pushes the
 * characters of the string into the stack. Because how the stack is implemented,
 * when we iterate through the stack we are actually reading the characters in reverse.
 * 
 * @param <Item> The type of the stack, in this case a generic type.
 */
public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;
    
    /**
     * Declaration of the Node class. The Node class contains
     * two references to the next node and the item. This two references
     * is what each node will contain, the next address and the current item.
     */
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Construct an empty stack by defining the first node as null,
     * and the size of the stack to 0.
     */
    public Stack() {
        first = null;
        n = 0;
    }
    
    /**
     * Pushes an item onto the the first position in the stack.
     * 
     * @param item The item to be pushed onto the stack
     */
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    /**
     * Removes the item at the first position in the stack.
     * 
     * @throws NoSuchElementException if the stack is empty
     * @return The item at the first position in the stack
     */
    Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    /**
     * By comparing if the first element in the stack is null, we can
     * determine if the stack is empty or not.
     * 
     * @return True if the stack is empty, false if the stack is not empty
     */
    boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the size of the stack.
     * 
     * @return The size of the stack
     */
    int size() {
        return n;
    }

    /**
     * Returns a string to represent each element in the stack.
     * 
     * @return A string to represent each element in the stack
     */
    public String toString() {
        Node<Item> current = first;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < n; i++) {
            sb.append(current.item);
            if (i < n - 1)
                sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Peeks at the item at the first position in the stack.
     * 
     * @throws NoSuchElementException if the stack is empty
     * @return The item at the first position in the stack
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return first.item;
    }

    /**
     * Returns an iterator for the stack.
     * 
     * @return An iterator for the stack by passing the first node
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    /**
     * The class LinkedIterator is used to iterate through the stack.
     * 
     * @param <Item> The type of the stack, in this case a generic stack
     */
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;
        
        /**
         * The current node is set to the first node in the stack.
         * 
         * @param first The first node in the stack
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
         * Returns the next item in the stack.
         * 
         * @throws NoSuchElementException if the stack is empty
         * @return The next item in the stack
         */
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("The stack is empty");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}