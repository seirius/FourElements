package main.entity;

import java.util.Random;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Level.Level;

public class Entity {
	
	protected double x, y;
	protected Sprite sprite;
	protected boolean removed = false;
	protected Level level;
	protected Random random = new Random();
	
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
	}
	
	public void remove() {
		//Remove from level
		removed = true;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
}
