/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-07
 * Code Updated: 2021-09-13
 * Problem: Implement a iterative and recursive method which reverses a string using
 * one of the ADTs suggested in Algorithms ch. 1.3 for the iterative version.
 * Sources: https://algs4.cs.princeton.edu/10fundamentals/, Algorithms 4th Edition, Section 1.3 Stack
*/
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Uppgift2 {
    /** 
     * A test for the recursive version of the program and
     * the iterative version of the program. The test is built using
     * cases which the user can choose which method to test and use.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1: Reverse String Using Recursive");
            System.out.println("2: Reverse String Using Iterative");
            System.out.println("3: Stack Push");
            System.out.println("4: Stack Pop");
            System.out.println("5: Stack IsEmpty");
            System.out.println("6: Stack Size");
            System.out.println("7: Stack Print");
            System.out.println("8: Exit Program");
            
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nEnter Characters For The Recursive Version");
                    String str = input.nextLine();
                    recursive(str);
                    System.out.println("\n");
                    break;

                case 2:
                    System.out.println("\nEnter Characters For The Iterative Version");
                    String str2 = input.nextLine();

                    for (int i = 0; i < str2.length(); i++) {
                        stack.push(str2.substring(i, i + 1));
                    }

                    for(String s : stack) {
                        System.out.print(s);
                    }
                    
                    System.out.println("\n");
                    break;
                
                case 3:
                    System.out.println("\nEnter Characters to store in the stack");
                    String push = input.nextLine();

                    for (int i = 0; i < push.length(); i++) {
                        stack.push(push.substring(i, i + 1));
                    }

                    System.out.println(stack);
                    System.out.println("\n");
                    break;
                
                case 4:
                    System.out.print("\nCharacter removed: ");
                    System.out.println(stack.pop());
                    System.out.println(stack);
                    System.out.println("\n");
                    break;
                
                case 5:
                    System.out.print("\nThe stack is empty: ");
                    System.out.println(stack.isEmpty());
                    System.out.println("\n");
                    break;
                
                case 6:
                    System.out.print("\nThe stack size is: ");
                    System.out.println(stack.size());
                    System.out.println("\n");
                    break;

                case 7:
                    System.out.println();
                    System.out.println(stack);
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
     * when we iterate through the stack we are actually reading the characters in reverse.
     * 
     * @param <Item> The type of the stack, in this case a generic type.
     */
    public static class Stack<Item> implements Iterable<Item> {
        private Node<Item> first;
        private int n;
        
        /**
         * Declaration of the Node class.
         * The Node class is used to create a linked list of the stack.
         * The Node class contains two references to the next node and the item.
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
                current = current.next;
                if (i < n - 1)
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
            if (isEmpty()) throw new NoSuchElementException("Stack is empty");
            return first.item;
        }

        /**
         * Returns an iterator for the stack.
         * 
         * @return An iterator for the stack
         */
        public Iterator<Item> iterator() {
            return new LinkedIterator(first);
        }

        /**
         * The class ListIterator is used to iterate through the stack. The method remove
         * is not implemented.
         * 
         * @param <Item> The type of the stack, in this case a generalized stack
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
}
