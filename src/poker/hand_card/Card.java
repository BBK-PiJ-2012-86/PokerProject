package poker.hand_card;

import java.util.Comparator;

import lombok.Data;


/**
 * Class to encapsulate playing cards
 *
 */
@Data public class Card {
	
	private final Rank rank;
	private final Suit suit;
	
	private static final Comparator<Card> cardRankComparator = new Comparator<Card>() {

		@Override
		public int compare(Card card1, Card card2) {
			return card1.getRank().compareTo(card2.getRank());
		}		
	};
	
	/**
	 * @return a Comparator to compare cards by rank
	 */
	public static Comparator<Card> getCardRankComparator() {
		return cardRankComparator;
	}
}
