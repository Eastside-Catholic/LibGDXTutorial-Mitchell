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
	int numBadGuys = 1;
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
		Gdx.graphics.setDisplayMode(800, 400, false);
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
		Badguy bad = new Badguy(10, 10, 0, 0);
		Badguy bad1 = new Badguy(30, 10, 0, 0);
		Badguy bad2 = new Badguy(50, 10, 0, 0);
		Badguy bad3 = new Badguy(70, 10, 0, 0);
		Badguy bad4 = new Badguy(90, 10, 0, 0);
		Badguy bad5 = new Badguy(110, 10, 0, 0);
		characters.add(bad);
		characters.add(bad1);
		characters.add(bad2);
		characters.add(bad3);
		characters.add(bad4);
		characters.add(bad5);
		
		
		animation = new Animation(1/15f, textureAtlas.getRegions());
		//Gdx.input.setInputProcessor(this);
	}
	
	public void dispose() {
		batch.dispose();
		textureAtlas.dispose();
	}

	@Override
	public void render () {
		System.out.println(characters.size());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		move();
		//checkPhys(hero);
		
		for(int i = 0; i < characters.size(); i++){//resets the already stuck boolean
			characters.get(i).setAlreadyStuck(false);
		}
		for(int i = 0; i < characters.size(); i++){
			Character temp = characters.get(i);
		
			Boolean a = false;
			a = control.checkCollision(temp);
			if(a){
				temp.setAlreadyStuck(a);
			}
			for(int j = i + 1; j < characters.size(); j++){
				a = control.checkCollision(temp,characters.get(j));
				if(a){
					temp.setAlreadyStuck(a);
					characters.get(j).setAlreadyStuck(a);
				}
			}
		}
		
		checkAlive();
		
		control.control();
		
		batch.begin();
		//makeBack();
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
	
	public void checkAlive(){
		List<String> toKill = new ArrayList<String>();
		String type;
		String location;
		for(int i = 0; i < characters.size(); i++){
			Character temp = characters.get(i);
			type = temp.getType();
			if(temp.getHealth() == 0){
				temp.setAlive(false);
			}
			if(!type.equals("hero")){
				if(!temp.getAlive()){
					characters.remove(i);
				}
			}
		}
		
	}
	
	public void makeBack(){
		elapsedTime += Gdx.graphics.getDeltaTime();
		for(int i = 0; i < width; i += 50){
			for(int j = 0; j < height; j+= 50){
				 batch.draw(animation.getKeyFrame(elapsedTime, true), i, j);		
			}
		}
	}
}
