/**
 * 
 */
package poker;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 86
 * 
 */
public class CheckerImpl implements Checker {	//assumes exactly five cards for now
	
	private List<CheckResult> results = new LinkedList<CheckResult>();	/////TODO: Low-high straight

	@Override
	public CheckResult check(Hand hand) {
		results.clear();
		if (hand.getCards().size()==0) {return null;}
		checkFlush(hand);
		checkStraight(hand);
		MultiplesChecker multiplesChecker = MultiplesCheckerFactory.getInstance().getMultiplesChecker();
		results.add(multiplesChecker.checkMultiples(hand));
		checkStraightFlush(hand); 
		
		return Collections.max(results);
	}

	private void checkStraightFlush(Hand hand) {
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
			results.add( new CheckResult(ConditionType.StraightFlush, hand.sortByRank()));
		}
	}

	private void checkStraight(Hand hand) {
		hand = hand.sortByRank();
		Iterator<Card> it = hand.iterator();
		
		Card curr = it.next();
		Card next = null;
		while (it.hasNext()) {
			next = it.next();
			if ((curr.getRank().ordinal()+1)!=next.getRank().ordinal()) {
				return;
			}
			curr = next;
		}
		results.add( new CheckResult(ConditionType.Straight,hand));
	}

	private void checkFlush(Hand hand) {
		Map<Suit,List<Card>> suitMap = Utilities.suitMap(hand);

		for (List<Card> list : suitMap.values()) {
			if (list.size()==5) {
				hand = hand.sortByRank();
				results.add( new CheckResult(ConditionType.Flush, hand));
			}
		}
	}
	
	

}
