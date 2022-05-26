package Algorithms.Graphs.DepthFirstSearch;

import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {
    private Stack<Vertex> vertexStack;
    public void dfs (List<Vertex> vertexList) {
        vertexStack = new Stack<>();
        for(Vertex v : vertexList) {
            if(!v.isVisited()) {
                v.setVisited(true);
                dfsWithStack(v);
            }
        }
    }

    private void dfsWithStack(Vertex vertex) {
        this.vertexStack.push(vertex); // push the given vertex into the stack
        System.out.println("The actual vertex: " + this.vertexStack.pop());

        for(Vertex v : vertex.getNeighbours()) {
            if (!v.isVisited()) {
                v.setVisited(true);
                this.vertexStack.push(v);
                System.out.println("Vertex: " + v + " is pushed");
                dfsWithStack(v); // recursively checks if given vertex has its own neighbours
            }
        }
    }
}
