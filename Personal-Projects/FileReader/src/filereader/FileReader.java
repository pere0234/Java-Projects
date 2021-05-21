/* A simple file-reader program for reading .txt files
 * By Daniel Pereira
 * */

package filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileReader {

	public static void main(String[] args) {

		//Loads the text file
		File textFile = new File("archive.txt");
		//creates a new scanner to read the text file
		Scanner textReader;

		try {
			//Loads the text file onto the Scanner
			textReader = new Scanner(textFile);
			//For every line in the text
			while (textReader.hasNextLine()) {
				//Ensure each line is read
				String line = textReader.nextLine();
				//Read the line
				Scanner readLine = new Scanner(line);
				//Print the line to the screen
				System.out.println(readLine.next());
				
				readLine.close();

			}

			textReader.close();
			//If the file is missing, throw exception
		} catch (FileNotFoundException e) {
			System.out.println("The file cannot be found");
			e.printStackTrace();

		}

	}

}