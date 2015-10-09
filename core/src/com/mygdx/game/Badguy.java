package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Badguy extends Character{
	
	private static Texture img = new Texture(Gdx.files.internal("Badguy.png"));
	public Badguy(float x1, float y1, float vx1, float vy1) {
		super(x1, y1, vx1, vy1, img);
		// TODO Auto-generated constructor stub
	}
	
	public boolean movement(){
		char a;
		char b;
		boolean loop = true;
		boolean[] stuck = this.getStuck();
		if(this.getVectorX() == 0 && this.getVectorY() == 0){
			randomDirection();
		}
		for(int i = 0; i < stuck.length; i++){
			if(stuck[i]){
				
				//To figure out direction stuck
				if(i == 0){
					b = 'n';
				}else if(i == 1){
					b = 'e';
				}else if (i == 2){
					b = 's';
				}else{
					b = 'w';
				}
				
				//To check to make sure direction is not the same as stuck direction
				while(loop){
					a = randomDirection().charAt(0);
					if(!(a == b) ){
						loop = false;
					}
				}
			}
		}
		
		this.setX(this.getX() + this.getVectorX());
		this.setY(this.getY() + this.getVectorY());
		setLocation(this.getX(), this.getY());
		return true;
	}
	
	public String randomDirection(){
		int direction = (int)(Math.random()*8);
		
		if(direction == 0){//N
			this.setVectorX(0);
			this.setVectorY(2);
			return "n";
		}else if(direction == 1){//NE
			this.setVectorX(2);
			this.setVectorY(2);
			return "ne";
		}else if(direction == 2){//E
			this.setVectorX(2);
			this.setVectorY(0);
			return "e";
		}else if(direction == 3){//SE
			this.setVectorX(2);
			this.setVectorY(-2);
			return "se";
		}else if(direction == 4){//S
			this.setVectorX(0);
			this.setVectorY(-2);
			return "s";
		}else if(direction == 5){//SW
			this.setVectorX(-2);
			this.setVectorY(-2);
			return "sw";
		}else if(direction == 6){//W
			this.setVectorX(-2);
			this.setVectorY(0);
			return "w";
		}else if(direction == 7){//NW
			this.setVectorX(-2);
			this.setVectorY(2);
			return "nw";
		}
		
	    return "error";
	}

}
