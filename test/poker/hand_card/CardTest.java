package poker.hand_card;

import static org.junit.Assert.*;

import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.Rank;
import poker.hand_card.Suit;

public class CardTest {

	@Test
	public void testToString1() {
		
		Rank rank = Rank.Ace;  
		Suit suit = Suit.Hearts;
		Card card = new Card(rank, suit);
		
		String expected = "Ace of Hearts";
		String result = card.toString();
		assertEquals(expected, result);
	}
	
	@Test
	public void testToString2(){
		Rank rank = Rank.King;
		Suit suit = Suit.Spades;
		
		Card card = new Card(rank, suit);
		
		String expected = "King of Spades";
		String result = card.toString();
		assertEquals(expected, result);
	}
}