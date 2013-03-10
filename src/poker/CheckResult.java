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
	private final HandImpl supportingCards;
	
	
	public CheckResult(ConditionType conditionType, HandImpl hand) {
		this.conditionType = conditionType;
		this.supportingCards = hand;
	}

	public ConditionType getConditionType() {
		return this.conditionType;
	}
	
	public HandImpl getSupportingCards() {
		return supportingCards;
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
	private static final Comparator<HandImpl> comparator = new Comparator<HandImpl>() {

		@Override
		public int compare(HandImpl hand1, HandImpl hand2) {
			Iterator<Card> it1 = hand1.iterator();
			Iterator<Card> it2 = hand2.iterator();
			int result = 0;
			while ((result == 0) && it1.hasNext() && it2.hasNext()) {
				result = it1.next().compareTo(it2.next());
			}
			return result;
		}
		
	};

	
	
	
}
