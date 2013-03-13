package poker.hand_card;

import java.util.List;

public interface Deck {

	/**
	 * @return
	 */
	public abstract List<Card> getDeck();

	/**
	 * 
	 */
	public abstract void shuffleDeck();

}