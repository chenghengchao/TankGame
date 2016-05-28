package com.chc.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * 用于开始时的提示,做成线程是为了闪烁
 */
public class HintPanel extends JPanel implements Runnable{

	int times=0;
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//提示信息
		if(times%2==0){
			
			g.setColor(Color.YELLOW);
			g.setFont(new Font("华文新魏",Font.BOLD,30));
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
