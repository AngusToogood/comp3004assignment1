package comp3004assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

abstract public class Hand {
	protected List<String> card;
	protected List<String> splitCard;
	public int splitHand; //0: on card/first hand, 1: on splitCard/second hand, 2: done. Used for toString.
	
	public Hand() {
		card = new ArrayList<String>();
		splitCard = null;
		splitHand = 0;
	}
	abstract public String toString();
	public void add(String c) {
		if((splitCard == null) || (splitHand == 0))
			card.add(c);
		else
			splitCard.add(c);
	}
	public int size() {
		return card.size() + ((splitCard == null)?0:splitCard.size());
	}
	public int score(boolean split) {
		if(splitCard == null) split = false;
		int s = 0;
		int aces = 0;
		char c;
		for(int i = 0; i < (!split?card.size():splitCard.size()); i++) {
			c = (!split?card.get(i).charAt(1):splitCard.get(i).charAt(1));
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
			if(s <= 10) {
				s += 11;
				s += aces - 1;
			}else {
				s += aces;
			}
		}
		return s;
	}
	public int score() {return score(false);} //old interface for non-split
	public void clear() {
		card.clear();
	}
	public boolean canSplit() {
		return card.get(0).charAt(1) == card.get(1).charAt(1);
	}
	public void split() {
		splitCard = new ArrayList<String>();
		splitCard.add(card.remove(1));
		splitHand = 0;
	}
}

