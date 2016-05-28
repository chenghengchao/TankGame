package com.chc.entities;

import java.util.Vector;

public class Hero extends Tank {
	
	

	public Vector<Shot> ss = new Vector<Shot>();
	public Shot s = null;//一颗子弹
	public Hero(int x, int y) {
		super(x, y);
		
		
	}
	
	//初始化子弹，子弹的位置和坦克相关,方向也需要考虑
	public void shotEnemy(){
		

		switch(this.derect){
		case 0:
			s= new Shot(x+10,y,0);
			ss.add(s);
			break;
		case 1:
			s = new Shot(x+30,y+10,1);
			ss.add(s);
			break;
		case 2:
			s = new Shot(x+10,y+30,2);
			ss.add(s);
			break;
		case 3:
			s = new Shot(x,y+10,3);
			ss.add(s);
			break;
		}
		//创建子弹，启动线程？！
		Thread t = new Thread(s);
		t.start();
	}
	//tank 向上移动
	public void moveUp(){
		y-=speed;
	}
	//tank 向右移动
	public void moveRight(){
		x+=speed;
	}
	//tank 向下移动
	public void moveDown(){
		y+=speed;
	}
	//tank 向左移动
	public void moveLeft(){
		x-=speed;
	}

}
