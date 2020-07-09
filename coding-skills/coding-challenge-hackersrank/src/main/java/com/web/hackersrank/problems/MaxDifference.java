package com.web.hackersrank.problems;

import java.util.Scanner;

public class MaxDifference {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();

		Difference difference = new Difference(a);

		difference.computeDifference();

		System.out.print(difference.maximumDifference);
	}

}

class Difference {
	private int[] elements;
  	public int maximumDifference;
  	
  	public Difference(int[] a) {
		this.elements = a;
	}
  	
  	public void computeDifference() {
  		int temp= 0;
  		for(int i=0; i<elements.length;i++) {
  			for(int j=i+1;j<elements.length;j++) {
  				if(elements[i]>elements[j]) {
  					temp = elements[i];
  					elements[i]=elements[j];
  					elements[j]=temp;
  				}
  			}
  		}
  		maximumDifference = elements[elements.length-1]-elements[0];
  	}
}
