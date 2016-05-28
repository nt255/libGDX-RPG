package com.gdxrpg.game.model;

public class MainCharacter {

	private boolean facingDown = true; // faces down by default
	private boolean facingLeft = false;
	private boolean facingRight = false;
	private boolean facingUp = false;

	private boolean movingDown = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingUp = false;

	private float x;
	private float y;

	protected MainCharacter(float x, float y) {
		this.x = x;
		this.y = y;
	}

	protected void changePosition(float x, float y) {
		this.x += x;
		this.y += y;
	}

	private void setMoving() {
		facingDown = false;
		facingLeft = false;
		facingRight = false;
		facingUp = false;
	}

	private boolean isNotMoving() {
		return !movingDown && !movingLeft
				&& !movingRight && !movingUp;
	}

	public void setMovingDown() {
		setMoving();
		movingDown = true;
	}

	public void setMovingLeft() {
		setMoving();
		movingLeft = true;
	}

	public void setMovingRight() {
		setMoving();
		movingRight = true;
	}

	public void setMovingUp() {
		setMoving();
		movingUp = true;
	}

	public void unsetMovingDown() {
		movingDown = false;
		if (isNotMoving())
			facingDown = true;
	}

	public void unsetMovingLeft() {
		movingLeft = false;
		if (isNotMoving())
			facingLeft = true;
	}

	public void unsetMovingRight() {
		movingRight = false;
		if (isNotMoving())
			facingRight = true;
	}

	public void unsetMovingUp() {
		movingUp = false;
		if (isNotMoving())
			facingUp = true;
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
		return movingDown;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
