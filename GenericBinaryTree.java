package secondyear;

import java.util.*;
import java.util.stream.Collectors;

class TreeNode<T> {
    T data;
    TreeNode<T> left, right;

    TreeNode(T item) {
        data = item;
        left = right = null;
    }
}

class BinaryTree<T extends Comparable<T>> {
    TreeNode<T> root;

    // Insert method (binary-search-tree style)
    public void insert(T data) {
        root = insertRec(root, data);
    }

    private TreeNode<T> insertRec(TreeNode<T> node, T data) {
        if (node == null) {
            return new TreeNode<>(data);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = insertRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertRec(node.right, data);
        }
        
        return node;
    }

    public List<T> inOrderList() {
        List<T> res = new ArrayList<>();
        inOrderRec(root, res);
        return res;
    }
    private void inOrderRec(TreeNode<T> node, List<T> out) {
        if (node == null) return;
        inOrderRec(node.left, out);
        out.add(node.data);
        inOrderRec(node.right, out);
    }

    public List<T> preOrderList() {
        List<T> res = new ArrayList<>();
        preOrderRec(root, res);
        return res;
    }
    private void preOrderRec(TreeNode<T> node, List<T> out) {
        if (node == null) return;
        out.add(node.data);
        preOrderRec(node.left, out);
        preOrderRec(node.right, out);
    }

    public List<T> postOrderList() {
        List<T> res = new ArrayList<>();
        postOrderRec(root, res);
        return res;
    }
    private void postOrderRec(TreeNode<T> node, List<T> out) {
        if (node == null) return;
        postOrderRec(node.left, out);
        postOrderRec(node.right, out);
        out.add(node.data);
    }

    // --- Iterative pre-order (stack) for debugging/verification ---
    public List<T> preOrderIterative() {
        List<T> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.pop();
            res.add(node.data);
            if (node.right != null) stack.push(node.right);
            if (node.left  != null) stack.push(node.left);
        }
        return res;
    }

    
    public List<T> levelOrderList() {
        List<T> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode<T>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode<T> node = q.poll();
            res.add(node.data);
            if (node.left  != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        return res;
    }
}

public class GenericBinaryTree {
    private static <T> String joinList(List<T> list) {
        return list.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinaryTree<Integer> tree = new BinaryTree<>();

        System.out.println("Enter 3 integers:");
        for (int i = 0; i < 3; i++) {
            if (!sc.hasNextInt()) {
                System.out.println("Please enter an integer.");
                sc.next(); 
                i--;
                continue;
            }
            int num = sc.nextInt();
            tree.insert(num);
        }

        System.out.println("Level Order: " + joinList(tree.levelOrderList()));
        System.out.println("In Order:    " + joinList(tree.inOrderList()));
        System.out.println("Pre Order: " + joinList(tree.preOrderList()));
        
        System.out.println("Post Order:  " + joinList(tree.postOrderList()));
        System.out.println("Name: DHARSHINI V");
		System.out.println("Reg.no:2117240020077");
        sc.close();
    }
}

