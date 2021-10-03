/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-30
 * Code Updated: 2021-10-03
 * Problem: Implement a hash table that uses separate chaining which should store a text files words
 * and the frequency of each word. The user should be able enter a word and get the frequency of that
 * word.
 * Sources: https://algs4.cs.princeton.edu/30searching/, Algorithms 4th Edition (3.4 Hash Tables)
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class L3Uppgift6 {
    /**
     * The main method which reads a text file and filters the text
     * to save only alphabetic characters. We than store the filtered
     * words in a separate chain hash table. The user can than input a word
     * and the program will return a number of times the word was found in the
     * text.
     * 
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
        int maxWords = 16301;
        int wordCounter = 0;
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("textFile.txt"), "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        outerLoop:
        while (scanner.hasNextLine()) {
            Scanner line = new Scanner(scanner.nextLine());
            while (line.hasNext()) {
                String string = line.next();
                String[] stringArray = filterText(string);

                for(String word : stringArray) {
                    if(word.length() > 0) {
                        if (!st.contains(word)) st.put(word, 1);
                        else st.put(word, st.get(word) + 1);
                    }
                    if (wordCounter >= maxWords) break outerLoop;
                        wordCounter++;
                }
            }
        }

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter a word for frequency: ");
            System.out.println("Press 9 to Exit Program");
            
            String choice = input.nextLine();
            if (choice.equals("9")) break;
            
            if (st.contains(choice)) {
                System.out.println(choice + " Exists: " + st.get(choice));
            } else {
                System.out.println(choice + " Exists: " + 0);
            }
        }
        input.close();
    }

    /**
     * Filters out non alphabetic characters and returns a string array with the words.
     * 
     * @param s the string to be filtered
     * @return the filtered string array
     */
    public static String[] filterText(String s) {
        s = s.toLowerCase();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z') && c != '\n') {
                s = s.replace(c, ' ');
            }
        }
        s = s.trim();
        return s.split(" ");
    }

    /**
     * The SeparateChainingHashST class is a symbol table of 
     * generic key-value pairs. m = table size, n = key-value pairs
     */
    public static class SeparateChainingHashST<Key, Value> {
        private static final int INIT_CAPACITY = 4;
        private int n;
        private int m;                  
        private SequentialSearchST<Key, Value>[] st;
        
        /**
         * Initializes a symbol table with the given initial capacity.
         */
        public SeparateChainingHashST() {
            this(INIT_CAPACITY);
        } 
        
        /**
         * Initializes a symbol table with chains
         * 
         * @param m The initial capacity
         */
        @SuppressWarnings("unchecked")
        public SeparateChainingHashST(int m) {
            this.m = m;
            st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
            for (int i = 0; i < m; i++)
                st[i] = new SequentialSearchST<Key, Value>();
        } 
        
        /**
         * Resizes the hash table to the given capacity.
         * 
         * @param chains The new capacity
         */
        private void resize(int chains) {
            SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
            for (int i = 0; i < m; i++) {
                for (Key key : st[i].keys()) {
                    temp.put(key, st[i].get(key));
                }
            }
            this.m  = temp.m;
            this.n  = temp.n;
            this.st = temp.st;
        }
        
        /**
         * Hash function for keys.
         * 
         * @param key The key
         * @return The hash value
         */
        public int hashTextbook(Key key) {
            return (key.hashCode() & 0x7fffffff) % m;
        }
        
        /**
         * Hashes the key for the hash table.
         * 
         * @param key The key to be hashed
         * @return The hash value
         */
        private int hash(Key key) {
            int h = key.hashCode();
            h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
            return h & (m-1);
        }
        
        /**
         * Returns the number of key-value pairs in this symbol table.
         * 
         * @return The number of key-value pairs in this symbol table
         */
        public int size() {
            return n;
        } 
        
        /**
         * Returns if the symbol table is empty.
         * 
         * @return True if the symbol table is empty, false otherwise
         */
        public boolean isEmpty() {
            return size() == 0;
        }
        
        /**
         * Checks if the symbol table contains the given key.
         * 
         * @throws IllegalArgumentException if the key is null
         * @param key The key
         * @param key The key
         * @return True if the symbol table contains the given key, false otherwise
         */ 
        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("Null Key");
            return get(key) != null;
        } 
        
        /**
         * Gets a specific value from the symbol table using a key
         * 
         * @throws IllegalArgumentException if the key is null
         * @param key The key
         * @return The value associated with the key
         */
        public Value get(Key key) {
            if (key == null) throw new IllegalArgumentException("Null Key");
            int i = hash(key);
            return st[i].get(key);
        } 
        
        /**
         * Inserts a key into the has table.
         * 
         * @throws IllegalArgumentException if the key is null
         * @param key The key to be inserted
         * @param val The value to be inserted
         */
        public void put(Key key, Value val) {
            if (key == null) throw new IllegalArgumentException("Null Key");
            if (val == null) {
                delete(key);
                return;
            }
    
            if (n >= 10*m) resize(2*m);
    
            int i = hash(key);
            if (!st[i].contains(key)) n++;
            st[i].put(key, val);
        } 
        
        /**
         * Deletes a key in the hash table
         * 
         * @throws IllegalArgumentException if the key is null
         * @param key The key to be deleted
         */
        public void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("Null Key");
    
            int i = hash(key);
            if (st[i].contains(key)) n--;
            st[i].delete(key);
    
            if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
        } 

        /**
         * Iterates through the keys in the symbol table
         * 
         * @return An iterator of the keys
         */
        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<Key>();
            for (int i = 0; i < m; i++) {
                for (Key key : st[i].keys())
                    queue.enqueue(key);
            }
            return queue;
        }
    }

    /**
     * The SeparateChainingHashST class is a symbol table of
     * generic key-value pairs.
     */
    public static class SequentialSearchST<Key, Value> {
        private int n;
        private Node first;
        
        /**
         * Linked list helper
         */
        private class Node {
            private Key key;
            private Value val;
            private Node next;
    
            public Node(Key key, Value val, Node next)  {
                this.key  = key;
                this.val  = val;
                this.next = next;
            }
        }
        
        /**
         * Initializes a sequential search symbol table
         */
        public SequentialSearchST() {
        }
    
        public int size() {
            return n;
        }
        
        /**
         * Checks if the symbol table is empty
         * 
         * @return True if the symbol table is empty, false otherwise
         */
        public boolean isEmpty() {
            return size() == 0;
        }
        
        /**
         * Checks if the symbol table contains the given key
         * 
         * @throws IllegalArgumentException if the key is null
         * @param key The key
         * @return True if the symbol table contains the given key, false otherwise
         */
        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("Null Key");
            return get(key) != null;
        }
        
        /**
         * Gets the value associated with the given key
         * 
         * @throws IllegalArgumentException if the key is null
         * @param key The key
         * @return The value associated with the key
         */
        public Value get(Key key) {
            if (key == null) throw new IllegalArgumentException("Null Key"); 
            for (Node x = first; x != null; x = x.next) {
                if (key.equals(x.key))
                    return x.val;
            }
            return null;
        }
        
        /**
         * Inserts a key-value pair into the symbol table
         * 
         * @throws IllegalArgumentException if the key is null
         * @param key The key
         * @param val The value
         */
        public void put(Key key, Value val) {
            if (key == null) throw new IllegalArgumentException("Null Key"); 
            if (val == null) {
                delete(key);
                return;
            }
    
            for (Node x = first; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    x.val = val;
                    return;
                }
            }
            first = new Node(key, val, first);
            n++;
        }
        
        /**
         * Deletes the key-value pair with the given key
         * 
         * @throws IllegalArgumentException if the key is null
         * @param key The key
         */
        public void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("Null Key"); 
            first = delete(first, key);
        }
        
        /**
         * Deletes the key-value pair with the given key
         * 
         * @param x The current node
         * @param key The key to delete
         * @return The next node
         */
        private Node delete(Node x, Key key) {
            if (x == null) return null;
            if (key.equals(x.key)) {
                n--;
                return x.next;
            }
            x.next = delete(x.next, key);
            return x;
        }
        
        /**
         * Iterates through the keys in the symbol table
         * 
         * @return An iterator of the keys
         */
        public Iterable<Key> keys()  {
            Queue<Key> queue = new Queue<Key>();
            for (Node x = first; x != null; x = x.next)
                queue.enqueue(x.key);
            return queue;
        }
    }
}
