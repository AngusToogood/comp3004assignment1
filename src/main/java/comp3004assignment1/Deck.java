package comp3004assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
	/*
	 * A Deck of 52 Cards, represented by a list of strings.
	 * The top of the deck is the back of the list
	 */
	 
	private List<String> card; 
	private Random rng;
	
	public Deck() {
		rng = new Random();
		card = new ArrayList<String>();
		card.add("HK");
		card.add("HQ");
		card.add("HJ");
		card.add("H10");
		card.add("H9");
		card.add("H8");
		card.add("H7");
		card.add("H6");
		card.add("H5");
		card.add("H4");
		card.add("H3");
		card.add("H2");
		card.add("HA");
		card.add("DK");
		card.add("DQ");
		card.add("DJ");
		card.add("D10");
		card.add("D9");
		card.add("D8");
		card.add("D7");
		card.add("D6");
		card.add("D5");
		card.add("D4");
		card.add("D3");
		card.add("D2");
		card.add("DA");
		card.add("CK");
		card.add("CQ");
		card.add("CJ");
		card.add("C10");
		card.add("C9");
		card.add("C8");
		card.add("C7");
		card.add("C6");
		card.add("C5");
		card.add("C4");
		card.add("C3");
		card.add("C2");
		card.add("CA");
		card.add("SK");
		card.add("SQ");
		card.add("SJ");
		card.add("S10");
		card.add("S9");
		card.add("S8");
		card.add("S7");
		card.add("S6");
		card.add("S5");
		card.add("S4");
		card.add("S3");
		card.add("S2");
		card.add("SA");
	}
	
	public String draw() {
		return card.remove(card.size()-1);
	}
	public int size() {
		return card.size();
	}
	public void shuffle() {
		Collections.shuffle(card, rng);
	}
	public boolean equals(Deck other) {
		return card.equals(other.card);
	}
}
