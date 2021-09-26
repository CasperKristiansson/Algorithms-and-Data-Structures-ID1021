/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-25
 * Code Updated: 2021-09-25
 * Problem: Implement two modified functions for quick sort, one which
 * chooses the pivot element as the first in the sub array and one
 * which uses median of three to determine the pivot element.
 * Sources: https://algs4.cs.princeton.edu/20sorting/, Algorithms 4th Edition, Section 2.3 Quicksort
*/
public class L2Uppgift6 {
    /**
     * A test for the two modified quicksort functions. Each test performs with
     * a random given array of different sizes. With the lower sizes the
     * test is run multiple times to get a average time which will result in
     * a more accurate time.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numberOfTests = 10;
        int arraySizePotence = 5;
        int arrayOffset = 5;

        int[] array2 = new int[(int)Math.pow(10, arraySizePotence) * arrayOffset];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = (int)(Math.random() * 100000);
        }
        
        long duration = 0;
        long duration2 = 0;
        
        for (int i = 0; i < numberOfTests; i++) {
            int[] array = new int[(int)Math.pow(10, arraySizePotence) * arrayOffset];
            for (int j = 0; j < array.length; j++) array[j] = (int)(Math.random() * 100000);

            long startTime = System.nanoTime();
            quickSortLeftPartitioning(array.clone());
            long endTime = System.nanoTime();
            duration += (endTime - startTime);

            startTime = System.nanoTime();
            quickSortMedianOfThree(array.clone());
            endTime = System.nanoTime();
            duration2 += (endTime - startTime);
        }

        System.out.println("Quick sort left partitioning:\t" + duration / numberOfTests + " nanoseconds");
        System.out.println("Quick sort median of three:\t" + duration2 / numberOfTests + " nanoseconds");

        // for (int i : array2) System.out.print(i + " "); System.out.println("\n");
        // quickSortLeftPartitioning(array2);
        // for (int i : array2) System.out.print(i + " ");
        // System.out.println("\n" + isSorted(array2));
    }

    /**
     * Helper function to check whenever a array is sorted
     * or not.
     * 
     * @param array the array to check
     * @return true if the array is sorted, otherwise false
     */
    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) if (array[i] > array[i + 1]) return false;
        return true;
    }

    /**
     * The caller for the modified quick sort algorithm.
     * 
     * @param array The array to be sorted.
     */
    public static void quickSortLeftPartitioning(int[] array) {
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
     * We start by getting the pivot element which in this version is the
     * first element. We than need to defined which elements that should
     * be compared. In this case we compare elements between left + 1 until
     * right. We lastly swap the pivot element to the correct position which
     * is the pivot correct position.
     * 
     * @param array The array to be sorted.
     * @param left The left index of the array.
     * @param right The right index of the array.
     * @return The pivot position.
     */
    public static int partition(int[] array, int left, int right) {
        int pivot = array[left];
        int i = left + 1; 
        for (int j = left + 1; j <= right; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i - 1, left);
        return i - 1;
    }

    /**
     * The caller for the modified quick sort algorithm.
     * 
     * @param array The array to be sorted.
     */
    public static void quickSortMedianOfThree(int[] array) {
        quickSortInnerMedianOfThree(array, 0, array.length - 1);
    }

    /**
     * Recursive method for the algorithm. We first check if the
     * array / sub array size is less than 3. If it is we call the
     * medianOfThree function. We also check if the left index is less
     * than the right index. If it is we start by sorting the array using
     * partition modified with median of three.
     * 
     * @param array The array to be sorted.
     * @param left The left index of the array.
     * @param right The right index of the array.
     */
    public static void quickSortInnerMedianOfThree(int[] array, int left, int right) {
        if (right - left < 3) medianOfThree(array, left, right);
        else if (left < right) {
            int pivot = partitionMedianOfThree(array, left, right);
            quickSortInnerMedianOfThree(array, left, pivot - 1);
            quickSortInnerMedianOfThree(array, pivot + 1, right);
        }
    }
    
    /**
     * A function which calculates the median of three by first calculating the
     * middle index of the array. We than compare and sort those three elements
     * (left, middle, right). We than return the middle value position.
     * 
     * @param array The array to be sorted.
     * @param left The left index of the array.
     * @param right The right index of the array.
     * @return The new pivot index.
     */
    public static int medianOfThree(int[] array, int left, int right) {
        int middle = (left + right) / 2;
        if (array[left] > array[middle]) swap(array, left, middle);
        if (array[left] > array[right]) swap(array, left, right);
        if (array[middle] > array[right]) swap(array, middle, right);

        return middle;
    }

    /**
     * A swap function which swaps the elements of two 
     * positions of an array.
     * 
     * @param array The array containing the two elements.
     * @param index1 The index of the first element.
     * @param index2 The index of the second element.
     */
    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * The modified quick sort algorithm which uses the 
     * next to last element as the pivot. The algorithm uses
     * median of three to improve the performance.
     * 
     * @param array The array to be sorted.
     * @param left The left index of the array.
     * @param right The right index of the array.
     * @return The pivot position.
     */
    public static int partitionMedianOfThree(int[] array, int left, int right) {
        int middle = medianOfThree(array, left, right);
        swap(array, middle, right - 1);
        int pivot = array[right - 1];
        int i = left + 1;

        for (int j = i; j < right - 1; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, right - 1);
        return i;
    }
}
