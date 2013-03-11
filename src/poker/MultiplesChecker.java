package poker;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MultiplesChecker {

	//private List<CheckResult> results = new LinkedList<CheckResult>();
	
	public CheckResult checkMultiples(Hand hand) {
		return analyseMultiples(hand);
		//return results;	//should be just a single result..?
	}
	
	private CheckResult analyseMultiples(Hand hand) {
		Map<Rank, List<Card>> rankMap = createRankMap(hand);
		Rank tripleRank = null;
		Rank pairRank1 = null;
		Rank pairRank2 = null;
		for (Entry<Rank,List<Card>> entry : rankMap.entrySet()) {
			switch (entry.getValue().size()) {
			case 4:
				return ( new CheckResult(ConditionType.FourOfAKind, orderCards(entry.getValue(), hand)));
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
		return checkTupleConditions(rankMap, tripleRank, pairRank1, pairRank2, hand);
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

	private CheckResult checkTupleConditions(Map<Rank, List<Card>> rankMap,
			Rank tripleRank, Rank pairRank1, Rank pairRank2, Hand hand) {
		if ((tripleRank!=null) && (pairRank1!=null)) {
			return addFullHouse(rankMap, tripleRank, pairRank1);
		}
		if ((tripleRank!=null) && (pairRank1==null)) {
			return addThreeOfAKind(rankMap, tripleRank, hand);
		}
		if (pairRank2!=null) {
			return addTwoPair(rankMap, pairRank1, pairRank2, hand);
		}
		if (pairRank1!=null) {
			return addPair(rankMap, pairRank1, hand);
		} else {
			return addHighCard(rankMap, hand);
		}
	}
	
	private CheckResult addHighCard(Map<Rank, List<Card>> rankMap, Hand hand) {
		List<Card> tupleList = new LinkedList<Card>();
		return( new CheckResult(ConditionType.HighCard, orderCards(tupleList, hand)));
	}
	
	private CheckResult addPair(Map<Rank, List<Card>> rankMap, Rank pairRank1, Hand hand) {
		List<Card> tupleList = rankMap.get(pairRank1);
		return( new CheckResult(ConditionType.Pair, orderCards(tupleList, hand)));
	}

	private CheckResult addTwoPair(Map<Rank, List<Card>> rankMap, Rank pairRank1,
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
		return( new CheckResult(ConditionType.TwoPair, orderCards(tupleList, hand)));
	}

	private CheckResult addThreeOfAKind(Map<Rank, List<Card>> rankMap, Rank tripleRank, Hand hand) {
		List<Card> tupleList = rankMap.get(tripleRank);
		return( new CheckResult(ConditionType.ThreeOfAKind, orderCards(tupleList, hand)));
	}

	private CheckResult addFullHouse(Map<Rank, List<Card>> rankMap, Rank tripleRank,
			Rank pairRank1) {
		List<Card> cards = rankMap.get(tripleRank);
		cards.addAll(rankMap.get(pairRank1));
		return( new CheckResult(ConditionType.FullHouse, new HandImpl(cards)));	//consider..
	}
	
	private Hand orderCards (List<Card> relevantCards, Hand hand) {
		hand = hand.sortByRank();
		Hand result = new HandImpl();	// use a factory instead so it uses a mockHand when checking?
		result.addCards(relevantCards);
		List<Card> extras = hand.getCards();
		extras.removeAll(relevantCards);
		result.addCards(extras);
		return result;
	}

}
