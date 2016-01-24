package main.entity.mob;

import java.awt.Rectangle;

import main.Game;
import main.Graphics.Font;
import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Input.Keyboard;
import main.Input.Mouse;
import main.entity.projectile.AirAtack;
import main.entity.projectile.MeleeAtack;

public class Player extends Mob {
	
	private Keyboard input;
	private int anim = 0;
	private Sprite hpCount;
	private Font font;
//	private char[] phrase;
	
	private double xa = 0, ya = 0;
	
	private int minusWidth = 0, porcCurrentHP = 100;
	
	private int fireRate = 0, meleeRate = 0;

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		healthPoints = 300;
		speed = 1.2;
		currentHP = healthPoints;
		this.input = input;
		sprite = Sprite.player_back;
		hpCount = Sprite.avatarHP[0];
		fireRate = AirAtack.FIRERATE; 
		meleeRate = MeleeAtack.FIRERATE;
		hitBox = new Rectangle(((int)this.x) - 5, (int)this.y, 10, 13);
		font = new Font(Sprite.numbers);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean autoMove(int destinationX, int destinationY) {

		destinationX = destinationX << 4;
		destinationY = destinationY << 4;

		xa = 0;
		ya = 0;
		if ((int) x < (int) destinationX)
			xa += 1;
		if ((int) x > (int) destinationX)
			xa -= 1;
		if ((int) y > destinationY)
			ya -= 1;
		if ((int) y < destinationY)
			ya += 1;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

		if ((int) y == destinationY && (int) x == destinationX) {
			return true;
		} else {
			return false;
		}

	}
	
	public void update() {
		if (fireRate > 0) fireRate--;
		if (meleeRate > 0) meleeRate--;
		
		double xa = 0, ya = 0;
		
		//System.out.println("X: "+x);
		//System.out.println("Y: "+y);
		
		//Para que el int no llegue al limite de entero y el juego crashee
		if (anim < 9999) anim++;
		else anim = 0;
		
		if (!stop) {
			if (input.up) ya -= speed;
			if (input.down) ya += speed;
			if (input.right) xa += speed;
			if (input.left) xa -= speed;
			updateShooting();
		}
		
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}
//		clear();
		updateHitBox(- 5, 0);
		itemCollision();
		updateBuff();
		updateHealthBar();
	}
	
//	private void clear() {
//		for (int i = 0; i < level.getProjectiles().size(); i++) {
//			Projectile p= level.getProjectiles().get(i);
//			if (p.isRemoved()) level.getProjectiles().remove(i);
//		}
//	}

	private void updateShooting() {
		if (Mouse.getB() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - (Game.getWindowWidth() >> 1);
			double dy = Mouse.getY() - (Game.getWindowHeight() >> 1);
			double dir = Math.atan2(dy, dx);
			shoot(x, y, 190, dir, 0);
			fireRate = AirAtack.FIRERATE;
		}
		if(Mouse.getB() == 3 && meleeRate <= 0) {
			double dx = Mouse.getX() - (Game.getWindowWidth() >> 1);
			double dy = Mouse.getY() - (Game.getWindowHeight() >> 1);
			double dir = Math.atan2(dy, dx);
			if (dir >= 0.80 && dir <= 2.30) {
				meleeAtack(x - 14, y + 15, 30, 10, 1, dir);
			} else if (dir >= -0.80 && dir < 0.80) {
				meleeAtack(x + 10, y - 15, 10, 30, 1, dir);
			} else if (dir >= -2.30 && dir < 0.80) {
				meleeAtack(x - 15, y - 20, 30, 10, 1, dir);
			} else {
				meleeAtack(x - 20, y - 15, 10, 30, 1, dir);
			}
			meleeRate = MeleeAtack.FIRERATE;
		}
	}
	
	public void damageThePlayer(int dmg) {
		if (currentHP > 0) {
			currentHP -= dmg;
			if (currentHP < 0) currentHP = 0;
		}
	}
	
//	private void updateHealthBar() {
//		if		(currentHP == healthPoints) 						hpCount = Sprite.avatarHP5;
//		else if (healthPoints - healthPoints / 5 < currentHP)		hpCount = Sprite.avatarHP4;
//		else if (healthPoints - 2 * (healthPoints / 5) < currentHP) hpCount = Sprite.avatarHP3;
//		else if (healthPoints - 3 * (healthPoints / 5) < currentHP) hpCount = Sprite.avatarHP2;
//		else if (healthPoints / 5 < currentHP) 						hpCount = Sprite.avatarHP1;
//		else 														hpCount = Sprite.avatarHP0;
//	}
	
	private void updateHealthBar() {
		if (time % 10 == 0) {
			porcCurrentHP = currentHP * 100 / healthPoints;
		} 
		
		if (porcCurrentHP >= 100) 
			minusWidth = 0;
		else if (porcCurrentHP > 95) 
			minusWidth = 4;
		else if (porcCurrentHP > 90) 
			minusWidth = 8;
		else if (porcCurrentHP > 85) 
			minusWidth = 12;
		else if (porcCurrentHP > 80) 
			minusWidth = 16;
		else if (porcCurrentHP > 75) 
			minusWidth = 20;
		else if (porcCurrentHP > 70) 
			minusWidth = 24;
		else if (porcCurrentHP > 65) 
			minusWidth = 28;
		else if (porcCurrentHP > 60) 
			minusWidth = 32;
		else if (porcCurrentHP > 55) 
			minusWidth = 36;
		else if (porcCurrentHP > 50) 
			minusWidth = 40;
		else if (porcCurrentHP > 45) 
			minusWidth = 44;
		else if (porcCurrentHP > 40) 
			minusWidth = 48;
		else if (porcCurrentHP > 35) 
			minusWidth = 52;
		else if (porcCurrentHP > 30) 
			minusWidth = 56;
		else if (porcCurrentHP > 25) 
			minusWidth = 60;
		else if (porcCurrentHP > 20) 
			minusWidth = 64;
		else if (porcCurrentHP > 15) 
			minusWidth = 68;
		else if (porcCurrentHP > 10) 
			minusWidth = 72;
		else if (porcCurrentHP > 5) 
			minusWidth = 76;
	}
	
	public int getCurrentHP() {
		return currentHP;
	}
	
	public void render(Screen screen) {
		//Down
		if (dir == Direction.DOWN) {
			sprite = Sprite.player_back;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_back1;
				}
				else {
					sprite = Sprite.player_back2;
				}
			}
		}
		//Left
		if (dir == Direction.LEFT) {
			sprite = Sprite.player_left;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_left1;
				}
				else {
					sprite = Sprite.player_left2;
				}
			}
		}
		//Up
		if (dir == Direction.UP) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_forward1;
				}
				else {
					sprite = Sprite.player_forward2;
				}
			}
		}
		//Right
		if (dir == Direction.RIGHT) {
			sprite = Sprite.player_right;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_right1;
				}
				else {
					sprite = Sprite.player_right2;
				}
			}
		}
		int gameHeightHPBar = Game.getHeightWOScale() - hpCount.getHeight();
		
		screen.renderMob((int)(x - 16), (int)(y - 16), this);
		screen.renderSprite(0, gameHeightHPBar, Sprite.avatarHP[0], false);
		screen.renderSprite(0, gameHeightHPBar, minusWidth, Sprite.avatarHP[1], false);
		font.renderNumbers(currentHP, 0, gameHeightHPBar - 4, screen);
		screen.renderSprite(15, gameHeightHPBar - 4, Sprite.bar, false);
		font.renderNumbers(healthPoints, 21, gameHeightHPBar - 4, screen);
//		screen.renderSprite(hitBox.x, hitBox.y, new Sprite(hitBox.width, hitBox.height, 0xFF0000), true);
	}

}
