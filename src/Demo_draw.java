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
 * 绘图原理
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
	
	public void paint(Graphics g){//g 画笔,自动调用
		//初始化画笔。不可少
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
//		g.drawImage(im,80,80,80,80,this);//参数的含义和图片路径
		g.setColor(Color.red);
		g.setFont(new Font("微软雅黑",Font.BOLD,30));
		g.drawString("祖国万岁", 100, 100);
		
	}
	
}
