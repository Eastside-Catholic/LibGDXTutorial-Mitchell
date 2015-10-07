package com.mygdx.game;

import java.util.List;

import javax.xml.stream.events.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class MovementController {
	List<Character> characters;
	float heroX;
	float heroY;
	Hero hero;
	int i;
	float height;
	float width;
	boolean[] stuck;
	boolean xs;
	boolean ys;
	public MovementController(List<Character> a, float h,float w){
		characters = a;
		height = h;
		width = w;
		
	}
	
	public void control(){
		hero = (Hero) characters.get(0);
		heroX = hero.getX();
		heroY = hero.getY();
		Bullet z;
		
		stuck = hero.getStuck();
		for(int i = 0; i < stuck.length; i++){
			System.out.println(stuck[i]);
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			hero.setVectorX(-1.0f);
			
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			hero.setVectorX(1.0f);
		}
		if(Gdx.input.isKeyPressed(Keys.UP )){
			hero.setVectorY(1.0f);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			hero.setVectorY(-1.0f);
		}
		if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT) 
				|| hero.getVectorX() < 0 && stuck[3] || hero.getVectorX() > 0 && stuck[1] ){
			hero.setVectorX(0);
		}
		if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN)
				|| hero.getVectorY() < 0 && stuck[2] || hero.getVectorY() > 0 && stuck[0]){
			hero.setVectorY(0);
		}
		
		if(Gdx.input.isKeyPressed(Keys.W)){
			if(i > 0 && i <= 10){
				i++;
			}else if(i >10){
				i = 0;
			}else{
				z = new Bullet(heroX + hero.getWidth()/2,heroY + hero.getHeight(), 0f, 10f);
				characters.add(z);
				i++;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.A)){
			if(i > 0 && i <= 10){
				i++;
			}else if(i >10){
				i = 0;
			}else{
				z = new Bullet(heroX + hero.getWidth()/2,heroY + hero.getHeight(), -10.0f, 0f);
				characters.add(z);
				i++;
			}
		}
				
		if(Gdx.input.isKeyPressed(Keys.S)){
			if(i > 0 && i <= 10){
				i++;
			}else if(i >10){
				i = 0;
			}else{
				z = new Bullet(heroX + hero.getWidth()/2,heroY + hero.getHeight(), 0f, -10f);
				characters.add(z);
				i ++;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.D)){
			if(i > 0 && i <= 10){
				i++;
			}else if(i >10){
				i = 0;
			}else{
				z = new Bullet(heroX + hero.getWidth()/2,heroY + hero.getHeight(), 10f, 0);
				characters.add(z);
				i++;
			}
		}
		
		if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && 
				!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)){
			i = 0;
		}	
	}
	
	public void checkCollision(Character one){
		float[] locationOne = one.getLocation();
		//System.out.println("width " + width + " height " + height);
		float x1 = locationOne[0];
		float y1 = locationOne[1];
		float x2 = locationOne[2];
		float y2 = locationOne[3];
		//System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
		
		if(x1 < 0){
			one.makeStuck('w');
		}else{
			one.unStuck('w');
		}
		if(x2 > width){
			one.makeStuck('e');
		}else{
			one.unStuck('e');
		}
		if(y1 < 0){
			one.makeStuck('s');
		}else{
			one.unStuck('s');
		}
		if(y2 > height){
			one.makeStuck('n');
		}else{
			one.unStuck('n');
		}	
	}
}
