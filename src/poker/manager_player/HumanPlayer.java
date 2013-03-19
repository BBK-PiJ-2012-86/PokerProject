package poker.manager_player;


import java.util.ArrayList;
import java.util.List;

import lombok.Setter;


import poker.hand_card.Card;
import poker.ui.*;

/**
 * A HumanPayer plays poker and needs input to make its card replacement decisions
 *
 */
public class HumanPlayer extends Player {
	
	@Setter private UserInput userInput;
	
	public HumanPlayer(String username, GameType gameType){
		super(username, gameType);
		userInput = new UserInput();
	}
	
	public int exchangeCards(){
		int maxCardsSwapped = gameType.maxCardsSwapped();
		displayCards();
		System.out.println("How many cards would you like to swap (max " + maxCardsSwapped + ")?");
		int swap = userInput.getInteger();
		if(swap > maxCardsSwapped){
			throw new IllegalArgumentException("Maximum number of cards is " + maxCardsSwapped);	//change to just loop and ask again?
		}
		if(swap > 0){
			List<Card> cards = new ArrayList<Card>();
			for(int i = 0; i < swap; i++){
				cards.add(selectCardsToRemove());
			}
			removeCardsFromHand(cards);
		}
		return swap;
	}
	
	public Card selectCardsToRemove(){
		System.out.println("Which card would you like to swap (From 1 - 5)?");
		int swap = userInput.getInteger();
		if(swap > hand.getCards().size() || swap < 1){
			throw new IllegalArgumentException("That is not a valid selection");		//change to just loop and ask again?
		}
		return hand.getCardAt(swap - 1);
	}

	public void displayCards(){
		System.out.println("Your hand is:");
		System.out.println(hand);
	}

}

