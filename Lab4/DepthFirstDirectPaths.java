/**
 * A class that implements the DepthFirstSearch algorithm using direct graph
 */
public class DepthFirstDirectPaths {
    private boolean[] marked;  // Does start have a path to specified vertex?
    private int[] edgeTo;      // Last edge on path from start to v
    private final int start;   // Start vertex

    /**
     * Constructor to initialize the algorithm
     * 
     * @param G the graph to be used
     * @param start the start position
     */
    public DepthFirstDirectPaths(DirectGraph G, int start) {
        this.start = start;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(start);
        dfs(G, start);
    }

    /**
     * A recursive method which searches from a specific index
     * 
     * @param G the graph
     * @param v the index of the vertex
     */
    private void dfs(DirectGraph G, int v) { 
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
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
    private void validateVertex(int v) {
        if (v < 0 || v >= marked.length) throw new IllegalArgumentException("Out of bounds");
    }
}
