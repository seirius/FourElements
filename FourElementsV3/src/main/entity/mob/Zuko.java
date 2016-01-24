package main.entity.mob;

import java.awt.Rectangle;

import main.Game;
import main.Graphics.Font;
import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Level.ZukoLevel;
import main.entity.projectile.FireAtack;

public class Zuko extends Mob {
	
	private Font font;
	private char[] phrase, phraseZero;
	private boolean showHP = false;
	
	private double xa = 0; 
	private double ya = 0;
	private double speed = 0.8;
	
	private int fireRate = 0;
	private int contShots = 0;
	private int fireRateNova = 0;
	private int numfireRateHellFire = 900, fireRateHellFire = numfireRateHellFire;
	private int minusWidth = 0, porcCurrentHP = 100;
	private int halfScreenWidth, halfhpCountWIdth;
	
	private boolean talk = false;
	private int talkCont = 0;
	
	public Zuko(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.zuko_back;
		hitBox = new Rectangle((int)this.x - 6, (int)this.y + 1, 11, 13);
		
		hpCount = Sprite.zukoHP2[0];
		halfhpCountWIdth = Sprite.zukoHP2[0].getWidth() >> 1;
		halfScreenWidth = Game.getWidthWOScale() >> 1;
		
		healthPoints = 750; //1000
		currentHP = healthPoints;
		
		font = new Font(Sprite.letters);
		phrase = font.getStringsArray("YOU MADE ME ANGRY!");
		phraseZero = font.getStringsArray("LETS DANCEEE");
	}
	
	private void updateHealthBar() {
		if (time % 10 == 0) {
			porcCurrentHP = currentHP * 100 / healthPoints;
		} 
		
		if (porcCurrentHP >= 100) 
			minusWidth = 0;
		else if (porcCurrentHP > 98) 
			minusWidth = 10;
		else if (porcCurrentHP > 91) 
			minusWidth = 20;
		else if (porcCurrentHP > 84) 
			minusWidth = 30;
		else if (porcCurrentHP > 77) 
			minusWidth = 40;
		else if (porcCurrentHP > 70) 
			minusWidth = 50;
		else if (porcCurrentHP > 63) 
			minusWidth = 60;
		else if (porcCurrentHP > 56) 
			minusWidth = 70;
		else if (porcCurrentHP > 49) 
			minusWidth = 80;
		else if (porcCurrentHP > 42) 
			minusWidth = 90;
		else if (porcCurrentHP > 35) 
			minusWidth = 100;
		else if (porcCurrentHP > 28) 
			minusWidth = 110;
		else if (porcCurrentHP > 21) 
			minusWidth = 120;
		else if (porcCurrentHP > 14) 
			minusWidth = 130;
		else if (porcCurrentHP >= 7) 
			minusWidth = 140;
	}
	
	
	public void showHPBar(double distance) {
		if (distance < 170) {
			showHP = true;
		} else {
			showHP = false;
		}
	}
	
	public int getCurrentHP() {
		return currentHP;
	}
	
	private void move(double distance) {

		if (distance > 125) {
			xa = 0;
			ya = 0;
			Player player = level.getPlayer();
			if ((int) x < (int) player.getX())
				xa += speed;
			if ((int) x > (int) player.getX())
				xa -= speed;
			if ((int) y > (int) player.getY())
				ya -= speed;
			if ((int) y < (int) player.getY())
				ya += speed;
		} else {
			if (time % (random.nextInt(50) + 30) == 0) {
				xa = (random.nextInt(2) - 1) * speed;
				ya = (random.nextInt(2) - 1) * speed;
			}
		}

	}
	
	public void autoMove(int destinationX, int destinationY) {
		
		destinationX = destinationX << 4;
		destinationY = destinationY << 4;
		
		xa = 0;
		ya = 0;
		Player player = level.getPlayer();
		if ((int) x < (int) player.getX())
			xa += speed;
		if ((int) x > (int) player.getX())
			xa -= speed;
		if ((int) y > destinationY)
			ya -= speed;
		if ((int) y < destinationY)
			ya += speed;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

		if ((int) y == destinationY) {
			talk = true;
		}
			
	} 

	@Override
	public void update() {

		double distance = level.getPlayerDistance(this);

		if (!stop && !((ZukoLevel)level).getCinematic(0)) {
			if (fireRate > 0)
				fireRate--;
			if (fireRateNova > 0)
				fireRateNova--;
			if (fireRateHellFire > 0)
				fireRateHellFire--;

			move(distance);

			if (xa != 0 || ya != 0) {
				move(xa, ya);
				walking = true;
			} else {
				walking = false;
			}

			updateShooting(distance);
		} 
		
		if (!stop && currentHP <= 0) {
			stop = true;
		}

		updateHitBox(-6, 1);
		updateHealthBar();
		showHPBar(distance);

		if (anim < 9999)
			anim++;
		else
			anim = 0;
		
		time++;
		
		if (talk) talkCont++;
	}
	
	public int getTalkcont() {
		return talkCont;
	}
	
	private void updateShooting(double distance) {
		if (fireRateHellFire <= 0) {
			hellFire();
			fireRateHellFire += numfireRateHellFire;
		}
		if (fireRate <= 0 && distance < 150 && contShots < 4) {
			double dir = dirPlayer();
			shoot(x, y, 250, dir, 1);
			fireRate += FireAtack.FIRERATE;
			contShots++;
			if (contShots > 2) {
				fireRate += 110;
				contShots = 0;
			}
		}
		if (fireRateNova <= 0 && distance < 140) {
			fireNova();
		}
	}
	
	private void hellFire() {
		for (int i = 0; i < 30; i ++) {
			shoot(x, y, random.nextInt(200) + 50, random.nextDouble() * 6.26 - 3.13, 2);
		}
	}
	
	private void fireNova() {
		double dir = -3.11;
		while (dir < 3.12) {
			shoot(x, y, 250, dir, 4);
			dir += 0.3;
		}
		fireRateNova += 180;
	}
	
	public double dirPlayer() {
		Player player = level.getPlayer();
		double dx = player.getX() - x;
		double dy = player.getY() - y;
		double dir = Math.atan2(dy, dx);
		return dir;
	}

	@Override
	public void render(Screen screen) {
		
		if ( ((ZukoLevel)level).getCinematic(0) && talk) {
			if (anim % 20 > 10) {
				sprite = Sprite.zukoCinematic[0];
			} else {
				sprite = Sprite.zukoCinematic[1];
			}
			font.renderString(phraseZero, (int)getX() - 35, (int)getY() - 25, true, screen);
		} else {
			renderStageZero();
		}
		
		screen.renderMob((int)(x - 16), (int)(y - 16), this);
		
		if (showHP && currentHP > 0) {
//			screen.renderSprite( (Game.getWidthWOScale() >> 1) - (hpCount.getWidth() >> 1), 0, hpCount, false);
			screen.renderSprite( (halfScreenWidth) - (halfhpCountWIdth), 0, Sprite.zukoHP2[0], false);
			if (anim % 20 > 10) {
				screen.renderSprite( (halfScreenWidth) - (halfhpCountWIdth), 0, minusWidth, Sprite.zukoHP2[1], false);
			} else {
				screen.renderSprite( (halfScreenWidth) - (halfhpCountWIdth), 0, minusWidth, Sprite.zukoHP2[2], false);
			}
		} else if (currentHP <= 0) {
			font.renderString(phrase, (int)getX() - 50, (int)getY() - 20, true, screen);
		}
//		screen.renderSprite(hitBox.x, hitBox.y, new Sprite(hitBox.width, hitBox.height, 0xFF0000), true);
	}
	
	private void renderStageZero() {
		if (currentHP <= 0) {
			if (anim % 20 > 10)
				sprite = Sprite.zukoDefeatedV2;
			else 
				sprite = Sprite.zukoDefeatedV22;
		} else {
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
		}
	}

	public void setTalk(boolean talk) {
		this.talk = talk;
	}
}
