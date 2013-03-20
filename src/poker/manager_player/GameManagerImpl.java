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
	private GameListener listener;

	public GameManagerImpl(GameType gameType, GameListener listener) {
		this.gameType = gameType;
		this.listener = listener;
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
			player.receiveCards(deck.dealCards(numCards));
		}
	}

	@Override
	public void playRound(){
		deal();
		playersChangeCards();
		listener.announceWinner(evaluateWinner());	//odd
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
				player.receiveCards(cards);
			}
		}
	}

	private List<Player> evaluateWinner(){
		List<Player> tempList = new ArrayList<Player>();
		for(Player player: players){
			tempList.add(player);
		}
		Comparator<Player> c = players.getTheDealer().getCheckResultRanking();
		Collections.sort(tempList, c);
		List<Player> result = new ArrayList<Player>();
		result.add(tempList.get(tempList.size() - 1));
		for(int i = tempList.size() - 2; i >= 0; i--){
			if(c.compare(tempList.get(tempList.size() - 1), tempList.get(i)) == 0){
				result.add(tempList.get(i));
			}
		}
		return result;
	}

	private void deletePlayerCards(){
		for(Player player: players){
			player.removeCards();
		}
	}

	public static void main(String [] args){
		GameManager game = new GameManagerImpl(GameType.FIVE_CARD_DRAW, new GameConsoleListener());
		((GameManagerImpl) game).launch();
	}

	private void launch(){
		PlayerFactory pfactory = new PlayerFactoryImpl();
		players.add(pfactory.createHumanPlayer("Ted", GameType.FIVE_CARD_DRAW));
		players.add(pfactory.createComputerPlayer(GameType.FIVE_CARD_DRAW, AiType.NORMAL));
		for(int i = 0; i < 2; i++){
			System.out.println("NEW GAME! Have fun :)");
			playRound();
			System.out.println();
		}
	}

}
