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