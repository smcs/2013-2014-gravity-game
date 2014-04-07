import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class Platform {
	private int topY;
	private int bottomY;
	private int leftX;
	private int rightX;
	private Image platformImg;
	
	public Platform(int x,int y,String ImageLoc){
		topY=y;
		leftX=x;	
		platformImg = new ImageIcon(ImageLoc).getImage();
		bottomY=topY+this.getHeight();
		rightX=leftX+this.getWidth();
		
	}
	
	
	private int getHeight(){
		return platformImg.getHeight(null);
	}
	
	private int getWidth(){
		return platformImg.getWidth(null);
	}
	public int gettopY(){
		return topY;
	}
	public int getbottomY(){
		return bottomY;
	}
	public int getleftX(){
		return leftX;
	}
	public int getrightX(){
		return rightX;
	}
	public Image getImage(){
		return platformImg;
	}
	public void update(long timePassed){
		
	}
	public void draw(Graphics2D gelf){
		gelf.drawImage(platformImg, leftX, topY,null);

	}
}
