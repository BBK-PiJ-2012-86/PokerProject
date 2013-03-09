/**
 * 
 */
package poker;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 86
 *
 */
public class Checker {	//assumes exactly five cards for now
	
	private Hand hand;
	private List<CheckResult> results;

	public Checker(Hand hand) {
		this.hand = hand;
	}
	
	public CheckResult check(Hand hand) {
		checkFlush(hand);
		
		checkStraight(hand);
	
		checkMultiples(hand);
		
		Set<ConditionType> conditions = new HashSet<ConditionType>();
		for (CheckResult checkResult : results) {
			conditions.add(checkResult.getConditionType());
		}
		if (conditions.contains(ConditionType.Straight) && conditions.contains(ConditionType.Flush) ) {
			return 
		}
		//
		
		return Collections.max(results);	//consider straight flush at this level?
		
		
	}

	private void checkMultiples(Hand hand) {
		Map<Rank,List<Card>> rankMap = new HashMap<Rank,List<Card>>();
		for (Rank rank : Rank.values()) {
			rankMap.put(rank, new LinkedList<Card>());
		}
		for (Card card : hand) {
			rankMap.get(card.getRank()).add(card);
		}
		
		for (List<Card> list : rankMap.values()) {
			switch (list.size()) {
			case 4:
				results.add( new CheckResult(ConditionType.FourOfAKind,orderCards(list)));
				return;
			case 3:
				results.add( new CheckResult(ConditionType.ThreeOfAKind,orderCards(list)));
				break;
			case 2:
				results.add( new CheckResult(ConditionType.Pair,orderCards(list)));
			}
		}
	}
	
	private Hand orderCards (List<Card> relevantCards) {
		Hand result = new Hand();
		hand.addCards(relevantCards);
		List<Card> extras = hand.getCards();
		extras.removeAll(relevantCards);
		result.addCards(extras);
		return result;
	}

	private void checkStraight(Hand hand) {
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

	private void checkFlush(Hand hand) {
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
