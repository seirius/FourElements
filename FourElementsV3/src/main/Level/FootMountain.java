package main.Level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;
import main.Input.Keyboard;
import main.entity.items.AirBuff;
import main.entity.items.HPBuff;
import main.entity.mob.Emibluh;

public class FootMountain extends Level {
	
	private int chLevelTimer = 0;

	public FootMountain(String path) {
		super(path);
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
		
		if (player.getY() < 0) {
			chLevelTimer++;
			game.setScreenRendering(false);
			game.cleanScreen();
			if (chLevelTimer > 120) {
				game.setScreenRendering(true);
				game.changeLevel(new ZukoLevel("/levels/zukoMap.png"), new TileCoordinate(14, 36));
			}
		}
	}
	
	public void generateLevel() {
		add(new Emibluh(4, 16));
		add(new Emibluh(4, 25));
		add(new Emibluh(4, 34));
		add(new AirBuff(1, 37, 20, 20, 999999999));
		add(new HPBuff(57, 37, 20, 20, 999999999));
		add(new Emibluh(52, 17));
		add(new Emibluh(57, 10));
		add(new Emibluh(51, 35));
	}
}
