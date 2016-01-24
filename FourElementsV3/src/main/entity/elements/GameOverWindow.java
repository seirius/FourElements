package main.entity.elements;

import main.Game;
import main.Graphics.Font;
import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Input.Keyboard;

public class GameOverWindow extends Element {

	private Sprite sprite;
	private Font font;
	private char[] retry, exit, gameOverText;
	
	private int posOption = 0, timerPush = 0, xCoor;
	
	public GameOverWindow(Sprite sprite) {
		this.sprite = sprite;
		this.x = (Game.getWidthWOScale() >> 1) - (this.sprite.getWidth() >> 1);
		this.y = (Game.getHeightWOScale() >> 1) - (this.sprite.getHeight() >> 1);
		
		font = new Font(Sprite.letters);
		
		retry = font.getStringsArray("RETRY");
		exit = font.getStringsArray("EXIT");
		gameOverText = font.getStringsArray("GAME OVER");
	}
	
	public void update(Game game, Keyboard key) {
		if (key.arrowLeft && timerPush > 10) {
			posOption--;
			if (posOption < 0) posOption = 1;
			timerPush = 0;
		}
		if (key.arrowRight && timerPush > 10) {
			posOption++;
			if (posOption > 1) posOption = 0;
			timerPush = 0;
		}
		
		if (posOption == 0) {
			xCoor = 53;
		} else {
			xCoor = 103;
		}
		
		if (key.enter && timerPush > 10) {
			timerPush = 0;
			if (posOption == 0) {
				game.initGame();
				game.gameStartOver();
			} else if (posOption == 1) {
				game.gameStartOver();
				game.initMainTitle();
			}
		}
		
		timerPush++;
	}
	
	public void render(Screen screen) {
		screen.renderSprite(x, y, Sprite.gameOverWindow, false);
		font.renderString(retry, x + 60, y + 59, false, screen);
		font.renderString(exit, x + 110, y + 59, false, screen);
		font.renderString(gameOverText, x + 73, y + 30, false, screen);
		screen.renderSprite(x + xCoor, y + 56, Sprite.selectedOption, false);
	}
}
