package com.gdxrpg.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gdxrpg.game.view.GameRenderer;

public class CameraInputProcessor implements InputProcessor {

	private GameRenderer gameRenderer;
	private OrthographicCamera camera;

	public CameraInputProcessor(GameRenderer gameRenderer) {
		this.gameRenderer = gameRenderer;
		this.camera = gameRenderer.getCamera();
	}

	private void translateCamera(float x, float y) {
		camera.translate(x, y);
		camera.update();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.DOWN)
			translateCamera(0, -5);
		if (keycode == Input.Keys.LEFT) 
			translateCamera(-5, 0);
		if (keycode == Input.Keys.RIGHT) 
			translateCamera(5, 0);
		if (keycode == Input.Keys.UP) 
			translateCamera(0, 5);

		gameRenderer.updateRenderer(camera);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
