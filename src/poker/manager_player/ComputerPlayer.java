package poker.manager_player;


import java.util.List;

import poker.hand_card.Card;
import poker.hand_card.Decider;
import poker.hand_card.DeciderFactory;


/**
 * A ComputerPlayer plays poker with a Decider to make its card replacement decisions
 *
 */
public class ComputerPlayer extends Player {
	
	private AiType aiType;
	private Decider computerAI;

	public ComputerPlayer(String username, GameType gameType, AiType aiType){
		super(username, gameType);
		this.aiType = aiType;
		DeciderFactory d = DeciderFactory.getInstance();
		computerAI = d.getDecider(gameType, aiType);
	}
	
	@Override
	public int exchangeCards() {
		List<Card> cards = computerAI.decide(check());
		removeCardsFromHand(cards);
		return cards.size();
	}
	
	public void receiveCards(List<Card> cards){
		hand.addCards(cards);
	}
	
	@Override
	public synchronized void changeGameType(GameType gameType) {
		super.changeGameType(gameType);
		DeciderFactory d = DeciderFactory.getInstance();
		computerAI = d.getDecider(gameType, aiType);
	}
	
}
