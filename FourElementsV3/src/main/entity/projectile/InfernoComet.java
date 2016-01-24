package main.entity.projectile;

import main.Graphics.Screen;
import main.Graphics.Sprite;

public class InfernoComet extends Projectile {
	
	private int targetY;
//	private int targetX;

	public InfernoComet(double x, double y, int targetX, int targetY, double dir) {
		super(x, y, dir);
		speed = 1.6;
		sprite1 = Sprite.comet[0];
		sprite2 = Sprite.comet[1];
		sprite3 = Sprite.comet[2];
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
//		this.targetX = targetX;
		this.targetY = targetY;
		
	}
	
	public void update() {
		move();
		
		if (anim < 999999999) anim++;
		else anim = 0;
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if (y + 32 > targetY) {
			remove();
		}
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int)this.x - 16, (int)this.y - 16, getSprite(), true);
	}

}
