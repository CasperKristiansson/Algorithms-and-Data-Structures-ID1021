/**
 * A class that implements the BreadthFirstSearch algorithm
 */
public class BreadthFirstPaths {
    private boolean[] marked;   // Does it have a path to start?
    private int[] edgeTo;       // last vertex on known path to this vertex
    private int[] distTo;       // length of shortest known path to this vertex

    /**
     * Constructor to initialize the algorithm
     * 
     * @param G the graph
     * @param start the source vertex
     */
    public BreadthFirstPaths(Graph G, int start) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(start);
        bfs(G, start);
    }

    /**
     * Performs the breadth-first search algorithm by first setting
     * the default distance to infinity for all vertices and then
     * setting the distance to the source vertex to 0. We than use a queue
     * to iterate through the vertices and count the distance to each one.
     * 
     * @param G the graph
     * @param start the source vertex
     */
    private void bfs(Graph G, int start) {
        Queue<Integer> queue = new Queue<Integer>();

        for (int v = 0; v < G.V(); v++) distTo[v] = Integer.MAX_VALUE;
        distTo[start] = 0;
        marked[start] = true;
        queue.enqueue(start);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    /**
     * Returns if it exist a path from start to a given vertex
     * 
     * @param v the vertex
     * @return true if it exist a path
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of edges in a path between start and a given vertex
     * 
     * @param v the vertex
     * @return the number of edges in a path
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns the path between start and a given vertex
     * 
     * @param v the vertex
     * @return the path
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) path.push(x);
        path.push(x);
        return path;
    }

    /**
     * Validates the vertex
     * 
     * @throws IllegalArgumentException if the vertex is invalid
     * @param v the vertex
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= marked.length) throw new IllegalArgumentException("Out of bounds");
    }
}
