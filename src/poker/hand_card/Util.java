/**
 * 
 */
package poker.hand_card;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A Utilities class to enable map making of hands.
 *
 */
public class Util {

	
	/**
	 * Makes a map from each suit to a list of cards in the hand of that suit
	 * @param hand to be represented in the map
	 * @return the map
	 */
	public static Map<Suit,List<Card>> suitMap(Hand hand) {
		Map<Suit,List<Card>> result = new HashMap<Suit,List<Card>>();
		for (Suit suit : Suit.values()) {
			result.put(suit, new LinkedList<Card>());
		}
		for (Card card : hand.getCards()) {
			result.get(card.getSuit()).add(card);
		}
		return result;
	}
	
	/**
	 * Makes a map from each card rank to a list of cards in the hand of that rank
	 * @param hand to be represented in the map
	 * @return the map
	 */
	public static Map<Rank, List<Card>> rankMap(Hand hand) {
		Map<Rank,List<Card>> result = new HashMap<Rank,List<Card>>();
		for (Rank rank : Rank.values()) {
			result.put(rank, new LinkedList<Card>());
		}
		for (Card card : hand.getCards()) {
			result.get(card.getRank()).add(card);
		}
		return result;
	}
}
