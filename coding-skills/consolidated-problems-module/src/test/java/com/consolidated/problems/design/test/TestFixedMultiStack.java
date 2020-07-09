package com.consolidated.problems.design.test;

import java.util.Arrays;

import com.consolidated.problems.design.FixedMultiStack;

public class TestFixedMultiStack {
	public static void main(String[] args) throws Exception {
		FixedMultiStack stacks = new FixedMultiStack(4);
		System.out.println(Arrays.toString(stacks.getValues()));
		stacks.push(0, 10);
		System.out.println(Arrays.toString(stacks.getValues()));
		stacks.push(1, 20);
		System.out.println(Arrays.toString(stacks.getValues()));
		stacks.push(2, 30);
		System.out.println(Arrays.toString(stacks.getValues()));

		stacks.push(1, 21);
		System.out.println(Arrays.toString(stacks.getValues()));
		stacks.push(0, 11);
		System.out.println(Arrays.toString(stacks.getValues()));
		stacks.push(0, 12);
		System.out.println(Arrays.toString(stacks.getValues()));

		stacks.pop(0);
		System.out.println(Arrays.toString(stacks.getValues()));

		stacks.push(2, 31);
		System.out.println(Arrays.toString(stacks.getValues()));

		stacks.push(0, 13);
		System.out.println(Arrays.toString(stacks.getValues()));
		stacks.push(1, 22);
		System.out.println(Arrays.toString(stacks.getValues()));

		stacks.push(2, 31);
		System.out.println(Arrays.toString(stacks.getValues()));
		stacks.push(2, 32);
		System.out.println(Arrays.toString(stacks.getValues()));
	}
}
