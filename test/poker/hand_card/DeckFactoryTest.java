package poker.hand_card;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import poker.hand_card.DeckFactory;


public class DeckFactoryTest {

	@Test
	public void test() {
		DeckFactory classUnderTest = DeckFactory.getDeckFactory();
		Deck deck = classUnderTest.getDeck();

		List<Card> cardList = deck.getCards();
		int i = 0;
		for(Suit suit: Suit.values()){
			for(Rank rank: Rank.values()){
				assertEquals(suit, cardList.get(i).getSuit());
				assertEquals(rank, cardList.get(i).getRank());
				i++;
			}
		}
	}
	

}
