package com.practice.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Java FileReader class is used to read data from the file. It returns data in byte format like FileInputStream class. It is 
 * character-oriented class which is used for file handling in java.
 * 
 * Java FileWriter class is used to write character-oriented data to a file. It is character-oriented class which is used for 
 * file handling in java. Unlike FileOutputStream class, you don't need to convert string into byte array because it provides 
 * method to write string directly.
 */
public class CharStream implements FileOperations {
	final String RESOURCE_PATH = "src/main/resources/";

	public static void main(String[] args) {
		CharStream ob = new CharStream();
		String data = ob.readDataFromFile("Input.txt");
		System.out.println("Read data from file: " + data);

		System.out.println("\nWrite data int output file: ");
		ob.writeDataIntoFile(data);
	}

	@Override
	public String readDataFromFile(String fileName) {
		StringBuilder dataFromFile = new StringBuilder();
		int ch;
		StringBuilder temp = new StringBuilder();
		try {
			File file = new File(RESOURCE_PATH + "Input.txt");
			FileReader in = new FileReader(file);
			while ((ch = in.read()) != -1) {
				temp.append(ch);
				dataFromFile.append((char) ch);
			}
			System.out.println("Direct Char Stream Value: " + temp.toString());
			in.close();
		} catch (IOException exp) {
			exp.printStackTrace();
		}

		return dataFromFile.toString();
	}

	@Override
	public void writeDataIntoFile(String data) {
		data = "test";
		try {
			File file = new File(RESOURCE_PATH + "Output.txt");
			FileWriter out = new FileWriter(file);
			out.write(data);
			System.out.println("Successfully written data into file!");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
