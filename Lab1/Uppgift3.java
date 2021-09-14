/**
@author Casper Kristiansson
Code Generated: 2021-09-07
Code Updated: 2021-09-14
Problem: Implement a generic FIFO-queue based on a double linked circular list. This means that
the author implements a queue based on a that each node has the address of the next and previous
node. It also has a reference to the current item. The author also made sure that the queue is
circular and that the first and last node are connected.
Sources: https://algs4.cs.princeton.edu/13stacks/, Algorithms 4th Edition, Section 1.3 Queues.
*/
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Uppgift3 {

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
            System.out.println("1: Queue Enqueue Character");
            System.out.println("2: Queue Enqueue Word");
            System.out.println("3: Queue Dequeue");
            System.out.println("4: Queue IsEmpty");
            System.out.println("5: Queue Size");
            System.out.println("6: Queue Print");
            System.out.println("7: Queue Peek");
            System.out.println("8: Exit Program");

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
     * The class Queue represents a first-in-first-out (FIFO) queue of objects.
     * The class is generic, which means that it can store objects of any type.
     * The dequeue method removes the element that has been on the queue the longest,
     * which is the first element inserted into the queue. The enqueue method adds an
     * element to the end of the queue. This specific queue is a double linked circular list.
     * 
     * @param <Item> The type of the queue, in this case a generic type.
     */
    public static class Queue<Item> implements Iterable<Item> {
        private Node<Item> head;
        private int n;

        /**
         * Construct an empty queue by defining the first and last node
         * as null and the size as 0.
         */
        public Queue() {
            head = null;
            n = 0;
        }
        /**
         * Declaration of the Node class. The Node class contains three
         * references to the next node, the previous node and the item. The next
         * and previous node contains the address of those nodes.
         */
        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
            private Node<Item> prev;
        }

        /**
         * Enqueue method adds an element to the end of the queue by creating a new node. The node is that
         * connected to the last old node as previous and the next node to the head as next to construct
         * a double linked circular list. If the queue is empty, the new node is the head and the nodes next
         * and previous points to itself.
         * 
         * @param item The item to be added to the queue.
         */
        void enqueue(Item item) {
            Node<Item> newNode = new Node<Item>();
            newNode.item = item;
            newNode.next = head;
            
            if (isEmpty()) {
                head = newNode;
                head.next = newNode;
                head.prev = newNode;
                newNode.prev = head;
            }
            else {
                newNode.prev = head.prev;
                head.prev.next = newNode;
                head.prev = newNode;
            }
            n++;
        }

        /**
         * Dequeue method removes the element that has been on the queue the longest,
         * which is the first element inserted into the queue. It does this by making sure that
         * the head.next.prev points to the last item in the queue and that the head.prev.next
         * points to the "new" head. The method returns the item of the first node, which is
         * the element removed from the queue.
         * 
         * @return The item of the first node, which is the element removed from the queue.
         */
        Item dequeue() {
            if (isEmpty()) 
                throw new NoSuchElementException("The queue is empty");

            Item item = head.item;

            if (head == head.next) {
                head = null;
            }
            else {
                head.next.prev = head.prev;
                head = head.next;
                head.prev.next = head;
            }
            n--;
            return item;
        }
        
        /**
         * By comparing if the first element in the queue is null, we can
         * determine if the queue is empty or not.
         * 
         * @return True if the queue is empty, false if the queue is not empty
         */
        boolean isEmpty() {
            return head == null;
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
            Node<Item> current = head;
            for(int i = 0; i < n; i++) {
                sb.append(current.item);
                current = current.next;
                if (current != head) {
                    sb.append(", ");
                }
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
            if (isEmpty()) throw new NoSuchElementException("Stack is empty");
            return head.item;
        }

        /**
         * Returns an iterator for the queue by passing the head of the queue.
         * 
         * @return An iterator for the queue
         */
        public Iterator<Item> iterator() {
            return new LinkedIterator(head);
        }

        /**
         * The class ListIterator is used to iterate through the queue.
         * Implementing a interface like this, will allows an object to be the target for 
         * "foreach" statement.
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
