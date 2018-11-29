package stack;

import java.util.*;
import java.io.*;

public class StackWithMax {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() throws FileNotFoundException {
            in = new BufferedReader(new InputStreamReader(System.in));
//            in = new BufferedReader(new InputStreamReader(new FileInputStream("tests\\stack\\01")));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public void solve() throws IOException {

        TreeMap<Integer, Integer> values = new TreeMap<>();

        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<Integer> stack = new Stack<Integer>();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                stack.push(value);
                if (!values.containsKey(value)) {
                    values.put(value, 1);
                } else {
                    values.put(value, values.get(value) + 1);
                }
            } else if ("pop".equals(operation)) {
                Integer poppedValue = stack.pop();
                Integer occurances = values.get(poppedValue);
                if (occurances == 1) {
                    values.remove(poppedValue);
                } else {
                    values.put(poppedValue, occurances - 1);
                }
            } else if ("max".equals(operation)) {
                System.out.println(values.lastKey());
            }
        }
    }

    static public void main(String[] args) throws IOException {
//        long startTime = System.currentTimeMillis();
        new StackWithMax().solve();
//        long stopTime = System.currentTimeMillis();
//        System.out.println("Seconds: " + (stopTime - startTime)/1000.0);
    }
}
