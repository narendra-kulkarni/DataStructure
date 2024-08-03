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

		while (fast != null && fast.next != null) {
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
		Node current = head;
		Node next = null;
		Node prev = null;

		int count = 0;

		/* Reverse first k nodes of linked list */
		while (count < k && current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count++;
		}

		/*
		 * next is now a pointer to (k+1)th node Recursively call for the list
		 * starting from current and make rest of the list as next of first node
		 */
		if (next != null)
			head.next = reverseKNodes(next, k);

		// prev is now head of input list
		return prev;
	}

	/***********************************************************/

	public static void main(String[] args) {
		LinkedList s = new LinkedList();
		Node head = s.createLinkedList();
		s.print(head);
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
		Node temp = s.getNthFromLast(head, 7);
		if (temp != null)
			System.out.println("Nth node : " + temp.data);
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
