package com.practice.io.backup;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {

	public static void main(String[] args) {
		FileWriter fileOutput = null;
		String content = "FileWriter(16bits) is used to write the data into external file.\r\n" +
				"FileReader(16bits) is used to read the data from file";
		try {
			fileOutput = new FileWriter("D:/test/WriteNote1.txt");
			fileOutput.write(content);
			System.out.println("Content:"+content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fileOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
