package com.mission.test.stack;

import java.util.LinkedList;
import java.util.Queue;

public class Stack_twoQ<T> {

	private Queue<T> firstq;

	private Queue<T> secondq;

	public Stack_twoQ() {
		firstq = new LinkedList<>();
		secondq = new LinkedList<>();
	}

	// This can be done by making remove/pop operation heavy(like q with two stacks). Addition will always happen
	// in the first queue. In removal, transfer n-1 elements from one queue to the empty queue. 
	public void push(T num) {
		firstq.offer(num);
	}

	public T pop() {
		if (firstq.isEmpty()) {
			while (secondq.size() > 1) 
				firstq.offer(secondq.poll());
			return secondq.poll();
		} else {
			while (firstq.size() > 1)
				secondq.offer(firstq.poll());
			return firstq.poll();
		}
	}

	public static void main(String[] args) {
		Stack_twoQ<Integer> stack = new Stack_twoQ<>();
		stack.push(4);
		stack.push(7);
		stack.push(9);
		System.out.println(stack.pop()); //9
		stack.push(12);
		System.out.println(stack.pop()); //12
	}
}
