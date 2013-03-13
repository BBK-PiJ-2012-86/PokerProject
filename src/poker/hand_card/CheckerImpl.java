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

/**
 * 
 * 
 */
public class CheckerImpl implements Checker {	//assumes exactly five cards
	
	private List<CheckResult> results = new LinkedList<CheckResult>();

	@Override
	public CheckResult check(Hand hand) {
		results.clear();
		if (hand.getCards().size()==0) {return null;}
		//moved earlier
		checkWheelStraightAlternative(hand);
		//checkFlush(hand);
		//checkStraight(hand);
		//checkWheelStraightAlternative(hand);// bodged - improve
		MultiplesChecker multiplesChecker = MultiplesCheckerFactory.getInstance().getMultiplesChecker();
		results.add(multiplesChecker.checkMultiples(hand));
		checkStraightFlush(hand); 
		
		return Collections.max(results, CheckResult.getComparator());
	}

	private void checkWheelStraight(Hand hand) {//improve
		hand = hand.sortByRank();
		
		if ((hand.getCardAt(0).getRank()==Rank.Ace) && (hand.getCardAt(1).getRank()==Rank.Five) && (hand.getCardAt(2).getRank()==Rank.Four)
				&& (hand.getCardAt(3).getRank()==Rank.Three) && (hand.getCardAt(4).getRank()==Rank.Two)) {
			List<Card> list = hand.getCards();
			Card cardToMove = list.get(0);
			list.remove(cardToMove);
			list.add(cardToMove);
			results.add( new CheckResult(ConditionType.Straight, hand));
		}	
	}
	
	private void checkWheelStraightAlternative(Hand hand) {//to work on
		System.out.println("Before making map");
		Map<Rank, List<Card>> rankMap = Util.rankMap(hand);			//problem if we ask it to make it again.
		System.out.println("After making map");
		
		for (Entry<Rank,List<Card>> entry : rankMap.entrySet()) {
			//System.out.println("entry::"+entry);
			//System.out.println("cardList::"+entry.getValue());
			if ((entry.getKey()==Rank.Ace)||(entry.getKey().compareTo(Rank.Six)<0)) {
				System.out.println("I'm now checking the map");
				System.out.println(entry.getKey()+"occurs "+entry.getValue().size());
				if (entry.getValue().size()!=1) {return;}
			}
		}
		// to sort better
		List<Card> list = hand.getCards();
		Card cardToMove = rankMap.get(Rank.Ace).get(0);//list.get(0);
		list.remove(cardToMove);
		list.add(cardToMove);
		//System.out.println(hand);
		
		results.add( new CheckResult(ConditionType.Straight, hand));
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
			if ((curr.getRank().ordinal()-1)!=next.getRank().ordinal()) {
				return;
			}
			curr = next;
		}
		results.add( new CheckResult(ConditionType.Straight,hand));
	}

	private void checkFlush(Hand hand) {
		Map<Suit,List<Card>> suitMap = Util.suitMap(hand);

		for (List<Card> list : suitMap.values()) {
			if (list.size()==5) {
				hand = hand.sortByRank();
				results.add( new CheckResult(ConditionType.Flush, hand));
			}
		}
	}
	
	

}
