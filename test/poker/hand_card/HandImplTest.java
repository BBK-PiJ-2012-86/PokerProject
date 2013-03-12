package poker.hand_card;

import static org.junit.Assert.assertEquals;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.JACK_SPADE;
import static poker.hand_card.TestCards.KING_SPADE;
import static poker.hand_card.TestCards.QUEEN_SPADE;
import static poker.hand_card.TestCards.TEN_SPADE;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class HandImplTest {
	
	@Test
	public void test() {	//to split and test nicely
		Card[] initial = new Card[] {KING_SPADE, JACK_SPADE, QUEEN_SPADE, ACE_SPADE};  //change to use mock Card??
		List<Card> cards = TestUtil.toLinkedList(initial);	
		Hand hand = new HandImpl(cards);	// need mocks here
		
		assertEquals(cards, hand.getCards());
		assertEquals("[King of Spades, Jack of Spades, Queen of Spades, Ace of Spades]", hand.toString());
		
		List<Card> extraCards = new LinkedList<Card>();
		extraCards.add(TEN_SPADE);
		hand.addCards(extraCards);
		
		assertEquals("[King of Spades, Jack of Spades, Queen of Spades, Ace of Spades, Ten of Spades]", hand.toString());
		
		hand.sortByRank();
		

		assertEquals("[Ace of Spades, King of Spades, Queen of Spades, Jack of Spades, Ten of Spades]", hand.toString());
		
		Card[] toRemove = new Card[] {KING_SPADE, JACK_SPADE, QUEEN_SPADE};
		List<Card> removalCards = TestUtil.toLinkedList(toRemove);

		hand.removeCards(removalCards);
		
		assertEquals("[Ace of Spades, Ten of Spades]", hand.toString());
	}
	
}
