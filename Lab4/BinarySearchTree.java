import java.util.NoSuchElementException;
/**
 * The class represents a ordered binary search tree with generic key-value pairs.
 * The keys are ordered by their values, which in this exercise is letters in the alphabet.
 * The value (letter) is stored in key and the frequency of the letter is stored in val.
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
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
}
