package com.practice.io.backup;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferOutputStreamDemo {

	public static void main(String[] args) {
		FileOutputStream fileOutput = null;
		BufferedOutputStream bufferOutput = null;
		String content = new String("FileOutputStream is used to write the data into external file.\r\n" +
				"FileInputStream is used to read the data from file");
		try {
			fileOutput = new FileOutputStream("D:/test/WriteNote1.txt");
//			bufferOutput = new BufferedOutputStream(fileOutput);
			bufferOutput = new BufferedOutputStream(fileOutput, 25);
			bufferOutput.write(content.getBytes());
			System.out.println("Content:"+content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				//bufferOutput.flush();
				fileOutput.close();
				bufferOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
