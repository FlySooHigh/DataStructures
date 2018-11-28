import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class CheckBrackets {

    private static final int numberOfTests = 54;

    public static void main(String[] args) throws IOException {

        String pathToTests = "tests\\checkBrackets\\";

        for (int i = 1; i <= numberOfTests; i++) {

            String fileName = String.format("%02d", i);

            Scanner scanner = new Scanner(new File(pathToTests + fileName));
            String inputToTest = scanner.nextLine();

            String result = bracketValidator(inputToTest);

            scanner = new Scanner(new File(pathToTests + fileName + ".a"));

            String expectedResult = scanner.nextLine();
            if (expectedResult.equals(result)) {
                System.out.println("Test #" + fileName + " passed");
            } else {
                System.out.println("Expected result: " + expectedResult);
                System.out.println("Result: " + result);
            }
        }
    }

    private static String bracketValidator(String text) {
        Stack<Bracket> openingBracketsStack = new Stack<>();

        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                openingBracketsStack.push(new Bracket(next, position));
            }

            if (next == ')' || next == ']' || next == '}') {
                if (!openingBracketsStack.isEmpty()) {
                    Bracket bracket = openingBracketsStack.pop();
                    if (!bracket.match(next)) {
                        return String.valueOf(position + 1);
                    }
                } else {
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
