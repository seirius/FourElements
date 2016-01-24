package main.Level.tile;

import main.Graphics.Screen;
import main.Graphics.Sprite;

public class RocksB extends Tile {

	public RocksB(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	

}
