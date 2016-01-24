package main.entity.projectile;

import java.awt.Rectangle;
import java.util.List;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.Entity;
import main.entity.mob.Mob;
import main.entity.mob.Player;
import main.entity.spawner.ParticleSpawner;

public class BeetleAtack extends Projectile {

	
	private int time = 0, width, height;
	
	public static final int FIRERATE = 75;
	private int shotCounter = 3;
	
	private Sprite sprite, sprite4;
	
	private int xAdjust = 0, yAdjust = 0;

	public BeetleAtack(double x, double y, int width, int height, int range, double dir) {
		super(x, y, dir);
		speed = 1.5;
		damage = 10;
		this.range = range;
		sprite = Sprite.airMeleeAtack[0];
		this.width = width;
		this.height = height;
		hitBox = new Rectangle((int)this.x, (int)this.y, this.width, this.height);
		loadSprites();
		dirAtack();
	}
	
	private void dirAtack() {
		if (angle >= -0.80 && angle < 0.80) {
			nx = speed * 1;
		} else if (angle >= 0.80 && angle <= 2.30) {
			ny = speed * 1;
		} else if (angle >= -2.30 && angle < 0.80) {
			ny = speed * -1;
		} else {
			nx = speed * -1;
		}
	}
	
	private void loadSprites() {
		//Right
		if (angle >= -0.80 && angle < 0.80) {
			sprite1 = Sprite.babas1[0];
			sprite2 = Sprite.babas1[2];
			sprite3 = Sprite.babas1[4];
			sprite4 = Sprite.babas1[6];
			yAdjust = - 15;
		}
		//Down
		else if (angle >= 0.80 && angle <= 2.30) {
			sprite1 = Sprite.babas2[4];
			sprite2 = Sprite.babas2[5];
			sprite3 = Sprite.babas2[6];
			sprite4 = Sprite.babas2[7];
			xAdjust = - 10;
			yAdjust = + 3;
		}
		//Up
		else if (angle >= -2.30 && angle < 0.80) {
			sprite1 = Sprite.babas2[0];
			sprite2 = Sprite.babas2[1];
			sprite3 = Sprite.babas2[2];
			sprite4 = Sprite.babas2[3];
			xAdjust = - 8;
			yAdjust = - 51;
		}
		//Left
		else {
			sprite1 = Sprite.babas1[1];
			sprite2 = Sprite.babas1[3];
			sprite3 = Sprite.babas1[5];
			sprite4 = Sprite.babas1[7];
			xAdjust = - 43;
			yAdjust = - 15;
		}
	}
	
	public void update() {
		if (level.getEntitiesSize() > 1) {
			List<Entity> entities = level.getEntitiesList();
			if (time % 6 == 0) {
				for (int i = 0; i < entities.size(); i++) {
					Entity e = entities.get(i);
					if ((e instanceof Player) && time % 2 == 0 && ((Mob)e).getHitBox().intersects(hitBox)) {
						level.add(new ParticleSpawner((int) x, (int) y, 20, 15, level, Sprite.particle_bluh));
						((Player)e).damageThePlayer(damage);
						shotCounter--;
						if (shotCounter <= 0) {
							remove();
						}
					}
				}
			}
		}
		
		time++;
		if (time > 32) {
			remove();
		}
		
		
		move();
		updateHitBox(0, 0);
		
		if (anim < 9999) anim++;
		else anim = 0;
		updateSprite();
	}
	
	protected void move() {
		x += nx;
		y += ny;
		
		if (distance() > range) remove();
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}
	
	public void updateSprite() {
		if (anim < 8) {
			sprite = sprite1;
		}
		else if (anim < 16){
			sprite = sprite2;
		} else if (anim < 24) {
			sprite = sprite3;
		} else {
			sprite = sprite4;
		}
	}
	
	public void render(Screen screen) {
//		screen.renderSprite((int)x, (int)y, new Sprite(width, height, 0xFF0000), true);
		screen.renderSprite((int)xOrigin + xAdjust, (int)yOrigin + yAdjust, sprite, true);
	}
}
