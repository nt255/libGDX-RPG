package com.gdxrpg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
// import com.badlogic.gdx.audio.Music;
import com.gdxrpg.game.model.Area;
import com.gdxrpg.game.model.GameModel;
import com.gdxrpg.game.view.GameRenderer;
import com.gdxrpg.game.controller.MovementInputProcessor;

public class Game extends ApplicationAdapter {

	private GameModel gameModel;
	private GameRenderer gameRenderer;

	@Override
	public void create () {
		String mapName = "cafe";
		String sheet = "pirate_m1.png";
		float startingX = 684;
		float startingY = 100;

		Area area = new Area(mapName, sheet, startingX, startingY);
		area.addCharacter("steampunk_f8.png", 736, 232);
		area.addCharacter("judgeturpin.png", 574, 255);

		gameModel = new GameModel(area);
		gameRenderer = new GameRenderer(gameModel);

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new MovementInputProcessor(gameModel));
		// multiplexer.addProcessor(new CameraInputProcessor(gameRenderer));

		Gdx.input.setInputProcessor(multiplexer);
		Gdx.graphics.setTitle("libgdx-rpg");

		/*
		Music m = Gdx.audio.newMusic(
				Gdx.files.internal("cafe.mp3"));
		m.play();
		 */
	}

	@Override
	public void render () {
		gameModel.update();
		gameRenderer.render();
	}

}
