package poker;

public interface Checker {

	/**
	 * @param hand
	 * @return
	 */
	CheckResult check(Hand hand);

}