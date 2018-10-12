#include <lua.hpp>
#include <lauxlib.h>
#include <lualib.h>
#include <string>
#include <iostream>

using namespace std;

void printHeader();

int main(int argc, char* argv[]){

    string filename = argv[1];
    
    printHeader();

    lua_State *lua = luaL_newstate();
    luaL_openlibs(lua);

    luaL_dofile(lua, filename.c_str());

    lua_close(lua);
}

void printHeader(){
    cout << "Assignment #3-1, Allison Schroder, aschroder@sdsu.edu" << endl;
}