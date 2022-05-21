package Algorithms;  ///////////////////////////////////    QUICKSORT /////////////////////////////////////////////
// There are some rules for Quicksort :
// 1) there are two pointers in the array (left and right) which moves towards each other until they meet up
// 2) if leftPointer encounters a value which is equal or greater than the chosen pivot, it must stop right there
// 3) if rightPointer encounters a value which is equal or less than the chosen pivot, it must stop right there
// 4) both pointers swap each other's value to continue to move
// 5) if both pointers are on the same index of the given array, then leftPointer's value and the pivot are swapped with each other
// 6) the process starts from the beginning by sorting the left side of the pivot and the right side of the pivot recursively until there is no element to sort

import java.util.Arrays;

public class Quicksort {
    public void quicksort(int[] array, int lowIndex, int highIndex) {

        if(lowIndex >= highIndex) { // we, first, check if lowIndex violates the rules for sorting the array
            return;
        }

        int leftPointer =  lowIndex;
        int rightPointer = highIndex;
        int pivot = array[highIndex]; // we choose last index of array as a pivot

        while (leftPointer < rightPointer) {
            while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(array, leftPointer, rightPointer); // we swap the value in leftPointer with rightPointer if there is a value violating the rule in while loop
        }
        swap(array, leftPointer, highIndex); // we swap the leftPointer index with index of pivot which is highIndex
        quicksort(array, lowIndex, leftPointer - 1); //we sort the left side of the pivot of the given array
        quicksort(array, leftPointer + 1, highIndex); // we sort the right side of the pivot of the given array
    }

    public void swap(int[] array, int value1, int value2) {
        int temp = array[value1];
        array[value1] = array[value2];
        array[value2] = temp;
    }

    public static void main(String[] args) {
        Quicksort quicksort = new Quicksort();
        int[] array = {2,5,1,2,71,10,0};
        System.out.println("Array before using Quicksort: " + Arrays.toString(array));

        quicksort.quicksort(array, 0, array.length-1);
        System.out.println("Array after using Quicksort: " + Arrays.toString(array));
    }
}
