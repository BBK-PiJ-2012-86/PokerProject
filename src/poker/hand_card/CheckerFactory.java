package poker.hand_card;

import poker.manager_player.GameType;

/**
 * A CheckerFactory produces Checkers
 *
 */
public class CheckerFactory {
	
	private static final CheckerFactory INSTANCE = new CheckerFactory();

	/**
	 * @return a CheckerFactory
	 */
	public static CheckerFactory getInstance() {
		return INSTANCE;
	}

	public Checker getChecker(GameType gameType) {
		return new CheckerImpl();	//set up to have others depending on gameType
	}

}
