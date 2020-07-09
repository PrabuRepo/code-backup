package com.andrei.session;

import java.util.Arrays;

public class CountingSort {

	static void sort(int[] a) {
		int[] appArray = new int[25];
		int max=0;
		//Find the appearance array
		for(int i=0;i<a.length;i++) { 
			++appArray[a[i]];
			if(max < a[i]) 
				max = a[i];
		}
		System.out.println("Max in the elements:"+max);
		System.out.println("Appearance Array:"+Arrays.toString(appArray));
		
		int[] result = new int[a.length];
		int k = 0;
		for(int i=0; i<=max; i++) {
			System.out.println("No of iterations:"+ i);
			if(appArray[i] != 0) {
				for(int j=0; j<appArray[i]; j++) {
					result[k] = i;
					++k;
				}
			}
		}
		
		System.out.println("Output:"+Arrays.toString(result));
	}
	
	public static void main(String[] args) {
		int a[] = {3,1,24,7,1,15,2,1,24,15,10};
		System.out.println("Input:"+Arrays.toString(a));
		sort(a);
	}

}
