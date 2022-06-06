package Algorithms.Graphs.TravelingSalesmanProblem;
////////////////////////////////////////////////////// TRAVELING SALESMAN PROBLEM ALGORITHM /////////////////////////////////////////////////////////
//This algorithm is based on user input of vertex number which creates a graph with random edge number and weights.
//This algorithm also makes each vertex to connect with other vertices randomly.
//The edge number is in the range ( vertexNumber <= edgeNumber <= vertexNumber*(vertexNumber-1)/2 ) where algorithm randomly takes the number between this range.
//When we choose origin, it is always placed at the beginning of the matrix by using matrix manipulation ('swapInMatrix' method).
//Since we have to visit each vertex in order to explore and obtain the shortest path, the time complexity rises by factorial frequency.


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TSP {
    private final List<List<Vertex>> possibleWays = new ArrayList<>();
    private final List<Character> randomLettersForVertices = new ArrayList<>();

    private void createPossibleWays(Vertex[][] graph, String originVertexName) {
        int indexOfOriginVertex = 0;

        for(int i = 0; i < graph.length; i++) { // searches for that particular vertex in the graph matrix
            if(graph[0][i].getName().equals(originVertexName)) {
                indexOfOriginVertex = i;
                break;
            }
        }
        this.swapInMatrix(graph, indexOfOriginVertex); // puts our origin vertex at the beginning of the matrix for our convenience.
        this.exploreTheGraph(graph, 0, 0);
    }

    public List<List<Vertex>> findShortestPath() { // finds the shortest way among all possible ways
        int[] totalWeightForEachWay = new int[this.getPossibleWays().size()];
        List<List<Vertex>> shortestPaths = new ArrayList<>();

        if(this.possibleWays.size() != 0) {
            for (int i = 0; i < this.possibleWays.size(); i++) { // sums all weights of each possible way and inserts into the int array
                for (int j = 0; j < this.possibleWays.get(i).size(); j++) {
                    totalWeightForEachWay[i] += this.possibleWays.get(i).get(j).getWeight();
                }
            }
            this.sort(totalWeightForEachWay);

            int shortestPathWeight = totalWeightForEachWay[0]; // gets the smallest value in the array after we sorted it.
            for (List<Vertex> possibleWay : this.possibleWays) {
                int totalWeight = 0;
                for (Vertex vertex : possibleWay) {
                    totalWeight += vertex.getWeight();
                }
                if (totalWeight == shortestPathWeight) { // if the total weight in the list matches with the shortest weight, return that list of path
                    shortestPaths.add(possibleWay);
                }
            }
            return shortestPaths;
        }
        System.out.println("""

                The method can't give neither possible nor shortest path due to three reasons:
                1) many vertices are connected to one vertex only.\s
                2) many vertices do not have a connection to the origin.\s
                3) some vertices might isolated from the other vertices which makes it impossible to visit all the vertices in the graph.""");

        return null; // if there is nothing to return, then it returns null.
    }


    public Vertex[][] getRandomGraph(int vertexNumber) { // creates a random graph based on the vertex number
        Vertex[] vertices = new Vertex[vertexNumber];
        Vertex[][] graphMatrix = new Vertex[vertexNumber][];
        int desiredNumberOfEdges = this.getRandomNumberOfEdges(vertexNumber);

        this.createRandomLetters(vertexNumber); // creates characters of vertex number for vertices in the graph.

        for(int i = 0; i < vertexNumber; i++) { // creates random vertices for the graph
            vertices[i] = new Vertex(String.valueOf(this.randomLettersForVertices.get(i))); // labels each vertex with random letter
        }
        this.randomConnection(vertices, 0, desiredNumberOfEdges); // randomly connects vertices with each other with random weights between them

        for(int i = 0; i < vertexNumber; i++) { // fills the matrix with edge weights
            graphMatrix[i] = new Vertex[vertexNumber];

            for(int j = 0; j < vertexNumber; j++) {
                graphMatrix[i][j] = new Vertex(vertices[j].getName());

                if(i != j && vertices[i].getNeighbourVertex(vertices[j]) != null) { // if there is such a neighbour of that vertex
                    graphMatrix[i][j].setWeight(vertices[i].getNeighbourVertex(vertices[j]).getWeight()); // then it gets that neighbours weight to fill graph matrix with data
                }
                if(i == j) {
                    graphMatrix[i][j].setWeight(0); // the self-vertex must be equal to zero
                }
            }
        }
        return graphMatrix;
    }

    private int maxNumberOfEdges(int vertexNumber) { // gives the maximum edge number which a graph can have.
        return vertexNumber*(vertexNumber-1)/2;
    }

    private int getRandomNumberOfEdges(int vertexNumber) { // gives random number of edges between  the ranges (vertexNumber <= edgeNumber <= maxNumberOfEdges)
        return new Random().nextInt(vertexNumber, this.maxNumberOfEdges(vertexNumber));
    }

    private int getRandomWeight(int limitOfEdges) { // randomly assigns a weight between the ranges (1 <= randomWeight <= maxNumberOfEdges) to a particular edge between the vertices
        return (int)(Math.random() * limitOfEdges + 1);
    }

    private void createRandomLetters(int vertexNumber) { // inserts characters according to the ASCII code table into the list.
        if(vertexNumber > 0) {
            for(int i = 65; i <= 90; i++) {
                this.randomLettersForVertices.add((char) i);
            }
            Collections.shuffle(this.randomLettersForVertices, new Random());
        }
        if(vertexNumber > 25) {
            for (int i = 97; i <= 122; i++) {
                this.randomLettersForVertices.add((char) i);
            }
            Collections.shuffle(this.randomLettersForVertices, new Random());
        }
        if(vertexNumber >= 62) {
            for (int i = 128; i <= 165; i++) {
                this.randomLettersForVertices.add((char) i);
            }
            Collections.shuffle(this.randomLettersForVertices, new Random());
        }
    }
    private char getRandomLetter(int i) {
        return this.randomLettersForVertices.get(i);
    }

    private int vertexIndexToConnect(int totalVertexNumberInGraph) { // gives random index of vertex to connect with.
        return (int)(Math.random() * totalVertexNumberInGraph + 1); // the range of index;
    }

    private void randomConnection(Vertex[] vertices, int currentNumberOfEdges, int desiredNumberOfEdges) { // makes random connections with other vertices based on random index of vertex'.
        if(currentNumberOfEdges != desiredNumberOfEdges){

            for (Vertex vertex : vertices) {
                int vertexToConnect = this.vertexIndexToConnect(vertices.length); // random vertex index in 'vertices' array to make connection with
                int randomWeight = this.getRandomWeight(this.maxNumberOfEdges(vertices.length)); // random weight value between those two vertices

                if (!vertex.getName().equals(vertices[vertexToConnect-1].getName()) && // if random vertex turns out to be itself, then it refuses to make connection with itself
                        vertex.getNeighbourVertex(vertices[vertexToConnect - 1]) == null) { // checks whether there is such vertex as a neighbour

                    vertex.addNeighbours(new Vertex(vertices[vertexToConnect - 1].getName()), randomWeight); // randomly gives weight and adds that weight as a neighbour of a particular vertex
                    vertices[vertexToConnect-1].addNeighbours(new Vertex(vertex.getName()), randomWeight); // we also add our current vertex as a neighbour to that neighbour vertex
                    currentNumberOfEdges++;
                }
                if(currentNumberOfEdges == desiredNumberOfEdges) { // when we reach the desired number of edges in the graph, it stops
                    break;
                }
            }
            randomConnection(vertices, currentNumberOfEdges, desiredNumberOfEdges);
        }
    }

    private void exploreTheGraph(Vertex[][] graph, int startingRow, int visitedVertexNumber) {
        if(visitedVertexNumber != graph.length-1) { // the number of vertices must be equal to the size of a graph matrix.
            for (int j = 0; j < graph.length; j++) {

                if (graph[startingRow][j].getWeight() != 0 && !graph[0][j].isVisited()
                        && !graph[0][j].getName().equals(graph[0][0].getName())) {

                    graph[0][j].setVisited(true); // before we go into another vertex, we label current one as 'visited'
                    visitedVertexNumber++; // since the vertex is labeled as 'visited', we increase the number of visited vertex in the graph.
                    graph[0][j].setPrevVertex(graph[0][startingRow]); // we set previous visited vertex as a record for the next vertex.
                    this.exploreTheGraph(graph, j, visitedVertexNumber);

                    graph[0][j].setVisited(false); // when we come back to our current vertex, it should be 'not visited' because we will have to visit that vertex again.
                    visitedVertexNumber--;
                    graph[0][j].setPrevVertex(null);
                }
            }
        }
        if(visitedVertexNumber == graph.length-1 && graph[startingRow][0].getWeight() != 0) {
            List<Vertex> possiblePath = new ArrayList<>();
            this.addVerticesByTracking(graph[0][startingRow], possiblePath);
            possiblePath.add(graph[startingRow][0]);
            this.possibleWays.add(possiblePath); // we add this list to the list of possible ways
        }
    }

    private void addVerticesByTracking(Vertex vertex, List<Vertex> possiblePath) { // by back tracking the previous vertices, the method adds all the vertices to the list
        if(vertex != null) {
            this.addVerticesByTracking(vertex.getPrevVertex(), possiblePath);
            possiblePath.add(vertex);
        }
    }
    private void sort(int[] totalWeightArray) { // swaps the elements in the array of weights
        for(int i = 0; i < totalWeightArray.length; i++) {
            for(int j = i + 1; j < totalWeightArray.length; j++) {
                if(totalWeightArray[i] > totalWeightArray[j]) {
                    swap(totalWeightArray, i, j);
                }
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void swapInMatrix(Vertex[][] graph, int j) { // swaps rows and columns in the matrix
        Vertex[] temp = graph[0]; // swaps the rows in the matrix.
        graph[0] = graph[j];
        graph[j] = temp;

        for(int k = 0; k < graph.length; k++) { // swaps the columns in the matrix.
            Vertex tempVertex = graph[k][0];
            graph[k][0] = graph[k][j];
            graph[k][j] = tempVertex;
        }
    }
    public List<List<Vertex>> getPossibleWays() {
        return this.possibleWays;
    }
    public void setOriginVertexName(Vertex[][] graph, String originVertexName) {
        this.createPossibleWays(graph, originVertexName);
    }
}
