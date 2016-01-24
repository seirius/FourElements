package main.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	private boolean[] keys = new boolean[120];
	public boolean up, right, down, left;
	public boolean arrowUp, arrowRight, arrowDown, arrowLeft;
	public boolean enter, esc;
	
	public void update() {
		up = keys[KeyEvent.VK_W];
		right = keys[KeyEvent.VK_D];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		
		arrowUp = keys[KeyEvent.VK_UP];
		arrowRight = keys[KeyEvent.VK_RIGHT];
		arrowDown = keys[KeyEvent.VK_DOWN];
		arrowLeft = keys[KeyEvent.VK_LEFT];
		
		enter = keys[KeyEvent.VK_ENTER];
		esc = keys[KeyEvent.VK_ESCAPE];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			keys[e.getKeyCode()] = true;
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("(Pressed)Key fuera del rango asignado '120': " + e.getKeyCode());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			keys[e.getKeyCode()] = false;
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("(Released)Key fuera del rango asignado '120': " + e.getKeyCode());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
