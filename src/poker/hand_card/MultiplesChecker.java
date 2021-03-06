package poker.hand_card;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * A MultiplesChecker is used to check for the poker hands which are based on multiples
 * i.e. Full house, 4/3/2 of a kind, Two pair, High card
 *
 */
public class MultiplesChecker {

	/**
	 * @param hand the hand to be checked
	 * @return the result corresponding the the best possible multiples based results
	 */
	public CheckResult checkMultiples(Hand hand) {
		Map<Rank, List<Card>> rankMap = Util.rankMap(hand);
		Rank tripleRank = null;
		Rank pairRank1 = null;
		Rank pairRank2 = null;
		for (Entry<Rank,List<Card>> entry : rankMap.entrySet()) {
			switch (entry.getValue().size()) {
			case 4:
				return makeFromRank(ConditionType.FOUR_OF_A_KIND, entry.getKey(), rankMap, hand);
			case 3:
				tripleRank = entry.getKey();
				break;
			case 2:
				if (pairRank1 == null) {
					pairRank1 = entry.getKey();
				} else {
					pairRank2 = entry.getKey();
				}
				break;
			default:
				//do nothing
				break;
			}
		}
		return checkTupleConditions(rankMap, tripleRank, pairRank1, pairRank2, hand);
	}

	private CheckResult checkTupleConditions(Map<Rank, List<Card>> rankMap,
			Rank tripleRank, Rank pairRank1, Rank pairRank2, Hand hand) {
		if ((tripleRank!=null) && (pairRank1!=null)) {
			return makeFromRank(ConditionType.FULL_HOUSE, tripleRank, rankMap, hand);
		}
		if ((tripleRank!=null) && (pairRank1==null)) {
			return makeFromRank(ConditionType.THREE_OF_A_KIND, tripleRank, rankMap, hand);
		}
		if (pairRank2!=null) {
			return makeFromPairs(rankMap, pairRank1, pairRank2, hand);
		}
		if (pairRank1!=null) {
			return makeFromRank(ConditionType.PAIR, pairRank1, rankMap, hand);
		} else {
			return makeFromHighCard(rankMap, hand);
		}
	}
	
	private CheckResult makeFromRank(ConditionType conditionType, Rank rank, Map<Rank, List<Card>> rankMap, Hand hand) {
		List<Card> tupleList = rankMap.get(rank);
		hand = hand.moveCardsToStartOthersRankOrder(tupleList);
		return( new CheckResult(conditionType, hand));
	}
		
	private CheckResult makeFromPairs(Map<Rank, List<Card>> rankMap, Rank pairRank1,
			Rank pairRank2, Hand hand) {
		Rank higher, lower;
		if (pairRank1.compareTo(pairRank2)<0) {
			lower = pairRank1;
			higher = pairRank2;
		} else {
			lower = pairRank2;
			higher = pairRank1;
		}
		List<Card> tupleList = rankMap.get(higher);
		tupleList.addAll(rankMap.get(lower));
		hand = hand.moveCardsToStartOthersRankOrder(tupleList);
		return( new CheckResult(ConditionType.TWO_PAIR, hand));
	}
	
	private CheckResult makeFromHighCard(Map<Rank, List<Card>> rankMap, Hand hand) {
		List<Card> tupleList = new LinkedList<Card>();
		hand = hand.moveCardsToStartOthersRankOrder(tupleList);
		return( new CheckResult(ConditionType.HIGH_CARD, hand));
	}

}
