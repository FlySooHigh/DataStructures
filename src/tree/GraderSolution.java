package tree;

import java.util.*;

public class GraderSolution {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
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
        System.out.println(maxHeight);
    }
}


class Node {
    private int payload;
    private int depth;

    Node(int payload, int depth) {
        this.payload = payload;
        this.depth = depth;
    }

    public int getPayload() {
        return payload;
    }

    public void setPayload(int payload) {
        this.payload = payload;
    }

    int getDepth() {
        return depth;
    }

    void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return payload == node.payload;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payload);
    }
}

