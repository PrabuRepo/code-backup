package com.practice.others.util.random;

import java.util.Random;

/*Random Number Generators:
 *  Math Random: Many applications will find the method Math.random() simpler to use. The Math class contains the static 
 *  Math.random() method to generate random numbers of the double type. The random() method returns a double value with 
 *  a positive sign, greater than or equal to 0.0 and less than 1.0. When you call Math.random(), under the hood, 
 *  a java.util.Random pseudorandom-number generator object is created and used.
 *  
 *  Util Random: Instances of java.util.Random are threadsafe. However, the concurrent use of the same java.util.Random 
 *  instance across threads may encounter contention and consequent poor performance. Consider instead using ThreadLocalRandom 
 *  in multi threaded designs.
 *  
 *  Secure Random: Instances of java.util.Random are not cryptographically secure. Consider instead using SecureRandom to 
 *  get a cryptographically secure pseudo-random number generator for use by security-sensitive applications.
 *  This class provides a cryptographically strong random number generator (RNG).
 *  SecureRandom must produce non-deterministic output. Therefore any seed material passed to a SecureRandom object must be
 *  unpredictable, and all SecureRandom output sequences must be cryptographically strong
 *  
 *  Note: Random number generates from 0 to maxLimit-1; Eg: for input 10, generates 0 to 9.
 *  
 *  Ref: https://www.geeksforgeeks.org/java-util-random-class-java/
 */
public class RandomSample {

	public static void main(String[] args) {
		RandomSample ob = new RandomSample();

		System.out.println("Util Random Methods: ");
		ob.testUtilRandom();

		System.out.println("\n\nMath Random Methods: ");
		ob.testMathRandom();
	}

	public void testUtilRandom() {
		System.out.println("Random Number for Int: ");
		utilRandom1();

		System.out.println("\nRandom Number from 0 to given value: ");
		utilRandom2(100);

		System.out.println("\nRandom Number for a given range: ");
		utilRandom3(20, 25);

		System.out.println("\nRandom Characters: ");
		utilRandomChar();
	}

	public void testMathRandom() {
		System.out.println("Random Number for Int: ");
		mathRandom1();

		System.out.println("\nRandom Number from 0 to given value: ");
		mathRandom2(100);

		System.out.println("\nRandom Number for a given range: ");
		mathRandom3(20, 25);
	}

	public void utilRandom1() {
		Random random = new Random();
		for (int i = 0; i < 10; i++)
			System.out.print(random.nextInt() + " ");
	}

	public void utilRandom2(int bound) {
		Random random = new Random();
		//This prints from 0 to bound-1
		for (int i = 0; i < 10; i++)
			System.out.print(random.nextInt(bound) + 1 + " ");
	}

	public void utilRandom3(int lowerBound, int upperBound) {
		//This prints lowerBound to higherBound
		for (int i = 0; i < 10; i++)
			System.out.print(randomGenerator(lowerBound, upperBound) + " ");
	}

	public int randomGenerator(int lowerBound, int upperBound) {
		Random random = new Random();
		return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
	}

	public void utilRandomChar() {
		Random random = new Random();
		System.out.println("Upper Case chars: ");
		for (int i = 0; i < 10; i++)
			System.out.print((char) (random.nextInt(26) + 65) + " ");

		System.out.println("\nLower Case chars: ");
		for (int i = 0; i < 10; i++)
			System.out.print((char) (random.nextInt(26) + 97) + " ");
	}

	public void mathRandom1() {
		//It prints from 0.0 to 1.0 double value 
		for (int i = 0; i < 10; i++)
			System.out.print(Math.random() + " ");
	}

	public void mathRandom2(int bound) {
		for (int i = 0; i < 10; i++)
			System.out.print((int) (Math.random() * bound) + " ");
	}

	public void mathRandom3(int lowerBound, int upperBound) {
		for (int i = 0; i < 10; i++)
			System.out.print(randomGenerator(lowerBound, upperBound) + " ");
	}

	public int mathRandomGenerator(int lowerBound, int upperBound) {
		return (int) (Math.random() * (upperBound - lowerBound + 1)) + lowerBound;
	}

}
