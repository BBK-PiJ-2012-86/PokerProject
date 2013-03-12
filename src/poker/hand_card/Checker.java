package poker.hand_card;


public interface Checker {

	/**
	 * @param hand
	 * @return
	 */
	CheckResult check(Hand hand);

}