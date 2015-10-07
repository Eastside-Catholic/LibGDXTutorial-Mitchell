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

public class MyGdxGame extends ApplicationAdapter implements ApplicationListener {
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
		control = new MovementController(characters, height, width);
		//Setting up the hero
		hero = new Hero(posX, posY,0, 0); 
		posX = width/2 - hero.getWidth()/2;
		posY =  height/2 - hero.getHeight()/2;
		hero.setPosition(posX, posY);
		characters.add(hero);
		
		animation = new Animation(1/15f, textureAtlas.getRegions());
		//Gdx.input.setInputProcessor(this);
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
		//checkPhys(hero);
		control.checkCollision(characters.get(0));
		
		
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
