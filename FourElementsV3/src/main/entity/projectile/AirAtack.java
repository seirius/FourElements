package main.entity.projectile;

import java.awt.Rectangle;
import java.util.List;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.Entity;
import main.entity.mob.Mob;
import main.entity.mob.Player;
import main.entity.spawner.ParticleSpawner;

public class AirAtack extends Projectile {
	
	public static final int FIRERATE = 30; //Cuanto mas alto sera mas lento

	public AirAtack(double x, double y, int range, double dir) {
		super(x, y, dir);
		this.range = random.nextInt(10) + range;
		speed = 3;
		damage = 30;
		sprite1 = Sprite.projectiles_air_atack1;
		sprite2 = Sprite.projectiles_air_atack2;
		sprite3 = Sprite.projectiles_air_atack3;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		hitBox = new Rectangle((int)this.x, (int)this.y, 8, 8);
	}
	
	public void update() {
		if (level.getEntitiesSize() > 1) {
			boolean oneHit = false;
			List<Entity> entities = level.getEntitiesList();
			for (int i = 0; i < entities.size() && !oneHit; i++) {
				Entity e = entities.get(i);
				if ((e instanceof Mob) && !(e instanceof Player) && ((Mob)e).getHitBox().intersects(hitBox)) {
					oneHit = true;
					((Mob)e).damageTheMob(damage);
					((Mob)e).wakeUp();
					level.add(new ParticleSpawner((int) x, (int) y, 50, 20, level, Sprite.particle_default));
					remove();
				}
			}
		}
		if (level.tileCollision((int)(x + nx),(int)(y + ny), 4, 4, 8)) {
			level.add(new ParticleSpawner((int) x, (int) y, 50, 20, level, Sprite.particle_default));
			remove(); 
		}
		
		move();
		
		if (anim < 9999) anim++;
		else anim = 0;
		
		updateHitBox(0, 0);
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
		screen.renderProjectile((int)x - 10, (int)y - 5, this);
	}

}
