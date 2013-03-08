package poker;

import java.util.Vector;

public class Card {
	
	private Rank rank;
	private Suit suit;
	
	public Card(Rank rank, Suit suit){
		this.rank = rank;
		this.suit = suit;
	}
	
	public Rank getRank(){
		return rank;
	}
	
	public Suit getSuit(){
		return suit;
	}
	
	@Override
	public String toString(){
		String result = rank.toString() + " of " + suit.toString();
		return result;
	}
	
	/*
	 *Returns a Vector. The integer in place 0 defines the card's rank. The integer in place 1
	 *defines the card's suit.
	 *
	 * @throws an IllegalStateException if the rank or the suit is null.
	 */
	public Vector<Integer> getCardValue(){
		Vector<Integer> cardValue = new Vector<Integer>();
		switch(rank){
		case Two: cardValue.add(2);
		break;
		case Three: cardValue.add(3);
		break;
		case Four: cardValue.add(4);
		break;
		case Five: cardValue.add(5);
		break;
		case Six: cardValue.add(6);
		break;
		case Seven: cardValue.add(7);
		break;
		case Eight: cardValue.add(8);
		break;
		case Nine: cardValue.add(9);
		break;
		case Ten: cardValue.add(10);
		break;
		case Jack: cardValue.add(11);
		break;
		case Queen: cardValue.add(12);
		break;
		case King: cardValue.add(13);
		break;
		case Ace: cardValue.add(14);
		break;
		default: throw new IllegalStateException();
		}
		switch(suit){
		case Spades: cardValue.add(1);
		break;
		case Clubs: cardValue.add(2);
		break;
		case Hearts: cardValue.add(3);
		break;
		case Diamonds: cardValue.add(4);
		break;
		default: throw new IllegalStateException();
		}
		return cardValue;
	}
}
