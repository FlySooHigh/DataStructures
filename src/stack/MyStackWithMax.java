package stack;

import java.io.*;
import java.util.*;

public class MyStackWithMax {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("tests\\stack\\01")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long startTime = System.currentTimeMillis();
//        MyStack myStack = new MyStack(null);
        Stack<Integer> stack = new Stack<>();
        TreeMap<Integer, Integer> values = new TreeMap<>();

        int numberOfCommands = Integer.parseInt(br.readLine());
        for (int i = 0; i < numberOfCommands; i++) {
            String command = br.readLine();
            switch (command) {
                case "pop":
                    Integer poppedValue = stack.pop();
                    Integer occurances = values.get(poppedValue);
                    if (occurances == 1) {
                        values.remove(poppedValue);
                    } else {
                        values.put(poppedValue, occurances - 1);
                    }
                    break;
                case "max":
                    System.out.println(values.lastKey());
                    break;
                default:
                    int value = Integer.parseInt(command.substring(5));
                    stack.push(value);
                    if (!values.containsKey(value)) {
                        values.put(value, 1);
                    } else {
                        values.put(value, values.get(value) + 1);
                    }
                    break;
            }
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Seconds: " + (stopTime - startTime)/1000.0);
    }
}

class MyStack{
    private Node head;

    public MyStack(Node head) {
        this.head = head;
    }

    public void push(int value) {
        if (head == null) {
            Node node = new Node(null, value);
            head = node;
        } else {
            Node node = new Node(head, value);
            head = node;
        }
    }

    public int pop() {
        int result = head.getValue();
        head = head.getNext();
        return result;
    }

    public int getMax() {
        Node temp = head;
        int max = -1;
        while (temp != null) {
            int value = temp.getValue();
            if (max < value) {
                max = value;
            }
            temp = temp.getNext();
        }
        return max;
    }
}

class Node{
    private Node next;
    private int value;

    public Node(Node next, int value) {
        this.next = next;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

