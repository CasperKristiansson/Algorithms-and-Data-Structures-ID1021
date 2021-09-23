/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-23
 * Code Updated: 2021-09-23
 * Problem: Implement a insertion sort which sorts an array of integers. This
 * exercise the goal is also to implement a function which prints the 
 * insertions and the amount of insertions.
 * 
 * The time complexity of insertion sort is:
 * Best case: O(n)
 * Average case: O(n^2)
 * Worst case: O(n^2)
 * 
 * Note that the modified insertion sort includes a extra nested for loop
 * which means that the time complexity is worse.
 * 
 * Sources: https://algs4.cs.princeton.edu/20sorting/
*/
import java.util.Scanner;

public class L2Uppgift2 {
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
        for (int k : array) System.out.print(k + " "); System.out.println();
        insertionSortInversions(array);
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

    /**
     * The method insertionSortInversions is the same as insertionSort
     * except that we count the number of inversions in the array and
     * print it out. The inversions is printed each time the array is
     * altered in the inner loop.
     * 
     * @param array
     */
    public static void insertionSortInversions(int[] array) {
        int[] arrayCopy = array.clone();
        int numberOfInversions = 0;

        for (int i = 1; i < arrayCopy.length; i++) {
            int temp = arrayCopy[i];
            int j = i - 1;
            while (j >= 0 && arrayCopy[j] > temp) {
                System.out.println("[" + i + ", " + array[i] + "]" + ", [" + j + ", " + array[j] + "]");
                numberOfInversions++;
                arrayCopy[j + 1] = arrayCopy[j];
                j--;
            }
            arrayCopy[j + 1] = temp;
        }
        System.out.println("Number of Inversions: " + numberOfInversions);
    }
}
