#include "Stack.hpp"
#include <iostream>

template <class T>
void Stack<T>::Push(T value){
    stack->push_back(value);
};

template <class T>
Stack<T>::Stack(){
    stack = new vector<T>;
};

template <class T>
Stack<T>::~Stack(){};

template <class T>
void Stack<T>::Pop() {
    if (stack->size() == 0){
        cout << "Stack Empty!\n";
        return;
    }
    stack->pop_back();
};

template <class T>
void Stack<T>::Print(){
    cout << "[ ";
    for(int i = 0; i < stack->size(); i++){
        cout << stack->at(i) << " ";
    }
    cout << "]\n";
};

template class Stack<int>;
