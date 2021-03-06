package com.gdxrpg.game.view;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gdxrpg.game.model.Map;

public class MapRenderer {

	private static final float BG_COLOR_RED   = 0.12f;
	private static final float BG_COLOR_GREEN = 0.12f;
	private static final float BG_COLOR_BLUE  = 0.12f;
	private static final float BG_COLOR_ALPHA = 1.0f;

	private OrthographicCamera camera;
	private TiledMapRenderer mapRenderer;
	private ShapeRenderer shapeRenderer;

	private Map map;

	private Array<Rectangle> edgeRectangles;

	public MapRenderer(Map map) {
		this.map = map;

		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		// camera.translate(this.map.getWidth() / 2, this.map.getHeight() / 2);
		camera.update();

		mapRenderer = new OrthogonalTiledMapRenderer(this.map.getTiledMap());
		mapRenderer.setView(camera);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.setColor(BG_COLOR_RED, BG_COLOR_GREEN,
				BG_COLOR_BLUE, BG_COLOR_ALPHA);

		MapLayer edgeLayer = map.getTiledMap().getLayers().get("edges");
		MapObjects edgeObjects = edgeLayer.getObjects();

		edgeRectangles = new Array<Rectangle>();
		for (MapObject obj : edgeObjects) {
			Rectangle r = ((RectangleMapObject) obj).getRectangle();
			edgeRectangles.add(r);
		}
	}

	public void setGL() {
		Gdx.gl.glClearColor(BG_COLOR_RED, BG_COLOR_GREEN,
				BG_COLOR_BLUE, BG_COLOR_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void renderBackground() {
		int[] layers = {0, 1, 2, 3, 4};
		mapRenderer.render(layers);
	}

	private Set<Vector2> getTiles(float x, float y, int w, int h) {
		// comparator not really necessary
		Set<Vector2> ret = new TreeSet<Vector2>(new Comparator<Vector2>() {
			public int compare(Vector2 v1, Vector2 v2) {
				if (v1.equals(v2))
					return 0;
				if (v1.x + v1.y < v2.x + v2.y)
					return -1;
				return 1;
			}
		});

		ret.add(map.getTile(x    , y    ));
		ret.add(map.getTile(x + w, y    ));
		ret.add(map.getTile(x    , y + h));
		ret.add(map.getTile(x + w, y + h));

		// System.out.println(ret.size());
		return ret;
	}

	public void renderForeground(float x, float y, int w, int h) {
		TiledMapTileLayer foregroundCells = map.getLayer(4);
		TiledMapTileLayer foreground      = map.getLayer(5);

		Set<Vector2> relevantTiles = getTiles(x, y, w, h);

		for (Vector2 c : relevantTiles) {
			int cx = (int) c.x;
			int cy = (int) c.y;
			foreground.setCell(cx, cy, foregroundCells.getCell(cx, cy));
		}

		int[] layers = {5};
		mapRenderer.render(layers);

		for (Vector2 c : relevantTiles)
			foreground.setCell((int) c.x, (int) c.y, null);
	}

	public void renderEdges() {
		shapeRenderer.begin(ShapeType.Filled);
		for (Rectangle r : edgeRectangles)
			shapeRenderer.rect(
					r.getX(), r.getY(), r.getWidth(), r.getHeight());
		shapeRenderer.end();
	}

	protected OrthographicCamera getCamera() {
		return camera;
	}

	public void updateRenderer(OrthographicCamera camera) {
		mapRenderer.setView(camera);
		shapeRenderer.setProjectionMatrix(camera.combined);
	}

}
