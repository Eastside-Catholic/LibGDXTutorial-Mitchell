package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Pixmap pixmap;
	Texture img;
	Sprite sprite;
	TextureAtlas textureAtlas;
	Animation animation;
	float elapsedTime = 0;
	
	@Override
	public void create () {
		Gdx.graphics.setDisplayMode(500, 200, false);
		batch = new SpriteBatch();
		textureAtlas = new TextureAtlas(Gdx.files.internal("ameeno.pack"));
		animation = new Animation(1/15f, textureAtlas.getRegions());
	}
	
	public void dispose() {
		batch.dispose();
		textureAtlas.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		elapsedTime += Gdx.graphics.getDeltaTime();
		for(int i = 0; i < 500; i += 50){
			for(int j = 0; j < 200; j+= 50){
				 batch.draw(animation.getKeyFrame(elapsedTime, true), i, j);		
			}
		}
		batch.end();
	}
}
