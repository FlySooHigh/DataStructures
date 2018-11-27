package check_brackets_in_code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {

    private char type;
    private int position;

    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean match(char c) {
        return this.type == '[' && c == ']' || this.type == '{' && c == '}' || this.type == '(' && c == ')';
    }

}

class CheckBrackets {

    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> openingBracketsStack = new Stack<>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
            }
        }

        // Printing answer, write your code here
    }
}
