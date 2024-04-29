package com.mission.test.array.arrangement;

import java.util.HashMap;
import java.util.Map;

public class ParkingProblem {

	public void getInstructions(char[] allotted, char[] current) {
		Map<Character, Integer> currentMap = new HashMap<>();
		for (int i = 0; i < current.length; i++)
			currentMap.put(current[i], i);

		for (int j = 0; j < allotted.length; j++) {
			if (allotted[j] != ' ') {
				if (currentMap.get(allotted[j]) != j) {
					if (current[j] != ' ')
						move(currentMap, j, current[j], current);
					move(currentMap, currentMap.get(allotted[j]), allotted[j], current);
				}
			}
		}
	}

	private void move(Map<Character, Integer> currentMap, int src, char srcChar, char[] current) {
		currentMap.put(srcChar, currentMap.get(' '));
		current[currentMap.get(' ')] = srcChar;
		System.out.println("Move car " + srcChar + " to location " + currentMap.get(' '));
		currentMap.put(' ', src);
		current[src] = ' ';
	}

	public static void main(String[] args) {
		char[] allotted = {'C', ' ', 'B', 'A', 'E', 'D', 'F', 'G'};
		char[] current = {'A', 'D', 'F', 'C', 'B', 'G', 'E', ' '};
		ParkingProblem p = new ParkingProblem();
		p.getInstructions(allotted, current);
		print(current);
	}

	private static void print(char[] arr) {
		for (char ch : arr)
			System.out.print(ch + " ");
	}
}
