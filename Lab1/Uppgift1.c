/*
Author: Casper Kristiansson
Code Generated: 2021-09-07
Code Updated: 2021-09-09
Problem: Implement a iterative and recursive method to reverse a string.
Sources: None
*/

#include <stdio.h>

int main() {
    printf("Enter Characters For The Recursive Version\n");
    recursive();
    
    printf("\nEnter Characters For The Iterative Version\n");
    iterative();
    
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
 * Recursive Version of the String Reversal which reverses the string
 * reading the input character. It than calls itself to read the next
 * character. When the end of the string is reached, (character == '\n')
 * the function returns. Which in turn prints the character in reverse.
 */
void recursive() {
    int character = getchar();
    if(character == '\n')
        return;

    recursive();
    putchar(character);
}