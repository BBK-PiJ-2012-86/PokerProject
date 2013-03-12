package poker.hand_card;

import static org.junit.Assert.assertEquals;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.FIVE_CLUB;
import static poker.hand_card.TestCards.FOUR_CLUB;
import static poker.hand_card.TestCards.JACK_DIAMOND;
import static poker.hand_card.TestCards.JACK_HEART;
import static poker.hand_card.TestCards.JACK_SPADE;
import static poker.hand_card.TestCards.KING_SPADE;
import static poker.hand_card.TestCards.QUEEN_SPADE;
import static poker.hand_card.TestCards.SIX_CLUB;
import static poker.hand_card.TestCards.TEN_CLUB;
import static poker.hand_card.TestCards.TEN_SPADE;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class DeciderImplTest {
	// pre-made cards like TEN_SPADE imported from TestCards to avoid repetition in tests
	// to consider mocking these as well - maybe a mock making factory..?

	@Test
	public void testStraightFlush() {	// TODO: do properly with mocks etc
		Card [] cardArray = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_SPADE, TEN_SPADE};
		Card [] expectedCards = new Card[] {};
		CheckResult checkResult = makeCheckResult(ConditionType.StraightFlush, cardArray);
		testDecider(checkResult, expectedCards);
	}
	
	@Test
	public void testThreeOfAKindReturnOne() {
		Card [] cardArray = new Card[] {JACK_DIAMOND, JACK_SPADE, JACK_HEART, KING_SPADE, QUEEN_SPADE,};
		Card [] expectedCards = new Card[] {QUEEN_SPADE};
		CheckResult checkResult = makeCheckResult(ConditionType.ThreeOfAKind, cardArray);
		testDecider(checkResult, expectedCards);
	}
	
	@Test
	public void testThreeOfAKindReturnTwo() {
		Card [] cardArray = new Card[] {JACK_DIAMOND, JACK_SPADE, JACK_HEART, SIX_CLUB, FIVE_CLUB};
		Card [] expectedCards = new Card[] {SIX_CLUB, FIVE_CLUB};
		CheckResult checkResult = makeCheckResult(ConditionType.ThreeOfAKind, cardArray);
		testDecider(checkResult, expectedCards);
	}
	
	@Test
	public void testTwoPair() {
		Card [] cardArray = new Card[] {JACK_DIAMOND, JACK_SPADE, TEN_SPADE, TEN_CLUB, FIVE_CLUB};
		Card [] expectedCards = new Card[] {FIVE_CLUB};
		CheckResult checkResult = makeCheckResult(ConditionType.TwoPair, cardArray);
		testDecider(checkResult, expectedCards);
	}
	
	@Test
	public void testPairReturnTwo() {
		Card [] cardArray = new Card[] {JACK_DIAMOND, JACK_SPADE, QUEEN_SPADE, TEN_CLUB, FIVE_CLUB};
		Card [] expectedCards = new Card[] {TEN_CLUB, FIVE_CLUB};
		CheckResult checkResult = makeCheckResult(ConditionType.Pair, cardArray);
		testDecider(checkResult, expectedCards);
	}
	
	@Test
	public void testPairReturnThree() {
		Card [] cardArray = new Card[] {JACK_DIAMOND, JACK_SPADE, SIX_CLUB, FIVE_CLUB, FOUR_CLUB};
		Card [] expectedCards = new Card[] {SIX_CLUB, FIVE_CLUB, FOUR_CLUB};
		CheckResult checkResult = makeCheckResult(ConditionType.Pair, cardArray);
		testDecider(checkResult, expectedCards);
	}
	
	@Test
	public void testHighCardCloseToFlush() {
		Card [] cardArray = new Card[] {ACE_SPADE, QUEEN_SPADE, JACK_SPADE, SIX_CLUB, FIVE_CLUB};
		Card [] expectedCards = new Card[] {SIX_CLUB, FIVE_CLUB};
		CheckResult checkResult = makeCheckResult(ConditionType.HighCard, cardArray);
		testDecider(checkResult, expectedCards);
	}
	
	@Test
	public void testHighCardFarFromFlush() {
		Card [] cardArray = new Card[] {ACE_SPADE, QUEEN_SPADE, JACK_DIAMOND, SIX_CLUB, FIVE_CLUB};
		Card [] expectedCards = new Card[] {JACK_DIAMOND, SIX_CLUB, FIVE_CLUB};
		CheckResult checkResult = makeCheckResult(ConditionType.HighCard, cardArray);
		testDecider(checkResult, expectedCards);
	}
	
	private CheckResult makeCheckResult(ConditionType conditionType, Card[] cardArray) {
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		Hand hand = new HandImpl(cardList);
		CheckResult checkResult = new CheckResult(conditionType, hand);
		return checkResult;
	}
	
	private void testDecider(CheckResult checkResult, Card[] expectedCards) {
		Decider decider = new DeciderImpl();
		List<Card> actual = decider.decide(checkResult);
		List<Card> expected = new LinkedList<Card>();
		for (Card card : expectedCards) {
			expected.add(card);
		}
		
		assertEquals(expected, actual);
	}
	
	
}
