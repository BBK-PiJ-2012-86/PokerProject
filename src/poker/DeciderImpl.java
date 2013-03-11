/**
 * 
 */
package poker;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ruth
 *
 */
public class DeciderImpl implements Decider {
	
	@Override
	public List<Card> decide(CheckResult checkResult) {
		List<Card> result = new LinkedList<Card>();
		ConditionType conditionType = checkResult.getConditionType();
		if (conditionType.compareTo(ConditionType.Straight)<0){
			result.add(new Card(Rank.Ace,Suit.Clubs));
		}
		// else add nothing  - we don't want to remove any cards
		return result;
	}

}
