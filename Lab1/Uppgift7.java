/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-08
 * Code Updated: 2021-09-13
 * Problem: Implement a function to check if a string has balanced parentheses. This algorithm
 * is developed using a stack. We iterate through the string and push the opening parentheses onto
 * the stack. If we encounter a closing parentheses we pop the stack and check if the popped
 * value is the same type as the closing parentheses. If it is not the same type we return false.
 * If the stack is empty we return true. If we encounter a closing parentheses and the stack is empty
 * we return false.
 * Sources: https://algs4.cs.princeton.edu/13stacks/, Algorithms 4th Edition, Section 1.3 Stack
 * 
 * Time Complexity: O(n) -> Linear Complexity
 * Auxiliary Space: O(n) for the stack in isBalanced
 * Space complexity: O(n) for the normal stack
*/
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Uppgift7 {
    /** 
     * A test for function isBalanced to check if the parentheses are balanced
     * The test also tests the other functions of the stack.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1: Parentheses Balance Check");
            System.out.println("2: Stack Push");
            System.out.println("3: Stack Pop");
            System.out.println("4: Stack IsEmpty");
            System.out.println("5: Stack Size");
            System.out.println("6: Stack Print");
            System.out.println("7: Stack Peek");
            System.out.println("8: Exit Program");
            
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {    
                case 1:
                    System.out.println("\nEnter Parentheses to check if they are balanced");
                    String parentheses = input.nextLine();
                    System.out.println(isBalanced(parentheses) ? "Balanced" : "Not Balanced");
                    System.out.println("\n");
                    break;

                case 2:
                    System.out.println("\nEnter Characters to store in the stack");
                    String push = input.nextLine();

                    for (int i = 0; i < push.length(); i++) {
                        stack.push(push.substring(i, i + 1));
                    }

                    System.out.println(stack);
                    System.out.println("\n");
                    break;
                
                case 3:
                    System.out.print("\nCharacter removed: ");
                    System.out.println(stack.pop());
                    System.out.println(stack);
                    System.out.println("\n");
                    break;
                
                case 4:
                    System.out.print("\nThe stack is empty: ");
                    System.out.println(stack.isEmpty());
                    System.out.println("\n");
                    break;
                
                case 5:
                    System.out.print("\nThe stack size is: ");
                    System.out.println(stack.size());
                    System.out.println("\n");
                    break;

                case 6:
                    System.out.println();
                    System.out.println(stack);
                    System.out.println("\n");
                    break;
                
                case 7:
                    System.out.println();
                    System.out.println(stack.peek());
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
     * Checks if the parentheses are balanced by using a stack. If its a open parenthesis it is
     * pushed onto the stack.If its a closed parenthesis we check if the top of the stack is
     * a open parenthesis. If it is we pop it off the stack. If the stack is empty or the top of
     * the stack is not a open parenthesis we return false.
     * 
     * @param parentheses the string to check if it is balanced
     * @return true if the parentheses are balanced, false if not
     */
    public static boolean isBalanced(String parentheses) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < parentheses.length(); i++) {
            if (parentheses.charAt(i) == '(' || parentheses.charAt(i) == '[' || parentheses.charAt(i) == '{') {
                stack.push(parentheses.charAt(i)); 
            }
            else {
                if (stack.isEmpty())
                    return false;
                if (parentheses.charAt(i) == ')') {
                    if (stack.pop() != '(')
                        return false;
                }
                else if (parentheses.charAt(i) == '}') {
                    if (stack.pop() != '{')
                        return false;
                }
                else if (parentheses.charAt(i) == ']') {
                    if (stack.pop() != '[')
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * The class Stack is a simple stack of generic items. The class represents LIFO
     * (Last in first out) It supports the stack operations such as push, pop, peek, isEmpty,
     * size toString and peak. 
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
         * By comparing if the first and element in the queue is null, we can
         * determine if the queue is empty or not.
         * 
         * @return True if the queue is empty, false if the queue is not empty
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
}
