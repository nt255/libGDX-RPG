package com.gdxrpg.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gdxrpg.game.model.GameModel;

public class GameRenderer {

	private MapRenderer mapRenderer;
	private MainCharacterRenderer mainCharacterRenderer;

	public GameRenderer(GameModel gameModel) {
		mapRenderer = new MapRenderer(gameModel.getMap());

		OrthographicCamera camera = mapRenderer.getCamera();
		mainCharacterRenderer = 
				new MainCharacterRenderer(camera, gameModel.getMainCharacter());
	}

	public void render() {
		mapRenderer.setGL();
		mapRenderer.renderBackground();

		mainCharacterRenderer.render();

		mapRenderer.renderForeground();
		mapRenderer.renderEdges();
	}

}
