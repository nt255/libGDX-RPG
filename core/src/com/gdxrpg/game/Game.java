package com.gdxrpg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Game extends ApplicationAdapter implements InputProcessor {

	private boolean facingDown = true; // faces down by default
	private boolean facingLeft = false;
	private boolean facingRight = false;
	private boolean facingUp = false;

	private boolean movingDown = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingUp = false;

	private GameMapRenderer mapRenderer;
	private MainCharacter mainCharacter;

	@Override
	public void create () {
		mapRenderer = new GameMapRenderer();
		mainCharacter = new MainCharacter();
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		mapRenderer.render();

		if (facingDown)
			mainCharacter.faceDown();
		else if (facingLeft)
			mainCharacter.faceLeft();
		else if (facingRight)
			mainCharacter.faceRight();
		else if (facingUp)
			mainCharacter.faceUp();

		else if (movingDown) {
			mainCharacter.walkDown();
			mainCharacter.changePosition(0, -1);
		}
		else if (movingLeft) {
			mainCharacter.walkLeft();
			mainCharacter.changePosition(-1, 0);
		}
		else if (movingRight) {
			mainCharacter.walkRight();
			mainCharacter.changePosition(1, 0);
		}
		else if (movingUp) {
			mainCharacter.walkUp();
			mainCharacter.changePosition(0, 1);
		}
	}

	private void setMoving() {
		facingDown = false;
		facingLeft = false;
		facingRight = false;
		facingUp = false;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.S) {
			setMoving();
			movingDown = true;
		}
		if (keycode == Input.Keys.A) {
			setMoving();
			movingLeft = true;
		}
		if (keycode == Input.Keys.D) {
			setMoving();
			movingRight = true;
		}
		if (keycode == Input.Keys.W) {
			setMoving();
			movingUp = true;
		}

		return false;
	}

	private boolean notMoving() {
		return !movingDown && !movingLeft
				&& !movingRight && !movingUp;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.S) {
			movingDown = false;
			if (notMoving())
				facingDown = true;
		}
		if (keycode == Input.Keys.A) {
			movingLeft = false;
			if (notMoving())
				facingLeft = true;
		}
		if (keycode == Input.Keys.D) {
			movingRight = false;
			if (notMoving())
				facingRight = true;
		}
		if (keycode == Input.Keys.W) {
			movingUp = false;
			if (notMoving())
				facingUp = true;
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
