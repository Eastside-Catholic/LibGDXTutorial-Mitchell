package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MyGdxGame extends ApplicationAdapter implements ApplicationListener,InputProcessor {
	SpriteBatch batch;
	Pixmap pixmap;
	Texture img;
	Sprite sprite;
	TextureAtlas textureAtlas;
	float posX, posY;
	Animation animation;
	float elapsedTime = 0;
	int x = 0;
	int y = 0;
	float moveAmount = 1.0f;
	boolean fast = false;
	
	@Override
	public void create () {
		Gdx.graphics.setDisplayMode(500, 200, false);
		float w = Gdx.graphics.getWidth();
	    float h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		textureAtlas = new TextureAtlas(Gdx.files.internal("ameeno.pack"));
		img = new Texture(Gdx.files.internal("maindude.png"));
		sprite = new Sprite(img);
		posX = w/2 - sprite.getWidth()/2;
		posY =  h/2 - sprite.getHeight()/2;
		sprite.setPosition(posX,posY);
		animation = new Animation(1/15f, textureAtlas.getRegions());
		Gdx.input.setInputProcessor(this);
	}
	
	public void dispose() {
		batch.dispose();
		textureAtlas.dispose();
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sprite.setPosition(posX, posY);
		batch.begin();
		if(x == 1 && y == 0){
			posX += moveAmount;
		}
		if(x == -1  && y == 0){
			posX -= moveAmount;
		}
		if(y == 1  && x == 0){
			posY += moveAmount;
		}
		if(y == -1  && x == 0){
			posY -= moveAmount;
		}
		if(x == 1 && y == 1){
			posX += moveAmount;
			posY += moveAmount;
		}
		if(x == -1  && y == -1){
			posX -= moveAmount;
			posY -= moveAmount;
		}
		if(x == 1 && y == -1){
			posX += moveAmount;
			posY -= moveAmount;
		}
		if(x == -1 && y == 1){
			posX -= moveAmount;
			posY += moveAmount;
		}
		elapsedTime += Gdx.graphics.getDeltaTime();
		for(int i = 0; i < 500; i += 50){
			for(int j = 0; j < 200; j+= 50){
				 batch.draw(animation.getKeyFrame(elapsedTime, true), i, j);		
			}
		}
		sprite.draw(batch);
		batch.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)){
			fast = true;
		}
		if(keycode == Keys.LEFT){
			x=-1;
		}
		if(keycode == Keys.RIGHT){
			x=1;
		}
		if(keycode == Keys.UP){
			y=1;
		}
		if(keycode == Keys.DOWN){
			y=-1;
		}
		if(fast){
			moveAmount = 10.0f;
		}
		
		
		System.out.println("push" + x + " " + y + " " + fast);
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)){
			fast = false;
		}
		if(keycode == Keys.LEFT){
			x=0;
		}
		if(keycode == Keys.RIGHT){
			x=0;
		}
		if(keycode == Keys.UP){
			y=0;
		}
		if(keycode == Keys.DOWN){
			y=0;
		}
		if(!fast){
			moveAmount = 1.0f;
		}
		System.out.println("pull" + x + " " + y + " " + fast);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
