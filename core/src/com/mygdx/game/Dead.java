package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Dead extends Character{

	private static Texture img = new Texture(Gdx.files.internal("dead.png"));
	public Dead(float x1, float y1, float vx1, float vy1, Texture img) {
		super(x1, y1, vx1, vy1, img);
		// TODO Auto-generated constructor stub
	}

}
