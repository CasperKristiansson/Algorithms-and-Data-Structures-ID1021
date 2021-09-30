/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-29
 * Code Updated: 2021-09-29
 * Problem: 
 * Sources:
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class L3Uppgift2 {
    /**
     * A test method for the class BinarySearch Symbol table. In this exercise we count
     * the number of different words exist in a text file. This can be done by using
     * Scanner to read the text file. We than iterate through the text file word by 
     * word. We first need to filter out the non alphabetic characters. We than write
     * the word to the symbol table.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int maxWords = 1000;
        int wordCounter = 0;
        Scanner scanner = null;

        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(maxWords);

        try {
            scanner = new Scanner(new File("textFile.txt"), "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        outerLoop:
        while (scanner.hasNextLine()) {
            Scanner line = new Scanner(scanner.nextLine());
            while (line.hasNext()) {
                String s = line.next();

                for(int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if(!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z') && c != '\n') {
                        s = s.replace(c, ' ');
                    }
                }
                s = s.trim();

                if (!st.contains(s)) st.put(s, 1);
                else st.put(s, st.get(s) + 1);
                
                if (wordCounter >= maxWords) break outerLoop;
                wordCounter++;
            }
        }
        
        for (int i = 0; i < st.size(); i++) {
            String s = st.select(i);
            System.out.println(s + " " + st.get(s));
        }

        String max = "";
        st.put(max, 0);
        for (int i = 0; i < st.size(); i++) {
            String s = st.select(i);
            if (st.get(s) > st.get(max)) max = s;
        }
        System.out.println("Max value: " + max + " " + st.get(max));
    }
    /**
     * The class represents a ordered symbol table with generic key-value pairs.
     * The keys are ordered by their values, which in this exercise is letters in the alphabet.
     * The value (letter) is stored in keys and the frequency of the letter is stored in values.
     */
    public static class BinarySearchST<Key extends Comparable<Key>, Value> {
        private Key[] keys;
        private Value[] vals;
        private int N;

        /**
         * The constructor creates an empty symbol table with the a given capacity.
         * 
         * @param capacity the capacity of the symbol table
         */
        @SuppressWarnings("unchecked")
        public BinarySearchST(int capacity) {
            keys = (Key[]) new Comparable[capacity];
            vals = (Value[]) new Object[capacity];
        }

        /**
         * Returns the size
         * 
         * @return the size
         */
        public int size() {
            return N;
        }

        /**
         * Returns the number of keys that is less than the input key
         * 
         * @param key the input key
         * @return the number of keys that is less than the input key
         */
        public int rank(Key key) {
            int low = 0;
            int high = N - 1;
            while (low <= high) {
                int middle = low + (high - low) / 2;
                int cmp = key.compareTo(keys[middle]);
                if      (cmp < 0) high = middle - 1;
                else if (cmp > 0) low = middle + 1;
                else return middle;
            }
            return low;
        }

        /**
         * Gets a key-value pair
         * 
         * @param key the key
         * @return the value that is associated with the key
         */
        public Value get(Key key) {
            if (isEmpty()) return null;
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) return vals[i];
            else return null;
        }

        /**
         * Checks if the symbol table is empty
         * 
         * @return true if the symbol table is empty, false otherwise
         */
        public boolean isEmpty() {
            return N == 0;
        }

        /**
         * Inserts a key-value pair by first looking at how many keys are less than the input key.
         * From that we insert it into the correct position.
         * 
         * @param key the key to insert
         * @param val the value to insert
         */
        public void put(Key key, Value val) {
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) {
                vals[i] = val;
                return;
            }
            
            for (int j = N; j > i; j--) {
                keys[j] = keys[j - 1];
                vals[j] = vals[j - 1];
            }
            keys[i] = key;
            vals[i] = val;
            N++;
        }

        /**
         * We first find how many keys that is less exist in the symbol table.
         * From that we correct the position of the other key-value pairs in
         * the symbol table.
         * 
         * @throws NoSuchElementException if the symbol table is empty
         * @param key the key to delete
         * @return the value that is associated with the key
         */
        public Key delete(Key key) {
            if (isEmpty()) throw new NoSuchElementException("Symbol Table Is Empty");
            int i = rank(key);
            if (i == N || keys[i].compareTo(key) != 0) return null;
            Key deleted = keys[i];
            for (int j = i; j < N-1; j++) {
                keys[j] = keys[j+1];
                vals[j] = vals[j+1];
            }
            N--;
            return deleted;
        }

        /**
         * Deletes the key-value pair with the smallest key.
         * 
         * @throws NoSuchElementException if the symbol table is empty
         * @return the key-value pair with the smallest key
         */
        public Key deleteMin() {
            if (isEmpty()) throw new NoSuchElementException("Symbol Table Is Empty");
            return delete(min());
        }

        /**
         * Delete the key-value pair with the largest key.
         * 
         * @throws NoSuchElementException if the symbol table is empty
         * @return the key-value pair with the largest key
         */
        public Key deleteMax() {
            if (isEmpty()) throw new NoSuchElementException("Symbol Table Is Empty");
            return delete(max());
        }

        /**
         * Returns the smallest key in the symbol table.
         * 
         * @throws NoSuchElementException if the symbol table is empty
         * @return the smallest key in the symbol table
         */
        public Key min() {
            if (isEmpty()) throw new NoSuchElementException("Symbol Table Is Empty");
            return keys[0];
        }

        /**
         * Returns the largest key in the symbol table.
         * 
         * @throws NoSuchElementException if the symbol table is empty
         * @return the largest key in the symbol table
         */
        public Key max() {
            if (isEmpty()) throw new NoSuchElementException("Symbol Table Is Empty");
            return keys[N - 1];
        }

        /**
         * Gets a key value with a specified index.
         * 
         * @throws NoSuchElementException if the index is out of bounds
         * @param index the index
         * @return the key value with the specified index
         */
        public Key select(int index) {
            if (index < 0 || index >= N) throw new IllegalArgumentException("Index Out Of Bounds");
            return keys[index];
        }

        /**
         * Returns the key that is either greater than (closest) or equal to the input key.
         * 
         * @param key the input key
         * @return the that is either greater than (closest) or equal to the input key
         */
        public Key ceiling(Key key) {
            int i = rank(key);
            return keys[i];
        }

        /**
         * Return the key that is either less than (closest) or equal to the input key.
         * 
         * @param key the input key
         * @return the key that is either less than (closest) or equal to the input key
         */
        public Key floor(Key key) {
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) return keys[i];
            else return keys[i - 1];
        }

        /**
         * Checks if a specific key exist in the symbol table.
         * 
         * @throws NoSuchElementException if the key is invalid
         * @param key the key to check
         * @return true if the key exist in the symbol table, false otherwise
         */
        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("Invalid Key");
            return get(key) != null;
        }

        /**
         * Iterates through the symbol table with the key-value pairs.
         * 
         * @throws NoSuchElementException if the key is invalid
         * @param low the low index
         * @param high the high index
         * @return an queue to iterate through the symbol table
         */
        public Iterable<Key> keys(Key low, Key high) {
            if (low == null) throw new IllegalArgumentException("Invalid Key"); 
            if (high == null) throw new IllegalArgumentException("Invalid Key"); 
    
            Queue<Key> queue = new Queue<Key>(); 
            if (low.compareTo(high) > 0) return queue;
            for (int i = rank(low); i < rank(high); i++) 
                queue.enqueue(keys[i]);
            if (contains(high)) queue.enqueue(keys[rank(high)]);
            return queue; 
        }

        /**
         * Creates a iterator object by sending in the min and max keys.
         * 
         * @return an iterator object (Queue)
         */
        public Iterable<Key> keys() {
            return keys(min(), max());
        }
    }

    /**
     * The class Queue represents a first-in-first-out (FIFO) queue of objects.
     * The class is generic, which means that it can store objects of any type.
     * The dequeue method removes the element that has been on the queue the longest,
     * which is the first element inserted into the queue. The enqueue method adds an
     * element to the end of the queue. This specific queue is a double linked circular list.
     * 
     * @param <Item> The type of the queue, in this case a generic type.
     */
    public static class Queue<Item> implements Iterable<Item> {
        private Node<Item> head;
        private int n;

        /**
         * Construct an empty queue by defining the first and last node
         * as null and the size as 0.
         */
        public Queue() {
            head = null;
            n = 0;
        }
        /**
         * Declaration of the Node class. The Node class contains three
         * references to the next node, the previous node and the item. The next
         * and previous node contains the address of those nodes.
         */
        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
            private Node<Item> prev;
        }

        /**
         * Enqueue method adds an element to the end of the queue by creating a new node. The node is that
         * connected to the last old node as previous and the next node to the head as next to construct
         * a double linked circular list. If the queue is empty, the new node is the head and the nodes next
         * and previous points to itself.
         * 
         * @param item The item to be added to the queue.
         */
        void enqueue(Item item) {
            Node<Item> newNode = new Node<Item>();
            newNode.item = item;
            newNode.next = head;
            
            if (isEmpty()) {
                head = newNode;
                head.next = newNode;
                head.prev = newNode;
                newNode.prev = head;
            }
            else {
                newNode.prev = head.prev;
                head.prev.next = newNode;
                head.prev = newNode;
            }
            n++;
        }

        /**
         * Dequeue method removes the element that has been on the queue the longest,
         * which is the first element inserted into the queue. It does this by making sure that
         * the head.next.prev points to the last item in the queue and that the head.prev.next
         * points to the "new" head. The method returns the item of the first node, which is
         * the element removed from the queue.
         * 
         * @return The item of the first node, which is the element removed from the queue.
         */
        Item dequeue() {
            if (isEmpty()) 
                throw new NoSuchElementException("The queue is empty");

            Item item = head.item;

            if (head == head.next) {
                head = null;
            }
            else {
                head.next.prev = head.prev;
                head = head.next;
                head.prev.next = head;
            }
            n--;
            return item;
        }
        
        /**
         * By comparing if the first element in the queue is null, we can
         * determine if the queue is empty or not.
         * 
         * @return True if the queue is empty, false if the queue is not empty
         */
        boolean isEmpty() {
            return head == null;
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
            Node<Item> current = head;
            for(int i = 0; i < n; i++) {
                sb.append(current.item);
                current = current.next;
                if (current != head) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }

        /**
         * Peeks at the item at the first position in the queue.
         * 
         * @throws NoSuchElementException if the queue is empty
         * @return The item at the first position in the queue
         */
        public Item peek() {
            if (isEmpty()) throw new NoSuchElementException("Stack is empty");
            return head.item;
        }

        /**
         * Returns an iterator for the queue by passing the head of the queue.
         * 
         * @return An iterator for the queue
         */
        public Iterator<Item> iterator() {
            return new LinkedIterator(head);
        }

        /**
         * The class ListIterator is used to iterate through the queue.
         * Implementing a interface like this, will allows an object to be the target for 
         * "foreach" statement.
         * 
         * @param <Item> The type of the queue, in this case a generic queue
         */
        private class LinkedIterator implements Iterator<Item> {
            private Node<Item> current;
            
            /**
             * The current node is set to the first node in the queue.
             * 
             * @param first The first node in the queue
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
             * Returns the next item in the queue.
             * 
             * @throws NoSuchElementException if the queue is empty
             * @return The next item in the queue
             */
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException("The queue is empty");
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }
}
