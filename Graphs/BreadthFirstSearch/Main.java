package Algorithms.Graphs.BreadthFirstSearch;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");

        v1.addNeighbours(v2);
        v1.addNeighbours(v3);
        v2.addNeighbours(v4);
        v2.addNeighbours(v5);
        v3.addNeighbours(v6);
        v3.addNeighbours(v7);

        List<Vertex> vertexList = List.of(v1);
        bfs.bfs(vertexList);
    }
}
