package com.gdxrpg.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdxrpg.game.model.Character;

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

	private Character character;

	public CharacterRenderer(
			OrthographicCamera camera, Character character) {
		this.character = character;

		stateTime = 0f;

		Texture t = new Texture(
				Gdx.files.internal(character.getAnimationSheet()));
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
		float x = character.getX() - 7;
		float y = character.getY() - 2;

		s.begin();
		s.draw(t, x, y);
		s.end();
	}

	private void renderMovement(Animation a, SpriteBatch s) {
		stateTime += Gdx.graphics.getDeltaTime();
		renderFrame(a.getKeyFrame(stateTime, true), s);
	}

	public void render(SpriteBatch s) {
		if (character.isFacingDown())
			renderFrame(downFrame, s);
		else if (character.isFacingLeft())
			renderFrame(leftFrame, s);
		else if (character.isFacingRight())
			renderFrame(rightFrame, s);
		else if (character.isFacingUp())
			renderFrame(upFrame, s);

		else if (character.isMovingLeft())
			renderMovement(leftAnimation, s);
		else if (character.isMovingRight())
			renderMovement(rightAnimation, s);
		else if (character.isMovingDown())
			renderMovement(downAnimation, s);
		else if (character.isMovingUp())
			renderMovement(upAnimation, s);
	}

}
