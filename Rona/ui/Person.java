package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import input.MouseManager;

public class Person extends UIObject {

	private boolean selectable, jumping, finishline, inShoreline;
	private BufferedImage[] bufferedImage2;
	private int position, speedX, speedY, owner, delayClick;
	private Rectangle bounds;
	private String direction;

	public Person(float x, float y, int w, int h, boolean visible, BufferedImage[] bufferedImage, BufferedImage[] bufferedImage2, int position, int owner) {
		super(x, y, w, h, visible, bufferedImage);
		this.speedX = 0;
		this.speedY = 0;
		this.direction = "None";
		this.position = position;
		this.owner = owner;
		this.selectable = false;
		this.jumping = false;
		this.finishline = false;
		this.inShoreline = false;
		this.bufferedImage2 = bufferedImage2;
		this.delayClick = 0;
		this.bounds = new Rectangle((int) x, (int) y, w, h);
	}

	public void update(MouseManager m) {
		if(getDelayClick() > 0)
			setDelayClick(getDelayClick() - 1);
		if(getSpeedX() == 0 && getSpeedY() == 0)
			setJumping(false);
		if(getSpeedX() > 0) {
			setX(getX() + getSpeedX());
			this.bounds.x += getSpeedX();
			setSpeedX(getSpeedX() - 1);
		}
		if(getSpeedX() < 0) {
			setX(getX() + getSpeedX());
			this.bounds.x += getSpeedX();
			setSpeedX(getSpeedX() + 1);
		}
		if(getSpeedY() > 0) {
			setY(getY() + getSpeedY());
			this.bounds.y += getSpeedY();
			setSpeedY(getSpeedY() - 1);
		}
		if(getSpeedY() < 0) {
			setY(getY() + getSpeedY());
			this.bounds.y += getSpeedY();
			setSpeedY(getSpeedY() + 1);
		}
		if(getX() >= 1200)
			setFinishline(true);
	}
	
	@Override
	public void render(Graphics g) {
		if(isVisible()) {
			g.drawImage(bufferedImage[getPosition()], (int) x, (int) y, w, h, null);
			if(isSelectable())
				g.drawImage(bufferedImage2[0], (int) x, (int) y, 120, 60, null);
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
	
	public void teleport(int boundsX, int boundsY) {
		this.setX(boundsX);
		this.setY(boundsY);
		this.bounds.x = boundsX;
		this.bounds.y = boundsY;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isFinishline() {
		return finishline;
	}

	public void setFinishline(boolean finishline) {
		this.finishline = finishline;
	}

	public boolean isInStore() {
		return inShoreline;
	}

	public void setInStore(boolean inShoreline) {
		this.inShoreline = inShoreline;
	}

	public int getDelayClick() {
		return delayClick;
	}

	public void setDelayClick(int delayClick) {
		this.delayClick = delayClick;
	}

}
