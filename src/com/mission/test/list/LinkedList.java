package com.mission.test.list;

import java.util.Stack;

public class LinkedList {

	public void print(Node head) {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}

	public void printReverse(Node head) {
		if (head != null) {
			printReverse(head.next);
			System.out.print(head.data + " ");
		}
	}

	public void printReverseIterative(Node head) {
		if (head != null) {
			Node temp = head;
			Stack<Node> stack = new Stack<>();

			while (temp != null) {
				stack.push(temp);
				temp = temp.next;
			}

			while (!stack.isEmpty()) {
				temp = stack.pop();
				System.out.print(temp.data + " ");
			}
		}
	}

	public Node findMiddle(Node head) {
		Node slow = head;
		Node fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	public Node getNthFromLast(Node head, int n) {
		Node nth = head;
		Node temp = head;

		int i = 0;
		while (temp != null && i < n) {
			temp = temp.next;
			i++;
		}

		while (temp != null) {
			temp = temp.next;
			nth = nth.next;
		}

		// This condition is important
		if (i == n)
			return nth;
		else
			return null;
	}

	public Node reverse(Node head) {
		Node prev = null;
		Node curr = head;
		Node next = null;

		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		return prev;
	}

	public Node reverseKNodes(Node head, int k) {
		if (head == null || k <= 1) {
			return head;
		}

		Node prevGroupEnd = null;
		Node newHead = null;
		Node current = head;

		while (current != null) {
			Node groupStart = current;

			// Check if there are at least k nodes left in the list
			int count = 0;
			while (count < k && current != null) {
				current = current.next;
				count++;
			}

			// If we have fewer than k nodes, connect the previous group and exit
			if (count < k) {
				if (prevGroupEnd != null) {
					prevGroupEnd.next = groupStart;
				}
				break;
			}

			// Reverse the current group
			Node prev = null;
			Node curr = groupStart;
			for (int i = 0; i < k; i++) {
				Node nextNode = curr.next;
				curr.next = prev;
				prev = curr;
				curr = nextNode;
			}

			// If this is the first group, set the new head
			if (newHead == null) {
				newHead = prev;
			}

			// Connect the previous group with the current group
			if (prevGroupEnd != null) {
				prevGroupEnd.next = prev;
			}

			// Move to the next group
			prevGroupEnd = groupStart;
			groupStart.next = current;
		}

		return newHead == null ? head : newHead;
	}

	/***********************************************************/

	public static void main(String[] args) {
		LinkedList s = new LinkedList();
		Node head = s.createLinkedList();
		s.print(head);
		System.out.println();
		System.out.print("Mid of the list : " + s.findMiddle(head).data);
		System.out.println();
		s.printReverse(head);
		System.out.println();
		s.printReverseIterative(head);
		System.out.println();
		head = s.reverse(head);
		s.print(head);
		head = s.reverseKNodes(head, 3);
		System.out.println();
		s.print(head);
		System.out.println();
		Node temp = s.getNthFromLast(head, 4);
		if (temp != null)
			System.out.println("Nth (4th) node from the end : " + temp.data);
	}

	private Node createLinkedList() {
		List list = new List();
		list.add(4);
		list.add(8);
		list.add(-3);
		list.add(1);
		list.add(6);
		list.add(9);
		return list.add(2);
		// 4 8 -3 1 6 9 2
	}

	private class List {

		private Node head;

		Node add(int data) {
			if (head == null)
				head = new Node(data);
			else {
				Node temp = head;
				while (temp.next != null)
					temp = temp.next;
				temp.next = new Node(data);
			}

			return head;
		}
	}

	private class Node {
		private int data;

		private Node next;

		Node(int data) {
			this.data = data;
		}
	}
}
