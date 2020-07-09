package com.practice.io.backup;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterDemo {

	public static void main(String[] args) {
		FileWriter fileOutput = null;
		BufferedWriter bufferOutput = null;
		String content = new String( "FileWriter(16bits) is used to write the data into external file.\r\n" +
				"FileReader(16bits) is used to read the data from file");
		try {
			fileOutput = new FileWriter("D:/test/WriteNote1.txt");
			bufferOutput = new BufferedWriter(fileOutput);
			bufferOutput.write(content);
			System.out.println("Content:"+content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bufferOutput.flush();
				fileOutput.close();
				bufferOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
