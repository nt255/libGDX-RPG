package com.gdxrpg.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Character {

	private static final float COLLISION_RECTANGLE_WIDTH = 20;
	private static final float COLLISION_RECTANGLE_HEIGHT = 13;
	private static final float COLLISION_SPEED = 0.7f;

	/**
	 * Speed NPCs move at when run into.
	 */
	private static final float PUSH_SPEED = 0.5f;
	private static final boolean IS_PUSHABLE = true;

	private String animationSheet;

	private boolean facingDown = true; // faces down by default
	private boolean facingLeft = false;
	private boolean facingRight = false;
	private boolean facingUp = false;

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 velocityNor;

	protected Character(String sheet, float x, float y) {
		animationSheet = sheet;
		position = new Vector2(x, y);
		velocity = Vector2.Zero;
		velocityNor = Vector2.Zero;
	}

	protected Vector2 getPosition() {
		return position;
	}

	protected Vector2 getVelocityNor() {
		return velocityNor;
	}

	protected void changePosition(float x, float y) {
		this.position.add(x, y);
	}

	public float getCollisionRectangleWidth() {
		return COLLISION_RECTANGLE_WIDTH;
	}

	public float getCollisionRectangleHeight() {
		return COLLISION_RECTANGLE_HEIGHT;
	}

	public float getCollisionSpeed() {
		return COLLISION_SPEED;
	}

	public float getPushSpeed() {
		return PUSH_SPEED;
	}

	public boolean isPushable() {
		return IS_PUSHABLE;
	}

	/**
	 * Returns rectangle based on character position
	 * for use with collision detection. Offsets
	 * rectangle values for improved visuals.
	 * 
	 * @return the collision rectangle
	 */
	public Rectangle getCollisionRectangle() {
		return new Rectangle(getX() + 2, getY() + 1,
				getCollisionRectangleWidth() - 4,
				getCollisionRectangleHeight() - 2);
	}

	public String getAnimationSheet() {
		return animationSheet;
	}

	private void setMoving() {
		facingDown = false;
		facingLeft = false;
		facingRight = false;
		facingUp = false;
	}

	private boolean isNotMoving() {
		return velocity.isZero();
	}

	public void accelerateDown() {
		velocity.add(0, -1);
		velocityNor = new Vector2(velocity).nor();
		if (isNotMoving())
			facingUp = true;
		else
			setMoving();
	}

	public void accelerateLeft() {
		velocity.add(-1, 0);
		velocityNor = new Vector2(velocity).nor();
		if (isNotMoving())
			facingRight = true;
		else
			setMoving();
	}

	public void accelerateRight() {
		velocity.add(1, 0);
		velocityNor = new Vector2(velocity).nor();
		if (isNotMoving())
			facingLeft = true;
		else
			setMoving();
	}

	public void accelerateUp() {
		velocity.add(0, 1);
		velocityNor = new Vector2(velocity).nor();
		if (isNotMoving())
			facingDown = true;
		else
			setMoving();
	}

	public void pushDown() {
		position.add(0, -PUSH_SPEED);
	}

	public void pushLeft() {
		position.add(-PUSH_SPEED, 0);
	}

	public void pushRight() {
		position.add(PUSH_SPEED, 0);
	}

	public void pushUp() {
		position.add(0, PUSH_SPEED);
	}

	public boolean isFacingDown() {
		return facingDown;
	}

	public boolean isFacingLeft() {
		return facingLeft;
	}

	public boolean isFacingRight() {
		return facingRight;
	}

	public boolean isFacingUp() {
		return facingUp;
	}

	public boolean isMovingDown() {
		return velocity.y < 0;
	}

	public boolean isMovingLeft() {
		return velocity.x < 0;
	}

	public boolean isMovingRight() {
		return velocity.x > 0;
	}

	public boolean isMovingUp() {
		return velocity.y > 0;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

}
