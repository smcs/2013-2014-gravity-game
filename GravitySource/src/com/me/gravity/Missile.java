package com.me.gravity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Missile {
	
	private int startX,startY;
	private double xVel0,yVel0;
	private double xAcc,yAcc;
	private float xPos,yPos;
	private int sWidth,sHeight;
	private Texture missileTex;
	private Rectangle misRect;
	
	public Missile(int sX, int sY, int screenW, int screenH, Texture mT){
		startX = sX;
		startY = sY;
		sWidth = screenW;
		sHeight = screenH;
		missileTex=mT;
		xPos=startX;
		yPos=startY;
		misRect= new Rectangle(xPos,yPos,missileTex.getWidth(),missileTex.getHeight());
		if(startX==0){
			xVel0=75;
			xAcc=100;
			yVel0=0;
			yAcc=0;
		}else if(startX>=sWidth){
			xVel0=-75;
			xAcc=-100;
			yVel0=0;
			yAcc=0;
		}else if(startY==0){
			yVel0=75;
			yAcc=100;
			xVel0=0;
			xAcc=0;
		}else if(startY>=sHeight){
			yVel0=-75;
			yAcc=-100;
			xVel0=0;
			xAcc=0;
		}
		
	}
	public void update(float timePassed){
		if(xAcc!=0){
			xVel0+=xAcc*timePassed;		
			xPos+=xVel0*timePassed;
		}
		if(yAcc!=0){
			yVel0+=yAcc*timePassed;
			yPos+=yVel0*timePassed;
		}	
		misRect.setPosition(xPos, yPos);
	}
	
	public boolean pastScreenEnd(){
		if((xVel0!=0 && (xPos+missileTex.getWidth()<0 || xPos>sWidth))||(yVel0!=0 && (yPos+missileTex.getHeight()<0 || yPos>sHeight))){
			return true;
		}else{
			return false;
		}
	}	
	
	public float getY(){
		return yPos;
	}
	public float getX() {
		return xPos;
	}
	public Rectangle getMissileBound() {
		return misRect;
	}
}
