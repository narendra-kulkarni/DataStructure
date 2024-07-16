package com.mission.test.tree;

import java.util.HashMap;
import java.util.Map;

public class TreeConstruction {

    private int preIndex = 0;
    private Map<Character, Integer> inorderMap;

    public TreeNode buildTree(char[] preorder, char[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(char[] preorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preIndex++]);

        if (inStart == inEnd) {
            return root;
        }

        int inIndex = inorderMap.get(root.val);
        root.left = buildTreeHelper(preorder, inStart, inIndex - 1);
        root.right = buildTreeHelper(preorder, inIndex + 1, inEnd);
        return root;
    }

    /************************************************************/

    public static void main(String[] args) {
        char[] inorder = {'D', 'B', 'E', 'A', 'F', 'C'};
        char[] preorder = {'A', 'B', 'D', 'E', 'C', 'F'};

        TreeConstruction solution = new TreeConstruction();
        TreeNode root = solution.buildTree(preorder, inorder);

        System.out.println("Inorder traversal of constructed tree:");
        solution.printInorder(root);
    }

    // Helper method to print inorder traversal
    public void printInorder(TreeNode node) {
        if (node == null) return;

        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }

    static class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;

        TreeNode(char val) {
            this.val = val;
        }
    }
}
