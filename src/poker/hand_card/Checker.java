package poker.hand_card;


/**
 * A Checker allows checking of cards for the best poker hand
 *
 */
public interface Checker {

	/**
	 * @param hand the hand to be checked
	 * @return the result corresponding to the best available poker hand
	 */
	CheckResult check(Hand hand);

}