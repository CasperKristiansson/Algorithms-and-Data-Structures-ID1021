/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-24
 * Code Updated: 2021-09-24
 * Problem: Implement a cutoff in the merge sort algorithm. The cutoff is used to determine when to switch to insertion sort.
 * if the array is smaller than the cutoff.
 * Sources: https://algs4.cs.princeton.edu/20sorting/, Algorithms 4th Edition, Section 2.3 Mergesort,
 * Section 2.3 Quicksort
*/
public class L2Uppgift5 {
    /**
     * The test for the improved merge sort algorithm by using a cutoff
     * element to determine when to switch to insertion sort. We first generate
     * a array with a size and than randomly fill it with integers. We also run each test
     * multiple times. We also test by inputting the cutoffs between 0-30.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numberOfTests = 1;
        int arraySizePotence = 2;
        int arrayOffset = 1;

        int[] array = new int[(int)Math.pow(10, arraySizePotence) * arrayOffset];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 100);
        }

        // for (int i = 0; i < 31; i++) {
        //     long startTime = System.nanoTime();
        //     for (int j = 0; j < numberOfTests; j++) {
        //         mergeSortCutoff(array, i);
        //     }
        //     long endTime = System.nanoTime();
        //     long duration = (endTime - startTime);
        //     System.out.println("Merge sort cutoff: " + duration / numberOfTests + " nanoseconds\t" + "Cutoff: " + i);
        // }

        mergeSortCutoff(array, 1);
        for (int i : array) System.out.print(i + " ");
        
    }

    /**
     * The method is a modified insertion sort which sorts the array with a left
     * and right index. Otherwise it follows the general insertion sort algorithm.
     * 
     * @param array the array to be sorted
     * @param left the left index of the array
     * @param right the right index of the array
     */
    public static void insertionSort(int[] array, int left, int right) {
        for (int i = left; i <= right; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= left && array[j] > temp) {
                array[j + 1] = array[j--];
            }
            array[j + 1] = temp;
        }
    }

    /**
     * The caller for the merge sort algorithm.
     * 
     * @param array The array to be sorted
     * @param cutoff The cutoff to determine when to switch to insertion sort.
     */
    public static void mergeSortCutoff(int[] array, int cutoff) {
        mergeSortInner(array, 0, array.length - 1, cutoff);
    }

    /**
     * The method is the recursive part of the merge sort algorithm. We first
     * check if the part array size is less than the cutoff. If it is we call
     * the insertion sort method. Otherwise we split the array using a middle
     * index and call the mergeSortInner again.
     * 
     * @param array The array to be sorted
     * @param left  The left index of the array
     * @param right The right index of the array
     * @param cutoff The cutoff to determine when to switch to insertion sort.
     */
    public static void mergeSortInner(int[] array, int left, int right, int cutoff) {
        if (right - left < cutoff) insertionSort(array, left, right);

        else if (left < right) {
            int middle = (left + right) / 2; 
            mergeSortInner(array, left, middle, cutoff);
            mergeSortInner(array, middle + 1, right, cutoff);
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
            if (array1[array1Index] < array2[array2Index]) array[left++] = array1[array1Index++];
            else array[left++] = array2[array2Index++];
        }
    
        while (array1Index < lower) array[left++] = array1[array1Index++];
        while (array2Index < upper) array[left++] = array2[array2Index++];
    }
}
