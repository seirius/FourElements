package main.Level.tile;

import main.Graphics.Screen;
import main.Graphics.Sprite;

public class GrassTileB extends Tile {

	public GrassTileB(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}