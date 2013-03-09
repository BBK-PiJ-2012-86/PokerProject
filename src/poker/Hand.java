/**
 * 
 */
package poker;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author 86
 *
 */
public class Hand {
	List<Card> cards;
	
	public void sortByRank() {
		Collections.sort(cards, new Comparator<Card>(){

			@Override
			public int compare(Card card1, Card card2) {
				return card1.getRank().compareTo(card2.getRank());
			}
			
		});
	}
	
	public void sortBySuit() {
		Collections.sort(cards, new Comparator<Card>(){

			@Override
			public int compare(Card card1, Card card2) {
				return card1.getSuit().compareTo(card2.getSuit());
			}
			
		});
	}
}
