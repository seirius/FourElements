package main.util;

public class CopyScreen extends Thread {
	
	private int[] pixelsGame, pixelsScreen;
	private int start, end;
	public boolean done = false;

	public CopyScreen(int start, int end, int[] pixelsGame, int[] pixelsScreen) {
		this.start = start;
		this.end = end;
		this.pixelsGame = pixelsGame;
		this.pixelsScreen = pixelsScreen;
	}
	
	public void run() {
		while (true) {
			if (!done) {
				for (int i = start; i < end; i++) {
					pixelsGame[i] = pixelsScreen[i];
				}
				done = true;
			}
		}
	}
}
