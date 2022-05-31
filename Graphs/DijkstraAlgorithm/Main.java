package Algorithms.Graphs.DijkstraAlgorithm;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("D");
        Vertex v3 = new Vertex("E");
        Vertex v4 = new Vertex("C");
        Vertex v5 = new Vertex("B");

        v1.addNeighbours(v2, 5);
        v1.addNeighbours(v5, 5);
        v1.addNeighbours(v4, 3);

        v2.addNeighbours(v4, 4);
        v2.addNeighbours(v3, 1);
        v2.addNeighbours(v1, 5);

        v3.addNeighbours(v4, 12);
        v3.addNeighbours(v2, 1);

        v4.addNeighbours(v5, 10);
        v4.addNeighbours(v1, 3);
        v4.addNeighbours(v2, 4);
        v4.addNeighbours(v3, 12);

        v5.addNeighbours(v1, 5);
        v5.addNeighbours(v4, 10);

        List<Vertex> vertices = List.of(v1, v2, v3, v4, v5);

        vertices = dijkstra.Dijkstra(v2, v5, vertices); // starting vertex: D     target vertex: B
        System.out.println("\nShortest Path to the target vertex " + v5 + " is:\n" + vertices);
    }
}
