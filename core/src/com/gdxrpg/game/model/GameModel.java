package com.gdxrpg.game.model;

public class GameModel {

	private Map map;
	private MainCharacter mainCharacter;

	public GameModel(float x, float y) {
		map = new Map();
		mainCharacter = new MainCharacter(x, y);
	}

	public void update() {
		mainCharacter.updatePosition();
	}

	public Map getMap() {
		return map;
	}

	public MainCharacter getMainCharacter() {
		return mainCharacter;
	}

}
