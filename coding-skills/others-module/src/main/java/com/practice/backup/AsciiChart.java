package com.practice.backup;

import java.util.ArrayList;

public class AsciiChart {
	static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ?";

	public static void main(String[] args) {

		/*int width = 3, height = 5;
		System.out.println("Display char chart from given String: ");
		displayCharChart(mockData1(), width, height, "PRABU");
		
		System.out.println("Display String from  given char chart: " + getAlphabetChar(mockData1(), mockDataC(), width, height));*/

		System.out.println("Max money: " + maxMoney(3, 3));// Result = 5

	}

	public static int maxMoney(int n, int k) {
		return maxMoney(n, 1, 0, k);
	}

	public static int maxMoney(int n, int index, int currSum, int k) {
		if (index > n)
			return 0;
		currSum += index;
		if (currSum == k)
			currSum -= index;
		return Integer.max(currSum + maxMoney(n, index + 1, currSum, k), maxMoney(n, index + 1, 0, k));
	}

	public static char getAlphabetChar(ArrayList<String> asciiChart, ArrayList<String> singleChar, int width, int height) {
		int count, index = -1;
		char ch = ' ';
		int ALPHABHET_SIZE = 26;
		int w = width + 1;// width+1 -> to consider space b/w char's
		for (int i = 0; i < (ALPHABHET_SIZE * w); i = i + 4) {
			count = 0;
			for (int j = 0; j < 5; j++) {
				if (asciiChart.get(j).substring(i, i + width).equalsIgnoreCase(singleChar.get(j).substring(0, width)))
					count++;
			}
			if (count == height) {
				index = i;
				break;
			}
		}

		if (index != -1)
			ch = (char) ((index / w) + 65);
		return ch;
	}

	public static void displayCharChart(ArrayList<String> ascii, int width, int height, String text) {
		// find the necessary indexes of letters
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (char c : text.toCharArray()) {
			if (c < 'A' || c > 'Z')
				indexes.add(26);
			else {
				for (char letter : alphabet.toCharArray()) {
					if (c == letter) {
						indexes.add(alphabet.indexOf(letter));
					}
				}
			}
		}
		width = width + 1;// width+1 -> to consider space b/w char's
		// print result
		for (int j = 0; j < height; j++) {
			for (int k = 0; k < indexes.size(); k++) {
				System.out.print(ascii.get(j).substring(indexes.get(k) * width, indexes.get(k) * width + width));
			}
			System.out.println();
		}
	}

	public static ArrayList<String> mockData1() {
		ArrayList<String> ascii = new ArrayList<String>();
		/*for (int i = 0; i < height; i++) {
			ascii.add(in.nextLine());
		}*/

		// ABCDEFGHIJKLMNOPQRSTUVWXYZ?
		ascii.add(" #  ##   ## ##  ### ###  ## # # ###  ## # # #   # # ###  #  ##   #  ##   ## ### # # # # # # # # # # ### ###  ");
		ascii.add("# # # # #   # # #   #   #   # #  #    # # # #   ### # # # # # # # # # # #    #  # # # # # # # # # #   #   #  ");
		ascii.add("### ##  #   # # ##  ##  # # ###  #    # ##  #   ### # # # # ##  # # ##   #   #  # # # # ###  #   #   #   ##  ");
		ascii.add("# # # # #   # # #   #   # # # #  #  # # # # #   # # # # # # #    ## # #   #  #  # # # # ### # #  #  #        ");
		ascii.add("# # ##   ## ##  ### #    ## # # ###  #  # # ### # # # #  #  #     # # # ##   #  ###  #  # # # #  #  ###  #  ");
		return ascii;

	}

	public static ArrayList<String> mockDataA() {
		ArrayList<String> str = new ArrayList<String>();
		str.add(" # ");
		str.add("# #");
		str.add("###");
		str.add("# #");
		str.add("# #");
		return str;
	}

	public static ArrayList<String> mockDataB() {
		ArrayList<String> str = new ArrayList<String>();
		str.add("## ");
		str.add("# #");
		str.add("## ");
		str.add("# #");
		str.add("## ");
		return str;
	}

	public static ArrayList<String> mockDataC() {
		ArrayList<String> str = new ArrayList<String>();
		str.add(" ##");
		str.add("#  ");
		str.add("#  ");
		str.add("#  ");
		str.add(" ##");
		return str;
	}

	public static ArrayList<String> mockDataM() {
		ArrayList<String> str = new ArrayList<String>();
		str.add("# #");
		str.add("###");
		str.add("###");
		str.add("# #");
		str.add("# #");
		return str;
	}

	public static ArrayList<String> mockDataZ() {
		ArrayList<String> str = new ArrayList<String>();
		str.add("### ");
		str.add("  #");
		str.add(" # ");
		str.add("#  ");
		str.add("###");
		return str;
	}

}
