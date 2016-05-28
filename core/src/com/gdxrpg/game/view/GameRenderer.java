package com.gdxrpg.game.view;

import com.gdxrpg.game.model.GameModel;

public class GameRenderer {

	private MapRenderer mapRenderer;
	private MainCharacterRenderer mainCharacterRenderer;

	public GameRenderer(GameModel gameModel) {
		mapRenderer = new MapRenderer(gameModel.getMap());
		mainCharacterRenderer = 
				new MainCharacterRenderer(gameModel.getMainCharacter());
	}

	public void render() {
		mapRenderer.render();
		mainCharacterRenderer.render();
	}

}
