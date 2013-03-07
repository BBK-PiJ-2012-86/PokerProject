package poker;

import java.util.Iterator;


/*
 * A List Class designed for poker games.
 * 
 * This class differs from an ordinary list in that it iterates from the dealer rather
 * than the beginning of the list.
 * 
 * Operations:
 * add() adds a given object to the end of the list
 * newDealer() increments the position of the dealer
 * iterator() iterates through the items from the dealer. Items (players) can be removed
 * from the list using this function.
 */

public class PokerList<T> implements Iterable<T> {
	
	private int numItems;
	private T[] players;
	private int dealer;
	private static final int INIT_LENGTH = 10;
	private int index;
	
	@SuppressWarnings("unchecked")
	public PokerList(){
		
		players = (T[]) new Object[INIT_LENGTH];
		dealer = 0;
		numItems = 0;
	}
	
	public void add(T player){
		
		if(numItems < players.length){
			players[numItems] = player;
			numItems++;
		}else{
			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Object[players.length * 2];
			for(int i = 0; i < players.length; i++){
				temp[i] = players[i];
			}
			temp[numItems] = player;
			players = temp;
			numItems++;
		}
		
	}
	
	public void newDealer(){
		if(dealer == numItems){
			dealer = 0;
		}else{
			dealer++;
		}
	}

	public void setIndex(){
		if(dealer == numItems){
			index = 0;
		}else{
			index = dealer++;
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		
		setIndex();
		
		Iterator<T> iterator = new Iterator<T>(){
			
			@Override
			public boolean hasNext(){
				return (index != dealer);
			}
			
			@Override
			public T next(){
				if(index == numItems){
					index = 0;
					return players[numItems];
				}else{
					index++;
					return players[index--];
				}
			}

			@Override
			public void remove() {
				@SuppressWarnings("unchecked")
				T[] temp = (T[]) new Object[players.length];
				if(index == dealer){
					for(int i = 0; i < index - 1; i++){
						temp[i] = players[i];
					}
					for(int i = index++; i < numItems; i++){
						temp[i] = players[i];
					}
					players = temp;
				}else if(dealer < index){
					for(int i = 0; i < index - 1; i++){
						temp[i] = players[i];
					}
					for(int i = index++; i < numItems; i++){
						temp[i] = players[i];
					}
					players = temp;
				}else{
					for(int i = 0; i < index; i++){
						temp[i] = players[i];
					}
					for(int i = index++; i < numItems; i++){
						temp[i] = players[i];
					}
					dealer--;
					players = temp;
				}
			}
		};
		return iterator;
	}
	
}
