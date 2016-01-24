package main.Graphics;


public class Sprite {
	
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private int width, height;
	
	private SpriteSheet sheet;
	
	//Main Title
	public static Sprite mainTitle = new Sprite(400, 225, 0, 0, SpriteSheet.mainTitleBackGround);
	//Menu Background
	public static Sprite menuBackground = new Sprite(65, 225, 0, 0, SpriteSheet.menuBackground);
	public static Sprite selectedOption = new Sprite(44, 16, 0, 0, SpriteSheet.selectedOption);
	public static Sprite gameOverWindow = new Sprite(200, 100, 0, 0, SpriteSheet.gameOverWindow);
	
	public static Sprite[] fourElements = SpriteSheet.getSprites(64, 64, 4, 1, SpriteSheet.fourElements);
	public static Sprite[] buttons = SpriteSheet.getSprites(128, 56, 2, 2, SpriteSheet.buttons);
	
	//Beetle
	public static Sprite[] beetle = SpriteSheet.getSprites(32, 32, 2, 4, SpriteSheet.beetle);
		//hp Mirar esto para cambiarlo a algo mas "pro"
	public static Sprite[] beetleHpBar = { new Sprite(32, 2, 0xFFFF0000), new Sprite(30, 2, 0xFFFF0000), new Sprite(28, 2, 0xFFFF0000),
										   new Sprite(26, 2, 0xFFFF0000), new Sprite(24, 2, 0xFFFF0000), new Sprite(22, 2, 0xFFFF0000),
										   new Sprite(20, 2, 0xFFFF0000), new Sprite(16, 2, 0xFFFF0000), new Sprite(16, 2, 0xFFFF0000),
										   new Sprite(14, 2, 0xFFFF0000), new Sprite(12, 2, 0xFFFF0000), new Sprite(10, 2, 0xFFFF0000),
										   new Sprite(9, 2, 0xFFFF0000), new Sprite(6, 2, 0xFFFF0000), new Sprite(3, 2, 0xFFFF0000)};
	
	//Emibluh
	public static Sprite[] emibluh = SpriteSheet.getSprites(32, 32, 4, 5, SpriteSheet.emibluh);
	
	//hp Bar universal
	public static Sprite[] hpBar = { new Sprite(28, 2, 0xFFFF0000), new Sprite(24, 2, 0xFFFF0000), new Sprite(20, 2, 0xFFFF0000),
									 new Sprite(16, 2, 0xFFFF0000), new Sprite(12, 2, 0xFFFF0000), new Sprite(8, 2, 0xFFFF0000),
									 new Sprite(4, 2, 0xFFFF0000)};
	
	//tiles
	public static Sprite grassA = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite grassB = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rockA = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite rockB = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite rockSolidA = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x7F006E);
	
	//Spawn Level
	public static Sprite spawnGrassA = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawnGrassB = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawnRockGrassA = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawnRockGrassB = new Sprite(16, 3, 0, SpriteSheet.spawn_level);
	public static Sprite RockSolidA = new Sprite(16, 4, 0, SpriteSheet.spawn_level);
	public static Sprite wallA = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite wallB = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
	public static Sprite dustA = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite dustB = new Sprite(16, 1, 2, SpriteSheet.spawn_level);
	public static Sprite stonesA = new Sprite(16, 0, 3, SpriteSheet.spawn_level);
	public static Sprite rockRoadA = new Sprite(16, 1, 3, SpriteSheet.spawn_level);
	
	//ZukoMap
	public static Sprite lavaA = new Sprite(16, 0, 3, SpriteSheet.zukoMap);
	public static Sprite mountainTextureA = new Sprite(16, 0, 0, SpriteSheet.zukoMap);
	public static Sprite dirtLava = new Sprite(16, 0, 2, SpriteSheet.zukoMap);
	public static Sprite lavaMountainUp = new Sprite(16, 2, 3, SpriteSheet.zukoMap);
	public static Sprite dirtLavaMountainUp = new Sprite(16, 2, 2, SpriteSheet.zukoMap);
	
	public static Sprite[] zukoStageCh = SpriteSheet.getSprites(96, 96, 4, 5, SpriteSheet.zukoStageCh);
	
	//Cinematics
	public static Sprite[] zukoCinematic = SpriteSheet.getSprites(32, 32, 3, 1, SpriteSheet.zukoCinematic);
	public static Sprite[] zukoLeaving = SpriteSheet.getSprites(32, 32, 4, 1, SpriteSheet.zukoLeaving);
	
	//Player 
	public static Sprite player_forward = new Sprite(32, 0, 3, SpriteSheet.playerSheet);
	public static Sprite player_back = new Sprite(32, 0, 0, SpriteSheet.playerSheet);
	public static Sprite player_left = new Sprite(32, 0, 1, SpriteSheet.playerSheet);
	public static Sprite player_right = new Sprite(32, 0, 2, SpriteSheet.playerSheet);
	
	public static Sprite player_forward1 = new Sprite(32, 1, 3, SpriteSheet.playerSheet);
	public static Sprite player_forward2 = new Sprite(32, 2, 3, SpriteSheet.playerSheet);
	
	public static Sprite player_back1 = new Sprite(32, 1, 0, SpriteSheet.playerSheet);
	public static Sprite player_back2 = new Sprite(32, 2, 0, SpriteSheet.playerSheet);
	
	public static Sprite player_right1 = new Sprite(32, 1, 2, SpriteSheet.playerSheet);
	public static Sprite player_right2 = new Sprite(32, 2, 2, SpriteSheet.playerSheet);
	
	public static Sprite player_left1 = new Sprite(32, 1, 1, SpriteSheet.playerSheet);
	public static Sprite player_left2 = new Sprite(32, 2, 1, SpriteSheet.playerSheet);
	
	//Zuko
	public static Sprite zuko_forward = new Sprite(32, 0, 3, SpriteSheet.zukoSheet);
	public static Sprite zuko_back = new Sprite(32, 0, 0, SpriteSheet.zukoSheet);
	public static Sprite zuko_left = new Sprite(32, 0, 1, SpriteSheet.zukoSheet);
	public static Sprite zuko_right = new Sprite(32, 0, 2, SpriteSheet.zukoSheet);
	
	public static Sprite zuko_forward1 = new Sprite(32, 1, 3, SpriteSheet.zukoSheet);
	public static Sprite zuko_forward2 = new Sprite(32, 2, 3, SpriteSheet.zukoSheet);
	
	public static Sprite zuko_back1 = new Sprite(32, 1, 0, SpriteSheet.zukoSheet);
	public static Sprite zuko_back2 = new Sprite(32, 2, 0, SpriteSheet.zukoSheet);
	
	public static Sprite zuko_right1 = new Sprite(32, 1, 2, SpriteSheet.zukoSheet);
	public static Sprite zuko_right2 = new Sprite(32, 2, 2, SpriteSheet.zukoSheet);
	
	public static Sprite zuko_left1 = new Sprite(32, 1, 1, SpriteSheet.zukoSheet);
	public static Sprite zuko_left2 = new Sprite(32, 2, 1, SpriteSheet.zukoSheet);
	
	public static Sprite zukoDefeated = new Sprite(32, 0, 4, SpriteSheet.zukoSheet);
	public static Sprite zukoDefeatedV2 = new Sprite(32, 1, 4, SpriteSheet.zukoSheet);
	public static Sprite zukoDefeatedV22 = new Sprite(32, 2, 4, SpriteSheet.zukoSheet);
	
	public static Sprite[] zuko2nd = SpriteSheet.getSprites(32, 32, 4, 5, SpriteSheet.zuko2nd);
	
	//Katara
	public static Sprite katara_forward = new Sprite(32, 0, 3, SpriteSheet.kataraSheet);
	public static Sprite katara_back = new Sprite(32, 0, 0, SpriteSheet.kataraSheet);
	public static Sprite katara_left = new Sprite(32, 0, 1, SpriteSheet.kataraSheet);
	public static Sprite katara_right = new Sprite(32, 0, 2, SpriteSheet.kataraSheet);
		
	public static Sprite katara_forward1 = new Sprite(32, 1, 3, SpriteSheet.kataraSheet);
	public static Sprite katara_forward2 = new Sprite(32, 2, 3, SpriteSheet.kataraSheet);
		
	public static Sprite katara_back1 = new Sprite(32, 1, 0, SpriteSheet.kataraSheet);
	public static Sprite katara_back2 = new Sprite(32, 2, 0, SpriteSheet.kataraSheet);
		
	public static Sprite katara_right1 = new Sprite(32, 1, 2, SpriteSheet.kataraSheet);
	public static Sprite katara_right2 = new Sprite(32, 2, 2, SpriteSheet.kataraSheet);
		
	public static Sprite katara_left1 = new Sprite(32, 1, 1, SpriteSheet.kataraSheet);
	public static Sprite katara_left2 = new Sprite(32, 2, 1, SpriteSheet.kataraSheet);
	
	//Toph
	public static Sprite[] toph = SpriteSheet.getSprites(32, 32, 3, 5, SpriteSheet.tophSheet);
	
	//Earth
	public static Sprite[] earthApp = SpriteSheet.getSprites(32, 32, 5, 1, SpriteSheet.earthApp);
	
	
	//Airbending sprites
	public static Sprite projectiles_air_atack1 = new Sprite(16, 0, 0, SpriteSheet.projectile_aang);
	public static Sprite projectiles_air_atack2 = new Sprite(16, 0, 1, SpriteSheet.projectile_aang);
	public static Sprite projectiles_air_atack3 = new Sprite(16, 0, 2, SpriteSheet.projectile_aang);
	
	//Earthbending sprite
	public static Sprite[] earthCrash = SpriteSheet.getSprites(32, 32, 5, 4, SpriteSheet.earthCrash);
	
	//Melee air
	public static Sprite[] airMeleeAtack = SpriteSheet.getSprites(16, 32, 4, 1, SpriteSheet.airMeleeAtack);
	public static Sprite[] airMeleeAtackUpDown = SpriteSheet.getSprites(32, 16, 1, 4, SpriteSheet.airMeleeAtackUpDown);
	
	//Beetle melee
	public static Sprite[] babas1 = SpriteSheet.getSprites(64, 32, 2, 4, SpriteSheet.beetleBabas1);
	public static Sprite[] babas2 = SpriteSheet.getSprites(32, 64, 4, 2, SpriteSheet.beetleBabas2);
	
	//Firebending sprite
	public static Sprite projectiles_fire_atack = new Sprite(16, 1, 0, SpriteSheet.projectile_aang);
		//HellFire
	public static Sprite projectiles_hell_fire1 = new Sprite(16, 3, 0, SpriteSheet.projectile_aang);
	public static Sprite projectiles_hell_fire2 = new Sprite(16, 3, 1, SpriteSheet.projectile_aang);
	public static Sprite projectiles_hell_fire3 = new Sprite(16, 3, 2, SpriteSheet.projectile_aang);
		//HellFireShot
	public static Sprite projectiles_hell_fire_shot1 = new Sprite(16, 2, 0, SpriteSheet.projectile_aang);
	public static Sprite projectiles_hell_fire_shot2 = new Sprite(16, 2, 1, SpriteSheet.projectile_aang);
	public static Sprite projectiles_hell_fire_shot3 = new Sprite(16, 2, 2, SpriteSheet.projectile_aang);
	
	//Particles
	public static Sprite particle_default = new Sprite(2, 0xC1CAFF);
	public static Sprite particle_fire = new Sprite(2, 0xFF3E00);
	public static Sprite particle_blood = new Sprite(2, 0xFF0000);
	public static Sprite particle_bluh = new Sprite(2, 0x00FF21);
	
	//Items
	public static Sprite[] airBuff = SpriteSheet.getSprites(32, 32, 3, 1, SpriteSheet.airBuff);
	
	//Avatar HP
	public static Sprite avatarHP[] = SpriteSheet.getSprites(80, 16, 1, 2, SpriteSheet.avatarHP);
	
	//Zuko hp
	public static Sprite[] zukoHP = SpriteSheet.getSprites(120, 20, 1, 6, SpriteSheet.zukoHP);
	public static Sprite[] zukoHP2 = SpriteSheet.getSprites(150, 20, 1, 5, SpriteSheet.zukoHP2);
	
	public static Sprite[] numbers = SpriteSheet.getSprites(8, 8, 10, 1, SpriteSheet.numbers);
	public static Sprite[] letters = SpriteSheet.getSprites(8, 8, 10, 3, SpriteSheet.letters);
	public static Sprite bar = new Sprite(8, 8, 0, 0, SpriteSheet.characters);
	
	//Fog of War Solo funcionara si no renderizo lo que cubro
	public static Sprite fogOfWar = new Sprite(400, 225, 0, 0, SpriteSheet.fogOfWar);
	
	//Inferno Projectile
	public static Sprite[] infernoPointer = SpriteSheet.getSprites(48, 48, 3, 1, SpriteSheet.infernoPointer);
	public static Sprite[] comet = SpriteSheet.getSprites(32, 32, 3, 1, SpriteSheet.comet);
	
	//Thanks
	public static Sprite thanks = new Sprite(400, 225, 0, 0, SpriteSheet.thanks);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		this.SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[this.width * this.height];
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
		load2();
	}
	
	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int size, int color) {
		this.SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}
	
	private void load2() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}
}
