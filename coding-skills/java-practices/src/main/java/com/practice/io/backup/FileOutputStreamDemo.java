package com.practice.io.backup;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {

	public static void main(String[] args) {
		FileOutputStream fileOutput = null;
		String content = "FileOutputStream(8 bits) is used to write the data into external file.\r\n"
				+ "FileInputStream(8 bits) is used to read the data from file";
		// byte[] bytes = new byte[1000];

		try {
			fileOutput = new FileOutputStream("C:\\Users\\xyz\\Desktop\\Local Deployment\\readNote1.txt");
			// bytes = content.getBytes();
			fileOutput.write(content.getBytes());
			System.out.println("Content:" + content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
