package Factories;

import poker.Deck;

public class DeckFactory {

	private int defaultSize = 1;
	private int size;
	
	public DeckFactory(){
		size = defaultSize;
	}
	
	public DeckFactory(int size){
		this.size = size;
	}
	
	public Deck getDeck(){
		Deck deck = new Deck(size);
		return deck;
	}
	
}
