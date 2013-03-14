package poker.hand_card;

import poker.manager_player.GameType;

/**
 * A CheckerFactory produces Checkers
 *
 */
public class CheckerFactory {
	
	private static final CheckerFactory instance = new CheckerFactory();	//others depending on game type

	/**
	 * @param gameType the type of game to be played
	 * @return a CheckerFactory
	 */
	public static CheckerFactory getInstance(GameType gameType) {	//NB doesn't depend on gameType atm
		return instance;
	}

	public Checker getChecker() {
		return new CheckerImpl();	//consider other checker options?
	}

}
