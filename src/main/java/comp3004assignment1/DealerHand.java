package comp3004assignment1;

public class DealerHand extends Hand {
	
	public boolean turn;
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(splitCard == null) {
			sb.append("Dealer: ");
			for(int i=0; i<card.size(); i++) {
				if((turn == true) || (i == 0))
					sb.append(card.get(i) + " ");
				else
					sb.append("** ");
			}
			if(turn == true)
				sb.append("(" + this.score(false) + ")");
		}else {
			if (splitHand == 0) sb.append("Dealer Hand 1: ");
			else if (splitHand == 1) sb.append("Dealer Hand 2: ");
			else sb.append("Dealer's best hand: ");
			if((splitHand == 0) || ((splitHand == 2) && score() == score(false))) {
				for(int i=0; i<card.size(); i++) {
					sb.append(card.get(i) + " ");
				}
				sb.append("(" + this.score(false) + ")");
			}else if((splitHand == 1) || (splitHand == 2) && score() == score(true)) {
				for(int i=0; i<splitCard.size(); i++) {
					sb.append(splitCard.get(i) + " ");
				}
				sb.append("(" + this.score(true) + ")");
			}
		}
		
		return sb.toString();
	}
	
	public boolean isSoft17(boolean split) {
		int s = 0;
		int aces = 0;
		char c;
		if(splitCard == null) split = false;
		for(int i = 0; i < (split?splitCard:card).size(); i++) {
			c = (split?splitCard:card).get(i).charAt(1);
			if((c >= '2') && (c <= '9')) {
				s += Character.getNumericValue(c);
			}else if((c == '1') || (c == 'J') || (c == 'Q') || (c == 'K')) {
				s += 10;
			}
			else if(c == 'A') {
				aces++;
			}
		}
		if(aces == 0) return false;
		if(s + aces == 7) return true;
		return false;
	}
	public boolean isSoft17() {
		return isSoft17(false);
	}
	public boolean canSplit() {
		return ((card.get(0).charAt(1) == 'A') || ((card.get(0).charAt(1) <= '8') 
				&& (card.get(0).charAt(1) >= '2'))) && super.canSplit();
	}
}
