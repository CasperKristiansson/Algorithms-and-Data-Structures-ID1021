/*Implement a generalized queue which allows the user to remove the kth
element from the queue. Assume the most recently added element has index 1.
You should print the content of the list after each insertion/deletion of an element.

Fix index last = 1
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
