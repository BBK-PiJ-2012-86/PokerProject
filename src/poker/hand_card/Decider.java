package poker.hand_card;


import java.util.List;

/**
 * A Decider is used by a computer player to chose which cards to swap
 *
 */
public interface Decider {

	/**
	 * @param checkResult the result of checking the currently held cards for best poker hand
	 * @return a list of the cards to swap
	 */
	public List<Card> decide(CheckResult checkResult);

}