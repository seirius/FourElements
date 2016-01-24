package main.entity.projectile;

import java.awt.Rectangle;
import java.util.Random;

import main.Graphics.Sprite;
import main.entity.Entity;

public abstract class Projectile extends Entity {

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite1, sprite2, sprite3; 
	protected double x, y;
	protected double nx, ny;
	protected double distance;
	protected double speed, range;
	protected int damage;
	protected int anim = 0;
	protected Rectangle hitBox;
	
	protected final Random random = new Random();
	
	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	public Sprite getSprite() {
		Sprite sprite;
		if (anim % 20 < 5) {
			sprite = sprite1;
		}
		else if (anim % 20 < 10){
			sprite = sprite2;
		}
		else {
			sprite = sprite3;
		}
		return sprite;
	}
	
	public int getSpriteSize() {
		return sprite1.SIZE;
	}
	
	protected void move() {
		
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public int getDamage() {
		return damage;
	}
	
	protected void updateHitBox(int adjustX, int adjustY) {
		hitBox.setLocation((int)x + adjustX, (int)y + adjustY);
	}
}
