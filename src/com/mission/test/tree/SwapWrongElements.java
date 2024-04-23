package com.mission.test.tree;

public class SwapWrongElements {

	private Node root;
	private Node prev;
	private Node first;
	private Node middle;
	private Node last;

	public void swapWrongElements() {
		prev = null;
		first = null;
		middle = null;
		last = null;
		swapWrongElements(root);
		if (last == null)
			swap(first, middle);
		else
			swap(first, last);
	}

	private void swap(Node first, Node second) {
		Node temp = new Node(first.data);
		first.data = second.data;
		second.data = temp.data;
	}

	private void swapWrongElements(Node root) {
		if (root != null) {
			swapWrongElements(root.left);
			if (prev != null && prev.data > root.data) {
				if (first == null) {
					first = prev;
					middle = root;
				} else
					last = root;
			}
			prev = root;
			swapWrongElements(root.right);
		}
	}

	/****************************************************/

	public static void main(String[] args) {
		SwapWrongElements h = new SwapWrongElements();
		h.createBinaryTree();
		h.swapWrongElements();
		h.inorder();
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
		root.left.left.left = new Node(9);
	}

	public void inorder() {
		inorder(root);
	}

	private void inorder(Node root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(root.data + " ");
			inorder(root.right);
		}
	}
}