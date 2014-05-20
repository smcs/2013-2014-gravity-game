package com.me.gravity;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Gravity implements ApplicationListener, GestureListener {
	private SpriteBatch batch;
	private Texture hero,misTex;
	private TextureRegion[] bgTex = new TextureRegion[39];
	private Animation bgAni;
	private InputProcessor inputProcessor;
	private Vector2 position;
	private OrthographicCamera camera;
	private Babe babe;
	private Missile[] missile=new Missile[5];
	private float TimePassedForMissiles=0;
	private int missilecounter=0;
	private float aniCounter=0;
	private float bgRot=0;
	
	
	@Override
	public void create() {		
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
	    camera.setToOrtho(false, 1920, 1080);
	    for(int i = 0; i<39; i++){
	    	bgTex[i] = new TextureRegion(new Texture(Gdx.files.internal("data\\stargif1_"+i+".gif")));
	    }
	    bgAni = new Animation((float) 1,bgTex);
		hero = new Texture(Gdx.files.internal("data\\CharacterFiller.png"));
		babe= new Babe(50,50,Gdx.graphics.getHeight(),Gdx.graphics.getWidth(), hero);
		misTex = new Texture(Gdx.files.internal("data\\missilefiller.png"));
		
		GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);
        
	}

	public void MissileMaker(){
		if(babe.getXVel()>0 || babe.getYVel()>0){
			TimePassedForMissiles+=Gdx.graphics.getDeltaTime();
			float randInt;
			randInt = MathUtils.random(3,6);
			
			if(TimePassedForMissiles>=randInt){
				TimePassedForMissiles=0;
				//startX startY screenW, screenH, Texture mT
				if(MathUtils.randomBoolean()){
					if(MathUtils.randomBoolean()){
						//Y AT 0
						missile[missilecounter] = new Missile(MathUtils.random(50,Gdx.graphics.getWidth()-50),0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), misTex);
					}else{
						//Y AT EDGE
						missile[missilecounter] = new Missile(MathUtils.random(50,Gdx.graphics.getWidth()-50),Gdx.graphics.getHeight(),Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), misTex);
	
					}
				}else{
					if(MathUtils.randomBoolean()){
						//X AT 0
						missile[missilecounter] = new Missile(0,MathUtils.random(50,Gdx.graphics.getHeight()-50),Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), misTex);
					}else{
						//X AT EDGE
						missile[missilecounter] = new Missile(Gdx.graphics.getWidth(),MathUtils.random(50,Gdx.graphics.getHeight()-50),Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), misTex);
	
					}
				}
				if(missilecounter<4){
					missilecounter++;
				}else{
					missilecounter=0;
				}
			}
		}	
	}
	@Override
	public void dispose() {
		hero.dispose();
		if(missile!=null){
			misTex.dispose();
		}
		bgTex[bgAni.getKeyFrameIndex(aniCounter)].getTexture().dispose();
		batch.dispose();
	
	}
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	    camera.update();
	    batch.setProjectionMatrix(camera.combined);

	    
		if(Gdx.input.isKeyPressed(Keys.W)){
			babe.setDirection(2);
			bgRot=90;
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			babe.setDirection(1);
			bgRot=270;
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			babe.setDirection(4);
			bgRot=0;
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			babe.setDirection(3);
			bgRot=180;
		}
		babe.update(Gdx.graphics.getDeltaTime());
		for(int i=0; i<5;i++){
			if(missile[i]!=null && missile[i].pastScreenEnd()){
				missile[i]=null;
				TimePassedForMissiles=0; 
			}
			if(missile[i]!=null){
				missile[i].update(Gdx.graphics.getDeltaTime());
				if(babe.getBabeBound().contains(missile[i].getMissileBound())){
					babe.setPlatformCollision(true);
					missile[i]=null;
					TimePassedForMissiles=0;
				}
			}
			
		}
		
		MissileMaker();
		
		batch.begin();
		if(bgRot==180||bgRot==0){
			batch.draw(bgTex[bgAni.getKeyFrameIndex(aniCounter)], 0, 0, 1920/2, 1080/2, 1920, 1080, 1, 1, bgRot);
		}else if(bgRot==90||bgRot==270){
			batch.draw(bgTex[bgAni.getKeyFrameIndex(aniCounter)], (1920-1080)/2, -(1920-1080)/2, 1080/2, 1920/2, 1080, 1920, 1, 1, bgRot);
		}
		for(int i=0; i<5;i++){
			if(missile[i]!=null){
				batch.draw(misTex, missile[i].getX(), missile[i].getY(),misTex.getHeight(),misTex.getHeight());
			}
		}
		if(aniCounter>=bgAni.getKeyFrames().length){
			aniCounter=0;
		}else{
			aniCounter++;
		}
		
		System.out.println(aniCounter + "\t" + Gdx.graphics.getDeltaTime());
		batch.draw(hero, babe.getBabeBound().getX(), babe.getBabeBound().getY());
		batch.end();
	}
	
	

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if(button>=4){
			babe.setDirection(0);
		}
		return true;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		System.out.println("Fling performed velocity: " + Float.toString(velocityX) + ", " + Float.toString(velocityY));
		if(velocityY>1500){
			babe.setDirection(1);
			bgRot=270;
		}else if(velocityY<=-1500){
			babe.setDirection(2);
			bgRot=90;
		}else if(velocityX>=1500){
			babe.setDirection(4);
			bgRot=0;
		}else if(velocityX<=-1500){
			babe.setDirection(3);
			bgRot=180;
		}
		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
}
