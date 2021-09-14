/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-08
 * Code Updated: 2021-09-13
 * Problem: Implement a generalized queue which can remove an element using a index. In this exercise
 * the index will be started at the end of the queue, meaning that the most recent element will be
 * 1. This can be achieved using while loop to stop at the right position, to than modified the node references.
 * Otherwise the queue will be following the standard queue rules.
 * Sources: https://algs4.cs.princeton.edu/13stacks/, Algorithms 4th Edition, Section 1.3.38 GeneralizedQueue
*/
import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException; 

public class Uppgift5 {
    /** 
     * A test for the GeneralizedQueue class. The test is build using 
     * cases which the user can choose which method to test and use.
     * The test covers all of the methods in the Queue class.
     * 
     * @param args command line arguments
     */  
    public static void main(String[] args) {
        GeneralizedQueue<String> queue = new GeneralizedQueue<String>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1: Generalized Queue Insert Characters");
            System.out.println("2: Generalized Queue Insert Word");
            System.out.println("3: Generalized Queue Delete");
            System.out.println("4: Queue IsEmpty");
            System.out.println("5: Queue Size");
            System.out.println("6: Queue Print");
            System.out.println("7: Queue Peek");
            System.out.println("8: Exit Program");

            int choice = input.nextInt();
            input.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.println("\nEnter a characters to insert");
                    String characters = input.nextLine();

                    for (int i = 0; i < characters.length(); i++) {
                        queue.insert(characters.substring(i, i + 1));
                    }
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 2:
                    System.out.println("\nEnter a word to insert");
                    String word = input.nextLine();
                    queue.insert(word);
                    System.out.println(queue);
                    System.out.println("\n");
                    break;
                
                case 3:
                    System.out.println("\nEnter a index to remove, (recently added = 1)");
                    int index = input.nextInt();
                    System.out.println("\nRemoved: " + queue.delete(index));
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 4:
                    System.out.println("\nIsEmpty: " + queue.isEmpty());
                    System.out.println("\n");
                    break;

                case 5:
                    System.out.println("\nSize: " + queue.size());
                    System.out.println("\n");
                    break;

                case 6:
                    System.out.println(queue);
                    System.out.println("\n");
                    break;
                
                case 7:
                    System.out.println();
                    System.out.println("First item: " + queue.peek());
                    System.out.println("\n");
                    break;

                case 8:
                    input.close();
                    System.exit(0);
                    break;
            }
        }
    }
    /**
     * The class Queue represents a modified queue of objects.
     * The class is generic, which means that it can store objects of any type.
     * The queue has a modified remove which allows the user to remove an element using an index.
     * 
     * @param <Item> The type of the queue, in this case a generic type.
     */
    public static class GeneralizedQueue<Item> implements Iterable<Item> {
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
        public GeneralizedQueue() {
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
        public void insert(Item item) {
            Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            last.next = null;
            if (isEmpty()) {
                first = last;
            } else {
                oldlast.next = last;
            }
            n++;
        }

        /**
         * If the index is either bigger than the size of the linked list
         * or if it is less than one we throw a exception. By using a 
         * for loop we can navigate to the node before the one at the index.
         * We can than set the current node to the next.next node. Because if the goal
         * is to remove the last node we need to set the last node to the node before last.
         * 
         * @throws NoSuchElementException if the index is out of bounds.
         * @return the item that was removed from the queue
         */
        public Item delete(int index) {
            if (index > n || index < 1) throw new NoSuchElementException("Index out of bounds");
            
            index = size() - index;
            Item item = null;
            
            if (index == 0) {
                item = first.item;
                first = first.next;
            }
            else {
                Node<Item> current = first;
                for(int i = 0; i < index - 1; i++) {
                    current = current.next;
                }
                if (current.next == last) {
                    last = current;
                }
                item = current.next.item;
                current.next = current.next.next;
            }
            n--;
            if (isEmpty()) {
                last = null;
                first = null;
            }
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
}
