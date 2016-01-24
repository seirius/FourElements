package main.entity.projectile;

import java.awt.Rectangle;

import main.Graphics.Screen;
import main.Graphics.Sprite;

public class EarthCrasher extends Projectile {
	
	private Rectangle hitBox1, hitBox2, hitBox3, hitBox4;
	private Sprite northR, westR, southR, eastR;
	private int playerX, playerY;
	private int time = 0;

	public EarthCrasher(double x, double y, double dir) {
		super(x, y, dir);
		
		hitBox1 = hitBox;
		
		northR = Sprite.earthCrash[15];
		westR = Sprite.earthCrash[0];
		southR = Sprite.earthCrash[10];
		eastR = Sprite.earthCrash[5];
		
		
	}
	
	public void update() {
		if (anim > 999999999) anim = 0;
		
		anim++;
		
		if (anim < 2) {
			playerX = (int)level.getPlayer().getX();
			playerY = (int)level.getPlayer().getY();
			
			hitBox1 = new Rectangle(playerX - 16, playerY - 64, 25, 31);
			hitBox2 = new Rectangle(playerX + 48, playerY - 16, 25, 31);
			hitBox3 = new Rectangle(playerX - 16, playerY + 32, 25, 31);
			hitBox4 = new Rectangle(playerX - 80, playerY - 16, 25, 31);
		}
		
		time++;
		
		if (time > 60) {
			if (hitBox1.y != playerY - 24) 
				hitBox1.setLocation(hitBox1.x, hitBox1.y + 2);
			if (hitBox2.x != playerX + 8)
				hitBox2.setLocation(hitBox2.x - 2, hitBox2.y);
			if (hitBox3.y != playerY + 6)
				hitBox3.setLocation(hitBox3.x, hitBox3.y - 2);
			if (hitBox4.x != playerX - 40)
				hitBox4.setLocation(hitBox4.x + 2, hitBox4.y);
		}
	}
	
	public void render(Screen screen) {
		if (anim < 10) {
			northR = Sprite.earthCrash[10];
			westR = Sprite.earthCrash[5];
			southR = Sprite.earthCrash[15];
			eastR = Sprite.earthCrash[0];
		} else if (anim < 20) {
			northR = Sprite.earthCrash[11];
			westR = Sprite.earthCrash[6];
			southR = Sprite.earthCrash[16];
			eastR = Sprite.earthCrash[1];
		} else if (anim < 30) {
			northR = Sprite.earthCrash[12];
			westR = Sprite.earthCrash[7];
			southR = Sprite.earthCrash[17];
			eastR = Sprite.earthCrash[2];
		} else if (anim > 60) {
			if (anim % 20 > 10) {
				northR = Sprite.earthCrash[13];
				westR = Sprite.earthCrash[8];
				southR = Sprite.earthCrash[18];
				eastR = Sprite.earthCrash[3];
			} else {
				northR = Sprite.earthCrash[14];
				westR = Sprite.earthCrash[9];
				southR = Sprite.earthCrash[19];
				eastR = Sprite.earthCrash[4];
			}
		}
//		System.out.println(hitBox2.x);
//		System.out.println("P: "+playerX);
		screen.renderSprite(hitBox1.x, hitBox1.y, northR, true);
		screen.renderSprite(hitBox2.x, hitBox2.y, westR, true);
		screen.renderSprite(hitBox3.x, hitBox3.y, southR, true);
		screen.renderSprite(hitBox4.x, hitBox4.y, eastR, true);
//		screen.renderSprite(hitBox1.x, hitBox1.y, new Sprite(hitBox1.width, hitBox1.height, 0xFF0000), true);
			
	}

}
