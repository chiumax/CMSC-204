
public class StackOverflowException extends Exception {
	public StackOverflowException() {
		super("Push method called on a full stack.");
	}
}
