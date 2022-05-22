package Algorithms; ///////////////////////////////////  Bucket Sort /////////////////////////////////////////////////
// There are some rules for this algorithm:
// 1) This algorithm sorts only float type arrays at the moment (the improvement for sorting integer arrays is coming).
// 2) It is accepted that the number of buckets should be equal tho the size of the given array
// 3) This algorithm uses built-in Quicksort in order to sort the values in each bucket
// 4) After inserting each value of array and sorting them in the buckets, we gather them all into the array
// 5) That's why it is called scatter-gather approach
// 6) Time Complexity: (Best, Average, Worst Case: O(n)   ) ( because we use the n number of buckets and gather n number of values into the array )


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BucketSort {
    public void bucketSort(float[] array, int sizeOfArray) {
        ArrayList<Float>[] buckets = new ArrayList[sizeOfArray]; // we create buckets for an array

        for(int i = 0; i < sizeOfArray; i++) {
            buckets[i] = new ArrayList<Float>(); //we create empty buckets for each index in array
        }
        for(int i = 0; i < sizeOfArray; i++) {
            int index = (int) array[i] * sizeOfArray; // we calculate bucket index to which the element should be added (if 0.15, then 0.15*sizeOfArray(7) = 1(bucket index) )
            buckets[index].add(array[i]); // we add elements in array to the buckets by calculated bucket index (if 0.12, then the bucket index is 0)
        }
        for(int i = 0; i < sizeOfArray; i++) {
            Collections.sort(buckets[i]); // we use built in Quicksort for sorting values in each bucket
        }
        int index = 0;
        for(int i = 0; i < sizeOfArray; i++) { // we access each bucket by their index
            for(int j = 0, size = buckets[i].size(); j < size; j++) { // we access each element of buckets[i]
                array[index++] = buckets[i].get(j); // we fill cells of array by the values of buckets[i]
            }
        }
    }
    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        float[] array = {(float) 0.11, (float) 0.23, (float) 0.21, (float) 0.112 };
        System.out.println("Array before using Bucket Sort: " + Arrays.toString(array));

        bucketSort.bucketSort(array, array.length);
        System.out.println("Array after using Bucket Sort: " + Arrays.toString(array));
    }
}
