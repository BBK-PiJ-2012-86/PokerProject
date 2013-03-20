package poker.hand_card;

import poker.manager_player.AiType;
import poker.manager_player.GameType;

/**
 * A DeciderFactory returns a Decider dependent on the game type and AI type (??)
 *
 */
public class DeciderFactory {
	
	private static final DeciderFactory INSTANCE = new DeciderFactory();

	/**
	 * @return a DeciderFactory
	 */
	public static DeciderFactory getInstance() {
		return INSTANCE;
	}

	public Decider getDecider(GameType gameType, AiType aiType) {
		return new DeciderImpl();	//set up to have others depending on gameType / aiType
	}
	

}
