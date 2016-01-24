package main.entity.projectile;

import java.awt.Rectangle;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.mob.Player;
import main.entity.spawner.ParticleSpawner;

public class Inferno extends Projectile {
	
	private int spawnX, spawnY;
	private int time = 0;
	private boolean cometAlive = true, shot = false;

	public Inferno(double x, double y, double dir) {
		super(x, y, dir);
		
		damage = 80;
		
		spawnX = (int)this.x - 80;
		spawnY = (int)this.y - 120;
		
		sprite1 = Sprite.infernoPointer[0];
		sprite2 = Sprite.infernoPointer[1];
		sprite3 = Sprite.infernoPointer[2];
		
		hitBox = new Rectangle((int)this.x - 24, (int)this.y - 24, 48, 48);
	}
	
	public void update() {
		if (!shot) {
			level.add(new InfernoComet(spawnX, spawnY, (int)this.x + 24, (int)this.y + 24, dirPlayer()));
			shot = true;
		}
		if (!cometAlive) {
			if (level.getPlayer() != null) {
				Player player = level.getPlayer();
				if (player.getHitBox().intersects(hitBox)) {
					player.damageThePlayer(damage);
					level.add(new ParticleSpawner((int)player.getX(), (int)player.getY(), 45, 40, level, Sprite.particle_blood));
				}
			}
			
			level.add(new ParticleSpawner((int)x - 24, (int)y - 24, 15, 10, level, Sprite.particle_fire));
			level.add(new ParticleSpawner((int)x, (int)y - 24, 15, 10, level, Sprite.particle_fire));
			level.add(new ParticleSpawner((int)x + 24, (int)y - 24, 10, 52, level, Sprite.particle_fire));
			level.add(new ParticleSpawner((int)x - 24, (int)y, 15, 10, level, Sprite.particle_fire));
			level.add(new ParticleSpawner((int)x, (int)y, 15, 10, level, Sprite.particle_fire));
			level.add(new ParticleSpawner((int)x + 24, (int)y, 15, 10, level, Sprite.particle_fire));
			level.add(new ParticleSpawner((int)x - 24, (int)y + 24, 10, 25, level, Sprite.particle_fire));
			level.add(new ParticleSpawner((int)x, (int)y + 24, 15, 10, level, Sprite.particle_fire));
			level.add(new ParticleSpawner((int)x + 24, (int)y + 24, 15, 10, level, Sprite.particle_fire));
			remove();
		}
		updateTimers();
		updateHitBox(- 24, - 24);
	}
	
	public void render(Screen screen) {
		if (cometAlive) {
			screen.renderSprite((int)this.x - 24, (int)this.y - 24, getSprite(), true);
		}
		
//		screen.renderSprite(hitBox.x, hitBox.y, new Sprite(hitBox.width, hitBox.height, 0xFF0000), true);
	}
	
	private double dirPlayer() {
		double dx = x - spawnX;
		double dy = y - spawnY;
		double dir = Math.atan2(dy, dx);
		return dir;
	}
	
	private void updateTimers() {
		if (anim < 999999999) anim++;
		else anim = 0;
		
		if (time < 99999999) time++;
		else time = 9999;
		
		if (time > 90 && cometAlive) cometAlive = false; 
	}
}
