package com.chc.entities;

public class Shot implements Runnable {
	int x;
	int y;
	int speed=1;
	int direct;
	public boolean isLive = true;
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Shot(int x,int y,int direct){
		this.x=x;
		this.y=y;
		this.direct=direct;
	}

	@Override
	public void run() {

		//一旦创建，就马上运动
		while(true){
			//考虑到内存问题，休息一下
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(direct){
				case 0:
					y-=speed;
					break;
				case 1:
					x+=speed;
					break;
				case 2:
					y+=speed;
					break;
				case 3:
					x-=speed;
					break;
		
			}
		//	System.out.println("x="+x+"y="+y);
			//何时死亡？？ 碰到边界或者碰到敌人，死亡→属性是否活着
		
			//判断该子弹是否碰到边界
			if(x<0||x>400||y<0||y>300){
				this.isLive=false;
				break;
			}
		}
		
		
	}

}
