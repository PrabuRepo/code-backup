package com.practice.io;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Summary:
If we try to write to a file that doesn’t exist, the file will be created first and no exception will be thrown (except using Path method).
Always close the output stream after writing the file content to release all resources. It will also help in not corrupting the file.
Use PrintWriter is used to write formatted text.
Use FileOutputStream to write binary data.
Use DataOutputStream to write primitive data types.
Use FileChannel to write larger files.
 */
public class WriteToFile {

	// Write File using BufferedWritter
	/*BufferedWritter the simplest way to write the content to a file. It writes text to a character-output stream, buffering characters so as 
	 *to provide for the efficient writing of single characters, arrays, and strings. As it buffers before writing, so it result in less IO 
	 *operations, so it improve the performance.*/
	public static void usingBufferedWritter() throws IOException {
		String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";
		BufferedWriter writer = new BufferedWriter(new FileWriter("c:/temp/samplefile1.txt"));
		writer.write(fileContent);
		writer.close();
	}

	// Write File using FileWriter
	/*FileWriter the most clean way to write files. Syntax is self explanatory and easy to read and understand. FileWriter writes directly into 
	 *file (less performance) and should be used only when number of writes are less.*/
	public static void usingFileWriter() throws IOException {
		String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";

		FileWriter fileWriter = new FileWriter("c:/temp/samplefile2.txt");
		fileWriter.write(fileContent);
		fileWriter.close();
	}

	// Write File using PrintWriter
	/*Use PrintWriter to write formatted text to a file. This class implements all of the print methods found in PrintStream, so you can use 
	 * all formats which you use with System.out.println() statements.*/
	public static void usingPrintWriter() throws IOException {
		String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";

		FileWriter fileWriter = new FileWriter("c:/temp/samplefile3.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(fileContent);
		printWriter.printf("Blog name is %s", "howtodoinjava.com");
		printWriter.close();
	}

	// Write File using FileOutputStream
	/*Use FileOutputStream to write binary data to a file. FileOutputStream is meant for writing streams of raw bytes such as image data. 
	 * For writing streams of characters, consider using FileWriter.*/
	public static void usingFileOutputStream() throws IOException {
		String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";

		FileOutputStream outputStream = new FileOutputStream("c:/temp/samplefile4.txt");
		byte[] strToBytes = fileContent.getBytes();
		outputStream.write(strToBytes);

		outputStream.close();
	}

	// Write File using DataOutputStream
	/*DataOutputStream lets an application write primitive Java data types to an output stream in a portable way. An application can then
	 *  use a data input stream to read the data back in.*/
	public static void usingDataOutputStream() throws IOException {
		String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";

		FileOutputStream outputStream = new FileOutputStream("c:/temp/samplefile5.txt");
		DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(outputStream));
		dataOutStream.writeUTF(fileContent);

		dataOutStream.close();
	}

	// Write File using FileChannel
	/*FileChannel can be used for reading, writing, mapping, and manipulating a file. If you are dealing with large files, FileChannel 
	 * can be faster than standard IO. File channels are safe for use by multiple concurrent threads.*/
	public static void usingFileChannel() throws IOException {
		String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";

		RandomAccessFile stream = new RandomAccessFile("c:/temp/samplefile6.txt", "rw");
		FileChannel channel = stream.getChannel();
		byte[] strBytes = fileContent.getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
		buffer.put(strBytes);
		buffer.flip();
		channel.write(buffer);
		stream.close();
		channel.close();
	}

	// Write File using Java 7 Path
	/*Java 7 introduced Files utility class and we can write a file using it’s write function, internally it’s using OutputStream to 
	 * write byte array into file.*/
	public static void usingPath() throws IOException {
		String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";

		Path path = Paths.get("c:/temp/samplefile7.txt");

		Files.write(path, fileContent.getBytes());
	}
}
