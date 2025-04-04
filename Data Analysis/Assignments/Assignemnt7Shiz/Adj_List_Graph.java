package Assignemnt7Shiz;

import java.util.*;
import java.io.*;

public class Adj_List_Graph {
    int n; // no of nodes
    ArrayList<ArrayList<Integer>> adj;

    // Constructor taking as the single parameter the number of nodes
    Adj_List_Graph(int no_nodes) {
        n = no_nodes;
        adj = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<Integer>());
    }

    // Main method
    public static void main(String[] args) throws IOException {

        ArrayList<Integer> nums = new ArrayList<Integer>();
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.out.println("Please enter the name of the file:");
            String fileName = input.nextLine();

            try {
                File fileReader = new File(fileName);
                Scanner myReader = new Scanner(fileReader);
                while (myReader.hasNextInt()) {
                    int data = myReader.nextInt();
                    nums.add(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
            }
        }

        int n = nums.get(0);
        nums.remove(0);

        int[][] adjMatrix = new int[n][n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = nums.get(count++);
            }
        }

        Adj_List_Graph G = new Adj_List_Graph(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] == 1) {
                    G.addEdge(i, j);
                }
            }
        }

        Adj_List_Graph G2 = computeG2(G);
        G2.printGraph();
    }

    // Computes G2
    public static Adj_List_Graph computeG2(Adj_List_Graph G) {
        int n = G.n;
        Adj_List_Graph G2 = new Adj_List_Graph(n);

        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                // Checks if there exists a path of length 1 or 2 from u to v
                if (G.adj.get(u).contains(v) || hasPathLength2(G, u, v)) {
                    G2.addEdge(u, v);
                }
            }
        }
        return G2;
    }

    // Checks if there exists a path of length 2 from u to v
    public static boolean hasPathLength2(Adj_List_Graph G, int u, int v) {
        for (int neighbor : G.adj.get(u)) {
            if (G.adj.get(neighbor).contains(v)) {
                return true;
            }
        }
        return false;
    }

    // A utility function to add an edge in an
    // undirected graph; for directed graph remove the second line
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        // adj.get(v).add(u); //this line should be un-commented, if graph is undirected
    }

    // A utility function to print the adjacency list
    // representation of graph
    public void printGraph() {
        for (int i = 0; i < n; i++) {
            System.out.println("\nAdjacency list of vertex " + i);
            System.out.print(i + "");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> " + adj.get(i).get(j));
            }
            System.out.println();
        }
    }
}
