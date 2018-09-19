package comp3004assignment1;

public class DealerHand extends Hand {
	
	public boolean turn;
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Dealer: ");
		for(int i=0; i<card.size(); i++) {
			if((turn == true) || (i == 0))
				sb.append(card.get(i) + " ");
			else
				sb.append("** ");
		}
		if(turn == true)
			sb.append("(" + this.score() + ")");
		
		return sb.toString();
	}
	
	public boolean isSoft17() {
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
		if(aces == 0) return false;
		if(s + aces == 7) return true;
		return false;
	}

}
