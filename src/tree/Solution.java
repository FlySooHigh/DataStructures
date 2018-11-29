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

            Map<Node, List<Node>> tree = createTree(scanner);
            int maxHeight = getTreeHeight(tree);

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

    private static int getTreeHeight(Map<Node, List<Node>> tree) {
        List<Node> root = tree.get(new Node(-1, 1));
        Queue<Node> nodesToCheck = new LinkedList<>();
        nodesToCheck.add(root.get(0));
        int maxHeight = 1;
        while (!nodesToCheck.isEmpty()) {
            Node fromQueue = nodesToCheck.remove();
            List<Node> children = tree.get(fromQueue);
            if (children != null) {
                for (Node child:children){
                    child.setDepth(fromQueue.getDepth() + 1);
                }
                nodesToCheck.addAll(children);
            } else {
                if (maxHeight < fromQueue.getDepth()) {
                    maxHeight = fromQueue.getDepth();
                }
            }
        }
        return maxHeight;
    }

    private static Map<Node, List<Node>> createTree(Scanner scanner) {
        int nodesNum = Integer.parseInt(scanner.nextLine());
        Map<Node, List<Node>> tree = new HashMap<>();
        for (int i = 0; i < nodesNum; i++) {
            int parent = scanner.nextInt();
            Node parentNode = new Node(parent, 1);
            if (tree.get(parentNode) == null) {
                List<Node> children = new ArrayList<>();
                Node childNode = new Node(i, 1);
                children.add(childNode);
                tree.put(parentNode, children);
            } else {
                Node childNode = new Node(i, 1);
                tree.get(parentNode).add(childNode);
            }
        }
        return tree;
    }
}

