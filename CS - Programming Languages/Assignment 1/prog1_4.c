#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

void printHeader();
void fixTrailingWhitespace(char*);
int countTokens(char*);
void storeTokens(char**, char*);
void printTypeOfToken(char**, int*);

int main(){
    
    char buffer[65];

    char **tokens;

    int num_tokens;

    int quit_flag = 0;


    
    
    printHeader();

    
    //continue prompting until 'quit' entered
    while(quit_flag == 0){

        num_tokens = 3;

    //while loop until there are one or two tokens from 1_3
    //including 20 characters for 1_4
    while(num_tokens > 2 || num_tokens < 1 ){
        num_tokens = 0;

        //ask user for input

        printf("> ");
    
        //read in entire string to newline and store in 'buffer'
        fgets(buffer, 256, stdin);

        if((int)(strlen(buffer) -1) > 20){
            printf("ERROR! Input string too long.\n");
            continue;
        }

        fixTrailingWhitespace(buffer);

        num_tokens = countTokens(buffer);
        
        //error message if user has entered more than 2 tokens
        if(num_tokens > 2 || num_tokens < 1){
            printf("ERROR! Incorrect number of tokens found.\n");
        }
    }
    
    //set size of pointer array from number of words
    tokens = (char**) malloc(sizeof(char*) * num_tokens);

    storeTokens(tokens, buffer);

    //if 'quit' is entered, exit while loop
    if (strcasecmp(tokens[0],"QUIT") == 0){
        quit_flag = 1;
        continue;
    }
      
    printTypeOfToken(tokens, &num_tokens);

    free(tokens);

    }
    
}

//////////////////////////////////////////////////////////////////////

    void printHeader(){
    //header
        printf("Assignment #1-4, Allison Schroder, aschroder@sdsu.edu\n");
    }


    void fixTrailingWhitespace(char* buffer){
        int counter = 0;
        while(isspace(buffer[strlen(buffer) - 1 - counter])){
            counter++;       
        }

        counter--;
        buffer[strlen(buffer) - counter - 1] = '\n';
    }


    int countTokens(char* buffer){
        int size;
        int num_tokens = 0;
        char* pointer;
        char string[256];

    //set pointer to the start of buffer, use sscanf to count the number of substrings in the string based on whitespace
        pointer = buffer;

        while (*pointer != '\n'){
            sscanf(pointer, " %s%n ", string, &size);
            num_tokens++;
            pointer = pointer + size;
        }
        return num_tokens;
    }


    void storeTokens(char** tokens, char* buffer){
        //use sscanf again to store words
        int counter = 0;
        int size;
        char currentString[256];
        char* pointer = buffer;

        while (*pointer != '\n'){
            sscanf(pointer, " %s%n ", currentString, &size);
            tokens[counter] = (char*) malloc(sizeof(char) * (strlen(currentString) + 1));
        strcpy(tokens[counter], currentString);
        pointer = pointer + size;
        counter++;
        }
    }

    void printTypeOfToken(char** tokens, int* num_tokens){
        int counter;
        char* pointer;
        
        //cycle through collected tokens
        for (int i = 0; i < *num_tokens; i++){
            counter = 0;
            pointer = tokens[i];
        
        //count number of digits in string
        while(isdigit(*pointer)){
            pointer++;
            counter++;
        }

        //check if string is made entirely of character
        //or if the number of digits counted is not the whole string
        if(counter == 0 || counter != (int)strlen(tokens[i])){
            printf("STR ");
        }
        else{
            printf("INT ");
        }
    }
    printf("\n");
    }
