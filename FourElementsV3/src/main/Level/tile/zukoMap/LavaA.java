package main.Level.tile.zukoMap;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Level.tile.Tile;

public class LavaA extends Tile {

	public LavaA(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}

}