package com.practice.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
 * Java FileInputStream class obtains input bytes from a file. It is used for reading byte-oriented data (streams of raw bytes)
 * such as image data, audio, video etc.
 * 
 * Java FileOutputStream is an output stream used for writing data to a file. If you have to write primitive values into a file, 
 * use FileOutputStream class. You can write byte-oriented as well as character-oriented data through FileOutputStream class.
 */
public class ByteStream implements FileOperations {
	final String RESOURCE_PATH = "src/main/resources/";

	public static void main(String[] args) {
		ByteStream ob = new ByteStream();

		String data = ob.readDataFromFile("Input.txt");
		System.out.println("Read data from Input file: " + data);

		System.out.println("Write data int output file: ");
		ob.writeDataIntoFile(data);
	}

	@Override
	public String readDataFromFile(String fileName) {
		StringBuilder dataFromFile = new StringBuilder();
		int ch;
		StringBuilder temp = new StringBuilder();
		try {
			FileInputStream in = new FileInputStream(RESOURCE_PATH + fileName);
			// InputStream in = this.getClass().getResourceAsStream("/"+fileName);
			while ((ch = in.read()) != -1) {
				temp.append(ch);
				dataFromFile.append((char) ch);
			}
			in.close();
		} catch (IOException exp) {
			exp.printStackTrace();
		}
		System.out.println("Direct Byte Stream Value: " + temp.toString());
		return dataFromFile.toString();
	}

	@Override
	public void writeDataIntoFile(String data) {
		try {
			File file = new File(RESOURCE_PATH + "Output.txt");
			OutputStream out = new FileOutputStream(file);
			/* Another way to write the file in resources path:
			 Path newFilePath = Paths.get("src/main/resources/Output1.txt");
			OutputStream out = new FileOutputStream(newFilePath.toFile());*/
			out.write(data.getBytes());
			System.out.println("Successfully written data into file!");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
