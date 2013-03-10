package poker;

import java.util.List;

public interface Hand extends Iterable<Card>{

	List<Card> getCards();

	void addCards(List<Card> cards);
	

	/**
	 * @return
	 */
	Hand sortByRank();

}