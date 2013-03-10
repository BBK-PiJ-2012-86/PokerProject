package poker;

import java.util.Iterator;
import java.util.List;

public interface Hand extends Iterable<Card>{

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
	Hand sortByRank();

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