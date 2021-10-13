/**
 * @author Casper Kristiansson
 * Code Generated: 2021-10-08
 * Code Updated: 2021-10-09
 * Problem: Write a program that uses BFS which can calculates a path from
 * two different nodes in a graph.
 * Sources: Algorithms 4th edition (Undirected Graphs, 4.1), https://algs4.cs.princeton.edu/40graphs/
*/
public class L4Uppgift2 {
    /**
     * A test method for the DFS algorithm. We first input a file and a start and end position.
     * From their we calculate the path and print it out. In this exercise we find the shortest path.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "data.txt";
        String from = "AL";
        String to = "OR";

        SymbolGraph sg = new SymbolGraph(filename); 
        Graph G = sg.graph();

        int start = sg.indexOf(from);
        int end = sg.indexOf(to);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, start);

        if (bfs.hasPathTo(end)) {
            System.out.println(from + " to " + to);
            for(int x : bfs.pathTo(end)) System.out.println("-" + sg.nameOf(x));
        }
        else {
            System.out.println(from + " to " + to + " not connected ");
        }
    }
}
