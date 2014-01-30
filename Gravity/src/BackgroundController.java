import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;


public class BackgroundController extends Core implements KeyListener {
	public static void main(String args[]){
		new BackgroundController().run();
	}

	private Image bg;
	private Point image;
	
	double dx=5;
	double dy=5;
	
	
	public void init(){
		super.init();
		
		image = new Point();
		
		Window w = s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);

		bg= new ImageIcon("C:\\Users\\David\\Documents\\GitHub\\adv-topics-eacho\\Graphics\\bgplaceholder.jpg").getImage();
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE){
			stop();
		}else if(keyCode==KeyEvent.VK_LEFT){
			image.x-=dx;
			e.consume();
		}else if(keyCode==KeyEvent.VK_RIGHT){
			image.x+=dx;
			e.consume();
		}else{
			e.consume();
		}
	}

	public void keyReleased(KeyEvent e) {
		e.consume();
	}

	public void keyTyped(KeyEvent e) {
		e.consume();
	}
	
	public void update(long timePassed){
		
	}

	public void draw(Graphics2D gelf) {
		Window w = s.getFullScreenWindow();
		int width = s.getWidth();
		
		if(image.x<0){
			image.x+=width;
		}
		
		image.x %= width;
		int x = image.x;
		gelf.drawImage(bg,x,0,null);
		gelf.drawImage(bg,x-width,0,null);

	}

}
