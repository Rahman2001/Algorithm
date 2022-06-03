package Algorithms.Graphs.Finding_Isomorphism;
//////////////////////////////////////////////////////// ISOMORPHISM IN GRAPHS /////////////////////////////////////////////////////
//There are some rules applied in order to find isomorphism between the graphs:
// 1) The number of vertices of both graphs must be equal
// 2) The degree of each vertex of both graphs must be equal
// 3) The set of vertices of each vertex of both graphs must be equal
// Violation of one of the rules results in false isomorphism of the graphs

public class Isomorphism {

    public boolean areIsomorphic(int[][] graphA, int[][] graphB) {
        return this.hasEqualVertex(graphA, graphB) && this.hasEqualDegrees(graphA, graphB)
                && this.hasSameConnections(graphA, graphB);
    }

    public boolean hasEqualVertex(int[][] graphA, int[][] graphB) {
        return this.vertexNumber(graphA) == this.vertexNumber(graphB);
    }

    public boolean hasEqualDegrees(int[][] graphA, int[][] graphB) {
        int[] graphADegrees = this.degreeNumber(graphA);
        int[] graphBDegrees = this.degreeNumber(graphB);

        return graphADegrees.length == graphBDegrees.length && this.areEqualArrays(graphADegrees, graphBDegrees);
    }

    public boolean hasSameConnections(int[][] graphA, int[][] graphB) {
        int matchedConnections = 0;

        for(int i = 0; i < graphA.length; i++) {
            if(this.areEqualArrays(graphA[i], graphB[i])){
                matchedConnections++;
            }
        }
        return matchedConnections == graphA.length; // if both graphs' vertices have the same connections, then it's true.
    }

    public int vertexNumber(int[][] graph) { // the graph matrix might be empty which is null. If it is empty, we cannot call length method of that matrix since it will throw exception error
        return graph != null ? graph.length : 0;
    }

    public int[] degreeNumber(int[][] graph) { // this method calculates the number of degrees in each vertex in the graph
        int[] degreeArray = new int[graph.length];
        int index = 0;
        for(int[] vertex : graph) {
            int degree = 0; // each of vertex's degree should start from zero.

            for (int i = 0; i < graph.length; i++) {
                if(vertex[i] > 0) { // the edge value must not be zero or negative value
                    degree += vertex[i];
                }
            }
            if(degree > 0) { // if the vertex is not isolated, then it has at least one degree
                degreeArray[index] = degree;
            }
            index++;
        }
        return sort(degreeArray, graph); // we sort degree values in order to compare each value with another array of degrees
    }

    private int[] sort(int[] degreeArray, int[][] graph) { // swaps the raws in the graph and elements in the array of degrees
        for(int i = 0; i < degreeArray.length; i++) {
            for(int j = i + 1; j < degreeArray.length; j++) {
                if(degreeArray[i] > degreeArray[j]) {
                    swap(degreeArray, i, j, graph);
                }
            }
        }
        return degreeArray;
    }

    private void swap(int[] array, int i, int j, int[][] graph) {
        int temp = array[i]; // swap the elements in the array of degrees
        array[i] = array[j];
        array[j] = temp;

        int[] tempArray = graph[i]; // swap raws in the graph
        graph[i] = graph[j];
        graph[j] = tempArray;

        for(int k = 0; k < graph.length; k++) { // swap columns in the matrix since we swapped matrix's raws
            temp = graph[k][i];
            graph[k][i] = graph[k][j];
            graph[k][j] = temp;
        }
    }

    private boolean areEqualArrays(int[] graphADegrees, int[] graphBDegrees) { // compare values in the same index of both arrays
        int matchedDegreeNumber = 0;
        for(int i = 0; i < graphADegrees.length; i++) {
            if(graphADegrees[i] == graphBDegrees[i]) {
                matchedDegreeNumber++;
            }
        }
        return matchedDegreeNumber == graphADegrees.length; // matched ones are equal to the size of array, then both arrays are equal
    }
}
