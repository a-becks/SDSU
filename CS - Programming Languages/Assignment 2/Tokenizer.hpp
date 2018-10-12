#include <string>
#include <vector>

using namespace std;

class Tokenizer{

private:
    string inputString;
    vector<string> *tokens;
    bool correctInput;
    size_t position;
    string someSubString;

    bool isInteger(string arg);
    void processTokens(vector<string> *strVector, string inputString);
    void promptUser();

public:
    Tokenizer();
    ~Tokenizer();
    vector<string> *GetTokens();
};
