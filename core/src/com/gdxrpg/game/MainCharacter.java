package com.gdxrpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainCharacter {

	private static final String ANIMATION_SHEET = "pirate_m1.png";
	private static final int SHEET_COLUMNS = 4;
	private static final int SHEET_ROWS = 4;
	private static final float FRAME_DURATION = 0.25f;

	private float stateTime;
	private SpriteBatch spriteBatch;

	private TextureRegion downFrame;
	private TextureRegion leftFrame;
	private TextureRegion rightFrame;
	private TextureRegion upFrame;

	private Animation downAnimation;
	private Animation leftAnimation;
	private Animation rightAnimation;
	private Animation upAnimation;

	public MainCharacter() {
		stateTime = 0f;
		spriteBatch = new SpriteBatch();

		Texture t = new Texture(Gdx.files.internal(ANIMATION_SHEET));
		TextureRegion[][] frames = TextureRegion.split(t, 
				t.getWidth() / SHEET_COLUMNS, t.getHeight() / SHEET_ROWS);

		downFrame  = frames[0][0];
		leftFrame  = frames[1][0];
		rightFrame = frames[2][0];
		upFrame    = frames[3][0];

		downAnimation  = new Animation(FRAME_DURATION, frames[0]);
		leftAnimation  = new Animation(FRAME_DURATION, frames[1]);
		rightAnimation = new Animation(FRAME_DURATION, frames[2]);
		upAnimation    = new Animation(FRAME_DURATION, frames[3]);
	}

	private void render(TextureRegion t) {
		spriteBatch.begin();
		spriteBatch.draw(t, 300, 100);
		spriteBatch.end();
	}

	private void renderMovement(Animation a) {
		stateTime += Gdx.graphics.getDeltaTime();
		render(a.getKeyFrame(stateTime, true));
	}

	public void faceDown() {
		render(downFrame);
	}

	public void faceLeft() {
		render(leftFrame);
	}

	public void faceRight() {
		render(rightFrame);
	}

	public void faceUp() {
		render(upFrame);
	}

	public void walkDown() {
		renderMovement(downAnimation);
	}

	public void walkLeft() {
		renderMovement(leftAnimation);
	}

	public void walkRight() {
		renderMovement(rightAnimation);
	}

	public void walkUp() {
		renderMovement(upAnimation);
	}

}
