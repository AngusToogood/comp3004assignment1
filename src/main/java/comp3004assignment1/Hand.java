package comp3004assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

abstract public class Hand {
	protected List<String> card;
	
	public Hand() {
		card = new ArrayList<String>();
	}
	abstract public String toString();
	public void add(String c) {
		card.add(c);
	}
	public int size() {
		return card.size();
	}
	public int score() {
		int s = 0;
		int aces = 0;
		char c;
		for(int i = 0; i < card.size(); i++) {
			c = card.get(i).charAt(1);
			if((c >= '2') && (c <= '9')) {
				s += Character.getNumericValue(c);
			}else if((c == '1') || (c == 'J') || (c == 'Q') || (c == 'K')) {
				s += 10;
			}
			else if(c == 'A') {
				aces++;
			}
		}
		if(aces > 0) {
		//TODO: handle aces
		}
		return s;
	}
}

