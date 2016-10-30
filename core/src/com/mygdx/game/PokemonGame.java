package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gameassets.AssetLoader;
import com.mygdx.gamescreens.GameScreen;

public class PokemonGame extends Game {
	@Override
	public void create(){
		Gdx.app.log("Game","created");
		AssetLoader.load();
		this.setScreen(new GameScreen());

	}
	@Override
	public void dispose(){
		super.dispose();
	}
}
