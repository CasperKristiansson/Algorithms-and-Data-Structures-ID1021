/**
 * @author Casper Kristiansson
 * Code Generated: 2021-10-07
 * Code Updated: 2021-10-09
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
        String to = "CA";

        SymbolGraph sg = new SymbolGraph(filename); 
        Graph G = sg.graph();

        int start = sg.indexOf(from);
        int end = sg.indexOf(to);
        DepthFirstPaths dfs = new DepthFirstPaths(G, start);

        if(dfs.hasPathTo(end)){
            System.out.println(from + " to " + to);
            for(int x : dfs.pathTo(end)) System.out.println("-" + sg.nameOf(x));
        }
        else{
            System.out.println(from + " to " + to + " not connected ");
        }
    }
}
