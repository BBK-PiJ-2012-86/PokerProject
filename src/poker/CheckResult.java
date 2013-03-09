/**
 * 
 */
package poker;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author 86
 *
 */
public class CheckResult implements Comparable<CheckResult>{
	
	private static final Comparator<Hand> comparator = new Comparator<Hand>() {

		@Override
		public int compare(Hand hand1, Hand hand2) {
			Iterator<Card> it1 = hand1.iterator();
			Iterator<Card> it2 = hand2.iterator();
			int result = 0;
			while ((result == 0) && it1.hasNext() && it2.hasNext()) {
				result = it1.next().compareTo(it2.next());
			}
			return result;
		}
		
	};
	
	
	
	private final ConditionType conditionType = null;
	private final Hand supportingCards = null;
	
	
	public ConditionType getConditionType() {
		return this.conditionType;
	}
	
	private Hand getSupportingCards() {
		return supportingCards;
	}
	
	@Override
	public int compareTo(CheckResult other) {
		int conditionCompareInt = this.getConditionType().compareTo(other.getConditionType());
		if (conditionCompareInt == 0) {
			return comparator.compare(this.getSupportingCards(), other.getSupportingCards())
		} else {
			return conditionCompareInt;
		}
	}

	
	

	
	
	
}
