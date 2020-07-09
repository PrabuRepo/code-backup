package com.common.model;

public interface List {

	// Add the elements at the end
	public boolean add(int data);

	// Insert the element in the specific position
	public boolean insert(int pos, int data);

	// Search the element
	public boolean contains(int data);

	// Remove the element
	public boolean remove(int data);

	// Access the element in the specific position
	public int get(int pos);

	// Check the list is empty or not
	public boolean isEmpty();

	// Find the size of the list
	public int size();

}
