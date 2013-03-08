package poker;

import static org.junit.Assert.*;

import org.junit.Test;

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
	
	@Test
	public void testRanktoRankConversionInt(){
		Rank rank = Rank.Eight;
		Suit suit = Suit.Clubs;
				
		Card card = new Card(rank, suit);
		int expected = 8;
		int result = card.getRankInt();
		assertEquals(expected, result);
	}
	
	@Test
	public void testRanktoRankConversionRank(){
		Card card = new Card(4,3);
		Rank expected = Rank.Four;
		Rank result = card.getRank();
		assertEquals(expected, result);
		
	}
	
	@Test
	public void testSuitToSuitConversionInt(){
		Rank rank = Rank.Jack;
		Suit suit = Suit.Spades;
		Card card = new Card(rank, suit);
		assertEquals(1, card.getSuitInt());
	}
	
	@Test
	public void testSuitToSuitConversionSuit(){
		Card card = new Card(14,2);
		Suit suit = Suit.Clubs;
		assertEquals(suit, card.getSuit());
	}

}
