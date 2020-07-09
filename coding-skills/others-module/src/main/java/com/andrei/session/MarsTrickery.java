package com.andrei.session;

import java.util.Arrays;

public class MarsTrickery {
	void regularApproach(int[] a, int[][] mqueries) {
		System.out.println("Regular approach:");
		for(int j=0;j<mqueries.length;j++) {
			int left = mqueries[j][0] - 1;
			int right = mqueries[j][1];
			for (int i = 0; i < a.length; i++) {
				if (i >= left && i < right)
					a[i] += mqueries[j][2];
			}
		}
		System.out.println("Output:" + Arrays.toString(a));
	}
	
	int[] auxiliaryApproach(int[] a, int[][] mqueries) {
		System.out.println("marsTrick approach:");
		int[] b = new int[a.length];
		
		for (int i = 0; i < mqueries.length; i++) {
			int left = mqueries[i][0]-1;
			int right = mqueries[i][1];
			b[left]+=mqueries[i][2];
			b[right]-=mqueries[i][2];
		}
		
		int auxB=0;
		for (int i = 0; i < a.length; i++) {
			auxB += b[i];
			a[i]+=auxB;
		}
		
		System.out.println("Output:" + Arrays.toString(a));
		return a;
	}
	

	public static void main(String[] args) {
		int[] a = { 1, 1, 1, 4, 5, 6 };
		int[][] q1 = {{ 1, 5, 2 }, { 2, 3, 10}};
		MarsTrickery obj = new MarsTrickery();
		System.out.println("Input:" + Arrays.toString(a));
		obj.regularApproach(a, q1);
		int[] a2 = { 1, 1, 1, 4, 5, 6 };
		System.out.println("Input" + Arrays.toString(a2));
		obj.auxiliaryApproach(a2, q1);
	}

}
