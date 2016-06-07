package com.gdxrpg.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameModel {

	private Map map;
	private MainCharacter mainCharacter;

	public GameModel(String mapName, float x, float y) {
		map = new Map(mapName);
		mainCharacter = new MainCharacter(x, y);
	}

	@SuppressWarnings("static-access")
	public void update() {
		Vector2 pos = mainCharacter.getPosition();
		Vector2 vNor = mainCharacter.getVelocityNor();

		// Vector2 tilePos = map.getTile(pos);
		// System.out.println((int) tilePos.x + ", " + (int) tilePos.y);

		Vector2 newPosX = new Vector2(pos).add(vNor.x, 0);
		Vector2 newPosY = new Vector2(pos).add(0, vNor.y);

		float w = mainCharacter.getCollisionRectangleWidth();
		float h = mainCharacter.getCollisionRectangleHeight();

		Rectangle playerRectangleX = new Rectangle(newPosX.x, newPosX.y, w, h);
		Rectangle playerRectangleY = new Rectangle(newPosY.x, newPosY.y, w, h);

		boolean noCollisionX = !map.isCollision(playerRectangleX);
		boolean noCollisionY = !map.isCollision(playerRectangleY);

		if (noCollisionX && noCollisionY)
			mainCharacter.changePosition(vNor.x, vNor.y);
		else if (noCollisionX)
			mainCharacter.changePosition(vNor.x, 0);
		else if (noCollisionY)
			mainCharacter.changePosition(0,
					vNor.y * mainCharacter.getCollisionSpeed());
	}

	public Map getMap() {
		return map;
	}

	public MainCharacter getMainCharacter() {
		return mainCharacter;
	}

}
