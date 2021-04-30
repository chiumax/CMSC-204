import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {
	Queue<Integer> q;
	Random rand = new Random();
	
	public CarQueue() {
		q = new LinkedList<>();
		q.add(3);
		q.add(3);
		q.add(3);
		q.add(3);
		q.add(0);
	}
	
	public int deleteQueue() {
		return q.remove();
	}
	
	public void addToQueue() {
		class temp implements Runnable {
			@Override
			public void run() {
				try {
					for(int i = 0; i < 1000; i++) {
						int num = rand.nextInt(4);
						q.add(num);
						Thread.sleep(50);
					}
				}catch(Exception exception) {
					exception.printStackTrace();
			}
		}
	}
		Runnable run = new temp();
		Thread t = new Thread(run);
		t.start();
	}

}
