package Algorithms.Graphs.TravelingSalesmanProblem;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TSP tsp = new TSP();
        Vertex[][] graphMatrix = tsp.getRandomGraph(7); // The vertex number must be minimum 5 according to the course assignment

        System.out.print("\nThis is our random graph represented as a matrix with weight values:\n  ");
        for(int j = 0; j < graphMatrix.length; j++) {
            System.out.print("  " + graphMatrix[j][j].getName() + "  ");
        }
        System.out.println();
        int i = 0;
        for(Vertex[] vertices : graphMatrix) {
            System.out.print(vertices[i].getName() + " ");
            Arrays.stream(vertices).forEach(x-> System.out.print(" " + x.getName() + "(" + x.getWeight() + ")" ));
            System.out.println();
            i++;
        }
        Scanner input = new Scanner(System.in);

        System.out.print("Which vertex should be origin? : ");
        String name = input.next("[A-Za-z]"); // indicates that the input should be a character.
        System.out.println();

        tsp.setOriginVertexName(graphMatrix, name);
        List<List<Vertex>> possibleWays = tsp.getPossibleWays();
        System.out.println("\nThese are the possible paths of the graph starting from " + name + ": -------------");
        for(List<Vertex> possiblePaths : possibleWays){
            System.out.print(possiblePaths + "   ");
            possiblePaths.forEach(System.out::print);
            System.out.println(" = " + possiblePaths.stream().mapToInt(Vertex::getWeight).sum());
        }
        System.out.println("\nThis is the shortest path of the graph:--------------");
        List<List<Vertex>> shortestPaths = tsp.findShortestPath();
        for(List<Vertex> shortestPath : shortestPaths) {
            System.out.println(shortestPath);
        }
    }
}
