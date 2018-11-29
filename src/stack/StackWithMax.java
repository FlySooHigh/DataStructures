package stack;

import java.util.*;

public class StackWithMax{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> values = new HashMap<>();

        int numberOfCommands = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfCommands; i++) {
            String command = scanner.nextLine();
            switch (command) {
                case "pop":
                    processPopCommand(stack, values);
                    break;
                case "max":
                    processMaxCommand(values);
                    break;
                default:
                    processPushCommand(stack, values, command);
                    break;
            }
        }
    }

    private static void processPopCommand(Stack<Integer> stack, Map<Integer, Integer> values) {
        Integer poppedValue = stack.pop();
        Integer occurances = values.get(poppedValue);
        if (occurances == 1) {
            values.remove(poppedValue);
        } else {
            values.put(poppedValue, occurances - 1);
        }
    }

    private static void processMaxCommand(Map<Integer, Integer> values) {
        System.out.println(Collections.max(values.keySet()));
    }

    private static void processPushCommand(Stack<Integer> stack, Map<Integer, Integer> values, String command) {
        String parsedNumber = command.trim().replaceAll("\\D+", "");
        int value = Integer.parseInt(parsedNumber);
        stack.push(value);
        if (!values.containsKey(value)) {
            values.put(value, 1);
        } else {
            values.put(value, values.get(value) + 1);
        }
    }
}

