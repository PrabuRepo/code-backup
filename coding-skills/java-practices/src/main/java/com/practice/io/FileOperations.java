package com.practice.io;

public interface FileOperations {

	final String RESOURCE_PATH = "src/main/resources/";

	public String readDataFromFile(String fileName);

	public void writeDataIntoFile(String data);
}
