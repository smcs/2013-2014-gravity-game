import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;


public class BackgroundController extends Core implements KeyListener {
	public static void main(String args[]){
		new BackgroundController().run();
	}

	private Image backgroundImg = new ImageIcon("C:\\Users\\David\\Documents\\GitHub\\Gravity\\Graphics\\bgplaceholder.jpg").getImage();
	
	private Babe babe;
	private String babeImg = "C:\\Users\\David\\Documents\\GitHub\\Gravity\\Graphics\\CharacterFiller.png";
	
	private Platform platformtest;
	private String platformtestImg = "C:\\Users\\David\\Documents\\GitHub\\Gravity\\Graphics\\platformfiller.png";
	
	private int DirectionInt;
	private double babeV=0,bgV=0;
	
	public void init(){
		super.init();
				
		Window w = s.getFullScreenWindow();
		babe = new Babe(w.getWidth()/2,w.getHeight()/2,s.getHeight(),s.getWidth(),babeImg);
		platformtest= new Platform(s.getWidth()/2-150,s.getHeight()-200,platformtestImg);
		
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);

	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if(keyCode == KeyEvent.VK_ESCAPE){
			stop();
		}
		if(keyCode==KeyEvent.VK_LEFT){
			DirectionInt=3;	
			e.consume();
				
		}else if(keyCode==KeyEvent.VK_RIGHT){
			DirectionInt=4;
				
			e.consume();
		}else if(keyCode==KeyEvent.VK_UP){
			DirectionInt=1;			
				
			e.consume();
		}else if(keyCode==KeyEvent.VK_DOWN){
			DirectionInt=2;
			e.consume();
		}else if(keyCode==KeyEvent.VK_SPACE){
			DirectionInt=0;
		}else{	
			e.consume();
		}
	}


	public void keyReleased(KeyEvent e) {
		babe.setDirection(DirectionInt);
		e.consume();
	}

	public void keyTyped(KeyEvent e) {
		e.consume();
	}

	public void update(long timePassed){
		checkPlatformCollisions();
		babe.update(timePassed);
		platformtest.update(timePassed);
		
		
	}
	public void checkPlatformCollisions(){
		
		 if(
		 //TODO: Fix the Side Bug
			(babe.getY()+babe.getHeight()>=platformtest.getbottomY()&&babe.getY()+babe.getHeight()<=platformtest.gettopY())||
					//partially to the left
			(babe.getY()>=platformtest.getbottomY()&&babe.getY()<=platformtest.gettopY())||
					//all the way on the platform
			(babe.getY()>=platformtest.gettopY()&&babe.getY()+babe.getHeight()<=platformtest.getbottomY())
			){
			 System.out.println("Y is lined up!");
			 if((int)babe.getX()+babe.getWidth()==platformtest.getleftX()||(int)babe.getX()==platformtest.getrightX()){
			babe.setPlatformCollision(false, false, true);
			System.out.println("You bumped the side!");

			 }
		}else if(
				//Checks X first
				//Partially to the right
				(babe.getX()+babe.getWidth()>=platformtest.getrightX()&&babe.getX()+babe.getWidth()<=platformtest.getleftX())||
				//partially to the left
				(babe.getX()>=platformtest.getrightX()&&babe.getX()<=platformtest.getleftX())||
				//all the way on the platform
				(babe.getX()>=platformtest.getleftX()&&babe.getX()+babe.getWidth()<=platformtest.getrightX())
				){
			if((int)babe.getY()==platformtest.getbottomY()){
				//babe.setPlatformCollision(true, false, false);
				System.out.println("You hit your head!");
			}else if((int)babe.getY()+babe.getHeight()==platformtest.gettopY()){
				babe.setPlatformCollision(false, true, false);
				System.out.println("You landed!");
			}
		}
		 
		 
		 
	}
	
	public void draw(Graphics2D gelf) {
		gelf.drawImage(backgroundImg, 0, 0, null);
		babe.draw(gelf);
		platformtest.draw(gelf);
		

	}

}
