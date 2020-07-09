package com.practice.functional.prog.model;

/*
 * Function interface: Interface which has only one abstract method
 * @FunctionalInterface is optional
 */
@FunctionalInterface
public interface MathOperation {

	public int operation(int a, int b);

	/*
	 * Compiler shows compilation error, if below abstract method adds in
	 * interface; Error: Invalid '@FunctionalInterface' annotation; MathOperation
	 * is not a functional interface
	 */
	// public int test();

}