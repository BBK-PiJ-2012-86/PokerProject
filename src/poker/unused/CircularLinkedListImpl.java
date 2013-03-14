/**
 * 
 */
package poker.unused;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedListImpl<T> implements Iterable<T>, CircularLinkedList<T>{

	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;

	public class Node<V> {
		private V contents;
		private Node<V> next;
		private Node<V> previous;

		@Override
		public String toString() {
			return contents.toString();
		}
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return size;
	}


	@Override
	public void add(T t) {
		Node<T> newNode = new Node<T>();
		newNode.contents = t;
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		}
		newNode.next = head;
		newNode.previous = tail;
		head.previous = newNode;
		tail.next = newNode;
		tail = newNode;
		size++;
	}

	@Override
	public boolean remove(T t) {
		if (isEmpty()) {
			return false;
		}
		if ((size==1) && head.contents.equals(t)) {
			head = null;
			tail = null;
			size--;
			return true;
		} else if (size>1) {
			Node<T> curr = head;
			do {
				if (curr.contents.equals(t)) {
					curr.previous.next = curr.next;
					curr.next.previous = curr.previous;
					size--;
					return true;
				}
				curr = curr.next;	
			} while (curr != head);
		}
		return false;
	}

	@Override
	public void moveHead(int n) {
		if(isEmpty()) {
			throw new UnsupportedOperationException();
		} else {
			for (int i=0; i<n; i++) {
				head = head.next;
				tail = tail.next;
			}
		}
	}


	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> curr = head;
			Node<T> before = null;
			
			@Override
			public boolean hasNext() {
				if (isEmpty()) {
					return false;
				}
				if (curr.equals(head)&&(before!=null)) {
					return false;
				}
				return true;
			}
			
			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				before = curr;
				T toReturn = curr.contents;
				curr = curr.next; 
				return toReturn;
			}

			@Override
			public void remove() {
				//throw new UnsupportedOperationException();
				/*if (before == null) {
					throw new UnsupportedOperationException();
				} else if (isEmpty()) {
					throw new UnsupportedOperationException();
				}
	            else if (size() == 1) {
	            	head = null;
	            	tail = null;
	            	curr = null;
	            	}
	            Node<T> x = before.previous;
	            Node<T> y = before.next;
	            x.next = y;
	            y.previous = x;
	            before = null;
	            size--;*/
			}

		};
	}

	@Override
	public String toString() {
		String result = "";
		if (isEmpty()) {
			return result;
		}
		Node<T> curr = head;
		StringBuffer buf = new StringBuffer();
		do {
			buf.append(curr.equals(head)?"":",");
			buf.append(curr.toString());
			curr = curr.next;
		} while (!curr.equals(head));
		result = buf.toString();
		return result;
	}

	
}
