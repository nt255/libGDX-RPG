package com.gdxrpg.game.model;

import com.badlogic.gdx.utils.Array;

public class Area {

	private Map map;
	private MainCharacter mainCharacter;
	private Array<Character> characters; // NPCs

	public Area(String mapName, String sheet, float x, float y) {
		map = new Map(mapName);
		mainCharacter = new MainCharacter(sheet, x, y);
		characters = new Array<Character>();
	}

	public void addCharacter(String sheet, float x, float y) {
		Character c = new Character(sheet, x, y);
		characters.add(c);
	}

	public Map getMap() {
		return map;
	}

	public MainCharacter getMainCharacter() {
		return mainCharacter;
	}

	public Array<Character> getCharacters() {
		return characters;
	}

}
