package com.chc.entities;

import java.util.Vector;

//坐标变化，做成线程
public class Enemy extends Tank implements Runnable{
	
	int times =0;
	//定义一个向量，可以访问到myPanel上的所有敌人坦克
	Vector<Enemy> ets = new Vector<Enemy>() ;
	//定义存放子弹向量
	public Vector<Shot> ss = new Vector<Shot>();
	//敌人发射子弹是自己完成的
	//敌人添加子弹，应该在刚创建坦克和敌人的坦克子弹死亡之后
	
	public Enemy(int x,int y){
		super(x,y);
	}
	
	//得到MyPanel上敌人的坦克向量
	//敌人的坦克一旦被创建，就要调用这个函数——>找敌人坦克创建的函数
	public void setEts(Vector<Enemy> vv){
		this.ets=vv;
	}
	
	@Override
	public void run() {

		while(true){
			
			switch(this.derect){
			case 0:
				//说明坦克正在向上移动
				for(int i=0;i<30;i++){
					if(y>0 && !this.isTouchOtherEnemy()){
						y-=speed;
					}
					try {//否则会很快消失，幽灵
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				break;
			case 1:
				//→
				for(int i=0;i<30;i++){
					if(x<400 && !this.isTouchOtherEnemy()){
						x+=speed;
					}
					try {//否则会很快消失，幽灵
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 2:
				for(int i=0;i<30;i++){
					if(y<300 && !this.isTouchOtherEnemy()){
						y+=speed;
					}
					try {//否则会很快消失，幽灵
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				break;
			case 3:
				for(int i=0;i<30;i++){
					if(x>0 && !this.isTouchOtherEnemy()){
						x-=speed;
					}
					try {//否则会很快消失，幽灵
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
			this.times++;
			if(times%2==0){
				if(isLive){
					if(ss.size()<5){
						Shot s =null;
						switch(this.derect){
						case 0:
							s=new Shot(x+10,y,0);
							ss.add(s);
							break;
						case 1:
							s=new Shot(x+30,y+10,1);
							ss.add(s);
							break;
						case 2:
							s=new Shot(x+10,y+30,2);
							ss.add(s);
							break;
						case 3:
							s=new Shot(x,y+10,3);
							ss.add(s);
							break;
						}
						Thread t = new Thread(s);
						t.start();
					}
				}
			}
			
			
			//方向要随机的变化，随机数
			this.derect=(int)(Math.random()*4);
		
			//判断敌人坦克是否死亡,让线程结束
			if(!this.isLive){
				break;//让坦克死亡后退出线程
			}

		}
		
		
	}
	//判断是否碰到了别的敌人坦克,需要访问到外部世界，所以定义一个新的成员变量vector
	public boolean isTouchOtherEnemy(){
		boolean b = false;
		switch (this.derect) {
		case 0:
			//本坦克向上，取出所有别的坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				Enemy et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.derect==0||et.derect==2)
					{
						//左点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
					}
					if(et.derect==3||et.derect==1)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 1:
			//坦克向右
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				Enemy et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.derect==0||et.derect==2)
					{
						//上点
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//下点 
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.derect==3||et.derect==1)
					{
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 2:
			//坦克向下
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				Enemy et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.derect==0||et.derect==2)
					{
						//我的左点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						//我的右点
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
					}
					if(et.derect==3||et.derect==1)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
						
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 3:
			//向左
			//取出所有的敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出第一个坦克
				Enemy et=ets.get(i);
				//如果不是自己
				if(et!=this)
				{
					//如果敌人的方向是向下或者向上
					if(et.derect==0||et.derect==2)
					{
						//我的上一点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//下一点
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.derect==3||et.derect==1)
					{
						//上一点
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			
			break;
		}
		return b;
	}
	

}
