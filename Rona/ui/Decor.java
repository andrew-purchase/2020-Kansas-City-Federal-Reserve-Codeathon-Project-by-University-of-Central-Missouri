package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Decor extends UIObject {

	private int speed;
	private String direction;

	public Decor(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage) {
		super(x, y, w, h, visible, bufferedImage);
		this.speed = 0;
		this.direction = "None";
	}
	
	public void update() {
		if(speed > 0 && direction == "Down") {
			y+=speed;
			speed--;
		}
		if(speed > 0 && direction == "Up") {
			y-=speed;
			speed--;
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(isVisible())
			g.drawImage(bufferedImage[0], (int) x, (int) y, w, h, null);
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

	public void setDirectionAndSpeed(String direction, int speed) {
		this.direction = direction;
		this.speed = speed;
	}
}
