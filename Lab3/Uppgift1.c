/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-29
 * Code Updated: 2021-09-29
 * Problem:
 * Sources: None
 */

#include <stdio.h>
#include <stdlib.h>

int main() {
    filterText();
    return 0;
}

void filterText() {
    char *str;
    int c;
    int size = 1;
    str = malloc(1 * sizeof(char));

    printf("\nEnter text to be filtered: ");
    for(int i = 0; (c = getchar()) && c != EOF; ++i){
        if(i == size){
            size = 2*size;
            str = realloc(str, size*sizeof(char));
        } 
        str[i] = c;
    }

    for(int i = 0; i < size; i++){
        if(str[i] != isalpha() && str[i] != ' ' && str[i] != '\n'){
            str[i] = ' ';
        }
    }

    printf("\nFiltered Text: %s\n", str);
}
