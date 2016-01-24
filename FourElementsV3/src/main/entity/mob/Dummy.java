package main.entity.mob;

import java.awt.Rectangle;

import main.Graphics.Screen;
import main.Graphics.Sprite;

public class Dummy extends Mob{

	private double xa = 0;
	private double ya = 0;
	private double speed = 1.0;
	
	public Dummy(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.zuko2nd[0];
//		hpCount = Sprite.beetleHpBar[0];
		defaultAwake = 600;
		awakeTime = defaultAwake;
		hitBox = new Rectangle((int)this.x, (int)this.y - 5, 14, 16);
		healthPoints = 350;
		currentHP = healthPoints;
	}
	
	private void move() {
		
		/*//Follow IA
		xa = 0;
		ya = 0;
		List<Player> players = level.getPlayers(this, 50);
		if (players.size() > 0) {
			Player player = players.get(0);
			if ((int)x < (int)player.getX()) xa += speed;
			if ((int)x > (int)player.getX()) xa -= speed;
			if ((int)y > (int)player.getY()) ya -= speed;
			if ((int)y < (int)player.getY()) ya += speed;
		}*/
		
		time++;
		
		if (time % (random.nextInt(50) + 30) == 0) {
			xa = (random.nextInt(3) - 1) * speed;
			ya = (random.nextInt(3) - 1) * speed;
			if (random.nextInt(3) == 0) {
				xa = 0;
				ya = 0;
			}
		}
	}

	@Override
	public void update() {
		move();
		
		if (anim < 99999) {
			anim++;
		} else {
			anim = 0;
		}
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}
		
		updateHitBox(0, 0);
	}

	@Override
	public void render(Screen screen) {
//		//Down
//		if (dir == Direction.DOWN) {
//			sprite = Sprite.emibluh[0];
//			if (walking) {
//				if (anim% 20 > 10) {
//					sprite = Sprite.emibluh[1];
//				} else {
//					sprite = Sprite.emibluh[2];
//				}
//			}
//		}
//		//Left
//		if (dir == Direction.LEFT) {
//			sprite = Sprite.emibluh[4];
//			if (walking) {
//				if (anim % 20 > 10) {
//					sprite = Sprite.emibluh[5];
//				}
//				else{
//					sprite = Sprite.emibluh[6];
//				}
//			}
//		}
//		//Up
//		if (dir == Direction.UP) {
//			sprite = Sprite.emibluh[12];
//			if (walking) {
//				if (anim % 20 > 10) {
//					sprite = Sprite.emibluh[13];
//				}
//				else {
//					sprite = Sprite.emibluh[14];
//				}
//			}
//		}
//		//Right
//		if (dir == Direction.RIGHT) {
//			sprite = Sprite.emibluh[8];
//			if (walking) {
//				if (anim % 20 > 10) {
//					sprite = Sprite.emibluh[9];
//				}
//				else {
//					sprite = Sprite.emibluh[10];
//				}
//			}
//		}
		if (walking) {
			if (dir == Direction.UP) {
				if (anim % 40 > 30) {
					sprite = Sprite.zuko2nd[16];
				} else if (anim % 40 > 20) {
					sprite = Sprite.zuko2nd[17];
				} else if (anim % 40 > 10) {
					sprite = Sprite.zuko2nd[18];
				} else {
					sprite = Sprite.zuko2nd[19];
				}
			}
			if (dir == Direction.RIGHT) {
				if (anim % 40 > 30) {
					sprite = Sprite.zuko2nd[12];
				} else if (anim % 40 > 20) {
					sprite = Sprite.zuko2nd[13];
				} else if (anim % 40 > 10) {
					sprite = Sprite.zuko2nd[14];
				} else {
					sprite = Sprite.zuko2nd[15];
				}
			}
			if (dir == Direction.DOWN) {
				if (anim % 40 > 30) {
					sprite = Sprite.zuko2nd[4];
				} else if (anim % 40 > 20) {
					sprite = Sprite.zuko2nd[5];
				} else if (anim % 40 > 10) {
					sprite = Sprite.zuko2nd[6];
				} else {
					sprite = Sprite.zuko2nd[7];
				}
			}
			if (dir == Direction.LEFT) {
				if (anim % 40 > 30) {
					sprite = Sprite.zuko2nd[8];
				} else if (anim % 40 > 20) {
					sprite = Sprite.zuko2nd[9];
				} else if (anim % 40 > 10) {
					sprite = Sprite.zuko2nd[10];
				} else {
					sprite = Sprite.zuko2nd[11];
				}
			}
		} else {
			if (anim % 40 > 30) {
				sprite = Sprite.zuko2nd[0];
			} else if (anim % 40 > 20) {
				sprite = Sprite.zuko2nd[1];
			} else if (anim % 40 > 10) {
				sprite = Sprite.zuko2nd[2];
			} else {
				sprite = Sprite.zuko2nd[3];
			}
		}
		
		screen.renderMob((int)(x - 10), (int)(y - 15), this);
//		screen.renderSprite((int)x + 6, (int)y + 14, new Sprite(30, 30, 0xFF0000), true);
//		screen.renderSprite(hitBox.x, hitBox.y, new Sprite(hitBox.width, hitBox.height, 0xFF0000), true);
	}
}
	
