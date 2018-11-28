package tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("tests\\tree\\01"));
        int nodesNum = Integer.parseInt(scanner.nextLine());
        List<TreeNode> treeNodes = new ArrayList<>();

        for (int i = 0; i < nodesNum; i++) {
            int parent = scanner.nextInt();
            treeNodes.add(new TreeNode(parent));
        }

        Queue<TreeNode> nodesToVisit = new LinkedList<>(treeNodes);
        int height = 0, maxHeight = 0;
        while (!nodesToVisit.isEmpty()) {
            TreeNode removed = nodesToVisit.remove();
            height++;
            int parent = removed.getParent();
            while (parent != -1) {
                TreeNode treeNode = treeNodes.get(parent);
                height++;
                nodesToVisit.remove(treeNode);
                parent = treeNode.getParent();
            }
            if (maxHeight < height) {
                maxHeight = height;
            }
            height = 0;
        }
    }
}

class TreeNode {
    private int parent;

    public TreeNode(int parent) {
        this.parent = parent;
    }

    public int getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return parent == treeNode.parent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent);
    }
}
