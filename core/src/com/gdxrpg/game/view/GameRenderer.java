package com.gdxrpg.game.view;

import java.util.Comparator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.gdxrpg.game.model.GameModel;
import com.gdxrpg.game.model.MainCharacter;
import com.gdxrpg.game.model.Character;

public class GameRenderer {

	private MapRenderer mapRenderer;
	private OrthographicCamera camera;
	private CameraRefresher cameraRefresher;

	private SpriteBatch spriteBatch;
	private Array<CharacterRenderer> characterRenderers;

	private MainCharacter mainCharacter;

	public GameRenderer(GameModel gameModel) {
		mapRenderer = new MapRenderer(gameModel.getMap());
		camera = mapRenderer.getCamera();
		cameraRefresher = new CameraRefresher(camera);

		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);

		mainCharacter = gameModel.getMainCharacter();
		camera.position.set(mainCharacter.getX(), mainCharacter.getY(), 0);
		camera.update();

		characterRenderers = new Array<CharacterRenderer>();
		characterRenderers.add(new CharacterRenderer(camera, mainCharacter));
		for (Character c : gameModel.getCharacters())
			characterRenderers.add(new CharacterRenderer(camera, c));
	}

	public void render() {
		cameraRefresher.refresh(mainCharacter);
		updateRenderer(camera);

		mapRenderer.setGL();
		mapRenderer.renderBackground();
		mapRenderer.renderEdges();

		characterRenderers.sort(new Comparator<CharacterRenderer>() {
			public int compare(CharacterRenderer r1, CharacterRenderer r2) {
				if (r1.getY() == r2.getY())
					return 0;
				if (r1.getY() > r2.getY())
					return -1;
				return 1;
			}
		});

		for (CharacterRenderer cr : characterRenderers) {
			cr.render(spriteBatch);
			float x = cr.getX();
			float y = cr.getY();
			int w = (int) cr.getCollisionRectangleWidth();
			int h = (int) cr.getCollisionRectangleHeight();
			mapRenderer.renderForeground(x, y, w, h);
		}
	}

	public OrthographicCamera getCamera() {
		return mapRenderer.getCamera();
	}

	public void updateRenderer(OrthographicCamera camera) {
		mapRenderer.updateRenderer(camera);
		spriteBatch.setProjectionMatrix(camera.combined);
	}

}
