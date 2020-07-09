package backup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * Temp Class for quick testing; Can be deleted at any time 
 */
public class Solution {
	private static int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) {

		Date d1 = new Date("7/10/2019");
		List<List<String>> list = new ArrayList<>();

		int[][] binaryMatrix = { { 0, 1, 0, 1, 0 }, { 0, 0, 1, 1, 1 }, { 1, 0, 0, 1, 0 }, { 0, 1, 1, 0, 0 },
				{ 1, 0, 1, 0, 1 } };
		HashMap<String, List<String>> map = new HashMap<>();
		List<List<String>> result = new ArrayList<>();
		result.addAll(map.values());

		// System.out.println(getNumberOfIslands(binaryMatrix));
		// System.out.println("Date: " + buildDate("").trim());

		String[] emails = { "test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com",
				"testemail+david@lee.tcode.com" };
		System.out.println("Size: " + numUniqueEmails(emails));

		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <Receipt><HTML><BODY><PRE> SLS<BR/>******************************************<BR/>SALE 8675309 5 005 00749<BR/> 8505 02/26/19 15:19<BR/>QTY SKU PRICE<BR/> *********************************<BR/> DUPLICATE COPY<BR/> Not Valid For Refund<BR/> *********************************<BR/> <BR/> <BR/>1 SLS 8.5X11 COP<BR/> 135848 47.99<BR/>1 CENTURY CROSS CHRO<BR/> 112888 200.00<BR/> *****Promotion*****<BR/>1 SINGLE TAB FLDR LT *<BR/> 116723 14.79<BR/>1 STAMPS-BOOK OF 20 *<BR/> 827378 0.90N<BR/> *Reg. Price $1.00 <BR/> *Item Discount [-0.10] <BR/> Total Promotion Discount [-0.10]<BR/> ******************************<BR/> <BR/>SUBTOTAL 263.68<BR/> Standard Tax 6.25% 16.42<BR/> <BR/>TOTAL $280.10<BR/> <BR/>Cash USD$280.10<BR/> <BR/> <BR/> <BR/> <BR/> <BR/> <BR/> TOTAL ITEMS 4<BR/>*Item is currently on promotion. Some <BR/>coupons are only valid on regular priced <BR/>items. Please see the coupon terms and <BR/>conditions for details.<BR/> <BR/> <BR/> *********************************<BR/> DUPLICATE COPY<BR/> Not Valid For Refund<BR/> *********************************<BR/> <BR/></PRE></BODY></HTML></Receipt> ";
		int si = str.indexOf("<HTML>");
		int ei = str.lastIndexOf("</Receipt>");

		System.out.println("String: " + str);
		System.out.println("substring: " + str.substring(si, ei));

		String s1 = "All Items Returned Exception exception";
		s1 = s1.replace("Exception", "");
		System.out.println("Replace: " + s1);
		s1 = s1.replace("exception", "");
		System.out.println("Replace: " + s1);

		System.out.println((int) s1.charAt(0));

		String s = "A man, a plan, a canal: Panama";
		System.out.println(isPalindrome(s));

		System.out.println("Sqrt: " + mySqrt(2147395599));

		int[] cells = { 1, 0, 0, 1, 0, 0, 1, 0 };
		prisonAfterNDays(cells, 1000000000);
	}

	public static boolean isPalindrome(String s) {
		if (s.isEmpty()) return true;

		int l = 0, h = s.length() - 1;
		char cHead, cTail;
		while (l <= h) {
			cHead = s.charAt(l);
			cTail = s.charAt(h);
			if (!Character.isLetterOrDigit(cHead)) {
				l++;
			} else if (!Character.isLetterOrDigit(cTail)) {
				h--;
			} else {
				if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) return false;
				l++;
				h--;
			}
		}

		return true;
	}

	public static int numUniqueEmails(String[] emails) {
		if (emails == null || emails.length == 0) return 0;
		Set<String> set = new HashSet<>();

		for (int i = 0; i < emails.length; i++) {
			System.out.println(applyRule(emails[i]));
			set.add(applyRule(emails[i]));
		}

		return set.size();
	}

	private static String applyRule(String str) {
		String[] arr = str.split("@");
		String localName = arr[0];
		String domainName = arr[1];

		int plusIndex = localName.indexOf("+");
		if (plusIndex != -1) localName = localName.substring(0, plusIndex);
		localName = localName.replace(".", "");
		return localName + "@" + domainName;
	}

	static int getNumberOfIslands(int[][] binaryMatrix) {
		int count = 0, m = binaryMatrix.length, n = binaryMatrix[0].length;

		System.out.println(Integer.MAX_VALUE);

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (binaryMatrix[i][j] == 1) {
					dfs2(binaryMatrix, i, j);
					count++;
				}
		return count;
	}

	static void dfs2(int[][] matrix, int i, int j) {
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == 0) return;

		matrix[i][j] = 0;

		for (int[] dir : directions) {
			dfs2(matrix, dir[0] + i, dir[1] + j);
		}
	}

	private static String buildDate(String date) {
		if (date == null || date.length() == 0) return "";
		String[] arr = date.split("/");
		return arr[2] + arr[0] + arr[1];
	}

	public static ArrayList<Integer> riverSizes(int[][] matrix) {
		ArrayList<Integer> result = new ArrayList<>();
		if (matrix.length == 0 || matrix[0].length == 0) return result;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					result.add(dfs(matrix, i, j));
				}
			}
		}

		return result;
	}

	public static int dfs(int[][] matrix, int i, int j) {
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == 0) return 0;
		matrix[i][j] = 0;
		return 1 + dfs(matrix, i + 1, j) + dfs(matrix, i - 1, j) + dfs(matrix, i, j + 1) + dfs(matrix, i, j - 1);
	}

	public static int mySqrt(int n) {
		if (n == 0 || n == 1) return n;
		int low = 1, high = n, div = 0, mid;
		while (low <= high) {
			mid = low + (high - low) / 2;
			div = n / mid;
			if (mid == div) return mid;
			else if (mid > div) high = mid - 1;
			else low = mid + 1;

		}
		return low - 1;
	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) return 0;

		LinkedList<String> queue = new LinkedList<>();
		Set<String> wordDict = new HashSet<>(wordList);
		queue.add(beginWord);
		int ladderLen = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				String top = queue.poll();
				if (top.equals(endWord)) return ladderLen;
				char[] arr = top.toCharArray();
				for (int i = 0; i < arr.length; i++) {
					for (char c = 'a'; c <= 'z'; c++) {
						if (arr[i] == c) continue;
						char temp = arr[i];
						arr[i] = c;
						String newStr = new String(arr);
						if (wordDict.contains(newStr)) {
							queue.add(newStr);
							wordDict.remove(newStr); // After visit remove the word
						}
						arr[i] = temp;
					}
				}
			}
			ladderLen++;
		}
		return 0;
	}

	public static int[] prisonAfterNDays(int[] cells, int N) {
		if (N == 0) return cells;
		int len = cells.length; // or 8 which is given in the problems
		// if N>len; Find the cycle and N % cycle length
		if (N > len) {
			int cycleLen = findCycleLength(cells);
			System.out.println(cycleLen);
			N %= cycleLen;
		}
		int[] temp = new int[len];
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < len - 1; j++) {
				temp[j] = (cells[j - 1] ^ cells[j + 1]) == 0 ? 1 : 0;
			}
			cells = temp.clone();
			System.out.println(Arrays.toString(cells));
		}

		return cells;
	}

	private static int findCycleLength(int[] cells) {
		int cycle = 0, len = cells.length;
		int[] first = new int[len], curr = new int[len];
		while (true) {
			for (int j = 1; j < len - 1; j++)
				curr[j] = (cells[j - 1] ^ cells[j + 1]) == 0 ? 1 : 0;

			System.out.println(cycle + "->" + Arrays.toString(curr));
			if (cycle == 0) first = curr.clone();
			else if (Arrays.equals(first, curr)) return cycle;
			cells = curr.clone();
			cycle++;
		}
	}
}
