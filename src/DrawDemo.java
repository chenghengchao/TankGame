import java.awt.*;

import javax.swing.*;

//import tankgame.MyPanel;


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
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
//�����Լ��Ļ���
class MyPanel extends JPanel
{
	public void paint(Graphics g)//һ֧����
	{
		super.paint(g);
		g.drawOval(10, 10, 30, 30);
		
	}
}
