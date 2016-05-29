package com.gdxrpg.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.gdxrpg.game.model.Map;

public class MapRenderer {

	private static final float BG_COLOR_RED   = 0.1f;
	private static final float BG_COLOR_GREEN = 0.1f;
	private static final float BG_COLOR_BLUE  = 0.1f;
	private static final float BG_COLOR_ALPHA = 1.0f;

	private static final float CROP_PX_SIDE   = 23f;
	private static final float CROP_PX_TOP    = 17f;
	private static final float CROP_PX_BOTTOM = 16f;

	private OrthographicCamera camera;
	private TiledMapRenderer mapRenderer;
	private ShapeRenderer shapeRenderer;

	private Map map;

	public MapRenderer(Map map) {
		this.map = map;

		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		camera.translate(this.map.getWidth() / 2, this.map.getHeight() / 2);
		camera.update();

		mapRenderer = new OrthogonalTiledMapRenderer(this.map.getTiledMap());
		mapRenderer.setView(camera);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.setColor(BG_COLOR_RED, BG_COLOR_GREEN,
				BG_COLOR_BLUE, BG_COLOR_ALPHA);
	}

	public void render() {
		Gdx.gl.glClearColor(BG_COLOR_RED, BG_COLOR_GREEN,
				BG_COLOR_BLUE, BG_COLOR_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mapRenderer.render();

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.rect(0, 0, CROP_PX_SIDE, map.getHeight());
		shapeRenderer.rect(0, 0, map.getWidth(), CROP_PX_BOTTOM);
		shapeRenderer.rect(map.getWidth(), 0, -CROP_PX_SIDE, map.getHeight());
		shapeRenderer.rect(0, map.getHeight(), map.getWidth(), -CROP_PX_TOP);
		shapeRenderer.end();
	}

}
