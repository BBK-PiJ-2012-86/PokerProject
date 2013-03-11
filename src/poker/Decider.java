package poker;

import java.util.List;

public interface Decider {

	/**
	 * @param checkResult
	 * @return
	 */
	public List<Card> decide(CheckResult checkResult);

}