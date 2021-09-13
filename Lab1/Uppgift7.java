/*Implement a program which takes as input a series of parentheses , that is a series
of the characters: '(', ')', '[', ']', '{', '}'. The program should check if the parentheses
are "balanced" or not. Also show the time and memory complexity of the algorithm.

Balanced: (([{}]){}[])
Not balanced: (([{)}]){}[])}

if emty return true
TODO: Dubbel checka vad listiterator är rätt
TODO: Kolla om man kan använda for each för alla prints out istället för tostrig
TODO: Kolla canvas om circular list är implementerat rätt

Time Complexity: O(n) -> Linear Complexity
Auxiliary Space: O(n) for stack, Space Complexity = Auxiliary Space + Input space
*/
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Uppgift7 {
    public static void main(String[] args) {
        String input = "(([{}]){}[])";

        System.out.println(parenthesesBalanced(input));
    }

    public static boolean parenthesesBalanced(String input) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(' || input.charAt(i) == '[' || input.charAt(i) == '{') {
                stack.push(input.charAt(i)); 
            }
            else {
                if (stack.isEmpty())
                    return false;
                if (input.charAt(i) == ')') {
                    if (stack.pop() != '(')
                        return false;
                }
                else if (input.charAt(i) == '}') {
                    if (stack.pop() != '{')
                        return false;
                }
                else if (input.charAt(i) == ']') {
                    if (stack.pop() != '[')
                        return false;
                }
            }
        }
        return stack.isEmpty();
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
