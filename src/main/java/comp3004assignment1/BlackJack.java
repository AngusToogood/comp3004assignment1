package comp3004assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
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
			return null;
		}
		String fileLine;
		try {
			fileLine = scanner.nextLine();
		}catch(NoSuchElementException e) {
			System.out.print(file.getAbsolutePath());
			System.out.println(" : File blank");
			scanner.close();
			return null;
		}
		scanner.close();
		
		List<String> card = new ArrayList<String>(Arrays.asList(fileLine.split("\\s+")));
		Collections.reverse(card);
		
		//check input
		char suit, rank;
		if(card.size() == 0) {
			System.out.print(file.getAbsolutePath());
			System.out.println(" : File blank"); 
			return null;
		}
		for(int i=0; i<card.size()-1; i++) {
			for(int j=i+1; j<card.size(); j++) {
				if(card.get(i).equals(card.get(j))) {
					System.out.print(file.getAbsolutePath());
					System.out.println(" : Duplicate card(s)");
					return null;
				}
				suit = card.get(i).charAt(0);
				rank = card.get(i).charAt(1);
				if((suit != 'S')&&(suit != 'C')&&(suit != 'H')&&(suit != 'D')) {
					System.out.print(file.getAbsolutePath());
					System.out.println(" : Invalid Suit " + suit);
					return null;
				}
				if((rank < '1')&&(rank > '9')&&
						(rank != 'J')&&(rank != 'Q')&&(rank != 'K')&&(rank != 'A')) {
					System.out.print(file.getAbsolutePath());
					System.out.println(" : Invalid Rank " + rank);
					return null;
				}
			}
		}
		
		return new Deck(card);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
