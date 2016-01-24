package main.Graphics;

import java.util.Random;

import main.Level.tile.Tile;
import main.entity.mob.Mob;
import main.entity.mob.Star;
import main.entity.projectile.Projectile;

public class Screen {

	public int width;
	public int height;

	public int[] pixels;
	public int xOffset, yOffset;
	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height]; // 50.400

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != 0xFFFF4CD5) {
					pixels[xa + ya * width] = col;
				}
			}
		}
	}
	
	public void renderSprite(int xp, int yp, int minusWidth, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth() - minusWidth; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != 0xFFFF4CD5) {
					pixels[xa + ya * width] = col;
				}
			}
		}
	}

	// xp || yp = posicion de casilla; xa || ya = posicion absoluta segun el
	// movimiento de la casilla
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0
						|| ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y
						* tile.sprite.SIZE];
			}
		}
	}

	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0|| ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
				if (col != 0xFFFF4CD5) {
					pixels[xa + ya * width] = col;
				}
			}
		}
	}

	public void renderMob(int xp, int yp, Mob mob) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = mob.getSprite().pixels[x + (y << 5)];
				if ((mob instanceof Star) && col == 0xFF650300)
					col = 0xFFFFD002;
				// Se añade FF al color porque en SpriteSheet se carga la imagen
				// con Alpha
				// y en Game la imagen es sin el, por lo que hace falta hacer
				// esto
				if (col != 0xFFFF4CD5) {
					pixels[xa + ya * width] = col;
				}
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
