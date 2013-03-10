package poker;

public class CheckerFactory {
	
	private static final CheckerFactory instance = new CheckerFactory();	//others depending on game type

	public static CheckerFactory getInstance(GameType game) {
		return instance;
	}

	public Checker getChecker(HandImpl hand) {
		return new CheckerImpl(hand);	//consider other checker options?
	}

}
