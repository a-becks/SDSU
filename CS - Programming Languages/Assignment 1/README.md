Allison Schroder
aschroder@sdsu.edu

This project contains 6 programs designed to process user input:

Program 1_1 - This program takes input from the user (65 character limit) and separtes them into tokens based on a space delimiter.  Individual tokens are output in a =token= format.

Program 1_2 - This program prompts for user input (65 character limit) and separates them into tokens as in program 1_1.  Each token is identified as a string or integer, which is echoed onto the screen.

Program 1_3 - This program prompts the user for input (65 character limit) with a limit of two tokens.  If the appropriate input is not provided, the user is given an error and re-prompted until one or two tokens are input.

Progam 1_4 - This program promps the user for input (20 character, 2 token limit).  If the number of tokens or input characters is incorrect, the program outputs an error.  Once appropriate input is given, the program identifies the tokens as strings or integers and outputs the result.  This continues until the user enters the token 'quit'.

Program 1_5 - This program prompts the user for input (65 charcter, 2 token limit) and accepts only two token inputs - "string integer" or "string".  If any of these input requirements are violated, the program outputs an error message.  As above, this continues until the user enters the token 'quit'.

Program 1_6 - This program takes one command line argument in the form of an integer.  If there are more or less than 1 argument, an error message is output.  The integer argument is used to designate the maximum number of iterations for the user prompt.  The user input requires are the same as the previous program.  The program exits when either the user enters the token 'quit', or the total number of iterations has been reached.