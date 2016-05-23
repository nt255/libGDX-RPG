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

	private Animation downAnimation;
	private Animation leftAnimation;
	private Animation rightAnimation;
	private Animation upAnimation;

	private SpriteBatch spriteBatch;
	private TextureRegion currentFrame;

	public MainCharacter() {
		stateTime = 0f;

		Texture t = new Texture(Gdx.files.internal(ANIMATION_SHEET));
		TextureRegion[][] frames = TextureRegion.split(t, 
				t.getWidth() / SHEET_COLUMNS, t.getHeight() / SHEET_ROWS);

		downAnimation  = new Animation(FRAME_DURATION, frames[0]);
		leftAnimation  = new Animation(FRAME_DURATION, frames[1]);
		rightAnimation = new Animation(FRAME_DURATION, frames[2]);
		upAnimation    = new Animation(FRAME_DURATION, frames[3]);

		spriteBatch = new SpriteBatch();
	}

	private void render(Animation a) {
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = a.getKeyFrame(stateTime, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, 300, 100);
		spriteBatch.end();
	}

	public void walkDown() {
		render(downAnimation);
	}

	public void walkLeft() {
		render(leftAnimation);
	}

	public void walkRight() {
		render(rightAnimation);
	}

	public void walkUp() {
		render(upAnimation);
	}

}
