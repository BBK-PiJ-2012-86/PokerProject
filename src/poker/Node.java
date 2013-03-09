/**
 * 
 */
package poker;

/**
 * @author 86
 *
 */
public class Node<T> {
	
	private T contents;
	private Node<T> next;
	private Node<T> previous;
	
	public Node() {
		this(null);
	}

	public Node(T t) {
		this.contents = t;
		this.next = this;
		this.previous = this;
	}
	
	public T getContents() {
		return contents;
	}

	public void setContents(T contents) {
		this.contents = contents;
	}
	
	public Node<T> getNext() {
		return this.next;
	}
	
	public void setNext(Node<T> node) {
		this.next = node;
	}

	public Node<T> getPrevious() {
		return previous;
	}

	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}
}
