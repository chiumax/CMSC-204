
public class QueueOverflowException extends Exception {
	public QueueOverflowException() {
		super("Enqueue method called on a full queue");
	}

}
