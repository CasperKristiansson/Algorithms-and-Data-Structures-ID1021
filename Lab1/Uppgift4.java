/**
@author Casper Kristiansson
Code Generated: 2021-09-07
Code Updated: 2021-09-14
Problem: Implement a generic linked circular list which can insert and remove elements
from both the start and the end of the list. The author solved this by using creating a circular
list which contains the address of the next node and the item of the current node. To implement the
four methods to inset and remove from both the start and at the end of the queue, the first and last
node were modified, to point to the right node.
Sources: https://algs4.cs.princeton.edu/13stacks/, Algorithms 4th Edition, Section 1.3 Queues
*/
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException; 

public class Uppgift4 {
    /** 
     * A test for the Queue class. The test is build using 
     * cases which the user can choose which method to test and use.
     * The test covers all of the methods in the Queue class.
     * 
     * @param args command line arguments
     */  
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1: Queue Enqueue Characters");
            System.out.println("2: Queue Enqueue Word");
            System.out.println("3: Queue Enqueue First Characters");
            System.out.println("4: Queue Enqueue First Word");
            System.out.println("5: Queue Dequeue");
            System.out.println("6: Queue Dequeue Last");
            System.out.println("7: Queue IsEmpty");
            System.out.println("8: Queue Size");
            System.out.println("9: Queue Print");
            System.out.println("10: Queue Peek");
            System.out.println("11: Exit Program");

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
                    System.out.println("\nEnter a character to insert at the front");
                    String characters2 = input.nextLine();
                    for (int i = 0; i < characters2.length(); i++) {
                        queue.enqueueFirst(characters2.substring(i, i + 1));
                    }
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 4:
                    System.out.println("\nEnter a word to insert at the front");
                    String word2 = input.nextLine();
                    queue.enqueueFirst(word2);
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 5:
                    System.out.println("\nRemoved: " + queue.dequeue());
                    System.out.println(queue);
                    System.out.println("\n");
                    break;

                case 6:
                    System.out.println("\nRemoved: " + queue.dequeueLast());
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
                    System.out.println();
                    System.out.println("First item: " + queue.peek());
                    System.out.println("\n");
                    break;

                case 11:
                    System.out.println("\n");
                    input.close();
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * The class Queue represents modified queue.
     * The class is generic, which means that it can store objects of any type.
     * The dequeue method removes the element that has been on the queue the longest,
     * which is the first element inserted into the queue. The enqueue method adds an
     * element to the end of the queue. The queue is modified so it can insert and remove
     * elements from both the start and the end of the queue.
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
         * Declaration of the Node class. The Node class contains two
         * references to the next node and the item. The next nodes contains
         * the address of the next node.
         */
        private static class Node<Item> {
            Item item;
            Node<Item> next;
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
            Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            if (isEmpty()) {
                first = last;
            } else {
                oldlast.next = last;
            }
            last.next = first;
            n++;
        }
        
        /**
         * To enqueue first we set the new node to the first node and if the
         * queue is empty we set the last node to the new node (first) and the 
         * next node of the new node to the last node. If the queue is not empty
         * we set the next node of the new node to the first node.
         * 
         * @param item The item to be added to the queue.
         */
        void enqueueFirst(Item item) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;

            if (isEmpty()) {
                last = first;
                first.next = last;
            }
            else {
                last.next = first;
            }
            n++;
        }

        /**
         * If the queue only contains one node, we set the first and last node
         * to null. if the queue is empty we set first and last to null.
         * If the queue is not empty we set the first node to the next node in the queue.
         * We also set the last.next to the new first node.
         * 
         * @throws NoSuchElementException if the queue is empty
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
         * node and update the new last node to the first node. we can navigate to the
         * next to last node using a while statement.
         * 
         * @return The item that was removed from the queue.
         */
        Item dequeueLast() {
            if (isEmpty()) 
                throw new NoSuchElementException("Queue is empty");
            
            Item item = last.item;

            if (first == last) {
                first = null;
                last = null;
            }
            else {
                Node<Item> current = first;
                while(current.next != last) 
                    current = current.next;
                current.next = first;
                last = current;
            }
            n--;
            return item;
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
         * By comparing if the first and element in the queue is null, we can
         * determine if the queue is empty or not.
         * 
         * @return True if the queue is empty, false if the queue is not empty
         */
        boolean isEmpty() {
            return first == null || last == null;
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
         * Returns an iterator for the queue.
         * 
         * @return An iterator for the queue
         */
        public Iterator<Item> iterator() {
            return new LinkedIterator(first);
        }

        /**
         * The class LinkedIterator is used to iterate through the queue.
         * 
         * @param <Item> The type of the queue, in this case a generalized queue
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
             * @return The next item in the queue
             */
            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }
}
