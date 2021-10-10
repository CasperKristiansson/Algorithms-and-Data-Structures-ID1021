/**
 * The graph class represents a undirected graph of vertices and edges.
 */
public class DirectGraph {
    private final int V;           // number of different vertices
    private int E;                 // number of edges
    private Bag<Integer>[] adj;    // the different vertices connected to the vertex
    private int[] indegree;        // returns the indegree of the vertex
    
    /**
     * Initializes an empty graph
     * 
     * @param V the number of vertices
     * @throws IllegalArgumentException if V < 0
     */
    @SuppressWarnings("unchecked")
    public DirectGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("The number of the vertices are negative");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
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
        if (v < 0 || v >= V) throw new IllegalArgumentException("Out of bounds");
    }

    /**
     * Adds the edge v-w to the graph. The addEdge uses direct graph
     * which manes that v is only connected to w and not the other way
     * around
     * 
     * @param v the index of the first vertex
     * @param w the index of the second vertex
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        E++;
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
     * Returns the number of edges v is connected to
     * 
     * @param v the index of the vertex
     * @return the number of edges v is connected to
     */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of edges connected to v 
     * but v is not connected to.
     * 
     * @param v the index of the vertex
     * @return number of edges
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns a string representation of the graph
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
