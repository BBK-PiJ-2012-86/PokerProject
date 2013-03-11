package poker;

import java.util.ArrayList;

public abstract class Player {
	
	protected CheckerFactory checkerFactory;
	protected HandImpl hand;
	protected String username;
	
	public Player(String username, CheckerFactory checkerFactory) {
		this.checkerFactory = checkerFactory;
		this.username = username;
	}
	
	public CheckResult check() {
		Checker checker = checkerFactory.getChecker();
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
