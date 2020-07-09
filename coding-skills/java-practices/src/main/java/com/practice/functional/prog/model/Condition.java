package com.practice.functional.prog.model;

@FunctionalInterface
public interface Condition {

	public boolean test(Person person, String str);
}
