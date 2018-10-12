//
// Created by allison on 6/9/17.
//
#include "Tokenizer.hpp"
#include <iostream>

Tokenizer::Tokenizer(){
}

Tokenizer::~Tokenizer(){
}

vector<string>* Tokenizer::GetTokens() {
    promptUser();
    return tokens;
}

void Tokenizer::promptUser() {
    correctInput = false;

    while (!correctInput) {
        tokens = new vector<string>;
        cout << ">";
        getline(cin, inputString);

        if (inputString.size() > 65) {
            cout << "ERROR! Input string too long." << endl;
        }

        processTokens(tokens, inputString);

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

        correctInput = true;
    }
}

void Tokenizer::processTokens(vector <string> *strVector, string inputString) {
    position = 0;
    someSubString = "";

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

bool Tokenizer::isInteger(string someString) {
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

