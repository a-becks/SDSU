opStack = {}
tokens = {}
output = ""

function split(input)
  for i in string.gmatch(input, "%S+")
  do
    table.insert(tokens, i)
  end
end

function isOperator(someString)
    if(someString == '+' or someString == '-')
    then
        return true, 1
    elseif(someString == '*' or someString == '/')
    then
        return true, 2
    else
        return false
    end
end

function processTokens(someTable)
    for k, v in pairs(someTable)    
        do
        if (isOperator(v) == false)
        then
            output = output .. ' ' .. v
        else
        a, rank = isOperator(v)
            if (next(opStack) == nil)
            then
                table.insert(opStack, {v, rank})
            else
            while (next(opStack) ~= nil and (opStack[#opStack][2] > rank or opStack[#opStack][2] == rank))
            do
                output = output .. " " .. opStack[#opStack][1]
                table.remove(opStack)
            end
        table.insert(opStack, {v, rank})
        end
    end
 end
    while (next(opStack) ~= nil)
    do
        output = output .. " " .. opStack[#opStack][1]
        table.remove(opStack)
    end
end

function InfixToPostfix(someString)
    --print("Assignment #3-3, Allison Schroder, aschroder@sdsu.edu")
    split(someString)
    processTokens(tokens)
    return output
end
