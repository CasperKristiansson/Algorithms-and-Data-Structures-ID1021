/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-30
 * Code Updated: 2021-09-30
 * Problem: 
 * Sources:
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class L3Uppgift3 {
    /**
     * A test method for the class BinarySearchTree. In this exercise we count
     * the number of different words exist in a text file. This can be done by using
     * Scanner to read the text file. We than iterate through the text file word by 
     * word. We first need to filter out the non alphabetic characters. We than write
     * the word to the tree.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int maxWords = 1000;
        int wordCounter = 0;
        Scanner scanner = null;

        BinarySearchTree<String, Integer> st = new BinarySearchTree<String, Integer>();

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
     * The class represents a ordered binary search tree with generic key-value pairs.
     * The keys are ordered by their values, which in this exercise is letters in the alphabet.
     * The value (letter) is stored in key and the frequency of the letter is stored in val.
     */
    public static class BinarySearchTree<Key extends Comparable<Key>, Value> {
        private Node root;

        /**
         * Constructs an node with the given key and value, and no left or right child.
         * It also constructs the size which is the number of nodes that is stored under
         * the node.
         */
        private class Node {
            private Key key;
            private Value val;
            private Node left, right;
            private int N;

            public Node(Key key, Value val, int N) {
                this.key = key;
                this.val = val;
                this.N = N;
            }
        }

        /**
         * Initializes the binary search tree.
         */
        public BinarySearchTree() {
        }

        /**
         * Returns the number of key-value pairs.
         * @return the size of the tree
         */
        public int size() {
            return size(root);
        }

        /**
         * Returns the number of key-value pairs in the subtree.
         * @param x The node to start the counting from.
         * @return the size of the subtree
         */
        private int size(Node x) {
            if (x == null) return 0;
            else return x.N;
        }

        /**
         * Returns the value which is associated with the given key.
         * @param key The key to search for.
         * @return the value associated with the key
         */
        public Value get(Key key) {
            return get(root, key);
        }

        /**
         * Gets the a node and a key value and returns either the next node
         * or the value associated with the key.
         * 
         * @param x The current node
         * @param key The key to search for.
         * @return the value associated with the key
         */
        private Value get(Node x, Key key) { 
            if (x == null) return null;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) return get(x.left, key);
            else if (cmp > 0) return get(x.right, key);
            else return x.val;
        }

        /**
         * Inserts the given key-value pair into the tree.
         * 
         * @throws IllegalArgumentException if the key is null
         * @param key The key to insert.
         * @param val The value to insert.
         */
        public void put(Key key, Value val) {
            if (key == null) throw new IllegalArgumentException("Null Key");
            root = put(root, key, val);
        }

        /**
         * Inserts a key-value pair by recursively navigating
         * to the right position and than inserting the key-value pair.
         * We also need to adjust the size of each node to the new size.
         * 
         * @param x The current node
         * @param key The key to insert
         * @param val The value to insert
         * @return The next node
         */
        private Node put(Node x, Key key, Value val) {
            if (x == null) return new Node(key, val, 1);
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x.left = put(x.left, key, val);
            else if (cmp > 0) x.right = put(x.right, key, val);
            else x.val = val;
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }

        /**
         * Return true if the tree is empty, false otherwise
         * 
         * @return true if the tree is empty, false otherwise
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        /**
         * Returns the minimum key in the tree
         * 
         * @throws NoSuchElementException if the tree is empty
         * @return the minimum key in the tree
         */
        public Key min() {
            if (isEmpty()) throw new NoSuchElementException("BST is empty");
            return min(root).key;
        } 

        /**
         * Returns the minimum key in the subtree
         * @param x the current node
         * @return the minimum key in the subtree
         */
        private Node min(Node x) { 
            if (x.left == null) return x; 
            else return min(x.left); 
        } 

        /**
         * Returns the maximum key in the tree
         * 
         * @throws NoSuchElementException if the tree is empty
         * @return the maximum key in the tree
         */
        public Key max() {
            if (isEmpty()) throw new NoSuchElementException("BST is empty");
            return max(root).key;
        } 

        /**
         * Returns the maximum key in the subtree
         * 
         * @param x the current node
         * @return the maximum key in the subtree
         */
        private Node max(Node x) {
            if (x.right == null) return x; 
            else return max(x.right); 
        }

        /**
         * Selects a specific key in the tree using a index
         * 
         * @throws IllegalArgumentException if the index is out of bounds
         * @param rank The index of the key
         * @return the key in the tree
         */
        public Key select(int rank) {
            if (rank < 0 || rank >= size()) {
                throw new IllegalArgumentException("Index out of bounds");
            }
            return select(root, rank);
        }

        /**
         * Returns the key in the tree using a index
         * 
         * @param x The current node
         * @param rank The index of the key
         * @return the key in the tree
         */
        private Key select(Node x, int rank) {
            if (x == null) return null;
            int leftSize = size(x.left);
            if (leftSize > rank) return select(x.left, rank);
            else if (leftSize < rank) return select(x.right, rank - leftSize - 1); 
            else return x.key;
        }

        /**
         * Returns the number of keys in the tree less than a specified key
         * 
         * @throws IllegalArgumentException if the key is null
         * @param key The key to search for
         * @return the number of keys in the tree less than a specified key
         */
        public int rank(Key key) {
            if (key == null) throw new IllegalArgumentException("Null Key");
            return rank(key, root);
        } 

        /**
         * Returns the number of keys in the subtree less than a specified key
         * 
         * @param key The key to search for
         * @param x The current node
         * @return the number of keys in the subtree less than a specified key
         */
        private int rank(Key key, Node x) {
            if (x == null) return 0; 
            int cmp = key.compareTo(x.key); 
            if (cmp < 0) return rank(key, x.left); 
            else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
            else return size(x.left); 
        } 

        /**
         * Iterator for the keys in the tree
         * 
         * @return the iterator for the keys in the tree
         */
        public Iterable<Key> keys() {
            if (isEmpty()) return new Queue<Key>();
            return keys(min(), max());
        }

        /**
         * Iterator for the keys in the tree between a specified range
         * 
         * @throws IllegalArgumentException if the range is null
         * @param low The lower bound of the range
         * @param high The upper bound of the range
         * @return the iterator for the keys in the tree between a specified range
         */
        public Iterable<Key> keys(Key low, Key high) {
            if (low == null) throw new IllegalArgumentException("Low is Null");
            if (high == null) throw new IllegalArgumentException("High is Null");

            Queue<Key> queue = new Queue<Key>();
            keys(root, queue, low, high);
            return queue;
        } 

        /**
         * Passes the keys in the tree between a specified range to a queue
         * to find the right key to enqueue
         * 
         * @param x The current node
         * @param queue The queue to enqueue the keys
         * @param low The lower bound of the range
         * @param high The upper bound of the range
         */
        private void keys(Node x, Queue<Key> queue, Key low, Key high) { 
            if (x == null) return; 
            int cmpLow = low.compareTo(x.key); 
            int cmpHigh = high.compareTo(x.key); 
            if (cmpLow < 0) keys(x.left, queue, low, high); 
            if (cmpLow <= 0 && cmpHigh >= 0) queue.enqueue(x.key); 
            if (cmpHigh > 0) keys(x.right, queue, low, high); 
        } 

        /**
         * Returns if a specified key is in the tree or not
         * 
         * @param key The key to search for
         * @return if a specified key is in the tree or not
         */
        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("Null Key");
            return get(key) != null;
        }

        /**
         * Returns the size of the subtree
         * 
         * @throws IllegalArgumentException if the range is null
         * @param low The lower bound of the range
         * @param high The upper bound of the range
         * @return the size of the subtree
         */
        public int size(Key low, Key high) {
            if (low == null) throw new IllegalArgumentException("Low is null");
            if (high == null) throw new IllegalArgumentException("High is null");

            if (low.compareTo(high) > 0) return 0;
            if (contains(high)) return rank(high) - rank(low) + 1;
            else return rank(high) - rank(low);
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
