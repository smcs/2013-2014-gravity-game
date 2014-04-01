
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class Babe {
	private double xAcc=0,yAcc=0;
	private double xVel=0,yVel=0;
	private double xPos,yPos;
	private Image babeImg;
	private Point babePt;
	private int height,width;
	private boolean slowmedown=false;
	private boolean HeadHitBottom,LandedOnPlatform,HitSideofPlatform;
	
	public Babe(double x,double y,int h, int w, String ImageLoc){
		xPos=x;
		yPos=y;
		height=h;
		width=w;
		babePt=new Point();
		babeImg= new ImageIcon(ImageLoc).getImage();

		
	}
	public void update(long timePassed){
			
		if(slowmedown){
			if(xVel>0){
				xAcc=-100;
			}else if(xVel<0){
				xAcc=100;
			}else if(xVel==0){
				xAcc=0;
			}
			
			if(yVel>0){
				yAcc=-100;
			}else if(yVel<0){
				yAcc=100;
			}else if(yVel==0){
				yAcc=0;
			}
		}
		if(xAcc!=0){
			xVel+=xAcc*timePassed/1000;		
			xPos+=xVel*timePassed/1000;
		}
		if(yAcc!=0){
			yVel+=yAcc*timePassed/1000;
			yPos+=yVel*timePassed/1000;
		}
		babePt.x=(int) xPos;
		babePt.y=(int) yPos;	
		
	
	}
	public void setDirection(int DirectionNumber){
		if(DirectionNumber==1){
			//UP
			if(slowmedown){
				xAcc=0;
				xVel=0;
			}
			yAcc=-100;
			slowmedown=false;
		}else if(DirectionNumber==2){
			//DOWN
			if(slowmedown){
				xAcc=0;
				xVel=0;
			}
			yAcc=100;
			slowmedown=false;
		}else if(DirectionNumber==3){
			//LEFT
			if(slowmedown){
				yAcc=0;
				yVel=0;
			}
			xAcc=-100;
			slowmedown=false;
		}else if(DirectionNumber==4){
			//RIGHT
			if(slowmedown){
				yAcc=0;
				yVel=0;
			}
			xAcc=100;
			slowmedown=false;
		}else{
			slowmedown=true;
		}
		System.out.println( "   Y Acc is "+yAcc+ "   X Acc is "+xAcc);

		
	}
	
	public double getX(){
		return xPos;
	}
	public double getY(){
		return yPos;
	}
	
	public void setPlatformCollision(boolean hithead,boolean landed, boolean hitside){
		HeadHitBottom=hithead;
		LandedOnPlatform = landed;
		HitSideofPlatform = hitside;
	}

	public void checkPlatforms(){
		//head hits bottom
		if(HeadHitBottom==true){
			yVel=0;
		}else if(LandedOnPlatform==true){
			yVel=0;
		}else if(HitSideofPlatform==true){
			xVel=0;
		}else{
			if(LandedOnPlatform==false&&height-yPos>=100){
			}
		}
	
	}
	public int getWidth(){
		return babeImg.getWidth(null);
	}
	public int getHeight(){
		return babeImg.getHeight(null);
	}
	public void draw(Graphics2D gelf){
		if(babePt.x<0){
			babePt.x+=width;
		}
		if(babePt.y<0){
			babePt.y+=height;
		}
		babePt.x %= width;
		babePt.y %=height;
		int ix = babePt.x;
		int iy = babePt.y;
		gelf.drawImage(babeImg, ix, iy,null);
	}
	

	
}

