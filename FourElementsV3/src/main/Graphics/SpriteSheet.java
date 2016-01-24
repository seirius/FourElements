package main.Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] pixels;
	
	public static SpriteSheet playerSheet = new SpriteSheet("/Textures/spriteSheets/playerSheet.png", 96, 128);
	public static SpriteSheet zukoSheet = new SpriteSheet("/Textures/spriteSheets/Mobs/Zuko.png", 96, 160);
	public static SpriteSheet zuko2nd = new SpriteSheet("/Textures/spriteSheets/Mobs/Zuko2nd.png", 128, 160);
	public static SpriteSheet kataraSheet = new SpriteSheet("/Textures/spriteSheets/Mobs/Katara.png", 96, 128);
	public static SpriteSheet tophSheet = new SpriteSheet("/Textures/spriteSheets/Mobs/Toph.png", 96, 160);
	
	
	public static SpriteSheet beetle = new SpriteSheet("/Textures/spriteSheets/Monsters/beetle.png", 64, 128);
	public static SpriteSheet emibluh = new SpriteSheet("/Textures/spriteSheets/Monsters/PrototypeMonster.png", 128, 160);
	
	public static SpriteSheet tiles = new SpriteSheet("/Textures/spriteSheets/spriteSheet.png", 256);
	public static SpriteSheet spawn_level = new SpriteSheet("/Textures/spriteSheets/spawn_level.png", 80);
	public static SpriteSheet projectile_aang = new SpriteSheet("/Textures/spriteSheets/Projectiles/airbending.png", 96);
	public static SpriteSheet airMeleeAtack = new SpriteSheet("/Textures/spriteSheets/Projectiles/airMeleeAtackv2.png", 64, 32);
	public static SpriteSheet airMeleeAtackUpDown = new SpriteSheet("/Textures/spriteSheets/Projectiles/airMeleeAtackUpDownv2.png", 32, 64);
	public static SpriteSheet earthCrash = new SpriteSheet("/Textures/spriteSheets/Projectiles/EarthCrash.png", 160, 128);
	public static SpriteSheet beetleBabas1 = new SpriteSheet("/Textures/spriteSheets/Projectiles/babas1.png", 128, 128);
	public static SpriteSheet beetleBabas2 = new SpriteSheet("/Textures/spriteSheets/Projectiles/babas2.png", 128, 128);
	public static SpriteSheet zukoMap = new SpriteSheet("/Textures/spriteSheets/zuko_map.png", 96);
	public static SpriteSheet avatarHP = new SpriteSheet("/Textures/spriteSheets/avatarHP.png", 80, 32);
	public static SpriteSheet numbers = new SpriteSheet("/Textures/spriteSheets/CharactersFonts/numbers.png", 80, 8);
	public static SpriteSheet characters = new SpriteSheet("/Textures/spriteSheets/CharactersFonts/characters.png", 80, 8);
	public static SpriteSheet mainTitle = new SpriteSheet("/Textures/spriteSheets/Title/Title.png", 400, 225);
	public static SpriteSheet mainTitleBackGround = new SpriteSheet("/Textures/spriteSheets/Title/background.png", 400, 225);
	public static SpriteSheet fourElements = new SpriteSheet("/Textures/spriteSheets/fourElements.png", 256, 64);
	public static SpriteSheet buttons = new SpriteSheet("/Textures/spriteSheets/Title/buttons.png", 256, 168);
	public static SpriteSheet zukoHP = new SpriteSheet("/Textures/spriteSheets/zukoHP.png", 120, 120);
	public static SpriteSheet zukoHP2 = new SpriteSheet("/Textures/spriteSheets/zukoHP2.png", 150, 100);
	public static SpriteSheet letters = new SpriteSheet("/Textures/spriteSheets/CharactersFonts/letters.png", 80, 24);
	public static SpriteSheet menuBackground = new SpriteSheet("/Textures/spriteSheets/Interface/interface.png", 65, 225);
	public static SpriteSheet selectedOption = new SpriteSheet("/Textures/spriteSheets/Interface/selectedOption.png", 44, 16);
	public static SpriteSheet gameOverWindow = new SpriteSheet("/Textures/spriteSheets/Interface/gameOverWindow.png", 200, 100);
	public static SpriteSheet fogOfWar = new SpriteSheet("/Textures/spriteSheets/Interface/fogWar.png", 400, 225);
	
	//Items
	public static SpriteSheet airBuff = new SpriteSheet("/Textures/spriteSheets/Items/airBuff.png", 96, 32);
	
	
	
	//Pruebas
	public static SpriteSheet infernoPointer = new SpriteSheet("/Textures/spriteSheets/Projectiles/Inferno/Inferno.png", 144, 48);
	public static SpriteSheet comet = new SpriteSheet("/Textures/spriteSheets/Projectiles/Inferno/Comet.png", 96, 32);
	
	
	//Cenimatica de Zuko
	public static SpriteSheet zukoStageCh = new SpriteSheet("/Textures/spriteSheets/Cinematics/ZukoStageChange.png", 384, 480);
	public static SpriteSheet zukoCinematic = new SpriteSheet("/Textures/spriteSheets/Cinematics/ZukoCinematic.png", 96, 32);
	public static SpriteSheet zukoLeaving = new SpriteSheet("/Textures/spriteSheets/Cinematics/ZukoLeaving.png", 128, 32);
	
	//Cinematica de Toph
	public static SpriteSheet earthApp = new SpriteSheet("/Textures/spriteSheets/Cinematics/earthApp.png", 160, 32);
	
	
	//Thanks
	public static SpriteSheet thanks = new SpriteSheet("/Textures/spriteSheets/Thanks.png", 400, 225);
	
	
	//Este constructor era para cargar una parte de un Sheet, no lo hago porque no me parece correcto 
	//cargar una imagen + partes de su imagen solo para quitar los Sprites creados de la clase Sprite
/*	public SpriteSheet(SpriteSheet sheet, int x, int y, int width , int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = width;
		else 				 SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + y0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
	}*/
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		this.WIDTH = size;
		this.HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.SIZE = -1;
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}
	
	public static Sprite[] getSprites(int width, int height, int columns, int rows,  SpriteSheet sheet) {
		Sprite[] array = new Sprite[rows * columns];
		int arrayCont = 0;
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				array[arrayCont] = new Sprite(width, height, x, y, sheet);
				arrayCont++;
			}
		}
		return array;
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
