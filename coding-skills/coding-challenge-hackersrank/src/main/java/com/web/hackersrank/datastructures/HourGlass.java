package com.web.hackersrank.datastructures;

import java.util.Scanner;

public class HourGlass {

	public static void main(String[] args) {
		int[][] a = new int[6][6];
		int max=0, sum=0;
		boolean setFirstMax = true;
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				a[i][j] = scanner.nextInt();
			}
		}

		for(int r=0;r<4;r++) {
			for(int c=0;c<4;c++) {
				sum = a[r][c]+a[r][c+1]+a[r][c+2]         //First row
						+a[r+1][c+1]+                     //Middle row
					  a[r+2][c]+a[r+2][c+1]+a[r+2][c+2];  //Third row
				if(setFirstMax) {
					max=sum;
					setFirstMax=false;
				}
				if(sum>max) {
					max=sum;
				}
			}
		}
		System.out.println(max);
		scanner.close();
	}
	
	static void hourClassTest() {

		int[][] a = new int[6][6];
		int rmin, rmax, cmin, cmax, sum = 0, max = -999999999;
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				a[i][j] = in.nextInt();
			}
		}

		rmin = 0;
		rmax = 2;
		for (int i = 0; i < 4; i++) {
			cmin = 0;
			cmax = 2;
			for (int j = 0; j < 4; j++) {
				System.out.println("rmin:" + rmin + "; " + "rmax:" + rmax + " | cmin:" + cmin + "; " + "cmax:" + cmax);
				sum = hourGlassSum(rmin, rmax, cmin, cmax, a);
				cmin++;
				cmax++;
				if (sum > max)
					max = sum;

				System.out.println("Sum:" + sum + "; " + "Max:" + max);
			}
			rmin++;
			rmax++;
		}

		System.out.println(max);
		in.close();
	
	}
	
	static int hourGlassSum(int rmin, int rmax, int cmin, int cmax, int[][] a) {
		int total = 0, count = 0;
		for (int i = rmin; i <= rmax; i++) {
			for (int j = cmin; j <= cmax; j++) {
				count++;
				if (!(count == 4 || count == 6)) {
					total += a[i][j];
					System.out.print(count + ":" + i + "," + j + "-" + a[i][j] + "; ");
				}
			}
		}
		System.out.println();
		return total;
	}
}
