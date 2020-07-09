package com.practice.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/*
 * This program explains how to use the reflection concepts.
 * 
 * Reflection means ability of a software to analyze itself. In Java, Reflection API provides facility to analyze and change runtime 
 * behavior of a Class, at runtime. 
 * 
 * For example, using reflection at the runtime you can determine what method, field, constructor or modifiers a class supports.
 *
 * java.lang.reflect package encapsulates several important interfaces and classes. These classes and interface define methods which 
 * are used for reflection. java.lang.Class is another very important class used in Reflection API.
 * 
 * java.lang.Class:
 *    Class is a final class in java.lang package which extends Object class. Instance of this class represents classes and interfaces
 *    in a running Java application. It is used to analyze and change dynamic behavior of a class at runtime.
 *    
 *    This class defines several methods using which we can get information about methods, constructors, modifiers and members of 
 *    a class at runtime. Syntax: getConstructors(), getDeclaredConstructors(), getMethods(), getDeclaredMethods(), getFields(),
 *    getDeclaredFields()
 *    
 *    forName(): This method takes fully qualified name of classes or interface as its argument and returns instance of the class 
 *    associated with it
 */
public class ReflectionTest {

	public static void main(String[] args) {
		ReflectionTest ob = new ReflectionTest();
		ob.invokeMethods();
	}

	public void reflectionConcept1() throws NoSuchFieldException, SecurityException {

		System.out.println("ClassName:" + UFOEnemyShip.class.getName());
		System.out.println("Super ClassName:" + UFOEnemyShip.class.getSuperclass());

		Method[] methods = UFOEnemyShip.class.getDeclaredMethods();
		// Method method = UFOEnemyShip.class.getDeclaredMethod(name, parameterTypes);
		// //To get the specific method
		for (Method method : methods)
			System.out.println("Method Name:" + method.getName());

		Field[] fields = UFOEnemyShip.class.getDeclaredFields();
		System.out.println("Specific field name:" + UFOEnemyShip.class.getDeclaredField("idcode"));
		for (Field field : fields)
			System.out.println("Field Name:" + field.getName());

		Constructor[] constructors = UFOEnemyShip.class.getDeclaredConstructors();
		for (Constructor constructor : constructors)
			System.out.println("Constructor Name:" + constructor.getName());

	}

	public void reflectionConcept2() {

		/**
		 * First should get the class name & then we can collect all the information of that class using the reflection API.
		 * Returntype of class name is "Class".
		 */
		Class reflectionClass = UFOEnemyShip.class;
		String className = reflectionClass.getName();
		System.out.println("Class Name:" + className);

		/**
		 * Below API's used to find the modifiers: isAbstract, isFinal, isInterface, isPrivate, isProtected isStatic, isStrict,
		 * isSynchronized, isVolatile
		 */
		int classModifier = reflectionClass.getModifiers();
		System.out.println("Is Public Modifier:" + Modifier.isPublic(classModifier));

		// Find the super class of given class name.
		Class classSuper = reflectionClass.getSuperclass();
		System.out.println("Super Class Name:" + classSuper);

		// Get the list of methods defined in the given class name. Returntype of Method should be "Method".
		// We can't access the private method using the getMethods().
		// Method[] classMethods = reflectionClass.getMethods(); //Returns all the method including Parent class methods
		Method[] classMethods = reflectionClass.getDeclaredMethods(); // Returns only declared methods
		for (Method method : classMethods) {
			System.out.println("Method Name:" + method.getName());
			System.out.println("Method ReturnType:" + method.getReturnType());
			System.out.println("Method Parameters:");
			Class[] parameters = method.getParameterTypes();
			for (Class param : parameters) {
				System.out.println(param.getName());
			}
		}

		// Get constructor used in the given class name. Returntype of constructor should be "Constructor".
		Constructor cons = null;
		Object obj = null;
		try {
			cons = reflectionClass.getConstructor(int.class, String.class);
			Class[] consParams = cons.getExceptionTypes();
			System.out.println("Cons Param Name:");
			for (Class consParam : consParams) {
				System.out.println(consParam.getName());
			}
			// Invoke(Initialize) the constructor by using the newInstance.
			obj = cons.newInstance(10, "Cons Sample");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		// To access the private field using reflection
		try {
			Field privateStringName = UFOEnemyShip.class.getDeclaredField("idcode");
			privateStringName.setAccessible(true);
			System.out.println("PrivateField:Idcode->" + privateStringName.get(obj));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		// To access the private method using Reflection
		try {
			// 1. Method without any parameters
			Method privateMethodName = UFOEnemyShip.class.getDeclaredMethod("getPrivate", null);
			privateMethodName.setAccessible(true);
			System.out.println("Invoke Private method:" + privateMethodName.getName() + "->" + privateMethodName.invoke(obj, null));

			// 2. Method with some parameters
			Class[] methParams = new Class[] { Integer.TYPE, String.class };
			privateMethodName = UFOEnemyShip.class.getDeclaredMethod("getPrivate2", methParams);
			privateMethodName.setAccessible(true);
			Object setMethParam = new Object[] { (int) new Integer(10), new String("Access getPrivate2") };
			System.out.println("Invoke Private method:" + privateMethodName.getName() + "->" + privateMethodName.invoke(obj, 5, ""));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void reflectionConcept3() {

		/*
		 Foo f = new Foo();
		// Example 1: Get class name from object//
		//System.out.println("Class Name: "+f.getClass().getName());
		//f.getClass(); -> Used to get the information about class, which has lot of API to do the same.
		 
		 //Example 2: Invoke method on unknown object//
			Method singleMethod;
			Method[] allMethod;
			try {
				//getMethod -> used to get the method specified in the parameter
				System.out.println("Single Method->");
				singleMethod = f.getClass().getMethod("print3", new Class<?>[0]);
				singleMethod.invoke(f);
				
				//getMethods() -> used to get all the methods defined in the class
				System.out.println("List of Methods->");
				allMethod = f.getClass().getMethods();
				//for(Method method : allMethod){
				for(int i=0;i<allMethod.length;i++){
					allMethod[i].invoke(f);
				}
		//				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/

		// Example 3: Create object from Class instance//
		// create instance of "Class"
		Class<?> c = null;
		try {
			c = Class.forName("com.sample.reflection.Foo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		//create instance of "Foo"
		Foo f = null;
		
		try {
			f = (Foo) c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		f.print1();
		*/

		// Example 4: Get constructor and create instance//
		// create instance of "Foo"
		Foo f1 = null;
		Foo f2 = null;

		// get all constructors
		Constructor<?> cons[] = c.getConstructors();

		try {
			f1 = (Foo) cons[0].newInstance();
			f2 = (Foo) cons[1].newInstance("abc");
		} catch (Exception e) {
			e.printStackTrace();
		}
		f2.print();
		f1.print();

	}

	// Invoke the methods using reflection
	void invokeMethods() {
		try {
			Method setterMethod = ReflectionTest.class.getMethod("firstMethod", null);
			setterMethod.invoke(this, null);
			setterMethod = ReflectionTest.class.getMethod("secondMethod", null);
			setterMethod.invoke(this, null);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void firstMethod() {
		System.out.println("first method call" + "->");
	}

	public void secondMethod() {
		System.out.println("Second Method call" + "->");
	}
}

class UFOEnemyShip extends EnemyShip {

	private String	idcode	= "100";
	Integer			data;

	private String getPrivate() {
		return "getPrivate() is Executed";
	}

	private String getPrivate2(int inp1, String inp2) {
		return "getPrivate2() is Executed:" + inp1 + inp2;
	}

	public UFOEnemyShip(int no, String s) {
		System.out.println("Constructor:" + no + " " + s);
	}
}

class EnemyShip {

}

class Foo {

	String s;

	public Foo() {
	}

	public Foo(String s) {
		this.s = s;
	}

	public void print() {
		System.out.println("String frm Constructor:" + s);
	}

	public void print1() {
		System.out.println("print1");
	}

	public void print2() {
		System.out.println("print2");
	}

	public void print3() {
		System.out.println("print3");
	}
}
