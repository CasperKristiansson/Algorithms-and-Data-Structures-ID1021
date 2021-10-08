/**
 * @author Casper Kristiansson
 * Code Generated: 2021-10-07
 * Code Updated: 2021-10-08
 * Problem: Write a program that uses DFS which can calculates a path from
 * two different nodes in a graph.
 * Sources: Algorithms 4th edition (Undirected Graphs, 4.1), https://algs4.cs.princeton.edu/40graphs/
*/
public class L4Uppgift1 {
    /**
     * A test method for the DFS algorithm. We first input a file and a start and end position.
     * From their we calculate the path and print it out.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "data.txt";
        String from = "AL";
        String to = "OK";

        SymbolGraph sg = new SymbolGraph(filename); 
        Graph G = sg.graph();

        int start = sg.indexOf(from);
        int end = sg.indexOf(to);
        DepthFirstPaths dfs = new DepthFirstPaths(G, start);

        if(dfs.hasPathTo(end)){
            System.out.println(sg.nameOf(start) + " to " + sg.nameOf(end));
            for(int x : dfs.pathTo(end)) System.out.println("-" + sg.nameOf(x));
        }
        else{
            System.out.println(sg.nameOf(start) + " to " + sg.nameOf(end) + " not connected ");
        }
    }

    /**
     * A class that implements the DepthFirstPaths algorithm
     */
    public static class DepthFirstPaths {
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
         * Returns the path from a input vertex to the start vertex.
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
            if (v < 0 || v >= marked.length)
                throw new IllegalArgumentException("Out of Bounds");
        }
    }
}
