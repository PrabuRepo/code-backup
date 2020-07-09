package com.practice.ood.principle.others;

/**
 * Sample program to explain the cohesion concepts
 */

/* Theory:(Similar to Single Responsibility Principle)
 * In object oriented design, cohesion refers all about how a single class is designed. Cohesion is the Object Oriented principle 
 * most closely associated with making sure that a class is designed with a single, well-focused purpose. 
 * 
 * The advantages of high cohesion is that such classes are much easier to maintain (and less frequently changed) than classes
 * with low cohesion.
 * 
 * High Cohesion Vs Low Cohesion:
 *   - High cohesion is when you have a class that does a well defined job. Low cohesion is when a class does a lot of jobs 
 *     that don’t have much in common.
 *   - High cohesion gives us better maintaining facility and Low cohesion results in monolithic classes that are difficult 
 *     to maintain, understand and reduces re-usability
 **/

public class Cohesion {
	public static void main(String[] args) {

	}
}

/* Low Cohesive:A class is identified as a low cohesive class when it contains many unrelated functions within it. And that what we need to avoid because 
 * big classes with unrelated functions hamper their maintaining. Always make your class small and with precise purpose and highly related functions.
 * 
 * Example: In this example, the purpose of MyReader class is to read the resource. But it contains some unrelated functions such as validateLocation(),
 * 	 checkFTP(), ping(). Hence it is a low cohesive.
 */
class MyReader {
	// -------------- unrelated functions
	public boolean validateLocation(String pathIP) {
		return ping(pathIP) && checkFTP(pathIP);
	}

	private boolean checkFTP(String pathIP) {
		return true;
	}

	private boolean ping(String pathIP) {
		return true;
	}

	// -------------- functions related to read resource

	// read the resource from disk
	public String readFromDisk(String fileName) {
		return "data of " + fileName;
	}

	// read the resource from web
	public String readFromWeb(String url) {
		return "data of " + url;
	}

	// read the resource from network
	public String readFromNetwork(String networkAddress) {
		return "data of " + networkAddress;
	}

}

/*
 * High Cohesion: 
 *   The code has to be very specific in its operations.
 *   The responsibilities/methods are highly related to the class/module.
 *   The term cohesion is used to indicate the degree to which a class has a single, well-focused responsibility.
 *   
 *  Example: In this example, the purpose of MyReader class is to read the resource and it does that only. It does not implement other unrelated things.
 *  Hence it is highly cohesive.
 */
class MyReader2 {
	// -------------- functions related to read resource
	// read the resource from disk
	public String readFromDisk(String fileName) {
		return "reading data of " + fileName;
	}

	// read the resource from web
	public String readFromWeb(String url) {
		return "reading data of " + url;
	}

	// read the resource from network
	public String readFromNetwork(String networkAddress) {
		return "reading data of " + networkAddress;
	}
}
