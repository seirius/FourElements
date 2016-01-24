package main.entity.projectile;

import java.awt.Rectangle;
import java.util.List;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.Entity;
import main.entity.mob.Mob;
import main.entity.mob.Player;
import main.entity.spawner.ParticleSpawner;

public class MeleeAtack extends Projectile {
	
	private int time = 0, width, height;
	public static final int FIRERATE = 45;
	private Sprite sprite, sprite4;
	private int xAdjust = 0;
	private boolean verticalShot = false; //para saber en que eje se esta disparando
	private int currentHP;

	public MeleeAtack(double x, double y, int width, int height, int range, double dir) {
		super(x, y, dir);
		speed = 1;
		damage = 25;
		currentHP = 65;
		sprite = Sprite.airMeleeAtack[0];
		this.width = width;
		this.height = height;
		hitBox = new Rectangle((int)this.x, (int)this.y, this.width, this.height);
		loadSprites();
	}
	
	private void loadSprites() {
		//Right
		if (angle >= -0.80 && angle < 0.80) {
			sprite1 = Sprite.airMeleeAtack[0];
			sprite2 = Sprite.airMeleeAtack[1];
			sprite3 = Sprite.airMeleeAtack[2];
			sprite4 = Sprite.airMeleeAtack[3];
			
		}
		//Down
		else if (angle >= 0.80 && angle <= 2.30) {
			sprite1 = Sprite.airMeleeAtackUpDown[0];
			sprite2 = Sprite.airMeleeAtackUpDown[1];
			sprite3 = Sprite.airMeleeAtackUpDown[2];
			sprite4 = Sprite.airMeleeAtackUpDown[3];
			xAdjust = -2;
			verticalShot = true;
		}
		//Up
		else if (angle >= -2.30 && angle < 0.80) {
			sprite1 = Sprite.airMeleeAtackUpDown[0];
			sprite2 = Sprite.airMeleeAtackUpDown[1];
			sprite3 = Sprite.airMeleeAtackUpDown[2];
			sprite4 = Sprite.airMeleeAtackUpDown[3];
			verticalShot = true;
		}
		//Left
		else {
			sprite1 = Sprite.airMeleeAtack[0];
			sprite2 = Sprite.airMeleeAtack[1];
			sprite3 = Sprite.airMeleeAtack[2];
			sprite4 = Sprite.airMeleeAtack[3];
			xAdjust = - 6;
		}
	}
	
	public void update() {
		if (level.getEntitiesSize() > 1) {
			List<Entity> entities = level.getEntitiesList();
			if (time % 15 == 0) {
				for (int i = 0; i < entities.size(); i++) {
					Entity e = entities.get(i);
					if ((e instanceof Mob) && !(e instanceof Player) && ((Mob)e).getHitBox().intersects(hitBox)) {
						((Mob)e).damageTheMob(damage);
						((Mob)e).wakeUp();
						if (verticalShot) {
							level.add(new ParticleSpawner((int) x, (int) y, 10, 5, level, Sprite.particle_default));
							level.add(new ParticleSpawner((int) x + (width>>1), (int) y, 10, 5, level, Sprite.particle_default));
							level.add(new ParticleSpawner((int) x + width, (int) y, 10, 5, level, Sprite.particle_default));
						} else {
							level.add(new ParticleSpawner((int) x, (int) y, 10, 5, level, Sprite.particle_default));
							level.add(new ParticleSpawner((int) x, (int) y  + (height>>1), 10, 5, level, Sprite.particle_default));
							level.add(new ParticleSpawner((int) x, (int) y + height, 10, 5, level, Sprite.particle_default));
						}
					}
				}
			}
		}
		
		if (level.getProjectiles().size() > 0) {
			if (time % 3 == 0) {
				for (Projectile p: level.getProjectiles()) {
					if ( (p instanceof FireAtack || p instanceof BeetleAtack) && p.getHitBox().intersects(hitBox)) {
						currentHP -= p.getDamage();
						p.remove();
						if (currentHP <= 0) {
							if (verticalShot) {
								level.add(new ParticleSpawner((int) x, (int) y, 10, 5, level, Sprite.particle_default));
								level.add(new ParticleSpawner((int) x + (width>>1), (int) y, 10, 5, level, Sprite.particle_default));
								level.add(new ParticleSpawner((int) x + width, (int) y, 10, 5, level, Sprite.particle_default));
							} else {
								level.add(new ParticleSpawner((int) x, (int) y, 10, 5, level, Sprite.particle_default));
								level.add(new ParticleSpawner((int) x, (int) y  + (height>>1), 10, 5, level, Sprite.particle_default));
								level.add(new ParticleSpawner((int) x, (int) y + height, 10, 5, level, Sprite.particle_default));
							}
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
		
		if (anim < 9999) anim++;
		else anim = 0;
		updateSprite();
	}
	
	public void updateSprite() {
		if (anim < 8) {
			sprite = sprite1;
		} else if (anim < 16){
			sprite = sprite2;
		} else if (anim < 24) {
			sprite = sprite3;
		} else {
			sprite = sprite4;
		}
	}
	
	public void render(Screen screen) {
//		screen.renderSprite((int)x, (int)y, new Sprite(width, height, 0xFF0000), true);
		screen.renderSprite((int)x + xAdjust, (int)y, sprite, true);
	}
	
}
