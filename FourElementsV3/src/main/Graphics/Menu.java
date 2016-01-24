package main.Graphics;

import main.Game;
import main.Input.Keyboard;

public class Menu {

	private Sprite background, airElement;
	private int posOption = 0, timerPush = 0, yCoor;
	
	private Font font;
	private char[] resume, exit;
	
	private int xp = Game.getWidthWOScale() - 65;
	private int yp = 0;
	
	private Keyboard key;
	
	public Menu(Sprite background, Sprite airElement, Keyboard key) {
		this.background = background;
		this.airElement = airElement;
		this.key = key;
		
		font = new Font(Sprite.letters);
		resume = font.getStringsArray("RESUME");
		exit = font.getStringsArray("EXIT");
	}
	
	public void update(Game game) {
		if (key.arrowUp && timerPush > 10) {
			posOption--;
			if (posOption < 0) posOption = 1;
			timerPush = 0;
		}
		if (key.arrowDown && timerPush > 10) {
			posOption++;
			if (posOption > 1) posOption = 0;
			timerPush = 0;
		}
		
		if (posOption == 0) {
			yCoor = 72;
		} else {
			yCoor = 87;
		}
		
		if (key.enter && timerPush > 10) {
			timerPush = 0;
			if (posOption == 0) {
				game.gameResume();
			} else if (posOption == 1) {
				game.initMainTitle();
				game.gameResume();
			}
		}
		timerPush++;
	}
	
	public void render(Screen screen) {
		screen.renderSprite(xp, yp, background, false);
		screen.renderSprite(xp, yp, airElement, false);
		font.renderString(resume, xp + 12, 75, false, screen);
		font.renderString(exit, xp + 17, 90, false, screen);
		screen.renderSprite(xp + 8, yCoor, Sprite.selectedOption, false);
//		screen.renderSprite(xp + 8, 87, selectedOption[0], false);
	}
}
