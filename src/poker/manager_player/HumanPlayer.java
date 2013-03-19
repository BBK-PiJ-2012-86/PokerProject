package poker.manager_player;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import poker.hand_card.Card;

/**
 * A HumanPayer plays poker and needs input to make its card replacement decisions
 *
 */
public class HumanPlayer extends Player {
	
	//@Setter private UserInput userInput;
	private Scanner scanner = new Scanner(System.in);	//for now - think about closing it etc..
	
	public HumanPlayer(String username, GameType gameType){
		super(username, gameType);
		//userInput = new UserInput();
	}
	
	public void receiveCards(List<Card> cards){
		hand.addCards(cards);
		// print out their new hand
		System.out.println("Your hand is:");
		System.out.println(hand);
	}
	
	public int exchangeCards(){
		int maxCardsSwapped = gameType.maxCardsSwapped();
		//displayCards();
		System.out.println("How many cards would you like to swap (max " + maxCardsSwapped + ")?");
		//int swap = userInput.getInteger();
		int swap = scanner.nextInt();
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
		//displayCards();
		return swap;
	}
	
	public Card selectCardsToRemove(){
		System.out.println("Which card would you like to swap (From 1 - 5)?");
		//int swap = userInput.getInteger();
		int swap = scanner.nextInt();
		if(swap > hand.getCards().size() || swap < 1){
			throw new IllegalArgumentException("That is not a valid selection");		//change to just loop and ask again?
		}
		return hand.getCardAt(swap - 1);
	}

	/*public void displayCards(){
		System.out.println("Your hand is:");
		System.out.println(hand);
	}*/

}

