package com.mygdx.game;

import java.util.List;

import javax.xml.stream.events.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;

public class MovementController {
	List<Character> characters;
	float heroX;
	float heroY;
	float heroSpeed = 2f;
	Hero hero;
	int i;
	int fireRate = 20;
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
		//System.out.println(i);
		hero = (Hero) characters.get(0);
		heroX = hero.getX();
		heroY = hero.getY();
		Bullet z;
		
		stuck = hero.getStuck();
		/*for(int i = 0; i < stuck.length; i++){
			System.out.println(stuck[i]);
		}*/
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			hero.setVectorX(-heroSpeed);
			
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			hero.setVectorX(heroSpeed);
		}
		if(Gdx.input.isKeyPressed(Keys.UP )){
			hero.setVectorY(heroSpeed);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			hero.setVectorY(-heroSpeed);
		}
		if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT) 
				|| hero.getVectorX() < 0 && stuck[3] || hero.getVectorX() > 0 && stuck[1] ){
			hero.setVectorX(0);
		}
		if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN)
				|| hero.getVectorY() < 0 && stuck[2] || hero.getVectorY() > 0 && stuck[0]){
			hero.setVectorY(0);
		}
		
		if(Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D)){//Shoot N
			if(i > 0 && i <= fireRate){
				i++;
			}else if(i >fireRate){
				i = 0;
			}else{
				z = new Bullet(heroX + hero.getWidth()/2 - 6,heroY + hero.getHeight(), 0f, 10f);
				characters.add(z);
				i++;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D)){//Shoot NW
			if(i > 0 && i <= fireRate){
				i++;
			}else if(i >fireRate){
				i = 0;
			}else{
				z = new Bullet(heroX - 13,heroY + hero.getHeight(), -10f, 10f);
				characters.add(z);
				i++;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.S)){ //Shoot west
			if(i > 0 && i <= fireRate){
				i++;
			}else if(i >fireRate){
				i = 0;
			}else{
				z = new Bullet(heroX -12, heroY + hero.getHeight()/2 - 6 , -10.0f, 0f);
				characters.add(z);
				i++;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.W)){ //Shoot SW
			if(i > 0 && i <= fireRate){
				i++;
			}else if(i >fireRate){
				i = 0;
			}else{
				z = new Bullet(heroX - 13, heroY - 12, -10.0f, -10.0f);
				characters.add(z);
				i++;
			}
		}
				
		if(Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D)){//Shoot S
			if(i > 0 && i <= fireRate){
				i++;
			}else if(i > fireRate){
				i = 0;
			}else{
				z = new Bullet(heroX + hero.getWidth()/2 - 6,heroY - 12, 0f, -10f);
				characters.add(z);
				i ++;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A)){ //Shoot SE
			if(i > 0 && i <= fireRate){
				i++;
			}else if(i >fireRate){
				i = 0;
			}else{
				z = new Bullet(heroX + hero.getWidth(), heroY - 12, 10.0f, -10.0f);
				characters.add(z);
				i++;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.W)){ //Shoot East
			if(i > 0 && i <= fireRate){
				i++;
			}else if(i > fireRate){
				i = 0;
			}else{
				z = new Bullet(heroX + hero.getWidth(),heroY + hero.getHeight()-16, 10f, 0);
				characters.add(z);
				i++;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A)){//Shoot NE
			if(i > 0 && i <= fireRate){
				i++;
			}else if(i >fireRate){
				i = 0;
			}else{
				z = new Bullet(heroX + hero.getWidth(),heroY + hero.getHeight(), 10f, 10f);
				characters.add(z);
				i++;
			}
		}
		
		if(!Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.S) && 
				!Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D)){
			i = 0;
		}	
	}
	
	public boolean checkCollision(Character one){
		float[] locationOne = one.getLocation();
		//System.out.println("width " + width + " height " + height);
		float x1 = locationOne[0];
		float y1 = locationOne[1];
		float x2 = locationOne[2];
		float y2 = locationOne[3];
		boolean preStuck = one.getAlreadyStuck();
		//System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
		boolean a = false;
		
		if(x1 < 0){
			one.makeStuck('w');
			a = true;
		}else if(!preStuck){
			one.unStuck('w');
			
		}
		if(x2 > width){
			one.makeStuck('e');
			a = true;
		}else if(!preStuck){
			one.unStuck('e');
			
		}
		if(y1 < 0){
			one.makeStuck('s');
			a = true;
		}else if(!preStuck){
			one.unStuck('s');
		}
		if(y2 > height){
			one.makeStuck('n');
			a = true;
		}else if(!preStuck){
			one.unStuck('n');
		}	
		
		return a;
	}
	
	public boolean checkCollision(Character one, Character two){
		float[] a = one.getLocation();
		float[] b = two.getLocation();
		float Ax1 = a[0];
		float Ax2 = a[2];
		float Bx1 = b[0];
		float Bx2 = b[2];
		float Ay1 = a[1];
		float Ay2 = a[3];
		float By1 = b[1];
		float By2 = b[3];
		boolean collide = false;
		
		
		if((Ay2 >= By1 && Ay1 + one.getHeight()/2 <= By1) && (Bx1 >= Ax1 && Bx1 <= Ax2 || Bx2 >=Ax1 && Bx2 <= Ax2)){//Collides from north
			one.makeStuck('n');
			two.makeStuck('s');
			collide = true;
		}else if((Ax2 >= Bx1 && Ax1 + one.getWidth()/2 <= Bx1) && (By1 >= Ay1 && By1 <= Ay2 || By2 >=Ay1 && By2 <= Ay2)){//Collides from east
			one.makeStuck('e');
			two.makeStuck('w');
			collide = true;
		}else if((Ay2 - one.getHeight()/2 >= By2 && Ay1 <= By2) && (Bx1 >= Ax1 && Bx1 <= Ax2 || Bx2 >=Ax1 && Bx2 <= Ax2)){//Collides from south
			one.makeStuck('s');
			two.makeStuck('n');
			collide = true;
		}else if((Ax1 + one.getWidth()/2 >= Bx2 && Ax1  <= Bx2) && (By1 >= Ay1 && By1 <= Ay2 || By2 >=Ay1 && By2 <= Ay2)){//Collides from west
			one.makeStuck('w');
			two.makeStuck('e');
			collide = true;
		}else{
			collide = false;
		}
		
		if(two.getType().equals("bullet") && collide){
			one.setHealth(one.getHealth() - 1);
		}
		
		if(two.getType().equals("badguy") && one.getType().equals("hero") && collide){
			one.setHealth(one.getHealth() - 1);
			Gdx.gl.glClearColor(256, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		}
		
		return collide;
	}
}
