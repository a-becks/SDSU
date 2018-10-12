#include "Tokenizer.hpp"
#include "Stack.hpp"
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
    Stack<int> *stack = new Stack<int>();

    vector<string> *tokens;
    int iterations = 0;

    printHeader();
    checkCmdLineArgs(argc, argv);

    while(iterations < atoi(argv[1])) {

        tokens = tokenizer->GetTokens();

        checkQuit(tokens);

        if (tokens->size() < 1 || tokens->size() > 2) {
            cout << "ERROR! Incorrect number of tokens found." << endl;
            continue;
        }

        if (tokens->size() == 1) {
            if (isInteger(tokens->at(0))) {
                cout << "ERROR! Expected STR." << endl;
                continue;
            }
        }

        if (tokens->size() == 2) {
            if (isInteger(tokens->at(0)) || !isInteger(tokens->at(1))) {
                cout << "ERROR! Expected STR INT." << endl;
                continue;
            }
        }


        if (tokens->size() == 1 && tokens->at(0).compare("pop") == 0){
            stack->Pop();
        }

       
        if (tokens->size() == 2 && tokens->at(0).compare("push") == 0){
            stack->Push(atoi(tokens->at(1).c_str()));
        }

        iterations ++;

    }
    stack->Print();
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
    cout << "Assignment #2-3, Allison Schroder, aschroder@sdsu.edu" << endl;
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
