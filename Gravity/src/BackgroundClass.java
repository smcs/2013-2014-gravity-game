import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class BackgroundClass {
	private double xVel=0;
	private double xPos;
	private Image bgImg;
	private Point bgPt;
	
	public BackgroundClass(double x){
		xPos=x;
		bgPt=new Point();
		bgImg= new ImageIcon("C:\\Users\\David\\Documents\\GitHub\\Gravity\\Graphics\\bgplaceholder.jpg").getImage();
		
	}
	public void update(long timePassed){
		
	}
	public void setXVelocity(double newV){
		xVel=newV;
	}

	public void draw(Graphics2D gelf){
		
	}
	

	
}

