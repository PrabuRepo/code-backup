package com.practice.oop.concepts;

import java.util.ArrayList;
import java.util.List;

/*
 * Composition is an association represents a part of a whole relationship where a part cannot exist without a whole. If a whole is deleted then 
 * all parts are deleted. It has a stronger relationship.
 */
public class Composition {
	public static void main(String[] args) {
		// Create Products
		Product p1 = new Product(1, "Pen", "This is red pen");
		Product p2 = new Product(2, "Pencil", "This is pencil");
		Product p3 = new Product(3, "ColorBox", "This is color box");

		// Create Order and Add Line Items
		Order o = new Order(1, "ORD#1");
		o.addItem(1, 2, p1); // Ordered of 2 quantity for p1 product
		o.addItem(2, 1, p2); // Ordered of 1 quantity for p2 product
		o.addItem(3, 5, p3); // Ordered of 5 quantity for p3 product
		// Print Order detail before deleting
		System.out.println("Order ---");
		System.out.println(o);
		// Deleting order would also delete associated LineItems
		o = null;
	}
}

/*
 * Let's take an example of Placing Order. If order HAS-A line-items, then an order is a whole and line items are parts. If an order is deleted
 * then all corresponding line items for that order should be deleted.
 */
class Order {
	private int id;
	private String name;
	private List<LineItem> lineItems;

	public Order(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.lineItems = new ArrayList<LineItem>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", lineItems=" + lineItems + "]";
	}

	// Add line item to order
	public void addItem(int id, int quantity, Product p) {
		this.lineItems.add(new LineItem(id, quantity, p));
	}

	// Remove line item from order for given item id
	public void removeItemById(int itemId) {
		// TODO - Not implemented yet
	}
}