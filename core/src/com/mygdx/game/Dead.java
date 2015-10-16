package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Dead extends Character{

	private static Texture img = new Texture(Gdx.files.internal("dead.png"));
	public Dead(float x1, float y1) {
		super(x1, y1, 0, 0, img);
		this.type = "dead";
		// TODO Auto-generated constructor stub
	}

}
