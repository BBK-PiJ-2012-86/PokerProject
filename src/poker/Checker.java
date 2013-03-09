/**
 * 
 */
package poker;

import java.util.Collections;
import java.util.List;

/**
 * @author 86
 *
 */
public class Checker {
	
	private List<CheckResult> results;

	public CheckResult check(Hand hand) {
		//use a ConditionChecker
		
		return Collections.max(results);
		
		
	}
	
	

}
