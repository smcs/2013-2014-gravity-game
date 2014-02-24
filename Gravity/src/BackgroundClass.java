package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class BackgroundClass {
	private double xVel=0;
	private double xPos;
	private Image bgImg;
	private Point bgPt;
	private int ScreenWidth;
	
	public BackgroundClass(double x, int sW){
		xPos=x;
		ScreenWidth=sW;
		bgPt=new Point();
		bgImg= new ImageIcon("C:\\Users\\David\\Documents\\GitHub\\Gravity\\Graphics\\bgplaceholder.jpg").getImage();
		
	}
	public void update(long timePassed){
		xPos+=xVel*timePassed/1000;
		bgPt.x=(int)xPos;
		
	}
	public void setXVelocity(double newV){
		xVel=newV;
	}

	public void draw(Graphics2D gelf){
		
		if(bgPt.x<0){
			bgPt.x+=ScreenWidth;
		}
		
		bgPt.x %= ScreenWidth;
		int x = bgPt.x;
		gelf.drawImage(bgImg,x,0,null);
		gelf.drawImage(bgImg,x-ScreenWidth,0,null);

	}

}
	

