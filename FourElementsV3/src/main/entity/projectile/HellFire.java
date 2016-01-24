package main.entity.projectile;

import java.awt.Rectangle;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.spawner.ParticleSpawner;

public class HellFire extends Projectile {
	
	private int time = 0;
	public int fireRate = 30;

	public HellFire(double x, double y, int range, double dir) {
		super(x, y, dir);
		this.range = range;
		speed = 0;
		damage = 10;
		sprite1 = Sprite.projectiles_hell_fire1;
		sprite2 = Sprite.projectiles_hell_fire2;
		sprite3 = Sprite.projectiles_hell_fire3;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		hitBox = new Rectangle((int)this.x, (int)this.y, 16, 16);
	}
	
	public void update() {
		if (level.tileCollision((int)(x + nx),(int)(y + ny), 4, 4, 8)) {
			level.add(new ParticleSpawner((int) x, (int) y, 15, 5, level, Sprite.particle_fire));
			remove();
		}
		
		if (level.getPlayer() != null) {
			if (level.getPlayer().getHitBox().intersects(hitBox) && fireRate <= 0) {
				level.getPlayer().damageThePlayer(damage);
				level.add(new ParticleSpawner((int) x, (int) y, 45, 15, level, Sprite.particle_blood));
				fireRate = 30;
			}
		}
		move();
		
		if (anim < 9999) anim++;
		else anim = 0;
		
		time++;
		if (time > 600) remove();
		
		fireRate--;
		
		updateHitBox(0, 0);
	}
	
	protected void move() {
		x += nx;
		y += ny;
	}
	
//	private double distance() {
//		double dist = 0;
//		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
//		return dist;
//	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int)x - 10, (int)y - 5, this);
	}
}