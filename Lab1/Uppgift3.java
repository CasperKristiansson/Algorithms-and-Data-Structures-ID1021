/*
Author: Casper Kristiansson
Code Generated: 2021-09-07
Code Updated: 2021-09-013
Problem: Implement a FIFO-queue based on a double linked circular list
Sources: https://algs4.cs.princeton.edu/10fundamentals/, Algorithms 4th Edition, Section 1.3 Queues,
https://media.geeksforgeeks.org/wp-content/uploads/Insertion-in-a-list.png
*/
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Uppgift3 {

    /** 
     * A test for the Queue class. The test is build using 
     * cases which the user can choose which method to test and use.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1: Queue Enqueue Character");
            System.out.println("2: Queue Enqueue Word");
            System.out.println("3: Queue Dequeue");
            System.out.println("4: Queue IsEmpty");
            System.out.println("5: Queue Size");
            System.out.println("6: Queue Print");
            System.out.println("7: Exit Program");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nEnter characters to Enqueue");
                    String characters = input.nextLine();

                    for (int i = 0; i < characters.length(); i++) {
                        queue.enqueue(characters.substring(i, i + 1));
                    }
                    System.out.println(queue);
                    System.out.println("\n");
                    break;
                
                case 2:
                    System.out.println("\nEnter word to Enqueue");
                    String word = input.nextLine();
                    queue.enqueue(word);
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 3:
                    System.out.println("\nCharacter dequeue: " + queue.dequeue());
                    System.out.println(queue);
                    System.out.println("\n");
                    break;
                
                case 4:
                    System.out.println("\nThe Queue is empty: " + queue.isEmpty());
                    System.out.println("\n");
                    break;
                
                case 5:
                    System.out.println("\nThe Queue size is: " + queue.size());
                    System.out.println("\n");
                    break;

                case 6:
                    System.out.println();
                    System.out.println(queue);
                    System.out.println("\n");
                    break;
                
                case 7:
                    input.close();
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * The class Queue represents a first-in-first-out (FIFO) queue of objects.
     * The class is generic, which means that it can store objects of any type.
     * The dequeue method removes the element that has been on the queue the longest,
     * which is the first element inserted into the queue. The enqueue method adds an
     * element to the end of the queue. This specific queue is a double linked circular list.
     * 
     * @param <Item> The type of the queue, in this case a generalized type.
     */
    public static class Queue<Item> implements Iterable<Item> {
        private Node<Item> first;
        private Node<Item> last;
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
         * Declaration of the Node class. The Node class contains three
         * references to the next node, the previous node and the item.
         */
        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
            private Node<Item> prev;
        }

        /**
         * Enqueue method adds an element to the end of the queue by creating a new node
         * and setting the new node as the last node with the help of oldlast.next. If the 
         * queue is empty, the new node is set as the first and last node to keep the queue
         * circular.
         * 
         * @param item The item to be added to the queue.
         */
        void enqueue(Item item) {
            Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            last.next = first;
            last.prev = oldlast;
            
            if (isEmpty()) {
                first = last;
                first.next = last;
                first.prev = last;
            }
            else {
                first.prev = last;
                oldlast.next = last;
            }
            n++;
        }

        /**
         * Dequeue method removes the element that has been on the queue the longest,
         * which is the first element inserted into the queue. The method returns the
         * item of the first node, which is the element removed from the queue.
         * 
         * @return The item of the first node, which is the element removed from the queue.
         */
        Item dequeue() {
            if (isEmpty()) 
                throw new NoSuchElementException("The queue is empty");

            Item item = first.item;

            if (first == last) {
                first = null;
                last = null;
            }
            else {
                first = first.next;
                first.prev = last;
                last.next = first;
            }
            n--;
            return item;
        }
        /**
         * If the stack is empty, return true. If the stack is not empty, return false.
         * By comparing the first node with null.
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
            Node<Item> current = first;
            for(int i = 0; i < n; i++) {
                sb.append(current.item);
                current = current.next;
                if (current != first) {
                    sb.append(", ");
                }
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
            if (isEmpty()) throw new NoSuchElementException("The queue is empty");
            return first.item;
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
            private Node<Item> current = first;
            
            /**
             * The current node is set to the first node in the stack.
             * 
             * @param first The first node in the stack
             */
            public void LinkedIterator(Node<Item> first) {
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
                if (!hasNext()) throw new NoSuchElementException("The queue is empty");
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
