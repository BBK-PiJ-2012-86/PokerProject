/**
 * 
 */
package poker;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author 86
 *
 */
public class Hand implements Iterable<Card>{
	List<Card> cards;
	
	public void sortByRankthenSuit() {
		sortBySuitOnly();
		sortByRankOnly();
	}
	
	public void sortBySuitthenRank() {
		sortByRankOnly();
		sortBySuitOnly();
	}
	
	
	private void sortByRankOnly() {
		Collections.sort(cards, new Comparator<Card>(){

			@Override
			public int compare(Card card1, Card card2) {
				return card1.getRank().compareTo(card2.getRank());
			}
			
		});
	}
	
	private void sortBySuitOnly() {
		Collections.sort(cards, new Comparator<Card>(){

			@Override
			public int compare(Card card1, Card card2) {
				return card1.getSuit().compareTo(card2.getSuit());
			}
			
		});
	}

	@Override
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
	
	
}
