package main.entity.elements;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.Entity;

public class Element extends Entity {
	
	protected int x, y;

	public Element(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public Element() {
		
	}
	
	public int getIX() {
		return x;
	}
	
	public int getIY() {
		return y;
	}
	
	public void render(Screen screen) {
		screen.renderSprite(x, y, sprite, false);
	}
}
