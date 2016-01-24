package main.entity.mob;

import java.awt.Rectangle;
import java.util.List;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Level.Node;
import main.entity.projectile.BeetleAtack;
import main.util.Vector2i;

public class Beetle extends Mob {
	
	private double xa = 0;
	private double ya = 0;
	private double speed = 1.0;
	private int porcCurrentHP = 100, fireRate = 0;
	
	private List<Node> path = null;
	
	public Beetle(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.beetle[6];
		hpCount = Sprite.beetleHpBar[0];
		defaultAwake = 600;
		awakeTime = defaultAwake;
		hitBox = new Rectangle((int)this.x, (int)this.y, 14, 13);
		healthPoints = 200;
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
		if (porcCurrentHP <= 6) 
			hpCount = Sprite.beetleHpBar[14];
		else if (porcCurrentHP <= 12) 
			hpCount = Sprite.beetleHpBar[13];
		else if (porcCurrentHP <= 18) 
			hpCount = Sprite.beetleHpBar[12];
		else if (porcCurrentHP <= 24) 
			hpCount = Sprite.beetleHpBar[11];
		else if (porcCurrentHP <= 30) 
			hpCount = Sprite.beetleHpBar[10];
		else if (porcCurrentHP <= 36) 
			hpCount = Sprite.beetleHpBar[9];
		else if (porcCurrentHP <= 42) 
			hpCount = Sprite.beetleHpBar[8];
		else if (porcCurrentHP <= 48) 
			hpCount = Sprite.beetleHpBar[7];
		else if (porcCurrentHP <= 54) 
			hpCount = Sprite.beetleHpBar[6];
		else if (porcCurrentHP <= 60) 
			hpCount = Sprite.beetleHpBar[5];
		else if (porcCurrentHP <= 66) 
			hpCount = Sprite.beetleHpBar[4];
		else if (porcCurrentHP <= 72) 
			hpCount = Sprite.beetleHpBar[3];
		else if (porcCurrentHP <= 78) 
			hpCount = Sprite.beetleHpBar[2];
		else if (porcCurrentHP <= 84) 
			hpCount = Sprite.beetleHpBar[1];
		else if (porcCurrentHP == 100) 
			hpCount = Sprite.beetleHpBar[0];
	}

	@Override
	public void update() {
		xa = 0;
		ya = 0;
		double distance = level.getPlayerDistance(this);
		if (distance < 75 || woken) {
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
		
		if (distance < 55 && fireRate <= 0) {
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
		
		if (fireRate > 0) {
			fireRate--;
		}
		
	}
		
		
		if (anim < 9999) anim++;
		else anim = 0;
		
//		time++;
//		
//		if (time % (random.nextInt(50) + 30) == 0) {
//			xa = (random.nextInt(3) - 1) * speed;
//			ya = (random.nextInt(3) - 1) * speed;
//			if (random.nextInt(3) == 0) {
//				//xa = 0;
//				//ya = 0;
//			}
//		}
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}
		
		if (currentHP <= 0) {
			remove();
		}
		
		updateHitBox(0, 5);
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

	@Override
	public void render(Screen screen) {
		//Down
		if (dir == Direction.DOWN) {
			sprite = Sprite.beetle[6];
			if (walking) {
				if (anim% 20 > 10) {
					sprite = Sprite.beetle[6];
				}
				else {
					sprite = Sprite.beetle[7];
				}
			}
		}
		//Left
		if (dir == Direction.LEFT) {
			sprite = Sprite.beetle[4];
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.beetle[4];
				}
				else {
					sprite = Sprite.beetle[5];
				}
			}
		}
		//Up
		if (dir == Direction.UP) {
			sprite = Sprite.beetle[0];
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.beetle[0];
				}
				else {
					sprite = Sprite.beetle[1];
				}
			}
		}
		//Right
		if (dir == Direction.RIGHT) {
			sprite = Sprite.beetle[2];
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.beetle[2];
				}
				else {
					sprite = Sprite.beetle[3];
				}
			}
		}
		screen.renderMob((int)(x - 10), (int)(y - 10), this);
		screen.renderSprite((int)this.x - 10, (int)this.y - 10, hpCount, true);
//		screen.renderSprite(hitBox.x, hitBox.y, new Sprite(hitBox.width, hitBox.height, 0xFF0000), true);
	}

}
