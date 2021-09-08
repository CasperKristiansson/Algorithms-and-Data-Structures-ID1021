/*Implement the above program in JAVA (both iterative and recursive) using one of the
ADTs suggested in Algorithms ch. 1.3 for the iterative version.
https://algs4.cs.princeton.edu/13stacks/
https://introcs.cs.princeton.edu/java/43stack/Stack.java
*/

import java.util.Scanner;
import java.util.Iterator;

public class Uppgift2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Characters For The Recursive Version");
        String str = input.nextLine();
        recursive(str);
        
        System.out.println("\nEnter Characters For The Iterative Version");
        String str2 = input.nextLine();

        Stack<String> stack = new Stack<String>();

        for (int i = 0; i < str2.length(); i++) {
            stack.push(str2.substring(i, i + 1));
        }

        for(String s : stack) {
            System.out.print(s);
        }

        input.close();
    }

    public static void recursive(String str) {
        if (str.length() == 0)
            return;

        recursive(str.substring(1));
        System.out.print(str.charAt(0));
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
