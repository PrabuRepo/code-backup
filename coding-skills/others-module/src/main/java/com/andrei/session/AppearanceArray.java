package com.andrei.session;

import java.util.Arrays;

public class AppearanceArray {

	static void appArray(int[] a) {
		int[] result = new int[10];
		for(int i=0;i<a.length;i++) {
			++result[a[i]];
		}
		System.out.println("Result:"+Arrays.toString(result));
	}
	public static void main(String[] args) {
		int a[] = {3,1,5,7,1,2,1,2};
		System.out.println("Input:"+Arrays.toString(a));
		appArray(a);
	}

}
