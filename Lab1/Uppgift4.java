/*
Author: Casper Kristiansson
Code Generated: 2021-09-07
Code Updated: 2021-09-09
Problem: Implement a FIFO-queue based on a double linked circular list
Sources: https://algs4.cs.princeton.edu/10fundamentals/, Algorithms 4th Edition, Section 1.3 Queues
TODO: double linked circular list, last.next = first, first.next = last
*/
import java.util.Iterator;    

public class Uppgift4 {
    /** 
     * A test for the Queue class. 
     * 
     * @param args command line arguments
     */    
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();

        queue.enqueue(1);
        System.out.println(queue);
        queue.enqueue(2);
        System.out.println(queue);
        queue.enqueue(3);
        System.out.println(queue);

        queue.enqueueStart(4);
        System.out.println(queue);

        queue.dequeueEnd();
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);
    }

    /**
     * The class Queue represents a first-in-first-out (FIFO) queue of objects.
     * The class is generic, which means that it can store objects of any type.
     * The dequeue method removes the element that has been on the queue the longest,
     * which is the first element inserted into the queue. The enqueue method adds an
     * element to the end of the queue. The queue is implemented using a double linked
     * circular list.
     * 
     * @param <Item> The type of the queue, in this case a generalized type.
     */
    public static class Queue<Item> implements Iterable<Item> {
        private Node first;
        private Node last;
        private int n;
        
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
         * Declaration of the Node class.
         * The Node class contains three references to the next node, the item
         * and the previous node.
         */
        private class Node {
            Item item;
            Node next;
            Node prev;
        }

        /**
         * TODO: FIX COMMENT
         * @param item The item to be enqueued.
         */
        void enqueue(Item item) {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = oldlast;
            if (isEmpty())
                first = last;
            else
                oldlast.next = last;
            n++;
        }

        void enqueueStart(Item item) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.prev = null;
            if (isEmpty())
                last = first;
            else
                oldfirst.prev = first;
            n++;
        }

        Item dequeueEnd() {
            Item item = last.item;
            last = last.prev;
            if (isEmpty())
                first = null;
            else
                last.next = null;
            n--;
            return item;
        }

        Item dequeue() {
            if (isEmpty()) {
                return null;
            }
            Item item = first.item;
            first = first.next;
            n--;
            if (isEmpty())
                last = null;
            return item;
        }

        /**
         * If the stack is empty, return true. If the stack is not empty, return false.
         * By comparing if the first node is null, we can determine if the stack is empty.
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
         * Returns the string representation of the stack by using a StringBuilder.
         * We first add a bracket to the string, and then we add the items of the stack
         * by using a for loop. We add a comma between the items. We add a bracket to the
         * end of the string which than is returned.
         * 
         * @return The string representation of the stack
         */
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            Node current = first;
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
         * Returns an iterator for the stack.
         * 
         * @return An iterator for the stack
         */
        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        /**
         * The class ListIterator is used to iterate through the stack. The method remove
         * is not implemented.
         * 
         * @param <Item> The type of the stack, in this case a generalized stack
         */
        private class ListIterator implements Iterator<Item> {
            private Node current = first;

            /**
             * The current node is set to the first node in the stack.
             * 
             * @param first The first node in the stack
             */
            public void LinkedIterator(Node first) {
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
             * @return The next item in the stack
             */
            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }

            /**
             * The remove method is not implemented.
             */
            public void remove() {  }
        }
    }
}
