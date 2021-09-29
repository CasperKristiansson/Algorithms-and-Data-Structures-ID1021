/**
 * @author Casper Kristiansson
 * Code Generated: 2021-09-29
 * Code Updated: 2021-09-29
 * Problem:
 * Sources: 
 */

#include <stdio.h>
#include <stdlib.h>

int main() {
    filterInput();
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

void filterInput() {
    char *string;
    int size = 1;
    int c;
    string = malloc(1 * sizeof(char));

    printf("Enter your text: ");

    for(int i = 0; (c = getchar()) && c != EOF; ++i){
        if(i == size){
            size = 2*size;
            string = realloc(string, size*sizeof(char));
        }

        if(!isalpha(c) && c != ' ' && c != '\n'){
            string[i] = ' ';
        }else{
            string[i] = c;
        }
    }

    printf("\nThe filtered text: %s", string);
}
