import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * 
 * ��ͼԭ��
 */
public class Demo_draw extends JFrame{
	MyPanel1 mp = null;

	public static void main(String[] args) {
		Demo_draw dd = new Demo_draw();

		
	}
	public Demo_draw(){
		mp = new MyPanel1();
		this.add(mp);
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}

}

class MyPanel1 extends JPanel{
	
	public void paint(Graphics g){//g ����,�Զ�����
		//��ʼ�����ʡ�������
		super.paint(g);
//		g.drawOval(10, 10, 30, 30);
//		g.drawLine(10, 10, 40, 40);
		
		//g.draw3DRect(10, 10, 30, 25, false);
		
//		g.setColor(Color.YELLOW);
//		g.fillRect(10, 10, 30, 25);
//		g.setColor(Color.RED);
//		g.fillRect(70, 70, 30, 25);
		//draw a image
//		Image im = Toolkit.getDefaultToolkit().getImage
//				(Panel.class.getResource("/bj.jpg"));
//		g.drawImage(im,80,80,80,80,this);//�����ĺ����ͼƬ·��
		g.setColor(Color.red);
		g.setFont(new Font("΢���ź�",Font.BOLD,30));
		g.drawString("�������", 100, 100);
		
	}
	
}
