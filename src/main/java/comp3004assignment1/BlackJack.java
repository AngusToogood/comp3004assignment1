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
		
		for(int i=4; i<card.size(); i++) {
			if(card.get(i).length() > 1) continue;
			if(card.get(i).charAt(0) == 'H') command.add('H'); //hit
			else if(card.get(i).charAt(0) == 'S') command.add('S'); //stand
			else if((i == 4) && (card.get(i).charAt(0) == 'D') && //split
					(card.get(0).charAt(1) == card.get(1).charAt(1))) {
				command.add('D');
			}
			else return null; //invalid command
		}
		return command;
	}
	
	public static char playerCommandInput() {
		char c = 0;
		Scanner keyboard = new Scanner(System.in);
		while((c != 'H') && (c != 'S')) {		
			System.out.print("Hit (H) or Stand (S): ");
			c = keyboard.next().charAt(0);
		}
		keyboard.close();
		return c;
	}
	public static char playerSplitInput() {
		char c = 0;
		Scanner keyboard = new Scanner(System.in);
			
		System.out.print("Enter D to split, anything else to not: ");
		c = keyboard.next().charAt(0);
		
		keyboard.close();
		return c;
	}
	//returns true for player win, false for dealer win
	public static boolean play(Deck deck, List<Character> command) {
		System.out.println("------------------------");
		boolean consolePlay = false;
		boolean playerSplitting = false;
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
		System.out.println(player);
		if(player.canSplit()) {
			if(consolePlay && (playerSplitInput() == 'D')) {
				player.split();
				playerSplitting = true;
			}
			else if (!consolePlay && (command.get(0) == 'D')) {
				player.split();
				command.remove(0);
				playerSplitting = true;
			}
		}
		if(playerSplitting) {
			System.out.println("Player Splits");
			player.add(deck.draw());
		}
		while(!dealer.turn) {
			System.out.println(player);
			if(consolePlay) pc = playerCommandInput();
			else pc = command.remove(0);
			switch(pc) {
			case 'H': 
				player.add(deck.draw()); 
				System.out.println("Player Hits");
				break;
			default: 
				System.out.println("Player stands.");
				if(!playerSplitting)
					dealer.turn = true; 
				else if(player.splitHand == 1) {
					dealer.turn = true; 
					player.splitHand = 2;
				}
				else {
					player.splitHand = 1;
					player.add(deck.draw());
				}
				break;
			}
			if(dealer.turn) break;
			if(player.score(player.splitHand == 1) == 21) { 
				System.out.println("Player stands.");
				if(!playerSplitting) {
					dealer.turn = true; 
					break;
				}else if(player.splitHand == 1) {
					dealer.turn = true; 
					player.splitHand = 2;
					break;
				}
				else {
					player.splitHand = 1;
					player.add(deck.draw());
					System.out.println(player);
				}
			}
			if(player.score(player.splitHand == 1) > 21) {
				if(playerSplitting) {
					if(player.splitHand == 0) {
						System.out.println(player);
						System.out.println("Player's first hand goes bust");
						player.splitHand = 1;
						player.add(deck.draw());
					}
					else if (player.score() > 21){
						System.out.println(player);
						System.out.println("Player's second hand also goes bust. Dealer wins.");
						return false;
					}
					else {
						System.out.println(player);
						System.out.println("Player's second hand goes bust.");
						dealer.turn = true;
					}
				} else {
				System.out.println("Player goes bust. Dealer wins.");
				return false;
				}
			}
		}
		if(playerSplitting) player.splitHand = 2;
		//dealer turn
		System.out.println();
		System.out.println("Dealer's Turn");
		System.out.println(dealer);
		boolean dealerSplitting = dealer.canSplit();
		if(dealerSplitting) {
			dealer.split();
			System.out.println("Dealer splits.");
		}
		do {
			if(dealerSplitting) {
				dealer.add(deck.draw());
				System.out.println(dealer);
			}
			while((dealer.score(dealer.splitHand == 1) < 17) || dealer.isSoft17(dealer.splitHand == 1)) {
				dealer.add(deck.draw());
				System.out.println("Dealer hits.");
				System.out.println(dealer);
			}
			if(dealer.score(dealer.splitHand == 1) > 21) {
				if(dealerSplitting) {
					if(dealer.splitHand == 0) {
						System.out.println("Dealer's first hand goes bust.");
					}else if(dealer.score() > 21) {
						System.out.println("Dealer's second hand also goes bust. Player wins");
						return true;
					}else {
						System.out.println("Dealer's second hand goes bust");
					}
				}
				else {
					System.out.println("Dealer goes bust. Player wins.");
					return true;
				}
			} else
				System.out.println("Dealer stands.");
			if(dealerSplitting) dealer.splitHand++;
		} while(dealerSplitting && (dealer.splitHand < 2));
		
		System.out.println();
		System.out.println(player);
		System.out.println(dealer);
		if(dealer.score() < player.score()) {
			System.out.println("Player Wins.");
			return true;
		}
		System.out.println("Dealer Wins.");
		return false;
	}
	
	public static void main(String[] args) {
		char c = 0;
		Deck deck = null;
		Scanner keyboard = new Scanner(System.in);
		while((c != 'c' && c != 'f')) {	
			System.out.println("Console (c) or File (f) input : ");
			c = keyboard.next().charAt(0);
		}
		if (c == 'c') {
			deck = new Deck();
			deck.shuffle();
			keyboard.reset();
			play(deck, null);
		}
		else {
			String filename;
			List<Character> command = null;
			while((deck == null) || (command == null)) {
				System.out.println("Enter file path : ");
				filename = keyboard.next();
				deck = fileToDeck(filename);
				command = fileToCommand(filename);
			}
			keyboard.close();
			System.out.println();
			play(deck, command);
		}
	}

}
