/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-23
 * Code Updated: 2021-09-23
 * Problem: 
 * Sources: None
*/
import java.util.Scanner;

public class L2Uppgift4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.close();
    }

    /**
     * Sorts the array using the insertion sort algorithm. By first
     * having a outer loop that iterates through the array and a inner
     * loop that iterates through the array between the index and 
     * the first element or if the other index is less than index.
     * 
     * @param array
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

    public static void mergeSort(int[] array) {
        int[] tempArray = new int[array.length];
        mergeSortInner(array, tempArray, 0, array.length - 1);
    }

    public static void mergeSortInner(int[] array, int[] tempArray, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSortInner(array, tempArray, left, middle);
            mergeSortInner(array, tempArray, middle + 1, right);
            merge(array, tempArray, left, middle, right);
        }
    }
}
