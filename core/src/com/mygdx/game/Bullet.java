package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Bullet extends Character{
	
	private static Texture img = new Texture(Gdx.files.internal("pew.png"));
	
	public Bullet (float x1, float y1, float vx1, float vy1){
		super(x1, y1, vx1, vy1, img);
		this.type = "bullet";
	}
	
	public boolean movement(){
		this.setX(this.getX() + this.getVectorX());
		this.setY(this.getY() + this.getVectorY());
		setLocation(this.getX(), this.getY());
		return true;
	}
	
	public void makeStuck(char a){//If the bullet collides with something, it is set for removal
		this.setAlive(false);
	}
	
	

	
	
}
