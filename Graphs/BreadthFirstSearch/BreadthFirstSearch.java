package Algorithms.Graphs.BreadthFirstSearch;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {
    private Queue<Vertex> queue;
    public void bfs(List<Vertex> vertexList) {
        this.queue = new ArrayDeque<>();
        for(Vertex v : vertexList) {
            if(!v.isVisited()) {
                v.setVisited(true);
                this.queue.add(v);
                System.out.println("Added vertex: " + v + " to the queue");
            }
        }
        while(!this.queue.isEmpty()) {
            bfsWithQueue(this.queue.poll());
        }
    }
    private void bfsWithQueue(Vertex vertex) {
        if(!vertex.getNeighbours().isEmpty()) {
            System.out.println("Adding the vertexes of " + vertex + " to the queue");
            vertex.getNeighbours().forEach(v -> System.out.println("Adding vertex " + v + " to the queue"));
            this.queue.addAll(vertex.getNeighbours());
        }
    }
}
