package com.gdxrpg.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.gdxrpg.game.model.MainCharacter;

public class CameraRefresher {

	private static final int REQUIRED_FAST_DST = 20;
	private static final float FAST_RATE = 1.0f;
	private static final float SLOW_RATE = 0.5f;

	private OrthographicCamera camera;

	public CameraRefresher(OrthographicCamera camera) {
		this.camera = camera;
	}

	private void translate(float rate, Vector3 dir, float dst) {
		dir.scl(rate / dst);
		camera.position.add(dir);
		camera.update();
	}

	protected void refresh(MainCharacter mainCharacter) {
		Vector3 pos = new Vector3();
		pos.x = mainCharacter.getX();
		pos.y = mainCharacter.getY();

		pos.sub(camera.position);
		float dst = pos.len();

		if (dst > REQUIRED_FAST_DST)
			translate(FAST_RATE, pos, dst);
		else if (dst > 1) // avoids translating when dst very small
			translate(SLOW_RATE, pos, dst);
	}

}
