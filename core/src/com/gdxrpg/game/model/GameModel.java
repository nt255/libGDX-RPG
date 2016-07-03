package com.gdxrpg.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameModel {

	private Map map;
	private MainCharacter mainCharacter;

	private Array<Character> characters; // NPCs
	private Array<Character> allCharacters;

	public GameModel(String mapName, String sheet, float x, float y) {
		map = new Map(mapName);
		mainCharacter = new MainCharacter(sheet, x, y);

		characters = new Array<Character>();
		allCharacters = new Array<Character>();
		allCharacters.add(mainCharacter);
	}

	public void addCharacter(String sheet, float x, float y) {
		Character c = new Character(sheet, x, y);
		characters.add(c);
		allCharacters.add(c);
	}

	/**
	 * Returns character object which has collided with rectangle.
	 * Otherwise, returns null if there is no collision. Does not
	 * check for overlap with (it)self.
	 * 
	 * @param r the collision rectangle
	 * @param self character to not check for overlap with
	 * @return the character object or null
	 */
	private Character getCharacterCollision(Rectangle r, Character self) {
		for (Character c : characters) {
			boolean isNotItself = self == null || !self.equals(c);
			if (r.overlaps(c.getCollisionRectangle()) && isNotItself)
				return c;
		}
		return null;
	}

	/**
	 * @see #getCharacterCollision(Rectangle)
	 * 
	 * @param r
	 * @return true if collision with a character, else false
	 */
	private boolean isCharacterCollision(Rectangle r) {
		return getCharacterCollision(r, null) != null;
	}

	public void update() {

		for (Character c : allCharacters) {

			Vector2 pos = c.getPosition();
			Vector2 vNor = c.getVelocityNor();

			// Vector2 tilePos = map.getTile(pos);
			// System.out.println((int) tilePos.x + ", " + (int) tilePos.y);

			Vector2 newPosX = new Vector2(pos).add(vNor.x, 0);
			Vector2 newPosY = new Vector2(pos).add(0, vNor.y);

			float w = c.getCollisionRectangleWidth();
			float h = c.getCollisionRectangleHeight();

			Rectangle playerRectangleX = new Rectangle(newPosX.x, newPosX.y, w, h);
			Rectangle playerRectangleY = new Rectangle(newPosY.x, newPosY.y, w, h);

			Character charX = getCharacterCollision(playerRectangleX, c);
			Character charY = getCharacterCollision(playerRectangleY, c);

			if (charX != null) {
				if (charX.getX() > mainCharacter.getX())
					charX.accelerateRight();
				else
					charX.accelerateLeft();
			}

			if (charY != null) {
				if (charY.getY() > mainCharacter.getY())
					charY.accelerateUp();
				else
					charY.accelerateDown();
			}

			boolean noCollisionX = !map.isCollision(playerRectangleX) &&
					!isCharacterCollision(playerRectangleX);
			boolean noCollisionY = !map.isCollision(playerRectangleY) &&
					!isCharacterCollision(playerRectangleY);

			if (noCollisionX && noCollisionY)
				c.changePosition(vNor.x, vNor.y);
			else if (noCollisionX)
				c.changePosition(vNor.x, 0);
			else if (noCollisionY)
				c.changePosition(0, vNor.y * c.getCollisionSpeed());
		}
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
