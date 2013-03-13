/**
 * 
 */
package poker.hand_card;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

/**
 * 
 *
 */
public class HandImpl implements Hand{
	@Getter private final List<Card> cards = new LinkedList<Card>();
	
	public HandImpl(List<Card> cards) {			//override Lombok atm..  ?
		this.cards.addAll(cards);				// make so cards is only updated through Hand?? done?
	}
	
	public HandImpl() {	//poss delete this one   ? Need to look at my Mocks
		this(new LinkedList<Card>());
	}
	
	@Override
	public Card getCardAt(int i) {
		return cards.get(i);
	}

	@Override
	public void addCards (List<Card> cardsToAdd) {
		cards.addAll(cardsToAdd);
	}
	
	@Override
	public void removeCards (List<Card> cardsToRemove) {
		cards.removeAll(cardsToRemove);
	}
	
	@Override
	public Hand sortByRank() {
		Collections.sort(cards, new Comparator<Card>(){

			@Override
			public int compare(Card card1, Card card2) {
				return -card1.getRank().compareTo(card2.getRank());
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
		return(Arrays.toString(cards.toArray()));
	}

	@Override
	public void clearHand() {
		cards.clear();
	}
	
	
	
	
	/*@Override
	public List<Card> getCards() {
		return cards;
	}*/
}
