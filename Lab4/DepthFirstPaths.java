/**
 * A class that implements the DepthFirstPaths algorithm
 */
public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int start;

    /**
     * Constructor to initialize the algorithm
     * 
     * @param G the graph to be used
     * @param start the start position
     */
    public DepthFirstPaths(Graph G, int start) {
        this.start = start;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(start);
        dfs(G, start);
    }
    
    /**
     * A recursive method which searches from a specific index
     * 
     * @param G the graph
     * @param v the index of the vertex
     */
    public void dfs(Graph G, int v) {
        marked[v] = true;
        for (int i : G.adj(v)) {
            if (!marked[i]) {
                edgeTo[i] = v;
                dfs(G, i);
            }
        }
    }
 
    /**
     * Returns true if there is a path from the start vertex to the
     * input vertex v.
     * 
     * @param v the vertex
     * @return true if there is a path from the start vertex to the
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the path from a input vertex to the start vertex. Using the
     * DeepFirstPaths algorithm, this method will randomly return a path
     * from the end vertex to the start vertex.
     * 
     * @param v the vertex
     * @return the path from the start vertex to the input vertex v
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != start; x = edgeTo[x]) path.push(x);
        path.push(start);
        return path;
    }

    /**
     * Checks if the input vertex index is within the bounds of the graph
     * 
     * @param v the vertex index
     */
    public void validateVertex(int v) {
        if (v < 0 || v >= marked.length) throw new IllegalArgumentException("Out of Bounds");
    }
}