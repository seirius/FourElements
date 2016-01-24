package main.Level.tile;

import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Level.tile.spawnLevel.RockSolidA;
import main.Level.tile.spawnLevel.SpawnDustA;
import main.Level.tile.spawnLevel.SpawnDustB;
import main.Level.tile.spawnLevel.SpawnGrassTileA;
import main.Level.tile.spawnLevel.SpawnGrassTileB;
import main.Level.tile.spawnLevel.SpawnRockGrassTileA;
import main.Level.tile.spawnLevel.SpawnRockGrassTileB;
import main.Level.tile.spawnLevel.SpawnRockRoad;
import main.Level.tile.spawnLevel.SpawnStonesA;
import main.Level.tile.spawnLevel.SpawnWallA;
import main.Level.tile.spawnLevel.SpawnWallB;
import main.Level.tile.zukoMap.DirtLava;
import main.Level.tile.zukoMap.DirtLavaMountainUp;
import main.Level.tile.zukoMap.LavaA;
import main.Level.tile.zukoMap.LavaMountainUp;
import main.Level.tile.zukoMap.MountainTextureA;

public class Tile {
	
	public Sprite sprite;
	
	public static Tile grassA = new GrassTileA(Sprite.grassA);
	public static Tile grassB = new GrassTileB(Sprite.grassB);
	public static Tile rockA = new RocksA(Sprite.rockA);
	public static Tile rockB = new RocksB(Sprite.rockB);
	public static Tile rockSolidA = new RockSolidA(Sprite.rockSolidA);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	//Spawn level
	public static Tile spawnGrassA = new SpawnGrassTileA(Sprite.spawnGrassA);
	public static Tile spawnGrassB = new SpawnGrassTileB(Sprite.spawnGrassB);
	public static Tile spawnRockGrassA = new SpawnRockGrassTileA(Sprite.spawnRockGrassA);
	public static Tile spawnRockGrassB = new SpawnRockGrassTileB(Sprite.spawnRockGrassB);
	public static Tile spawnRockSolidA = new RockSolidA(Sprite.RockSolidA);
	public static Tile spawnWallA = new SpawnWallA(Sprite.wallA);
	public static Tile spawnWallB = new SpawnWallB(Sprite.wallB);
	public static Tile spawnDustA = new SpawnDustA(Sprite.dustA);
	public static Tile spawnDustB = new SpawnDustB(Sprite.dustB);
	public static Tile spawnStonesA = new SpawnStonesA(Sprite.stonesA);
	public static Tile spawnRockRoadA = new SpawnRockRoad(Sprite.rockRoadA);
	
	//ZukoMap
	public static Tile lavaA = new LavaA(Sprite.lavaA);
	public static Tile mountainTextureA = new MountainTextureA(Sprite.mountainTextureA);
	public static Tile dirtLava = new DirtLava(Sprite.dirtLava);
	public static Tile lavaMountainUp = new LavaMountainUp(Sprite.lavaMountainUp);
	public static Tile dirtLavaMountainUp = new DirtLavaMountainUp(Sprite.dirtLavaMountainUp);
	
	public static final int colSpawnGrassA = 0xFF267F00;
	public static final int colSpawnStonesA = 0xFFB200FF;
	public static final int colSpawnWallA = 0xFF808080;
	public static final int colSpawnWallB = 0xFF7F3300;
	public static final int colSpawnDustA = 0xFFFFD800;
	public static final int colRockGrassA = 0xFF567C54;
	public static final int colRockGrassB = 0xFF3B5439;
	public static final int colRockSolidA = 0xFF8CAD87;
	public static final int colRockRoadA = 0xFF636363;
	public static final int colLavaA = 0xFFFF2400;
	public static final int colMountainTextureA = 0xFF777777;
	public static final int colDirtLava = 0xFF3D1800;
	public static final int colLavaMountainUp = 0xFF883030;
	public static final int colDirtLavaMountainUp = 0xFF3A302A;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
	}
	
	public boolean solid() {
		return false;
	}
}
