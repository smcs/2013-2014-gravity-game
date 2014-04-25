package com.me.gravity;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class Gravity implements ApplicationListener, GestureListener {
	private SpriteBatch batch;
	private Texture hero;
	private InputProcessor inputProcessor;
	private Vector2 position;
	private OrthographicCamera camera;
	private Babe babe;
	@Override
	public void create() {		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);
		hero = new Texture(Gdx.files.internal("data\\CharacterFiller.png"));
		babe= new Babe(50,50,Gdx.graphics.getHeight(),Gdx.graphics.getWidth(), hero);
        GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);

	}

	@Override
	public void dispose() {
		hero.dispose();
		
		batch.dispose();
	
	}
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	    camera.update();
	    batch.setProjectionMatrix(camera.combined);

	    
		if(Gdx.input.isKeyPressed(Keys.W)){
			babe.setDirection(2);
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			babe.setDirection(1);
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			babe.setDirection(4);
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			babe.setDirection(3);
		}
		babe.update(Gdx.graphics.getDeltaTime());
		batch.begin();
		
		batch.draw(hero, babe.getX(), babe.getY());
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
		}else if(velocityY<=-1500){
			babe.setDirection(2);
		}else if(velocityX>=1500){
			babe.setDirection(4);	
		}else if(velocityX<=-1500){
			babe.setDirection(3);
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
