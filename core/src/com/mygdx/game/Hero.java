package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Hero extends Character {
	private static Texture img = new Texture(Gdx.files.internal("maindude.png"));
	
	public Hero(float x1, float y1, float vx1, float vy1) {
		super(x1, y1, vx1, vy1, img);
		this.type = "hero";
		this.setHealth(5f);
	}
	
	public boolean movement(){//moves character based on vectors
		this.setX(this.getX() + this.getVectorX());
		this.setY(this.getY() + this.getVectorY());
		setLocation(this.getX(), this.getY());
		return true;
	}

}
