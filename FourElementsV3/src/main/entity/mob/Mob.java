package main.entity.mob;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.entity.Entity;
import main.entity.items.AirBuff;
import main.entity.items.HPBuff;
import main.entity.items.Item;
import main.entity.projectile.AirAtack;
import main.entity.projectile.BeetleAtack;
import main.entity.projectile.FireAtack;
import main.entity.projectile.HellFire;
import main.entity.projectile.HellFireShot;
import main.entity.projectile.Inferno;
import main.entity.projectile.MeleeAtack;
import main.entity.projectile.Projectile;

public abstract class Mob extends Entity {
	
	protected boolean walking = false;
	protected int anim = 0;
	protected double speed;
	protected int currentHP;
	protected int time = 0;
	protected Sprite hpCount;
	protected Rectangle hitBox;
	protected Direction dir;
	protected int healthPoints;
	protected boolean applyBuff = false;
	protected Item buff = null;
	protected int buffedTime;
	protected boolean woken = false;
	protected int awakeTime = 0, defaultAwake = 0;
	protected boolean solid = true;
	protected boolean stop = false;
	
	public enum Direction {
		UP, RIGHT, DOWN, LEFT
	}
	
	public void damageTheMob(int dmg) {
		if (currentHP > 0) {
			currentHP -= dmg;
			if (currentHP < 0) currentHP = 0;
		}
	}
	
	public void move(double xa, double ya) {
		
		//Para que la colision funcione cuando se colisione verticalmente y se pueda mover horizontalmente
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = Direction.RIGHT; //Right dir
		if (xa < 0) dir = Direction.LEFT; //Left dir
		if (ya > 0) dir = Direction.DOWN; //Down dir
		if (ya < 0) dir = Direction.UP; //Up dir
		
		while (xa != 0) {
			if (Math.abs(xa) > 1) {
				if (!collision(abs(xa), ya) && !collisionEntities(abs(xa), ya)) {
					this.x += abs(xa);
				}
				xa -= abs(xa);
			} else {
				if (!collision(abs(xa), ya)  && !collisionEntities(abs(xa), ya)) {
					this.x += xa;
				}
				xa = 0;
			}
		}
		
		while (ya != 0) {
			if (Math.abs(ya) > 1) {
				if (!collision(xa, abs(ya)) && !collisionEntities(xa, abs(ya))) {
					this.y += abs(ya);
				}
				ya -= abs(ya);
			} else {
				if (!collision(xa, abs(ya)) && !collisionEntities(xa, abs(ya))) {
					this.y += ya;
				}
				ya = 0;
			}
		}
	}
	
	private int abs(double value) {
		if (value < 0) return -1;
		else return 1;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);
	
	protected void shoot(double x, double y, int range, double dir, int projType) {
		Projectile p;
		switch(projType) {
		case 0: 
			p = new AirAtack(x, y, range, dir);
			break;
		case 1: 
			p = new FireAtack(x, y, range, dir);
			break;
		case 2:
			p = new HellFireShot(x, y, range, dir);
			break;
		case 3:
			p = new HellFire(x, y, range, dir);
			break;
		//FireAtackConMenosSpeed
		case 4:
			p = new FireAtack(x, y, range, dir, 1);
			break;
		case 5:
			p = new Inferno(x, y, dir);
			break;
		default: 
			p = new AirAtack(x, y, 10, dir);
		}
		level.add(p);
	}
	
	protected void meleeAtack(double x, double y, int width, int height, int typeMelee, double dir) {
		Projectile p;
		switch (typeMelee) {
		case 1:
			p = new MeleeAtack(x, y, width, height, 0, dir);
			break;
		case 2:
			p = new BeetleAtack(x, y, width, height, 100, dir);
			break;
		default:
			p = new MeleeAtack(x, y, width, height, 0, dir);
			break;
		}
		level.add(p);
	}
	
	private boolean collision(double xa, double ya) {
		boolean solid = false;
		for (int corner = 0; corner < 4; corner++) {
			double xt;
			if (this instanceof Player) xt = (((int) x + (int) xa) + ((corner % 2) * 12) - 6) >> 4;
			else xt = (((int) x + (int) xa) + ((corner % 2) * 12)) >> 4;
			double yt = (((int) y + (int) ya) + ((corner / 2) * 13)) >> 4;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (corner % 2 == 0) ix = (int) Math.floor(xt);
			if (corner / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}
	
	private boolean collisionEntities(double xa, double ya) {
		boolean solid = false;
		ArrayList<Entity> entities = (ArrayList<Entity>)level.getEntitiesList();
		for (int i = 0; i < level.getEntitiesSize(); i++) {
			Entity mob = entities.get(i);
			if (mob instanceof Mob && !mob.equals(this) 
					&& ((Mob)mob).getHitBox().intersects(getNextHitBox(xa, ya))) {
				solid = ((Mob)mob).isSolid();
			}
		}
		return solid;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	private Rectangle getNextHitBox(double xa, double ya) {
		return new Rectangle(hitBox.x + (int)xa, hitBox.y + (int)ya, hitBox.width, hitBox.height);
	}
	
	protected void itemCollision() {
		List<Item> items = level.getItemsList();
		if (items.size() > 0) {
			for (int i = 0; i < items.size(); i++) {
				Item item = items.get(i);
				if (item.getHitBox().intersects(hitBox)) {
					applyBuff(item);
					item.remove();
				}
			}
		}
	}
	
	
	protected void applyBuff(Item item) {
		buff = item;
		if (item instanceof AirBuff) {
			speed += ((AirBuff)item).getExtraSpeed();
			buffedTime = item.getTimeBuffed();
			applyBuff = true;
		} else if (item instanceof HPBuff) {
			healthPoints += ((HPBuff)item).getExtraHP();
			currentHP = healthPoints;
			buffedTime = item.getTimeBuffed();
			applyBuff = true;
		}
		
	}
	
	protected void updateBuff() {
		if (buff != null) {
			buffedTime--;
			if (buffedTime <= 0) {
				if (buff instanceof AirBuff) {
					speed -= ((AirBuff)buff).getExtraSpeed();
				}
				applyBuff = false;
				buff = null;
			}
		}
	}
	
	public void wakeUp() {
		if (woken) {
			reset();
		} else {
			woken = true;
		}
	}
	
	protected void reset() {
		awakeTime = defaultAwake;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	public boolean getStop() {
		return stop;
	}
	
	protected void updateHitBox(int adjustX, int adjustY) {
		hitBox.setLocation(((int)x) + adjustX, ((int)y) + adjustY);
	}
}
