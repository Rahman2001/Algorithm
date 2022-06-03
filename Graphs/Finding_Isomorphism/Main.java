package Algorithms.Graphs.Finding_Isomorphism;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Isomorphism graphIsomorphism = new Isomorphism();
        int[][] graphA = { {0, 2, 0, 1, 0},
                {2, 0, 1, 0, 1},
                {0, 1, 0, 1, 1},
                {1, 0, 1, 0, 0},
                {0, 1, 1, 0, 0}};

        int[][] graphB = {{0, 1, 0, 1, 0},
                {1, 0, 0, 0, 2},
                {0, 0, 0, 1, 1},
                {1, 0, 1, 0, 1},
                {0, 2, 1, 1, 0}};

        System.out.println("These graphs are isomorphic: " + graphIsomorphism.areIsomorphic(graphA, graphB));
        System.out.println("hasEqualVertex: " + graphIsomorphism.hasEqualVertex(graphA, graphB));
        System.out.println("hasEqualDegrees: " + graphIsomorphism.hasEqualDegrees(graphA, graphB));
        Arrays.stream(graphIsomorphism.degreeNumber(graphA)).forEach(x -> System.out.print(" " + x));
        System.out.println();
        Arrays.stream(graphIsomorphism.degreeNumber(graphB)).forEach(x -> System.out.print(" " + x));
        System.out.println("\nhasSameConnections: " + graphIsomorphism.hasSameConnections(graphA, graphB));

        System.out.println("\n/-------------------graphA");
        for(int[] vertex : graphA) {
            Arrays.stream(vertex).forEach(System.out::print);
            System.out.println();
        }
        System.out.println("\n/-------------------graphB");
        for(int[] vertex : graphB) {
            Arrays.stream(vertex).forEach(System.out::print);
            System.out.println();
        }

    }
}
