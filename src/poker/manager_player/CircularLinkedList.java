package poker.manager_player;

import java.util.Collection;
import java.util.Iterator;

/**
 * @param <T>
 */
public interface CircularLinkedList<T> extends Collection<T> {

	/**
	 * @return true if the list has no elements, else false
	 */
	boolean isEmpty();

	/**
	 * @param t the element to be added
	 * @return true
	 */
	boolean add(T t);

	/**
	 * @param o the element to be removed
	 * @return true if successful, else false
	 */
	boolean remove(Object o);

	/**
	 * Moves the head of the list around
	 * @param n the number of places around to move the head
	 */
	void moveHead(int n);

	/**
	 * @return an iterator for the list
	 */
	Iterator<T> iterator();

	/**
	 * @return a String representation of the list
	 */
	String toString();

	/**
	 * @return the size of the list
	 */
	int size();

}