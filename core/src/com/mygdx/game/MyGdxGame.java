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
	
	public SpriteBatch batch;
	public List<Character> characters = new ArrayList<Character>();
	public int numStartBadguys = 10;
    public MovementController control;
    public int loops = 0;
    public int badguyCount;
    public int score = 0;
    public int highScore = 0;
    public boolean hero = true;
    BitmapFont font;
	@Override
	public void create () { //Sets up initial screen, with hero, enemies, etc.
		Gdx.graphics.setDisplayMode(800, 400, false);
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		control = new MovementController(characters, height, width);
		//Setting up the hero
		Hero hero = new Hero(0,0,0, 0); 
		float posX = width/2 - hero.getWidth()/2;
		float posY =  height/2 - hero.getHeight()/2;
		hero.setPosition(posX, posY);
		characters.add(hero);
		for(int i = 0; i < numStartBadguys; i++){
			spawnBadguy();
		}
		
	}
	
	public void dispose() {//called when program ends
		batch.dispose();
	}

	@Override
	public void render () {//repeatedly called
		System.out.println(score);
		Gdx.gl.glClearColor(0, 0, 0, 1);//Setting Background to Black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		move();
		
		checkCollisions();
		
		checkAlive();
		
		hScore();
		
		control.control();
		
		draw();
		
		if(loops % 500 == 0){//spawns new enemy every 500 loops
			spawnBadguy();
		}
		
		if(badguyCount == 0){//if no badguys then spawn in more and give 1000 pts
			score += 1000;
			numStartBadguys += 5;
			for(int i = 0; i < numStartBadguys; i++){
				spawnBadguy();
			}
		}
		
		if(!hero){
			reset();
		}
		
		loops ++;
	}
	
	public void move(){
		//Handles movement
		for(int i = 0; i < characters.size(); i++){
			Character temp = characters.get(i);
			temp.movement();
		}
	}
	
	public void checkCollisions(){
		
		for(int i = 0; i < characters.size(); i++){//resets the already stuck boolean
			characters.get(i).setAlreadyStuck(false);
		}
		for(int i = 0; i < characters.size(); i++){//Goes through each Character
			Character temp = characters.get(i);
		
			Boolean a = false;
			a = control.checkCollision(temp);//Checks if collides with wall
			
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
	}
	
	public void checkAlive(){
		String type;
		int tempCount = 0;//used to count number of badguys
		for(int i = 0; i < characters.size(); i++){//Looks through every character
			
			Character temp = characters.get(i);
			type = temp.getType();
			
			if(type.equals("badguy")){
				tempCount++;
			}
			if(temp.getHealth() <= 0){//Checks Health, if <1, tells it that its dead
				temp.setAlive(false);
			}
			
			if(!type.equals("hero")){
				if(!temp.getAlive()){
					characters.remove(i);
					if(type.equals("badguy")){
						score += 100;
						tempCount--;
					}
				}
			}else{
				if(!temp.getAlive()){
					float[] locat = characters.get(i).getLocation();
					characters.remove(i);
					Dead dead = new Dead(locat[0], locat[1]);
					characters.add(i, dead);
					hero = false;
				}
			}
		}
		
		if(tempCount != badguyCount){//if the num of Badguys is different, update it
			badguyCount = tempCount;
		}
		
	}
	
	public void draw(){//used to draw characters on screen
		batch.begin();
		
		for(int i = 0; i < characters.size(); i++){//Draws every character to screen
			Character temp = characters.get(i);
			temp.draw(batch);
		}
		
		drawText();
		batch.end();
	}
	
	public void spawnBadguy(){
		int rand = (int)(Math.random()*4);//Picks a side for the badguy to spawn
		
		if(rand == 0){
			Badguy bad = new Badguy(0, (int)(Math.random()*390), 0, 0);
			characters.add(bad);
		}else if(rand == 1){
			Badguy bad = new Badguy(790, (int)(Math.random()*390), 0, 0);
			characters.add(bad);			
		}else if(rand == 2){
			Badguy bad = new Badguy((int)(Math.random()*780), 0, 0, 0);
			characters.add(bad);			
		}else{
			Badguy bad = new Badguy((int)(Math.random()*780), 390, 0, 0);
			characters.add(bad);			
		}
	}
	
	public void drawText(){
		int live;
		String score = ("Score: " + this.score);
		String hiscore = ("High Score: " + this.highScore);
		if(characters.get(0).getType().equals("hero")){//if the hero is removed from screen, then it assumes lives 0
			live = (int)characters.get(0).getHealth();
		}else{
			live = 0;
		}
		String lives = ("Lives: " + live);
		int sLength = score.length();
		int hLength = hiscore.length();
		int lLength = lives.length();
		font.draw(batch, hiscore , 802 - (hLength*7), 395);
		font.draw(batch, score , 800 - (sLength*7), 375);
		font.draw(batch, lives, 806 - (lLength*7), 355);
		if(!hero){
			font.draw(batch, "You are dead." , 357, 220);
			font.draw(batch, "Press enter to try again!" , 330, 200);
		}
	}
	
	public void hScore(){
		if(score > highScore){
			highScore=score;
		}
	}
	public void reset(){
		if(Gdx.input.isKeyPressed(Keys.ENTER)){
			hero = true;
		}
		if(hero){
			score = 0;
			numStartBadguys = 10;
			loops = 0;
			characters.removeAll(characters);
			create();
		}
	}
	
}
