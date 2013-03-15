package poker.manager_player;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import poker.hand_card.Card;
import poker.hand_card.Deck;
import poker.hand_card.DeckFactory;



/**
 * This is the manager class for the poker game
 *
 */
public class GameManagerImpl {
	
	private CircularArrayList<Player> players;
	private Deck deck;
	//private final GameType type;	//TODO: decide where this is used
	
	public GameManagerImpl(GameType type, CircularArrayList<Player> players) {
		//this.type = type;
		this.deck = null;
		this.players = players;
		for(Player player: players){
			player.changeGameType(type);
		}
	}
	
	public void deal(){
		deck = DeckFactory.getDeckFactory().getDeck();
		for(Player player: players){
			player.recieveCards(deck.dealCards(5));
		}
	}
	
	public void playRound(){
		deal();
		playersChangeCards();
		evaluateWinner();
		deletePlayerCards();
	}
	
	public void playersChangeCards(){
		for(Player player: players){
			int cardsToSwap = player.exchangeCards();
			if(cardsToSwap > 0){
				List<Card> cards = new ArrayList<Card>();
				for(int i = 0; i < cardsToSwap; i++){
					cards.add(deck.getCards().get(0));
					deck.getCards().remove(0);
				}
				player.recieveCards(cards);
			}
		}
	}
	
	public void evaluateWinner(){
		List<Player> tempList = new ArrayList<Player>();
		for(Player player: players){
			tempList.add(player);
		}
		Comparator<Player> c = players.getTheDealer().getCheckResultRanking();
		Collections.sort(tempList, c);
		System.out.println("The winner is " + tempList.get(0));
		System.out.println("Their hand was " + tempList.get(0).getHand().toString());
	}
	
	public void deletePlayerCards(){
		for(Player player: players){
			player.removeCards();
		}
	}
	
}
