/**
 * 
 */
package poker;

/**
 * @author 86
 *
 */
public class ConditionCheckerFactory {
	
		private static ConditionCheckerFactory ccfactory = new ConditionCheckerFactory();

		public static ConditionCheckerFactory getInstance() {
			return ccfactory;
		}
		
		public ConditionChecker getConditionCheckerFor(ConditionType conditionType) {
			switch(conditionType) {
			case StraightFlush:
				return null;
			case FourOfAKind:
				return null;
			case FullHouse:
				return null;
			case Flush:
				return null;
			case Straight:
				return null;
			case ThreeOfAKind:
				return null;
			case TwoPair:
				return null;
			case Pair:
				return null;
			case HighCard:
				return null;
			}
		}
}
