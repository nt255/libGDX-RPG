package com.gdxrpg.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdxrpg.game.model.MainCharacter;

public class CharacterRenderer {

	private static final int SHEET_COLUMNS = 4;
	private static final int SHEET_ROWS = 4;
	private static final float FRAME_DURATION = 0.25f;

	private float stateTime;

	private TextureRegion downFrame;
	private TextureRegion leftFrame;
	private TextureRegion rightFrame;
	private TextureRegion upFrame;

	private Animation downAnimation;
	private Animation leftAnimation;
	private Animation rightAnimation;
	private Animation upAnimation;

	private MainCharacter mainCharacter;

	public CharacterRenderer(
			OrthographicCamera camera, MainCharacter mainCharacter) {
		this.mainCharacter = mainCharacter;

		stateTime = 0f;

		Texture t = new Texture(
				Gdx.files.internal(mainCharacter.getAnimationSheet()));
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

	private void renderFrame(TextureRegion t, SpriteBatch s) {
		float x = mainCharacter.getX() - 7;
		float y = mainCharacter.getY() - 2;

		s.begin();
		s.draw(t, x, y);
		s.end();
	}

	private void renderMovement(Animation a, SpriteBatch s) {
		stateTime += Gdx.graphics.getDeltaTime();
		renderFrame(a.getKeyFrame(stateTime, true), s);
	}

	public void render(SpriteBatch s) {
		if (mainCharacter.isFacingDown())
			renderFrame(downFrame, s);
		else if (mainCharacter.isFacingLeft())
			renderFrame(leftFrame, s);
		else if (mainCharacter.isFacingRight())
			renderFrame(rightFrame, s);
		else if (mainCharacter.isFacingUp())
			renderFrame(upFrame, s);

		else if (mainCharacter.isMovingLeft())
			renderMovement(leftAnimation, s);
		else if (mainCharacter.isMovingRight())
			renderMovement(rightAnimation, s);
		else if (mainCharacter.isMovingDown())
			renderMovement(downAnimation, s);
		else if (mainCharacter.isMovingUp())
			renderMovement(upAnimation, s);
	}

}
