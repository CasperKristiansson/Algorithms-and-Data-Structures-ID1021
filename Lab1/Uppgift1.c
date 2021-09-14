/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-07
 * Code Updated: 2021-09-14
 * Problem: Implement a iterative and recursive method to reverse a char array.
 * The user can input and choose which word should be reversed. The iterative 
 * version has a fixed length of 10.
 * Sources: None
 * 
 * Recursion - Good for when the code size needs to be small, time complexity
 * could be very high. In our example the recursive version have the same as iterative
 * version. O(n). Recursion also takes up more auxiliary space. Each recursive call
 * the temporary variable will be created. But some times a recursive function
 * could be more elegant.
 * 
 * Iterative - Good for when the code size needs to be small, time complexity could be
 * very low. In our example the iterative version have the same as recursive version.
 * O(n).
 * 
 * Because the recursive version is more elegant but more complex, the iterative version
 * is a easier to understand and implement.
*/

#include <stdio.h>

int main() {
    printf("Enter Characters For The Iterative Version\n");
    iterative();

    printf("\nEnter Characters For The Recursive Version\n");
    recursive();
    
    return 0;
}
/**
 * Iterative Version of the String Reversal which reverses the string
 * by inputing each character into a char array which has a fixed size.
 * We than iterate through the array and print each character.
 */
void iterative() {
    int length = 10;
    char characters[length];
    int index = 0;

    for(int i = 0; i < length; i++) {
        int character = getchar();
        if(character == '\n') {
            break;
        }
        characters[i] = character;
        index++;
    }

    for(; index >= 0; index--){
        printf("%c", characters[index]);
    }
}

/**
 * Recursive Version of the word reverse works by reading the input characters.
 * It than calls itself to read the next character. When the end of the char is reached,
 * (character == '\n') the function returns. Which in turn prints the characters in reverse.
 */
void recursive() {
    int character = getchar();
    if(character == '\n')
        return;

    recursive();
    putchar(character);
}
