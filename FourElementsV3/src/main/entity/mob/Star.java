package main.entity.mob;

import java.util.List;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Level.Node;
import main.util.Vector2i;

public class Star extends Mob {
	
	private int anim = 0;
	private boolean walking = false;
	
	private double xa = 0; 
	private double ya = 0;
//	private double speed = 0.8;
	
	private int time = 0;
	private List<Node> path = null; 
	
	public Star(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.zuko_back;
	}
	
	private void move() {
		xa = 0;
		ya = 0;
		double distance = level.getPlayerDistance(this);
		if (distance < 200) {
		Player player = level.getPlayer();
		int px = ((int)player.getX());
		int py = ((int)player.getY());
		Vector2i start = new Vector2i(((int)getX()) >> 4, ((int)getY()) >> 4);
		Vector2i destination = new Vector2i(px >> 4, py >> 4);
//		if (time % 60 == 0) {
//		}
		path = level.findPath(start, destination);
		if (path != null) {
			if (path.size() > 0) {
				Vector2i vec = path.get(path.size() - 1).tile;
				if (x < vec.getX() << 4) xa++;
				if (x > vec.getX() << 4) xa--;
				if (y < vec.getY() << 4) ya++;
				if (y > vec.getY() << 4) ya--;
			}
		}
		
		
		}
		
		time++;
		if (time > 9999) time = 0;
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}
	}

	@Override
	public void update() {
		move();
		if (anim < 9999) anim++;
		else anim = 0;
	}

	@Override
	public void render(Screen screen) {
		//Down
				if (dir == Direction.DOWN) {
					sprite = Sprite.zuko_back;
					if (walking) {
						if (anim% 20 > 10) {
							sprite = Sprite.zuko_back1;
						}
						else {
							sprite = Sprite.zuko_back2;
						}
					}
				}
				//Left
				if (dir == Direction.LEFT) {
					sprite = Sprite.zuko_left;
					if (walking) {
						if (anim % 20 > 10) {
							sprite = Sprite.zuko_left1;
						}
						else {
							sprite = Sprite.zuko_left2;
						}
					}
				}
				//Up
				if (dir == Direction.UP) {
					sprite = Sprite.zuko_forward;
					if (walking) {
						if (anim % 20 > 10) {
							sprite = Sprite.zuko_forward1;
						}
						else {
							sprite = Sprite.zuko_forward2;
						}
					}
				}
				//Right
				if (dir == Direction.RIGHT) {
					sprite = Sprite.zuko_right;
					if (walking) {
						if (anim % 20 > 10) {
							sprite = Sprite.zuko_right1;
						}
						else {
							sprite = Sprite.zuko_right2;
						}
					}
				}
		screen.renderMob((int)(x - 16), (int)(y - 16), this);
	}

}
