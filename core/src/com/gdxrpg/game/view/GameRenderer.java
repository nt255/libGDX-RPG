package com.gdxrpg.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gdxrpg.game.model.GameModel;
import com.gdxrpg.game.model.MainCharacter;

public class GameRenderer {

	private MapRenderer mapRenderer;

	private MainCharacter mainCharacter;
	private CharacterRenderer characterRenderer;

	public GameRenderer(GameModel gameModel) {
		mapRenderer = new MapRenderer(gameModel.getMap());

		OrthographicCamera camera = mapRenderer.getCamera();
		mainCharacter = gameModel.getMainCharacter();
		characterRenderer =
				new CharacterRenderer(camera, mainCharacter);
	}

	public void render() {
		mapRenderer.setGL();
		mapRenderer.renderBackground();

		characterRenderer.render();

		float x = mainCharacter.getX();
		float y = mainCharacter.getY();
		int w = (int) mainCharacter.getCollisionRectangleWidth();
		int h = (int) mainCharacter.getCollisionRectangleHeight();

		mapRenderer.renderForeground(x, y, w, h);
		mapRenderer.renderEdges();
	}

}
