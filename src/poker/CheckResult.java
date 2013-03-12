/**
 * 
 */
package poker;

import java.util.Comparator;
import java.util.Iterator;

/**
 * @author 86
 *
 */
public class CheckResult implements Comparable<CheckResult>{
	
	private final ConditionType conditionType;
	private final Hand supportingCards;
	
	
	public CheckResult(ConditionType conditionType, Hand hand) {
		this.conditionType = conditionType;
		this.supportingCards = hand;
	}

	public ConditionType getConditionType() {
		return this.conditionType;
	}
	
	public Hand getSupportingCards() {
		return supportingCards;
	}
	
	@Override
	public String toString() {
		String result = "";
		result+=this.getConditionType()+",";
		result+=this.getSupportingCards();
		return result;
	}
	
	@Override
	public int compareTo(CheckResult other) {
		int conditionCompareInt = this.getConditionType().compareTo(other.getConditionType());
		if (conditionCompareInt == 0) {
			return comparator.compare(this.getSupportingCards(), other.getSupportingCards());
		} else {
			return conditionCompareInt;
		}
	}

	/**
	 * Assumes the supporting cards are in the order:
	 * 1. relevant for win condition (decreasing rank)
	 * 2. other cards (decreasing rank)
	 */
	private static final Comparator<Hand> comparator = new Comparator<Hand>() {

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
