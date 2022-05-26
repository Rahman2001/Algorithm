package Algorithms.Graphs.DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String name;
    private boolean isVisited;
    private List<Vertex> neighbours;

    public Vertex(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
    }

    public Vertex() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public List<Vertex> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Vertex> neighbours) {
        this.neighbours = neighbours;
    }
    public void addNeighbours(Vertex vertex) {
        this.neighbours.add(vertex);
    }
    public String toString() {
        return this.name;
    }
}