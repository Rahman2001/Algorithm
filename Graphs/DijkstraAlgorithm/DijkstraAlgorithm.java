package Algorithms.Graphs.DijkstraAlgorithm;
///////////////////////////////////////////////// DIJKSTRA'S ALGORITHM for FINDING SHORTEST PATH ////////////////////////////
// There are some rules used in Dijkstra's Algorithm:
// 1) The starting vertex is always in A[0][0] and target vertex in A[A.length-1][A.length-1]
// 2) The graph matrix is built as a square matrix to be able to track easily all raw and column
// 3) We give Integer.MAX_VALUE to the edge in the matrix when it is unreachable for a particular vertex
// 4) We give zero (0) value to the edge in the matrix when it is self vertex
// 5) In order to find the shortest path, we have to explore all the possible ways by traversing all the vertices and updating their edges
// 6) We update the value of vertex's (v1) edge for a particular vertex (v2) by summing v2 + v1. If that sum is less than the current one or is unreachable for a starting vertex then we update the value of the edge


import java.util.*;

public class DijkstraAlgorithm {
    private final List<Vertex> listOfShortestPath = new ArrayList<>();

    public List<Vertex> Dijkstra(Vertex startingVertex, Vertex targetVertex, List<Vertex> graphList) {
        Vertex[][] graphMatrix = new Vertex[graphList.size()][graphList.size()];
        createMatrixForGraph(startingVertex, targetVertex, graphMatrix, graphList); // creates a matrix for the graph
        findShortestPath(graphMatrix); // finds the shortest path possible in the matrix
        addShortestPath(graphMatrix[0][graphMatrix.length - 1], this.listOfShortestPath); // adds vertices by starting vertex should follow to reach the target vertex
        return this.listOfShortestPath;
    }

    private void createMatrixForGraph(Vertex startingVertex, Vertex targetVertex, Vertex[][] graphMatrix, List<Vertex> graphList) {
        List<Vertex> tempCopy = new ArrayList<>(graphList);
        for (int i = 0; i < graphList.size(); i++) { // swaps the elements in the list (starting vertex is at the beginning and target vertex at the end)
            if (tempCopy.get(i).getName().equals(startingVertex.getName()) && !tempCopy.get(0).getName().equals(startingVertex.getName())) {
                Collections.swap(tempCopy, 0, i);
                i--;
            }
            if (tempCopy.get(i).getName().equals(targetVertex.getName()) && !tempCopy.get(graphList.size()-1).getName().equals(targetVertex.getName())) {
                Collections.swap(tempCopy, tempCopy.size() - 1, i);
                i--;
            }
        }

        for (int i = 0; i < tempCopy.size(); i++) { // creates the matrix of the graph
            for (int j = 0; j < tempCopy.size(); j++) {
                Vertex temp = new Vertex(tempCopy.get(j).getName());
                graphMatrix[i][j] = temp;
            }
        }

        for (int i = 0; i < graphList.size(); i++) { // inserts the edge weights of vertices into the matrix
            for (int j = 0; j < graphList.size(); j++) {

                if (i != j) { // if it is not self vertex in the matrix, then check if that vertex is a neighbour of vertex in position of graphMatrix[i][]
                    Vertex neighbour = tempCopy.get(i).getNeighbourVertex(graphMatrix[i][j]);
                    if (neighbour != null) { // if such a neighbour exists, insert the weight of the edge between these vertices (neighbour and graphList.get(i) )
                        graphMatrix[i][j].setWeight(neighbour.getWeight());
                    } else {
                        graphMatrix[i][j].setWeight(Integer.MAX_VALUE); // if such a neighbour does not exist, then mark the edge between them as 'unreachable' by using MAX_VALUE of Integer
                    }
                } else {
                    graphMatrix[i][j].setWeight(0); // since there is no self edge in the graph, it always equals to zero
                }
            }
        }
       int i = 0;
       for(Vertex[] matrix : graphMatrix) {
           System.out.println();
           System.out.print(tempCopy.get(i));
           Arrays.stream(matrix).forEach(x -> System.out.print(" " + x.getName() + "(" + x.getWeight() + ")"));
           i++;
       }
    }

    private void findShortestPath(Vertex[][] graphMatrix) {
        this.exploreTheGraph(graphMatrix, 0);
        this.listOfShortestPath.add(graphMatrix[0][0]);

        System.out.println("\n\nAfter using Dijkstra's Algorithm, the graph matrix is filled with edge weights\n\n------------------------------------");
        int i = 0;
        for(Vertex[] matrix : graphMatrix) {
            System.out.println();
            System.out.print(graphMatrix[i][i]);
            Arrays.stream(matrix).forEach(x -> System.out.print(" " + x.getName() + "(" + x.getWeight() + ")"));
            i++;
        }
        System.out.println();
    }

    private void exploreTheGraph(Vertex[][] graphMatrix, int raw) {
        if(raw < graphMatrix.length) { // we don't include target vertex's raw since it is our final destination
            for (int column = 1; column < graphMatrix.length; column++) {
                if (raw != column && graphMatrix[raw][column].getWeight() != Integer.MAX_VALUE // we don't want to access self vertex and unreachable ones
                && !graphMatrix[raw][raw].getName().equals(graphMatrix[graphMatrix.length-1][graphMatrix.length-1].getName())) { // we don't want to access the target vertex's raw since it is useless to calculate the weights between target vertex and others

                    if (raw > 0 && graphMatrix[raw][column].getWeight() < graphMatrix[0][column].getWeight() &&
                            graphMatrix[raw][column].getWeight() + graphMatrix[0][raw].getWeight() < graphMatrix[0][column].getWeight() ) {
                        graphMatrix[0][column].setWeight(graphMatrix[0][raw].getWeight() + graphMatrix[raw][column].getWeight()); // we set the updated weight of the last accessed vertex
                        graphMatrix[0][column].setPrevVertex(graphMatrix[0][raw]); // we insert previous vertex as a record into the last accessed vertex
                        graphMatrix[column][0].setWeight(graphMatrix[0][column].getWeight()); // we fill relevant raw with edge weight
                        graphMatrix[0][raw].setVisited(true); // since we are going to enter into another vertex's raw, let's label current vertex as 'Visited' in order not to enter to it again
                        exploreTheGraph(graphMatrix, column);
                    } else if (raw == 0 && !graphMatrix[raw][column].isVisited()){ // if the vertex is not visited and the raw index is at the beginning, then continue to iterate the graph matrix
                        exploreTheGraph(graphMatrix, column);
                    }
                }
            }
        }
    }

    private void addShortestPath(Vertex targetMatrix, List<Vertex> listOfShortestPath) {
        if (targetMatrix != null) {
            addShortestPath(targetMatrix.getPrevVertex(), listOfShortestPath);
            listOfShortestPath.add(targetMatrix);
        }
    }
}