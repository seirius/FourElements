package main.Graphics;

import java.util.ArrayDeque;

public class Font {

	private Sprite[] sprites;
	
	public Font(Sprite[] sprites) {
		this.sprites = sprites;
	}
	
	public void renderString(char[] array, int xp, int yp, boolean fixed, Screen screen) {
		for (int i = 0; i < array.length; i++) {
			screen.renderSprite(xp, yp, getSpriteLetters(array[i]), fixed);
			xp += 6;
		}
	}
	
	public Sprite getSpriteLetters(char character) {
		switch(character) {
		case 'A': 
			return sprites[0];
		case 'B':
			return sprites[1];
		case 'C':
			return sprites[2];
		case 'D':
			return sprites[3];
		case 'E':
			return sprites[4];
		case 'F': 
			return sprites[5];
		case 'G':
			return sprites[6];
		case 'H':
			return sprites[7];
		case 'I':
			return sprites[8];
		case 'J':
			return sprites[9];
		case 'K': 
			return sprites[10];
		case 'L':
			return sprites[11];
		case 'M':
			return sprites[12];
		case 'N':
			return sprites[13];
		case 'O':
			return sprites[14];
		case 'P': 
			return sprites[15];
		case 'Q':
			return sprites[16];
		case 'R':
			return sprites[17];
		case 'S':
			return sprites[18];
		case 'T':
			return sprites[19];
		case 'U':
			return sprites[20];
		case 'V':
			return sprites[21];
		case 'W':
			return sprites[22];
		case 'X': 
			return sprites[23];
		case 'Y':
			return sprites[24];
		case 'Z':
			return sprites[25];
		case '.':
			return sprites[26];
		case ' ':
			return sprites[27];
		default:
			return sprites[27];
		}
	}
	
	public char[] getStringsArray(String text) {
		char[] array = text.toCharArray();
		return array;
	}
	
	public void renderNumbers(int number, int xp, int yp, Screen screen) {
		ArrayDeque<Integer> array = new ArrayDeque<Integer>();
		int xPos = 0;
		if (number == 0) {
			screen.renderSprite(xp, yp, sprites[0], false);
			screen.renderSprite(xp + 5, yp, sprites[0], false);
			screen.renderSprite(xp + 10, yp, sprites[0], false);
		} else {
			while (number > 0) {
				array.addFirst(number%10);
				number /= 10;
			}
			for (Integer i: array) {
				screen.renderSprite(xPos + xp, yp, sprites[i], false);
				xPos += 5;
			}
		}
	}
}
