package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Character> characters = new ArrayList<Character>();
	TextureAtlas textureAtlas;
	float posX, posY;
	Animation animation;
	float elapsedTime = 0;
	char shoot = 'e';
	int x = 0;
	int y = 0;
	float moveAmount = 1.0f;
	boolean fast = false;
	boolean left = true;
	boolean right = true;
	boolean down = true;
	boolean up = true;
	float width;
    float height;
    char dir;
    Hero hero;
    MovementController control;
	@Override
	public void create () {
		Gdx.graphics.setDisplayMode(500, 200, false);
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		textureAtlas = new TextureAtlas(Gdx.files.internal("ameeno.pack"));
		control = new MovementController(characters);
		//Setting up the hero
		hero = new Hero(posX, posY,0, 0); 
		posX = width/2 - hero.getWidth()/2;
		posY =  height/2 - hero.getHeight()/2;
		hero.setPosition(posX, posY);
		characters.add(hero);
		
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
		
		move();
		checkPhys(hero);
		//shooty();
		//movement();
		control.control();
		
		batch.begin();
		makeBack();
		hero.draw(batch);
		for(int i = 0; i < characters.size(); i++){
			Character temp = characters.get(i);
			temp.draw(batch);
		}
		batch.end();
	}
	
	public void move(){
		for(int i = 0; i < characters.size(); i++){
			Character temp = characters.get(i);
			temp.movement();
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		/*
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)){
			fast = true;
		}
		if(keycode == Keys.LEFT){
			characters.get(0).setVectorX(-1.0f);
		}
		if(keycode == Keys.RIGHT){
			characters.get(0).setVectorX(1.0f);
		}
		if(keycode == Keys.UP){
			characters.get(0).setVectorY(1.0f);
		}
		if(keycode == Keys.DOWN){
			characters.get(0).setVectorY(-1.0f);
		}
		if(keycode == Keys.W){
			shoot = 'W';
		}
		if(keycode == Keys.A){
			shoot = 'A';
		}
		if(keycode == Keys.S){
			shoot = 'S';
		}
		if(keycode == Keys.D){
			shoot = 'D';
		}
		if(fast){
			moveAmount = 10.0f;
		}
		*/
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		/*
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)){
			fast = false;
		}
		if(keycode == Keys.LEFT){
			characters.get(0).setVectorX(0);
		}
		if(keycode == Keys.RIGHT){
			characters.get(0).setVectorX(0);
		}
		if(keycode == Keys.UP){
			characters.get(0).setVectorY(0);
		}
		if(keycode == Keys.DOWN){
			characters.get(0).setVectorY(0);
		}
		if(!fast){
			moveAmount = 1.0f;
		}
		*/
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		System.out.println(screenX + " " + screenY);
		posX = screenX;
		posY = Gdx.graphics.getHeight()-screenY;
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
	
	public void shooty(){
		if(shoot != 'e'){
			Bullet z;
			if(shoot == 'W'){
				z = new Bullet(posX + hero.getWidth()/2, posY + hero.getHeight(), 0f, 10f);
				characters.add(z);
				dir = 'n';
				
			}
			if(shoot == 'A'){
				z = new Bullet(posX, posY + hero.getHeight()/2, -10f, 0f);
				characters.add(z);
				dir = 'w';
				
			}
			if(shoot == 'S'){
				z = new Bullet(posX + hero.getWidth()/2, posY, 0f, -10f);
				characters.add(z);
				
				dir = 's';
			}
			if(shoot == 'D'){
				z = new Bullet(posX + hero.getWidth(), posY + hero.getHeight()/2, 10f, 0f);
				characters.add(z);
				
				dir = 'e';
			}
			
			
		}
		
		shoot = 'e';
	}
	/*
	public void movement() {
		if(x == 1 && y == 0 && right){
			posX += moveAmount;
		}
		if(x == -1  && y == 0 && left){
			posX -= moveAmount;
		}
		if(y == 1  && x == 0 && up){
			posY += moveAmount;
		}
		if(y == -1  && x == 0 && down){
			posY -= moveAmount;
		}
		if(x == 1 && y == 1){
			if(right && up){
				posX += moveAmount;
				posY += moveAmount;
			}else if(right && !up){
				posX += moveAmount;
			}else if(!right && up){
				posY += moveAmount;
			}
		}
		if(x == -1  && y == -1){
			if(left && !down){
				posX -= moveAmount;
			}else if(!left && down){
				posY -= moveAmount;
			}else if(left && down){
				posX -= moveAmount;
				posY -= moveAmount;
			}
		}
		if(x == 1 && y == -1){
			if(right && !down){
				posX += moveAmount;
			}else if(!right && down){
				posY -= moveAmount;
			}else if(right && down){
				posX += moveAmount;
				posY -= moveAmount;
			}
		}
		if(x == -1 && y == 1){
			if(left && !up){
				posX -= moveAmount;
			}else if(!left && up){
				posY += moveAmount;
			}else if(left && up){
				posX -= moveAmount;
				posY += moveAmount;
			}
		}
	}*/
	public void makeBack(){
		elapsedTime += Gdx.graphics.getDeltaTime();
		for(int i = 0; i < 500; i += 50){
			for(int j = 0; j < 200; j+= 50){
				 batch.draw(animation.getKeyFrame(elapsedTime, true), i, j);		
			}
		}
	}
	public void checkPhys(Sprite sprite){
		for(int i = 0; i < characters.size(); i++){
			Character temp = characters.get(i);
			if(temp.getX() < 0 || temp.getX() > width || temp.getY() < 0 || temp.getY() > height){
				characters.remove(i);
			}
			
		}
		
		float ex = sprite.getX();
		float why = sprite.getY();
		if(ex <= 0){
			left = false;
		}else{
			left = true;
		}
		if(ex >= width - sprite.getWidth()){
			right = false;
		}else{
			right = true;
		}
		if(why <= 0){
			down = false;
		}else{
			down = true;
		}
		if(why >= height - sprite.getHeight()){
			up = false;
		}else{
			up = true;
		}
	}
	

	
}
