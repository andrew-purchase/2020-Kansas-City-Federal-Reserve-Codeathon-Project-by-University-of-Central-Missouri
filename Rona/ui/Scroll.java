package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Scroll extends UIObject {

	private int speed;
	private String direction;
	
	public Scroll(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage, String direction, int speed) {
		super(x, y, w, h, visible, bufferedImage);
		this.direction = direction;
		this.speed = speed;
	}
	public void update() {
		if(isVisible()) {
			if(direction == "Up") {
				y-=speed;
				if(y <= -h)
					y += h;
			}
			if(direction == "Left") {
				x-=speed;
				if(x <= -w)
					x += w;
			}
			if(direction == "Right") {
				x+=speed;
				if(x > w)
					x -= w;
			}
			if(direction == "Down") {
				y+=speed;
				if(y > h)
					y -= h;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(isVisible()) {
			if(direction == "Up") {
				g.drawImage(bufferedImage[0], (int) x, (int) y + getH(), getW(), getH(), null);
				g.drawImage(bufferedImage[0], (int) x, (int) y, getW(), getH(), null);
			}
			if(direction == "Left") {
				g.drawImage(bufferedImage[0], (int) x + getW(), (int) y, getW(), getH(), null);
				g.drawImage(bufferedImage[0], (int) x, (int) y, getW(), getH(), null);
			}
			if(direction == "Right") {
				g.drawImage(bufferedImage[0], (int) x - getW(), (int) y, getW(), getH(), null);
				g.drawImage(bufferedImage[0], (int) x, (int) y, getW(), getH(), null);
			}
			if(direction == "Down") {
				g.drawImage(bufferedImage[0], (int) x, (int) y - getH(), getW(), getH(), null);
				g.drawImage(bufferedImage[0], (int) x, (int) y, getW(), getH(), null);
			}
		}
	}
	
}
