import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

//import tankgame.MyPanel;
/*
 * java 绘图原理
 * 
 * */

public class DrawDemo extends JFrame{
	MyPanel mp = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawDemo dd = new DrawDemo();

	}
	public DrawDemo ()
	{
		mp = new MyPanel();
		this.add(mp);
		
		this.addKeyListener(mp);
		
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
//创建自己的画板
class MyPanel extends JPanel implements KeyListener
{
	int x =10;
	int y =10;
	public void paint(Graphics g)//一支画笔
	{
		super.paint(g);
		g.drawOval(x, y, 30, 30);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			y+=10;
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
