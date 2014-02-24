package src;
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

	private Image babeImg;
	private Image bg;
	private Point bImg;
	private Point image;
	private boolean PlayerCentered=false;
	private boolean bgDone = false;
	private int DirectionInt=0;
	private int PixelsTraveled=0;
	private BackgroundClass background;
	private Babe babe;
	
	double dx=20;
	double dy=5;
	
	
	public void init(){
		super.init();
		
		image = new Point();		
		Window w = s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);
		background= new BackgroundClass(0,s.getWidth());
		babe = new Babe(20,w.getHeight()-100,s.getHeight(),s.getWidth());
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE){
			stop();
		}else if(keyCode==KeyEvent.VK_LEFT){
			if (babe.getX()>0){
				babe.setXVelocity(-20);
			}
			DirectionInt=1;
                                                                                                                       			e.consume();
			
		}else if(keyCode==KeyEvent.VK_RIGHT){
			babe.setXVelocity(20);
			
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
		background.update(timePassed);
		babe.update(timePassed);
	}
	
	public void draw(Graphics2D gelf) {
		background.draw(gelf);
		babe.draw(gelf);
		

	}

}
