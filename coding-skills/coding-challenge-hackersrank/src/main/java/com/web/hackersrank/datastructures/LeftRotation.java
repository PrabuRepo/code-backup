package com.web.hackersrank.datastructures;

import java.util.Scanner;

public class LeftRotation {
	
	//Try to implement this using Circular  Linked List

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        int[] a = new int[n];
        for(int a_i = 0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
     //My solution
      int[] result = new int[n];
        int count =0;
        for(int i=d;i<n;i++) 
        	result[count++] = a[i]; 
        
        for(int j=0;j<d;j++)
        	result[count++]= a[j];
        
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
        //Better solution
        //for(int i=0;i<n;i++)  
        	//System.out.print(a[(i+d)%n]+" ");
        
        System.out.println("");


        in.close();
	}

}
