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

		else if (movingDown)
			mainCharacter.walkDown();
		else if (movingLeft)
			mainCharacter.walkLeft();
		else if (movingRight)
			mainCharacter.walkRight();
		else if (movingUp)
			mainCharacter.walkUp();
	}

	@Override
	public boolean keyDown(int keycode) {
		facingDown = false;
		facingLeft = false;
		facingRight = false;
		facingUp = false;

		if (keycode == Input.Keys.DOWN)
			movingDown = true;
		if (keycode == Input.Keys.LEFT)
			movingLeft = true;
		if (keycode == Input.Keys.RIGHT)
			movingRight = true;
		if (keycode == Input.Keys.UP)
			movingUp = true;

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.DOWN)
			facingDown = true;
		if (keycode == Input.Keys.LEFT)
			facingLeft = true;
		if (keycode == Input.Keys.RIGHT)
			facingRight = true;
		if (keycode == Input.Keys.UP)
			facingUp = true;

		movingDown = false;
		movingLeft = false;
		movingRight = false;
		movingUp = false;

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
