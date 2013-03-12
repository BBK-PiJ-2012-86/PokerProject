package poker.hand_card;

import java.util.LinkedList;

public class TestUtil {
	public static <T> LinkedList<T> toLinkedList(T[] array) {
		LinkedList<T> result = new LinkedList<T>();
		for (T t : array) {
			result.add(t);
		}
		return result;
	}

}
