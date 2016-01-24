package main.entity.items;

import java.awt.Rectangle;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.Entity;

public abstract class Item extends Entity {
	
	protected int anim = 0;
	protected int widthHB, heightHB;
	protected Sprite[] sprites;
	protected Rectangle hitBox;
	protected int timeBuffed;
	
	public Item(int x, int y, int widthHitBox, int heightHitBox, int timeBuffed) {
		this.x = x << 4;
		this.y = y << 4;
		this.widthHB = widthHitBox;
		this.heightHB = heightHitBox;
		this.timeBuffed = timeBuffed;
		sprite = Sprite.airBuff[0];
		hitBox = new Rectangle((int)this.x + 6, (int)this.y + 12, widthHB, heightHB);
	}
	
	protected abstract void updateSprite();
	
	public abstract void update();
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public int getTimeBuffed() {
		return timeBuffed;
	}
	
	public void render(Screen screen) {
//		screen.renderSprite((int)x + 6, (int)y + 12, new Sprite(widthHB, heightHB, 0xFF0000), true);
		screen.renderSprite((int)x, (int)y, sprite, true);
	}
}
