package poker;

import java.util.List;

public interface Hand extends Iterable<Card>{

	/**
	 * @return
	 */
	List<Card> getCards();

	/**
	 * @param cards
	 */
	void addCards(List<Card> cards);
	
	/**
	 * @return
	 */
	Hand sortByRank();

	/**
	 * @param i
	 * @return
	 */
	Card getCardAt(int i);

}