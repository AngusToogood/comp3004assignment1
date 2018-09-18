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
		card.removeIf(c -> (c.length() < 2)); // remove commands
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
		
		return new Deck(card);
	}
	
	public static List<Character> fileToCommand(String filename){
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
		List<Character> command = new ArrayList<Character>();
		for(int i=4; i<card.size(); i+=2) {
			if(card.get(i).length() > 1) break; //reached dealer's turn
			if(card.get(i).charAt(0) == 'H') command.add('H');
			else if(card.get(i).charAt(0) == 'S') command.add('S');
			else return null; //invalid command
		}
		return command;
	}
	
	public static char playerCommandInput() {
		char c = 0;
		Scanner keyboard = new Scanner(System.in);
		while((c != 'H' && c != 'S')) {		
			System.out.print("Hit (H) or Stand (S): ");
			c = keyboard.next().charAt(0);
		}
		keyboard.close();
		return c;
	}
	//returns true for player win, false for dealer win
	public static boolean play(Deck deck, List<Character> command) {
		boolean consolePlay = false;
		if(command == null) consolePlay = true;
		
		PlayerHand player = new PlayerHand();
		DealerHand dealer = new DealerHand();
		player.add(deck.draw());
		player.add(deck.draw());
		dealer.add(deck.draw());
		dealer.add(deck.draw());
		dealer.turn = false;
		
		System.out.println(player);
		//check for blackjack
		if(dealer.score() == 21) {
			dealer.turn = true;
			System.out.println(dealer);
			System.out.println("Blackjack! Dealer wins.");
			return false;
		}
		if(player.score() == 21) {
			dealer.turn = true;
			System.out.println(dealer);
			System.out.println("Blackjack! Player wins.");
			return true;
		}
		System.out.println(dealer);
		
		char pc;
		//player turn
		System.out.println();
		System.out.println("Player's Turn");
		while(!dealer.turn) {
			if(consolePlay) pc = playerCommandInput();
			else pc = command.remove(0);
			switch(pc) {
			case 'H': 
				player.add(deck.draw()); 
				System.out.println("Player Hits");
				System.out.println(player); 
				break;
			default: 
				dealer.turn = true; 
				System.out.println("Player stands.");
				break;
			}
			if(player.score() == 21) {
				System.out.println("Blackjack! Player wins.");
				return true;
			}
			if(player.score() > 21) {
				System.out.println("Player goes bust. Dealer wins.");
				return false;
			}
		}
		//dealer turn
		System.out.println();
		System.out.println("Dealer's Turn");
		System.out.println(dealer);
		while(dealer.score() < 17) {
			dealer.add(deck.draw());
			System.out.println("Dealer hits.");
			System.out.println(dealer);
		}
		if(dealer.score() == 21) {
			System.out.println("Blackjack! Dealer wins.");
			return false;
		}
		if(dealer.score() > 21) {
			System.out.println("Dealer goes bust. Player wins.");
			return true;
		}
		System.out.println("Dealer stands.");
		if(dealer.score() < player.score()) {
			System.out.println("Player Wins.");
			return true;
		}
		System.out.println("Dealer Wins.");
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
