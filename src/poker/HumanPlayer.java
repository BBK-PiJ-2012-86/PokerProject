package poker;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String username, GameType gameType){
		super(username, gameType);
	}
	
	public int exchangeCards(){
		System.out.println("How many cards would you like to swap (max 3)?");
		String str = System.console().readLine();
		int swap = Integer.parseInt(str);
		if(swap > 0){
			List<Card> cards = new ArrayList<Card>();
			for(int i = 0; i < swap; i++){
				cards.add(selectCardsToRemove());
			}
			removeCardFromHand(cards);
		}
		return swap;
	}
	
	public Card selectCardsToRemove(){
		System.out.println("Which card would you like to swap (From 1 - 5)?");
		String str = System.console().readLine();
		int swap = Integer.parseInt(str);
		if(swap > hand.getCards().size() || swap < 1){
			throw new IllegalArgumentException("That is not a valid selection");
		}
		return hand.getCardAt(swap - 1);
	}
	
	public void removeCardFromHand(List<Card> cards){
		for(Card card: cards){
			hand.getCards().remove(card);
		}
	}

	public void displayCards(){
		System.out.println("Your hand is:");
		for(Card card: hand){
			System.out.println(card.toString());
		}
	}

	@Override
	public int compare(Player o1, Player o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
