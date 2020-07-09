package com.geeks.problem.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

import com.common.model.BuildingPoint;
import com.common.utilities.Utils;

/*
Divide and Conquer algorithm solves a problem using following three steps:
	1. Divide: Break the given problem into subproblems of same type.
	2. Conquer: Recursively solve these subproblems
	3. Combine: Appropriately combine the answers
	Eg: Binary Search, Quick Sort, Merge Sort, Strassen’s Algorithm
 */
public class DivideAndConquer {

	/******************* Standard Algorithms Problems *********************/
	public static int binarySearch2(int[] a, int x) {
		return binarySearch(a, 0, a.length - 1, 610);
	}

	private static int binarySearch(int[] a, int l, int h, int x) {
		int index = -1;
		if (l > h) return index;

		int m = (l + h) / 2;
		if (a[m] == x) index = m;
		else if (x < a[m]) index = binarySearch(a, l, m - 1, x);
		else index = binarySearch(a, m + 1, h, x);

		return index;
	}

	public void mergeSort() {
		SortingAlgorithms ob = new SortingAlgorithms();
		// MergeSort for array
		// ob.mergeSort(a);

		// MergeSort for linked list
		// ob.mergeSortForLL(head);
	}

	public void quickSort() {
		SortingAlgorithms ob = new SortingAlgorithms();
		// QuickSort for array
		// ob.quickSort(a);
	}

	// Calculate pow(x, n)
	public int pow(int m, int n) {
		if (n == 0) return 1;
		return m * pow(m, n - 1);
	}

	// Better Approach: Time Complexity: o(n)
	public int pow2(int m, int n) {
		if (n == 0) return 1;
		else if (n % 2 == 0) return pow2(m, n / 2) * pow2(m, n / 2);
		else return m * pow2(m, n / 2) * pow2(m, n / 2);
	}

	// Time Complexity of optimized solution: O(logn)
	static int pow3(int m, int n) {
		int temp;
		if (n == 0) return 1;
		temp = pow3(m, n / 2);
		if (n % 2 == 0) return temp * temp;
		else return m * temp * temp;
	}

	// 1.Brute Force Approach: Time complexity: 0(n^2)
	public int maxSubArraySumBruteForce(int[] a) {
		int max = 0, sum = 0, start = 0, end = 0, temp = 0;
		for (int i = 0; i < a.length; i++) {
			sum = 0;
			for (int j = i; j < a.length; j++) {
				sum += a[j];
				if (sum > max) {
					max = sum;
					start = temp;
					end = j;
				}
				if (sum < 0) {
					sum = 0;
					temp = i + 1;
				}
			}
		}

		System.out.println("Starting Index: " + start + "; Ending Index: " + end);
		return max;
	}

	// 2. Divide & Conquer Algorithm:Time complexity: O(nLogn)
	public int maxSubArraySum(int[] a, int l, int h) {
		if (l == h) return a[l];
		int m = (l + h) / 2;
		return Math.max(Math.max(maxSubArraySum(a, l, m), maxSubArraySum(a, m + 1, h)), calculateSum(a, l, m, h));
	}

	public int calculateSum(int[] a, int l, int m, int h) {
		int sum = 0, leftSum = 0, rightSum = 0;

		for (int i = m; i >= l; i--) {
			sum += a[i];
			if (sum > leftSum) leftSum = sum;
		}

		sum = 0;
		for (int i = m + 1; i <= h; i++) {
			sum += a[i];
			if (sum > rightSum) rightSum = sum;
		}
		return leftSum + rightSum;
	}

	// 3. The Kadane’s Algorithm for this problem takes O(n) time
	public int maxSubArraySumKadaneAlg(int[] a) {
		int max, sum = 0, startIndexUpdate = 0, start = 0, end = 0;
		max = a[0];
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum > max) {
				max = sum;
				start = startIndexUpdate;
				end = i;
			}
			if (sum < 0) {
				sum = 0;
				startIndexUpdate = i + 1;
			}
		}
		System.out.println("Starting Index: " + start + "; Ending Index: " + end);
		return max;
	}

	/*
	 * 	1.Problem Understanding: Input consists of (l,r,h). Skyline changes based on building starting & ending point. So simply build 
	 *    the collection using starting point(1,h) & ending point(r,h) and sort based on logic(x-axis is not equal, x in increasing order 
	 *    and x-axis equal, then y in decreasing order). Then add the height/y axis one by one in the queue/map and get max value for
	 *    every entry. 
	 * 	2.Identify the challenges & problem patterns: 
	 * 	3.Solution: 
	 * 	     - Divide & Conquer 
	 * 		 - Priority Queue - Time: O(n^2)
	 *       - TreeMap - Time: O(nlogn) 
	 * 	4.Syntax required: PriorityQueue, TreeMap, Comparator-Lambda Expr
	 */
	/*SkyLine Problem using Priority Queue:
	 *   TimeComplexity:  O(n^2) (Here n - no of building points) 
	 *    As we remove from priority queue it takes O(n) time. Instead of that, we can use a TreeMap to sort values by height and 
	 *    remove values in O(log n) time. Because Time Complexity for delete operation is : O(n)
	 * TreeMap gives TimeComplexity: O(nlogn); Because Time Complexity for delete operation is : O(logn)
	 */
	public void skyLineProblem2(int[][] buildings) {

		BuildingPoint[] buildingPoints = constructBuildingPoints(buildings);

		int prevHeight, currHeight;
		List<int[]> result = new ArrayList<>();
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());// Insert height values in queue
		queue.add(0); // Set initial value
		prevHeight = 0;

		for (BuildingPoint point : buildingPoints) {
			// System.out.println("x: " + point.x + "; y: " + point.y + "; isStart: " + point.isStart);
			if (point.isStart) {
				queue.add(point.y);
			} else {
				queue.remove(point.y);
			}
			currHeight = queue.peek();
			if (prevHeight != currHeight) {
				result.add(new int[] { point.x, currHeight });
				prevHeight = currHeight;
			}
		}

		// Print the results
		for (int[] outputLine : result) {
			System.out.println(Arrays.toString(outputLine));
		}
	}

	/*SkyLine Problem using TreeMap:
	 * TreeMap gives TimeComplexity: O(nlogn); Because Time Complexity for delete operation is : O(logn)
	 */
	public void skyLineProblem3(int[][] buildings) {

		BuildingPoint[] buildingPoints = constructBuildingPoints(buildings);

		int prevHeight, currHeight, key, value;
		List<int[]> result = new ArrayList<>();
		TreeMap<Integer, Integer> map = new TreeMap<>();// height, height count
		map.put(0, 1);// Set initial value
		prevHeight = 0;

		for (BuildingPoint point : buildingPoints) {
			// System.out.println("x: " + point.x + "; y: " + point.y + "; isStart: " + point.isStart);
			key = point.y;
			if (point.isStart) {
				if (map.get(key) == null) map.put(key, 0);

				value = map.get(key);
				map.put(key, value + 1);
			} else {
				value = map.get(key);
				if (value <= 1) map.remove(key);
				else map.put(key, value - 1);
			}
			currHeight = map.lastKey();
			if (prevHeight != currHeight) {
				result.add(new int[] { point.x, currHeight });
				prevHeight = currHeight;
			}
		}

		// Print the results
		for (int[] outputLine : result) {
			System.out.println(Arrays.toString(outputLine));
		}
	}

	/* Skyline problem without any additional object 
	 * SkyLine Problem using Priority Queue: TimeComplexity:  O(n^2) (Here n - no of building points)
	 * SkyLine Problem using TreeMap: TimeComplexity: O(nlogn);
	 */
	public List<int[]> getSkyline4(int[][] buildings) {

		List<int[]> points = new ArrayList<>();
		List<int[]> result = new ArrayList<>();
		for (int i = 0; i < buildings.length; i++) {
			points.add(new int[] { buildings[i][0], buildings[i][2] }); // + denotes starting point
			points.add(new int[] { buildings[i][1], -buildings[i][2] }); // - denotes ending point
		}

		Collections.sort(points, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] != b[0]) // if x is different, sort(increasing order) based on x axis
					return a[0] - b[0];

				return b[1] - a[1]; // if x is same, sort(decreasing order) based on y axis or height
			}

		});

		// Using lambda expression
		/*Collections.sort(points, (a, b) -> {
			if (a[0] != b[0])
				return a[0] - b[0];
			return b[1] - a[1];
		});
		*/

		// Solution using Priority Queue - O(n^2) operation
		/*	PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
			queue.add(0);
			int prev = 0, curr = 0;
			for (int[] point : points) {
				if (point[1] < 0) {
					queue.remove(-point[1]);
				} else {
					queue.add(point[1]);
				}
		
				curr = queue.peek();
				if (prev != curr) {
					result.add(new int[] { point[0], curr });
					prev = curr;
				}
			}*/

		// Solution using Tree Map - O(nlogn) operation
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(0, 1);
		int prev = 0, curr = 0;
		for (int[] point : points) {
			if (point[1] < 0) {
				int value = map.get(-point[1]);
				if (value > 1) map.put(-point[1], value - 1);
				else map.remove(-point[1]);
			} else {
				map.put(point[1], map.getOrDefault(point[1], 0) + 1);
			}

			curr = map.lastKey();
			if (prev != curr) {
				result.add(new int[] { point[0], curr });
				prev = curr;
			}
		}
		return result;
	}

	private BuildingPoint[] constructBuildingPoints(int[][] buildings) {
		BuildingPoint startingPoint = null, endingPoint;
		BuildingPoint[] buildingPoints = new BuildingPoint[buildings.length * 2];
		int index = 0;
		for (int i = 0; i < buildings.length; i++) {
			startingPoint = new BuildingPoint();
			endingPoint = new BuildingPoint();

			// Pos: 0- x value from left, 1- height, 2- x value from right
			startingPoint.x = buildings[i][0];
			startingPoint.y = buildings[i][1];
			startingPoint.isStart = true;
			buildingPoints[index++] = startingPoint;

			endingPoint.x = buildings[i][2];
			endingPoint.y = buildings[i][1];
			endingPoint.isStart = false;
			buildingPoints[index++] = endingPoint;
		}

		// Sorting should be done based on x value, it handles using comparable interface in BuildingPoint class
		Arrays.sort(buildingPoints);
		return buildingPoints;
	}

	// Multiply two polynomials
	// Brute Force Approach
	public void multiplyPolynomials1(int[] a, int[] b) {
		int m = a.length, n = b.length;
		if (m == 0 && n == 0) return;

		int[] result = new int[m + n - 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result[i + j] += a[i] * b[j];
			}
		}

		System.out.println("Result: ");
		Utils.printPoly(result);
	}

	// Search in a row wise and column wise sorted matrix - Refer MatrixProblems.java for this solution

	/******************* Binary Search Based Problems *********************/
	// Median of two sorted arrays(Same Size) - Brute Force:Time Complexity- o(n)
	static int findMedian1(int[] a1, int[] a2) {
		int median = -1, m1 = -1, m2 = -1, index1 = 0, index2 = 0;
		if (a1.length == a2.length) {
			int n = a1.length;
			for (int i = 0; i <= n; i++) {
				if (index1 == n) {
					m1 = m2;
					m2 = a2[0];
				} else if (index2 == n) {
					m1 = m2;
					m2 = a1[0];
				} else if (a1[index1] < a2[index2]) {
					m1 = m2;
					m2 = a1[index1++];
				} else {
					m1 = m2;
					m2 = a2[index2++];
				}
			}
			median = (m1 + m2) / 2;
		}
		return median;
	}

	// Divide & conquer Alg(Recursive Approach)-> Time Complexity: O(logn)
	/*
	 * 1) Calculate the medians m1 and m2 of the input arrays ar1[]	and ar2[] respectively.
	2) If m1 and m2 both are equal then we are done. 
		return m1 (or m2)
	3) If m1 is greater than m2, then median is present in one of the below two subarrays.
		a)  From first element of ar1 to m1 (ar1[0...|_n/2_|])
		b)  From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
	4) If m2 is greater than m1, then median is present in one of the below two subarrays.
		a)  From m1 to last element of ar1  (ar1[|_n/2_|...n-1])
		b)  From first element of ar2 to m2 (ar2[0...|_n/2_|])
	5) Repeat the above process until size of both the subarrays becomes 2.
	6) If size of the two arrays is 2 then use below formula to get the median.
		Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
	 */
	public double findMedian2(int[] a1, int[] a2) {
		return findMedian2(a1, a2, 0, a1.length - 1, 0, a2.length - 1);
	}

	public double findMedian2(int[] a1, int[] a2, int l1, int h1, int l2, int h2) {
		int len = h1 - l1 + 1; // or h2-l2+1; Because every call, both arrays have equal no of values

		if (l1 > h1 || l2 > h2) return -1;

		if (l1 == h1 && l2 == h2) return (double) (a1[l1] + a2[l2]) / 2;

		if ((h1 - l1 == 1) && (h2 - l2 == 1)) return (double) (Math.max(a1[l1], a2[l2]) + Math.min(a1[h1], a2[h2])) / 2;

		int m1 = (h1 + l1) / 2;
		int m2 = (h2 + l2) / 2;

		if (a1[m1] == a2[m2]) {
			return a1[m1];
		} else if (a1[m1] < a2[m2]) { // Remove 1st half in a1 & 2nd half in a2
			l1 = l1 + len / 2;
			h2 = h2 - len / 2;
		} else {// Remove 2nd half in a1 & 1st half in a2
			h1 = h1 - len / 2;
			l2 = l2 + len / 2;
		}

		return findMedian2(a1, a2, l1, h1, l2, h2);
	}

	// Divide & conquer Alg(Iterative Approach)-> Time Complexity: O(logn)
	public double findMedian3(int[] a1, int[] a2) {
		int l1 = 0, h1 = a1.length - 1, l2 = 0, h2 = a2.length - 1, m1 = 0, m2 = 0, len = 0;

		while (l1 <= h1 && l2 <= h2) {
			len = h1 - l1 + 1; // or h2-l2+1; Because every call, both arrays have equal no of values
			if (l1 == h1 && l2 == h2) return (double) (a1[l1] + a2[l2]) / 2;
			if (h1 - l1 == 1 && h2 - l2 == 1) return (double) (Math.max(a1[l1], a2[l2]) + Math.min(a1[h1], a2[h2])) / 2;

			m1 = (l1 + h1) / 2;
			m2 = (l2 + h2) / 2;

			if (a1[m1] == a2[m2]) {
				return a1[m1];
			} else if (a1[m1] < a2[m2]) {
				l1 = l1 + len / 2; // or l1 = m1;
				h2 = h2 - len / 2; // or h2 = m2;
			} else {
				h1 = h1 - len / 2; // or h1 = m1;
				l2 = l2 + len / 2; // or l2 = m2;
			}
		}

		return -1;
	}

	// Median of two sorted arrays(different Size) - Brute Force:Time Complexity- O(n)
	public double findMedianDiffSizeArray1(int[] X, int[] Y) {
		return 0;
	}

	// Median of two sorted arrays(different Size) - Divide & Conquer Alg: Time Complexity- O(log(min(a,b)))
	public double findMedianDiffSizeArray2(int[] X, int[] Y) {
		int len1 = X.length, len2 = Y.length;

		if (len1 > len2) return findMedianDiffSizeArray2(Y, X);

		int maxLeftX, maxLeftY, minRightX, minRightY, low = 0, high = len1;
		while (low <= high) {
			int partitionX = (low + high) / 2;
			int partitionY = ((len1 + len2 + 1) / 2) - partitionX;

			// if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
			// if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
			maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : X[partitionX - 1];
			minRightX = partitionX == len1 ? Integer.MAX_VALUE : X[partitionX];

			maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : Y[partitionY - 1];
			minRightY = partitionY == len2 ? Integer.MAX_VALUE : Y[partitionY];

			if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
				if ((len1 + len2) % 2 == 0) { // For even no of data
					return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
				} else { // For odd no of data
					return (double) (Math.max(maxLeftX, maxLeftY));
				}
			} else if (maxLeftX > minRightY) { // we are too far on right side for partitionX. Go on left side(Reduce
												// high value).
				high = partitionX - 1;
			} else {// we are too far on left side for partitionX. Go on right side(Increase low value).
				low = partitionX + 1;
			}
		}
		return 0.0;
	}

	// K-th Element of Two Sorted Arrays
	// Brute force Approach: Time Complexity- O(m+n)
	public int findKthElement1() {
		return 0;
	}

	public int findKthElement2(int[] a1, int[] a2, int k) {
		int len1 = a1.length, len2 = a2.length;
		if (len1 > len2) return findKthElement2(a2, a1, k);

		return findKthElement2(a1, a2, 0, len1 - 1, 0, len2 - 1, k);
	}

	public int findKthElement2(int[] a1, int[] a2, int l1, int h1, int l2, int h2, int k) {
		int len1 = a1.length - 1, len2 = a2.length - 1;

		if ((len1 + len2) < k) return -1;

		if (l1 == h1 && k > 1) return a2[k - 1];

		if (k == 1) return Math.max(a1[l1], a2[l2]);

		int m1 = Math.min(len1, k / 2);
		int m2 = Math.min(len2, k / 2);

		if (a1[m1] < a2[m2]) return findKthElement2(a1, a2, l1 + m1, h1, l2, h2, k - m1);

		return findKthElement2(a1, a2, l1, h1, l2 + m2, h2, k - m2);
	}

	public static int kthElement(int[] a1, int[] a2, int k) {
		return kthElement(a1, a2, 0, a1.length - 1, 0, a2.length - 1, k);
	}

	public static int kthElement(int[] a1, int[] a2, int l1, int h1, int l2, int h2, int k) {
		int m = h1 - l1 + 1, n = h2 - l2 + 1; // Size of the array
		if (k > m + n || k < 0) return -1;

		// Keep always a1 > a2
		if (m < n) return kthElement(a2, a1, l2, h2, l1, h1, k);

		if (m == 0) return a1[l1 + k - 1]; // i.e k-1 th index in arr, adding l1 for base index

		if (k == 1) return Math.min(a1[l1], a2[l2]);

		int i = Math.min(m, k / 2), j = Math.min(n, k / 2);
		if (a1[l1 + i - 1] < a2[l2 + j - 1]) return kthElement(a1, a2, l1 + i, h1, l2, h2, k - i);

		return kthElement(a1, a2, l1, h1, l2 + j, h2, k - j);
	}

	private static void printArray(int[] a, int l, int h) {
		for (int i = l; i < h; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public int findKthSmallestElement(int[] A, int[] B, int k) {
		int lenA = A.length;
		int lenB = B.length;

		if (lenA + lenB < k) return -1;

		int iMin = 0;
		int iMax = Integer.min(A.length, k - 1);

		int i = 0;
		int j = 0;

		while (iMin <= iMax) {
			i = (iMin + iMax) / 2;
			j = k - 1 - i; // because of zero based index
			if (B[j - 1] > A[i]) {
				// i is too small, must increase it
				iMin = i + 1;
			} else if (i > 0 && A[i - 1] > B[j]) {
				// i is too big, must decrease it
				iMax = i - 1;
			} else {
				// i is perfect
				return Integer.min(A[i], B[j]);
			}
		}
		return -1;
	}

	/******************* Misc Problems *********************/

	// Square root of an integer - Refer MathAlgorithms.java for this solution

	// Search element in a sorted matrix - Refer MatrixProblems.java for this solution

	static final int	MIN	= Integer.MIN_VALUE;
	static final int	MAX	= Integer.MAX_VALUE;

	public static int KthElementUtil(int[] arr1, int[] arr2, int start1, int end1, int start2, int end2, int k) {
		int mid1 = Math.abs(start1 - end1) / 2;
		int mid2 = Math.abs(start2 - end2) / 2;
		// System.out.println("Arr1: " + start1 + "-" + end1 + "; k: " + k);
		// System.out.println("Arr2: " + start2 + "-" + end2 + "; k: " + k);
		System.out.print("Arr1: ");
		printArray(arr1, start1, end1);
		System.out.print("Arr2: ");
		printArray(arr2, start2, end2);
		System.out.println("k: " + k);

		if (start1 == end1) return arr2[start2 + k];

		if (start2 == end2) return arr1[start1 + k];

		if (mid1 + mid2 < k) {
			return arr1[start1 + mid1] < arr2[start2 + mid2]
					? KthElementUtil(arr1, arr2, start1 + mid1 + 1, end1, start2, end2, k - mid1 - 1)
					: KthElementUtil(arr1, arr2, start1, end1, start2 + mid2 + 1, end2, k - mid2 - 1);
		} else {
			return arr1[start1 + mid1] < arr2[start2 + mid2]
					? KthElementUtil(arr1, arr2, start1, end1, start2, start2 + mid2, k)
					: KthElementUtil(arr1, arr2, start1, start1 + mid1, start2, end2, k);
		}
	}

	// Largest Rectangular Area in a Histogram -

	// Refer StackProblems.java for stack Approach

	// Using Divide & Conquer Algorithm
	public int largestRectangleArea2(int[] heights) {
		if (heights.length == 0) return 0;
		return largestRectangleArea2(heights, 0, heights.length - 1);
	}

	public int largestRectangleArea2(int[] heights, int l, int h) {
		if (l > h) return 0;
		if (l == h) return heights[l];

		int m = findMin(heights, l, h);

		return max(largestRectangleArea2(heights, l, m - 1), largestRectangleArea2(heights, m - 1, l),
				(h - l + 1) * heights[m]);
	}

	private int findMin(int[] a, int l, int h) {
		int minIndex = l;
		for (int i = l + 1; i <= h; i++)
			if (a[i] < a[minIndex]) minIndex = i;

		return minIndex;
	}

	private int max(int a, int b, int c) {
		return (a > b && a > c) ? a : (b > c) ? b : c;
	}

}
