package com.mission.test.tree;

import java.util.*;

public class BinaryTree {

	static Node root;

	static Node prev;

	public static void main(String[] args) {
		BinaryTree h = new BinaryTree();
		root = h.new Node(4);
		root.left = h.new Node(2);
		root.right = h.new Node(8);
		root.left.left = h.new Node(1);
		root.left.right = h.new Node(3);
		root.left.left.left = h.new Node(9);
		h.verticalSum(root);
		h.reverseInorder(root);
		System.out.println();
		h.iterativeInorder(root);
		System.out.println();
		h.iterativePreorder(root);
		System.out.println();
		h.iterativePostorder(root);
		h.mirrorPost(root);
		System.out.println();
		h.inorder(root);
		System.out.println();
		h.mirrorPre(root);
		h.inorder(root);
		System.out.println();
		h.level(root);
		System.out.println();
		System.out.println("Level of 1 is : " + h.getLevel(root, 1, 0));
		System.out.println(h.isSymmetric(root, root));
		System.out.println(h.isBST(root) ? "Is BST" : "Is not BST");
		System.out.println("LCA of 1, 8 -> " + (h.lca(root, 1, 8)).data);
		System.out.println("LCA of 2, 3 -> " + (h.lca(root, 2, 3)).data);
		System.out.println("LCA of 1, 8 -> " + (h.lca_bst(root, 1, 8)).data);
		System.out.println("Diameter of the tree -> " + (h.diameter(root)));
		System.out.println("Closest to 7 is -> " + h.closestBST(root, 7).data);
		h.serialize(root);
	}

	class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	public void mirrorPost(Node root) {
		if (root != null) {
			mirrorPost(root.left);
			mirrorPost(root.right);
			Node temp = root.left;
			root.left = root.right;
			root.right = temp;
		}
	}

	public void mirrorPre(Node root) {
		if (root != null) {
			Node temp = root.left;
			root.left = root.right;
			root.right = temp;
			mirrorPre(root.left);
			mirrorPre(root.right);
		}
	}

	public void inorder(Node root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(root.data + " ");
			inorder(root.right);
		}
	}

	public void reverseInorder(Node root) {
		if (root != null) {
			reverseInorder(root.right);
			System.out.print(root.data + " ");
			reverseInorder(root.left);
		}
	}

	public void iterativeInorder(Node root) {
		if (root != null) {
			Stack<Node> stack = new Stack<>();
			while (true) {
				while (root != null) {
					stack.push(root);
					root = root.left;
				}

				if (stack.isEmpty())
					break;

				root = stack.pop();
				System.out.print(root.data + " ");
				root = root.right;
			}
		}
	}

	public void iterativePreorder(Node root) {
		if (root != null) {
			Stack<Node> stack = new Stack<>();
			while (true) {
				while (root != null) {
					System.out.print(root.data + " ");
					stack.push(root);
					root = root.left;
				}

				if (stack.isEmpty())
					break;

				root = stack.pop();
				root = root.right;
			}
		}
	}

	public void iterativePostorder(Node root) {
		if (root != null) {
			Stack<Node> stack = new Stack<>();
			while (true) {
				while (root != null) {
					stack.push(root);
					root = root.left;
				}

				if (stack.isEmpty())
					break;

				// The following condition means element at the top of the
				// stack, is either left or right child
				if (stack.peek().right == null) {
					root = stack.pop();
					System.out.print(root.data + " ");

					// This means currently popped element was right of the
					// root.
					while (!stack.isEmpty() && root == stack.peek().right) {
						root = stack.pop();
						System.out.print(root.data + " ");
					}
				}

				if (!stack.isEmpty())
					root = stack.peek().right;
				else
					root = null;
			}
		}
	}

	public void level(Node root) {
		if (root == null)
			return;

		Queue<Node> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			Node temp = q.remove();
			System.out.print(temp.data + " ");
			if (temp.left != null)
				q.add(temp.left);
			if (temp.right != null)
				q.add(temp.right);
		}
	}

	public int getLevel(Node root, int key, int level) {
		if (root == null)
			return -1;

		if (root.data == key)
			return level;

		int lev = getLevel(root.left, key, level + 1);
		if (lev != -1)
			return lev;

		return getLevel(root.right, key, level + 1);
	}

	public boolean isSymmetric(Node root1, Node root2) {
		if (root1 == null && root2 == null)
			return true;

		else if ((root1 == null && root2 != null) || (root1 != null && root2 == null))
			return false;

		else {
			return (root1.data == root2.data && isSymmetric(root1.left, root2.right)
					&& isSymmetric(root1.right, root2.left));
		}
	}

	public boolean equals(Node root1, Node root2) {
		if (root1 == null && root2 == null)
			return true;

		else if ((root1 == null && root2 != null) || (root1 != null && root2 == null))
			return false;

		else {
			return (root1.data == root2.data && equals(root1.left, root2.left) && equals(root1.right, root2.right));
		}
	}

	public boolean isBST(Node root) {
		if (root == null)
			return true;
		else {
			if (!isBST(root.left))
				return false;

			if (prev != null && root.data <= prev.data)
				return false;
			prev = root;

			return isBST(root.right);
		}
	}

	public boolean isBST2(Node root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST(Node root, int min, int max) {
		if (root == null)
			return true;

		return ((root.data > min && root.data <= max) && isBST(root.left, min, root.data)
				&& isBST(root.right, root.data, max));
	}

	// Assumption : Both the values are present in the tree
	public Node lca(Node root, int val1, int val2) {
		if (root == null)
			return null;

		if (root.data == val1 || root.data == val2)
			return root;

		Node temp1 = lca(root.left, val1, val2);
		Node temp2 = lca(root.right, val1, val2);

		if (temp1 != null && temp2 != null)
			return root;

		return temp1 != null ? temp1 : temp2;
	}

	// Assumption : Both the values are present in the tree
	public Node lca_bst(Node root, int val1, int val2) {
		if (root == null)
			return null;

		if (root.data > val1 && root.data > val2)
			return lca_bst(root.left, val1, val2);

		if (root.data < val1 && root.data < val2)
			return lca_bst(root.right, val1, val2);

		return root;
	}

	// This wrapper is required since in java two values can not be returned
	// back and also it does not have
	// pass by reference.
	private class HeightWrapper {
		int height;

		public HeightWrapper() {
			height = 0;
		}
	}

	// Diameter is the distance between the two farthest nodes in the tree.
	// So, Diameter = Max (Diameter of left subtree, Diameter of right subtree,
	// left height + right height + 1)
	// This can be calculated in n square time. If height and diameter is
	// calculated in the same iteration then complexity can be reduced to O(n).
	public int diameter(Node root) {
		HeightWrapper h = new HeightWrapper();
		return diameter(root, h);
	}

	private int diameter(Node root, HeightWrapper h) {
		if (root == null) {
			h.height = 0;
			return 0;
		}

		// The lh and rh are not returned back. They are used to calculate root
		// diameter and height of the current node.
		HeightWrapper lh = new HeightWrapper();
		HeightWrapper rh = new HeightWrapper();

		int ld = diameter(root.left, lh);
		int rd = diameter(root.right, rh);
		int rootd = lh.height + rh.height + 1;
		h.height = Math.max(lh.height, rh.height) + 1;

		return Math.max(rootd, Math.max(ld, rd));
	}

	/** BT to DLL */
	public Node bintree2list(Node node) {
		// Base case
		if (node == null) {
			return node;
		}

		// Convert to DLL using bintree2listUtil()
		node = bintree2listUtil(node);

		// bintree2listUtil() returns root node of the converted
		// DLL. We need pointer to the leftmost node which is
		// head of the constructed DLL, so move to the leftmost node
		while (node.left != null) {
			node = node.left;
		}

		return node;
	}

	private Node bintree2listUtil(Node node) {
		// Base case
		if (node == null) {
			return node;
		}

		// Convert the left subtree and link to root
		if (node.left != null) {
			// Convert the left subtree
			Node left = bintree2listUtil(node.left);

			// Find inorder predecessor. After this loop, left
			// will point to the inorder predecessor
			while (left.right != null)
				left = left.right;

			// Make root as next of the predecessor
			left.right = node;

			// Make predecessor as previous of root
			node.left = left;
		}

		// Convert the right subtree and link to root
		if (node.right != null) {
			// Convert the right subtree
			Node right = bintree2listUtil(node.right);

			// Find inorder successor. After this loop, right
			// will point to the inorder successor
			while (right.left != null)
				right = right.left;

			// Make root as previous of successor
			right.left = node;

			// Make successor as next of root
			node.right = right;
		}

		return node;
	}

	// Returns the closest element to the given value
	public Node closestBST(Node root, int val) {
		if (root.data == val)
			return root;

		if (val < root.data) {
			if (root.left == null)
				return root;
			Node p = closestBST(root.left, val);
			return Math.abs(p.data - val) > Math.abs(root.data - val) ? root : p;
		}

		else {
			if (root.right == null)
				return root;
			Node p = closestBST(root.right, val);
			return Math.abs(p.data - val) > Math.abs(root.data - val) ? root : p;
		}
	}

	public void verticalSum(Node root) {
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		verticalSum(root, 0, map);
		for (int temp : map.keySet())
			System.out.println("Sum for vertical line " + temp + " is : " + map.get(temp));
	}

	private void verticalSum(Node root, int level, Map<Integer, Integer> map) {
		if (root != null) {
			int data = root.data;
			if (map.get(level) != null)
				data += map.get(level);
			map.put(level, data);
			verticalSum(root.left, level - 1, map);
			verticalSum(root.right, level + 1, map);
		}
	}

	public void findPath(Node root, int sum) {
		if (root != null) {
			List<Integer> list = new ArrayList<>();
			findPath(root, sum, list);
		}
	}

	private void findPath(Node root, int sum, List<Integer> list) {
		list.add(root.data);
		int temp = sum - root.data;

		if (root.left == null && root.right == null) {
			if (temp == 0)
				printPath(list);
		} else {
			if (root.left != null)
				findPath(root.left, temp, list);
			if (root.right != null)
				findPath(root.right, temp, list);
		}

		list.remove((Integer) root.data);
	}

	private void printPath(List<Integer> list) {
		System.out.print("Path : ");
		list.forEach(a -> System.out.print(a + " "));
		System.out.println();
	}

	public void serialize(Node root) {
		List<Integer> list = new ArrayList<>();
		serialize(root, list);
		System.out.print("Serialized tree : ");
		list.forEach(l -> System.out.print(l + " "));
		Node root2 = deserialize(list);
		System.out.println(this.equals(root, root2) ? "\nTree deserialized successfully" : "\nFailed to deserialize");
	}

	// process elements in preorder while treating null nodes as -1
	private void serialize(Node root, List<Integer> list) {
		if (root == null)
			list.add(-1);
		else {
			list.add(root.data);
			serialize(root.left, list);
			serialize(root.right, list);
		}
	}

	private class NumberWrapper {
		int num = -1;
	}

	public Node deserialize(List<Integer> list) {
		return deserialize(list, new NumberWrapper());
	}

	private Node deserialize(List<Integer> list, NumberWrapper index) {
		index.num++;
		if (list.get(index.num) != -1) {
			Node node = new Node(list.get(index.num));
			node.left = deserialize(list, index);
			node.right = deserialize(list, index);
			return node;
		}
		return null;
	}
}
