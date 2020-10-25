package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import input.MouseManager;

public class Button extends UIObject {

	private boolean jumping;
	private int num, speed, special, speedX, speedY;
	private Rectangle bounds;
	private String direction;

	public Button(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage) {
		super(x, y, w, h, visible, bufferedImage);
		this.num = 0;
		this.speed = 0;
		this.special = -1;
		this.direction = "None";
		this.jumping = false;
		this.speedX = 0;
		this.speedY = 0;
		this.bounds = new Rectangle((int) x, (int) y, w, h);
	}

	public void update(MouseManager m) {
		if(isVisible()) {
			if(bounds.contains(m.getMouseX(), m.getMouseY()))
				setNum(1);
			else
				setNum(0);
			if(speed > 0 && direction == "Down") {
				y+=speed;
				bounds.y +=speed;
				speed--;
			}
			if(speed > 0 && direction == "Up") {
				y-=speed;
				bounds.y -=speed;
				speed--;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		if(isVisible()) {
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

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

} // end class Button