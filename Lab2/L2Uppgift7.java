/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-25
 * Code Updated: 2021-09-25
 * Problem: The task is to sort a array of integers in descending order using
 * a insertion sort which may not be modified. The goal is to inverse the ascending
 * array into descending order.
 * Sources: https://algs4.cs.princeton.edu/20sorting/
*/
import java.util.Scanner;

public class L2Uppgift7 {
    /**
     * The test for the inverse of the ascending array.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the size of the array: ");
        int[] array = new int[input.nextInt()];

        System.out.println("Enter the integers separated with enter: ");
        for (int i = 0; i < array.length; i++) array[i] = -input.nextInt();    
        input.close();

        insertionSort(array);

        for(int i = 0; i < array.length; i++) array[i] = -array[i];
        for(int i : array) System.out.print(i + " ");
    }
    /**
     * Sorts the array using the insertion sort algorithm. By first
     * having a outer loop that iterates through the array and a inner
     * loop that iterates through the array between the index and 
     * the first element or if the other index is less than index.
     * 
     * @param array the array to be sorted
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
     * The method insertionSortInversions is the same as insertionSort
     * except that we count the number of inversions in the array and
     * print it out. The inversions is printed each time the array is
     * altered in the inner loop.
     * 
     * @param array
     */
    public static void insertionSortInversions(int[] array) {
        int numberOfInversions = 0;

        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                System.out.println("[" + i + ", " + temp + "]" + ", [" + j + ", " + array[j] + "]");
                numberOfInversions++;
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
        System.out.println("Number of Inversions: " + numberOfInversions);
    }
}
