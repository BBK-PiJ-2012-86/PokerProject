package poker.manager_player;

import java.util.Iterator;

/**
 * @param <T>
 */
public interface CircularLinkedList<T> {

	/**
	 * @return
	 */
	public abstract boolean isEmpty();

	/**
	 * @param t
	 */
	public abstract void add(T t);

	/**
	 * @param t
	 * @return
	 */
	public abstract boolean remove(T t);

	/**
	 * @param n
	 */
	public abstract void moveHead(int n);

	/**
	 * @return
	 */
	public abstract Iterator<T> iterator();

	/**
	 * @return
	 */
	public abstract String toString();

	/**
	 * @return
	 */
	int size();

}