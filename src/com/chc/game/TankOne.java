package com.chc.game;

import javax.swing.JFrame;

import com.chc.panel.MyPanel;

public class TankOne extends JFrame{
	
	MyPanel mp =null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankOne tank = new TankOne();

	}
	public TankOne(){
		mp = new MyPanel();
		
		this.add(mp);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
