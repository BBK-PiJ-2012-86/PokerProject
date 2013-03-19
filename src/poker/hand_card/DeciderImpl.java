/**
 * 
 */
package poker.hand_card;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * This Decider returns the following:
 * Better than high card - all cards irrelevant to the poker hand type except the highest if over 7 
 * High card: bottom three - unless within two cards of a flush when it returns non matched suits, or within 
 * one card of a straight when it returns the other card
 */
public class DeciderImpl implements Decider {
	
	@Override
	public List<Card> decide(CheckResult checkResult) {
		ConditionType conditionType = checkResult.getConditionType();
		Hand hand = checkResult.getOrderedHand();
		
		switch(conditionType) {
			case THREE_OF_A_KIND:
				//return lowest leftover, and highest leftover if < 7
				if (hand.getCardAt(3).getRank().compareTo(Rank.SEVEN)<0) {
					return bottom(2, hand);
				} else {
					return bottom(1, hand);
				}
				
			case TWO_PAIR:
				//return leftover card
				return bottom(1, hand);
				
			case PAIR:
				//return lowest 2 leftovers, and highest leftover if < 7
				if (hand.getCardAt(2).getRank().compareTo(Rank.SEVEN)<0) {
					return bottom(3, hand);
				} else {
					return bottom(2, hand);
				}
				
			case HIGH_CARD:
				//if 4 of a straight, return the other
				//else if 3 or more of same suit, return others
				//else return bottom 3
			return doHighCardCase(hand);
								
			default:
				//straight or better
				return new LinkedList<Card>();
		}
	}
	
	private List<Card> bottom(int n, Hand hand) {
		List<Card> result = new LinkedList<Card>();
		for (int i=5-n; i<5; i++) {
			result.add(hand.getCardAt(i));
		}
		return result;
	}

	private List<Card> doHighCardCase(Hand hand) {
		List<Card> cardsToRemove = new LinkedList<Card>();
		
		//4 of a straight if: rank range of bottom 4 or top 4 cards is less than 5
		//ignores low Ace case - but if low cards may be better bet to swap out low cards anyway.
		hand = hand.sortByRank();																	//!!! this will ruin the rest
		int bottom4range = hand.getCardAt(0).getRank().ordinal()-hand.getCardAt(3).getRank().ordinal();
		int top4range = hand.getCardAt(1).getRank().ordinal()-hand.getCardAt(4).getRank().ordinal();
		if (bottom4range<5) {
			cardsToRemove.add(hand.getCardAt(4));
			return cardsToRemove;
		}
		if (top4range<5) {
			cardsToRemove.add(hand.getCardAt(0));
			return cardsToRemove;
		}
						
		//test if close to flush
		Map<Suit, List<Card>> suitMap = Util.suitMap(hand);
		boolean nearFlush = false;
		for (List<Card> list : suitMap.values()) {
			if (list.size()>=3) {
				nearFlush = true;
				break;
			}
		}
		if(nearFlush){
			for (List<Card> list : suitMap.values()) {
				if (list.size()<3) {
					cardsToRemove.addAll(list);
				}
			}
			return cardsToRemove;
		}

		//otherwise return lowest 3 cards
		return bottom(3, hand);
	}
	
	

}
