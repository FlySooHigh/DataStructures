import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class CheckBrackets {

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStream);
        String text = reader.readLine();

        System.out.println(bracketValidator(text));
    }

    private static String bracketValidator(String text) {
        Stack<Bracket> openingBracketsStack = new Stack<>();

        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                openingBracketsStack.push(new Bracket(next, position));
            }

            if (next == ')' || next == ']' || next == '}') {
                Bracket bracket = openingBracketsStack.pop();
                if (!bracket.match(next)) {
                    return String.valueOf(position + 1);
                }
            }
        }

        if (openingBracketsStack.isEmpty()) {
            return String.valueOf("Success");
        } else {
            return String.valueOf(openingBracketsStack.pop().getPosition() + 1);
        }
    }
}

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

    int getPosition() {
        return position;
    }
}
