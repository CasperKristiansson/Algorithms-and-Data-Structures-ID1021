/**
 * @author Casper Kristiansson
 * Code Generated: 2021-10-10
 * Code Updated: 2021-10-10
 * Problem: Implement a method that navigates through A -> C using a middle point B.
 * Sources: Algorithms 4th edition (Undirected Graphs, 4.1), https://algs4.cs.princeton.edu/40graphs/
*/
public class L4Uppgift3 {
    /**
     * A test method for the DFS algorithm. We first input a file and a start and end position.
     * From their we calculate the path and print it out. In this exercise we find the shortest path
     * using a middle part. Start -> Middle -> End.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "data.txt";
        String from = "AL";
        String intermediate = "SD";
        String to = "OR";

        SymbolGraph sg = new SymbolGraph(filename); 
        Graph G = sg.graph();

        int start = sg.indexOf(from);
        int middle = sg.indexOf(intermediate);
        int end = sg.indexOf(to);

        BreadthFirstPaths bfsStart = new BreadthFirstPaths(G, start);
        BreadthFirstPaths bfsMiddle = new BreadthFirstPaths(G, middle);
        
        if (bfsStart.hasPathTo(middle) && bfsMiddle.hasPathTo(end)) {
            System.out.println(from + " to " + intermediate);
            for(int x : bfsStart.pathTo(middle)) System.out.println("-" + sg.nameOf(x));

            System.out.println(intermediate + " to " + to);
            for(int x : bfsMiddle.pathTo(end)) System.out.println("-" + sg.nameOf(x));
        }
        else {
            if(bfsStart.hasPathTo(middle)) System.out.println(from + " to " + intermediate + " not connected ");
            if(bfsMiddle.hasPathTo(end)) System.out.println(intermediate + " to " + to + " not connected ");
        }
    }
}
