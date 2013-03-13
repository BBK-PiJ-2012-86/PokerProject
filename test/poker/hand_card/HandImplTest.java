package poker.hand_card;

import static org.junit.Assert.assertEquals;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.JACK_SPADE;
import static poker.hand_card.TestCards.KING_SPADE;
import static poker.hand_card.TestCards.QUEEN_SPADE;
import static poker.hand_card.TestCards.TEN_CLUB;

import java.util.List;

import org.junit.Test;

public class HandImplTest {
	
	@Test
	public void testaddCards() {		//change to use mock Card??
		List<Card> cards = TestUtil.toLinkedList(new Card[] {KING_SPADE, JACK_SPADE, QUEEN_SPADE});	
		List<Card> extraCards = TestUtil.toLinkedList(new Card[] {TEN_CLUB, ACE_SPADE});
		List<Card> expected = TestUtil.toLinkedList(new Card[] {KING_SPADE, JACK_SPADE, QUEEN_SPADE, TEN_CLUB, ACE_SPADE});
		
		Hand hand = new HandImpl(cards);
		hand.addCards(extraCards);
		
		assertEquals(expected, hand.getCards());
	}
	
	@Test
	public void testSortByRank() {
		List<Card> cards = TestUtil.toLinkedList(new Card[] {KING_SPADE, JACK_SPADE, QUEEN_SPADE, ACE_SPADE, TEN_CLUB});
		List<Card> expected = TestUtil.toLinkedList(new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_SPADE, TEN_CLUB});
		
		Hand hand = new HandImpl(cards);
		hand.sortByRank();
		
		assertEquals(expected, hand.getCards());
	}
	
	@Test
	public void testRemoveCards() {
		List<Card> cards = TestUtil.toLinkedList(new Card[] {KING_SPADE, JACK_SPADE, QUEEN_SPADE, ACE_SPADE, TEN_CLUB});
		List<Card> toRemove = TestUtil.toLinkedList(new Card[] {TEN_CLUB, JACK_SPADE, QUEEN_SPADE});
		List<Card> expected = TestUtil.toLinkedList(new Card[] {KING_SPADE, ACE_SPADE});

		Hand hand = new HandImpl(cards);
		hand.removeCards(toRemove);
		
		assertEquals(expected, hand.getCards());
	}
	
	@Test
	public void testMoveCardsToStartOthersRankOrder1() {
		List<Card> cards = TestUtil.toLinkedList(new Card[] {KING_SPADE, JACK_SPADE, QUEEN_SPADE, ACE_SPADE, TEN_CLUB});
		List<Card> toMove = TestUtil.toLinkedList(new Card[] {TEN_CLUB, JACK_SPADE, QUEEN_SPADE});
		List<Card> expected = TestUtil.toLinkedList(new Card[] {TEN_CLUB, JACK_SPADE, QUEEN_SPADE, ACE_SPADE, KING_SPADE});

		Hand hand = new HandImpl(cards);
		hand.moveCardsToStartOthersRankOrder(toMove);
		
		assertEquals(expected, hand.getCards());
	}
	
	@Test
	public void testMoveCardsToStartOthersRankOrder2() {
		List<Card> cards = TestUtil.toLinkedList(new Card[] {KING_SPADE, JACK_SPADE, QUEEN_SPADE, ACE_SPADE, TEN_CLUB});
		List<Card> toMove = cards;
		List<Card> expected = cards;

		Hand hand = new HandImpl(cards);
		hand.moveCardsToStartOthersRankOrder(toMove);
		
		assertEquals(expected, hand.getCards());
	}
	
	
}
