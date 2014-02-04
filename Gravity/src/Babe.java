import java.awt.Image;


public class Babe {
	private double xLoc,yLoc;
	private int sWidth;
	private boolean playerCentered=false;
	
	private Animation ani;
	private float x;
	private float y;
	private float vX;
	private float vY;
	
	
	public void Babe(Animation a){
		ani=a;
	}
	
	public boolean playerCentered(){
		return playerCentered;
	}
	
	public void updateLoc(long timePassed, int dX){
		x+=dX;
		ani.update(timePassed);
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setX(float newX){
		x=newX;
	}
	public void setY(float newY){
		y=newY;
	}
	
	public int getWidth(){
		return ani.getImage().getWidth(null);
	}
	
	public int getHeight(){
		return ani.getImage().getHeight(null);
	}
	public float getVX(){
		return vX;
	}
	public float getVY(){
		return vY;
	}
	public void setVX(float velX){
		vX=velX;
	}
	public void setVY(float velY){
		vY=velY;
	}
	public Image getImage(){
		return ani.getImage();
	}
	
	
}

