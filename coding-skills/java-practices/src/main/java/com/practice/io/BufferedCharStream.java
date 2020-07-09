package com.practice.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/*
 * Java BufferedReader class is used to read the text from a character-based input stream. It can be used to read data line
 *  by line by readLine() method. It makes the performance fast. It inherits Reader class.
 *  
 * Java BufferedWriter class is used to provide buffering for Writer instances. It makes the performance fast. It inherits
 * Writer class. The buffering characters are used for providing the efficient writing of single arrays, characters, and strings.
 */
public class BufferedCharStream implements FileOperations {
	final String RESOURCE_PATH = "src/main/resources/";

	public static void main(String[] args) {
		BufferedCharStream ob = new BufferedCharStream();
		String data = ob.readDataFromFile("Input.txt");
		System.out.println("Read data from file: " + data);

		System.out.println("\nWrite data int output file: ");
		ob.writeDataIntoFile(data);

		System.out.println("\nRead Line by Line from file(Using BufferedReader): ");
		ob.readDataLineByLine("Input.txt");

		System.out.println("\nRead Line by Line from file(Using Scanner): ");
		ob.readFileLineByLineUsingScanner("Input.txt");

		System.out.println("\nRead Line by Line from file(Using NIO Files): ");
		ob.readFileLineByLineUsingFiles("Input.txt");

		System.out.println("\nRead Line by Line from file(Using NIO Files & Java 8 Stream): ");
		ob.readFileLineByLineUsingFiles("Input.txt");
	}

	@Override
	public String readDataFromFile(String fileName) {
		StringBuilder dataFromFile = new StringBuilder();
		int ch;
		StringBuilder temp = new StringBuilder();
		try {
			File file = new File(RESOURCE_PATH + "Input.txt");
			FileReader in = new FileReader(file);
			BufferedReader bin = new BufferedReader(in);
			while ((ch = bin.read()) != -1) {
				temp.append(ch);
				dataFromFile.append((char) ch);
			}
			System.out.println("Direct Char Stream Value: " + temp.toString());
			in.close();
		} catch (IOException exp) {
			exp.printStackTrace();
		}

		return dataFromFile.toString();
	}

	@Override
	public void writeDataIntoFile(String data) {
		try {
			File file = new File(RESOURCE_PATH + "Output.txt");
			FileWriter out = new FileWriter(file);
			BufferedWriter bout = new BufferedWriter(out);
			bout.write(data);
			System.out.println("Successfully written data into file!");
			bout.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readDataLineByLine(String fileName) {
		StringBuilder dataFromFile = new StringBuilder();
		File file = new File(RESOURCE_PATH + "Input.txt");

		try (FileReader in = new FileReader(file); BufferedReader bin = new BufferedReader(in);) {
			String line;
			while ((line = bin.readLine()) != null) {
				System.out.println("Line: " + line);
				dataFromFile.append(line);
			}
			/*String line = bin.readLine();
			while (line != null) {
				System.out.println("Line: " + line);
				dataFromFile.append(line);
				line = bin.readLine();
			}*/
		} catch (IOException exp) {
			exp.printStackTrace();
		}

		return dataFromFile.toString();
	}

	public void readFileLineByLineUsingScanner(String fileName) {
		try {
			File file = new File(RESOURCE_PATH + "Input.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
			scanner.close();
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}

	public void readFileLineByLineUsingFiles(String fileName) {
		try {
			Path path = Paths.get(RESOURCE_PATH + "Input.txt");
			List<String> allLines = Files.readAllLines(path);
			for (String line : allLines) {
				System.out.println(line);
			}
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}

	public void readFileLineByLineUsingStream(String fileName) {
		Path path = Paths.get(RESOURCE_PATH + "Input.txt");
		try (Stream<String> allLines = (Stream<String>) Files.lines(path)) {
			allLines.forEach(k -> System.out.print(k));
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}
}
