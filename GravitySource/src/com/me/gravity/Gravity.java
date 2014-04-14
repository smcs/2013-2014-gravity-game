package com.me.gravity;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Gravity implements ApplicationListener {
	private SpriteBatch batch;
	private Texture hero;
	private InputProcessor inputProcessor;
	private Vector2 position;
	@Override
	public void create() {		
		batch = new SpriteBatch();
		
		hero = new Texture(Gdx.files.internal("data\\CharacterFiller.png"));
		position = new Vector2(50,50);
		
		
	}

	@Override
	public void dispose() {
	
	}
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		
		if(Gdx.input.isKeyPressed(Keys.W)){
			position.y+=1f;
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			position.y-=1f;
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			position.x+=1f;
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			position.x-=1f;
		}
		if(Gdx.input.isTouched()){
			position.y+=1f;
		}
	
		batch.begin();
		
		batch.draw(hero, position.x, position.y);
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
}
