package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class UIObject {

	protected boolean visible;
	protected float x, y;
	protected int w, h;
	protected BufferedImage[] bufferedImage;
	
	public UIObject(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.visible = visible;
		this.bufferedImage = bufferedImage;
	}

	public abstract void render(Graphics g);

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public BufferedImage[] getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage[] bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

} // end class UIObject
