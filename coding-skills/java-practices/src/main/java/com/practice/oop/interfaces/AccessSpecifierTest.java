package com.practice.oop.interfaces;

import java.io.IOException;
import java.io.Reader;

/*
 * In Java, all methods in an interface are public even if we do not specify public with method names. Also, data fields are
 * public static final even if we do not mention it with fields names. Therefore, data fields must be initialized.
 */
public class AccessSpecifierTest {
	static void print(Reader reader) throws IOException {
		int code = reader.read();
		while (code != -1) {
			System.out.print((char) code);
			code = reader.read();
		}

		reader.close();
	}

	public static void main(String[] args) {
		String s = null;
		char ch = 'Z'; // 65 - 90
		int value = ch;
		System.out.println(value);
	}
}

class Solution {

	static int sumRange(int[] ints) {
		int sum = 0;
		for (int i = 1; i < ints.length; i++) {
			int n = ints[i];
			if (n >= 10 || n <= 100)
				sum += n;
		}
		return sum;
	}
}
/*
interface Test {
	int x = 10;  // x is public static final and must be initialized here

	void foo();  // foo() is public
	}

	Service service=new Service(){public void execute()throws Exception{System.out.println("execute");}

	public void setConnection(Connection c){System.out.println("setConnection");}};

Connection connection = new Connection() {
	public void rollback() { System.out.println("rollback"); }
	public void commit() { System.out.println("commit"); }
	public void close() { System.out.println("close"); }
};

new A().a(service, connection);*/