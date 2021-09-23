/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-23
 * Code Updated: 2021-09-23
 * Problem: Implement Insertion sort, merge sort and quick sort. The goal
 * is also to time the sorting algorithms.
 * Sources: https://algs4.cs.princeton.edu/20sorting/
*/
import java.util.Scanner;

public class L2Uppgift4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] array = {1,3,1,4,7,1,8,3,1,9,2,3,1};
        quickSort(array);
        for (int i : array) System.out.print(i + " ");

        input.close();
    }

    /**
     * Sorts the array using the insertion sort algorithm. By first
     * having a outer loop that iterates through the array and a inner
     * loop that iterates through the array between the index and 
     * the first element or if the other index is less than index.
     * 
     * @param array The array to be sorted.
     */
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    /**
     * The caller for the merge sort algorithm.
     * 
     * @param array The array to be sorted.
     */
    public static void mergeSort(int[] array) {
        mergeSortInner(array, 0, array.length - 1);
    }

    /**
     * Recursive method for the algorithm. Starts with comparing if left
     * is less than right If it is we define the middle of both left and
     * right. We than divide the array in half and call the method again.
     * After the recursive call we merge the two arrays.
     * 
     * @param array The array to be sorted.
     * @param left The left index of the array.
     * @param right The right index of the array.
     */
    public static void mergeSortInner(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSortInner(array, left, middle);
            mergeSortInner(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    /**
     * Merges two arrays together by creating to temporary arrays, a lower and a upper.
     * We first fil the arrays with the correct elements from the start array. We than
     * compare those elements and which of those elements that should be placed in the
     * correct position in the main array.
     * 
     * @param array The array to be sorted.
     * @param left The left index of the array.
     * @param middle The middle index of the array.
     * @param right The right index of the array.
     */
    public static void merge(int[] array, int left, int middle, int right) {
        int lower = middle - left + 1;
        int upper = right - middle;
        int array1Index = 0;
        int array2Index = 0;
        
        int array1[] = new int[lower];
        int array2[] = new int[upper];
    
        for (int i = 0; i < lower; i++) array1[i] = array[left + i];
        for (int j = 0; j < upper; j++) array2[j] = array[middle + 1 + j];
    
        while (array1Index < lower && array2Index < upper) {
            if (array1[array1Index] < array2[array2Index]) array[left] = array1[array1Index++];
            else array[left] = array2[array2Index++];
            left++;
        }
    
        while (array1Index < lower) array[left++] = array1[array1Index++];
        while (array2Index < upper) array[left++] = array2[array2Index++];
    }

    /**
     * The caller for the quick sort algorithm.
     * 
     * @param array The array to be sorted.
     */
    public static void quickSort(int[] array) {
        quickSortInner(array, 0, array.length - 1);
    }

    /**
     * Recursive method for the algorithm. Starts with comparing if left 
     * is less than right. If it is we start by sorting the array. After it
     * has been sorted we get the pivot position. We than call the function 
     * again with the left and right indexes and the offset for the pivot.
     * 
     * @param array The array to be sorted.
     * @param left The left index of the array.
     * @param right The right index of the array.
     */
    public static void quickSortInner(int[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            quickSortInner(array, left, pivot - 1);
            quickSortInner(array, pivot + 1, right);
        }
    }
    
    /**
     * We start by getting the pivot element which is the last
     * element in the array. We also define the amount of lower
     * elements than the pivot (i). We than swap if a element is lower
     * than the pivot. After the loop is done we swap the pivot with the
     * last lower element + 1. Which means that the pivot is now in the
     * correct position.
     * 
     * @param array The array to be sorted.
     * @param left The left index of the array.
     * @param right The right index of the array.
     * @return The pivot position.
     */
    public static int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left;   
        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }
        int temp = array[i];
        array[i] = array[right];
        array[right] = temp;

        return i;
    }
}
