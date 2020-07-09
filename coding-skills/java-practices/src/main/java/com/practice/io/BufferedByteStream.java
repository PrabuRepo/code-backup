package com.practice.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
 * Java BufferedInputStream class is used to read information from stream. It internally uses buffer mechanism to make the 
 * performance fast.
 * 
 * Java BufferedOutputStream class is used for buffering an output stream. It internally uses buffer to store data. It adds 
 * more efficiency than to write data directly into a stream. So, it makes the performance fast.
 */
public class BufferedByteStream implements FileOperations {
	public static void main(String[] args) {
		BufferedByteStream ob = new BufferedByteStream();
		String data = ob.readDataFromFile("Input.txt");
		System.out.println("Read data from file: " + data);

		System.out.println("\nWrite data int output file: ");
		ob.writeDataIntoFile(data);
	}

	@Override
	public String readDataFromFile(String fileName) {
		StringBuilder data = new StringBuilder();
		try {
			File file = new File(FileOperations.RESOURCE_PATH + fileName);
			FileInputStream in = new FileInputStream(file);
			BufferedInputStream bin = new BufferedInputStream(in);
			int ch;
			while ((ch = bin.read()) != -1) {
				data.append((char) ch);
			}
			bin.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data.toString();
	}

	@Override
	public void writeDataIntoFile(String data) {
		try {
			File file = new File(RESOURCE_PATH + "Output.txt");
			OutputStream out = new FileOutputStream(file);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			bout.write(data.getBytes());
			System.out.println("Successfully written data into file!");
			bout.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
