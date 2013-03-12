package poker;

import static org.junit.Assert.*;

import org.junit.Test;

import Factories.DeckFactory;

public class DeckFactoryTest {

	@Test
	public void test() {
		DeckFactory classUnderTest = new DeckFactory();
		Deck deck = classUnderTest.getDeck();
		for(int n = 0; n < 52; n++){
			for(int i = 1; i < 4; i++){
				for(int k = 2; i < 14; i++){
					assertEquals(i, deck.getDeck().get(n).getSuitInt());
					assertEquals(k, deck.getDeck().get(n).getRankInt());
				}
			}
		}
	}

}
