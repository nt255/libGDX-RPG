package com.gdxrpg.game.model;

public class GameModel {

	private Map map;
	private MainCharacter mainCharacter;

	public GameModel(float x, float y) {
		map = new Map();
		mainCharacter = new MainCharacter(x, y);
	}

	public void update() {
		if (mainCharacter.isMovingDown())
			mainCharacter.changePosition(0, -1);
		else if (mainCharacter.isMovingLeft())
			mainCharacter.changePosition(-1, 0);
		else if (mainCharacter.isMovingRight())
			mainCharacter.changePosition(1, 0);
		else if (mainCharacter.isMovingUp())
			mainCharacter.changePosition(0, 1);
	}

	public Map getMap() {
		return map;
	}

	public MainCharacter getMainCharacter() {
		return mainCharacter;
	}

}
