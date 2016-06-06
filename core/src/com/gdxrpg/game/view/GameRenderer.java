package com.gdxrpg.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gdxrpg.game.model.GameModel;
import com.gdxrpg.game.model.MainCharacter;

public class GameRenderer {

	private MapRenderer mapRenderer;

	private MainCharacter mainCharacter;
	private MainCharacterRenderer mainCharacterRenderer;

	public GameRenderer(GameModel gameModel) {
		mapRenderer = new MapRenderer(gameModel.getMap());

		OrthographicCamera camera = mapRenderer.getCamera();
		mainCharacter = gameModel.getMainCharacter();
		mainCharacterRenderer = 
				new MainCharacterRenderer(camera, mainCharacter);
	}

	public void render() {
		mapRenderer.setGL();
		mapRenderer.renderBackground();

		mainCharacterRenderer.render();

		float x = mainCharacter.getX();
		float y = mainCharacter.getY();

		mapRenderer.renderForeground(x, y, 20, 13);
		mapRenderer.renderEdges();
	}

}
