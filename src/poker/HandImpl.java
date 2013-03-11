/**
 * 
 */
package poker;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 86
 *
 */
public class HandImpl implements Hand{
	private List<Card> cards;
	
	public HandImpl(List<Card> cards) {
		this.cards = cards;
	}
	
	public HandImpl() {	//poss delete this one
		this(new LinkedList<Card>());
	}
	
	@Override
	public List<Card> getCards() {
		return cards;
	}

	@Override
	public void addCards (List<Card> cardsToAdd) {
		cards.addAll(cardsToAdd);
	}
	
	@Override
	public Hand sortByRank() {
		Collections.sort(cards, new Comparator<Card>(){

			@Override
			public int compare(Card card1, Card card2) {
				return card1.getRank().compareTo(card2.getRank());
			}
			
		});
		return this;
	}

	@Override
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
	
	@Override
	public String toString() {
		String result = "";
		if (cards!=null) {
			for (Card card: cards) {
				result+=card;
			}
		}
		return result;
	}
	
	
}
