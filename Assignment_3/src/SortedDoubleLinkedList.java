import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	private Comparator<T> c;

	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		c = comparator2;
	}

	public SortedDoubleLinkedList<T> add(T data) {
		if (size == 0) {
			super.addToFront(data);
			return this;
		}

		if (c.compare(first.getItem(), data) > 0 || c.compare(last.getItem(), data) == 0) {
			super.addToFront(data);
			return this;
		}

		if (c.compare(last.getItem(), data) < 0 || c.compare(last.getItem(), data) == 0) {
			super.addToEnd(data);
			return this;
		}

		int prevC = -1;
		int currC;

		Node current = first;
		Node newNode;
		while (true) {
			currC = c.compare(current.getItem(), data);
			System.out.println(currC);
			if (prevC < 0 && currC > 0 || currC == 0) {
				newNode = new Node(data, current.getPrev(), current);
				current.getPrev().setNext(newNode);
				current.setPrev(newNode);
				size++;
				break;
			}
			prevC = currC;
			current = current.getNext();
		}
		return this;
	}

	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException();
	}

	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> iterator() {
		return super.iterator();
	}

	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
	}

}
