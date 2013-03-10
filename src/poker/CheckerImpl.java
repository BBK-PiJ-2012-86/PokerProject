/**
 * 
 */
package poker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 86
 *
 */
public class CheckerImpl implements Checker {	//assumes exactly five cards for now
	
	private Hand hand;
	private List<CheckResult> results;

	public CheckerImpl(Hand hand) {
		this.hand = hand;
	}
	
	/* (non-Javadoc)
	 * @see poker.Checker#check()
	 */
	@Override
	public CheckResult check() {
		checkFlush();
		checkStraight();
		MultiplesChecker multiplesChecker = new MultiplesChecker(hand);
		List<CheckResult> multiplesResults = multiplesChecker.checkMultiples();
		results.addAll(multiplesResults);
		checkStraightFlush(); 
				
		return Collections.max(results);
	}

	private void checkStraightFlush() {
		boolean straight = false;
		boolean flush = false;
		for (CheckResult checkResult : results) {
			if (checkResult.getConditionType().equals(ConditionType.Straight)) {
				straight = true;
			}
			if (checkResult.getConditionType().equals(ConditionType.Flush)) {
				flush = true;
			}
		}
		if (straight && flush) {
			results.add( new CheckResult(ConditionType.StraightFlush, hand));
		}
	}

	private void checkStraight() {
		hand.sortByRank();
		Iterator<Card> it = hand.iterator();
		Card curr = it.next();
		while (it.hasNext()) {
			if ((curr.getRank().ordinal()-1)!=(it.next().getRank().ordinal())) {
				return;
			}
		}
		results.add( new CheckResult(ConditionType.Straight,hand));
	}

	private void checkFlush() {
		Map<Suit,List<Card>> suitMap = new HashMap<Suit,List<Card>>();
		for (Suit suit : Suit.values()) {
			suitMap.put(suit, new LinkedList<Card>());
		}
		for (Card card : hand) {
			suitMap.get(card.getSuit()).add(card);
		}

		for (List<Card> list : suitMap.values()) {
			if (list.size()==5) {
				hand.sortByRank();
				results.add( new CheckResult(ConditionType.Flush,hand));
			}
		}
	}
	
	

}
