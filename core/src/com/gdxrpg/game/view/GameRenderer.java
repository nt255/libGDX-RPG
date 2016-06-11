package com.gdxrpg.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdxrpg.game.model.GameModel;
import com.gdxrpg.game.model.MainCharacter;

public class GameRenderer {

	private MapRenderer mapRenderer;

	private SpriteBatch spriteBatch;
	private MainCharacter mainCharacter;
	private CharacterRenderer characterRenderer;

	public GameRenderer(GameModel gameModel) {
		mapRenderer = new MapRenderer(gameModel.getMap());

		OrthographicCamera camera = mapRenderer.getCamera();

		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);

		mainCharacter = gameModel.getMainCharacter();
		characterRenderer =
				new CharacterRenderer(camera, mainCharacter);
	}

	public void render() {
		mapRenderer.setGL();
		mapRenderer.renderBackground();

		characterRenderer.render(spriteBatch);

		float x = mainCharacter.getX();
		float y = mainCharacter.getY();
		int w = (int) mainCharacter.getCollisionRectangleWidth();
		int h = (int) mainCharacter.getCollisionRectangleHeight();

		mapRenderer.renderForeground(x, y, w, h);
		mapRenderer.renderEdges();
	}

}
