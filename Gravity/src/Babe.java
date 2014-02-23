import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class Babe {
	private double xAcc=0,yAcc=-9.8;
	private double xVel=0,yVel=0;
	private double xPos,yPos;
	private Image babeImg;
	private Point babePt;
	private int height,width;
	
	public Babe(double x,double y,int h, int w){
		xPos=x;
		yPos=y;
		height=h;
		width=w;
		babePt=new Point();
		babeImg= new ImageIcon("C:\\Users\\David\\Documents\\GitHub\\Gravity\\Graphics\\CharacterFiller.png").getImage();

		
	}
	public void update(long timePassed){
		
	}
	public void setXVelocity(double newV){
		xVel=newV;
	}
	public void jump(double newyVel){
		yVel=newyVel;
	}
	
	public void draw(Graphics2D gelf){
		if(babePt.x<0){
			babePt.x+=width;
		}
		
		babePt.x %= width;
		int ix = babePt.x;
		
		gelf.drawImage(babeImg, ix, height-babeImg.getHeight(null),null);
	}
	

	
}

