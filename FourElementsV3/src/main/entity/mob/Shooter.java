package main.entity.mob;

import main.Graphics.Screen;
import main.Graphics.Sprite;

public class Shooter extends Mob {
	
	private double xa = 0;
	private double ya = 0;
	private double time = 0;
	
	private double speed = 0.8;
	
	public Shooter(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.zuko_back;
	}

	@Override
	public void update() {
		//Disparo al jugador
		Player player = level.getPlayer();
		double dx = player.getX() - x;
		double dy = player.getY() - y;
		double dir = Math.atan2(dy, dx);
		shoot(x, y, 100, dir, 1);
		
		time++;
		
		if (time % (random.nextInt(50) + 30) == 0) {
			xa = (random.nextInt(3) - 1) * speed;
			ya = (random.nextInt(3) - 1) * speed;
			if (random.nextInt(3) == 0) {
				//xa = 0;
				//ya = 0;
			}
		}
		
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			//walking = true;
		}
		else {
			//walking = false;
		}
	}

	@Override
	public void render(Screen screen) {
		screen.renderMob((int)x - 16, (int)y - 16, this);
	}

}
