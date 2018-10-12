#include <string>
#include <vector>
#include <iostream>

using namespace std;

template <class T>
class Stack{

    private:
        vector<T> *stack;

    public:
        Stack<T>();
        ~Stack<T>();

        void Push(T value);

        void Pop();

        void Print();
};
