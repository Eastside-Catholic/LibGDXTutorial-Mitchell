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
	public MovementController(List<Character> a){
		characters = a;
		
	}
	
	public void control(){
		hero = (Hero) characters.get(0);
		heroX = hero.getX();
		heroY = hero.getY();
		Bullet z;
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			hero.setVectorX(-1.0f);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			hero.setVectorX(1.0f);
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			hero.setVectorY(1.0f);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			hero.setVectorY(-1.0f);
		}
		if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)){
			hero.setVectorX(0);
		}
		if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN)){
			hero.setVectorY(0);
		}
		if(Gdx.input.isKeyPressed(Keys.W)){
			z = new Bullet(heroX + hero.getWidth()/2,heroY + hero.getHeight(), 0f, 10f);
			characters.add(z);
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			z = new Bullet(heroX + hero.getWidth()/2,heroY + hero.getHeight(), -10.0f, 0f);
			characters.add(z);
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			z = new Bullet(heroX + hero.getWidth()/2,heroY + hero.getHeight(), 0f, -10f);
			characters.add(z);
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			z = new Bullet(heroX + hero.getWidth()/2,heroY + hero.getHeight(), 10f, 0);
			characters.add(z);
		}
	}
}
