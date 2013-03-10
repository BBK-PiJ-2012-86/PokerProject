package poker;

import java.util.Iterator;
import java.util.List;

public interface Hand {

	List<Card> getCards();

	void addCards(List<Card> cards);

	/*public void sortByRankThenSuit() {
		sortBySuitOnly();
		sortByRankOnly();
	}
	
	public void sortBySuitThenRank() {
		sortByRankOnly();
		sortBySuitOnly();
	}
	
	 */
	void sortByRank();

	/*
	
	private void sortBySuitOnly() {
		Collections.sort(cards, new Comparator<Card>(){
	
			@Override
			public int compare(Card card1, Card card2) {
				return card1.getSuit().compareTo(card2.getSuit());
			}
			
		});
	}*/

	Iterator<Card> iterator();

}