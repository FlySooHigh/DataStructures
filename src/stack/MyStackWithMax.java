package stack;

import java.io.*;
import java.util.*;

public class MyStackWithMax {
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("tests\\stack\\01")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Reader reader = new Reader("tests\\stack\\01");

        long startTime = System.currentTimeMillis();
//        MyStack myStack = new MyStack(null);
//        Stack<Integer> stack = new Stack<>();
        int[] stack1 = new int[100_000];
        int writePos = 0;
        TreeMap<Integer, Integer> values = new TreeMap<>();

//        int numberOfCommands = Integer.parseInt(br.readLine());
        int numberOfCommands = reader.nextInt();
        for (int i = 0; i < numberOfCommands; i++) {
            String command = reader.readLine();
            switch (command) {
                case "pop":
//                    Integer poppedValue = stack.pop();
                    writePos--;
                    int poppedValue = stack1[writePos];
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
//                    stack.push(value);
                    stack1[writePos] = value;
                    writePos++;
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

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
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

