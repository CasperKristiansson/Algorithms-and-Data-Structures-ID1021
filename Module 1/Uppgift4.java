/*Implement a generic iterable circular linked list which allows the user to insert and
remove elements to/from the front and back end of the queue. Be careful when designing
the API. You should print the content of the list after each insertion/deletion of an element.
Page 126 for FIFO queue.
*/
import java.util.Iterator;    

public class Uppgift4 {    
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();

        queue.enqueue(1);
        System.out.println(queue);
        queue.enqueue(2);
        System.out.println(queue);
        queue.enqueue(3);
        System.out.println(queue);

        queue.enqueueStart(4);
        System.out.println(queue);

        queue.dequeueEnd();
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);
    }

    public static class Queue<Item> implements Iterable<Item> {
        private Node first;
        private Node last;
        private int n;

        private class Node {
            Item item;
            Node next;
            Node prev;
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
            last.prev = oldlast;
            if (isEmpty())
                first = last;
            else
                oldlast.next = last;
            n++;
        }

        void enqueueStart(Item item) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.prev = null;
            if (isEmpty())
                last = first;
            else
                oldfirst.prev = first;
            n++;
        }

        Item dequeueEnd() {
            Item item = last.item;
            last = last.prev;
            if (isEmpty())
                first = null;
            else
                last.next = null;
            n--;
            return item;
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
