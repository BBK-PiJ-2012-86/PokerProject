package poker;

public class Card implements Comparable<Card>{
	// a change again again! again ad infinitum?
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	public int compareTo(Card other) {
		return this.getRank().compareTo(other.getRank());
	}
	
	

}
