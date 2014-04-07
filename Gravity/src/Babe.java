
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class Babe {
	private double xAcc=0,yAcc=0;
	private double xVel=0,yVel=0;
	private double xPos,yPos;
	private double startX,startY;
	private Image babeImg;
	private Point babePt;
	private int sHeight,sWidth;
	private boolean slowmedown=false;
	private boolean collided;
	private int LivesLeft=5;
	private int TimeScore;
	private boolean GameOver=false;
	
	public Babe(double x,double y,int h, int w, String ImageLoc){
		startX=x;
		startY=y;
		sHeight=h;
		sWidth=w;
		babePt=new Point();
		babeImg= new ImageIcon(ImageLoc).getImage();
		reset();
		
	}
	public void reset(){
		//TODO: Reset Animation Goes Here
		if(LivesLeft<=0){
			LivesLeft=5;
		}
		xPos=startX;
		yPos=startY;
		xVel=0;
		xAcc=0;
		yVel=0;
		yAcc=0;
		collided=false;
	}
	public void update(long timePassed){
		checkfordeaths();
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
			TimeScore+=timePassed;
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
	public boolean getGameOver(){
		return GameOver;
	}
	
	public void setPlatformCollision(boolean youcollided){
		this.collided=youcollided;
	}

	
	public void checkfordeaths(){ 
		if(yPos<=0||yPos>=sHeight-babeImg.getHeight(null)||xPos<=0||xPos>=sWidth-babeImg.getWidth(null)||collided){
			//Hits wall
			if(LivesLeft>0){
				LivesLeft--;
				reset();
			}else{
				//TODO: Game Over Screen
				reset();
				GameOver=true;
			}
		}else{
			//Otherwise...
		}
		
		
	}
	public Image getImage(){
		return babeImg;
	}
	public int getWidth(){
		return babeImg.getWidth(null);
	}
	public int getHeight(){
		return babeImg.getHeight(null);
	}
	public void draw(Graphics2D gelf){
		gelf.drawString(("Time: "+TimeScore/10),sWidth/2,20);
		gelf.drawString(("Lives: "+ LivesLeft), 20, 20);
		gelf.drawImage(babeImg,(int) xPos,(int) yPos,null);
	}
	

	
}

