package com.basic.datastructures.java.api;

public class MathAPI {

	public static void main(String[] args) {
		mathApi();
	}

	public static void mathApi() {

		System.out.println(Math.abs(-32));
		System.out.println(Math.round(1.49)); // 1
		System.out.println(Math.round(1.5)); // 2
		System.out.println(Math.floor(1.99)); // 1.0
		System.out.println(Math.ceil(1.01)); // 2.0

		System.out.println(Math.sqrt(9));
		System.out.println(Math.cbrt(27));// cubic root
		System.out.println(Math.pow(2, 4));

		System.out.println(Math.exp(1)); // 2.718
		System.out.println(Math.log(8));
		System.out.println(Math.log10(8));

		System.out.println(Math.max(4, 8));
		System.out.println(Math.min(4, 8));

		System.out.println(Math.PI); // 3.14
		System.out.println(Math.E); // 2.718
		System.out.println(Math.random());

	}
}
