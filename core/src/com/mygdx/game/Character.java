package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Character extends Sprite {
	
	private float vectorX, vectorY;
	private float[] location = new float[4];
	private boolean alive = true;
	private boolean[] stuck = new boolean[4];; //N, E, S, E: Directions this array uses to tell if something is that direction
	private String type = "character";
	
	public Character(float x1, float y1, float vx1, float vy1, Texture img){
		super(img);
		this.setPosition(x1, y1);
		vectorX = vx1;
		vectorY = vy1;
		location[0] = x1;
		location[1] = y1;
		location[2] = x1 + this.getWidth();
		location[3] = y1 + this.getHeight();
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
	
	public boolean movement(){
		return false;
	}
	
	public float[] getLocation(){
		return location;
	}
	
	protected void setLocation(float tX, float tY){
		location[0] = tX;
		location[1] = tY;
		location[2] = tX + this.getWidth();
		location[3] = tY + this.getHeight();
		
	}
	
	public boolean getAlive(){
		return alive;
	}
	
	public void setAlive(boolean a){
		alive = a;
	}
	
	public String getType(){
		return type;
	}
	
	public void makeStuck(char direc){
		if(direc == 'n'){
			stuck[0] = true;
		}
		if(direc == 'e'){
			stuck[1] = true;
		}
		if(direc == 's'){
			stuck[2] = true;
		}
		if(direc == 'w'){
			stuck[3] = true;
		}	
	}
	
	public void unStuck(char direc){
		if(direc == 'n'){
			stuck[0] = false;
		}
		if(direc == 'e'){
			stuck[1] = false;
		}
		if(direc == 's'){
			stuck[2] = false;
		}
		if(direc == 'w'){
			stuck[3] = false;
		}	
	}
	
	public boolean[] getStuck(){
		return stuck;
	}
	
	
	
	

}
