package com.mission.test.queue;

//import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class JQ {

	public static void main(String[] args) {
		// Simple queue implementation. LinkedList is the class which provides FIFO data structure functionalities.
		// Queue is the interface which defines the queue operations.
		Queue<Integer> q = new LinkedList<>();
		q.offer(4);
		q.offer(5);
		q.offer(8);
		System.out.println(q.peek()); // 4
		System.out.println(q.poll()); // 4
		System.out.println(q.peek()); // 5

		// Doubly ended queue is a data structure where add and remove operations can be performed from both the 
		// ends. Deque is the interface which defines doubly ended queue operations. Java has provided concrete
		// implementations through LinkedList and ArrayDeque classes.
		Deque<Integer> dq = new LinkedList<>();
		//Deque<Integer> dq1 = new ArrayDeque<>();
		
		dq.offer(4); // 4
		dq.offerLast(5); // 4 5
		dq.offerFirst(3); // 3 4 5
		dq.poll(); // 4 5
		dq.offer(6); // 4 5 6
		dq.pollLast(); // 4 5
		dq.pollFirst(); // 5
	}
}
