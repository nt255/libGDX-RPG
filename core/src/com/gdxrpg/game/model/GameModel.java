package com.gdxrpg.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameModel {

	private Map map;
	private MainCharacter mainCharacter;
	private Array<Character> characters; // NPCs

	public GameModel(String mapName, String sheet, float x, float y) {
		map = new Map(mapName);
		mainCharacter = new MainCharacter(sheet, x, y);
		characters = new Array<Character>();
	}

	public void addCharacter(String sheet, float x, float y) {
		characters.add(new Character(sheet, x, y));
	}

	/**
	 * Returns character object which has collided with mainCharacter.
	 * Otherwise, returns null if there is no collision.
	 * 
	 * @param mainCharacter the collision rectangle
	 * @return the character object or null
	 */
	private Character getCharacterCollision(Rectangle mainCharacter) {
		for (Character c : characters)
			if (mainCharacter.overlaps(c.getCollisionRectangle()))
				return c;
		return null;
	}

	/**
	 * @see #getCharacterCollision(Rectangle)
	 * 
	 * @param r
	 * @return true if collision with a character, else false
	 */
	private boolean isCharacterCollision(Rectangle r) {
		return getCharacterCollision(r) != null;
	}

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

		boolean noCollisionX = !map.isCollision(playerRectangleX) &&
				!isCharacterCollision(playerRectangleX);
		boolean noCollisionY = !map.isCollision(playerRectangleY) &&
				!isCharacterCollision(playerRectangleY);

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

	public Array<Character> getCharacters() {
		return characters;
	}

}
