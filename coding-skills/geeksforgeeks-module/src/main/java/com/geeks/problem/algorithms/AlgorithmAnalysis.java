package com.geeks.problem.algorithms;

public class AlgorithmAnalysis {

	/*********** Constant(1) Time ************/
	public void constantTime1() {
		int c = 5;
		for (int i = 0; i < c; i++)
			System.out.println(i + " ");
	}

	/*********** Logarithmic(logn) Time ************/
	/*
	 * 1,2,4,8,16,32...
	 * 1,2^1,2^2,2^3...2^k
	 * Condition: n<=2^k
	 * when n = 2^k
	 *  Take log base 2 on both sides,
	 *   then k = logn
	 *   therefore time  complexity = O(logn)
	 *   
	 * Example; if n=16, loop executes for log(2^4) - 4 times
	 *          if n=32, loop executes for log(2^5) - 5 times
	 */
	public void logarithmicTime1(int n) {
		for (int i = 1; i <= n; i *= 2)
			System.out.println(i + " ");
	}

	public void logarithmicTime2(int n) {
		for (int i = n; i >= 1; i /= 2)
			System.out.println(i + " ");
	}

	/*********** Logarithmic(loglogn) Time ************/
	public void logLogTime2(int n) {
		int c = 2;
		for (int i = 1; i < n; i = (int) Math.pow(i, c))
			System.out.println(i + " ");
	}

	/*********** Square Root(n) Time ************/

	/*********** Linear Time(n) ************/
	public void linearTime(int n) {
		for (int i = 1; i <= n; i++)
			System.out.println(i + " ");
	}

	/*********** (nLogn) Time ************/

	/*********** Quadratic(n^2) Time ************/
	public void quadraticTime(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.println(j + " ");
			}
			System.out.println();
		}
	}

	/*********** Cubic(n^3) Time ************/
	public void cubicTime(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					System.out.println(k + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	/*********** Exponential(2^n) Time ************/
	public void exponentialTime(int n) {
		System.out.print(n + "");
		exponentialTime(n - 1);
		System.out.println();
		exponentialTime(n - 1);
	}

	/*********** Factorial(n!) Time ************/

	/****************** Recurrence Relation ******************/
	/******** 1.Recurrence relation for decreasing function *********/
	/*
	 * Summary for recurrence relation - decreasing function:
	 *   Recurrence Relation       Time Complexity
	 *   T(n) = T(n-1)+C      ->       O(n)
	 *   T(n) = T(n-1)+n      ->       O(n^2)
	 *   T(n) = T(n-1)+logn   ->       O(nlogn)
	 *   T(n) = T(n-1)+n^2    ->       O(n^3)
	 *   T(n) = T(n-2)+C      ->       n/2 = O(n)
	 *   T(n) = T(n-100)+C    ->       n/100 = O(n)
	 *   T(n) = 2T(n-1)+C     ->       O(2^n)
	 *   
	 *   Masters Theorem Decreasing Function
	 *     T(n) = aT(n-b)+f(n) where a>0, b>0 and f(n) = O(n^k) where k>=0
	 *       n - size of the current problem
	 *       a - no of subproblems in the recursion
	 *       n-b - size of the each subproblem
	 *       f(n) - cost of the work that has to be done outside the recursive calls(cost of dividing & merging)
	 *       
	 *       3 cases:
	 *          1. if a<1, O(f(n))
	 *          2. if a=1, O(n*f(n))
	 *          3. if a<1, O(a^n/b * f(n)) 
	 */

	/*
	 * Recurrence Relation for below method:
	 *   T(n) = 1(To denote the return);
	 *   T(n) = T(n-1)+C; when n>0      
	 * Time Complexity: O(n)
	 */
	public void recurrenceRelation1(int n) {
		if (n <= 0)
			return;
		System.out.print(n + " ");
		// n-1 recursive calls
		recurrenceRelation1(n - 1);
	}

	/*
	 * Recurrence Relation for below method: 
	 * 	 T(n) = 1; when n<=0(To denote the return)
	 *   T(n) = T(n-1)+n; when n>0           
	 * Time Complexity: O(n^2)
	 */
	public void recurrenceRelation2(int n) {
		if (n <= 0)
			return;
		// Executes n times
		for (int i = 0; i < n; i++)
			System.out.print(n + " ");
		System.out.println();
		// n-1 recursive calls
		recurrenceRelation2(n - 1);
	}

	/*
	 * Recurrence Relation for below method: 
	 * 	 T(n) = 1; when n<=0(To denote the return)
	 *   T(n) = T(n-1)+logn; when n>0           
	 * Time Complexity: O(n^2)
	 */
	public void recurrenceRelation3(int n) {
		if (n <= 0)
			return;
		// Executes logn times
		for (int i = 0; i < n; i *= 2)
			System.out.print(n + " ");
		System.out.println();
		// n-1 recursive calls
		recurrenceRelation3(n - 1);
	}

	/*
	 * Recurrence Relation for below method:
	 *   T(n) = 1; when n<=0(To denote the return)
	 *   T(n) = 2T(n-1)+C; when n>0      
	 * Time Complexity: O(2^n)
	 */
	public void recurrenceRelation4(int n) {
		if (n <= 0)
			return;
		System.out.print(n + " ");
		// n-1 recursive calls
		recurrenceRelation4(n - 1);
		// n-1 recursive calls
		recurrenceRelation4(n - 1);
	}

	/******** 2.Recurrence relation for dividing function *********/
	/*
	 * Summary for recurrence relation - dividing function:
	 *   Recurrence Relation       Time Complexity
	 *   T(n) = T(n/2)+C      ->       O(logn)
	 *   T(n) = T(n/2)+n      ->       O(n)
	 *   T(n) = 2T(n/2)+C     ->       O(n) 
	 *   T(n) = 4T(n/2)+C     ->       O(n^2)
	 *   T(n) = 4T(n/2)+n     ->       O(n^2)
	 *   
	 *   Masters Theorem Decreasing Function (Below O - is theta, not Big-Oh)
	 *     T(n) = aT(n/b)+f(n) where a>0, b>0 and f(n) = O(n^k log^p(n)) where k>=0
	 *     	 n - size of the current problem
	 *       a - no of subproblems in the recursion
	 *       n/b - size of the each subproblem
	 *       f(n) - cost of the work that has to be done outside the recursive calls(cost of dividing & merging) 
	 *       3 cases:
	 *          1. if log(b base a) > k, O(n^log(b base a))
	 *          2. if log(b base a) = k,
	 *             i. if p>-1 O(n^k log^p+1(n))
	 *             ii. if p=-1 O(n^k loglogn)
	 *             iii. if p<-1 O(n^k)
	 *          3. if log(b base a) < k, 
	 *             i. if p>=0 O(n^k log^p(n))
	 *             ii. if p<0 O(n^k)
	 */

	/*
	 * Recurrence Relation for below method:
	 *   T(n) = 1; when n<=0(To denote the return)
	 *   T(n) = T(n/2)+C; when n>0      
	 * Time Complexity: O(logn)
	 */
	public void recurrenceRelation5(int n) {
		if (n <= 1)
			return;
		System.out.print(n + " "); // constant time

		// n/2 recursive calls
		recurrenceRelation5(n / 2);
	}

	/*
	 * Recurrence Relation for below method:
	 *   T(n) = 1; when n<=0(To denote the return)
	 *   T(n) = T(n/2)+n; when n>0      
	 * Time Complexity: O(n)
	 */
	public void recurrenceRelation6(int n) {
		if (n <= 1)
			return;
		for (int i = 0; i < n; i++) // n times
			System.out.print(i + " ");

		System.out.println();
		// n/2 recursive calls
		recurrenceRelation6(n / 2);
	}

	/*
	 * Recurrence Relation for below method:
	 *   T(n) = 1; when n<=0(To denote the return)
	 *   T(n) = 2T(n/2)+n; when n>0      
	 * Time Complexity: O(n)
	 */
	public void recurrenceRelation7(int n) {
		if (n <= 1)
			return;
		for (int i = 0; i < n; i++) // n times
			System.out.print(i + " ");
		System.out.println();

		// n/2 recursive calls
		recurrenceRelation7(n / 2);
		// n/2 recursive calls
		recurrenceRelation7(n / 2);
	}

	/******** 2.Recurrence relation for root function *********/
	/*
	 * Summary for recurrence relation - root function:
	 *   Recurrence Relation       Time Complexity
	 *   T(n) = T(n/2)+C      ->       O(logn)
	 */

	/*
	 * Recurrence Relation for below method:
	 *   T(n) = 1; when n<=0(To denote the return)
	 *   T(n) = T(sqrt(n))+C; when n>0      
	 * Time Complexity: O(loglogn)
	 */
	public void recurrenceRelation8(int n) {
		if (n <= 1)
			return;
		System.out.print(n + " "); // constant time

		// sqrt(n) recursive calls
		recurrenceRelation8((int) Math.sqrt(n));
	}
}
