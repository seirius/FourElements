package main.entity.mob;

import java.awt.Rectangle;
import java.util.List;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Level.Node;
import main.entity.projectile.BeetleAtack;
import main.entity.spawner.ParticleSpawner;
import main.util.Vector2i;

public class Emibluh extends Mob{
	
	private double xa = 0;
	private double ya = 0;
	private double speed = 2;
	private int porcCurrentHP = 100, fireRate = 0, shooting = 0;
	private boolean dead = false;
	
	private List<Node> path = null;
	
	public Emibluh(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.emibluh[0];
		hpCount = Sprite.beetleHpBar[0];
		defaultAwake = 600;
		awakeTime = defaultAwake;
		hitBox = new Rectangle((int)this.x, (int)this.y - 5, 14, 16);
		healthPoints = 125;
		currentHP = healthPoints;
	}
	
	public double dirPlayer() {
		Player player = level.getPlayer();
		double dx = player.getX() - x;
		double dy = player.getY() - y;
		double dir = Math.atan2(dy, dx);
		return dir;
	}
	
	private void updateHealthBar() {
		if (time % 10 == 0) {
			porcCurrentHP = currentHP * 100 / healthPoints;
		} 
		if (porcCurrentHP <= 15) 
			hpCount = Sprite.hpBar[6];
		else if (porcCurrentHP <= 30) 
			hpCount = Sprite.hpBar[5];
		else if (porcCurrentHP <= 45) 
			hpCount = Sprite.hpBar[4];
		else if (porcCurrentHP <= 60) 
			hpCount = Sprite.hpBar[3];
		else if (porcCurrentHP <= 75) 
			hpCount = Sprite.hpBar[2];
		else if (porcCurrentHP <= 90) 
			hpCount = Sprite.hpBar[1];
		else 
			hpCount = Sprite.hpBar[0];
	}

	@Override
	public void update() {
		if (!dead) {
			xa = 0;
			ya = 0;
			double distance = level.getPlayerDistance(this);
			mobShoot(distance);
			moveMob(distance);
		}
		
		if (shooting > 0) {
			shooting--;
		}
		
		if (fireRate > 0) {
			fireRate--;
		}
		
		if (anim < 9999) {
			anim++;
		} else {
			anim = 0;
		}
		
		if (currentHP <= 0 && !dead) {
			dead = true;
			solid = false;
			anim = 0;
		} else if (dead && anim > 75) {
			level.add(new ParticleSpawner((int)x + 6, (int)y - 13, 50, 20, level, Sprite.particle_bluh));
			level.add(new ParticleSpawner((int)x + 6, (int)y + 3, 50, 20, level, Sprite.particle_bluh));
			level.add(new ParticleSpawner((int)x + 6, (int)y + 14, 50, 20, level, Sprite.particle_bluh));
			remove();
		}
		
		updateHitBox(0, 0);
		updateWoken();
		updateHealthBar();
		updateTime();
	}
	
	private void updateTime() {
		time++;
		if (time > 999999) {
			time = 0;
		}
	}
	
	public void updateWoken() {
		if (woken) {
			awakeTime--;
			if (awakeTime <= 0) {
				awakeTime = 600;
				woken = false;
			}
		}
	}
	
	private void moveMob(double distance) {
		if ( (distance < 100 && distance > 40) || (woken && distance > 40) ) {
			woken = true;
			Player player = level.getPlayer();
			int px = ((int)player.getX());
			int py = ((int)player.getY());
			Vector2i start = new Vector2i(((int)getX()) >> 4, ((int)getY()) >> 4);
			Vector2i destination = new Vector2i(px >> 4, py >> 4);
			path = level.findPath(start, destination);
			if (path != null) {
				if (path.size() > 0) {
					Vector2i vec = path.get(path.size() - 1).tile;
					if (x < vec.getX() << 4) xa += speed;
					if (x > vec.getX() << 4) xa -= speed;
					if (y < vec.getY() << 4) ya += speed;
					if (y > vec.getY() << 4) ya -= speed;
				}
			}
		}
		if ((xa != 0 || ya != 0) && shooting <= 0) {
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}
	}
	
	private void mobShoot(double distance) {
		if (distance < 55 && fireRate <= 0) {
			shooting = 45;
			double dir = dirPlayer();
			if (dir >= 0.80 && dir <= 2.30) {
				meleeAtack(x, y + 15, 13, 13, 2, dir);
			} else if (dir >= -0.80 && dir < 0.80) {
				meleeAtack(x + 15, y, 13, 13, 2, dir);
			} else if (dir >= -2.30 && dir < 0.80) {
				meleeAtack(x - 2, y - 18, 13, 13, 2, dir);
			} else {
				meleeAtack(x - 20, y - 1, 13, 13, 2, dir);
			}
			fireRate = BeetleAtack.FIRERATE;
			
			}
	}

	@Override
	public void render(Screen screen) {
		if (dead) {
			if (anim < 30) {
				sprite = Sprite.emibluh[0];
			} else if (anim < 45) {
				sprite = Sprite.emibluh[16];
			} else if (anim < 60) {
				sprite = Sprite.emibluh[17];
			} else {
				sprite = Sprite.emibluh[18];
			}
		} else {
			
		//Down
		if (dir == Direction.DOWN) {
			sprite = Sprite.emibluh[0];
			if (walking) {
				if (anim% 20 > 10) {
					sprite = Sprite.emibluh[1];
				} else {
					sprite = Sprite.emibluh[2];
				}
			} else if (shooting > 0) {
				sprite = Sprite.emibluh[3];
			}
		}
		//Left
		if (dir == Direction.LEFT) {
			sprite = Sprite.emibluh[4];
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.emibluh[5];
				}
				else{
					sprite = Sprite.emibluh[6];
				}
			} else if (shooting > 0) {
				sprite = Sprite.emibluh[7];
			}
		}
		//Up
		if (dir == Direction.UP) {
			sprite = Sprite.emibluh[12];
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.emibluh[13];
				}
				else {
					sprite = Sprite.emibluh[14];
				}
			} else if (shooting > 0) {
				sprite = Sprite.emibluh[15];
			}
		}
		//Right
		if (dir == Direction.RIGHT) {
			sprite = Sprite.emibluh[8];
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.emibluh[9];
				}
				else {
					sprite = Sprite.emibluh[10];
				}
			} else if (shooting > 0) {
				sprite = Sprite.emibluh[11];
			}
		}
		}
		screen.renderMob((int)(x - 10), (int)(y - 15), this);
		if (currentHP > 0) screen.renderSprite((int)this.x - 10, (int)this.y - 17, hpCount, true);
//		screen.renderSprite((int)x + 6, (int)y + 14, new Sprite(30, 30, 0xFF0000), true);
//		screen.renderSprite(hitBox.x, hitBox.y, new Sprite(hitBox.width, hitBox.height, 0xFF0000), true);
	}

}
