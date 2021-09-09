/*Implement a program which takes as input a series of parentheses , that is a series
of the characters: '(', ')', '[', ']', '{', '}'. The program should check if the parentheses
are "balanced" or not. Also show the time and memory complexity of the algorithm.

Balanced: (([{}]){}[])
Not balanced: (([{)}]){}[])}

if emty return true
TODO: Dubbel checka vad listiterator är rätt

Time Complexity: O(n) -> Linear Complexity
Auxiliary Space: O(n) for stack. 
*/
import java.util.Iterator;

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

    public static class Stack<Item> implements Iterable<Item> {
        private int n;
        private Node first;

        public Stack() {
            first = null;
            n = 0;
        }

        private class Node {
            Item item;
            Node next;
        }

        void push(Item item) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            n++;
        }

        Item pop() {
            if (n == 0)
                return null;

            Item item = first.item;
            first = first.next;
            return item;
        }

        boolean isEmpty() {
            return first == null;
        }

        int size() {
            return n;
        }

        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        private class ListIterator implements Iterator<Item> {
            private Node current = first;

            public boolean hasNext() {
                return current != null;
            }

            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }

            public void remove() {  }

        }
    }
}
