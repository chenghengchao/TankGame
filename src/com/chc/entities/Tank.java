package com.chc.entities;
/*
 * tank class
 */
public class Tank {
	//the location of a tank (x,y)
	int x = 0;
	int y;
	//0 ¡ü 1 ¡ú£¬2¡ý3 ¡û
	int derect;
	//ËÙ¶È
	int speed = 1;
	//color
	int color;
	public boolean isLive=true;
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDerect() {
		return derect;
	}

	public void setDerect(int derect) {
		this.derect = derect;
	}
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

}
