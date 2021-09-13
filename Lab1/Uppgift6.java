/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-07
 * Code Updated: 2021-09-13
 * Problem: Implement a ordered generalized queue which stores integers in ascending order.
 * Sources: https://algs4.cs.princeton.edu/10fundamentals/, Algorithms 4th Edition, Section 1.3.38 GeneralizedQueue
*/
import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException; 

public class Uppgift6 {
    /** 
     * A test for the GeneralizedQueue class. The test is build using 
     * cases which the user can choose which method to test and use.
     * 
     * @param args command line arguments
     */  
    public static void main(String[] args) {
        GeneralizedQueue<Integer> queue = new GeneralizedQueue<Integer>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1: Generalized Queue Insert Multiple Numbers");
            System.out.println("2: Generalized Queue Insert Single Numbers");
            System.out.println("3: Generalized Queue Delete");
            System.out.println("4: Queue IsEmpty");
            System.out.println("5: Queue Size");
            System.out.println("6: Queue Print");
            System.out.println("7: Exit Program");

            int choice = input.nextInt();
            input.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.println("\nEnter multiple numbers to insert");
                    String numbers = input.nextLine();

                    for (int i = 0; i < numbers.length(); i++) {
                        queue.insert(Integer.parseInt(numbers.substring(i, i + 1)));
                    }
                    System.out.println(queue);
                    System.out.println("\n");
                    break;
                
                case 2:
                    System.out.println("\nEnter number to insert");
                    int number = input.nextInt();
                    queue.insert(number);
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
                    input.close();
                    System.exit(0);
                    break;
            }
        }
    }
    /**
     * A class which implements a generalized queue using a linked list. The queue
     * can be used to insert and remove elements from the queue. the remove
     * method can be used to remove an element from the queue using an index.
     * 
     * @param <Item> The type of the queue, in this case a generalized type.
     */
    public static class GeneralizedQueue<Item> implements Iterable<Item> {
        private Node<Item> first;
        private Node<Item> last;
        private int n;

        /**
         * Declaration of the Node class. The Node class contains two
         * references to the next node and the item.
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
         * Inserts an item into the queue. If the queue is empty, the item
         * is added to the first and last node. If the queue is not empty,
         * we first check if the number is equal or less than the first node,
         * otherwise we iterate through the queue until the parameter is less or if the 
         * current node is null. We than add it to the queue at the position.
         * 
         * @param item The item to be added to the queue.
         */
        public void insert(Item item) {
            if (isEmpty()) {
                last = new Node<Item>();
                last.item = item;
                last.next = null;
                first = last;
            } else {
                Node<Item> current = first;

                if ((Integer)current.item >= (Integer)item) {
                    Node<Item> oldfirst = first;
                    first = new Node<Item>();
                    first.item = item;
                    first.next = oldfirst;
                } else {
                    while (current.next != null && (Integer)current.next.item < (Integer)item) {
                        current = current.next;
                    }
                    Node<Item> newNode = new Node<Item>();
                    newNode.item = item;
                    newNode.next = current.next;
                    current.next = newNode;
                }
            }
            n++;
        }

        /**
         * If the is either bigger than the size of the linked list
         * or if index is less than one we throw a exception. By using a 
         * while loop we can navigate to the node before the one at the index.
         * We can than set the current node to the next.next node.
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
                int i = 0;

                while (i < index - 1) {
                    current = current.next;
                    i++;
                }
                item = current.next.item;
                current.next = current.next.next;
            }
            n--;
            if (isEmpty()) {
                last = null;
            }
            return item;
        }

        /**
         * If the queue is empty, return true. If the queue is not empty, return false.
         * By comparing if the first node is null, we can determine if the queue is empty.
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
         * The method uses bubble sort to sort the queue. By iterating 
         * through each note we can compare the item of it and sort it
         * accordingly. We typecast the node.item to convert it to
         * a integer.
         * 
         * WARNING: This method is not being used but can be used to sort
         * a queue.
         */
        public void bubbleSort() {
            Node<Item> current = first;
            Node<Item> index = null;
            Item temp;
    
            while (current != null) {
                index = current.next;

                while (index != null) {
                    if ((Integer)current.item > (Integer)index.item) {
                        temp = current.item;
                        current.item = index.item;
                        index.item = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
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
