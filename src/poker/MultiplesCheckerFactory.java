package poker;

public class MultiplesCheckerFactory {
	
	private static final MultiplesCheckerFactory instance = new MultiplesCheckerFactory();
	
	public static MultiplesCheckerFactory getInstance() {
		return instance;
	}
	
	private MultiplesChecker mock = null;

	private MultiplesCheckerFactory() {
		//unused
	}
	
	public void setMockChecker(MultiplesChecker mock) {
		this.mock = mock;
	}
	
	public MultiplesChecker getMultiplesChecker(Hand hand) {
		if(mock != null) {
			return mock;
		}
		else {
			return new MultiplesChecker(hand);
		}
	}

}
