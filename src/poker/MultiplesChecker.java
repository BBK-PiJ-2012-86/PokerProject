package poker;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MultiplesChecker {

	private List<CheckResult> results;
	
	public List<CheckResult> checkMultiples(Hand hand) {
		analyseMultiples(hand);
		return results;	//should be just a single result..?
	}
	
	private void analyseMultiples(Hand hand) {
		Map<Rank, List<Card>> rankMap = createRankMap(hand);
		Rank tripleRank = null;
		Rank pairRank1 = null;
		Rank pairRank2 = null;
		for (Entry<Rank,List<Card>> entry : rankMap.entrySet()) {
			switch (entry.getValue().size()) {
			case 4:
				results.add( new CheckResult(ConditionType.FourOfAKind, orderCards(entry.getValue(), hand)));
				return;
			case 3:
				tripleRank = entry.getKey();
				break;
			case 2:
				if (pairRank1 == null) {
					pairRank1 = entry.getKey();
				} else {
					pairRank2 = entry.getKey();
				}
			}
		}
		checkTupleConditions(rankMap, tripleRank, pairRank1, pairRank2, hand);
	}

	private Map<Rank, List<Card>> createRankMap(Hand hand) {
		Map<Rank,List<Card>> rankMap = new HashMap<Rank,List<Card>>();
		for (Rank rank : Rank.values()) {
			rankMap.put(rank, new LinkedList<Card>());
		}
		for (Card card : hand) {
			rankMap.get(card.getRank()).add(card);
		}
		return rankMap;
	}

	private void checkTupleConditions(Map<Rank, List<Card>> rankMap,
			Rank tripleRank, Rank pairRank1, Rank pairRank2, Hand hand) {
		if ((tripleRank!=null) && (pairRank1!=null)) {
			addFullHouse(rankMap, tripleRank, pairRank1);
			return;
		}
		if ((tripleRank!=null) && (pairRank1==null)) {
			addThreeOfAKind(rankMap, tripleRank, hand);
			return;
		}
		if (pairRank2!=null) {
			addTwoPair(rankMap, pairRank1, pairRank2, hand);
			return;
		}
		if (pairRank1!=null) {
			addThreeOfAKind(rankMap, pairRank1, hand);
			return;
		}
	}

	private void addTwoPair(Map<Rank, List<Card>> rankMap, Rank pairRank1,
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
		results.add( new CheckResult(ConditionType.ThreeOfAKind, orderCards(tupleList, hand)));
	}

	private void addThreeOfAKind(Map<Rank, List<Card>> rankMap, Rank tripleRank, Hand hand) {
		List<Card> tupleList = rankMap.get(tripleRank);
		results.add( new CheckResult(ConditionType.ThreeOfAKind, orderCards(tupleList, hand)));
	}

	private void addFullHouse(Map<Rank, List<Card>> rankMap, Rank tripleRank,
			Rank pairRank1) {
		List<Card> cards = rankMap.get(tripleRank);
		cards.addAll(rankMap.get(pairRank1));
		results.add( new CheckResult(ConditionType.FullHouse,new HandImpl(cards)));
	}
	
	private Hand orderCards (List<Card> relevantCards, Hand hand) {
		hand = hand.sortByRank();
		HandImpl result = new HandImpl();
		hand.addCards(relevantCards);
		List<Card> extras = hand.getCards();
		extras.removeAll(relevantCards);
		result.addCards(extras);
		return result;
	}

}
