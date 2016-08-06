package com.gdxrpg.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameModel {

	private Area area;

	public GameModel(Area area) {
		this.area = area;
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
		for (Character c : area.getCharacters()) {
			boolean isNotItself = self == null || !self.equals(c);
			if (isNotItself && r.overlaps(c.getCollisionRectangle()))
				return c;
		}
		return null;
	}

	/**
	 * Returns true if provided character will collide with
	 * something on the map given velocity v.
	 * 
	 * @param c the character object
	 * @param v the velocity vector applied to c
	 * @return true if c (w/ v) will collide, otherwise false
	 */
	private boolean isFutureCollision(Character c, Vector2 v) {
		Vector2 pos = c.getPosition();
		Vector2 newPos = new Vector2(pos).add(v);

		float w = c.getCollisionRectangleWidth();
		float h = c.getCollisionRectangleHeight();

		Rectangle r = new Rectangle(newPos.x, newPos.y, w, h);

		return area.getMap().isCollision(r);
	}

	/**
	 * Changes position of charX (and charY) based on the
	 * position of mainCharacter by PUSH_SPEED of character.
	 * 
	 * @param charX the character which has collided with
	 *              mainCharacter horizontally
	 * @param charY the character which has collided with
	 *              mainCharacter vertically
	 */
	private void pushCharacterOnCollision(Character charX, Character charY) {
		if (charX != null && charX.isPushable()) {
			float ps = charX.getPushSpeed();
			if (charX.getX() > area.getMainCharacter().getX()) {
				if (!isFutureCollision(charX, new Vector2(ps, 0)))
					charX.pushRight();
			}
			else
				if (!isFutureCollision(charX, new Vector2(-ps, 0)))
					charX.pushLeft();
		}

		if (charY != null && charY.isPushable()) {
			float ps = charY.getPushSpeed();
			if (charY.getY() > area.getMainCharacter().getY()) {
				if (!isFutureCollision(charY, new Vector2(0, ps)))
					charY.pushUp();
			}
			else
				if (!isFutureCollision(charY, new Vector2(0, -ps)))
					charY.pushDown();
		}
	}

	public void update() {
		Vector2 pos = area.getMainCharacter().getPosition();
		Vector2 vNor = area.getMainCharacter().getVelocityNor();

		// Vector2 tilePos = map.getTile(pos);
		// System.out.println((int) tilePos.x + ", " + (int) tilePos.y);

		Vector2 newPosX = new Vector2(pos).add(vNor.x, 0);
		Vector2 newPosY = new Vector2(pos).add(0, vNor.y);

		float w = area.getMainCharacter().getCollisionRectangleWidth();
		float h = area.getMainCharacter().getCollisionRectangleHeight();

		Rectangle playerRectangleX = new Rectangle(newPosX.x, newPosX.y, w, h);
		Rectangle playerRectangleY = new Rectangle(newPosY.x, newPosY.y, w, h);

		Character charX = getCharacterCollision(playerRectangleX, area.getMainCharacter());
		Character charY = getCharacterCollision(playerRectangleY, area.getMainCharacter());

		pushCharacterOnCollision(charX, charY);

		boolean noCollisionX = !area.getMap().isCollision(playerRectangleX) &&
				charX == null;
		boolean noCollisionY = !area.getMap().isCollision(playerRectangleY) &&
				charY == null;

		if (noCollisionX && noCollisionY)
			area.getMainCharacter().changePosition(vNor.x, vNor.y);
		else if (noCollisionX)
			area.getMainCharacter().changePosition(vNor.x, 0);
		else if (noCollisionY)
			area.getMainCharacter().changePosition(0, vNor.y *
					area.getMainCharacter().getCollisionSpeed());

		Rectangle playerRectangle = new Rectangle(pos.x, pos.y, w, h);
		area.getMap().isOnPortal(playerRectangle);
	}

	public Map getMap() {
		return area.getMap();
	}

	public MainCharacter getMainCharacter() {
		return area.getMainCharacter();
	}

	public Array<Character> getCharacters() {
		return area.getCharacters();
	}

}
