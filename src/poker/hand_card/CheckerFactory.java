package poker.hand_card;

import poker.manager_player.GameType;

/**
 * A CheckerFactory produces Checkers
 *
 */
public class CheckerFactory {
	
	private static final CheckerFactory instance = new CheckerFactory();

	/**
	 * @return a CheckerFactory
	 */
	public static CheckerFactory getInstance() {
		return instance;
	}

	public Checker getChecker(GameType gameType) {
		return new CheckerImpl();	//have others depending on gameType
	}

}
