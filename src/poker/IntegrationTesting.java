package poker;

import java.util.LinkedList;
import java.util.List;

public class IntegrationTesting {		// I will change this to a JUnit test..
	
	private static final Card ACE_OF_SPADES = new Card(Rank.Ace, Suit.Spades);
	private static final Card KING_OF_SPADES = new Card(Rank.King, Suit.Spades);
	private static final Card QUEEN_OF_SPADES = new Card(Rank.Queen, Suit.Spades);
	private static final Card JACK_OF_SPADES = new Card(Rank.Jack, Suit.Spades);
	private static final Card TEN_OF_SPADES = new Card(Rank.Ten, Suit.Spades);

	public static void main (String[] args) {
		IntegrationTesting joined = new IntegrationTesting();
		joined.launch();
	}
	
	public void launch() {
		// put some cards in a hand
		Card[] cardsToAdd = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES};
		List<Card> cards = new LinkedList<Card>();
		for (Card card : cardsToAdd) {
			cards.add(card);
		}
		Hand hand = new HandImpl(cards);
		
		// get a checker given a GameType and use it to check the hand
		GameType type = GameType.fiveCardDraw;
		Checker checker = CheckerFactory.getInstance(type).getChecker();
		CheckResult result = checker.check(hand);
		
		//print out the result
		System.out.println(result);		//not quite right yet..
		
		
	}

}
