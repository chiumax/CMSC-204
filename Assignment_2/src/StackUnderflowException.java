
public class StackUnderflowException extends Exception {
	public StackUnderflowException() {
		super("Pop method called on an empty stack.");
	}
}
