package com.chc.game;
/**
 * 
 * ��ͣ�ͼ����������ͣ���ӵ���̹�˵��ٶ�����Ϊ0������̹�˷��򲻱�
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
		//didong mp�߳�
		Thread t = new Thread(mp);
		t.start();
		this.add(mp);
		this.setTitle("̹�˴�ս");
		this.addKeyListener(mp);*/
		//�����˵����˵�ѡ��
		jmb = new JMenuBar();
		jm1 = new JMenu("��Ϸ��G��");
		//���ÿ�ݷ�ʽ
		jm1.setMnemonic('G');
		jmi1 = new JMenuItem("��ʼ��Ϸ��G��");
		jmi2 = new JMenuItem("�˳���Ϸ��E��");
		jmi3 = new JMenuItem("�����˳���C��");
		jmi4 = new JMenuItem("���Ͼ֣�S��");
		
		jmi2.setMnemonic('E');
		jmi1.addActionListener(this);
		jmi1.setActionCommand("new");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveexit");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("congame");
		//���
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
		this.setTitle("̹�˴�ս");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("new")){
			//���������Ϸ������ս��
			mp = new MyPanel();
			//didong mp�߳�
			Thread t = new Thread(mp);
			t.start();
			//����֮ǰ��ɾ���ɵ�
			this.remove(hp);
			this.add(mp);
			
			this.addKeyListener(mp);
			//ˢ��JFrame
			this.setVisible(true);
		}else if(e.getActionCommand().equals("exit")){
			//������Ƴ���ť
			//������ٵ�������
			Recoder.keepRec();
			System.exit(0);
		}else if(e.getActionCommand().equals("saveexit")){
			//������ٵ��˵������͵��˵�����
			Recoder rd = new Recoder();
			rd.setEts(mp.ets);
			rd.keepRecAndEnemy();
			//Recoder.setEts(mp.ets);
			//Recoder.keepRecAndEnemy();
			System.exit(0);
		}else if(e.getActionCommand().equals("congame")){
			//�ָ�ʱ����취��ִ��Mypanel����ĳ�ʼ������̹�˴���
			
		}
	}

}
