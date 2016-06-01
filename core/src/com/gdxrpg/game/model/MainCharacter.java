package com.gdxrpg.game.model;

import com.badlogic.gdx.math.Vector2;

public class MainCharacter {

	private boolean facingDown = true; // faces down by default
	private boolean facingLeft = false;
	private boolean facingRight = false;
	private boolean facingUp = false;

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 velocityNor;

	protected MainCharacter(float x, float y) {
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
