/**
 * 
 */
package poker;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Ruth
 *
 */
public class DeciderImpl implements Decider {	//basic first decider
	
	@Override
	public List<Card> decide(CheckResult checkResult) {
		List<Card> result = new LinkedList<Card>();
		ConditionType conditionType = checkResult.getConditionType();
		if (conditionType.compareTo(ConditionType.Straight)<0){
			Hand hand = checkResult.getSupportingCards();
			Card possCard;
			switch(conditionType) {
			case ThreeOfAKind:
				//return lowest leftover, and highest leftover if < 7
				possCard = hand.getCardAt(3);
				if (possCard.getRank().compareTo(Rank.Seven)>0) {
					result.add(possCard);
				}
				result.add(hand.getCardAt(4));
				break;
				
			case TwoPair:
				//return leftover card
				result.add(hand.getCardAt(4));
				break;
				
			case Pair:
				//return lowest 2 leftovers, and highest leftover if < 7
				possCard = hand.getCardAt(2);
				if (possCard.getRank().compareTo(Rank.Seven)>0) {
					result.add(possCard);
				}
				result.add(hand.getCardAt(3));
				result.add(hand.getCardAt(4));
				break;
				
			case HighCard:
				//keep 3 if of same suit, else return bottom 3
				Map<Suit, List<Card>> suitMap = Utilities.suitMap(hand);
				for (List<Card> list : suitMap.values()) {
					if (list.size()==3) {
						//remove others
					}
				}
			default:
				//should never come here
			}
		}
		// else do nothing  - we don't want to remove any cards from such hands
		return result;
	}

}
