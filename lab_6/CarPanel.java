import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws two car shapes.
*/
public class CarPanel extends JComponent{  
	private Car car;
	private int x,y, d;
	private CarQueue q;
	private int direction;
	
	CarPanel(int x, int y, int d, CarQueue q){
		this.d = d;
        this.x=x;
        this.y=y;
        car = new Car(x, y, this);
        this.q = q;
	}
	public void startAnimation(){
	      class AnimationRunnable implements Runnable{
	         public void run(){
	            try{
	               for(int i=0;i<10;i++){
	            	   direction = q.deleteQueue();
	            	   switch(direction) {
	            	   case 0: 
	            		   y-=10;
	            		   if(y<10) {
	            			   y=10;
						   }
	            		   break;
	            	   case 1:
	            		   y+=10;
	            		   if(y>400){
	            			   y=390;
						   }
	            		   break;
	            	   case 2: 
	            		   x+=10;
	            		   if(x>300){
	            			   x=290;
						   }
	            		   break;
	            	   case 3: 
	            		   x-=10;
	            		   if(x<0){
	            			   x=10;
						   }
	            		   break;
	            	   }
	            	   repaint();
	            	   Thread.sleep(d*1000);
	            	   
	               }
	            }
	            catch (InterruptedException exception){
	            	// nothing
	            }
	            finally{
	            	//nothing
	            }
	         }
	      }
	      Runnable r = new AnimationRunnable();
	      Thread t = new Thread(r);
	      t.start();
	   }
   public void paintComponent(Graphics g){  
      Graphics2D g2 = (Graphics2D) g;
      car.draw(g2,x,y);    
   }
}