package main.entity.particle;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.Entity;

public class Particle extends Entity {

	private Sprite sprite;
	
	private int life;
	private int time = 0;
	
	protected double xx, yy, zz;
	protected double xa, ya, za;
	
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.particle_default;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}
	
	public Particle(int x, int y, int life, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) - 10);
		this.sprite = sprite;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}	
	
	public void update() {
		time++;
		if (time >= 99999) time = 0; //Control de seguridad por si alguna particula vive demasiado.
		if (time > life) {
			remove();
		}
		
		//Todo esto contiene las fisicas de votar en el suelo
		this.za -= 0.1;
		
		if (zz < 0) {
			zz = 0;
			za *= -0.5;
			xa *= 0.5;
			ya *= 0.5;
		}
		
		move(xx + xa, (yy + ya) + (zz + za));
	}
	
	private void move(double x, double y) {
		if (collision(x, y)) {
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}
	
	public boolean collision(double x, double y) {
		boolean solid = false;
		for (int corner = 0; corner < 4; corner++) {
			double xt = (x - ((corner % 2) << 4)) / 16;
			double yt = (y - ((corner / 2) << 4)) / 16; 
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (corner % 2 == 0) ix = (int) Math.floor(xt);
			if (corner / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy - (int) zz + 7, sprite, true);
	}
}
