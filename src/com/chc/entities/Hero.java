package com.chc.entities;

import java.util.Vector;

public class Hero extends Tank {
	
	

	public Vector<Shot> ss = new Vector<Shot>();
	public Shot s = null;//һ���ӵ�
	public Hero(int x, int y) {
		super(x, y);
		
		
	}
	
	//��ʼ���ӵ����ӵ���λ�ú�̹�����,����Ҳ��Ҫ����
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
		//�����ӵ��������̣߳���
		Thread t = new Thread(s);
		t.start();
	}
	//tank �����ƶ�
	public void moveUp(){
		y-=speed;
	}
	//tank �����ƶ�
	public void moveRight(){
		x+=speed;
	}
	//tank �����ƶ�
	public void moveDown(){
		y+=speed;
	}
	//tank �����ƶ�
	public void moveLeft(){
		x-=speed;
	}

}
