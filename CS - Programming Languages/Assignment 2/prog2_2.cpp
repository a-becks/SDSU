#include "Tokenizer.hpp"
#include <iostream>
#include <vector>
#include <string>
#include <cstdlib>


void printHeader();
void checkCmdLineArgs(int argc, char* argv[]);
bool isInteger(string arg);
void checkQuit(vector<string> *tokens);

int main(int argc, char* argv[]) {
    Tokenizer *tokenizer = new Tokenizer();
    vector<string> *tokens;
    int iterations = 0;

    printHeader();
    checkCmdLineArgs(argc, argv);

    while(iterations < atoi(argv[1])) {
        iterations++;

        tokens = tokenizer->GetTokens();

        checkQuit(tokens);

        for (int i = 0; i < tokens->size(); i++) {
            if (isInteger(tokens->at(i))) {
                cout << "INT ";
            } else {
                cout << "STR ";
            }
        }
        cout << endl;
    }
}

void checkCmdLineArgs(int argc, char* argv[]){
    //check for appropriate command line arguments
    if (argc != 2) {
        cout << "ERROR! Program accepts 1 command line argument." << endl;
        exit(1);
    }

    if (!isInteger(argv[1])){
        cout << "ERROR! Expected integer argument." << endl;
        exit(1);
    }
}

bool isInteger(string someString) {
    for (int i = 0; i < someString.length(); i++) {
        if (isdigit(someString[i])) {
            continue;
        }
        else {
            return false;
        }
    }
    return true;
}

void printHeader(){
    cout << "Assignment #2-2, Allison Schroder, aschroder@sdsu.edu" << endl;
}

void checkQuit(vector<string> *tokens) {
    char *whatever = (char *) tokens->at(0).c_str();
    char lowerInput[sizeof(whatever)];
    string input;
    for (int i = 0; i < sizeof(whatever); i++) {
        lowerInput[i] = (char)tolower(whatever[i]);
    }
    input = (string)lowerInput;

    if (tokens->size() == 1 && (input.compare("quit")==0)){
        exit(0);
    }
}
