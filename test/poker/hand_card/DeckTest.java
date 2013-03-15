package poker.hand_card;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DeckTest {
	
	private Deck deck;
	
	@Before
	public void setUp(){
		deck = DeckFactory.getDeckFactory().getDeck();
	}

	@Test
	public void test() {
		List<Card> testList = deck.dealCards(5);
		assertEquals(5, testList.size());
	}

}
