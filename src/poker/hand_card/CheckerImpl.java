/**
 * 
 */
package poker.hand_card;


import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CheckerImpl implements Checker {
	
	private final static int FLUSH_SIZE = 5;
	private List<CheckResult> results = new LinkedList<CheckResult>();

	@Override
	public CheckResult check(Hand hand) {
		results.clear(); //to allow checker reuse
		
		boolean straight = checkStraight(hand);	//adds to results if true
		boolean flush = checkFlush(hand);	//adds to results if true
		if(straight && flush) {
			return itsStraightFlush(hand);
		}

		MultiplesChecker multiplesChecker = MultiplesCheckerFactory.getInstance().getMultiplesChecker();
		results.add(multiplesChecker.checkMultiples(hand));
		
		return Collections.max(results, CheckResult.getComparator());
	}
	
	private CheckResult itsStraightFlush(Hand hand) {
		hand = hand.sortByRank();
		return new CheckResult(ConditionType.STRAIGHT_FLUSH, hand);
	}

	private boolean checkStraight(Hand hand) {
		if (checkNormalStraight(hand)) {
			return true;
		} else {
			return checkWheelStraight(hand);
		}
	}

	private boolean checkNormalStraight(Hand hand) {
		hand = hand.sortByRank();
		Iterator<Card> it = hand.iterator();
		
		Card curr = it.next();
		Card next = null;
		while (it.hasNext()) {
			next = it.next();
			if ((curr.getRank().ordinal()-1)!=next.getRank().ordinal()) {
				return false;
			}
			curr = next;
		}
		results.add( new CheckResult(ConditionType.STRAIGHT,hand));
		return true;
	}
	
	private boolean checkWheelStraight(Hand hand) {
		Map<Rank, List<Card>> rankMap = Util.rankMap(hand);
		
		// catch hand of form 5432A
		for (Entry<Rank,List<Card>> entry : rankMap.entrySet()) {
			if ((entry.getKey()==Rank.ACE)||(entry.getKey().compareTo(Rank.SIX)<0)) {
				if (entry.getValue().size()<1) {return false;}
			}
		}
		
		List<Card> list = hand.getCards();
		Card cardToMove = rankMap.get(Rank.ACE).get(0);
		list.remove(cardToMove);
		list.add(cardToMove);
		
		results.add( new CheckResult(ConditionType.STRAIGHT, hand));
		return true;
	}

	private boolean checkFlush(Hand hand) {
		Map<Suit,List<Card>> suitMap = Util.suitMap(hand);
		
		for (List<Card> list : suitMap.values()) {
			if (list.size()==FLUSH_SIZE) {
				hand = hand.sortByRank();
				results.add( new CheckResult(ConditionType.FLUSH, hand));
				return true;
			}
		}
		return false;
	}
	
	
}
