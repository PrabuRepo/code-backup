package com.problems.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/*
 * The 'closest' is defined as absolute difference minimized between two integers.
 */
public class ClosestElements {

	/*
	3Sum Closest - 2 ptr
	Maximum Sum Of Subarray Close To K - Sliding Window
	Closest Binary Search Tree Value I, II- Bin Search Alg
	Find Closest value in BST/Closest Binary Search Tree Value I, II- Bin Search Alg
	Closest Leaf in a Binary Tree
	Next Closest Time
	*/

	// Find K Closest Elements
	public List<Integer> findClosestElements1(int[] arr,
			int k, int x) {
		int lo = 0;
		int hi = arr.length - 1;
		while (hi - lo >= k) {
			if (Math.abs(arr[lo] - x) > Math
					.abs(arr[hi] - x)) {
				lo++;
			} else {
				hi--;
			}
		}
		List<Integer> result = new ArrayList<>(k);
		for (int i = lo; i <= hi; i++) {
			result.add(arr[i]);
		}
		return result;
	}

	//Time O(log(N - K)) to binary research and find result
	//Space O(K) to create the returned list.
	public List<Integer> findClosestElements21(int[] arr,
			int k, int x) {
		// Modification here: h = arr.length-k
		int l = 0, h = arr.length - k, m = 0;
		//Here finding the lowest index of the closest element
		while (l < h) {
			m = l + (h - l) / 2;
			if (x - arr[m] <= arr[m + k] - x)
				h = m;
			else
				l = m + 1;
		}
		/*List<Integer> result = new ArrayList<>();
		for (int i = l; i < l + k; i++)
			result.add(arr[i]);*/

		return Arrays.stream(arr, l, l + k).boxed()
				.collect(Collectors.toList());
	}

	//Same like above, only hi varies
	public List<Integer> findClosestElements22(int[] arr,
			int k, int x) {
		int lo = 0, hi = arr.length - k - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (Math.abs(x - arr[mid]) > Math
					.abs(x - arr[mid + k])) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		// return subarray
		List<Integer> res = new ArrayList(k);
		for (int i = 0; i < k; i++) {
			res.add(arr[lo + i]);
		}
		return res;
	}

	//Using minheap priority queue
	//Time: O(n*logk)
	//Space: O(k)
	public List<Integer> findClosestElements31(int[] arr,
			int k, int x) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (pq.size() > k - 1) {
				if (Math.abs(x - arr[i]) < Math
						.abs(x - pq.peek())) {
					pq.poll();
					pq.add(arr[i]);
				}
			} else
				pq.add(arr[i]);
		}
		int s = pq.size();
		for (int i = 0; i < s; i++) {
			result.add(pq.remove());
		}
		return result;
	}

	public List<Integer> findClosestElements32(int[] arr,
			int k, int x) {
		// maxHeap, sort descendingly according to diff to x
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
				(a, b) -> Math.abs(x - b) == Math.abs(x - a)
						? b - a
						: Math.abs(x - b)
								- Math.abs(x - a));
		// each time, if we have better option, delete num with max diff from x, and insert the new num
		for (int num : arr) {
			maxHeap.offer(num);
			if (maxHeap.size() > k) {
				maxHeap.poll();
			}
		}
		// convert heap back to List<Integer> and sort them to get the original order
		List<Integer> res = new ArrayList(maxHeap);
		Collections.sort(res);
		return res;
	}

	// Simple collection approach
	public List<Integer> findClosestElements4(int[] arr,
			int k, int x) {
		// convert int[] to List<Integer> for better implementation simplicity
		List<Integer> list = Arrays.stream(arr).boxed()
				.collect(Collectors.toList());
		Collections.sort(list, (a, b) -> a == b ? a - b
				: Math.abs(a - x) - Math.abs(b - x));
		list = list.subList(0, k);
		Collections.sort(list);
		return list;
	}

	//K Closest Points to Origin - Heap/QuickSelect or Partition

	//Euclidean Distance Formula: Sqrt((x2-x1)^2 - (y2-y1)^2)
	//But int this problem distance calculates from origin(0,0)

	//Sort:  O(NlogN),
	public int[][] kClosest1(int[][] points, int K) {
		Arrays.sort(points, (p1,
				p2) -> (getDistance(p1) - getDistance(p2)));
		return Arrays.copyOfRange(points, 0, K);
	}

	//Using Heap: Time: O(nlogk), space: O(k)
	public int[][] kClosest2(int[][] points, int k) {
		PriorityQueue<int[]> queue = new PriorityQueue<>(
				(p1, p2) -> (getDistance(p2))
						- getDistance(p1));

		for (int[] point : points) {
			queue.add(point);
			if (queue.size() > k) queue.poll();
		}
		int[][] result = new int[k][2];

		for (int i = 0; i < k; i++)
			result[i] = queue.poll();
		return result;
	}

	// QuickSelect: Avg Time: O(N), Worst Time: O(N^2)
	public int[][] kClosest(int[][] points, int K) {
		int len = points.length, l = 0, r = len - 1;
		while (l <= r) {
			int mid = helper(points, l, r);
			if (mid == K - 1) break;
			if (mid < K) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return Arrays.copyOfRange(points, 0, K);
	}

	private int helper(int[][] A, int l, int r) {
		int[] pivot = A[r];
		int i = l, j = l;
		while (j < r) {
			if (compare(A[j], pivot) < 0) {
				swap(A, i, j);
				i++;
			}
			j++;
		}
		swap(A, i, r);
		return i;
	}

	private int compare(int[] p1, int[] p2) {
		return getDistance(p1) - getDistance(p2);
	}

	private int getDistance(int[] point) {
		return point[0] * point[0] + point[1] * point[1];
	}

	private void swap(int[][] A, int i, int j) {
		int[] temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	//Quick Select/Parition: It looks interesting. Try to understand
	private int helper1(int[][] A, int l, int r) {
		int[] pivot = A[l];
		while (l < r) {
			while (l < r && compare(A[r], pivot) >= 0)
				r--;
			A[l] = A[r];
			while (l < r && compare(A[l], pivot) <= 0)
				l++;
			A[r] = A[l];
		}
		A[l] = pivot;
		return l;
	}

	//Find the Closest Palindrome
	public String nearestPalindromic(String n) {
		int order = (int) Math.pow(10, n.length() / 2);//Order used to eliminate half of digits
		Long num = Long.valueOf(new String(n));
		Long mirrorNum = mirror(num); //Same mid
		//Two Cases: 1.Increase 1 to mid;  2.handles input like 9, 99...
		Long mirrorLarger = mirror(
				(num / order) * order + order);
		//Two Cases: 1.Decrease 1 to mid & 2.handles input like 10, 100...
		Long mirrorSmaller = mirror(
				(num / order) * order - 1);

		if (mirrorNum > num) {
			mirrorLarger = (long) Math.min(mirrorNum,
					mirrorLarger);
		} else if (mirrorNum < num) {
			mirrorSmaller = (long) Math.max(mirrorNum,
					mirrorSmaller);
		}
		return String.valueOf(
				num - mirrorSmaller <= mirrorLarger - num
						? mirrorSmaller
						: mirrorLarger);
	}

	Long mirror(Long ans) {
		char[] a = String.valueOf(ans).toCharArray();
		int i = 0;
		int j = a.length - 1;
		while (i < j) {
			a[j--] = a[i++];
		}
		return Long.valueOf(new String(a));
	}
}