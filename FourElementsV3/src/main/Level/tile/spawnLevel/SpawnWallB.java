package main.Level.tile.spawnLevel;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Level.tile.Tile;

public class SpawnWallB extends Tile {

	public SpawnWallB(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}

}
