/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-23
 * Code Updated: 2021-09-23
 * Problem: Implement a insertion sort which sorts an array of integers. Each time the inner
 * loop is executed the array is printed out. The number of swaps is also printed out after the
 * array is sorted.
 * Sources: https://algs4.cs.princeton.edu/20sorting/
*/
import java.util.Scanner;

public class Uppgift1 {
    /** 
     * A test for for the insertion sort algorithm. The user
     * is asked to input the size of the array and the elements
     * of the array. The array is then sorted and printed out.
     * 
     * @param args command line arguments
     */
    public static void main(String argv[]) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the size of the array: ");
        int[] array = new int[input.nextInt()];

        System.out.println("Enter the integers separated with enter: ");
        for (int i = 0; i < array.length; i++) array[i] = input.nextInt();    
        input.close();

        insertionSort(array);
        for (int k : array) System.out.print(k + " ");
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
        for (int k : array) System.out.print(k + " "); System.out.println();
        int swaps = 0;

        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
                for (int k : array) System.out.print(k + " "); System.out.println("\t" + temp);
            }
            array[j + 1] = temp;
            if (j != i - 1) swaps++;
        }
        System.out.println("Swaps Performed: " + swaps);
    }
}
