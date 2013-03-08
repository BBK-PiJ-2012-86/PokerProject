package poker;

import java.util.Vector;

public class Card {
	
	private Rank rank;
	private Suit suit;
	private int rankInteger;
	private int suitInteger;
	
	public Card(Rank rank, Suit suit){
		this.rank = rank;
		this.suit = suit;
		setRankInteger();
		setRankInteger();
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
	
	public void setRankInteger(){
		switch(rank){
		case Two: rankInteger = 2;
		break;
		case Three: rankInteger = 3;
		break;
		case Four: rankInteger = 4;
		break;
		case Five: rankInteger = 5;
		break;
		case Six: rankInteger = 6;
		break;
		case Seven: rankInteger = 7;
		break;
		case Eight: rankInteger = 8;
		break;
		case Nine: rankInteger = 9;
		break;
		case Ten: rankInteger = 10;
		break;
		case Jack: rankInteger = 11;
		break;
		case Queen: rankInteger = 12;
		break;
		case King: rankInteger = 13;
		break;
		case Ace: rankInteger = 14;
		break;
		default: throw new IllegalStateException();
		}
	}
	
	public void setSuitInteger(){
		switch(suit){
		case Spades: suitInteger = 1;
		break;
		case Clubs: suitInteger = 2;
		break;
		case Hearts: suitInteger = 3;
		break;
		case Diamonds: suitInteger = 4;
		break;
		default: throw new IllegalStateException();
		}
	}

}
