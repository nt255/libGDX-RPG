package com.gdxrpg.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.gdxrpg.game.model.GameModel;
import com.gdxrpg.game.model.MainCharacter;

public class MovementInputProcessor implements InputProcessor {

	private MainCharacter mainCharacter;

	public MovementInputProcessor(GameModel gameModel) {
		this.mainCharacter = gameModel.getMainCharacter();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.S)
			mainCharacter.setMovingDown();
		if (keycode == Input.Keys.A) 
			mainCharacter.setMovingLeft();
		if (keycode == Input.Keys.D) 
			mainCharacter.setMovingRight();
		if (keycode == Input.Keys.W) 
			mainCharacter.setMovingUp();
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.S)
			mainCharacter.unsetMovingDown();
		if (keycode == Input.Keys.A) 
			mainCharacter.unsetMovingLeft();
		if (keycode == Input.Keys.D) 
			mainCharacter.unsetMovingRight();
		if (keycode == Input.Keys.W) 
			mainCharacter.unsetMovingUp();
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
