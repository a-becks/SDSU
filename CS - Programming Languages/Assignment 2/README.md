Allison Schroder
aschroder@sdsu.edu

This assignment has 3 programs and 2 classes to re-implement assignment 1 in C++ and create a rudimentary stack machine.

Prog2_1 - Reimplements program 1 in C++.

Tokenizer.cpp and Tokenizer.hpp - Source and header files for Tokenizer class.  Contains one public method -GetTokens- which takes a string from STDIN and returns a vector of strings to be used as tokens.

Prog2_2 - Reimplements prog2_1 using Tokenizer class to process tokens.

Stack.cpp and Stack.hpp - Source and header files for Stack class.  Creates a vector that stores any type of data with public methods to add to the end of the stack (Push), remove the last item from the stack(Pop), and print the contents of the stack (Print).

Prog2_3 - Processes tokens as previous programs, but if string token is a command for the stack (i.e. pop or push), will execute the respective commands via the Stack class.  Prints the contents of the stack before exiting.