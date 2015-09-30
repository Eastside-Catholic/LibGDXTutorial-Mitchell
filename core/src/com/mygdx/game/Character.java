package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Character extends Sprite {
	
	private float vectorX, vectorY;
	
	public Character(float x1, float y1, float vx1, float vy1, Texture img){
		super(img);
		this.setPosition(x1, y1);
		vectorX = vx1;
		vectorY = vy1;
	}
	
	public float getVectorX(){
		return vectorX;
	}
	
	public float getVectorY() {
		return vectorY;
	}
	
	public void setVectorX(float x){
		vectorX = x;
	}
	
	public void setVectorY(float x){
		vectorY = x;
	}
	
	public boolean movement() {
		return false;
	}

}
