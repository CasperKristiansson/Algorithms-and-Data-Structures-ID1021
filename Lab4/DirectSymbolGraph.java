import java.io.*;
import java.util.Scanner;

/**
 * The DirectSymbolGraph class represents an undirected graph where each
 * vertex is a named symbol.
 */
public class DirectSymbolGraph {
    private BinarySearchTree<String, Integer> st;
    private String[] keys;
    private DirectGraph graph;

    /**
     * Initializes a graph from a file which than it stores each line
     * of the file contains a pair of strings, the first of which is
     * the name of a vertex and the second of which is the name of an
     * edge.
     * 
     * @param filename the name of the file
     */
    public DirectSymbolGraph(String filename) {
        st = new BinarySearchTree<String, Integer>();
        File file = new File(filename);

        try {
            Scanner in = new Scanner(file);

            while (in.hasNext()) {
                String[] vertexes = in.nextLine().split(" ");
                for (String vertex : vertexes) if (!st.contains(vertex)) st.put(vertex, st.size());
            }
            in.close();

            keys = new String[st.size()];
            for (String vertex : st.keys()) keys[st.get(vertex)] = vertex;

            graph = new DirectGraph(st.size());
            in = new Scanner(file);
            while (in.hasNextLine()) {
                String[] a = in.nextLine().split(" ");
                graph.addEdge(st.get(a[0]), st.get(a[1]));
            }
            in.close();

        } catch(FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    /**
     * Checks if it contains the vertex with the given name.
     * 
     * @param name the name of the vertex
     * @return true if it contains the vertex
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * Returns the index of a vertex.
     * 
     * @param name the name of the vertex
     * @return the index of the vertex
     */
    public int indexOf(String s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex with the given index.
     * 
     * @param v the index of the vertex
     * @return the name of the vertex
     */
    public String nameOf(int v) {
        return keys[v];
    }

    /**
     * Returns the graph.
     * 
     * @return the graph
     */
    public DirectGraph graph() {
        return graph;
    }

    /**
     * Returns the number of different vertexes.
     * 
     * @return the number of different vertexes
     */
    public int size() {
        return st.size();
    }
}
