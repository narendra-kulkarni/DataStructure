package com.mission.test.dp;

import java.util.Arrays;

public class BoxStacking {

	public int getMaxHeight(Box[] boxes) {
		// Store all three rotations of the box in the new array
		Box[] finalBoxes = new Box[boxes.length * 3];
		int num = 0;
		for (int i = 0; i < boxes.length; i++) {
			finalBoxes[num++] = boxes[i];

			int height = boxes[i].width;
			int depth = Math.max(boxes[i].height, boxes[i].width);
			int width = Math.min(boxes[i].height, boxes[i].width);
			finalBoxes[num++] = new Box(height, width, depth);

			height = boxes[i].depth;
			depth = Math.max(boxes[i].height, boxes[i].width);
			width = Math.min(boxes[i].height, boxes[i].width);
			finalBoxes[num++] = new Box(height, width, depth);
		}

		// Sort the boxes in the decreasing order of the base area
		Arrays.sort(finalBoxes);

		// Create values array to store height
		int[] values = new int[finalBoxes.length];
		for (int i = 0; i < values.length; i++)
			values[i] = finalBoxes[i].height;

		// Modified LIS loops
		for (int i = 1; i < finalBoxes.length; i++) {
			for (int j = 0; j < i; j++) {
				if (finalBoxes[i].width < finalBoxes[j].width && finalBoxes[i].depth < finalBoxes[j].depth
						&& values[i] < values[j] + finalBoxes[i].height)
					values[i] = values[j] + finalBoxes[i].height;
			}
		}

		// Get the maximum height
		int max = values[0];
		for (int i = 1; i < values.length; i++)
			if (values[i] > max)
				max = values[i];

		return max;
	}

	public static void main(String[] args) {
		BoxStacking b = new BoxStacking();
		Box[] boxes = { b.new Box(4, 6, 7), b.new Box(1, 2, 3), b.new Box(4, 5, 6), b.new Box(10, 12, 32) };
		int res = b.getMaxHeight(boxes);
		System.out.println("The maximum attainable height is : " + res);
	}

	private class Box implements Comparable<Box> {
		int height;
		int width;
		int depth;

		Box(int h, int w, int d) {
			width = w;
			depth = d;
			height = h;
		}

		@Override
		public int compareTo(Box o) {
			return (o.width * o.depth) - (this.width * this.depth);
		}
		
		@Override
		public String toString() {
			return height + "x" + width + "x" + depth; 
		}
	}
}
