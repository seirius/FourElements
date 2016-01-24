package main.entity.items;

import main.Graphics.Font;
import main.Graphics.Screen;
import main.Graphics.Sprite;

public class AirBuff extends Item {
	
	private double extraSpeed = 0.4;
	private Font font;
	private char[] phrase;
	
	private boolean showText = false;

	public AirBuff(int x, int y, int widthHitBox, int heightHitBox, int timeBuffed) {
		super(x, y, widthHitBox, heightHitBox, timeBuffed);
		
		font = new Font(Sprite.letters);
		phrase = font.getStringsArray("SPEED BUFF");
	}
	
	protected void updateSprite() {
		anim++;
		
		if (anim < 20) sprite = Sprite.airBuff[0];
		else if (anim < 40) sprite = Sprite.airBuff[1];
		else if (anim < 60) sprite = Sprite.airBuff[2];
		else {
			sprite = Sprite.airBuff[1];
			if (anim == 79) {
				anim = 0;
			}
		}
	}
	
	public double getExtraSpeed() {
		return extraSpeed;
	}
	
	public void update() {
		updateSprite();
		
		if (level.getPlayerDistance(this) < 70) showText = true;
		else showText = false;
	}
	
	public void render(Screen screen) {
//		screen.renderSprite((int)x + 6, (int)y + 12, new Sprite(widthHB, heightHB, 0xFF0000), true);
		if (showText) font.renderString(phrase, (int)x - 13, (int)y - 10, true, screen);
		screen.renderSprite((int)x, (int)y, sprite, true);
	}

}
