/**
 * 
 */
package poker.hand_card;


 //Some re-usable pre-made cards to use in testing.	!!Think about mocks for these too..

public class TestCards {
	
	public static final Card ACE_SPADE = new Card(Rank.Ace, Suit.Spades);
	public static final Card KING_SPADE = new Card(Rank.King, Suit.Spades);
	public static final Card QUEEN_SPADE = new Card(Rank.Queen, Suit.Spades);
	public static final Card JACK_SPADE = new Card(Rank.Jack, Suit.Spades);
	public static final Card TEN_SPADE = new Card(Rank.Ten, Suit.Spades);
	public static final Card NINE_SPADE = new Card(Rank.Nine, Suit.Spades);
	
	public static final Card JACK_HEART = new Card(Rank.Jack, Suit.Hearts);
	public static final Card JACK_CLUB = new Card(Rank.Jack, Suit.Clubs);
	public static final Card JACK_DIAMOND = new Card(Rank.Jack, Suit.Diamonds);
	
	public static final Card TEN_CLUB = new Card(Rank.Ten, Suit.Clubs);
	public static final Card TEN_DIAMOND = new Card(Rank.Ten, Suit.Diamonds);
	
	public static final Card SIX_CLUB = new Card(Rank.Six, Suit.Clubs);
	public static final Card FIVE_CLUB = new Card(Rank.Five, Suit.Clubs);
	public static final Card FOUR_CLUB = new Card(Rank.Four, Suit.Clubs);
	public static final Card THREE_CLUB = new Card(Rank.Three, Suit.Clubs);
	public static final Card TWO_CLUB = new Card(Rank.Two, Suit.Clubs);

}
