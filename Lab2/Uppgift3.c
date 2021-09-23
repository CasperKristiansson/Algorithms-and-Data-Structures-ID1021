/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-23
 * Code Updated: 2021-09-23
 * Problem: Implement a function which sorts an array of integers by either
 * negative, positive. Which means that all negative numbers should be
 * stored in the beginning of the array and all positive numbers in the end. But the order
 * of either the positive or negative numbers should be the same as in the original array.
 * Sources: https://algs4.cs.princeton.edu/20sorting/
*/

#include <stdio.h>
/** 
 * A test for for the modified insertion sort algorithm which
 * sorts an array of integers by if they are either negative or positive.
 */
int main() {
    int size;
    printf("Enter the size of the array: \n");
    scanf("%d", &size);
    int array[size];
    printf("Enter the array: \n");
    for (int i = 0; i < size; i++) scanf("%d", &array[i]);

    sortNegativePositive(array, size);
    for (int i = 0; i < size; i++) printf("%d ", array[i]);

    return 0;
}

/**
 * Modified insertion sort algorithm which sorts an array of integers
 * by if they are either negative or positive. If the outerloop numbers
 * are positive, we skip the innerloop and move on to the next number.
 *
 * @param array The array to be sorted.
 * @param size The size of the array. 
 */
void sortNegativePositive(int array[], int size) {
    int temp, j;
    for (int i = 1; i < size; i++) {
        if (array[i] >= 0) continue;

        temp = array[i];
        j = i - 1;
        while (j >= 0 && array[j] >= 0) {
            array[j + 1] = array[j];
            j--;
        }
        array[j + 1] = temp;
        for (int k = 0; k < size; k++) printf("%d ", array[k]); printf("\n");
    }
}
