/**
 * 
 */
package poker.unused;

import java.util.Iterator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author 86
 * 
 */
public class CircularList<T> implements Iterable<T>{	//Consider making Node and inner class
	
	private Node<T> head = null;
	private int size = 0;
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public void add(T t) {
		Node<T> newNode = new Node<T>(t);
		if (size == 0) {
			head = newNode;
		} else {
			Node<T> end = head.getPrevious();
			end.setNext(newNode);
			newNode.setNext(head);
			head.setPrevious(newNode);
			newNode.setPrevious(end);
		}
		size++;
	}

	public boolean remove(T t) {
		if ((size==1) && head.getContents().equals(t)) {
			head = null;
			size--;
			return true;
		} else if (size>1) {
			Node<T> curr = head;
			do {
				if (curr.getContents().equals(t)) {
					curr.getPrevious().setNext(curr.getNext());
					curr.getNext().setPrevious(curr.getPrevious());
					size--;
					return true;
				}
				curr = curr.getNext();	
			} while (curr != head);
		}
		return false;
	}
	
	public void moveHead(int n) {
		if (size!=0) {
			for (int i=0; i<n; i++) {
				head = head.getNext();
			}
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> end = (size==0)? null: head.getPrevious();
			Node<T> curr = end;
			int at = 0;

			@Override
			public boolean hasNext() {
				return (at!=size);
			}

			@Override
			public T next() {
				curr = curr.getNext();
				at++;
				return curr.getContents();
			}

			@Override
			public void remove() {
				throw new NotImplementedException();
			}
			
		};
	}
	
	@Override
	public String toString() {
		String str = "";
		for (T next : this) {
			str+=next.toString()+",";
		}
		return str;
	}

}
