package poker.manager_player;

import java.util.LinkedList;
import java.util.List;

import poker.hand_card.Card;
import poker.hand_card.Deck;
import poker.hand_card.DeckFactory;

public class CardDealer {
	private Deck deck = null;

	public void deal(CircularLinkedList<Player> players, int numCards){
		deck = DeckFactory.getDeckFactory().getDeck();
		deck.shuffleDeck();
		for(Player player: players){
			player.receiveCards(deck.dealCards(numCards));
		}
	}
	
	public void playersChangeCards(CircularLinkedList<Player> players){
		List<Card> cards = new LinkedList<Card>();
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
}
