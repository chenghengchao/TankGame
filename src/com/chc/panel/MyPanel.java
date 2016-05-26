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
//�����ӵ���������panelӦ�������߳�
public class MyPanel extends JPanel implements KeyListener,Runnable{
	
	Hero hero = null;
	//tank ������panel���棬��Ϊ��Ա����
	
	
	Vector<Bomb> bombs = new Vector<Bomb>();
	
	
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;//��ʼ���ŵ����캯�����棬����ͼƬ���һ��ը����ը����
	//һ�����󣬲�����panel�ϣ�����һ��ը����
	
	//���˵�tank��
	public Vector<Enemy> ets = new Vector<Enemy>();
	int enSize = 5;
	
	//������ϱ�ը������ͼƬ����Ϊ��Ա����
	public MyPanel(){
		
		//�ָ���¼,���ڴ˹��캯���ĵ�һ��
		Recoder.getRec();
		hero = new Hero(100,150);//��������ʼ��
	
		//��ʼ������̹��
		for(int i=0;i<enSize;i++){
			Enemy em = new Enemy((i+1)*50,0);
			em.setColor(0);
			em.setDerect(2);//�ճ�������
			
			//��Myanel �ĵ���̹�����������õ���̹��
			em.setEts(ets);
			Thread t =new Thread(em);
			t.start();
			//������̹�˼����ӵ�
			Shot s = new Shot(em.getX()+10,em.getY()+30,2);
			em.ss.add(s);
			Thread t2 = new Thread(s);
			t2.start();
			ets.add(em);
		}
		//�ɴ�С��ը
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
		//��������
		AudioPlay ap = new AudioPlay("D:\\111.wav");
		ap.start();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
	
		//������ʾ��Ϣ
		this.showInfo(g);
		//draw a tank->a function
		if(hero.isLive){
			this.drawTank(hero.getX(),hero.getY(),g,this.hero.getDerect(),1);
		}
		
		//��ss��ȡ��ÿһ���ӵ�������
		for(int i=0;i<hero.ss.size();i++){
			
			//ȡ��һ��
			Shot temp = hero.ss.get(i);
			//����һ���ӵ� 
			if(temp!=null && temp.isLive){
				//�ӵ������ڣ������쳣
				g.draw3DRect(temp.getX(), temp.getY(), 1, 1, false);
				
				//����������ͣ�޸����꣬�����߳�
			}
			//����ӵ�������дi������temp
			if(!temp.isLive){
				hero.ss.remove(temp);
			}
		}
		
		//����ը��
		for(int i=0;i<bombs.size();i++){
			//ȡ��ը��
			Bomb bomb = bombs.get(i);
			if(bomb.life>6){
				g.drawImage(image1, bomb.getX(), bomb.getY(), 30, 30, this);
			}else if(bomb.life>3){
				g.drawImage(image2, bomb.getX(), bomb.getY(), 30, 30, this);
			}else{
				g.drawImage(image3, bomb.getX(), bomb.getY(), 30, 30, this);
			}
			//����ֵ����
			bomb.lifeDown();
			if(bomb.life==0){
				//����ֵΪ0���Ͱ�ը����Vector��remove
			
				bombs.remove(bomb);
			}
			
		}
		
		
		//������̹�ˣ������仯����������С
		for(int i=0;i<ets.size();i++){
			
			Enemy et = ets.get(i);
			if(et.isLive){
				this.drawTank(et.getX(), et.getY(), g, et.getDerect(), 0);
			
			
				//�ٻ������˵��ӵ�
				for(int j=0;j<et.ss.size();j++){
					//ȡ���ӵ�
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

	//�ж��ҵ��ӵ��Ƿ���е��˵�̹��
	
	public void hitMe(){
		for(int i=0;i<ets.size();i++){
			Enemy em = ets.get(i);
			
			for(int j=0;j<em.ss.size();j++){
				//ȡ���ӵ�
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

		//������ʾ̹�ˣ�������ս����
		this.drawTank(80, 330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recoder.getEnNum()+"", 110, 350);
		
		this.drawTank(130, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recoder.getMyLife()+"", 160, 350);
	//��������ܳɼ�
		g.setColor(Color.black);
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString("�����ܳɼ�", 420, 30);
		
		this.drawTank(420, 60, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recoder.getAllEnNum()+"", 460, 80);
	
	}
	public void hitEnemy(){
		//�Ƿ���е���̹�ˣ��ӵ���̹�ˣ���Ҫ�ж�
		for(int i =0;i<hero.ss.size();i++){
			//ȡ���ӵ�
			Shot myShot = hero.ss.get(i);
			//�ж��ӵ��Ƿ񻹻��ţ���һ���˻������أ�
			if(myShot.isLive){
				//ȡ��ÿһ��̹�ˡ���֮�ж�
				for(int j=0;j<ets.size();j++){
					//ȡ��̹��
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
	
	//�ж��ӵ��Ƿ���е���̹��
	//����д���ˣ���������ã���run������������ʱ���õ�
	public boolean hitTank(Shot s, Tank et){
		
		boolean b = false;
		//�ж�̹�˵ķ���
		switch(et.getDerect()){
		//��������
		case 0:
		case 2:
			//���У��ӵ�����������̹������
			if(s.getX()>et.getX()&&s.getX()<et.getX()+20&&s.getY()>et.getY()&&s.getY()<et.getY()+30){
				s.isLive=false;
				et.isLive=false;
//				Recoder.reduceEnNum();
//				Recoder.addEnNumRec();
				b=true;
				//����ը������Vector,��Ҫȷ��ը��λ��
				Bomb bomb = new Bomb(et.getX(),et.getY());//�ѱ����е�̹�����괫��ȥ
				bombs.add(bomb);
				
			}
			break;
		//��������
		case 1:
		case 3:
			if(s.getX()>et.getX()&&s.getX()<et.getX()+30&&s.getY()>et.getY()&&s.getY()<et.getY()+20){
				//���У��ӵ�����������̹������
				s.isLive=false;
				et.isLive=false;
//				Recoder.reduceEnNum();
//				Recoder.addEnNumRec();
				b = true;
				Bomb bomb = new Bomb(et.getX(),et.getY());//�ѱ����е�̹�����괫��ȥ
				//һ��Ҫ�Ž�ȥ
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
		case 1://�� ��
			//��������ľ���
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+30, y+10);		
			break;
		case 2://�� ��
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15,y,5,30,false);
			g.fill3DRect(x+5, y+5, 10, 20,false);
			
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
		case 3://�� ��
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

		////0 ��, 1 ��,2��,3 ��
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

		//���ӵ����ж�J��
		if (e.getKeyCode()==KeyEvent.VK_J){
			//����5���ӵ������Ǵ����û�ˣ���Ҫ��һ�����
			if(this.hero.ss.size()<=5){
				this.hero.shotEnemy();
			}
		}
		
		
		//�������»���
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
			//���»���
			this.repaint();
		}
	}

}
