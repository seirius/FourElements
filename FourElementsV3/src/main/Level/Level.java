package main.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.Game;
import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Input.Keyboard;
import main.Level.tile.Tile;
import main.entity.Entity;
import main.entity.elements.GameOverWindow;
import main.entity.items.Item;
import main.entity.mob.Mob;
import main.entity.mob.Player;
import main.entity.mob.Zuko;
import main.entity.mob.Zuko2;
import main.entity.particle.Particle;
import main.entity.projectile.Projectile;
import main.util.Vector2i;

public class Level {
	
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	
	protected List<Entity> entities = new ArrayList<Entity>();
	protected List<Projectile> projectiles = new ArrayList<Projectile>();
	protected List<Particle> particles = new ArrayList<Particle>();
	protected List<Item> items = new ArrayList<Item>();
	
	protected Player player;
	
	protected GameOverWindow gameOver;
	protected boolean gameOverOn = false;
	
	public static Level spawn = new SpawnLevel("/levels/spawnLevel.png");
	public static Level zukoLevel = new ZukoLevel("/levels/zukoMap.png");
	public static Level laberinthLevel = new LaberinthLevel("/levels/laberinth.png");
	public static Level footMountain = new FootMountain("/levels/FootMountain.png");
	
	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		@Override
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost) return 1;
			if (n1.fCost > n0.fCost) return -1;
			return 0;
		}
	};
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[this.width * this.height];
		
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		gameOver = new GameOverWindow(Sprite.gameOverWindow);
		
		generateLevel();
	}

	public void generateLevel() {
	}
	
	protected void loadLevel(String path) {
	}
	
	public void update(Game game, Keyboard key) {
		gameOver(game);
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		for (int i = 0; i < items.size(); i++) {
			items.get(i).update();
		}
		
		remove();
	}
	
	private void gameOver(Game game) {
		if (getPlayer().getCurrentHP() <= 0) {
			gameOverOn = true;
			game.gameOver();
		}
	}
	
	public GameOverWindow getGameOverWindow() {
		return gameOver;
	}
	
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).isRemoved()) items.remove(i);
		}
	}
	
	protected void removeAll() {
		for (int i = 0; i < entities.size(); i++) {
			entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.remove(i);
		}
		for (int i = 0; i < items.size(); i++) {
			items.remove(i);
		}
	}
	
	protected void removeAllProjectiles() {
		for (Projectile pr: projectiles) {
			pr.remove();
		}
	}
	
	public void loadPlayer(Player player) {
		this.player = player;
	}
	
	
//	private void time() {
//	}
	
	//Size = el tamaño en pixeles real del proyectil (no su sprite 16)
	public boolean tileCollision(int x, int y, int xOffset, int yOffset, int size) {
		boolean solid = false;
		for (int corner = 0; corner < 4; corner++) {
			int xt = (x - corner % 2 * size + xOffset) >> 4;
			int yt = (y - corner % 2 * size + yOffset + 5) >> 4; 
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		//Limites de la pantalla en posicion 'casilla'
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		
		
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		for (int i = 0; i < items.size(); i++) {
			items.get(i).render(screen);
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		
		if (gameOverOn) {
			gameOver.render(screen);
		}
	}
	
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		}
		else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		}
		else if (e instanceof Player) {
			entities.add((Player) e);
		}
		else if (e instanceof Item) {
			items.add((Item) e);
		}
		else if (e instanceof Entity){
			int size = entities.size();
			if (size > 0 && e instanceof Mob) {
				entities.add(size - 1, e);
			} else {
				entities.add(e);
			}
		}
	}
	
	public Player getPlayer() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Player) {
				return (Player) entities.get(i);
			}
		}
		return null;
	}
	
	public Zuko getZuko() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Zuko) {
				return (Zuko) entities.get(i);
			}
		}
		return null;
	}
	
	public Zuko2 getZuko2() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Zuko2) {
				return (Zuko2) entities.get(i);
			}
		}
		return null;
	}
	
	public int getEntitiesSize() {
		return entities.size();
	}
	
	public int getItemsSize() {
		return items.size();
	}
	
	public List<Node> findPath(Vector2i start, Vector2i goal) {
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, getDistance(start, goal));
		openList.add(current);
		while (openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			if (current.tile.equals(goal)) {
				List<Node> path = new ArrayList<Node>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 4) continue;
				int x = current.tile.getX();
				int y = current.tile.getY();
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Tile at = getTile(x + xi, y + yi);
				if (at == null) continue;
				if (at.solid()) continue;
				Vector2i a = new Vector2i(x + xi, y + yi);
				double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 0.95);
				double hCost = getDistance(a, goal);
				Node node = new Node(a, current, gCost, hCost);
				if (vecInList(closedList, a) && gCost >= node.gCost) continue;
				if (!vecInList(openList, a) || gCost < current.gCost) openList.add(node);
			}
		}
		closedList.clear();
		return null;
	}
	
	private boolean vecInList (List<Node> list, Vector2i vector) {
		for (Node n : list) {
			if (n.tile.equals(vector)) return true;
		}
		return false;
	}
	
	private double getDistance(Vector2i tile, Vector2i goal) {
		double dx = tile.getX() - goal.getX();
		double dy = tile.getY() - goal.getY();
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int)e.getX();
		int ey = (int)e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int)entity.getX();
			int y = (int)entity.getY();
			
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt(dx * dx + dy * dy);
			if (distance <= radius) result.add(entity);
		}
		return result;
	}
	
	public List<Entity> getEntitiesList() {
		return entities;
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public List<Item> getItemsList() {
		return items;
	}
	
	public boolean getPlayerInRadius(Entity e, int radius) {
		int ex = (int)e.getX();
		int ey = (int)e.getY();
		int px = (int)player.getX();
		int py = (int)player.getY();
		int dx = Math.abs(px - ex);
		int dy = Math.abs(py - ey);
		double distance = Math.sqrt(dx * dx + dy * dy);
		if (distance <= radius) return true;
		else return false;
	}
	
	public double getPlayerDistance(Entity e) {
		int ex = (int)e.getX();
		int ey = (int)e.getY();
		int px = (int)player.getX();
		int py = (int)player.getY();
		int dx = Math.abs(px - ex);
		int dy = Math.abs(py - ey);
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}
	
	public List<Player> getPlayers(Entity e, int radius) {
		List<Entity> entities = getEntities(e, radius);
		List<Player> result = new ArrayList<Player>();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Player) {
				result.add((Player) entities.get(i));
			}
		}
		return result;
	}
	
	//GrassA = 0xFF267F00
	//StonesA = 0xFFB200FF
	//WallA = 0xFF808080
	//DustA = 0xFFFFD800
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.colSpawnGrassA) return Tile.spawnGrassA;
		if (tiles[x + y * width] == Tile.colSpawnStonesA) return Tile.spawnStonesA;
		if (tiles[x + y * width] == Tile.colSpawnWallA) return Tile.spawnWallA;
		if (tiles[x + y * width] == Tile.colSpawnWallB) return Tile.spawnWallB;
		if (tiles[x + y * width] == Tile.colSpawnDustA) return Tile.spawnDustA;
		if (tiles[x + y * width] == Tile.colRockGrassA) return Tile.spawnRockGrassA;
		if (tiles[x + y * width] == Tile.colRockGrassB) return Tile.spawnRockGrassB;
		if (tiles[x + y * width] == Tile.colRockSolidA) return Tile.spawnRockSolidA;
		if (tiles[x + y * width] == Tile.colRockRoadA) return Tile.spawnRockRoadA;
		if (tiles[x + y * width] == Tile.colLavaA) return Tile.lavaA;
		if (tiles[x + y * width] == Tile.colMountainTextureA) return Tile.mountainTextureA;
		if (tiles[x + y * width] == Tile.colDirtLava) return Tile.dirtLava;
		if (tiles[x + y * width] == Tile.colLavaMountainUp) return Tile.lavaMountainUp;
		if (tiles[x + y * width] == Tile.colDirtLavaMountainUp) return Tile.dirtLavaMountainUp;
		return Tile.voidTile;
	}

}
