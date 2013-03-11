package poker;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HandImplTest {		//NB TODO: allow remove cards on Hand then change Impl for checkers
	
	private static final Card ACE_OF_SPADES = new Card(Rank.Ace, Suit.Spades);
	private static final Card KING_OF_SPADES = new Card(Rank.King, Suit.Spades);
	private static final Card QUEEN_OF_SPADES = new Card(Rank.Queen, Suit.Spades);
	private static final Card JACK_OF_SPADES = new Card(Rank.Jack, Suit.Spades);
	private static final Card TEN_OF_SPADES = new Card(Rank.Ten, Suit.Spades);

	@Test
	public void test() {	//to split and test nicely
		Card[] initial = new Card[] {KING_OF_SPADES, JACK_OF_SPADES, QUEEN_OF_SPADES, ACE_OF_SPADES};
		List<Card> cards = new LinkedList<Card>();	//make a copy rather than point?
		for (Card card : initial) {
			cards.add(card);					// change to use mock Card ??
		}
		Hand hand = new HandImpl(cards);
		
		assertEquals(cards, hand.getCards());
		
		List<Card> extraCards = new LinkedList<Card>();
		extraCards.add(TEN_OF_SPADES);
		hand.addCards(extraCards);
		System.out.println("after add to hand:"+hand.getCards());
		
		Card[] withExtra = new Card[] {KING_OF_SPADES, JACK_OF_SPADES, QUEEN_OF_SPADES, ACE_OF_SPADES, TEN_OF_SPADES};
		List<Card> withExtraCards = new LinkedList<Card>();
		for (Card card : withExtra) {
			withExtraCards.add(card);
		}
		
		assertEquals(withExtraCards, hand.getCards());
		
		hand.sortByRank();
		System.out.println("after sorting:"+hand.getCards());
		
		Card[] inOrder = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES};
		List<Card> cardsInOrder = new LinkedList<Card>();
		for (Card card : inOrder) {
			cardsInOrder.add(card);
		}

		assertEquals(cardsInOrder, hand.getCards());
	}
	
	/*
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
	*/
}
