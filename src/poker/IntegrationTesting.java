package poker;

import java.util.LinkedList;
import java.util.List;

public class IntegrationTesting {		// I will change this to a JUnit test..
	
	private static final Card ACE_OF_SPADES = new Card(Rank.Ace, Suit.Spades);
	private static final Card KING_OF_SPADES = new Card(Rank.King, Suit.Spades);
	private static final Card QUEEN_OF_SPADES = new Card(Rank.Queen, Suit.Spades);
	private static final Card JACK_OF_SPADES = new Card(Rank.Jack, Suit.Spades);
	private static final Card TEN_OF_SPADES = new Card(Rank.Ten, Suit.Spades);
	
	private static final Card ACE_OF_HEARTS = new Card(Rank.Ace, Suit.Hearts);

	public static void main (String[] args) {
		IntegrationTesting joined = new IntegrationTesting();
		joined.launch();
	}
	
	public void launch() {
		// put some cards in a hand
		Card[] straightFlush = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES};
		List<Card> straightFlushCards = new LinkedList<Card>();
		for (Card card : straightFlush) {
			straightFlushCards.add(card);
		}
		Hand straightFlushHand = new HandImpl(straightFlushCards);
		
		// get a checker given a GameType and use it to check the hand
		GameType type = GameType.fiveCardDraw;
		Checker checker = CheckerFactory.getInstance(type).getChecker();
		CheckResult result1 = checker.check(straightFlushHand);
		
		//print out the result
		System.out.println(result1);		//yay!
		
		//REPEAT
		// put some cards in a hand
		Card[] highCard = new Card[] {TEN_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, ACE_OF_HEARTS};
		List<Card> highCardCards = new LinkedList<Card>();
		for (Card card : highCard) {
			highCardCards.add(card);
		}
		Hand highCardHand = new HandImpl(highCardCards);
		
		// use same checker to check the hand
		CheckResult result2 = checker.check(highCardHand);
		System.out.println(result2);		//hmmmmmmmmmmmmm... didn't work.. try with a new checker..
		
		// get a checker given a GameType and use it to check the hand
		Checker checker2 = CheckerFactory.getInstance(type).getChecker();
		result2 = checker.check(highCardHand);
		System.out.println(result2);	//still wrong, try not SF first.. ok, yes, then works..! (sort this bug)

		
		
	}

}
