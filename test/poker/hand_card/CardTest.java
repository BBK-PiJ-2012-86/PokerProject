package poker.hand_card;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;


public class CardTest {
	
	private Card card1;
	private Card card2;
	private Card card3;
	private Comparator<Card> comp;
	
	@Before
	public void setUp(){
		card1 = new Card(Rank.ACE, Suit.SPADES);
		card2 = new Card(Rank.TWO, Suit.DIAMONDS);
		card3 = new Card(Rank.TEN, Suit.CLUBS);
		comp = Card.getCardRankComparator();
	}
	
	@Test
	public void testToString1() {
		
		String expected = "ace of spades";
		String result = card1.prettyPrint();
		assertEquals(expected, result);
	}
	
	@Test
	public void testToString2(){
		
		String expected = "two of diamonds";
		String result = card2.prettyPrint();
		assertEquals(expected, result);
	}
	
	@Test
	public void testToString3(){
		String expected = "ten of clubs";
		String result = card3.prettyPrint();
		assertEquals(expected, result);
	}
	
	@Test
	public void comparatorTest1(){
		int result = comp.compare(card1, card2);
		int expected = 12;
		assertEquals(expected, result);
	}
	
	@Test
	public void comparatorTest2(){
		int result = comp.compare(card1, card3);
		int expected = 4;
		assertEquals(expected, result);
	}
	
	@Test
	public void comparatorTest3(){
		int result = comp.compare(card2, card3);
		int expected = -8;
		assertEquals(expected, result);
	}
	
	@Test
	public void comparatorTest4(){
		Card card4 = new Card(Rank.ACE, Suit.DIAMONDS);
		assertTrue(comp.compare(card1, card4) == 0);
	}
}
