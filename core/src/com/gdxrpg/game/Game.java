package com.gdxrpg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.gdxrpg.game.model.GameModel;
import com.gdxrpg.game.view.GameRenderer;
import com.gdxrpg.game.controller.MovementInputProcessor;

public class Game extends ApplicationAdapter {

	private GameModel gameModel;
	private GameRenderer gameRenderer;
	private MovementInputProcessor inputProcessor;

	@Override
	public void create () {
		float startingX = 300;
		float startingY = 100;

		gameModel = new GameModel(startingX, startingY);
		gameRenderer = new GameRenderer(gameModel);
		inputProcessor = new MovementInputProcessor(gameModel);

		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render () {
		gameModel.update();
		gameRenderer.render();
	}

}
