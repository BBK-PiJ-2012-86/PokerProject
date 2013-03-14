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
import lombok.ToString;

/**
 * 
 *
 */
@ToString
public class HandImpl implements Hand{
	@Getter private final List<Card> cards = new LinkedList<Card>();
	
	public HandImpl(List<Card> cards) {
		this.cards.addAll(cards);
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
	
	// changed to return Hand for mocking (to consider..)
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
	
	
	
	
	/*@Override
	public String toString() {
		return(Arrays.toString(cards.toArray()));
	}
	
	@Override
	public List<Card> getCards() {
		return cards;
	}*/
}
