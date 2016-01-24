package main.entity.mob;

import main.Graphics.Screen;
import main.Graphics.Sprite;

public class Katara extends Mob {
	
	private int anim = 0;
	private boolean walking = false;
	
	private double xa = 0; 
	private double ya = 0;
	
	private int time = 0;
	private double speed = 0.8;
	
	public Katara(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		
		sprite = Sprite.katara_back;
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
				//xa = 0;
				//ya = 0;
			}
		}
	}

	@Override
	public void update() {
		move();
		
		if (anim < 9999) anim++;
		else anim = 0;
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}
	}

	@Override
	public void render(Screen screen) {
		//Down
		if (dir == Direction.DOWN) {
			sprite = Sprite.katara_back;
			if (walking) {
				if (anim% 20 > 10) {
					sprite = Sprite.katara_back1;
				}
				else {
					sprite = Sprite.katara_back2;
				}
			}
		}
		//Left
		if (dir == Direction.LEFT) {
			sprite = Sprite.katara_left;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.katara_left1;
				}
				else {
					sprite = Sprite.katara_left2;
				}
			}
		}
		//Up
		if (dir == Direction.UP) {
			sprite = Sprite.katara_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.katara_forward1;
				}
				else {
					sprite = Sprite.katara_forward2;
				}
			}
		}
		//Right
		if (dir == Direction.RIGHT) {
			sprite = Sprite.katara_right;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.katara_right1;
				}
				else {
					sprite = Sprite.katara_right2;
				}
			}
		}
		screen.renderMob((int)(x - 16), (int)(y - 16), this);
	}

}
