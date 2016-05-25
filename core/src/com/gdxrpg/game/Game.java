package com.gdxrpg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Game extends ApplicationAdapter implements InputProcessor {

	private static final String CAFE_MAP = "cafe.tmx";

	private static final float BG_COLOR_RED   = 0.1f;
	private static final float BG_COLOR_GREEN = 0.1f;
	private static final float BG_COLOR_BLUE  = 0.1f;
	private static final float BG_COLOR_ALPHA = 1.0f;

	private static final float CROP_PX_SIDE   = 23f;
	private static final float CROP_PX_TOP    = 17f;
	private static final float CROP_PX_BOTTOM = 16f;

	private boolean facingDown = true; // faces down by default
	private boolean facingLeft = false;
	private boolean facingRight = false;
	private boolean facingUp = false;

	private boolean movingDown = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingUp = false;

	private float mapWidth;
	private float mapHeight;

	private OrthographicCamera camera;
	private TiledMapRenderer mapRenderer;
	private ShapeRenderer shapeRenderer;

	private MainCharacter mainCharacter;

	@Override
	public void create () {
		TiledMap m = new TmxMapLoader().load(CAFE_MAP);
		TiledMapTileLayer l = (TiledMapTileLayer) m.getLayers().get(0);

		mapWidth = l.getWidth() * l.getTileWidth();
		mapHeight = l.getHeight() * l.getTileHeight();

		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		camera.translate(mapWidth/2, mapHeight/2);
		camera.update();

		mapRenderer = new OrthogonalTiledMapRenderer(m);
		mapRenderer.setView(camera);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.setColor(BG_COLOR_RED, BG_COLOR_GREEN,
				BG_COLOR_BLUE, BG_COLOR_ALPHA);

		mainCharacter = new MainCharacter();

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(BG_COLOR_RED, BG_COLOR_GREEN,
				BG_COLOR_BLUE, BG_COLOR_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mapRenderer.render();

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.rect(0, 0, CROP_PX_SIDE, mapHeight);
		shapeRenderer.rect(0, 0, mapWidth, CROP_PX_BOTTOM);
		shapeRenderer.rect(mapWidth, 0, -CROP_PX_SIDE, mapHeight);
		shapeRenderer.rect(0, mapHeight, mapWidth, -CROP_PX_TOP);
		shapeRenderer.end();

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
