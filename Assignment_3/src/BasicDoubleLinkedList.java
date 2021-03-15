import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	protected int size;
	protected Node first, last;

	public BasicDoubleLinkedList() {
		size = 0;
		first = null;
		last = null;
	}

	/**
	 * add data to end of linkedlist
	 * 
	 * @param data
	 * @return current state of linkedlist
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		if (last == null) {
			last = new Node(data, null, null);
			first = last;

		} else {

			Node nextLast = new Node(data, last, null);
			last.setNext(nextLast);
			last = nextLast;
		}

		size++;
		return this;
	}

	/**
	 * add data to front of linkedlist
	 * 
	 * @param data
	 * @return current state of linkedlist
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		if (first == null) {
			first = new Node(data, null, null);
			last = first;
		} else {
			Node nextFirst = new Node(data, null, first);
			first.setPrev(nextFirst);
			first = nextFirst;
		}
		size++;
		return this;
	}

	/**
	 * get first of linkedlist
	 * 
	 * @return first element
	 */
	public T getFirst() {
		if (first == null) {
			return null;
		}
		return first.getItem();
	}

	/**
	 * get last of linkedlist
	 * 
	 * @return last element
	 */
	public T getLast() {
		if (last == null) {
			return null;
		}
		return last.getItem();
	}

	/**
	 * the size of the linked list
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * iterator
	 * @return modified list iterator object
	 * @throw UnsupportedOperationException
	 * @throw NoSuchElementException
	 */
	@Override
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException{
		return new DoubIterator();
	}

	/**
	 * remove first occurrence of targetData from linkedlist
	 * @param targetData
	 * @param comparator
	 * @return current state of linkedlist
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		Node current = first;
		
		if(size == 0) {
			return this;
		}
		if(size ==1) {
			if(comparator.compare(targetData, first.getItem())==0) {
				first = null;
				last = null;
				size--;
			}
			return this;
		}
		
		while(current != null) {
			if(comparator.compare(targetData, current.getItem())==0) {
				if(current.equals(first)) {
					first = current.getNext();
					current.getNext().setPrev(null);
				} else if(current.equals(last)) {
					last = current.getPrev();
					current.getPrev().setNext(null); 
				} else {
					current.getPrev().setNext(current.getNext());
					current.getNext().setPrev(current.getPrev());				
				}
				
				size--;
				break;
			}
			current=current.getNext();
		}
		return this;
	}

	/**
	 * removes and returns first element from linkedlist
	 * 
	 * @return data of the first element if exists, otherwise null
	 */
	public T retrieveFirstElement() {
		if (first == null) {
			return null;
		}
		T t = first.getItem();

		if (size == 1) {
			first = null;
			last = null;
			return t;
		}

		first = first.getNext();
		first.setPrev(null);
		size--;
		return t;
	}

	/**
	 * removes and returns last element from linkedlist
	 * 
	 * @return data of last element if exists, otherwise null
	 */
	public T retrieveLastElement() {
		if (last == null) {
			return null;
		}
		T t = last.getItem();

		if (size == 1) {
			first = null;
			last = null;
			return t;
		}

		last = last.getPrev();
		last.setNext(null);
		size--;
		return t;
	}

	
	public ArrayList<T> toArrayList() {
		ArrayList<T> arr = new ArrayList<T>();
		DoubIterator iter = (BasicDoubleLinkedList<T>.DoubIterator) iterator();
		
		while(iter.hasNext()) {
			arr.add(iter.next());	
		}	
		return arr;
	}

	protected class Node {
		private T item;

		private Node prev, next = null;

		Node(T item, Node prev, Node next) {
			this.item = item;
			this.prev = prev;
			this.next = next;
		}

		public T getItem() {
			return item;
		}

		public Node getPrev() {
			return prev;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node n) {
			next = n;
		}

		public void setPrev(Node n) {
			prev = n;
		}
		
		public boolean equals(Node n) {
			if(next == n.getNext() && prev == n.getPrev() && item == n.getItem()) {
				return true;
			}
			return false;
		}
	}

	/**
	 * doubly linked iterator
	 */
	protected class DoubIterator implements ListIterator<T> {
		Node i;
		Node prevI;
		boolean isFirst = false;
		boolean isLast = false;

		DoubIterator() {
			i = first;
			isFirst = true;
		}

		/**
		 * check if there is another element in list
		 * 
		 * @return true if not last element
		 */
		@Override
		public boolean hasNext() {
			return !isLast;
		}

		/**
		 * set current pointer to next one if possible
		 * 
		 * @throw NoSuchElementException when not possible to set pointer to next one
		 * @return data of next element
		 */
		@Override
		public T next() throws NoSuchElementException {
			if (hasNext()) {
				if(i==null && isLast == false) {
					i = prevI;
				}
				T t = i.getItem();
				prevI = i;
				i = i.getNext();
				if(i==null) {
					isLast = true;
				} else {
					isFirst = false;
					isLast = false;
				}
				return t;
			}
			throw new NoSuchElementException();
		}

		/**
		 * check if there is an element before i in list
		 * 
		 * @return true if not first element
		 */
		@Override
		public boolean hasPrevious() {
			return !isFirst;
		}

		/**
		 * set current pointer to previous one if possible
		 * 
		 * @throw NoSuchElementException when not possible to set pointer to previous
		 *        one
		 * @return data of previous element
		 */
		@Override
		public T previous() throws NoSuchElementException {
			if (hasPrevious()) {
				if(i==null && isFirst == false) {
					i = prevI;
				}
				T t = i.getItem();
				prevI = i;
				i = i.getPrev();
				if(i==null) {
					isFirst = true;
				} else {
					isFirst = false;
					isLast = false;
				}
				return t;
			}

			throw new NoSuchElementException();
		}

		// not implemented

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
		}

	}
}
