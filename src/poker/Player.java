package poker;

import java.util.ArrayList;

public abstract class Player {
	
	protected GameType gameType;
	protected HandImpl hand;
	protected String username;
	protected Checker checker;
	
	
	public Player(String username, GameType gameType) {
		this.gameType = gameType;
		this.username = username;
		this.checker = CheckerFactory.getInstance(gameType).getChecker();
	}
	
	public CheckResult check() {
		return checker.check(hand);
	}
		
		/*
		 * 
		 * Throws IllegalArgumentExcpetion if the player does not enter a valid number of cards
		 * to swap.
		 */
	
	public abstract int swapCards();
	
	public abstract ArrayList<Integer> swapOneCard();
	
	public abstract ArrayList<Integer> swapTwoCards();
	
	public abstract ArrayList<Integer> swapThreeCards();
	
	public Hand getHand(){
		return hand;
	}
	
	public abstract ArrayList<Integer> exchangeCards();
		
	public void changeCards(ArrayList<Integer> cardNumbers, ArrayList<Card> replacements){
		for(int i = 0; i < cardNumbers.size(); i++){
			hand.getCards().get(i) = replacements.get(i);
		}
	}

}
