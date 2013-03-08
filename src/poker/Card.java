package poker;

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
	
	public Card(int rank, int suit){
		rankInteger = rank;
		suitInteger = suit;
		setCardRank();
		setCardSuit();
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
	
	public void setCardRank(){
		Rank temp;
		switch(rankInteger){
		case 2: temp = Rank.Two;
		rank = temp;
		break;
		case 3: temp = Rank.Three;
		rank = temp;
		break;
		case 4: temp = Rank.Four;
		rank = temp;
		break;
		case 5: temp = Rank.Five;
		rank = temp;
		break;
		case 6: temp = Rank.Six;
		rank = temp;
		break;
		case 7: temp = Rank.Seven;
		rank = temp;
		break;
		case 8: temp = Rank.Eight;
		rank = temp;
		break;
		case 9: temp = Rank.Nine;
		rank = temp;
		break;
		case 10: temp = Rank.Ten;
		rank = temp;
		break;
		case 11: temp = Rank.Jack;
		rank = temp;
		break;
		case 12: temp = Rank.Queen;
		rank = temp;
		break;
		case 13: temp = Rank.King;
		rank = temp;
		break;
		case 14: temp = Rank.Ace;
		rank = temp;
		break;
		default: throw new IllegalArgumentException();
		}
	}
	
	public void setCardSuit(){
		Suit temp;
		switch (suitInteger){
		case 1: temp = Suit.Spades;
		suit = temp;
		break;
		case 2: temp = Suit.Clubs;
		suit = temp;
		break;
		case 3: temp = Suit.Hearts;
		suit = temp;
		break;
		case 4: temp = suit.Diamonds;
		suit = temp;
		break;
		default: throw new IllegalArgumentException();
		}
	}

}
