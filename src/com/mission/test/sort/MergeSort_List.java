package com.mission.test.sort;

public class MergeSort_List {

	public Node mergeSort(Node head) {
		if (head != null && head.next != null) {
			Node mid = getMid(head);
			// Break the list around mid
			Node temp = mid.next;
			mid.next = null;

			// After sorting current halves of the list, the order of nodes might change. So need to 
			// store current heads of respective halves for the merge process.
			Node first = mergeSort(head);
			Node second = mergeSort(temp);
			// After merge process the head of the list will change. Need to store it to return it.
			head = merge(first, second);
		}

		return head;
	}

	// Merge process returns the head of the list after the merging process.
	private Node merge(Node first, Node second) {
		if (first == null)
			return second;
		if (second == null)
			return first;

		if (first.data < second.data) {
			first.next = merge(first.next, second);
			return first;
		} else {
			second.next = merge(first, second.next);
			return second;
		}
	}

	private Node getMid(Node head) {
		Node slow = head, fast = head.next;
		while (fast != null && fast.next != null) {
			fast = fast.next;
			if (fast != null) {
				fast = fast.next;
				slow = slow.next;
			}
		}
		return slow;
	}

	public static void main(String[] args) {
		MergeSort_List s = new MergeSort_List();
		Node head = s.createLinkedList();
		head = s.mergeSort(head);
		print(head);
	}

	private static void print(Node head) {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}

	private Node createLinkedList() {
		LinkedList list = new LinkedList();
		list.add(4);
		list.add(8);
		list.add(-3);
		list.add(1);
		list.add(6);
		list.add(9);
		return list.add(2);
	}

	private class LinkedList {

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
