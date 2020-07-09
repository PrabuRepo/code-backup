package com.practice.basics.arrays;

import java.util.Arrays;

public class ArraysAPI {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3 };
		arr = new int[5];
		arr = Arrays.copyOf(arr, 4);
		int[] res = Arrays.copyOfRange(arr, 0, 0);
		System.out.println(Arrays.toString(res));
	}
}
