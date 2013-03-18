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
	
	public HumanPlayer(String username, GameType gameType){
		super(username, gameType);
	}
	
	public int exchangeCards(){
		System.out.println("How many cards would you like to swap (max 3)?");
		Scanner keyBoard = new Scanner(System.in);
		int swap = keyBoard.nextInt();
		keyBoard.close();
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
		Scanner keyBoard = new Scanner(System.in);
		int swap = keyBoard.nextInt();
		keyBoard.close();
		if(swap > hand.getCards().size() || swap < 1){
			throw new IllegalArgumentException("That is not a valid selection");
		}
		return hand.getCardAt(swap - 1);
	}

	public void displayCards(){
		System.out.println("Your hand is:");
		for(Card card: hand){
			System.out.println(card.prettyPrint());
		}
	}

}

