package com.chc.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.chc.entities.Bomb;
import com.chc.entities.Enemy;
import com.chc.entities.Hero;
import com.chc.entities.Recoder;
import com.chc.entities.Shot;
import com.chc.entities.Tank;
import com.chc.entities.AudioPlay;
//想让子弹动起来，panel应该做成线程
public class MyPanel extends JPanel implements KeyListener,Runnable{
	
	Hero hero = null;
	//tank 生活在panel里面，作为成员变量
	
	
	Vector<Bomb> bombs = new Vector<Bomb>();
	
	
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;//初始化放到构造函数里面，三张图片组成一颗炸弹，炸弹是
	//一种现象，产生在panel上，定义一个炸弹类
	
	//敌人的tank组
	public Vector<Enemy> ets = new Vector<Enemy>();
	int enSize = 5;
	
	//在面板上爆炸，所以图片定义为成员变量
	public MyPanel(){
		
		//恢复记录,放在此构造函数的第一句
		Recoder.getRec();
		hero = new Hero(100,150);//构造器初始化
	
		//初始化敌人坦克
		for(int i=0;i<enSize;i++){
			Enemy em = new Enemy((i+1)*50,0);
			em.setColor(0);
			em.setDerect(2);//刚出来向下
			
			//将Myanel 的敌人坦克向量交给该敌人坦克
			em.setEts(ets);
			Thread t =new Thread(em);
			t.start();
			//给敌人坦克加入子弹
			Shot s = new Shot(em.getX()+10,em.getY()+30,2);
			em.ss.add(s);
			Thread t2 = new Thread(s);
			t2.start();
			ets.add(em);
		}
		//由大到小爆炸
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		try {
			image1= ImageIO.read(new File("bomb_1.gif"));
			image2= ImageIO.read(new File("bomb_2.gif"));
			image3= ImageIO.read(new File("bomb_3.gif"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		//播放声音
		AudioPlay ap = new AudioPlay("D:\\111.wav");
		ap.start();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
	
		//画出提示信息
		this.showInfo(g);
		//draw a tank->a function
		if(hero.isLive){
			this.drawTank(hero.getX(),hero.getY(),g,this.hero.getDerect(),1);
		}
		
		//从ss中取出每一颗子弹并绘制
		for(int i=0;i<hero.ss.size();i++){
			
			//取出一颗
			Shot temp = hero.ss.get(i);
			//画出一颗子弹 
			if(temp!=null && temp.isLive){
				//子弹不存在，会有异常
				g.draw3DRect(temp.getX(), temp.getY(), 1, 1, false);
				
				//动起来，不停修改坐标，做成线程
			}
			//清除子弹，不能写i，而是temp
			if(!temp.isLive){
				hero.ss.remove(temp);
			}
		}
		
		//画出炸弹
		for(int i=0;i<bombs.size();i++){
			//取出炸弹
			Bomb bomb = bombs.get(i);
			if(bomb.life>6){
				g.drawImage(image1, bomb.getX(), bomb.getY(), 30, 30, this);
			}else if(bomb.life>3){
				g.drawImage(image2, bomb.getX(), bomb.getY(), 30, 30, this);
			}else{
				g.drawImage(image3, bomb.getX(), bomb.getY(), 30, 30, this);
			}
			//生命值减少
			bomb.lifeDown();
			if(bomb.life==0){
				//生命值为0，就把炸弹从Vector中remove
			
				bombs.remove(bomb);
			}
			
		}
		
		
		//画敌人坦克，数量变化，用向量大小
		for(int i=0;i<ets.size();i++){
			
			Enemy et = ets.get(i);
			if(et.isLive){
				this.drawTank(et.getX(), et.getY(), g, et.getDerect(), 0);
			
			
				//再画出敌人的子弹
				for(int j=0;j<et.ss.size();j++){
					//取出子弹
					Shot es= et.ss.get(j);
					if(es.isLive){
						g.draw3DRect(es.getX(), es.getY(), 1, 1, false);
					}else{
						et.ss.remove(es);
					}
					
				}
			}
		}
	}

	//判断我的子弹是否击中敌人的坦克
	
	public void hitMe(){
		for(int i=0;i<ets.size();i++){
			Enemy em = ets.get(i);
			
			for(int j=0;j<em.ss.size();j++){
				//取出子弹
				Shot shot = em.ss.get(j);
				if(hero.isLive){
					if(this.hitTank(shot, hero)){
						Recoder.reduceMyNum();
						//Recoder.addEnNumRec();
					}
						
				}
			}
		}
	}
	
	public void showInfo(Graphics g){

		//画出提示坦克，不参与战斗！
		this.drawTank(80, 330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recoder.getEnNum()+"", 110, 350);
		
		this.drawTank(130, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recoder.getMyLife()+"", 160, 350);
	//画出玩家总成绩
		g.setColor(Color.black);
		g.setFont(new Font("宋体",Font.BOLD,20));
		g.drawString("您的总成绩", 420, 30);
		
		this.drawTank(420, 60, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recoder.getAllEnNum()+"", 460, 80);
	
	}
	public void hitEnemy(){
		//是否击中敌人坦克，子弹，坦克，都要判断
		for(int i =0;i<hero.ss.size();i++){
			//取出子弹
			Shot myShot = hero.ss.get(i);
			//判断子弹是否还活着，万一死了还工作呢！
			if(myShot.isLive){
				//取出每一个坦克。与之判断
				for(int j=0;j<ets.size();j++){
					//取出坦克
					Enemy et = ets.get(j);
				
					if(et.isLive){
						if(this.hitTank(myShot,et)){
							Recoder.reduceEnNum();
							Recoder.addEnNumRec();
						}
					}
				}
			}
		}
	}
	
	//判断子弹是否击中敌人坦克
	//函数写好了，在哪里调用？？run函数，它是随时调用的
	public boolean hitTank(Shot s, Tank et){
		
		boolean b = false;
		//判断坦克的方向
		switch(et.getDerect()){
		//向上向下
		case 0:
		case 2:
			//击中：子弹死亡，敌人坦克死亡
			if(s.getX()>et.getX()&&s.getX()<et.getX()+20&&s.getY()>et.getY()&&s.getY()<et.getY()+30){
				s.isLive=false;
				et.isLive=false;
//				Recoder.reduceEnNum();
//				Recoder.addEnNumRec();
				b=true;
				//创建炸弹放入Vector,需要确定炸弹位置
				Bomb bomb = new Bomb(et.getX(),et.getY());//把被击中的坦克坐标传进去
				bombs.add(bomb);
				
			}
			break;
		//向左向右
		case 1:
		case 3:
			if(s.getX()>et.getX()&&s.getX()<et.getX()+30&&s.getY()>et.getY()&&s.getY()<et.getY()+20){
				//击中：子弹死亡，敌人坦克死亡
				s.isLive=false;
				et.isLive=false;
//				Recoder.reduceEnNum();
//				Recoder.addEnNumRec();
				b = true;
				Bomb bomb = new Bomb(et.getX(),et.getY());//把被击中的坦克坐标传进去
				//一定要放进去
				bombs.add(bomb);
			}
			break;
		}
		return b;
	}
	
	public void drawTank(int x,int y, Graphics g,int direct, int type) {
		
		switch(type){
		case 0:
			g.setColor(Color.CYAN);
			break;
		case 1:
			g.setColor(Color.YELLOW);
			break;
			
		}
		switch(direct){
		case 0:
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15,y,5,30,false);
			g.fill3DRect(x+5, y+5, 10, 20,false);			
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y);
			break;
		case 1://向 →
			//画出上面的矩形
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+30, y+10);		
			break;
		case 2://向 ↓
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15,y,5,30,false);
			g.fill3DRect(x+5, y+5, 10, 20,false);
			
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
		case 3://向 ←
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x, y+10);
			break;
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		////0 ↑, 1 →,2↓,3 ←
		if(e.getKeyCode()==KeyEvent.VK_W){
			this.hero.setDerect(0);
			this.hero.moveUp();
		}else if (e.getKeyCode()==KeyEvent.VK_D){
			this.hero.setDerect(1);
			this.hero.moveRight();
		}else if (e.getKeyCode()==KeyEvent.VK_S){
			this.hero.setDerect(2);
			this.hero.moveDown();
		}else if (e.getKeyCode()==KeyEvent.VK_A){
			this.hero.setDerect(3);
			this.hero.moveLeft();
		}

		//发子弹，判断J键
		if (e.getKeyCode()==KeyEvent.VK_J){
			//控制5颗子弹，但是打完就没了，需要加一个清除
			if(this.hero.ss.size()<=5){
				this.hero.shotEnemy();
			}
		}
		
		
		//必须重新绘制
		this.repaint();
		
		//
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//

			this.hitEnemy();
			//
			this.hitMe();
			//重新绘制
			this.repaint();
		}
	}

}
