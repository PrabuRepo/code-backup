package com.web.codechef.problems;


class LifeUniverseEverything {

	public static void main(String[] args) throws Exception {
		int MAX_NO_ELEMENT = 10000000;
		int a[] = new int[MAX_NO_ELEMENT];
		int element, count=0;
		java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
	
		for(int i=0;i<MAX_NO_ELEMENT;i++) {
			element = Integer.parseInt(reader.readLine());
			if(element < 100 && element !=42) {
				a[i] = element;
			}else {
				break;
			}
			count++;
		}
		for(int i = 0; i<count; i++)
			System.out.println(a[i]);
	}
}

