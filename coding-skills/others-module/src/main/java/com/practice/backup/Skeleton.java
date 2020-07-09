package com.practice.backup;

import java.util.Scanner;

public class Skeleton {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char ch;
		int input;
		do {
			System.out.println("???? Operations:");
			System.out.println("1.Insert");
			System.out.println("2.Delete");
			System.out.println("3.Find");
			System.out.print("Enter option:");
			input = in.nextInt();
			switch (input) {

			case 1:
				System.out.println("Enter no of elements to be inserted:");
				int t = in.nextInt();
				while (t-- > 0) {
					//list.insert(in.nextInt());
				}
				System.out.println("Elements are inserted!");
				break;
			case 2:
				System.out.println("Enter element needs to be deleted:");
				//System.out.println("Element has deleted? " + list.delete(in.nextInt()));
				break;
			case 3:
				System.out.println("Enter elements needs to be find:");
				//System.out.println("Element is present in the list? " + list.find(in.nextInt()));
				break;
			default:
				System.out.println("Please enter the valid option!!!");
				break;

			}
			System.out.println("\nDisplay:");

			System.out.println("\nDo you want to continue(y/n):");
			ch = in.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		System.out.println("****Thank You******");
		in.close();
	}

}
