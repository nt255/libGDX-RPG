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
	private Array<RectangleMapObject> portalRectangles;

	protected Map(String mapName) {
		tiledMap = new TmxMapLoader().load(mapName + ".tmx");
		TiledMapTileLayer l = getLayer(0);

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

		MapLayer portalLayer = tiledMap.getLayers().get("portals");
		MapObjects portalObjects = portalLayer.getObjects();
		portalRectangles = portalObjects.getByType(RectangleMapObject.class);
	}

	/**
	 * Checks for collisions between mainCharacter and
	 * any rectangle in collisionRectangles.
	 * 
	 * @param mainCharacter rectangle to be checked for collisions
	 * @return true if at least one collision, otherwise false
	 */
	protected boolean isCollision(Rectangle mainCharacter) {
		for (Rectangle r : collisionRectangles)
			if (mainCharacter.overlaps(r))
				return true;
		return false;
	}

	/**
	 * Checks if mainCharacter is over a portal. If so,
	 * prints message containing the portal's name.
	 * 
	 * @see #isCollision(Rectangle)
	 * 
	 * @param mainCharacter the player character
	 * @return true if over one or more portal(s), otherwise false
	 */
	protected void isOnPortal(Rectangle mainCharacter) {
		for (RectangleMapObject r : portalRectangles)
			if (mainCharacter.overlaps(r.getRectangle()))
				System.out.println("portal: " + r.getName());
	}

	/**
	 * @see #getTile(float, float)
	 */
	public Vector2 getTile(Vector2 position) {
		return getTile(position.x, position.y);
	}

	/**
	 * Converts pixel coordinates of a map
	 * to tile coordinates.
	 * 
	 * @param x px coordinates along x
	 * @param y px coordinates along y
	 * @return the tile coordinates
	 */
	public Vector2 getTile(float x, float y) {
		float tileX = (float) Math.floor(x / tilePixelWidth);
		float tileY = (float) Math.floor(y / tilePixelHeight);
		return new Vector2(tileX, tileY);
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public TiledMapTileLayer getLayer(int i) {
		return (TiledMapTileLayer) tiledMap.getLayers().get(i);
	}

	public float getTilePixelWidth() {
		return tilePixelWidth;
	}

	public float getTilePixelHeight() {
		return tilePixelHeight;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}
