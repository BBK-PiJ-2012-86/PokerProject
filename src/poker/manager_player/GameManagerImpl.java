package poker.manager_player;


import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


/**
 * This is the manager class for the poker game
 *
 */
public class GameManagerImpl implements GameManager {
	private CircularLinkedList<Player> players = new CircularLinkedListImpl<Player>();
	private CardDealer cardDealer;
	private GameType gameType;
	private GameListener listener;
	/*@Setter private boolean fixed = false;
	@Setter private List<List<Card>> handCards = null;
	@Setter private List<List<Card>> swapCards = null;*/

	public GameManagerImpl(GameType gameType, GameListener listener) {
		this.gameType = gameType;
		this.listener = listener;
		cardDealer = CardDealerFactory.getInstance().getCardDealer();
	}

	@Override
	public void addComputerPlayer(AiType AI){
		PlayerFactory playerFactory = PlayerFactoryImpl.getInstance();
		Player p = playerFactory.createComputerPlayer(AI, idGenerator.getId());
		addPlayer(p);
	}
	
	@Override
	public void addHumanPlayer(String username){
		PlayerFactory playerFactory = PlayerFactoryImpl.getInstance();
		Player p = playerFactory.createHumanPlayer(username);
		addPlayer(p);
	}
	
	private void addPlayer(Player player){
		player.changeGameType(gameType);
		players.add(player);
	}

	@Override
	public void playRound(){
		cardDealer.deal(players, gameType.numCards());
		cardDealer.playersChangeCards(players);
		listener.announceWinner(evaluateWinner());
		deletePlayerCards();
	}
	
	/*private void deal(){
		if (fixed) {
			int i = 0;
			for(Player player: players){
				player.receiveCards(handCards.get(i));
				i++;
			}
		} else {
			int numCards = gameType.numCards();
			deck = DeckFactory.getDeckFactory().getDeck();
			deck.shuffleDeck();
			for(Player player: players){
				player.receiveCards(deck.dealCards(numCards));
			}
		}
	}*/

	/*private void playersChangeCards(){
		List<Card> cards = new LinkedList<Card>();
		if (fixed) {
			int playerNumber = 0;
			for(Player player: players){
				int cardsToSwap = player.exchangeCards();
				if(cardsToSwap > 0){
					cards = swapCards.get(playerNumber);
					player.receiveCards(cards);
				}
				playerNumber++;
			}
		} else {
			for(Player player: players){
				int cardsToSwap = player.exchangeCards();
				if(cardsToSwap > 0){
					for(int i = 0; i < cardsToSwap; i++){
						cards.add(deck.getCards().get(0));
						deck.getCards().remove(0);
					}
					player.receiveCards(cards);
				}
				
			}
		}
	}*/

	private List<Player> evaluateWinner(){
		List<Player> result = new LinkedList<Player>();
		Comparator<Player> c = Player.getCheckResultRanking();
		Player winner = Collections.max(players, c);
		for (Player player : players) {
			if (c.compare(winner,player)==0) {
				result.add(player);
			}
		}
		return result;
	}

	private void deletePlayerCards(){
		for(Player player: players){
			player.removeCards();
		}
	}
	
	private class GameUniqueIdGenerator {
		private int id = 1;
		public int getId() {
			return id++;
		}
	}
	
	private final GameUniqueIdGenerator idGenerator = new GameUniqueIdGenerator();
}
