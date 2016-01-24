package main.entity.mob;

import java.awt.Rectangle;

import main.Graphics.Font;
import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.projectile.EarthCrasher;

public class Toph extends Mob{

	private Font font;
	private char[] phrase, phrase2;
	
	private Sprite earthApp1;
//	
//	private double xa = 0; 
//	private double ya = 0;
//	private double speed = 0.8;
//	
//	private int fireRate = 0;
//	private int minusWidth = 0, porcCurrentHP = 100;
	
	
	public Toph(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = null;
		hitBox = new Rectangle((int)this.x - 6, (int)this.y + 1, 11, 13);
		
		healthPoints = 750; //1000
		currentHP = healthPoints;
		
		earthApp1 = Sprite.earthApp[0];
		
		font = new Font(Sprite.letters);
		phrase = font.getStringsArray("OH YOU ACTUALLY OVERCAME ZUKO");
		phrase2 = font.getStringsArray("THIS TIME WONT BE THAT EASY!");
	}
	
	
	public void update() {
		if (anim > 999999999) anim = 2000;
		anim++;
		
		if (anim == 331) {
			level.add(new EarthCrasher((int)x, (int)y, 0));
			setDirection(Direction.DOWN);
		}
	}
	
	public int getAnim() {
		return anim;
	}

	public void render(Screen screen) {
		if (anim < 10) 
			earthApp1 = Sprite.earthApp[0];
		else if (anim < 20) 
			earthApp1 = Sprite.earthApp[1];
		else if (anim < 30) 
			earthApp1 = Sprite.earthApp[2];
		else if (anim < 40) 
			earthApp1 = Sprite.earthApp[3];
		else if (anim < 50) {
			earthApp1 = Sprite.earthApp[4];
			sprite = Sprite.toph[0];
		}
		else if (anim < 60)
			earthApp1 = Sprite.earthApp[3];
		else if (anim < 70)
			earthApp1 = Sprite.earthApp[2];
		else if (anim < 80)
			earthApp1 = Sprite.earthApp[1];
		else if (anim > 79 && anim < 90)
			earthApp1 = Sprite.earthApp[0];
		
		if (anim > 90 && anim < 330) {
			if (anim % 20 > 10) {
				sprite = Sprite.toph[0];
			} else {
				sprite = Sprite.toph[12];
			}
		} else if (anim > 330){
			renderToph(screen);
		}
		
		if (sprite != null) screen.renderMob((int)(x - 16), (int)(y - 16), this);
		if (anim > 0 && anim < 90) screen.renderSprite((int)(x - 16), (int)(y - 16), earthApp1, true);
		if (anim > 90 && anim < 210) font.renderString(phrase, (int)getX() - 85, (int)getY() - 25, true, screen);
		else if (anim > 209 && anim < 330) font.renderString(phrase2, (int)getX() - 80, (int)getY() - 25, true, screen);
		
//		screen.renderSprite(hitBox.x, hitBox.y, new Sprite(hitBox.width, hitBox.height, 0xFF0000), true);
	}
	
	public void renderToph(Screen screen) {
		//Down
		if (dir == Direction.DOWN) {
			sprite = Sprite.toph[0];
			if (walking) {
				if (anim% 20 > 10) {
					sprite = Sprite.toph[1];
				}
				else {
					sprite = Sprite.toph[2];
				}
			}
		}
		//Left
		if (dir == Direction.LEFT) {
			sprite = Sprite.toph[3];
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.toph[4];
				}
				else {
					sprite = Sprite.toph[5];
				}
			}
		}
		//Up
		if (dir == Direction.UP) {
			sprite = Sprite.toph[9];
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.toph[10];
				}
				else {
					sprite = Sprite.toph[11];
				}
			}
		}
		//Right
		if (dir == Direction.RIGHT) {
			sprite = Sprite.toph[6];
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.toph[7];
				}
				else {
					sprite = Sprite.toph[8];
				}
			}
		}
	}

}
