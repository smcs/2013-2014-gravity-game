
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
	private boolean BgEnd=false;
	
	public BackgroundClass(double x, int sW){
		xPos=x;
		ScreenWidth=sW;
		bgPt=new Point();
		bgImg= new ImageIcon("/Gravity/src/bgplaceholder.jpg").getImage();
		
	}
	public void update(long timePassed){
		xPos+=xVel*timePassed/1000;
		bgPt.x=(int)xPos;
		if(xPos==bgImg.getWidth(null)*2){
			setBgEnd(true);
		}
		
	}
	private void setBgEnd(boolean asdf) {
		BgEnd=asdf;
	}
	public void setXVelocity(double newV){
		xVel=newV;
	}
	public double getX(){
		return xPos;
	}
	public Image getImage(){
		return bgImg;
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
	

