

import java.awt.*;
import java.awt.image.*;

import javax.swing.JFrame;



public class ScreenManager {
	private GraphicsDevice vc;
	
	//Gives videocard the screen
	public ScreenManager(){
		GraphicsEnvironment e= GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		vc = e.getDefaultScreenDevice();
	}
	//Recieves display modes from graphics card
	public DisplayMode[] getCompatibleDisplayModes(){
		return vc.getDisplayModes();
	}
	//Compares DM from VC with /this/ DM
	public DisplayMode findFirstCompatibleMode(DisplayMode modes[]){
		DisplayMode goodModes[] = vc.getDisplayModes();
		
		for(int x=0;x<modes.length;x++){
			for(int y=0;y<goodModes.length;y++){
				if(displayModesMatch(modes[x],goodModes[y])==true){
					return modes[x];
				}
			}
		}
		return null;
		
	}
	public boolean displayModesMatch(DisplayMode mode1,DisplayMode mode2){
		if(mode1.getWidth() !=mode2.getWidth()||mode2.getHeight()!=mode1.getHeight()){
			return false;
		}else if(mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI&&mode2.getBitDepth()!= DisplayMode.BIT_DEPTH_MULTI&&mode1.getBitDepth()!=mode2.getBitDepth()){
			return false;
		}else if(mode1.getRefreshRate()!=DisplayMode.REFRESH_RATE_UNKNOWN&&mode2.getRefreshRate()!=DisplayMode.REFRESH_RATE_UNKNOWN&&mode1.getRefreshRate()!=mode2.getRefreshRate()){
			return false;
		}else{
		return true;
		}
	}
	
	//get current displaymode
	public DisplayMode getCurrentDisplayMode(){
		return vc.getDisplayMode();
	}
	
	//Makes fullscreen
	public void SetFullScreen(DisplayMode dm){
		JFrame f = new JFrame();
		f.setUndecorated(true);
		f.setIgnoreRepaint(true);
		f.setResizable(false);
		vc.setFullScreenWindow(f);
	
		if(dm!=null && vc.isDisplayChangeSupported()){
			try{
				vc.setDisplayMode(dm);
				
			}catch(Exception ex){}
			f.createBufferStrategy(2);
			
		
		}
	}
	public Graphics2D getGraphics(){
		Window w=vc.getFullScreenWindow();
		if(w!=null){
			BufferStrategy s = w.getBufferStrategy();
			return (Graphics2D) s.getDrawGraphics();
		}
		else{
			return null;
		}
	}
	
	public void updateDisplay(){
		Window w=vc.getFullScreenWindow();
		if(w!=null){
			 BufferStrategy s=w.getBufferStrategy();
			 if(s.contentsLost()==false){
				 s.show();
			 }
		}
	}
	
	public Window getFullScreenWindow(){
		return vc.getFullScreenWindow();
	}
	
	public int getWidth(){
		Window w = getFullScreenWindow();
		
		if(w!=null){
			return w.getWidth();
		}else{
			return 0;
		}
	}
	public int getHeight(){
		Window w = getFullScreenWindow();
		
		if(w!=null){
			return w.getHeight();
		}else{
			return 0;
		}
	}
	
	//Leave fullscreen O.O
	public void restoreScreen(){
		Window w= getFullScreenWindow();
		if(w!=null){
			w.dispose();
		}
		vc.setFullScreenWindow(null);
	}
	
	//Compatible = yay!
	public BufferedImage createCompatibleImage(int w, int h, int t){
		Window win=vc.getFullScreenWindow();
		if(win!=null){
			GraphicsConfiguration gc = win.getGraphicsConfiguration();
			return gc.createCompatibleImage(w, h, t);
		}else{
			return null;
		}
	}
}
