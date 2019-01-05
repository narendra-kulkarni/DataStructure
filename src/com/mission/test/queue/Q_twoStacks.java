package com.mission.test.queue;

import java.util.Stack;

public class Q_twoStacks {

	private Stack<Integer> firstStack;

	private Stack<Integer> secondStack;

	public Q_twoStacks() {
		firstStack = new Stack<>();
		secondStack = new Stack<>();
	}

	// Approach 1 : By making insert operation heavy. In this approach, addition and removal happens from
	// stack 1 only. Stack 2 is used as auxiliary space. This method runs two loops in addition and hence
	// approach 2 is considered as the better one.
	public void insert(int num) {
		while (!firstStack.isEmpty())
			secondStack.push(firstStack.pop());
		firstStack.push(num);
		while (!secondStack.isEmpty())
			firstStack.push(secondStack.pop());
	}

	public int remove() {
		if (firstStack.isEmpty())
			throw new RuntimeException();
		return firstStack.pop();
	}

	// Approach 2 : By making remove operation heavy. In this approach, addition and removal happens from
	// different stacks. Addition will happen in stack 1 and removal will happen from stack 2. This method
	// runs only one loop while removal, hence this is considered as better approach than the first one.
	public void insert1(int num) {
		firstStack.push(num);
	}

	public int remove1() {
		if (secondStack.isEmpty()) {
			if (firstStack.isEmpty())
				throw new RuntimeException();
			while (!firstStack.isEmpty())
				secondStack.push(firstStack.pop());
		}
		return secondStack.pop();
	}

	public static void main(String[] args) {
		// Test approach 1
		Q_twoStacks q = new Q_twoStacks();
		q.insert(3);
		q.insert(7);
		q.insert(8);
		System.out.println(q.remove()); // 3

		// Test approach 2
		Q_twoStacks q1 = new Q_twoStacks();
		q1.insert1(3);
		q1.insert1(7);
		q1.insert1(8);
		System.out.println(q1.remove1()); // 3
	}
}
