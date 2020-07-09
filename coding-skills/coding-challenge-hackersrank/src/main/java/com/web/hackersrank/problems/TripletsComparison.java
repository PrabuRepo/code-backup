package com.web.hackersrank.problems;

import java.util.Scanner;

public class TripletsComparison {

	static int[] solve(int a0, int a1, int a2, int b0, int b1, int b2){
        int[] result = new int[2];
        int aTotal = 0, bTotal = 0;
 
       if(a0>b0)
    	   aTotal += 1;
       else if(a0<b0)
    	   bTotal += 1;
    
       if(a1>b1)
           aTotal += 1;
       else if(a1<b1)
           bTotal += 1;
        
       if(a2>b2)
          aTotal += 1;
       else if(a2<b2)
           bTotal += 1;
   
        result[0] = aTotal;
        result[1] = bTotal;
        return result;
    }

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a0 = in.nextInt();
		int a1 = in.nextInt();
		int a2 = in.nextInt();
		int b0 = in.nextInt();
		int b1 = in.nextInt();
		int b2 = in.nextInt();
		int[] result = solve(a0, a1, a2, b0, b1, b2);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
		}
		System.out.println("");
		in.close();
	}

}
