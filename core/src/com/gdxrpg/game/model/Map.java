package com.gdxrpg.game.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Map {

	private TiledMap tiledMap;

	private float width;
	private float height;

	protected Map(String mapName) {
		tiledMap = new TmxMapLoader().load(mapName + ".tmx");
		TiledMapTileLayer l = (TiledMapTileLayer) tiledMap.getLayers().get(0);

		width = l.getWidth() * l.getTileWidth();
		height = l.getHeight() * l.getTileHeight();
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
