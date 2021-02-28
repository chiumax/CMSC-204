
public class QueueUnderflowException extends Exception{
	public QueueUnderflowException() {
		super("Dnqueue method called on an empty queue");
	}

}
