package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Score extends UIObject {
	
	private int speed, value;
	private String direction;
	
	public Score(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage, int value) {
		super(x, y, w, h, visible, bufferedImage);
		this.value = value;
	}

	public void update(int newestScore) {
		setValue(newestScore);
		if(isVisible()) {
			if(speed > 0 && direction == "Down") {
				y+=speed;
				speed--;
			}
			if(speed > 0 && direction == "Up") {
				y-=speed;
				speed--;
			}
		}
	} // end update

	@Override
	public void render(Graphics g) {
		if(isVisible()) {
			if(getValue() >= 100)
				g.drawImage(bufferedImage[(int)((getValue() / 100) % 10)], (int) x, (int) y, w, h, null);
			if(getValue() >= 10)
				g.drawImage(bufferedImage[(int)((getValue() / 10) % 10)], (int) x + (int) w, (int) y, w, h, null);
			if(getValue() >= 0)
				g.drawImage(bufferedImage[(int)((getValue() / 1) % 10)], (int) x + ((int) w * 2), (int) y, w, h, null);
		}
	} // end render()

	public BufferedImage[] getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage[] bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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

} // end class Score
