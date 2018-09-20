package comp3004assignment1;

public class PlayerHand extends Hand {

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(splitCard == null) sb.append("Player: ");
		else if (splitHand == 0) sb.append("Player Hand 1: ");
		else if (splitHand == 1) sb.append("Player Hand 2: ");
		else sb.append("Player's best hand: ");
		
		if(splitHand == 0 || (splitHand == 2 && (score() == score(false)))) {
			for(int i=0; i<card.size(); i++) {
				sb.append(card.get(i) + " ");
			}
			sb.append("(" + this.score(false) + ")");
		}
		else if(splitHand == 1 || (splitHand == 2 && (score() == score(true)))) {
			for(int i=0; i<splitCard.size(); i++) {
				sb.append(splitCard.get(i) + " ");
			}
			sb.append("(" + this.score(true) + ")");
		}
		return sb.toString();
	}

}
