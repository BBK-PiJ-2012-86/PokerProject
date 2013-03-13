/**
 * 
 */
package poker.hand_card;


import java.util.Comparator;
import java.util.Iterator;

import lombok.Data;


@Data public class CheckResult {
	
	private final ConditionType conditionType;
	private final Hand orderedHand;
	
	public static Comparator<CheckResult> getComparator() {
		return comparator;
	}
	
	private static final Comparator<CheckResult> comparator = new Comparator<CheckResult>() {

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
	
	/**
	 * Assumes the supporting cards are in the order:
	 * 1. relevant for win condition (decreasing rank)
	 * 2. other cards (decreasing rank)
	 */
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

	
	
	
	
	/*@Override
	public String toString() {
		String result = "";
		result+=this.getConditionType()+",";
		result+=this.getSupportingCards();
		return result;
	}
	
	public CheckResult(ConditionType conditionType, Hand hand) {
		this.conditionType = conditionType;
		this.supportingCards = hand;
	}
	
	public ConditionType getConditionType() {
		return this.conditionType;
	}
	
	public Hand getSupportingCards() {
		return supportingCards;
	}*/
		
}
