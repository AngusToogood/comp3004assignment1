package comp3004assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackJack {

	public static Deck fileToDeck(String filename) {
		File file = null;
		Scanner scanner;
		try {
			file = new File(filename);
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.print(file.getAbsolutePath());
			System.out.println(" : File not found");
			return new Deck();
		}
		String fileLine = scanner.nextLine();
		scanner.close();
		
		List<String> card = new ArrayList<String>(Arrays.asList(fileLine.split("\\s+")));
		Collections.reverse(card);
		return new Deck(card);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
