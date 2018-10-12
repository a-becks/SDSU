#include <iostream>
#include <vector>
#include <cstdlib>
#include <string>
#include <cctype>

using namespace std;

void checkCmdLineArgs(int argc, char* argv[]);
bool isInteger(string arg);
void getTokens(vector<string> *strVector, string inputString);
void checkQuit(vector<string> *tokens);
void printHeader();

int main(int argc, char *argv[]) {
    
    printHeader();

    checkCmdLineArgs(argc, argv);

    string inputString;
    vector<string> *tokens;
    int iterations = 0;

    //continue loop until user input number of iterations is reached
    while(iterations < atoi(argv[1])) {
        iterations++;
        tokens = new vector<string>;
        cout << ">";
        getline(cin, inputString);

        if (inputString.size() > 65) {
            cout << "ERROR! Input string too long." << endl;
        }

        getTokens(tokens, inputString);


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

/////////////////////////////////////////////////////////////////////////////////////////////////////////

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

void getTokens(vector<string> *tokens, string inputString){
    size_t position = 0;
    string someSubString = "";

    //for input strings without spaces to store last token
    inputString.insert(inputString.size(), " ");


    while (inputString.length() > 0) {

        position = inputString.find(' ');

        //to fix multiple spaces in a row, where the space is found at position 0
        if (position == 0){
            inputString.erase(0, 1);
            continue;
        }

        //find() returns -1 when no more spaces found
        if (position == -1){
            break;
        }

        someSubString = inputString.substr(0, position);
        tokens->push_back(someSubString);
        inputString.erase(0, position + 1);
    }
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

void printHeader(){
    cout << "Assignment #2-1, Allison Schroder, aschroder@sdsu.edu" << endl;
}

