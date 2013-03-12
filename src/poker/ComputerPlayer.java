package poker;

import java.util.List;


public class ComputerPlayer extends Player {
	
	private Decider computerAI;

	public ComputerPlayer(String username, GameType gameType){
		super(username, gameType);
		DeciderFactory d = new DeciderFactory();
		computerAI = d.getDecider(gameType);
	}
	
	@Override
	public int compare(Player p1, Player p2){
		CheckResult p1Result = p1.check();
		CheckResult p2Result = p2.check();
		return p1Result.compareTo(p2Result);
	} 

	@Override
	public int exchangeCards() {
		List<Card> cards = computerAI.decide(check());
		removeCardsFromHand(cards);
		return cards.size();
	}

}
