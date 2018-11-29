package tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {

    private static final int numberOfTests = 24;

    public static void main(String[] args) throws FileNotFoundException {
        String pathToTests = "tests\\tree\\";

        for (int j = 1; j <= numberOfTests; j++) {
            String fileName = String.format("%02d", j);
            Scanner scanner = new Scanner(new File(pathToTests + fileName));
            int nodesNum = Integer.parseInt(scanner.nextLine());

            Map<Integer, List<Integer>> tree = new HashMap<>();

            for (int i = 0; i < nodesNum; i++) {
                int parent = scanner.nextInt();
                if (tree.get(parent) == null) {
                    List<Integer> children = new ArrayList<>();
                    children.add(i);
                    tree.put(parent, children);
                } else {
                    tree.get(parent).add(i);
                }
            }
            List<Integer> root = tree.get(-1);
            Queue<Integer> nodesToCheck = new LinkedList<>();
            nodesToCheck.add(root.get(0));
            int height = 1;
            int maxHeight = 1;
            while (!nodesToCheck.isEmpty()) {
                Integer fromQueue = nodesToCheck.remove();
                List<Integer> children = tree.get(fromQueue);
                if (children != null) {
                    height++;
                    nodesToCheck.addAll(children);
                } else {
                    if (maxHeight < height) {
                        maxHeight = height;
                    }
                }
            }
//            System.out.println(maxHeight);

            scanner = new Scanner(new File(pathToTests + fileName + ".a"));

            String expectedResult = scanner.nextLine();
            if (expectedResult.equals(String.valueOf(maxHeight))) {
                System.out.println("Test #" + fileName + " passed");
            } else {
                System.out.println("Test #" + fileName + " failed");
                System.out.println("Expected result: " + expectedResult);
                System.out.println("Result: " + maxHeight);
                break;
            }
        }



    }
}

class Node {
    private int value;
    private int depth;

    public Node(int value, int depth) {
        this.value = value;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
