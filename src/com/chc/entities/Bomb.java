package com.chc.entities;

public class Bomb {
	//���겻�ı䣬û��Ҫ�����߳���
	//�仯�ģ����Կ��������߳�
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
	public int life = 9;//ը��������
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
