package com.chc.game;
/**
 * 
 * 暂停和继续：点击暂停，子弹和坦克的速度设置为0，并让坦克方向不变
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.chc.entities.Recoder;
import com.chc.panel.HintPanel;
import com.chc.panel.MyPanel;

public class TankTwo extends JFrame implements ActionListener{
	
	MyPanel mp =null;

	HintPanel hp = null;
	
	JMenuBar jmb = null;
	JMenu jm1 = null;
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;
	JMenuItem jmi4= null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankTwo tank = new TankTwo();

	}
	public TankTwo(){
/*		mp = new MyPanel();
		//didong mp线程
		Thread t = new Thread(mp);
		t.start();
		this.add(mp);
		this.setTitle("坦克大战");
		this.addKeyListener(mp);*/
		//创建菜单及菜单选项
		jmb = new JMenuBar();
		jm1 = new JMenu("游戏（G）");
		//设置快捷方式
		jm1.setMnemonic('G');
		jmi1 = new JMenuItem("开始游戏（G）");
		jmi2 = new JMenuItem("退出游戏（E）");
		jmi3 = new JMenuItem("存盘退出（C）");
		jmi4 = new JMenuItem("续上局（S）");
		
		jmi2.setMnemonic('E');
		jmi1.addActionListener(this);
		jmi1.setActionCommand("new");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveexit");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("congame");
		//添加
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);
		

		hp = new HintPanel();
		Thread t = new Thread(hp);
		t.start();
		this.add(hp);
		
		this.setJMenuBar(jmb);
		this.setSize(600, 500);
		this.setTitle("坦克大战");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("new")){
			//点击了新游戏，创建战场
			mp = new MyPanel();
			//didong mp线程
			Thread t = new Thread(mp);
			t.start();
			//加入之前先删掉旧的
			this.remove(hp);
			this.add(mp);
			
			this.addKeyListener(mp);
			//刷新JFrame
			this.setVisible(true);
		}else if(e.getActionCommand().equals("exit")){
			//点击了推出按钮
			//保存击毁敌人数量
			Recoder.keepRec();
			System.exit(0);
		}else if(e.getActionCommand().equals("saveexit")){
			//保存击毁敌人的数量和敌人的坐标
			Recoder rd = new Recoder();
			rd.setEts(mp.ets);
			rd.keepRecAndEnemy();
			//Recoder.setEts(mp.ets);
			//Recoder.keepRecAndEnemy();
			System.exit(0);
		}else if(e.getActionCommand().equals("congame")){
			//恢复时，想办法再执行Mypanel里面的初始化敌人坦克代码
			
		}
	}

}
