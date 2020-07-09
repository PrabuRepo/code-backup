package com.practice.oop.concepts;

import java.util.ArrayList;
import java.util.List;

/*
 * Composition: Composition is a restricted form of Aggregation in which two entities are highly dependent on each other. 
 *  - It represents part-of relationship.
 *  - In composition, both the entities are dependent on each other.
 *  - When there is a composition between two entities, the composed object cannot exist without the other entity.
 *  
 *  Aggregation vs Composition 
 *   - Dependency: Aggregation implies a relationship where the child can exist independently of the parent. For example, 
 *        Bank and Employee, delete the Bank and the Employee still exist. whereas Composition implies a relationship where 
 *        the child cannot exist independent of the parent. Example: Human and heart, heart don’t exist separate to a Human
 *   - Type of Relationship: Aggregation relation is “has-a” and composition is “part-of” relation.
 *   - Type of association: Composition is a "strong Association" whereas Aggregation is a "weak Association".
 */
public class Composition {
	public static void main(String[] args) {

		// Creating the Objects of Book class.
		Book b1 = new Book("EffectiveJ Java", "Joshua Bloch");
		Book b2 = new Book("Thinking in Java", "Bruce Eckel");
		Book b3 = new Book("Java: The Complete Reference", "Herbert Schildt");

		// Creating the list which contains the no. of books.
		List<Book> books = new ArrayList<Book>();
		books.add(b1);
		books.add(b2);
		books.add(b3);

		Library library = new Library(books);

		List<Book> bks = library.getTotalBooksInLibrary();
		for (Book bk : bks) {
			System.out.println("Title : " + bk.title + " and " + " Author : " + bk.author);
		}
	}
}

class Book {
	public String	title;
	public String	author;

	Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
}

// Libary class contains list of books.
class Library {
	// reference to refer to list of books.
	private final List<Book> books;

	Library(List<Book> books) {
		this.books = books;
	}

	public List<Book> getTotalBooksInLibrary() {
		return books;
	}

}
