package com.me.gravity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Babe {
	private Texture babeTex;
	private double xAcc=0,yAcc=0;
	private double xVel=0,yVel=0;
	private float xPos,yPos;
	private double startX,startY;
	private int sHeight,sWidth;
	private boolean slowmedown=false;
	private boolean collided;
	private int LivesLeft=5;
	private int TimeScore;
	private boolean GameOver=false;
	private Rectangle boundRect;
	
	public Babe(double x,double y,int h, int w, Texture hero){
		startX=x;
		startY=y;
		sHeight=h;
		sWidth=w;
		babeTex=hero;
		reset();
	}
	public void reset(){
		//TODO: Reset Animation Goes Here
		if(LivesLeft<=0){
			LivesLeft=5;
		}
		xPos=(float) startX;
		yPos=(float) startY;
		boundRect = new Rectangle(xPos,yPos,babeTex.getWidth(),babeTex.getHeight());
		xVel=0;
		xAcc=0;
		yVel=0;
		yAcc=0;
		collided=false;
	}
	public void update(float timePassed){
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
				xVel+=xAcc*timePassed;		
				xPos+=xVel*timePassed;
			}
			if(yAcc!=0){
				yVel+=yAcc*timePassed;
				yPos+=yVel*timePassed;
			}	
			boundRect.setPosition(xPos, yPos);
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
			xVel=0;
			xAcc=0;
			yVel=0;
			yAcc=0;
		}
		
	}
	
	public Rectangle getBabeBound(){
		return boundRect;
	}
	public double getXVel(){
		return xVel;
	}
	public double getYVel(){
		return yVel;
	}
	public boolean getGameOver(){
		return GameOver;
	}
	
	public void setPlatformCollision(boolean youcollided){
		this.collided=youcollided;
	}

	
	public boolean checkfordeaths(){ 
		if(yPos<=0||yPos>=sHeight-babeTex.getHeight()||xPos<=0||xPos>=sWidth-babeTex.getWidth()||collided){
			//Hits wall
			if(LivesLeft>0){
				LivesLeft--;
				return true;
			}else{
				//TODO: Game Over Screen
				GameOver=true;
				return true;
			}
		}else{
			//Otherwise...
			return false;
		}
		
		
	}


		
	
	
}

