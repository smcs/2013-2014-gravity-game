import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class BackgroundController extends Core implements KeyListener {
	public static void main(String args[]){
		new BackgroundController().run();
	}

	private BackgroundClass background;
	private Babe babe;
	
	private int DirectionInt;
	private double babeV=0,bgV=0;
	
	public void init(){
		super.init();
				
		Window w = s.getFullScreenWindow();
		background= new BackgroundClass(0,s.getWidth());
		babe = new Babe(20,w.getHeight()-100,s.getHeight(),s.getWidth());
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);

	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_ESCAPE){
			stop();
		}
		if(background.getX()>=0&&background.getX()<=background.getImage().getWidth(null)){
			if(keyCode==KeyEvent.VK_LEFT){
				babe.setXVelocity(-1*babeV);
				background.setXVelocity(bgV);
				DirectionInt=1;
				
				e.consume();
				
			}else if(keyCode==KeyEvent.VK_RIGHT){
				babe.setXVelocity(babeV);
				background.setXVelocity(-1*bgV);
				DirectionInt=2;
				
				e.consume();
			}else if(keyCode==KeyEvent.VK_UP){
				//TODO: Set Y velocity for jumping
				babe.jump(200);
				
				e.consume();
			}else if(keyCode==KeyEvent.VK_DOWN){
				
				e.consume();
			}else{
				
				e.consume();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_RIGHT){
			background.setXVelocity(0);
			babe.setXVelocity(0);
		}
		
		e.consume();
	}

	public void keyTyped(KeyEvent e) {
		e.consume();
	}

	public void update(long timePassed){
		whoIsMoving();
		background.update(timePassed);
		babe.update(timePassed);
		
		
	}
	public void whoIsMoving(){
		if(!background.getBgEnd()){
			if((babe.getPlayerCentered()&&DirectionInt==2)){
				babeV=0;
				bgV=100;
			}else if(babe.getPlayerCentered()&&DirectionInt==1&&background.getX()>0){
				babeV=0;
				bgV=100;
			}else{
				babeV=100;
				bgV=0;
			}
		}else if(background.getBgEnd()){
			babeV=100;
			bgV=0;
		}
		
	}
	public void draw(Graphics2D gelf) {
		background.draw(gelf);
		babe.draw(gelf);
		

	}

}
