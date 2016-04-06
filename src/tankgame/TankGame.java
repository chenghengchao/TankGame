package tankgame;
import javax.swing.*;
import java.awt.*;


public class TankGame extends JFrame{
	MyPanel mp =null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankGame tg = new TankGame();
		

	}
	public TankGame()
	{
		mp = new MyPanel();
		this.add(mp);
		this.setSize(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
//Panel
class MyPanel extends JPanel
{
	//����һ���ҵ�̹��
	Hero hero = null;
	//���캯��
	public MyPanel()
	{
		hero = new Hero(10,10);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0,0,400,300);
		this.drawTank(hero.getX(), hero.getY(), g, 0, 1);
	
	}
	public void drawTank(int x, int y, Graphics g,int direct,int type)
	{
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
			
		}
		switch(direct)
		{
		case 0:
			//g.setColor(Color.CYAN);
			//����̹��
			//1.��������ľ���
			g.fill3DRect(x, y, 5, 30,false);
			//�ұ߾���
			g.fill3DRect(x+15, y, 5, 30,false);
			//�м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//Բ��
			g.fillOval(x+4, x+10, 10, 10);
			g.drawLine(x+10, x+15, x+10, x);
			break;
			
		}
		
	}
}

class Tank
{
	int x = 0;//������
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	int y = 0;//������
	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
}
class Hero extends Tank
{
	public Hero(int x, int y)
	{
		super(x,y);
	}
}
