package poker.manager_player;

import java.util.Comparator;
import java.util.List;

import lombok.Getter;
import poker.hand_card.Card;
import poker.hand_card.CheckResult;
import poker.hand_card.Checker;
import poker.hand_card.CheckerFactory;
import poker.hand_card.Hand;
import poker.hand_card.HandImpl;



public abstract class Player {
	
	protected GameType gameType;
	@Getter protected Hand hand;
	@Getter protected final String username;
	protected Checker checker;		//Problem: IS2_INCONSISTENT_SYNC ... ?? not sure
	
	public Player(String username, GameType gameType) {
		this.gameType = gameType;
		this.username = username;
		this.checker = CheckerFactory.getInstance().getChecker(gameType);
		this.hand = new HandImpl();
	}

	
	public synchronized void removeCards(){
		hand.clearHand();
	}
	
	public abstract void receiveCards(List<Card> cards);
	
	public abstract int exchangeCards();
	
	public CheckResult check() {
		return checker.check(hand);
	}
	
	public synchronized void changeGameType(GameType gameType) {
		this.gameType = gameType;
		checker = CheckerFactory.getInstance().getChecker(gameType);
	}
	
	
	public synchronized void removeCardsFromHand(List<Card> cards){
		hand.removeCards(cards);
	}

	private static final Comparator<Player> HAND_RANKING = new Comparator<Player>(){
		
		@Override
		public int compare(Player p1, Player p2){
			CheckResult p1Result = p1.check();
			CheckResult p2Result = p2.check();
			Comparator<CheckResult> comparator = CheckResult.getComparator();
			return comparator.compare(p1Result, p2Result);
		}
	};
	
	public static Comparator<Player> getCheckResultRanking(){
		return HAND_RANKING; 
	}

}
