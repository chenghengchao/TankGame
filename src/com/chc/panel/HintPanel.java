package com.chc.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * ���ڿ�ʼʱ����ʾ,�����߳���Ϊ����˸
 */
public class HintPanel extends JPanel implements Runnable{

	int times=0;
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//��ʾ��Ϣ
		if(times%2==0){
			
			g.setColor(Color.YELLOW);
			g.setFont(new Font("������κ",Font.BOLD,30));
			g.drawString("Stage1:", 150, 150);
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			times++;
			this.repaint();
		}
	}
}
