package org.exercises.tree;


import java.util.Objects;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        int limInferior = 0;
        int limSuperior = Integer.MAX_VALUE;

        return helper(root, limInferior, limSuperior);
    }

    private static boolean helper(TreeNode root, int inferior, int superior) {
        if (Objects.isNull(root)) {
            return true;
        }

        if (root.val < inferior || root.val > superior) {
            return false;
        }

        return helper(root.left, inferior, root.val) && helper(root.right, root.val, superior);
    }
}
