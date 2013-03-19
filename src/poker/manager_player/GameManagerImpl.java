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
public class GameManagerImpl implements GameManager {

	private CircularArrayList<Player> players = new CircularArrayList<Player>();
	private Deck deck = null;
	private GameType gameType;

	public GameManagerImpl(GameType gameType) {
		this.gameType = gameType;
	}

	@Override
	public void addPlayer(Player player){
		player.changeGameType(gameType);
		players.add(player);
	}

	private void deal(){
		int numCards = gameType.numCards();
		deck = DeckFactory.getDeckFactory().getDeck();
		deck.shuffleDeck();
		for(Player player: players){
			player.recieveCards(deck.dealCards(numCards));
		}
	}

	@Override
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

	private void evaluateWinner(){
		List<Player> tempList = new ArrayList<Player>();
		for(Player player: players){
			tempList.add(player);
		}
		Comparator<Player> c = players.getTheDealer().getCheckResultRanking();
		Collections.sort(tempList, c);
		System.out.println("The winner is " + tempList.get(0));
		System.out.println("Their hand was " + tempList.get(0).getHand().toString());
	}

	private void deletePlayerCards(){
		for(Player player: players){
			player.removeCards();
		}
	}

	public static void main(String [] args){
		GameManager game = new GameManagerImpl(GameType.FIVE_CARD_DRAW);
		((GameManagerImpl) game).launch();
	}
	
	private void launch(){
		PlayerFactory pfactory = new PlayerFactoryImpl();
		players.add(pfactory.createHumanPlayer("Ted", GameType.FIVE_CARD_DRAW));
		players.add(pfactory.createComputerPlayer(GameType.FIVE_CARD_DRAW));
		//for(int i = 0; i < 5; i++){
			playRound();
		//}
	}

}
