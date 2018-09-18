package comp3004assignment1;

public class PlayerHand extends Hand {

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Player: ");
		for(int i=0; i<card.size(); i++) {
			sb.append(card.get(i) + " ");
		}
		sb.append("(" + this.score() + ")");
		return sb.toString();
	}

}
