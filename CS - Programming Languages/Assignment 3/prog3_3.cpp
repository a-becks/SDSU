#include <lua.hpp>
#include <lauxlib.h>
#include <lualib.h>
#include <string>
#include <iostream>

using namespace std;

int main(int argc, char* argv[]){

    string input;
    string output = "something went wrong";
    

    lua_State *lua = luaL_newstate();
    luaL_openlibs(lua);

    luaL_loadfile(lua, "prog3_2.lua");

    lua_pcall(lua, 0, 0, 0);

    cout << "Assignment #3-3, Allison Schroder, aschroder@sdsu.edu\n";
    cout << "> ";

    getline(cin, input);

    lua_getglobal(lua, "InfixToPostfix");

    lua_pushstring(lua, input.c_str());

    lua_pcall(lua, 1, 1, 0);

    output = luaL_checkstring(lua, -1);

    cout << output;

    lua_close(lua);
}
