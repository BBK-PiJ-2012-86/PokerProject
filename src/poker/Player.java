package poker;

import java.util.Comparator;
import java.util.List;

public abstract class Player implements Comparator <Player> {
	
	protected GameType gameType;
	protected Hand hand;
	protected String username;
	protected Checker checker;
	
	
	public Player(String username, GameType gameType) {
		this.gameType = gameType;
		this.username = username;
		this.checker = CheckerFactory.getInstance(gameType).getChecker();
		Hand hand = new HandImpl();
	}
	
	public void recieveCards(List<Card> cards){
		for(Card card: cards){
			hand.getCards().add(card);
		}
	}
	
	public abstract int exchangeCards();
	
	public CheckResult check() {
		return checker.check(hand);
	}
	
	public abstract void removeCardFromHand(List<Card> cards);
	
	public Hand getHand(){
		return hand;
	}

	public void setGameType(GameType type) {
		this.gameType = type;
		checker = CheckerFactory.getInstance(gameType).getChecker();
	}
	
	@Override
	public int compare(Player p1, Player p2){
		CheckResult p1Result = p1.check();
		CheckResult p2Result = p2.check();
		return p1Result.compareTo(p2Result);
	}
	
}
