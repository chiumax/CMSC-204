import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {
	private ArrayList<T> q;
	private int size;

	public NotationQueue(int count) {
		q = new ArrayList<T>(count);
		size = count;
	}

	@Override
	public boolean isEmpty() {
		return q.size() == 0 ? true : false;
	}

	@Override
	public boolean isFull() {
		return q.size() == size ? true : false;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		return q.remove(0);
	}

	@Override
	public int size() {
		return q.size();
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(q.size() == size) {
			throw new QueueOverflowException();
		}
		q.add(e);
		return true;
	}

	@Override
	public String toString(String delimiter) {
		String returnString = "";
		for (T elm : q) {
			returnString += elm + delimiter;
		}
		returnString = returnString.substring(0, returnString.length()-1);
		return returnString;
	}
	
	public String toString() {
		String returnString = "";
		for(T elm: q) {
			returnString += elm;
		}
		return returnString;
	}

	@Override
	public void fill(ArrayList<T> list) {
		for (T elm : list) {
			try {
				this.enqueue(elm);
			} catch (QueueOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
