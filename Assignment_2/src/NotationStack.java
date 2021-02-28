import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T> {
	private ArrayList<T> s;
	private int size;
	
	public NotationStack(int count) {
		s = new ArrayList<T>(count);
		size = count;
	}

	@Override
	public boolean isEmpty() {
		return s.size() == 0 ? true : false;
	}

	@Override
	public boolean isFull() {
		return s.size() == size ? true : false;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		return s.remove(s.size()-1);
	}

	@Override
	public T top() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		return s.get(s.size()-1);
	}

	@Override
	public int size() {
		return s.size();
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		if(s.size() == size) {
			throw new StackOverflowException();
		}
		s.add(e);
		return true;
	}
	@Override
	public String toString(String delimiter) {
		String returnString = "";
		for (T elm : s) {
			returnString += elm + delimiter;
		}
		returnString = returnString.substring(0, returnString.length()-1);
		return returnString;
	}
	
	public String toString() {
		String returnString = "";
		for(T elm: s) {
			returnString += elm;
		}
		return returnString;
	}

	@Override
	public void fill(ArrayList<T> list) {
		for (T elm : list) {
			try {
				this.push(elm);
			} catch (StackOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
