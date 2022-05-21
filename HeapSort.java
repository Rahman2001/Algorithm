package Algorithms; //////////////////////////////////    HEAPSORT  /////////////////////////////////////////////
// remember that there are some rules for maxHeap:
// 1) half of the array are parent nodes and other ones are leaves of a tree
// 2) the parent nodes must be always have higher value than its child nodes
// 3) the construction of tree for mapHeap starts from the half index of array and decrements to the zero because of the rule 1)

import java.util.Arrays;

public class HeapSort {
    public int[] heapSort(int[] array) {
        buildMaxHeap(array);
        int heapSize = array.length-1;
        for(int i = heapSize; i > 0; i--) {
            swap(array, 0, i); // we swap the highest value (in zero index) with the unknown value (int the last index)
            heapSize--; // we decrement the size because the last element of array is the highest one, and we don't want to include it into maxHeap sorting
            maxHeap(array, 0, heapSize); // since there is unknown value (from the last element of array before swapping) we need to sort elements in array with maxHeap
        }
        return array;
    }
    public void buildMaxHeap(int[] array) {
        for(int i = array.length/2 -1; i >= 0; i--) {
            maxHeap(array, i, array.length-1);
        }
    }
    public void maxHeap(int[] array, int parentIndex, int size) {
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = 2* parentIndex + 2;
        int trackingIndex = parentIndex;

        if( leftChildIndex <= size && array[leftChildIndex] > array[parentIndex]) {
            trackingIndex = leftChildIndex;
        }
        if( rightChildIndex <= size && array[rightChildIndex] > array[parentIndex] && array[leftChildIndex] < array[rightChildIndex]) {
            trackingIndex = rightChildIndex;
        }
        if(trackingIndex != parentIndex) { // if our parent index is updated, then track our previous 'parentIndex' to check if it is less than its child nodes (if they exist)
            swap(array, parentIndex, trackingIndex); // we update our parent index by swapping it with one of its child node if child node had a higher value than parent node
            maxHeap(array, trackingIndex, size); // check again if our trackingIndex (parentIndex which was swapped by its child node) has less or higher value than its own child nodes
        }
    }
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] array = {0, 15, 2, 10, 22, 21, 4};
        System.out.println("Array before sorting with Heap Sort: " + Arrays.toString(array));
        int[] sortedArray = heapSort.heapSort(array);
        System.out.println("Array after sorting with Heap Sort: " + Arrays.toString(sortedArray));
    }
}
