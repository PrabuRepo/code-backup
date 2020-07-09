package backup;

public class TestDynamicProgramming extends DynamicProgramming {

	public static void main(String[] args) {

		TestDynamicProgramming ob = new TestDynamicProgramming();

		ob.testType1Problems();

		// ob.testType4Problems();
	}

	public void testType1Problems() {
		int[] val = { 1, 2, 3 }, weight = { 4, 5, 1 };
		int w = 4;
		System.out.println("Knapsack Problem: " + maxPossible1(val, weight, w));
		System.out.println("Knapsack Problem: " + maxPossible2(val, weight, w));

		int[] prices = { 1, 5, 8, 9, 10, 17, 17, 20 };
		System.out.println("Rod Cutting Problems: " + cutRod1(prices));
		System.out.println("Rod Cutting Problems: " + cutRod2(prices, 8));
		System.out.println("Rod Cutting Problems: " + cutRod3(prices));

		System.out.println("Min Edit Distance: " + minEditDistance("geek", "gesek"));

		System.out.println("Print max no of A's for given input: ");
		for (int N = 7; N <= 8; N++) {
			System.out.println("Recursive: " + printMaxNoOfA1(N));
			System.out.println("DP-Top Down: " + printMaxNoOfA2(N));
		}
		// System.out.println("DP-Top Down: " + printMaxNoOfA2(7));
	}

	public void testType4Problems() {
		int[] nums1 = { 1, 5, 11, 5 };
		System.out.println("Paritition Subset sum:" + canPartition1(nums1));
		System.out.println("Paritition Subset sum:" + canPartition2(nums1));
		System.out.println("Paritition Subset sum:" + canPartition3(nums1));

		int[] nums2 = { 1, 6, 5, 11 };
		System.out.println("Min Sum Partition: " + minSumPartition1(nums2));
		System.out.println("Min Sum Partition: " + minSumPartition2(nums2));
		int[] nums = { 1, 1, 1, 1, 1 };
		System.out.println("Target Sum:" + findTargetSumWays1(nums, 3));
		System.out.println("Target Sum:" + findTargetSumWays2(nums, 3));
		System.out.println("Target Sum:" + findTargetSumWays3(nums, 3));
		System.out.println("Target Sum:" + findTargetSumWays4(nums, 3));

		int[] nums3 = { 1, 2, 4, 8 };
		System.out.println("Largest Divisible Subset: " + largestDivisibleSubset(nums3));

		System.out.println("Palindromic Partioning: ");
		partition("aab");

		System.out.println("Palindromic Partioning - Min Cuts: " + minCut2("abcbm"));
		System.out.println("Palindromic Partioning - Min Cuts: " + minCut3("abcbm"));
	}
}
