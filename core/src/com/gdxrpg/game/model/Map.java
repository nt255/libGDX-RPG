package com.gdxrpg.game.model;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Map {

	private TiledMap tiledMap;

	private float tilePixelWidth;
	private float tilePixelHeight;

	private float width;
	private float height;

	private Array<Rectangle> collisionRectangles;

	protected Map(String mapName) {
		tiledMap = new TmxMapLoader().load(mapName + ".tmx");
		TiledMapTileLayer l = (TiledMapTileLayer) tiledMap.getLayers().get(0);

		tilePixelWidth = l.getTileWidth();
		tilePixelHeight = l.getTileHeight();

		width = l.getWidth() * tilePixelWidth;
		height = l.getHeight() * tilePixelHeight;

		MapLayer collisionLayer = tiledMap.getLayers().get("collision");
		MapObjects collisionObjects = collisionLayer.getObjects();

		collisionRectangles = new Array<Rectangle>();
		for (MapObject obj : collisionObjects) {
			Rectangle r = ((RectangleMapObject) obj).getRectangle();
			collisionRectangles.add(r);
		}
	}

	protected boolean isCollision(Rectangle mainCharacter) {
		for (Rectangle r : collisionRectangles)
			if (mainCharacter.overlaps(r))
				return true;
		return false;
	}

	/* converts pixel coordinates to tile coordinates */
	public Vector2 getTile(Vector2 position) {
		return getTile(position.x, position.y);
	}

	public Vector2 getTile(float x, float y) {
		float tileX = (float) Math.floor(x / tilePixelWidth);
		float tileY = (float) Math.floor(y / tilePixelHeight);
		return new Vector2(tileX, tileY);
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}
