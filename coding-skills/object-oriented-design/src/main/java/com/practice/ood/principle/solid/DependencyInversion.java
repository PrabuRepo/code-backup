package com.practice.ood.principle.solid;

/*
 * The Dependency Inversion Principle (DIP) states that high-level modules should not depend upon low-level modules; 
 * they should depend on abstractions.
 * 
 * Example:
 *  A program depends on Reader and Writer interfaces that are abstractions, and Keyboard and Printer are details 
 *  that depend on those abstractions by implementing those interfaces. 
 *  Here CharCopier is oblivious to the low-level details of Reader and Writer implementations and thus you can pass 
 *  in any Device that implements the Reader and Writer interface and CharCopier would still work correctly.
 */
public class DependencyInversion {
	public void main(String[] args) {
		CharCopier charCopier = new CharCopier();
		Reader reader = new Keyboard();
		Writer writer = new Printer();
		charCopier.copy(reader, writer);
	}
}

class CharCopier {
	void copy(Reader reader, Writer writer) {
		char ch = reader.getchar();
		writer.putchar(ch);
	}
}

class Keyboard implements Reader {
	@Override
	public char getchar() {
		return 'A';
	}
}

class Printer implements Writer {
	@Override
	public void putchar(char c) {
		System.out.println("Write.." + c);
	}
}

interface Reader {
	char getchar();
}

interface Writer {
	void putchar(char c);
}
