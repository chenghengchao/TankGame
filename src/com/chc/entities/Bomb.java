package com.chc.entities;

public class Bomb {
	//坐标不改变，没必要做成线程类
	//变化的，可以考虑做成线程
	int x;
	int y;
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
	public int life = 9;//炸弹的生命
	boolean isLive = true;
	public Bomb(int x,int y){
		this.x=x;
		this.y=y;
		
	}
	public void lifeDown(){
		if(life>0){
			life--;
		}else{
			this.isLive=false;
		}
	}

}
