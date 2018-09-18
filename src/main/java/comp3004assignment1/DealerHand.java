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

}
