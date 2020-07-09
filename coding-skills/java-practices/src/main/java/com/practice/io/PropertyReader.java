package com.practice.io;

import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertyReader {

	public static void main(String[] args) {
		PropertyReader ob = new PropertyReader();
		Properties properties = ob.reader("/test.properties");

		System.out.println("Display properties: ");
		ob.display(properties);
	}

	public Properties reader(String fileName) {
		Properties properties = null;
		try {
			InputStream in = this.getClass().getResourceAsStream(fileName);
			properties = new Properties();
			properties.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	public void display(Properties properties) {
		for (Entry<Object, Object> entry : properties.entrySet()) {
			System.out.println("Key: " + entry.getKey() + "; Value: " + entry.getValue());
		}
	}
}
