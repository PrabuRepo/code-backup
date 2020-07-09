package com.practice.ood.pattern.creational;

import java.util.HashMap;
import java.util.Map;

public class ProtoTypePattern {
	public static void main(String[] args) {
		ColorStore.getColor("blue").addColor();
		ColorStore.getColor("black").addColor();
		ColorStore.getColor("black").addColor();
		ColorStore.getColor("blue").addColor();
	}
}

abstract class Color implements Cloneable {

	protected String colorName;

	abstract void addColor();

	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
}

class blueColor extends Color {
	public blueColor() {
		this.colorName = "blue";
	}

	@Override
	void addColor() {
		System.out.println("Blue color added");
	}

}

class blackColor extends Color {

	public blackColor() {
		this.colorName = "black";
	}

	@Override
	void addColor() {
		System.out.println("Black color added");
	}
}

class ColorStore {

	private static Map<String, Color> colorMap = new HashMap<String, Color>();

	static {
		colorMap.put("blue", new blueColor());
		colorMap.put("black", new blackColor());
	}

	public static Color getColor(String colorName) {
		return (Color) colorMap.get(colorName).clone();
	}
}
