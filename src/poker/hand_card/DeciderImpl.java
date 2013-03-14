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
 * High card: bottom three - unless within two cards of a flush when it returns non matched suits
 */
public class DeciderImpl implements Decider {	//a semi reasonable decider (?)
	
	@Override
	public List<Card> decide(CheckResult checkResult) {
		ConditionType conditionType = checkResult.getConditionType();
		Hand hand = checkResult.getOrderedHand();
		
		switch(conditionType) {
			case ThreeOfAKind:
				//return lowest leftover, and highest leftover if < 7
				if (hand.getCardAt(3).getRank().compareTo(Rank.Seven)<0) {
					return bottom(2, hand);
				} else {
					return bottom(1, hand);
				}
				
			case TwoPair:
				//return leftover card
				return bottom(1, hand);
				
			case Pair:
				//return lowest 2 leftovers, and highest leftover if < 7
				if (hand.getCardAt(2).getRank().compareTo(Rank.Seven)<0) {
					return bottom(3, hand);
				} else {
					return bottom(2, hand);
				}
				
			case HighCard:
				//if 3 or more of same suit, return others, else return bottom 3
				Map<Suit, List<Card>> suitMap = Util.suitMap(hand);
				for (List<Card> list : suitMap.values()) {
					if (list.size()>=3) {
						//remove others
						hand.removeCards(list);
						return hand.getCards();
					}
				}
				return bottom(3, hand );
								
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


}
