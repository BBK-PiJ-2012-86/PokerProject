package poker.hand_card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;


public class Deck {

	@Getter private final List<Card> deck;
	
	public Deck(int size){
		deck = new ArrayList<Card>();
		for(int i = 0; i < size; i++){
			for(Suit suit: Suit.values()){
				for(Rank rank: Rank.values()){
					Card card = new Card(rank, suit);
					deck.add(card);
				}
				
			}
		}
	}
		
	public void shuffleDeck(){
		Collections.shuffle(deck);
	}
	
	
	
	
	/*public List<Card> getDeck(){
		return deck;
	}*/
}
