package poker;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class DeciderImplTest {
	private static final Card ACE_OF_SPADES = new Card(Rank.Ace, Suit.Spades);
	private static final Card KING_OF_SPADES = new Card(Rank.King, Suit.Spades);
	private static final Card QUEEN_OF_SPADES = new Card(Rank.Queen, Suit.Spades);
	private static final Card JACK_OF_SPADES = new Card(Rank.Jack, Suit.Spades);
	private static final Card TEN_OF_SPADES = new Card(Rank.Ten, Suit.Spades);

	@Test
	public void testStraightFlush() {	// to do properly with mocks etc
		//make a CheckResult (for now) for a SF
		Card [] cardArray = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES};
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		Hand hand = new HandImpl(cardList);
		CheckResult checkResult = new CheckResult(ConditionType.StraightFlush, hand);
		
		//make a Decider and get it to decide
		Decider decider = new DeciderImpl();
		List<Card> actual = decider.decide(checkResult);
		List<Card> expected = new LinkedList<Card>();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testThreeOfAKind() {
		//make a CheckResult (for now) for a SF
		Card [] cardArray = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES};
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		Hand hand = new HandImpl(cardList);
		CheckResult checkResult = new CheckResult(ConditionType.ThreeOfAKind, hand);
		
		//make a Decider and get it to decide
		Decider decider = new DeciderImpl();
		List<Card> actual = decider.decide(checkResult);
		List<Card> expected = new LinkedList<Card>();
		expected.add(new Card(Rank.Ace,Suit.Clubs));
		
		assertEquals(expected, actual);
	}
	
	
	
}
