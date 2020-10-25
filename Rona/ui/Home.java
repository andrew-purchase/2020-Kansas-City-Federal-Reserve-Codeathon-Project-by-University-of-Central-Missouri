package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import input.MouseManager;

public class Home extends UIObject {

	private int num, speed,/* floatTime,*/ realX, realY, delayClick;
	private Rectangle bounds;
//	private String direction, floatDirection;

	public Home(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage) {
		super(x, y, w, h, visible, bufferedImage);
		this.num = 0;
		this.speed = 0;
//		this.floatTime = 0;
//		this.direction = "None";
//		this.floatDirection = "Up";
		this.realX = (int) getX();
		this.realY = (int) getY();
		this.delayClick = 0;
		this.bounds = new Rectangle((int) x, (int) y, w, h);
	}

	public void update(MouseManager m) {
		if(isVisible()) {
			if(getDelayClick() > 0)
				setDelayClick(getDelayClick() - 1);
//			if(speed > 0 && direction == "Down") {
//				y+=speed;
//				bounds.y +=speed;
//				speed--;
//			}
//			if(speed > 0 && direction == "Up") {
//				y-=speed;
//				bounds.y -=speed;
//				speed--;
//			}
//			// FLOAT
//			if(getFloatTime() == 0) {
//				setFloatDirection("Up");
//			}
//			if(getFloatTime() == 330) {
//				setFloatDirection("Down");
//			}
//			setFloatTime(getFloatTime() + 2);
//			if(getFloatDirection() == "Up" && getFloatTime() % 30 == 0) {
//				y -= 1;
//			}
//			if(getFloatDirection() == "Down" && getFloatTime() % 30 == 0) {
//				y += 1;
//			}
//			if(getFloatTime() == 660)
//				setFloatTime(0);
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

	public void teleport(int boundsX, int boundsY) {
		this.setX(boundsX);
		this.setY(boundsY);
		this.bounds.x = boundsX;
		this.bounds.y = boundsY;
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

//	public String getDirection() {
//		return direction;
//	}
//
//	public void setDirection(String direction) {
//		this.direction = direction;
//	}

//	public int getFloatTime() {
//		return floatTime;
//	}
//
//	public void setFloatTime(int floatTime) {
//		this.floatTime = floatTime;
//	}
//
//	public String getFloatDirection() {
//		return floatDirection;
//	}
//
//	public void setFloatDirection(String floatDirection) {
//		this.floatDirection = floatDirection;
//	}

	public int getRealX() {
		return realX;
	}

	public void setRealX(int realX) {
		this.realX = realX;
	}

	public int getRealY() {
		return realY;
	}

	public void setRealY(int realY) {
		this.realY = realY;
	}

	public int getDelayClick() {
		return delayClick;
	}

	public void setDelayClick(int delayClick) {
		this.delayClick = delayClick;
	}

}
