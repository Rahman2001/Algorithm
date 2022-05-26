package Algorithms.Graphs.DepthFirstSearch;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");

        v1.addNeighbours(v2);
        v2.addNeighbours(v4);
        v3.addNeighbours(v5);
        v6.addNeighbours(v7);
        List<Vertex> vertexList = List.of(v1, v2, v3, v4, v5, v6, v7);
        depthFirstSearch.dfs(vertexList);
    }
}
