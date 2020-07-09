package com.web.hackersrank.problems;

import java.util.Scanner;

class FindPoint {

	public static void main(String[] args) {
		int n, px, py, qx, qy, rx, ry;
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		while(n-- > 0) {
			px = scanner.nextInt();
			py = scanner.nextInt();
			qx = scanner.nextInt();
			qy = scanner.nextInt();
			
			//equations to find the coordinates of the reflected point are; x'=2xc-x; y'=2yc-y
			rx = 2*qx - px;
			ry = 2*qy - py;
			
			System.out.println(rx + " " + ry);
		}
		scanner.close();
	}

}

