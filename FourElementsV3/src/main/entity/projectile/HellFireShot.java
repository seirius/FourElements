package main.entity.projectile;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.spawner.ParticleSpawner;

public class HellFireShot extends Projectile{

	public HellFireShot(double x, double y, int range, double dir) {
		super(x, y, dir);
		this.range = range;
		speed = 1;
		damage = 0;
		sprite1 = Sprite.projectiles_hell_fire_shot1;
		sprite2 = Sprite.projectiles_hell_fire_shot2;
		sprite3 = Sprite.projectiles_hell_fire_shot3;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update() {
		if (level.tileCollision((int)(x + nx),(int)(y + ny), 4, 4, 8)) {
			level.add(new ParticleSpawner((int) x, (int) y, 15, 5, level, Sprite.particle_fire));
			remove();
		}
		move();
		
		if (distance() > range) {
			level.add(new HellFire(x, y, 0, 0.0));
			remove();
		}
		
		if (anim < 9999) anim++;
		else anim = 0;
	}
	
	protected void move() {
		x += nx;
		y += ny;
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int)x - 10, (int)y - 5, this);
	}

}
