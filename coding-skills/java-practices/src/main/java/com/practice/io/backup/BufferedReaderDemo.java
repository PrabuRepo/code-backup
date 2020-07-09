package com.practice.io.backup;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderDemo {

	public static void main(String[] args) {
		FileReader fileInput = null;
		BufferedReader bufferInput = null;
		String line = null;
		try {
			fileInput = new FileReader("D:/test/ReadNote1.txt");
			bufferInput = new BufferedReader(fileInput);
			//To set the data into string variable, which is read from file
			//It reads line by line
			while((line = bufferInput.readLine())!= null){
				System.out.println("The content of the fileread using bufferedIOStream->"+line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fileInput.close();
				bufferInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
