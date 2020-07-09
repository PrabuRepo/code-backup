package com.practice.io.backup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {

	public static void main(String[] args) {
		FileReader fileInput = null;
		StringBuilder content = new StringBuilder();
		int ch;
		
		try {
			fileInput = new FileReader("D:/test/Read.xlsx");
			while((ch = fileInput.read())!= -1){
				content.append((char)ch);
			}
			System.out.println("Content:"+content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
