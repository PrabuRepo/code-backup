package com.practice.io.backup;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedInputStreamDemo {

	public static void main(String[] args) {
		FileInputStream fileInput = null;
		BufferedInputStream bufferInput = null;
		String content = null;
		int ch = 0;
		byte[] byteContent = new byte[24];
		try {
			fileInput = new FileInputStream("D:/test/ReadNote1.txt");
			bufferInput = new BufferedInputStream(fileInput);
			//To display the data which is read from file
			/*while(bufferInput.available()>0){
				System.out.print((char)bufferInput.read());
			}*/
			
			//To set the data into string variable, which is read from file
			while((ch = bufferInput.read(byteContent))!= -1){
				content = new String(byteContent, 0, ch);
				System.out.println("The content of the fileread using bufferedIOStream->"+content);
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
