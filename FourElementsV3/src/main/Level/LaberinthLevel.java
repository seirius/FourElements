package main.Level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;
import main.Input.Keyboard;
import main.entity.items.AirBuff;
import main.entity.mob.Emibluh;

public class LaberinthLevel extends Level {

	public LaberinthLevel (String path) {
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
	}
	
	public void generateLevel() {
//		add(new Zuko(14, 1));
//		add(new Katara(14, 5));
//		add(new Star(2, 1));
		add(new Emibluh(16, 62));
		add(new Emibluh(25, 62));
		add(new Emibluh(36, 61));
//		add(new Beetle(59, 18));
		add(new AirBuff(63, 13, 20, 20, 600));
		add(new AirBuff(27, 45, 20, 20, 3600));
		add(new AirBuff(21, 45, 20, 20, 3600));
//		add(new Dummy(25, 43));
	}
}
