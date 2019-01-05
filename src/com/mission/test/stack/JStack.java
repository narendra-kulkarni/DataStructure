package com.mission.test.stack;

import java.util.Stack;

public class JStack {

	// Stack is the java class which provides LIFO data structure functionality.
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(4);
		stack.push(3);
		stack.push(7);
		stack.pop();
		System.out.println(stack.peek());
	}
}
