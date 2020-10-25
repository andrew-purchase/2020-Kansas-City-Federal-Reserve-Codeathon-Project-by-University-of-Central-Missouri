package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import input.MouseManager;

public class Roulette extends UIObject {

	private boolean showSharks, hover;
	private int num, speed, spinning, swim, shark;
	private Rectangle bounds;
	private String direction;
	
	public Random r;

	public Roulette(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage) {
		super(x, y, w, h, visible, bufferedImage);
		this.num = 0;
		this.speed = 0;
		this.direction = "None";
		this.bounds = new Rectangle((int) x, (int) y, w, h);
		this.r = new Random();
	}

	public void update(MouseManager m) {
		if(isVisible()) {
			setHover(false);
			if(bounds.contains(m.getMouseX(), m.getMouseY()))
				setHover(true);
			if(getNum() > 0 && getNum() < 13 && getSpinning() > 0) {
				setNum(r.nextInt(12) + 1);
				setSpinning(getSpinning() - 1);
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(isVisible()) {
			if(!isHover() && getNum() == 0)
				g.drawImage(bufferedImage[0], (int) x, (int) y, (int)w , (int)h , null);
			if(isHover() && getNum() == 0)
				g.drawImage(bufferedImage[13], (int) x, (int) y, (int)w , (int)h , null);
			if(getNum() > 0)
				g.drawImage(bufferedImage[getNum()], (int) x, (int) y, (int)w , (int)h , null);
		}
	}
	
	public boolean isClicked(MouseManager m) {
		if(isVisible() && m.isLeftPressed() && bounds.contains(m.getMouseX(), m.getMouseY())) {
			m.setLeftPressed(false);
			return true;
		}
		return false;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public int getSpinning() {
		return spinning;
	}

	public void setSpinning(int spinning) {
		this.spinning = spinning;
	}

	public int getWalk() {
		return swim;
	}

	public void setWalk(int swim) {
		this.swim = swim;
	}

	public int getRona() {
		return shark;
	}

	public void setRona(int shark) {
		this.shark = shark;
	}

	public boolean isShowRonas() {
		return showSharks;
	}

	public void setShowRona(boolean showSharks) {
		this.showSharks = showSharks;
	}

	public boolean isHover() {
		return hover;
	}

	public void setHover(boolean hover) {
		this.hover = hover;
	}

} // end class Button