/**
 * The graph class represents a undirected graph of vertices and edges.
 */
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;        // number of vertices
    private int E;              // number of edges
    private Bag<Integer>[] adj;
    
    /**
     * Initializes an empty graph
     * 
     * @param V the number of vertices
     * @throws IllegalArgumentException if V < 0
     */
    @SuppressWarnings("unchecked")
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("The number of the vertices are negative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) adj[v] = new Bag<Integer>();
    }

    /**
     * Returns the number of vertices in the graph
     * 
     * @return the number of vertices in the graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in the graph
     * 
     * @return the number of edges in the graph
     */
    public int E() {
        return E;
    }

    /**
     * Checks if the index is valid
     * 
     * @param v Checks if the index v is in the graph
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Out of Bounds");
    }

    /**
     * Adds the edge v-w to the graph
     * 
     * @param v the index of the first vertex
     * @param w the index of the second vertex
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * Returns the vertex that is connected to v by an edge
     * 
     * @param v the index of the vertex
     * @return the index of the vertex connected to v by an edge
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of edges connected to v
     * 
     * @param v the index of the vertex
     * @return the number of edges connected to v
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns a string representation of the graph
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
