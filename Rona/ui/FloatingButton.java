package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import input.MouseManager;

public class FloatingButton extends UIObject {

	private boolean jumping;
	private int num, speed, special, floatingX, floatingY;
	private Rectangle bounds;
	private String direction;

	public FloatingButton(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage) {
		super(x, y, w, h, visible, bufferedImage);
		this.num = 0;
		this.speed = 0;
		this.special = -1;
		this.direction = "None";
		this.jumping = false;
		this.floatingX = 0;
		this.floatingY = 0;
		this.bounds = new Rectangle((int) x, (int) y, w, h);
	}

	public void update(MouseManager m) {
		if(isVisible()) {
			if(bounds.contains(m.getMouseX(), m.getMouseY()))
				setNum(1);
			else
				setNum(0);
			if(speed > 0) {
				if(direction == "Down") {
					y+=speed;
					bounds.y +=speed;
				}
				if(direction == "Up") {
					y-=speed;
					bounds.y -=speed;
				}
				if(direction == "Right") {
					x+=speed;
					bounds.x +=speed;
				}
				if(direction == "Left") {
					x-=speed;
					bounds.x -=speed;
				}
				speed--;
			}

		}
	}
	
	@Override
	public void render(Graphics g) {
		if(isVisible()) {
			g.drawImage(bufferedImage[getNum()], (int) x + floatingX, (int) y + floatingY, (int)w , (int)h , null);
		}
	}
	
	public boolean isClicked(MouseManager m) {
		if(isVisible() && m.isLeftPressed() && bounds.contains(m.getMouseX(), m.getMouseY())) {
			m.setLeftPressed(false);
			return true;
		}
		return false;
	}
	
	public void teleport(int mx, int my) {
		this.setX(mx);
		this.setY(my);
		this.bounds.x = mx;
		this.bounds.y = my;
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
	
	public void setDirectionAndSpeed(String direction, int speed) {
		this.direction = direction;
		this.speed = speed;
	}

	public int getSpecial() {
		return special;
	}

	public void setSpecial(int special) {
		this.special = special;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public int getFloatingX() {
		return floatingX;
	}

	public void setFloatingX(int floatingX) {
		this.floatingX = floatingX;
	}

	public int getFloatingY() {
		return floatingY;
	}

	public void setFloatingY(int floatingY) {
		this.floatingY = floatingY;
	}

} // end class Button