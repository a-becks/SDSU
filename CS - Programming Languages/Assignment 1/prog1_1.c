#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

int main(){
    
    char buffer[65];
    char currentString[65];

    char **tokens;

    char *pointer;
    int size;
    int num_tokens = 0;
    int counter;    
    
    //header
    printf("Assignment #1-1, Allison Schroder, aschroder@sdsu.edu\n");

    //read in entire string to newline and store in 'buffer'
    fgets(buffer, 256, stdin);
    counter = 0;
    while(isspace(buffer[strlen(buffer) - 1 - counter])){
        counter++;       
    }

    counter--;
    buffer[strlen(buffer) - counter - 1] = '\n';
    
    //set pointer to the start of buffer, use sscanf to count the number of substrings in the string based on whitespace
    pointer = buffer;
    while (*pointer != '\n'){
        sscanf(pointer, " %s%n ", currentString, &size);
        num_tokens++;
        pointer = pointer + size;
    }
    
    //set size of pointer array from number of words
    tokens = (char**) malloc(sizeof(char*) * num_tokens);
    
    //use sscanf again to store words
    counter = 0;
    pointer = buffer;
    while (*pointer != '\n'){
        sscanf(pointer, " %s%n ", currentString, &size);
        tokens[counter] = (char*) malloc(sizeof(char) * (strlen(currentString) + 1));
        strcpy(tokens[counter], currentString);
        pointer = pointer + size;
        counter++;
    }

    //header

    for (int i = 0; i < num_tokens; i ++){
        printf("=%s=\n", tokens[i]);
    }

}
