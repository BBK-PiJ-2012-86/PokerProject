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
public class HandImpl implements Hand{
	private List<Card> cards;
	
	public HandImpl(List<Card> cards) {
		this.cards = cards;
	}
	
	public HandImpl() {	//poss delete this one
		this(null);
	}
	
	/* (non-Javadoc)
	 * @see poker.Hand#getCards()
	 */
	@Override
	public List<Card> getCards() {
		return cards;
	}
	/* (non-Javadoc)
	 * @see poker.Hand#addCards(java.util.List)
	 */
	@Override
	public void addCards (List<Card> cards) {
		cards.addAll(cards);
	}
	
	/*public void sortByRankThenSuit() {
		sortBySuitOnly();
		sortByRankOnly();
	}
	
	public void sortBySuitThenRank() {
		sortByRankOnly();
		sortBySuitOnly();
	}
	
	*/
	/* (non-Javadoc)
	 * @see poker.Hand#sortByRank()
	 */
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
	/*
	
	private void sortBySuitOnly() {
		Collections.sort(cards, new Comparator<Card>(){

			@Override
			public int compare(Card card1, Card card2) {
				return card1.getSuit().compareTo(card2.getSuit());
			}
			
		});
	}*/

	/* (non-Javadoc)
	 * @see poker.Hand#iterator()
	 */
	@Override
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
	
	
}
