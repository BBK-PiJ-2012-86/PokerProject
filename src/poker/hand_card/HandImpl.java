/**
 * 
 */
package poker.hand_card;


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
//@ToString use a better one
public class HandImpl implements Hand{
	@Getter private final List<Card> cards = new LinkedList<Card>();
	
	public HandImpl(List<Card> cards) {
		this.cards.addAll(cards);
	}
	
	public HandImpl() {
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
				// reversed order of operands to get high->low ordering
				return card2.getRank().compareTo(card1.getRank());
			}
			
		});
		return this;
	}

	@Override
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
	
	@Override
	public void clearHand() {
		cards.clear();
	}
	
	@Override
	public Hand moveCardsToStartOthersRankOrder(List<Card> cardsToMove) {
		this.sortByRank();
		
		@SuppressWarnings("unchecked")
		List<Card> others = (List<Card>) ((LinkedList<Card>)cards).clone();

		others.removeAll(cardsToMove);
		
		this.clearHand();
		this.addCards(cardsToMove);
		this.addCards(others);
		return this;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		int size = cards.size();
		for (int i = 0; i<size-1; i++) {
			buf.append(cards.get(i).prettyPrint());
			buf.append(", ");
		}
		buf.append(cards.get(size-1).prettyPrint());	//no ", " after last card
		String result = buf.toString();
		return "["+result+"]";
	}
}
