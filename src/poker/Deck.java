package poker;

import java.util.ArrayList;

public class Deck {

		private ArrayList<Card> deck;
		
		public Deck(int size){
			for(int i = 0; i < size; i++){
				for(Suit suit: Suit.values()){
					for(Rank rank: Rank.values()){
						Card card = new Card(rank, suit);
						deck.add(card);
					}
					
				}
			}
		}
		
		public ArrayList<Card> getDeck(){
			return deck;
		}
		
		public void shuffleDeck(){
			deck.shuffle();
		}
}
