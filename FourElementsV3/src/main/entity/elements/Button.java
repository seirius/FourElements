package main.entity.elements;

import main.Graphics.Screen;
import main.Graphics.Sprite;

public class Button extends Element {
	
	private Sprite sprite1, sprite2;
	private boolean selected = false;

	public Button(int x, int y, Sprite sprite1, Sprite sprite2) {
		this.x = x;
		this.y = y;
		this.sprite1 = sprite1;
		this.sprite2 = sprite2;
	}
	
	public boolean getSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void render(Screen screen) {
		if (selected) 
			screen.renderSprite(x, y, sprite2, false);
		else 
			screen.renderSprite(x, y, sprite1, false);
	}
	
}
