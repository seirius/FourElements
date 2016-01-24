package main.entity.projectile;

import java.awt.Rectangle;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.spawner.ParticleSpawner;

public class FireAtack extends Projectile {
	
	public static final int FIRERATE = 10; //Cuanto mas alto sera mas lento

	public FireAtack(double x, double y, int range, double dir) {
		super(x, y, dir);
		this.range = random.nextInt(10) + range;
		speed = 3;
		damage = 30; //30
		sprite1 = Sprite.projectiles_fire_atack;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		hitBox = new Rectangle((int)this.x, (int)this.y, 8, 8);
	}
	
	public FireAtack(double x, double y, int range, double dir, int speed) {
		super(x, y, dir);
		this.range = random.nextInt(10) + range;
		this.speed = speed;
		damage = 15; //15
		sprite1 = Sprite.projectiles_fire_atack;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		hitBox = new Rectangle((int)this.x + 14, (int)this.y + 9, 8, 8);
	}
	
	public void update() {
		if (level.tileCollision((int)(x + nx),(int)(y + ny), 4, 4, 8)) {
			remove();
		}
		if (level.getPlayer() != null) {
			if (level.getPlayer().getHitBox().intersects(hitBox)) {
				level.getPlayer().damageThePlayer(damage);
				level.add(new ParticleSpawner((int) x, (int) y, 45, 15, level, Sprite.particle_blood));
				remove();
			}
		}
		
		move();
		
		updateHitBox(0, 0);
	}
	
	public void remove() {
		//Remove from level
		removed = true;
		level.add(new ParticleSpawner((int) x, (int) y, 15, 5, level, Sprite.particle_fire));
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

	public void render(Screen screen) {
		screen.renderProjectile((int)x - 4, (int)y - 4, this);
//		screen.renderSprite(hitBox.x, hitBox.y, new Sprite(hitBox.width, hitBox.height, 0xFF0000), true);
	}
}
