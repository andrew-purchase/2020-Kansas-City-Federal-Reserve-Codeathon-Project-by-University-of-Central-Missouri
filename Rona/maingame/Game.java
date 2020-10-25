package maingame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import graphics.Assets;
import input.MouseManager;
import screen.ScreenWindow;
import sharing.Share;
import states.GameState;
import states.SetupState;
import states.State;

public class Game implements Runnable {

	private boolean gameIsRunning = false;
	private BufferStrategy bs;
	private Graphics g;
	private int w, h;
	private ScreenWindow screenWindow;
	private Thread thread;
	
	// Fade Transitions
	private int fadeOut;
	private int fadeOutSpeed;
	private boolean fadeIn;
	
	// Share
	public Share share;
	
	// States
	public static State[] state;
	
	// Input
	private MouseManager m;

	public Game(int w, int h) {
		this.w = w;
		this.h = h;
		m = new MouseManager();
		
		// Share Class
		share = new Share();
		fadeOut = 0;
		fadeOutSpeed = 80;
		fadeIn = false;
	}
	
	private void init() {
		screenWindow = new ScreenWindow(w, h);
		screenWindow.getFrame().addMouseListener(m);
		screenWindow.getFrame().addMouseMotionListener(m);
		screenWindow.getCanvas().addMouseListener(m);
		screenWindow.getCanvas().addMouseMotionListener(m);
		Assets.getAllImages();
		state = new State[2];
		state[0] = new SetupState(m, state);
		state[1] = new GameState(m, state);
		State.setCurrentStateAt(state, 0);
	}

	public void run() {
		init();
		double fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while(gameIsRunning) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			if(delta >= 1) {
				update();
				render();
				delta--;
			}
		}
		stop();
	}
	
	public synchronized void start() {
		if(gameIsRunning)
			return;
		gameIsRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!gameIsRunning)
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void update() {
		if(State.getCurrentState() != null) {
			State.getCurrentState().update();
		}
	}
	
	private void render() {
		bs = screenWindow.getCanvas().getBufferStrategy();
		if(bs == null) {
			screenWindow.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		// Clear Screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		
		// Draw EVERYTHING
		if(State.getCurrentState() != null) {
			State.getCurrentState().render(g);
			if(share.getTransition() != -1) {
				if(getFadeOut() < w) {
					fadeOut += fadeOutSpeed;
					setFadeOutSpeed(getFadeOutSpeed() - 2);
					g.fillRect(0, 0, getFadeOut(), h);
				}
				if(getFadeOut() >= w) {
					State.setCurrentStateAt(state, share.getTransition());
					Share.setTransition(-1);
					setFadeOut(w);
					setFadeOutSpeed(100);
					setFadeIn(true);
				}
			}
			if(isFadeIn()) {
				if(getFadeOut() > 0) {
					fadeOut -= fadeOutSpeed;
					setFadeOutSpeed(getFadeOutSpeed() - 2);
					g.fillRect(0, 0, getFadeOut(), h);
				}
				if(getFadeOut() <= 0) {
					setFadeOut(0);
					setFadeOutSpeed(100);
					setFadeIn(false);
				}
			}
		}
		bs.show();
		g.dispose();
	}

	public int getFadeOut() {
		return fadeOut;
	}

	public void setFadeOut(int fadeOut) {
		this.fadeOut = fadeOut;
	}

	public int getFadeOutSpeed() {
		return fadeOutSpeed;
	}

	public void setFadeOutSpeed(int fadeOutSpeed) {
		this.fadeOutSpeed = fadeOutSpeed;
	}

	public boolean isFadeIn() {
		return fadeIn;
	}

	public void setFadeIn(boolean fadeIn) {
		this.fadeIn = fadeIn;
	}
			
} // end Game()
