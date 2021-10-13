/**
 * @author Casper Kristiansson
 * Code Generated: 2021-10-10
 * Code Updated: 2021-10-10
 * Problem: Search for a path between A and B using DFS with directed edges
 * Sources: Algorithms 4th edition (Directed Graphs, 4.2), https://algs4.cs.princeton.edu/40graphs/
*/
public class L4Uppgift4 {
    /**
     * A test method for the DFS algorithm. We first input a file and a start and end position.
     * This algorithm uses directed graphs when trying to find a path between the start and end position.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "data.txt";
        String from = "AL";
        String to = "WV";

        DirectSymbolGraph sg = new DirectSymbolGraph(filename); 
        DirectGraph G = sg.graph();

        int start = sg.indexOf(from);
        int end = sg.indexOf(to);
        DepthFirstDirectPaths bfs = new DepthFirstDirectPaths(G, start);

        if (bfs.hasPathTo(end)) {
            System.out.println(from + " to " + to);
            for(int x : bfs.pathTo(end)) System.out.println("-" + sg.nameOf(x));
        }
        else {
            System.out.println(from + " to " + to + " not connected ");
        }
    }
}
