import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by Allison on 7/20/2017.
 */
public class ExpressionEvaluator {

    Stack<String> stack = new Stack<String>();
    Queue<String> q = new Queue<String>();

    public String processInput(String someInput) {
        String output, token;
        StringTokenizer tokenizer = new StringTokenizer(someInput);

        stack.makeEmpty();
        q.makeEmpty();

        try {
            while (tokenizer.hasMoreTokens()) {
                token = tokenizer.nextToken();
                if (token.equals("("))
                    stack.push(token);
                else if (token.equals(")")) {
                    while (!stack.peek().equals("(")){
                        q.enqueue(stack.pop());
                    }

                    //remove "(" from stack
                    stack.pop();
                } else if (isOperator(token)) {
                    while (stack.size() != 0 && !stack.peek().equals("(") && getOperatorRank(stack.peek()) >= getOperatorRank(token)) {
                        q.enqueue(stack.pop());
                    }
                    stack.push(token);
                } else {
                    q.enqueue(token);
                }
            }
            while (stack.size() != 0) {
                q.enqueue(stack.pop());
            }

            evaluatePostfix();

            output = stack.pop();
            if(!stack.isEmpty()) throw new RuntimeException();
        } catch (Exception e) {
            output = "ERROR";
        }
        return output;
    }

    private void evaluatePostfix(){
        String token, calculatedToken;

        while (!q.isEmpty()) {
            token = q.dequeue();
            if (isOperator(token)) {
                calculatedToken = performOperation(stack.pop(), token, stack.pop()).toString();
                stack.push(calculatedToken);
            } else
                stack.push(token);
        }
    }

    private boolean isOperator(String token) {
        return (token.equals("^") || token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-"));
    }

    private int getOperatorRank(String token) {
        if (token.equals("^"))
            return 3;
        if (token.equals("*") || token.equals("/"))
            return 2;
        return 1;
    }

    private Double performOperation(String tokenFromStack1, String operator, String tokenFromStack2) {
        Double stackTokenDouble1, stackTokenDouble2;
        Double result = 0.0;
        stackTokenDouble1 = Double.parseDouble(tokenFromStack1);
        stackTokenDouble2 = Double.parseDouble(tokenFromStack2);

        if (operator.equals("^"))
            result = Math.pow(stackTokenDouble2, stackTokenDouble1);
        else if (operator.equals("*"))
            result = stackTokenDouble2 * stackTokenDouble1;
        else if (operator.equals("/"))
            result = stackTokenDouble2 / stackTokenDouble1;
        else if (operator.equals("+"))
            result = stackTokenDouble2 + stackTokenDouble1;
        else if (operator.equals("-"))
            result = stackTokenDouble2 - stackTokenDouble1;

        return result;
    }
}
