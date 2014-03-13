
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class Babe {
	private double xAcc=0,yAcc=-98;
	private double xVel=0,yVel=0;
	private double xPos,yPos;
	private Image babeImg;
	private Point babePt;
	private int height,width;
	private boolean jumping;
	private boolean PlayerCentered=false;
	
	public Babe(double x,double y,int h, int w){
		xPos=x;
		yPos=y;
		height=h;
		width=w;
		babePt=new Point();
		babeImg= new ImageIcon("C:\\Users\\David\\Documents\\GitHub\\Gravity\\Graphics\\CharacterFiller.png").getImage();

		
	}
	public void update(long timePassed){
		
		xVel+=xAcc*timePassed/1000;		
		xPos+=xVel*timePassed/1000;
		if(jumping){
			yVel+=yAcc*timePassed/1000;
		}
		if(height-yPos>=100&&jumping){
			yPos-=yVel*timePassed/1000;
		}else{
			yPos=height-100;
			yVel=0;
			jumping=false;
		}
		babePt.x=(int) xPos;
		babePt.y=(int) yPos;
		
		if(babePt.x>=width/2-babeImg.getWidth(null)/2-5&&babePt.x<=width/2-babeImg.getWidth(null)/2+5){
			setPlayerCentered(true);
		}else{
			setPlayerCentered(false);
		}
		
	
	}
	public void setXVelocity(double newV){  
		xVel=newV;
	}
	
	public void jump(double newyVel){
		if(!jumping){
			yVel=newyVel;
		}
		jumping=true;
	}
	public double getX(){
		return xPos;
	}
	public void setPlayerCentered(boolean asdf){
		PlayerCentered=asdf;
	}
	public boolean getPlayerCentered(){
		return PlayerCentered;
	}
	public void draw(Graphics2D gelf){
		if(babePt.x<0){
			babePt.x+=width;
		}
		
		babePt.x %= width;
		int ix = babePt.x;
		int iy = babePt.y;
		gelf.drawImage(babeImg, ix, iy,null);
	}
	

	
}

