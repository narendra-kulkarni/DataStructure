package com.mission.test.tree;

import java.util.*;

public class BinaryTree {

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

	public boolean isSymmetric(Node root1, Node root2) {
		if (root1 == null && root2 == null)
			return true;

		else if (root1 == null || root2 == null)
			return false;

		else {
			return root1.data == root2.data
					&& isSymmetric(root1.left, root2.right)
					&& isSymmetric(root1.right, root2.left);
		}
	}

	public boolean equals(Node root1, Node root2) {
		if (root1 == null && root2 == null)
			return true;

		else if (root1 == null || root2 == null)
			return false;

		else {
			return root1.data == root2.data
					&& equals(root1.left, root2.left)
					&& equals(root1.right, root2.right);
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

		return (root.data > min && root.data <= max)
				&& isBST(root.left, min, root.data)
				&& isBST(root.right, root.data, max);
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

	// Node deletion in BST
	public Node deleteNode(Node root, int key) {
		if (root == null) {
			return null;
		}

		if (key < root.data) {
			root.left = deleteNode(root.left, key);
		} else if (key > root.data) {
			root.right = deleteNode(root.right, key);
		} else {
			// Node to delete found

			// Case 1: Leaf node
			if (root.left == null && root.right == null) {
				return null;
			}

			// Case 2: Node with only one child
			if (root.left == null) {
				return root.right;
			}
			if (root.right == null) {
				return root.left;
			}

			// Case 3: Node with two children
			Node minNode = findMin(root.right);
			root.data = minNode.data;
			root.right = deleteNode(root.right, minNode.data);
		}

		return root;
	}

	private Node findMin(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	/************Height********Width********Diameter********************/

	/** Height or max depth of the tree **/

	public int height(Node root) {
		if (root == null)
			return 0;

		return Math.max(height(root.left), height(root.right)) + 1;
	}

	/** width of the tree **/

	public int width(Node root) {
		if (root == null)
			return 0;

		int width = Integer.MIN_VALUE;
		Queue<Node> q = new LinkedList<>();
		q.offer(root);

		while (!q.isEmpty()) {
			int count = q.size();
			width = Math.max(width, count);

			for (int i = 0; i < count; i++) {
				Node temp = q.poll();
				if (temp.left != null)
					q.offer(temp.left);
				if (temp.right != null)
					q.offer(temp.right);
			}
		}

		return width;
	}

	// Width variation: The width of one level is defined as the length between
	// the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes
	// between the end-nodes that would be present in a complete binary tree extending
	// down to that level are also counted into the length calculation.

	public int widthOfBinaryTree(Node root) {
		if (root == null)
			return 0;

		int width = Integer.MIN_VALUE;
		LinkedList<Pair> q = new LinkedList<>();
		q.add(new Pair(root, 0));

		while (!q.isEmpty()) {
			int count = q.size();
			width = Math.max(width, q.getLast().index - q.getFirst().index + 1);

			for (int i = 0; i < count; i++) {
				Pair temp = q.remove();

				if (temp.node.left != null)
					q.add(new Pair(temp.node.left, (2 * temp.index) + 1));
				if (temp.node.right != null)
					q.add(new Pair(temp.node.right, (2 * temp.index) + 2));
			}
		}

		return width;
	}

	private class Pair {
		Node node;
		int index;

		Pair(Node node, int index) {
			this.node = node;
			this.index = index;
		}
	}

	/** Diameter of the tree **/

	// Diameter is the distance between the two farthest nodes in the tree.
	// These nodes may or may not go through the root.
	// So, Diameter = Max (Diameter of left subtree, Diameter of right subtree,
	// left height + right height + 1)
	// This can be calculated in n square time. If height and diameter is
	// calculated in the same iteration then complexity can be reduced to O(n).
	public int diameter(Node root) {
		return diameterHelper(root).diameter;
	}

	private DiameterHeight diameterHelper(Node root) {
		if (root == null)
			return new DiameterHeight(0, 0);

		// Post order processing
		DiameterHeight left = diameterHelper(root.left);
		DiameterHeight right = diameterHelper(root.right);

		int height = Math.max(left.height, right.height) + 1;
		int rootDiameter = left.height + right.height + 1;
		int maxDiameter = Math.max(rootDiameter, Math.max(left.diameter, right.diameter));

		return new DiameterHeight(maxDiameter, height);
	}

	private static class DiameterHeight {
		int diameter;
		int height;

		DiameterHeight(int diameter, int height) {
			this.diameter = diameter;
			this.height = height;
		}
	}

	// Distance between two nodes =
	// distance of node1 from root + distance of node2 from root - 2 * lca(node1, node2)

	/****************************************************************************/

	/** BT to DLL **/
	private Node previous = null;

	public Node treeToDll(Node root) {
		if (root == null)
			return null;

		Node head = treeToDll(root.left);

		if (previous == null) {
			head = root;
		} else {
			previous.right = root;
			root.left = previous;
		}
		previous = root;

		treeToDll(root.right);
		return head;
	}

	/** Path finding **/

	public void findPath(Node root, int sum) {
		if (root != null) {
			findPath(root, sum, new ArrayList<>());
		}
	}

	private void findPath(Node root, int remainingSum, List<Integer> path) {
		if (root == null)
			return;

		path.add(root.data);
		remainingSum -= root.data;

		if (root.left == null && root.right == null) {
			if (remainingSum == 0)
				printPath(path);
		} else {
			findPath(root.left, remainingSum, path);
			findPath(root.right, remainingSum, path);
		}

		// backtrack by removing the last element
		path.remove(path.size() - 1);
	}

	private void printPath(List<Integer> list) {
		System.out.print("Path : ");
		list.forEach(a -> System.out.print(a + " "));
		System.out.println();
	}

	/** Tree serialize and deserialize **/

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

	public Node deserialize(List<Integer> list) {
		return deserializeHelper(list, new IndexWrapper());
	}

	private Node deserializeHelper(List<Integer> list, IndexWrapper index) {
		if (index.value >= list.size()) {
			return null;
		}

		Integer value = list.get(index.value);
		index.value++;

		if (value == -1) {
			return null;
		}

		Node node = new Node(value);
		node.left = deserializeHelper(list, index);
		node.right = deserializeHelper(list, index);
		return node;
	}

	private static class IndexWrapper {
		int value = 0;
	}

	/**********************************************************************/

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
		System.out.println("Width of the tree : " + h.width(root));
		h.serialize(root);
		System.out.println("Tree to DLL : ");
		Node head = h.treeToDll(root);
		h.printList(head);
	}

	public void serialize(Node root) {
		List<Integer> list = new ArrayList<>();
		serialize(root, list);
		System.out.print("Serialized tree : ");
		list.forEach(l -> System.out.print(l + " "));
		Node root2 = deserialize(list);
		System.out.println(this.equals(root, root2) ? "\nTree deserialized successfully" : "\nFailed to deserialize");
	}

	void printList(Node node) {
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.right;
		}
	}

	class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}
}
