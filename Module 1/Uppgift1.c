/*
In C implement a recursive and an iterative version of a function which reads characters from stdin until a
newline character is read and then prints them on stdout in reverse order. Hint: use getchar(), putchar()
(or getc(), putc()). For the iterative version you may assume a fixed max length of the input.
*/

#include <stdio.h>

int main() {
    printf("Enter Characters For The Recursive Version\n");
    recursive();
    
    printf("\nEnter Characters For The Iterative Version\n");
    iterative();
    
    return 0;
}

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

void recursive() {
    int character = getchar();
    if(character == '\n')
        return;

    recursive();
    putchar(character);
}