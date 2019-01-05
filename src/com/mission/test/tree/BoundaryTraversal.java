package com.mission.test.tree;

public class BoundaryTraversal {

	private Node root;

	private int maxLevel;

	public void leftBoundary() {
		maxLevel = 0;
		if (root != null) {
			System.out.print("Left Boundary is : ");
			leftBoundary(root, 1);
		}
	}

	private void leftBoundary(Node root, int curLevel) {
		if (root != null) {
			if (curLevel > maxLevel) {
				System.out.print(root.data + " ");
				maxLevel++;
			}
			leftBoundary(root.left, curLevel + 1);
			leftBoundary(root.right, curLevel + 1);
		}
	}

	public void rightBoundary() {
		maxLevel = 0;
		if (root != null) {
			System.out.print("\nRight Boundary is : ");
			rightBoundary(root, 1);
		}
	}

	private void rightBoundary(Node root, int curLevel) {
		if (root != null) {
			if (curLevel > maxLevel) {
				System.out.print(root.data + " ");
				maxLevel++;
			}
			// use reverse inorder here
			rightBoundary(root.right, curLevel + 1);
			rightBoundary(root.left, curLevel + 1);
		}
	}

	public void boundaryTraversal() {
		if (root != null) {
			System.out.print("\nBoundary Traversal is : ");
			System.out.print(root.data + " ");
			printLeftBoundary(root.left);
			printLeaves(root.left);
			printLeaves(root.right);
			printRightBoundary(root.right);
		}
	}

	private void printLeftBoundary(Node node) {
		if (node != null) {
			if (node.left != null) {
				System.out.print(node.data + " ");
				printLeftBoundary(node.left);
			} else if (node.right != null) {
				System.out.print(node.data + " ");
				printLeftBoundary(node.right);
			}
		}
	}

	private void printRightBoundary(Node node) {
		if (node != null) {
			// visit tree in reverse order
			if (node.right != null) {
				printRightBoundary(node.right);
				System.out.print(node.data + " ");
			} else if (node.left != null) {
				printRightBoundary(node.left);
				System.out.print(node.data + " ");
			}
		}
	}

	private void printLeaves(Node node) {
		if (node != null) {
			printLeaves(node.left);
			if (node.left == null && node.right == null)
				System.out.print(node.data + " ");
			printLeaves(node.right);
		}
	}

	public static void main(String[] args) {
		BoundaryTraversal bt = new BoundaryTraversal();
		bt.createBinaryTree();
		bt.leftBoundary();
		bt.rightBoundary();
		bt.boundaryTraversal();
	}

	private class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	public void createBinaryTree() {
		root = new Node(4);
		root.left = new Node(2);
		root.right = new Node(0);
		root.left.left = new Node(1);
		root.left.right = new Node(3);
		root.right.left = new Node(9);
	}
}