package main.Graphics;

import main.Game;
import main.Input.Keyboard;
import main.entity.elements.Button;
import main.entity.elements.Element;

public class MainTitle {
	
	public Sprite sprite;
	
	private Element[] elements = new Element[4];
	private Button[] buttons = new Button[2];
	private int buttonSelected = 0, timerPush = 0;
	
	private Keyboard keyboard;
	
	public MainTitle(Sprite sprite, Keyboard keyboard) {
		this.sprite = sprite;
		this.keyboard = keyboard;
		
		initObjects();
	}
	
	private void initObjects() {
		elements[0] = new Element(80, 16, Sprite.fourElements[0]);
		elements[1] = new Element(144, 80, Sprite.fourElements[1]);
		elements[2] = new Element(80, 144, Sprite.fourElements[2]);
		elements[3] = new Element(16, 80, Sprite.fourElements[3]);
		
		buttons[0] = new Button(240, 66, Sprite.buttons[0], Sprite.buttons[1]);
		buttons[1] = new Button(240, 122, Sprite.buttons[2], Sprite.buttons[3]);
	}
	
	private void selectButtons(int whichButton) {
		for (int i = 0; i < buttons.length; i++) {
			if (i == whichButton) {
				buttons[i].setSelected(true);
			} else {
				buttons[i].setSelected(false);
			}
		}
	}
	
	private void inputsForButtons(Game game) {
		if (keyboard.arrowUp && timerPush > 10) {
			buttonSelected++;
			if (buttonSelected >= buttons.length) buttonSelected = 0;
			timerPush = 0;
		}
		if (keyboard.arrowDown && timerPush > 10) {
			buttonSelected--;
			if (buttonSelected < 0) buttonSelected = buttons.length - 1;
			timerPush = 0;
		}
		if (keyboard.enter && timerPush > 10) {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i].getSelected() && i == 0) {
					game.setMainTitleNull();
					game.initGame();
				} else if (buttons[i].getSelected() && i == 1) {
					game.closeGame();
				}
			}
			timerPush = 0;
		}
		timerPush++;
	}
	
	public void renderElements(Screen screen) {
		for (int i = 0; i < 4; i++) {
			elements[i].render(screen);
		}
	}
	
	public void update(Game game) {
		inputsForButtons(game);
		selectButtons(buttonSelected);
	}
	
	
	public void render(Screen screen) {
		screen.renderSprite(0, 0, sprite, false);
		renderElements(screen);
		for (Button b: buttons) {
			b.render(screen);
		}
	}
}
