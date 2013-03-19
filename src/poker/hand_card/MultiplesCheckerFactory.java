package poker.hand_card;


/**
 * A MultiplesCheckFactory allows for production of MultiplesCheckers for checking multiples based hand conditions
 *
 */
public class MultiplesCheckerFactory {
	
	private static final MultiplesCheckerFactory instance = new MultiplesCheckerFactory();
	
	/**
	 * @return a MultiplesCheckerFactory
	 */
	public static MultiplesCheckerFactory getInstance() {
		return instance;
	}
	
	private MultiplesChecker mock = null;

	private MultiplesCheckerFactory() {
		//unused
	}
	
	public void setMockChecker() {
		this.mock = mock;
	}
	public void setNotMockChecker() {
		this.mock = null;
	}
	/**
	 * @return a MultiplesChecker for checking multiples based hand conditions
	 */
	public MultiplesChecker getMultiplesChecker() {
		if(mock != null) {
			return mock;
		}
		else {
			return new MultiplesChecker();
		}
	}

}
