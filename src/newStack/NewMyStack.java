package newStack;

import java.io.*;
import java.util.*;

public class NewMyStack {
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("tests\\stack\\01")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream bos = new BufferedOutputStream(System.out);
//        long startTime = System.currentTimeMillis();
        MyStack myStack = new MyStack(null);
        int numberOfCommands = Integer.parseInt(br.readLine());
        for (int i = 0; i < numberOfCommands; i++) {
            String command = br.readLine();
            switch (command) {
                case "pop":
                    myStack.pop();
                    break;
                case "max":
                    bos.write((myStack.getMax() + "\n").getBytes());
                    break;
                default:
                    int value = Integer.parseInt(command.substring(5));
                    myStack.push(value);
                    break;
            }
        }
        bos.flush();
//        long stopTime = System.currentTimeMillis();
//        System.out.println("Seconds: " + (stopTime - startTime)/1000.0);
    }
}

class MyStack{
    private Node head;

    public MyStack(Node head) {
        this.head = head;
    }

    public void push(int value) {
        if (head == null) {
            head = new Node(null, value, value);
        } else {
            if (value > head.getMax()) {
                head = new Node(head, value, value);
            } else {
                head = new Node(head, value, head.getMax());
            }
        }
    }

    public void pop() {
        head = head.getNext();
    }

    public int getMax() {
        return head.getMax();
    }
}

class Node{
    private Node next;
    private int value;
    private int max;

    public Node(Node next, int value, int max) {
        this.next = next;
        this.value = value;
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
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

