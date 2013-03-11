/**
 * 
 */
package poker;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Utilities {

	
	public static Map<Suit,List<Card>> suitMap(Hand hand) {
		Map<Suit,List<Card>> result = new HashMap<Suit,List<Card>>();
		for (Suit suit : Suit.values()) {
			result.put(suit, new LinkedList<Card>());
		}
		for (Card card : hand) {
			result.get(card.getSuit()).add(card);
		}
		return result;
	}
}
