package Algorithms.Graphs.TravelingSalesmanProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Vertex {
    private String name;
    private int weight;
    private boolean isVisited;
    private Vertex prev;
    private List<Vertex> neighbours;

    public Vertex(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
    }
    public Vertex() {
        this.neighbours = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return this.weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isVisited() {
        return this.isVisited;
    }

    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }

    public void setPrevVertex(Vertex vertex) {
        this.prev = vertex;
    }
    public Vertex getPrevVertex(){
        return this.prev;
    }

    public void setNeighbours(List<Vertex> vertices) {
        this.neighbours = vertices;
    }

    public List<Vertex> getNeighbours() {
        return this.neighbours;
    }
    public void addNeighbours(Vertex vertex, int weight) {
        if(this.getNeighbourVertex(vertex) == null) { // if the vertex is not in the list, then we can add it as a neighbour vertex
            Vertex v = new Vertex(vertex.getName());
            v.setWeight(weight);
            this.neighbours.add(v);
        }
    }
    public Vertex getNeighbourVertex(Vertex vertex) { // gets particular vertex from the list if such exists
        Optional<Vertex> vertex1 = this.getNeighbours().stream().filter(x -> x.getName().equals(vertex.getName())).findAny();
        return vertex1.orElse(null);
    }

    public String toString() {
        return this.getName();
    }
}
