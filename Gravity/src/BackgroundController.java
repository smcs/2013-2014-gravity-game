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

	private Image babeImg;
	private Image bg;
	private Point bImg;
	private Point image;
	private boolean PlayerCentered=false;
	private boolean bgDone = false;
	private int DirectionInt=0;
	private int PixelsTraveled=0;
	
	double dx=20;
	double dy=5;
	
	
	public void init(){
		super.init();
		
		image = new Point();
		bImg = new Point();
		
		Window w = s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);

		
		bg= new ImageIcon("C:\\Users\\David\\Documents\\GitHub\\Gravity\\Graphics\\bgplaceholder.jpg").getImage();
		babeImg= new ImageIcon("C:\\Users\\David\\Documents\\GitHub\\Gravity\\Graphics\\CharacterFiller.png").getImage();

	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE){
			stop();
		}else if(keyCode==KeyEvent.VK_LEFT){
			if(!PlayerCentered){
				bImg.x-=dx;
			}else{
				image.x-=dx;
				PixelsTraveled-=dx;

			}
			DirectionInt=1;
			whoismoving();
			e.consume();
			
		}else if(keyCode==KeyEvent.VK_RIGHT){
			if(!PlayerCentered){
				bImg.x+=dx;

			}else{
				PixelsTraveled+=dx;
				image.x+=dx;
			}
			DirectionInt=2;
			whoismoving();
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
	
	public void whoismoving(){
		
		if(DirectionInt==1){
			if(bImg.x<s.getWidth()/2&&bgDone){
				PlayerCentered=true;

			}
			if(PixelsTraveled<1){
				PlayerCentered=false;
				bgDone=false;
			}
		}
		if(DirectionInt==2){
			if(bImg.x>s.getWidth()/2&&!bgDone){
				PlayerCentered=true;
			}
			if(PixelsTraveled>s.getWidth()){
				bgDone=true;
			}else{
				bgDone=false;
			}
			if(bgDone){
				PlayerCentered=false;
			}
		}
		
	}
	
	public void draw(Graphics2D gelf) {
		Window w = s.getFullScreenWindow();
		int width = s.getWidth();
		int height = s.getHeight();
		if(image.x<0){
			image.x+=width;
		}
		
		image.x %= width;
		int ix = image.x;
		int bx = bImg.x;
		
		if(PixelsTraveled<=0){
			gelf.drawImage(bg, 0, 0, null);
		}else{
			gelf.drawImage(bg,ix,0,null);
			gelf.drawImage(bg,ix-width,0,null);
		}
		gelf.drawImage(babeImg, bx, height-babeImg.getHeight(null),null);

	}

}
