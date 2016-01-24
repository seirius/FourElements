package main.entity.mob;

import java.awt.Rectangle;

import main.Game;
import main.Graphics.Font;
import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Level.ZukoLevel;
import main.entity.projectile.FireAtack;

public class Zuko2 extends Mob {
	
	private Font font;
	private char[] phrase, phrase2, phrase3;
	private boolean showHP = false;
	
	private Sprite deathWishSprite;
	
	private double xa = 0; 
	private double ya = 0;
	private double speed = 1.6;
	
	private int fireRate = 0;
	private int contShots = 0;
	private int fireRateNova = 0;
	private int numfireRateHellFire = 900, fireRateHellFire = numfireRateHellFire, rateApocalipsis = 2000, apocalipsisTimer = 0;
	private int minusWidth = 0, porcCurrentHP = 100;
	private int halfScreenWidth, halfhpCountWIdth;
	
	private int deathCounter = 0;
	
	public Zuko2(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.zuko_back;
		hitBox = new Rectangle((int)this.x - 6, (int)this.y + 1, 11, 13);
		
		hpCount = Sprite.zukoHP2[0];
		deathWishSprite = Sprite.zukoLeaving[0];
		halfhpCountWIdth = Sprite.zukoHP2[0].getWidth() >> 1;
		halfScreenWidth = Game.getWidthWOScale() >> 1;
		
		healthPoints = 1750; //1750
		currentHP = healthPoints;
		
		font = new Font(Sprite.letters);
		phrase = font.getStringsArray("YOU WILL REGRET THIS");
		phrase2 = font.getStringsArray("I WILL SHOW YOU MY FINAL FORM!");
		phrase3 = font.getStringsArray("YOU WILL REGRET THIS");
	}
	
	public Zuko2(int x, int y, boolean anim) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.zuko_back;
		hitBox = new Rectangle((int)this.x - 6, (int)this.y + 1, 11, 13);
		
		hpCount = Sprite.zukoHP2[0];
		halfhpCountWIdth = Sprite.zukoHP2[0].getWidth() >> 1;
		halfScreenWidth = Game.getWidthWOScale() >> 1;
		
		healthPoints = 17; //1750
		currentHP = healthPoints;
		
		font = new Font(Sprite.letters);
		phrase = font.getStringsArray("YOU WILL REGRET THIS");
		phrase2 = font.getStringsArray("I WILL SHOW YOU MY FINAL FORM!");
		phrase3 = font.getStringsArray("YOU WILL REGRET THIS");
		
		if (!anim) {
			this.anim = 1500;
		}
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
	
	public void mobKilled() {
		if (currentHP <= 0) {
			remove();
		}
	}
	
	public void showHPBar(double distance) {
		if (distance < 250) {
			showHP = true;
		} else {
			showHP = false;
		}
	}
	
	public int getCurrentHP() {
		return currentHP;
	}
	
	private void move(double distance) {

		time++;

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
			if (time % (random.nextInt(30) + 25) == 0) {
				xa = (random.nextInt(2) - 1) * speed;
				ya = (random.nextInt(2) - 1) * speed;
			}
		}

	}

	@Override
	public void update() {
		double distance = level.getPlayerDistance(this);
			if (currentHP > 0 && !stop) {
				if (fireRate > 0) fireRate--;
				if (fireRateNova > 0) fireRateNova--;
				if (fireRateHellFire > 0) fireRateHellFire--;
				if (rateApocalipsis > 0) rateApocalipsis--;
				
				move(distance);
				
				updateShooting(distance);
				
				if (xa != 0 || ya != 0) {
					move(xa, ya);
					walking = true;
				}
				else {
					walking = false;
				}
				
			} else {
				walking = false;
			}
			
			updateHitBox(-6, 1);
			updateHealthBar();
			showHPBar(distance);
			
			if (level instanceof ZukoLevel && ((ZukoLevel)level).getCinematic(1))
				anim = ((ZukoLevel)level).getAnim();
			else {
				anim++;
				if (anim > 9999999) anim = 1283;
			}
			
			if (currentHP <= 0) {
				deathCounter++;
				if (deathCounter > 240) {
					if (level instanceof ZukoLevel) {
						((ZukoLevel)level).start3rdStage();
					}
					remove();
				}
			}
			
		}
		
	private void updateShooting(double distance) {
		if (fireRateHellFire <= 0) {
			hellFire();
			double dir = dirPlayer();
			shoot(level.getPlayer().getX(), level.getPlayer().getY(), 0, dir, 5);
			fireRateHellFire = numfireRateHellFire;
		}
		if (fireRate <= 0 && distance < 150 && contShots < 4) {
			double dir = dirPlayer();
			shoot(x, y, 250, dir, 1);
			fireRate = FireAtack.FIRERATE;
			contShots++;
			if (contShots > 2) {
				fireRate += 110;
				contShots = 0;
			}
		}
		if (fireRateNova <= 0 && distance < 140) {
			fireNova();
		}
		
		if (rateApocalipsis <= 0) {
			xa = 0;
			ya = 0;
			apocalipsisTimer++;
			fireRateHellFire = numfireRateHellFire;
			fireRateNova = 180;
			fireRate = FireAtack.FIRERATE;
			if (time % 60 > 58) {
				double dir = dirPlayer();
				for (int i = 0; i < 5; i++) {
					shoot(level.getPlayer().getX() + random.nextInt(128) - 64, level.getPlayer().getY() + random.nextInt(128) - 64, 0, dir, 5);
				}
			}
			if (apocalipsisTimer > 300) {
				rateApocalipsis = 2000;
				apocalipsisTimer = 0;
			}
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
		
		if (anim < 420) {
			//Frases
			if (anim < 300) {
				font.renderString(phrase, (int)getX() - 63, (int)getY() - 25, true, screen);
			} else {
				font.renderString(phrase2, (int)getX() - 85, (int)getY() - 25, true, screen);
			}
			
			//Animacion de hablar
			if (anim % 20 > 10) {
				sprite = Sprite.zukoCinematic[0];
			} else {
				sprite = Sprite.zukoCinematic[1];
			}
		} else if (anim < 1260) {
			sprite = Sprite.zukoCinematic[2];
		} else {
			if (currentHP <= 0) {
				if (anim % 20 > 10)
					sprite = Sprite.zukoDefeatedV2;
				else 
					sprite = Sprite.zukoDefeatedV22;
				if (deathCounter > 240) deathWishSprite = Sprite.zukoLeaving[3];
				else if (deathCounter > 225) deathWishSprite = Sprite.zukoLeaving[2];
				else if (deathCounter > 210) deathWishSprite = Sprite.zukoLeaving[1];
				else if (deathCounter > 179) deathWishSprite = Sprite.zukoLeaving[0];
				font.renderString(phrase3, (int)x - 60, (int)y - 25, true, screen);
			} else {
				renderStageOne();
			}
		}
		
		screen.renderMob((int)(x - 16), (int)(y - 16), this);
		if (deathCounter > 179) screen.renderSprite((int)(x - 16), (int)(y - 16), deathWishSprite, true);
		
		if (showHP && currentHP > 0 && !((ZukoLevel)level).getCinematic(1)) {
			screen.renderSprite( (halfScreenWidth) - (halfhpCountWIdth), 0, Sprite.zukoHP2[0], false);
			if (anim % 20 > 10) {
				screen.renderSprite( (halfScreenWidth) - (halfhpCountWIdth), 0, minusWidth, Sprite.zukoHP2[3], false);
			} else {
				screen.renderSprite( (halfScreenWidth) - (halfhpCountWIdth), 0, minusWidth, Sprite.zukoHP2[4], false);
			}
		}
//		screen.renderSprite(hitBox.x, hitBox.y, new Sprite(hitBox.width, hitBox.height, 0xFF0000), true);
	}
	private void renderStageOne() {
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
	}

}

