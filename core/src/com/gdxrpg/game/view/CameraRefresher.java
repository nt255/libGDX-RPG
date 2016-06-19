package com.gdxrpg.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.gdxrpg.game.model.MainCharacter;

public class CameraRefresher {

	private OrthographicCamera camera;

	public CameraRefresher(OrthographicCamera camera) {
		this.camera = camera;
	}

	protected void refresh(MainCharacter mainCharacter) {
		Vector3 pos = camera.position;
		// System.out.println(pos);

		Rectangle bounds = new Rectangle(pos.x - 75, pos.y - 75, 150, 150);

		while (!mainCharacter.getCollisionRectangle().overlaps(bounds)) {
			if (mainCharacter.getX() > pos.x + 70)
				camera.translate(1, 0);
			else if (mainCharacter.getX() < pos.x - 70)
				camera.translate(-1, 0);

			if (mainCharacter.getY() > pos.y + 70)
				camera.translate(0, 1);
			else if (mainCharacter.getY() < pos.y - 70)
				camera.translate(0, -1);

			camera.update();
			pos = camera.position;
			bounds = new Rectangle(pos.x - 75, pos.y - 75, 150, 150);
		}
	}

}
