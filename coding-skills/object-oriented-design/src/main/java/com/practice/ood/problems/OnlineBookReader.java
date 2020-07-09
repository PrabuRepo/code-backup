package com.practice.ood.problems;

import java.util.HashMap;

public class OnlineBookReader {

}

class Book {
	private int bookId;
	private String details;

	public Book(int id, String det) {
		bookId = id;
		details = det;
	}

	public void update() {
	}

	public int getID() {
		return bookId;
	}

	public void setID(int id) {
		bookId = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}

class Display {
	private Book activeBook;
	private User2 activeUser;
	private int pageNumber = 0;

	public void displayUser(User2 user) {
		activeUser = user;
		refreshUsername();
	}

	public void displayBook(Book book) {
		pageNumber = 0;
		activeBook = book;

		refreshTitle();
		refreshDetails();
		refreshPage();
	}

	public void refreshUsername() {
		/* updates username display */
	}

	public void refreshTitle() {
		/* updates title display */
	}

	public void refreshDetails() {
		/* updates details display */
	}

	public void refreshPage() {
		/* updated page display */
	}

	public void turnPageForward() {
		pageNumber++;
		refreshPage();
	}

	public void turnPageBackward() {
		pageNumber--;
		refreshPage();
	}
}

class Library {

	private HashMap<Integer, Book> books;

	public Book addBook(int id, String details) {
		if (books.containsKey(id)) {
			return null;
		}
		Book book = new Book(id, details);
		books.put(id, book);
		return book;
	}

	public boolean remove(Book b) {
		return remove(b.getID());
	}

	public boolean remove(int id) {
		if (!books.containsKey(id)) {
			return false;
		}
		books.remove(id);
		return true;
	}

	public Book find(int id) {
		return books.get(id);
	}
}

class OnlineReaderSystem {
	private Library library;
	private UserManager userManager;
	private Display display;

	private Book activeBook;
	private User2 activeUser;

	public OnlineReaderSystem() {
		userManager = new UserManager();
		library = new Library();
		display = new Display();
	}

	public Library getLibrary() {
		return library;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public Display getDisplay() {
		return display;
	}

	public Book getActiveBook() {
		return activeBook;
	}

	public void setActiveBook(Book book) {
		display.displayBook(book);
		activeBook = book;
	}

	public User2 getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User2 user) {
		activeUser = user;
		display.displayUser(user);
	}
}

class User2 {
	private int userId;
	private String details;
	private int accountType;

	public void renewMembership() {
	}

	public User2(int id, String details, int accountType) {
		userId = id;
		this.details = details;
		this.accountType = accountType;
	}

	/* getters and setters */
	public int getID() {
		return userId;
	}

	public void setID(int id) {
		userId = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
}

class UserManager {
	private HashMap<Integer, User2> users;

	public User2 addUser(int id, String details, int accountType) {
		if (users.containsKey(id)) {
			return null;
		}
		User2 user = new User2(id, details, accountType);
		users.put(id, user);
		return user;
	}

	public boolean remove(User2 u) {
		return remove(u.getID());
	}

	public boolean remove(int id) {
		if (!users.containsKey(id)) {
			return false;
		}
		users.remove(id);
		return true;
	}

	public User2 find(int id) {
		return users.get(id);
	}
}