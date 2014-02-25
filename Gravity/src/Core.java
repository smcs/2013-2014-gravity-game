
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.Window;


public abstract class Core {
	private static DisplayMode modes[] = {
		new DisplayMode(1920,1080,32,0),
		new DisplayMode(1920,1080,24,0),
		new DisplayMode(1920,1080,16,0),
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,316,0)
	};
	private boolean running;
	protected ScreenManager s;
	
	//stop method
	public void stop(){
		running=false;
	}
	
	//call initiate and gameloop
	public void run(){
		try{
			init();
			gameLoop();
		}finally{
			s.restoreScreen();
		}
	}
	//initiator
	public void init(){
		s=new ScreenManager();;
		DisplayMode dungeonmaster = s.findFirstCompatibleMode(modes);
		s.SetFullScreen(dungeonmaster);
		
		Window w=s.getFullScreenWindow();
	//	w.setFont(new Font("Arial",Font.PLAIN,20));
		w.setBackground(Color.green);
		w.setForeground(Color.white);
		
		running =true;
	}
	//main game loop
	
	
	public void gameLoop(){
		long startTime = System.currentTimeMillis();
		long cumiTime = startTime;
		
		while(running){
			long timePassed=System.currentTimeMillis()-cumiTime;
			cumiTime+=timePassed;
			update(timePassed);
			
			Graphics2D gelf = s.getGraphics();
			draw(gelf);
			gelf.dispose();
			s.updateDisplay();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){}
		}
	}
	
	public void update(long tP){
		
	}
	
	public abstract void draw(Graphics2D gelf);
	
}
