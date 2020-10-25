package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Turns extends UIObject {
	
	private int speed, frame, player;
	private String direction, glow;
	
	public Random r;
	
	public Turns(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage) {
		super(x, y, w, h, visible, bufferedImage);
		this.speed = 0;
		this.frame = 0;
		this.player = 0;
		this.direction = "None";
		this.glow = "up";
		this.r = new Random();
	}

	public void update() {
		if(isVisible()) {
			if(getGlow() == "Up")
				setFrame(getFrame() + 1);
			if(getGlow() == "Down")
				setFrame(getFrame() - 1);
			if(frame == 0)
				setGlow("Up");
			if(frame == 50)
				setGlow("Down");
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(isVisible())
			g.drawImage(bufferedImage[frame/10], (int) x + ((getPlayer() - 1) * 130), (int) y, w, h, null);
	}
	
	public void FirstPlayerToStart(int totalPlayers) {
		setPlayer(r.nextInt(totalPlayers) + 1);
	}
	
	public void NextPlayer(int totalPlayers) {
		setPlayer(getPlayer() + 1);
		if(getPlayer() > totalPlayers)
			setPlayer(1);
	}
	
	public void PreviousPlayer(int totalPlayers) {
		setPlayer(getPlayer() - 1);
		if(getPlayer() < 1)
			setPlayer(totalPlayers);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public String getGlow() {
		return glow;
	}

	public void setGlow(String glow) {
		this.glow = glow;
	}

}
