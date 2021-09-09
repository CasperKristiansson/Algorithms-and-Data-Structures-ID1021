/*
Author: Casper Kristiansson
Code Generated: 2021-09-07
Code Updated: 2021-09-09
Problem: Implement a generalized linked circular list which can insert and remove elements
from both the start and the end of the list.
Sources: https://algs4.cs.princeton.edu/10fundamentals/, Algorithms 4th Edition, Section 1.3 Queues
*/
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException; 

public class Uppgift4 {
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
            System.out.println("1: Queue Enqueue Characters");
            System.out.println("2: Queue Enqueue Word");
            System.out.println("3: Queue Start Enqueue Character");
            System.out.println("4: Queue Start Enqueue Word");
            System.out.println("5: Queue Dequeue");
            System.out.println("6: Queue Dequeue End");
            System.out.println("7: Queue IsEmpty");
            System.out.println("8: Queue Size");
            System.out.println("9: Queue Print");
            System.out.println("10: Exit Program");

            int choice = input.nextInt();
            input.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.println("\nEnter a characters to enqueue");
                    String characters = input.nextLine();

                    for (int i = 0; i < characters.length(); i++) {
                        queue.enqueue(characters.substring(i, i + 1));
                    }
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 2:
                    System.out.println("\nEnter a word to enqueue");
                    String word = input.nextLine();
                    queue.enqueue(word);
                    System.out.println(queue);
                    System.out.println("\n");
                    break;
                    
                case 3:
                    System.out.println("\nEnter a character to enqueue at the start");
                    String characters2 = input.nextLine();
                    for (int i = 0; i < characters2.length(); i++) {
                        queue.enqueueStart(characters2.substring(i, i + 1));
                    }
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 4:
                    System.out.println("\nEnter a word to enqueue at the start");
                    String word2 = input.nextLine();
                    queue.enqueueStart(word2);
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 5:
                    System.out.println("\nDequeued: " + queue.dequeue());
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 6:
                    System.out.println("\nDequeued: " + queue.dequeueEnd());
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 7:
                    System.out.println("\nIsEmpty: " + queue.isEmpty());
                    System.out.println("\n");
                    break;

                case 8:
                    System.out.println("\nSize: " + queue.size());
                    System.out.println("\n");
                    break;

                case 9:
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 10:
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
     * element to the end of the queue. The queue is modified so it can insert and remove
     * elements from both the start and the end of the queue.
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
         * Declaration of the Node class. The Node class contains two
         * references to the next node and the item.
         */
        private class Node {
            Item item;
            Node next;
        }

        /**
         * We set the new item to the last node and if the queue is empty
         * (first == null) we set the first node to the new node (last).
         * If the queue is not empty we set the next node of the old last node
         * to the new node.
         * 
         * @param item The item to be added to the queue.
         */
        void enqueue(Item item) {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = first;
            if (isEmpty()) {
                first = last;
                first.next = last;
            } else {
                oldlast.next = last;
            }
            n++;
        }
        
        /**
         * We set the new item to the first node and if the queue is empty
         * (first == null) we set the last node to the new node (first).
         * 
         * @param item The item to be added to the queue.
         */
        void enqueueStart(Item item) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            last.next = first;
            if (isEmpty())
                last = first;
            n++;
        }

        /**
         * If the queue only contains one node, we set the first and last node
         * to null. If the queue is not empty, we set the first node to the next
         * node and update the last node to the first node.
         * 
         * @return The item that was removed from the queue.
         */
        Item dequeue() {
            if (isEmpty()) 
                throw new NoSuchElementException("Queue is empty");

            Item item = first.item;

            if (first == last) {
                first = null;
                last = null;
            }
            else {
                first = first.next;
                last.next = first;
            }
            n--;
            return item;
        }

        /**
         * If the queue only contains one node, we set the first and last node
         * to null. If the queue is not empty, we set the last node to the previous
         * node and update the new last node to the first node.
         * 
         * @return The item that was removed from the queue.
         */
        Item dequeueEnd() {
            if (isEmpty()) 
                throw new NoSuchElementException("Queue is empty");
            
            Item item = last.item;

            if (first == last) {
                first = null;
                last = null;
            }
            else {
                Node current = first;
                while(current.next != last) 
                    current = current.next;
                current.next = first;
                last = current;
            }
            n--;
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
