/*
Author: Casper Kristiansson
Code Generated: 2021-09-08
Code Updated: 2021-09-09
Problem: Implement a FIFO-queue based on a double linked circular list
Sources: https://algs4.cs.princeton.edu/10fundamentals/, Algorithms 4th Edition, Section 1.3 Queues
TODO: Fix Index
*/
import java.util.Iterator;

public class Uppgift5 {
    
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(1);
        System.out.println(queue);
        queue.enqueue(2);
        System.out.println(queue);
        queue.enqueue(3);
        System.out.println(queue);
        queue.enqueue(4);
        System.out.println(queue);

        queue.removeSpecific(2);
        System.out.println(queue);
    }

    public static class Queue<Item> implements Iterable<Item> {
        private Node first;
        private Node last;
        private int n;

        private class Node {
            Item item;
            Node next;
        }

        public Queue() {
            first = null;
            last = null;
            n = 0;
        }

        void enqueue(Item item) {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty())
                first = last;
            else
                oldlast.next = last;
            n++;
        }

        Item dequeue() {
            if (isEmpty()) {
                return null;
            }
            Item item = first.item;
            first = first.next;
            n--;
            if (isEmpty())
                last = null;
            return item;
        }

        public Item removeSpecific(int index) {
            if (index < 0 || index > n) {
                return null;
            }
            Node node = first;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            node.next = node.next.next;
            n--;
            if (isEmpty())
                last = null;

            return node.next.item;
        }

        boolean isEmpty() {
            return first == null;
        }

        int size() {
            return n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            Node current = first;
            while (current != null) {
                sb.append(current.item);
                current = current.next;
                if (current != null)
                    sb.append(", ");
            }
            sb.append("]");
            return sb.toString();
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
