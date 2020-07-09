package com.practice.io.backup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamDemo {

	public static void main(String[] args) {
		FileInputStream fileInput = null;
		StringBuilder content = new StringBuilder();
		int ch;

		try {
			fileInput = new FileInputStream("C:\\Users\\xyz\\Desktop\\Local Deployment\\readNote1.txt");
			while ((ch = fileInput.read()) != -1) {
				content.append((char) ch);
			}
			System.out.println("Content:" + content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
