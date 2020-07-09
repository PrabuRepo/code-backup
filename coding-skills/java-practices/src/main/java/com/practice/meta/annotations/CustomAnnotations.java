package com.practice.meta.annotations;

import java.awt.print.Book;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/*
 * There are 3 categories of Annotations:-
 * 		1. Marker Annotations:
 * 		2. Single value Annotations:
 *    3. Full Annotations:
 */
public class CustomAnnotations {

	// Marker Annotations:
	@DemoAnnotation
	void toggle() {
	}

	@SingleValue("Test")
	void singleVal() {

	}

	// Multivalue or Full Annotatons
	@Author(first = "Lokesh", last = "Gupta")
	Book book = new Book();

	@TravelRequest(id = 112233, synopsis = "Teleport me", engineer = "Mr. John Carter", date = "04/01/3007")
	public static void sendMeToMars() {
	}
}

@interface DemoAnnotation {
}

@interface SingleValue {
	public String value();
}

@interface Author {
	String first();

	String last();
}

@interface TravelRequest {
	int id();

	String synopsis();

	String engineer()

	default "[unassigned]";

	String date() default "[unimplemented]";
}

/******************Processing Annotations Using Reflection**********************/
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@interface JavaFileInfo {
	String author()

	default "unknown";

	String version() default "0.0";
}

@JavaFileInfo
class DemoClass {
	@JavaFileInfo(author = "Lokesh", version = "1.0")
	public String getString() {
		return null;
	}
}

class ProcessAnnotationExample {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		new DemoClass();
		Class<DemoClass> demoClassObj = DemoClass.class;
		readAnnotationOn(demoClassObj);
		Method method = demoClassObj.getMethod("getString", new Class[] {});
		readAnnotationOn(method);
	}

	static void readAnnotationOn(AnnotatedElement element) {
		try {
			System.out.println("\n Finding annotations on " + element.getClass().getName());
			Annotation[] annotations = element.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof JavaFileInfo) {
					JavaFileInfo fileInfo = (JavaFileInfo) annotation;
					System.out.println("Author :" + fileInfo.author());
					System.out.println("Version :" + fileInfo.version());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}