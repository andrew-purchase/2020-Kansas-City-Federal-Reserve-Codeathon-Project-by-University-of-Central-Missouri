package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DataShow extends UIObject {

	private int fpm, frame;

	public DataShow(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage) {
		super(x, y, w, h, visible, bufferedImage);
		this.fpm = -1;
		this.frame = 0;
	}
	
	public void update() {
		if(isVisible()) {
			if(fpm > -1)
				fpm++;
			if(fpm % 10 == 0)
				frame++;
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(isVisible())
			g.drawImage(bufferedImage[getFrame()], (int) x, (int) y, w, h, null);
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int speed) {
		this.frame = speed;
	}

	public int getFPM() {
		return fpm;
	}

	public void setFPM(int count) {
		this.fpm = count;
	}

} // end class Data
