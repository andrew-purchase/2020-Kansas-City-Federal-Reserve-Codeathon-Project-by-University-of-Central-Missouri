package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import input.MouseManager;

public class Rona extends UIObject {

	private boolean notSafe;
	private int num, animation, stage, target;
	private Rectangle bounds;
	
	public Random r;
	
	public Rona(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage) {
		super(x, y, w, h, visible, bufferedImage);
		this.num = 0;
		this.animation = 0;
		this.stage = 0;
		this.notSafe = true;
		this.setBounds(new Rectangle((int) x, (int) y, w, h));
		this.r = new Random();
	}
	
	public void update(MouseManager m) {
		if(isVisible()) {
			if(stage == 0) {	// Selecting
				setNum(0);
				setNotSafe(true);
			}
			if(stage == 1) {	// Select Target
				setTarget((r.nextInt(9) + 1) * 120);
				setStage(2);
			}
			if(stage == 2) {	// moving
				setNum(1);
				setX(getX() - 4);
				bounds.x -= 4;
				if((int)getTarget() == (int)getX()) {
					setStage(3);
				}
			}
			if(stage == 3) {	// Attacking Up
				setAnimation(getAnimation() + 1);
				setNum((int)(getAnimation() / 10) + 2);
				if(getNum() == 8) {
					setNum(7);
					setStage(4);
				}
			}
			if(stage == 4) {	// Attacking Down
				setAnimation(getAnimation() - 1);
				setNum((int)(getAnimation() / 10) + 2);
				if(getNum() == 1) {
					setStage(2);
				}
			}
			if(getX() <= -120) { // Reached off the screen
				setVisible(false);
				setStage(0);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(isVisible()) {
			g.drawImage(bufferedImage[getNum()], (int) x, (int) y, w, h, null); // reached 8
		}
	}
	
	public boolean isClicked(MouseManager m) {
		if(isVisible() && m.isLeftPressed() && bounds.contains(m.getMouseX(), m.getMouseY())) {
			m.setLeftPressed(false);
			return true;
		}
		return false;
	}

	public void prepareToInfect(int boundsX, int boundsY) {
		this.setNum(0);
		this.setVisible(true);
		this.setX(boundsX);
		this.setY(boundsY);
		this.bounds.x = boundsX;
		this.bounds.y = boundsY;
	}
	
	public void teleport(int boundsX, int boundsY) {
		this.setVisible(false);
		this.setX(boundsX);
		this.setY(boundsY);
		this.bounds.x = boundsX;
		this.bounds.y = boundsY;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public int getAnimation() {
		return animation;
	}

	public void setAnimation(int animation) {
		this.animation = animation;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public boolean isNotSafe() {
		return notSafe;
	}

	public void setNotSafe(boolean notSafe) {
		this.notSafe = notSafe;
	}

}
