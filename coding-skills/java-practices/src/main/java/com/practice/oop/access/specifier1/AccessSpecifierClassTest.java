package com.practice.oop.access.specifier1;

/*
 * Access Modifiers: Class to test the class level access specifier
 */

/* private, protected  throws Compilation Error: 
 *  - "Illegal modifier for the class AccessSpecifierClassTest1; only public, abstract & final are permitted"
 */

//private class AccessSpecifierClassTest1 {
//protected class AccessSpecifierClassTest1 {

//1. public access modifier
public class AccessSpecifierClassTest {

	public static void main(String[] args) {

	}

}

// 2. abstract access modifier
abstract class AbstractAccessSpecifierClass {

}

// 3. final access modifier
final class FinalAccessSpecifierClass {

}

// 4. Default access modifier
class DefaultAccessSpecifierClass {

}

// Public class can be inherited
class ChildPublicClass extends AccessSpecifierTest1 {

}

// Abstract class can be inherited
class AbstractChildClass extends AbstractAccessSpecifierClass {

}

// Final class can be inherited
// The type FinalChildClass cannot subclass the final class FinalAccessSpecifierClass
/*class FinalChildClass extends FinalAccessSpecifierClass {

}*/