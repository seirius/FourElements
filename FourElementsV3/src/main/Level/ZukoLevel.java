package main.Level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;
import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Input.Keyboard;
import main.entity.Entity;
import main.entity.mob.Mob.Direction;
import main.entity.mob.Toph;
import main.entity.mob.Zuko;
import main.entity.mob.Zuko2;

public class ZukoLevel extends Level {
	
	private boolean playerEntered = false, cinematic = false, cinematicZero = false;
	public boolean start3rdStage = false;
	private int timer = 0, anim = 0;
	private int cinematicX, cinematicY;
	
	public ZukoLevel(String path) {
		super(path);
		
		cinematicX = 12 << 4;
		cinematicY = 9 << 4;
	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level file");
		}
	}
	
	public void update(Game game, Keyboard key) {
		super.update(game, key);
		
		if (!playerEntered && getPlayer().getY() < 330) {
			loadLevel("/levels/zukoMapEntered.png");
			playerEntered = true;
			add(new Zuko(15, 12));
//			add(new Zuko2(15, 12, false));
//			add(new Toph(15, 12));
			cinematicZero = true;
			if (getZuko() != null) {
				player.setStop(true);
				getZuko().setStop(true);
			}
		}
		
		if (getZuko() != null && getZuko().getCurrentHP() <= 0) {
			timer++;
			if (timer > 180) {
				getZuko().remove();
				removeAllProjectiles();
				game.setScreenRendering(false);
				game.cleanScreen();
				add(new Zuko2(15, 12));
				cinematic = true;
				player.setStop(true);
				getZuko2().setStop(true);
				player.setPosition(15 << 4, 16 << 4);
				player.setDirection(Direction.UP);
			}
		}
		
		updateCinematic(game);
		updateEndGame(game);
	}
	
	public void updateCinematic(Game game) {
		if (cinematic) {
			anim++;
			if (anim > 999999999) anim = 0;
			
			if (!game.getScreenRendering() && anim > 120) game.setScreenRendering(true);
			
		}
		
		if (cinematic && anim > 1283) {
			cinematic = false; 
			player.setStop(false);
			if (getZuko2() != null)
				getZuko2().setStop(false);
		}
		
		if (cinematicZero) {
			if (getZuko() != null) {
				getZuko().autoMove(0, 16);
			
				if (getZuko().getTalkcont() > 180) {
					getZuko().setStop(false);
					getZuko().setTalk(false);
					player.setStop(false);
					cinematicZero = false;
				}
			}
		}
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		renderLevel(xScroll, yScroll, screen);
		
		if (cinematic) {
			if (anim > 780 && anim < 810) {
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[6], true);
			} else if (anim > 809 && anim < 840) {
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[7], true);
			} else if (anim > 839 && anim < 870) {
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[8], true);
			} else if (anim > 869 && anim < 900) {
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[9], true);
			} else if (anim > 899 && anim < 930) {
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[10], true);
			} else if (anim > 929 && anim < 960) {
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[11], true);
			} else if (anim > 959 && anim < 990) {
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[12], true);
			}
			
			if (anim > 989 && anim < 1250) {
				if (anim % 20 > 10) {
					screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[13], true);
				} else {
					screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[14], true);
				}
			} else if (anim > 1249 && anim < 1270){
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[15], true);
			} else if (anim > 1269 && anim < 1285) {
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[16], true);
			}
		}
	}
	
	public void renderLevel(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		//Limites de la pantalla en posicion 'casilla'
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		
		if (cinematic) {
			//Animacion de fisuras
			if (anim > 480 && anim < 540) {
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[0], true);
			} else if (anim > 539 && anim < 600) {
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[1], true);
			} else if (anim > 599 && anim < 1223){
				if (anim % 60 > 50) {
					screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[2], true);
				} else if (anim % 60 > 40){
					screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[3], true);
				} else if (anim % 60 > 30){
					screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[4], true);
				} else if (anim % 60 > 20){
					screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[5], true);
				} else if (anim % 60 > 10){
					screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[3], true);
				} else {
					screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[2], true);
				}
			} else if (anim > 1222 && anim < 1243){
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[2], true);
			} else if (anim > 1242 && anim < 1263){
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[1], true);
			} else if (anim > 1262 && anim < 1283){
				screen.renderSprite(cinematicX, cinematicY, Sprite.zukoStageCh[0], true);
			} 
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		for (int i = 0; i < items.size(); i++) {
			items.get(i).render(screen);
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		
		if (gameOverOn) {
			gameOver.render(screen);
		}
	}
	
	public void setCinematic(boolean cinematic) {
		this.cinematic = cinematic;
	}
	
	public boolean getCinematic(int whichCinematic) {
		switch(whichCinematic) {
		case 0: return cinematicZero;
		case 1: return cinematic;
		default: return cinematic;
		}
	}
	
	public void generateLevel() {
//		add(new Zuko(14, 1));
//		add(new Katara(14, 5));
	}
	
	public int getAnim() {
		return anim;
	}
	
	public void start3rdStage() {
		start3rdStage = true;
	}
	
	public void updateEndGame(Game game) {
		if (start3rdStage) {
			player.setStop(true);
			if (player.autoMove(15, 14)) {
				player.setDirection(Direction.UP);
				if (getToph() == null)
					add(new Toph(15, 9));
			}
			if (getToph() != null) {
				if (getToph().getAnim() > 400) {
					game.setThanks(true);
				}
			}
		}
	}
	
	public Toph getToph() {
		for (Entity e: entities) {
			if (e instanceof Toph) {
				return (Toph)e;
			}
		}
		return null;
	}
}
