package com.practice.ood.problems;

import java.util.ArrayList;

public class FileSystem {

	public static void main(String[] args) {
		Directory root = new Directory("Food", null);
		File taco = new File("Taco", root, 4);
		File hamburger = new File("Hamburger", root, 9);
		root.addEntry(taco);
		root.addEntry(hamburger);

		Directory healthy = new Directory("Healthy", root);

		Directory fruits = new Directory("Fruits", healthy);
		File apple = new File("Apple", fruits, 5);
		File banana = new File("Banana", fruits, 6);
		fruits.addEntry(apple);
		fruits.addEntry(banana);

		healthy.addEntry(fruits);

		Directory veggies = new Directory("Veggies", healthy);
		File carrot = new File("Carrot", veggies, 6);
		File lettuce = new File("Lettuce", veggies, 7);
		File peas = new File("Peas", veggies, 4);
		veggies.addEntry(carrot);
		veggies.addEntry(lettuce);
		veggies.addEntry(peas);

		healthy.addEntry(veggies);

		root.addEntry(healthy);

		System.out.println(root.numberOfFiles());
		System.out.println(veggies.getFullPath());
	}
}

class Directory extends Entry {
	protected ArrayList<Entry> contents;

	public Directory(String n, Directory p) {
		super(n, p);
		contents = new ArrayList<Entry>();
	}

	protected ArrayList<Entry> getContents() {
		return contents;
	}

	public int size() {
		int size = 0;
		for (Entry e : contents) {
			size += e.size();
		}
		return size;
	}

	public int numberOfFiles() {
		int count = 0;
		for (Entry e : contents) {
			if (e instanceof Directory) {
				count++; // Directory counts as a file
				Directory d = (Directory) e;
				count += d.numberOfFiles();
			} else if (e instanceof File) {
				count++;
			}
		}
		return count;
	}

	public boolean deleteEntry(Entry entry) {
		return contents.remove(entry);
	}

	public void addEntry(Entry entry) {
		contents.add(entry);
	}
}

abstract class Entry {
	protected Directory parent;
	protected long created;
	protected long lastUpdated;
	protected long lastAccessed;
	protected String name;

	public Entry(String n, Directory p) {
		name = n;
		parent = p;
		created = System.currentTimeMillis();
	}

	public boolean delete() {
		if (parent == null) {
			return false;
		}
		return parent.deleteEntry(this);
	}

	public abstract int size();

	public String getFullPath() {
		if (parent == null) {
			return name;
		} else {
			return parent.getFullPath() + "/" + name;
		}
	}

	public long getCreationTime() {
		return created;
	}

	public long getLastUpdatedTime() {
		return lastUpdated;
	}

	public long getLastAccessedTime() {
		return lastAccessed;
	}

	public void changeName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}
}

class File extends Entry {
	private String content;
	private int size;

	public File(String n, Directory p, int sz) {
		super(n, p);
		size = sz;
	}

	public int size() {
		return size;
	}

	public String getContents() {
		return content;
	}

	public void setContents(String c) {
		content = c;
	}
}
