package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import main.Graphics.MainTitle;
import main.Graphics.Menu;
import main.Graphics.Screen;
import main.Graphics.Sprite;
import main.Input.Keyboard;
import main.Input.Mouse;
import main.Level.FootMountain;
import main.Level.Level;
import main.Level.TileCoordinate;
import main.Level.ZukoLevel;
import main.entity.mob.Player;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static int width = 400;
	private static int height = width / 16 * 9; // 168.75 // 225 con 400 de width
	private static int scale = 3;
	
	public static String title = "FourElements";

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private MainTitle mainTitle;
	private Menu menu;
	private boolean escPressed = false, pause = false, gameOver = false;
	private int contEscape = 0;
	private Player player;
	private boolean running = false;
	private boolean screenRendering = true;

	private Screen screen;
	
	
	private boolean thanks = false;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		mainTitle = new MainTitle(Sprite.mainTitle, key);
		menu = new Menu(Sprite.menuBackground, Sprite.fourElements[2], key);
//		initGame();
		addKeyListener(key);
		
		Mouse mouse = new Mouse(this);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public void initGame() {
		Level.footMountain = new FootMountain("/levels/FootMountain.png");
		Level.zukoLevel = new ZukoLevel("/levels/zukoMap.png");
		level = Level.footMountain;
//		level = Level.zukoLevel;
//		level = Level.laberinthLevel;
//		TileCoordinate playerSpawn = new TileCoordinate(14, 36); //zukoLevel
//		TileCoordinate playerSpawn = new TileCoordinate(25, 40); 
		TileCoordinate playerSpawn = new TileCoordinate(29, 31); //FootMountain
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
		level.add(player);
		level.loadPlayer(player);
	}
	
	public void initMainTitle() {
		mainTitle = new MainTitle(Sprite.mainTitle, key);
		level = null;
	}
	
	public void closeGame() {
		System.exit(0);
	}
	
	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}
	
	public static int getHeightWOScale() {
		return height;
	}
	
	public static int getWidthWOScale() {
		return width;
	}
	
	public MainTitle getMainTitle() {
		return mainTitle;
	}
	
	public void setMainTitle(MainTitle mainTitle) {
		this.mainTitle = mainTitle;
	}
	
	public void setMainTitleNull() {
		this.mainTitle = null;
	}
	
	public void setScreenRendering(boolean rendering) {
		screenRendering = rendering;
	}
	
	public boolean getScreenRendering() {
		return screenRendering;
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public void changeLevel(Level level, TileCoordinate coor) {
		this.level = level;
		this.level.add(player);
		this.level.loadPlayer(player);
		player.setPosition(coor.getX(), coor.getY());
	}
	
	public void menu() {
		if (key.esc && !escPressed && contEscape > 10) {
			pause = true;
			escPressed = true;
			contEscape = 0;
		} else if (key.esc && escPressed && contEscape > 10) {
			gameResume();
			contEscape = 0;
		}
		contEscape++;
	}
	
	public void gameResume() {
		pause = false;
		escPressed = false;
	}
	
	public void gameStartOver() {
		gameOver = false;
	}
	
	public void gameOver() {
		gameOver = true;
	}
	
	int cont = 0;
	
	public void update() {
		key.update();
		menu();
		if (pause) menu.update(this);
		if (gameOver) level.getGameOverWindow().update(this, key);
		if (!pause && !gameOver) {
			if (level != null) {
				level.update(this, key);
			}
			if (mainTitle != null) {
				mainTitle.update(this);
			}
		}
		
		if (thanks) {
			level = null;
			cont++;
		}
	}
	
	public void cleanScreen() {
		screen.clear();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
	}
	
	public void setThanks(boolean thanks) {
		this.thanks = thanks;
	}

	public void render() {
		// Coge el bufferStrategy del complemente (Canvas)
		BufferStrategy bs = getBufferStrategy();
		// En el caso de que bs sea null crea uno con 3 posibles frames para
		// formarse antes
		// de enseñarse en pantalla (precargados)
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		if (screenRendering) {
			screen.clear();
		}
		if (level != null && player != null) {
			double xScroll = player.getX() - screen.width / 2;
			double yScroll = player.getY() - screen.height / 2;
			level.render((int) xScroll, (int) yScroll, screen);
		} else if (mainTitle != null) {
			mainTitle.render(screen);
		}
		
		if (pause) {
			menu.render(screen);
		}
		
		if (thanks && cont > 120) {
			screen.renderSprite(0, 0, Sprite.thanks, false);
		}
		
		if (screenRendering) {
			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = screen.pixels[i];
			}
		}
		
		// Obtiene un link entre bufferStrategy y graphics para asi poder
		// dibujar
		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
//		g.setColor(Color.WHITE);
//		g.fillRect((int)level.getZuko().getX(), (int)level.getZuko().getY(), 20, 20);
//		g.fillRect((int)level.getPlayer().getX(), (int)level.getPlayer().getY(), 20, 20);
		//g.fillOval(Mouse.getX() - 16, Mouse.getY() - 16, 32, 32);

		// Remueve el frame de la pantalla
		g.dispose();
		// Hace que el siguiente buffer sea disponible
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("FourElements");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}
