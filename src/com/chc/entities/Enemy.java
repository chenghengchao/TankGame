package com.chc.entities;

import java.util.Vector;

//����仯�������߳�
public class Enemy extends Tank implements Runnable{
	
	int times =0;
	//����һ�����������Է��ʵ�myPanel�ϵ����е���̹��
	Vector<Enemy> ets = new Vector<Enemy>() ;
	//�������ӵ�����
	public Vector<Shot> ss = new Vector<Shot>();
	//���˷����ӵ����Լ���ɵ�
	//��������ӵ���Ӧ���ڸմ���̹�˺͵��˵�̹���ӵ�����֮��
	
	public Enemy(int x,int y){
		super(x,y);
	}
	
	//�õ�MyPanel�ϵ��˵�̹������
	//���˵�̹��һ������������Ҫ���������������>�ҵ���̹�˴����ĺ���
	public void setEts(Vector<Enemy> vv){
		this.ets=vv;
	}
	
	@Override
	public void run() {

		while(true){
			
			switch(this.derect){
			case 0:
				//˵��̹�����������ƶ�
				for(int i=0;i<30;i++){
					if(y>0 && !this.isTouchOtherEnemy()){
						y-=speed;
					}
					try {//�����ܿ���ʧ������
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				break;
			case 1:
				//��
				for(int i=0;i<30;i++){
					if(x<400 && !this.isTouchOtherEnemy()){
						x+=speed;
					}
					try {//�����ܿ���ʧ������
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
					try {//�����ܿ���ʧ������
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
					try {//�����ܿ���ʧ������
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
			
			
			//����Ҫ����ı仯�������
			this.derect=(int)(Math.random()*4);
		
			//�жϵ���̹���Ƿ�����,���߳̽���
			if(!this.isLive){
				break;//��̹���������˳��߳�
			}

		}
		
		
	}
	//�ж��Ƿ������˱�ĵ���̹��,��Ҫ���ʵ��ⲿ���磬���Զ���һ���µĳ�Ա����vector
	public boolean isTouchOtherEnemy(){
		boolean b = false;
		switch (this.derect) {
		case 0:
			//��̹�����ϣ�ȡ�����б��̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				Enemy et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.derect==0||et.derect==2)
					{
						//���
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
			//̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				Enemy et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.derect==0||et.derect==2)
					{
						//�ϵ�
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//�µ� 
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
			//̹������
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				Enemy et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.derect==0||et.derect==2)
					{
						//�ҵ����
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						//�ҵ��ҵ�
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
			//����
			//ȡ�����еĵ���̹��
			for(int i=0;i<ets.size();i++)
			{
				//ȡ����һ��̹��
				Enemy et=ets.get(i);
				//��������Լ�
				if(et!=this)
				{
					//������˵ķ��������»�������
					if(et.derect==0||et.derect==2)
					{
						//�ҵ���һ��
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						//��һ��
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.derect==3||et.derect==1)
					{
						//��һ��
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
