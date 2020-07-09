package com.backup;

import java.util.Arrays;
import java.util.HashMap;

public class ParkingLot {

	public int orderCars(int[] current, int[] desired) {
		int emptyIndex = -1;
		int n = current.length;
		int moves = 0;
		System.out.println("Current before resolved:" + Arrays.toString(current));
		HashMap<Integer, Integer> currentPos = new HashMap<>();
		for (int i = 0; i < n; ++i) {
			currentPos.put(current[i], i);
			if (current[i] == 0) {
				emptyIndex = i;
			}
		}

		for (int i = 0; i < n; i++) {

			// Solve cycle with empty space
			while (desired[emptyIndex] != 0) {
				int nextElem = desired[emptyIndex];
				int nextPos = currentPos.get(nextElem);

				current[emptyIndex] = nextElem;
				currentPos.replace(nextElem, emptyIndex);

				current[nextPos] = 0;
				currentPos.replace(0, nextPos);

				emptyIndex = nextPos;
				++moves;
			}

			// break this cycle if necessary
			if (current[i] != desired[i]) {
				int currentElem = current[i];

				current[emptyIndex] = currentElem;
				currentPos.replace(currentElem, emptyIndex);

				current[i] = 0;
				currentPos.replace(0, i);
				emptyIndex = i;
				++moves;
			}
		}

		System.out.println("Current:" + Arrays.toString(current));
		System.out.println("Desired:" + Arrays.toString(desired));
		return moves;
	}

	public static void main(String[] args) {
		System.out.println("Test");
		ParkingLot parkingLot = new ParkingLot();
		/*	int current[] = {1,3,0,4,2,5,6,7};
			int desired[] = {3,1,4,2,0,6,5,7};*/
		int current[] = { 5, 7, 2, 3, 0, 9, 6 };
		int desired[] = { 6, 2, 0, 5, 7, 9, 3 };
		System.out.println("Output:" + parkingLot.orderCars(current, desired));
	}
}
