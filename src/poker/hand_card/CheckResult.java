/**
 * 
 */
package poker.hand_card;


import java.util.Comparator;
import java.util.Iterator;

import lombok.Data;


/**
 * A CheckResult is the result of checking a hand of cards for the best poker hand available.
 * It contains the condition type e.g. Flush, and the hand of cards ordered by that poker hand.
 * The ordering is by most important first e.g. a two pair would be ordered 8877Q.
 *
 */
@Data public class CheckResult {
	
	private final ConditionType conditionType;
	private final Hand orderedHand;
	
	/**
	 * @return a Comparator to order CheckResults by usual poker rules
	 */
	public static Comparator<CheckResult> getComparator() {
		return CR_COMPARATOR;
	}
	
	private static final Comparator<CheckResult> CR_COMPARATOR = new Comparator<CheckResult>() {

		@Override
		public int compare(CheckResult cr1, CheckResult cr2) {
			int conditionCompareInt = cr1.getConditionType().compareTo(cr2.getConditionType());
			if (conditionCompareInt == 0) {
				return crHandComparator.compare(cr1.getOrderedHand(), cr2.getOrderedHand());
			} else {
				return conditionCompareInt;
			}
		}
		
	};
	
	/* Assumes the supporting cards are in the order:
	 * 	1. relevant for win condition (decreasing rank)
	 * 	2. other cards (decreasing rank) */
	private static final Comparator<Hand> crHandComparator = new Comparator<Hand>() {	//move to Hand..???

		@Override
		public int compare(Hand hand1, Hand hand2) {
			Iterator<Card> it1 = hand1.iterator();
			Iterator<Card> it2 = hand2.iterator();
			Comparator<Card> cardRankComparator = Card.getCardRankComparator();
			int result = 0;
			while ((result == 0) && it1.hasNext() && it2.hasNext()) {
				result = cardRankComparator.compare(it1.next(), it2.next());
			}
			return result;
		}
		
	};

}
