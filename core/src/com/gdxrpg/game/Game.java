package com.gdxrpg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Game extends ApplicationAdapter implements InputProcessor {

	private static final String CAFE_MAP = "cafe.tmx";

	private boolean downFlag = false;
	private boolean leftFlag = false;
	private boolean rightFlag = false;
	private boolean upFlag = false;

	private TiledMapRenderer mapRenderer;
	private OrthographicCamera camera;

	private MainCharacter mainCharacter;

	@Override
	public void create () {
		TiledMap m = new TmxMapLoader().load(CAFE_MAP);
		mapRenderer = new OrthogonalTiledMapRenderer(m);

		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		camera.translate(w/2f, h/2f);
		camera.update();

		mainCharacter = new MainCharacter();

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mapRenderer.setView(camera);
		mapRenderer.render();

		if (downFlag)
			mainCharacter.walkDown();
		if (leftFlag)
			mainCharacter.walkLeft();
		if (rightFlag)
			mainCharacter.walkRight();
		if (upFlag)
			mainCharacter.walkUp();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.DOWN)
			downFlag = true;
		if (keycode == Input.Keys.LEFT)
			leftFlag = true;
		if (keycode == Input.Keys.RIGHT)
			rightFlag = true;
		if (keycode == Input.Keys.UP)
			upFlag = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		downFlag = false;
		leftFlag = false;
		rightFlag = false;
		upFlag = false;
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
