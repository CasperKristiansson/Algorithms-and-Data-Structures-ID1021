/*
Author: Casper Kristiansson
Code Generated: 2021-09-07
Code Updated: 2021-09-09
Problem: Implement the above program in JAVA (both iterative and recursive) using one of the
ADTs suggested in Algorithms ch. 1.3 for the iterative version.
Sources: https://algs4.cs.princeton.edu/10fundamentals/
*/

import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Uppgift2 {
    /** 
     * A test for the recursive version of the program and
     * the iterative version of the program.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Characters For The Recursive Version");
        String str = input.nextLine();
        recursive(str);
        
        System.out.println("\nEnter Characters For The Iterative Version");
        String str2 = input.nextLine();
        input.close();

        Stack<String> stack = new Stack<String>();

        for (int i = 0; i < str2.length(); i++) {
            stack.push(str2.substring(i, i + 1));
        }

        for(String s : stack) {
            System.out.print(s);
        }
    }

    /**
    * Reverses a string using recursion. The method creates a substring which is 
    * then passed to the method again until the string is empty. When the string is empty
    * the method returns and prints out the character at the position 0.
    *
    * @param str The string to reverse
    */
    public static void recursive(String str) {
        if (str.length() == 0)
            return;

        recursive(str.substring(1));
        System.out.print(str.charAt(0));
    }

    /**
     * The class Stack is a simple stack of generic items. In this case it is used to
     * reverses a string using iteration. The method creates a stack and pushes the
     * characters of the string into the stack. Because how the stack is implemented,
     * when we iterate through the stack we are actually reading the characters from
     * the reverse of the string.
     * 
     * @param <Item> The type of the stack, in this case a generalized type.
     */
    public static class Stack<Item> implements Iterable<Item> {
        private int n;
        private Node first;
        
        /**
         * Construct an empty stack by defining the first node as null,
         * and the size of the stack to 0.
         */
        public Stack() {
            first = null;
            n = 0;
        }
        
        /**
         * Declaration of the Node class.
         * The Node class is used to create a linked list of the stack.
         * The Node class contains two references to the next node and the item.
         */
        private class Node {
            Item item;
            Node next;
        }

        /**
         * Pushes an item onto the the first position in the stack.
         * 
         * @param item The item to be pushed onto the stack
         */
        void push(Item item) {
            Node oldfirst = first;
            first = new Node();
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
            if (n == 0)
                throw new NoSuchElementException("Stack Underflow");

            Item item = first.item;
            first = first.next;
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
