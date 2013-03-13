package poker.hand_card;


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

	/**
	 * @param cardsToRemove
	 */
	void removeCards(List<Card> cardsToRemove);
	
	/*
	 * Removes all cards ready for the next deal
	 */
	
	void clearHand();

	Hand moveCardsToStartOthersRankOrder(List<Card> cardsToMove);


}