package com.gdxrpg.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.gdxrpg.game.model.GameModel;
import com.gdxrpg.game.model.MainCharacter;
import com.gdxrpg.game.model.Character;

public class GameRenderer {

	private MapRenderer mapRenderer;

	private SpriteBatch spriteBatch;
	private MainCharacter mainCharacter;
	private CharacterRenderer mainCharacterRenderer;
	private Array<CharacterRenderer> characterRenderers;

	public GameRenderer(GameModel gameModel) {
		mapRenderer = new MapRenderer(gameModel.getMap());

		OrthographicCamera camera = mapRenderer.getCamera();

		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);

		mainCharacter = gameModel.getMainCharacter();
		mainCharacterRenderer =
				new CharacterRenderer(camera, mainCharacter);

		characterRenderers = new Array<CharacterRenderer>();
		for (Character c : gameModel.getCharacters())
			characterRenderers.add(new CharacterRenderer(camera, c));
	}

	public void render() {
		mapRenderer.setGL();
		mapRenderer.renderBackground();

		for (CharacterRenderer cr : characterRenderers)
			cr.render(spriteBatch);

		mapRenderer.renderMidground();

		mainCharacterRenderer.render(spriteBatch);

		float x = mainCharacter.getX();
		float y = mainCharacter.getY();
		int w = (int) mainCharacter.getCollisionRectangleWidth();
		int h = (int) mainCharacter.getCollisionRectangleHeight();

		mapRenderer.renderForeground(x, y, w, h);
		mapRenderer.renderEdges();
	}

}
